package androidx.media3.extractor.text.ttml;

import android.text.Layout;
import androidx.core.view.MotionEventCompat;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes24.dex */
public final class TtmlParser implements SubtitleParser {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    private static final int DEFAULT_CELL_ROWS = 15;
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlParser";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    static final Pattern SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);

    public TtmlParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] data, int offset, int length, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> output) {
        Subtitle subtitle = parseToLegacySubtitle(data, offset, length);
        LegacySubtitleUtil.toCuesWithTiming(subtitle, outputOptions, output);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public Subtitle parseToLegacySubtitle(byte[] data, int offset, int length) {
        ArrayDeque<TtmlNode> nodeStack;
        int cellRows;
        FrameAndTickRate frameAndTickRate;
        FrameAndTickRate frameAndTickRate2;
        try {
            XmlPullParser xmlParser = this.xmlParserFactory.newPullParser();
            Map<String, TtmlStyle> globalStyles = new HashMap<>();
            Map<String, TtmlRegion> regionMap = new HashMap<>();
            Map<String, String> imageMap = new HashMap<>();
            regionMap.put("", new TtmlRegion(""));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data, offset, length);
            xmlParser.setInput(inputStream, null);
            ArrayDeque<TtmlNode> nodeStack2 = new ArrayDeque<>();
            int eventType = xmlParser.getEventType();
            FrameAndTickRate frameAndTickRate3 = DEFAULT_FRAME_AND_TICK_RATE;
            int cellRows2 = 15;
            TtmlSubtitle ttmlSubtitle = null;
            int unsupportedNodeDepth = 0;
            TtsExtent ttsExtent = null;
            int eventType2 = eventType;
            while (eventType2 != 1) {
                TtmlNode parent = nodeStack2.peek();
                if (unsupportedNodeDepth == 0) {
                    String name = xmlParser.getName();
                    TtsExtent ttsExtent2 = ttsExtent;
                    if (eventType2 == 2) {
                        if (!TtmlNode.TAG_TT.equals(name)) {
                            cellRows = cellRows2;
                            frameAndTickRate = frameAndTickRate3;
                        } else {
                            FrameAndTickRate frameAndTickRate4 = parseFrameAndTickRates(xmlParser);
                            int cellRows3 = parseCellRows(xmlParser, 15);
                            ttsExtent2 = parseTtsExtent(xmlParser);
                            cellRows = cellRows3;
                            frameAndTickRate = frameAndTickRate4;
                        }
                        if (isSupportedTag(name)) {
                            if (TtmlNode.TAG_HEAD.equals(name)) {
                                frameAndTickRate2 = frameAndTickRate;
                                nodeStack = nodeStack2;
                                parseHeader(xmlParser, globalStyles, cellRows, ttsExtent2, regionMap, imageMap);
                            } else {
                                frameAndTickRate2 = frameAndTickRate;
                                nodeStack = nodeStack2;
                                try {
                                    TtmlNode node = parseNode(xmlParser, parent, regionMap, frameAndTickRate2);
                                    nodeStack.push(node);
                                    if (parent != null) {
                                        parent.addChild(node);
                                    }
                                } catch (SubtitleDecoderException e) {
                                    Log.w(TAG, "Suppressing parser error", e);
                                    unsupportedNodeDepth++;
                                    frameAndTickRate3 = frameAndTickRate2;
                                    cellRows2 = cellRows;
                                    ttsExtent = ttsExtent2;
                                }
                            }
                            frameAndTickRate3 = frameAndTickRate2;
                            cellRows2 = cellRows;
                            ttsExtent = ttsExtent2;
                        } else {
                            Log.i(TAG, "Ignoring unsupported tag: " + xmlParser.getName());
                            unsupportedNodeDepth++;
                            frameAndTickRate3 = frameAndTickRate;
                            nodeStack = nodeStack2;
                            cellRows2 = cellRows;
                            ttsExtent = ttsExtent2;
                        }
                    } else {
                        int eventType3 = eventType2;
                        nodeStack = nodeStack2;
                        if (eventType3 == 4) {
                            ((TtmlNode) Assertions.checkNotNull(parent)).addChild(TtmlNode.buildTextNode(xmlParser.getText()));
                        } else if (eventType3 == 3) {
                            if (xmlParser.getName().equals(TtmlNode.TAG_TT)) {
                                ttmlSubtitle = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull(nodeStack.peek()), globalStyles, regionMap, imageMap);
                            }
                            nodeStack.pop();
                            ttsExtent = ttsExtent2;
                        }
                        ttsExtent = ttsExtent2;
                    }
                } else {
                    TtsExtent ttsExtent3 = ttsExtent;
                    int eventType4 = eventType2;
                    nodeStack = nodeStack2;
                    if (eventType4 == 2) {
                        unsupportedNodeDepth++;
                        ttsExtent = ttsExtent3;
                    } else if (eventType4 != 3) {
                        ttsExtent = ttsExtent3;
                    } else {
                        unsupportedNodeDepth--;
                        ttsExtent = ttsExtent3;
                    }
                }
                xmlParser.next();
                eventType2 = xmlParser.getEventType();
                nodeStack2 = nodeStack;
            }
            return (Subtitle) Assertions.checkNotNull(ttmlSubtitle);
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        } catch (XmlPullParserException xppe) {
            throw new IllegalStateException("Unable to decode source", xppe);
        }
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlParser) {
        int frameRate = 30;
        String frameRateString = xmlParser.getAttributeValue(TTP, "frameRate");
        if (frameRateString != null) {
            frameRate = Integer.parseInt(frameRateString);
        }
        float frameRateMultiplier = 1.0f;
        String frameRateMultiplierString = xmlParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (frameRateMultiplierString != null) {
            String[] parts = Util.split(frameRateMultiplierString, " ");
            Assertions.checkArgument(parts.length == 2, "frameRateMultiplier doesn't have 2 parts");
            float numerator = Integer.parseInt(parts[0]);
            float denominator = Integer.parseInt(parts[1]);
            frameRateMultiplier = numerator / denominator;
        }
        int subFrameRate = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String subFrameRateString = xmlParser.getAttributeValue(TTP, "subFrameRate");
        if (subFrameRateString != null) {
            subFrameRate = Integer.parseInt(subFrameRateString);
        }
        int tickRate = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String tickRateString = xmlParser.getAttributeValue(TTP, "tickRate");
        if (tickRateString != null) {
            tickRate = Integer.parseInt(tickRateString);
        }
        return new FrameAndTickRate(frameRate * frameRateMultiplier, subFrameRate, tickRate);
    }

    private static int parseCellRows(XmlPullParser xmlParser, int defaultValue) {
        String cellResolution = xmlParser.getAttributeValue(TTP, "cellResolution");
        if (cellResolution == null) {
            return defaultValue;
        }
        Matcher cellResolutionMatcher = CELL_RESOLUTION.matcher(cellResolution);
        if (!cellResolutionMatcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + cellResolution);
            return defaultValue;
        }
        boolean z = true;
        try {
            int columns = Integer.parseInt((String) Assertions.checkNotNull(cellResolutionMatcher.group(1)));
            int rows = Integer.parseInt((String) Assertions.checkNotNull(cellResolutionMatcher.group(2)));
            if (columns == 0 || rows == 0) {
                z = false;
            }
            Assertions.checkArgument(z, "Invalid cell resolution " + columns + " " + rows);
            return rows;
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + cellResolution);
            return defaultValue;
        }
    }

    private static TtsExtent parseTtsExtent(XmlPullParser xmlParser) {
        String ttsExtent = XmlPullParserUtil.getAttributeValue(xmlParser, TtmlNode.ATTR_TTS_EXTENT);
        if (ttsExtent == null) {
            return null;
        }
        Matcher extentMatcher = PIXEL_COORDINATES.matcher(ttsExtent);
        if (!extentMatcher.matches()) {
            Log.w(TAG, "Ignoring non-pixel tts extent: " + ttsExtent);
            return null;
        }
        try {
            int width = Integer.parseInt((String) Assertions.checkNotNull(extentMatcher.group(1)));
            int height = Integer.parseInt((String) Assertions.checkNotNull(extentMatcher.group(2)));
            return new TtsExtent(width, height);
        } catch (NumberFormatException e) {
            Log.w(TAG, "Ignoring malformed tts extent: " + ttsExtent);
            return null;
        }
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser xmlParser, Map<String, TtmlStyle> globalStyles, int cellRows, TtsExtent ttsExtent, Map<String, TtmlRegion> globalRegions, Map<String, String> imageMap) throws IOException, XmlPullParserException {
        do {
            xmlParser.next();
            if (XmlPullParserUtil.isStartTag(xmlParser, "style")) {
                String parentStyleId = XmlPullParserUtil.getAttributeValue(xmlParser, "style");
                TtmlStyle style = parseStyleAttributes(xmlParser, new TtmlStyle());
                if (parentStyleId != null) {
                    for (String id : parseStyleIds(parentStyleId)) {
                        style.chain(globalStyles.get(id));
                    }
                }
                String styleId = style.getId();
                if (styleId != null) {
                    globalStyles.put(styleId, style);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlParser, "region")) {
                TtmlRegion ttmlRegion = parseRegionAttributes(xmlParser, cellRows, ttsExtent);
                if (ttmlRegion != null) {
                    globalRegions.put(ttmlRegion.id, ttmlRegion);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlParser, imageMap);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlParser, TtmlNode.TAG_HEAD));
        return globalStyles;
    }

    private static void parseMetadata(XmlPullParser xmlParser, Map<String, String> imageMap) throws IOException, XmlPullParserException {
        String id;
        do {
            xmlParser.next();
            if (XmlPullParserUtil.isStartTag(xmlParser, "image") && (id = XmlPullParserUtil.getAttributeValue(xmlParser, "id")) != null) {
                String encodedBitmapData = xmlParser.nextText();
                imageMap.put(id, encodedBitmapData);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlParser, TtmlNode.TAG_METADATA));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01c0, code lost:
    
        if (r6.equals(androidx.media3.extractor.text.ttml.TtmlNode.VERTICAL_RL) != false) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.extractor.text.ttml.TtmlRegion parseRegionAttributes(org.xmlpull.v1.XmlPullParser r26, int r27, androidx.media3.extractor.text.ttml.TtmlParser.TtsExtent r28) {
        /*
            Method dump skipped, instructions count: 664
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseRegionAttributes(org.xmlpull.v1.XmlPullParser, int, androidx.media3.extractor.text.ttml.TtmlParser$TtsExtent):androidx.media3.extractor.text.ttml.TtmlRegion");
    }

    private static String[] parseStyleIds(String parentStyleIds) {
        String parentStyleIds2 = parentStyleIds.trim();
        return parentStyleIds2.isEmpty() ? new String[0] : Util.split(parentStyleIds2, "\\s+");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0121, code lost:
    
        if (r3.equals(androidx.media3.extractor.text.ttml.TtmlNode.NO_UNDERLINE) != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x016e, code lost:
    
        switch(r9) {
            case 0: goto L93;
            case 1: goto L92;
            default: goto L189;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0172, code lost:
    
        r13 = createIfNull(r13).setRubyPosition(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x017b, code lost:
    
        r13 = createIfNull(r13).setRubyPosition(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b8, code lost:
    
        if (r3.equals(androidx.media3.extractor.text.ttml.TtmlNode.RUBY_DELIMITER) != false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x021a, code lost:
    
        switch(r9) {
            case 0: goto L136;
            case 1: goto L135;
            default: goto L197;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x021e, code lost:
    
        r13 = createIfNull(r13).setTextCombine(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0227, code lost:
    
        r13 = createIfNull(r13).setTextCombine(false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.extractor.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, androidx.media3.extractor.text.ttml.TtmlStyle r13) {
        /*
            Method dump skipped, instructions count: 968
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, androidx.media3.extractor.text.ttml.TtmlStyle):androidx.media3.extractor.text.ttml.TtmlStyle");
    }

    private static TtmlStyle createIfNull(TtmlStyle style) {
        return style == null ? new TtmlStyle() : style;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Layout.Alignment parseAlignment(String alignment) {
        char c;
        String lowerCase = Ascii.toLowerCase(alignment);
        switch (lowerCase.hashCode()) {
            case -1364013995:
                if (lowerCase.equals(TtmlNode.CENTER)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 100571:
                if (lowerCase.equals("end")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3317767:
                if (lowerCase.equals(TtmlNode.LEFT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 108511772:
                if (lowerCase.equals(TtmlNode.RIGHT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 109757538:
                if (lowerCase.equals(TtmlNode.START)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                return Layout.Alignment.ALIGN_NORMAL;
            case 2:
            case 3:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 4:
                return Layout.Alignment.ALIGN_CENTER;
            default:
                return null;
        }
    }

    private static TtmlNode parseNode(XmlPullParser parser, TtmlNode parent, Map<String, TtmlRegion> regionMap, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        long startTime;
        long endTime;
        char c;
        long startTime2 = C.TIME_UNSET;
        long endTime2 = C.TIME_UNSET;
        int attributeCount = parser.getAttributeCount();
        TtmlStyle style = parseStyleAttributes(parser, null);
        long duration = -9223372036854775807L;
        String regionId = "";
        String imageId = null;
        String[] styleIds = null;
        for (int i = 0; i < attributeCount; i++) {
            String attr = parser.getAttributeName(i);
            String value = parser.getAttributeValue(i);
            switch (attr.hashCode()) {
                case -934795532:
                    if (attr.equals("region")) {
                        c = 4;
                        break;
                    }
                    break;
                case 99841:
                    if (attr.equals(ATTR_DURATION)) {
                        c = 2;
                        break;
                    }
                    break;
                case 100571:
                    if (attr.equals("end")) {
                        c = 1;
                        break;
                    }
                    break;
                case 93616297:
                    if (attr.equals(ATTR_BEGIN)) {
                        c = 0;
                        break;
                    }
                    break;
                case 109780401:
                    if (attr.equals("style")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1292595405:
                    if (attr.equals(ATTR_IMAGE)) {
                        c = 5;
                        break;
                    }
                    break;
            }
            c = 65535;
            switch (c) {
                case 0:
                    startTime2 = parseTimeExpression(value, frameAndTickRate);
                    break;
                case 1:
                    endTime2 = parseTimeExpression(value, frameAndTickRate);
                    break;
                case 2:
                    long duration2 = parseTimeExpression(value, frameAndTickRate);
                    duration = duration2;
                    break;
                case 3:
                    String[] ids = parseStyleIds(value);
                    if (ids.length > 0) {
                        styleIds = ids;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (regionMap.containsKey(value)) {
                        regionId = value;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (value.startsWith("#")) {
                        imageId = value.substring(1);
                        break;
                    } else {
                        break;
                    }
            }
        }
        if (parent == null || parent.startTimeUs == C.TIME_UNSET) {
            startTime = startTime2;
        } else {
            if (startTime2 != C.TIME_UNSET) {
                startTime2 += parent.startTimeUs;
            }
            if (endTime2 == C.TIME_UNSET) {
                startTime = startTime2;
            } else {
                endTime2 += parent.startTimeUs;
                startTime = startTime2;
            }
        }
        if (endTime2 == C.TIME_UNSET) {
            if (duration != C.TIME_UNSET) {
                long endTime3 = startTime + duration;
                endTime = endTime3;
            } else if (parent != null && parent.endTimeUs != C.TIME_UNSET) {
                long endTime4 = parent.endTimeUs;
                endTime = endTime4;
            }
            return TtmlNode.buildNode(parser.getName(), startTime, endTime, style, styleIds, regionId, imageId, parent);
        }
        endTime = endTime2;
        return TtmlNode.buildNode(parser.getName(), startTime, endTime, style, styleIds, regionId, imageId, parent);
    }

    private static boolean isSupportedTag(String tag) {
        return tag.equals(TtmlNode.TAG_TT) || tag.equals(TtmlNode.TAG_HEAD) || tag.equals(TtmlNode.TAG_BODY) || tag.equals(TtmlNode.TAG_DIV) || tag.equals(TtmlNode.TAG_P) || tag.equals(TtmlNode.TAG_SPAN) || tag.equals("br") || tag.equals("style") || tag.equals(TtmlNode.TAG_STYLING) || tag.equals(TtmlNode.TAG_LAYOUT) || tag.equals("region") || tag.equals(TtmlNode.TAG_METADATA) || tag.equals("image") || tag.equals("data") || tag.equals(TtmlNode.TAG_INFORMATION);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void parseFontSize(String expression, TtmlStyle out) throws SubtitleDecoderException {
        Matcher matcher;
        char c;
        String[] expressions = Util.split(expression, "\\s+");
        if (expressions.length == 1) {
            matcher = FONT_SIZE.matcher(expression);
        } else if (expressions.length == 2) {
            matcher = FONT_SIZE.matcher(expressions[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + expressions.length + ".");
        }
        if (matcher.matches()) {
            String unit = (String) Assertions.checkNotNull(matcher.group(3));
            switch (unit.hashCode()) {
                case MotionEventCompat.AXIS_GENERIC_6 /* 37 */:
                    if (unit.equals("%")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 3240:
                    if (unit.equals("em")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 3592:
                    if (unit.equals("px")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    out.setFontSizeUnit(1);
                    break;
                case 1:
                    out.setFontSizeUnit(2);
                    break;
                case 2:
                    out.setFontSizeUnit(3);
                    break;
                default:
                    throw new SubtitleDecoderException("Invalid unit for fontSize: '" + unit + "'.");
            }
            out.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + expression + "'.");
    }

    private static float parseShear(String expression) {
        Matcher matcher = SIGNED_PERCENTAGE.matcher(expression);
        if (!matcher.matches()) {
            Log.w(TAG, "Invalid value for shear: " + expression);
            return Float.MAX_VALUE;
        }
        try {
            String percentage = (String) Assertions.checkNotNull(matcher.group(1));
            float value = Float.parseFloat(percentage);
            return Math.min(100.0f, Math.max(-100.0f, value));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse shear: " + expression, e);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00cb, code lost:
    
        if (r11.equals("t") != false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long parseTimeExpression(java.lang.String r16, androidx.media3.extractor.text.ttml.TtmlParser.FrameAndTickRate r17) throws androidx.media3.extractor.text.SubtitleDecoderException {
        /*
            Method dump skipped, instructions count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseTimeExpression(java.lang.String, androidx.media3.extractor.text.ttml.TtmlParser$FrameAndTickRate):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float effectiveFrameRate, int subFrameRate, int tickRate) {
            this.effectiveFrameRate = effectiveFrameRate;
            this.subFrameRate = subFrameRate;
            this.tickRate = tickRate;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
