package androidx.media3.extractor.text.ssa;

import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.ssa.SsaStyle;
import com.google.common.base.Charsets;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes24.dex */
public final class SsaParser implements SubtitleParser {
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    private static final float DEFAULT_MARGIN = 0.05f;
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue:";
    static final String FORMAT_LINE_PREFIX = "Format:";
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    static final String STYLE_LINE_PREFIX = "Style:";
    private static final String TAG = "SsaParser";
    private final SsaDialogueFormat dialogueFormatFromInitializationData;
    private final boolean haveInitializationData;
    private final ParsableByteArray parsableByteArray;
    private float screenHeight;
    private float screenWidth;
    private Map<String, SsaStyle> styles;

    public SsaParser() {
        this(null);
    }

    public SsaParser(List<byte[]> initializationData) {
        this.screenWidth = -3.4028235E38f;
        this.screenHeight = -3.4028235E38f;
        this.parsableByteArray = new ParsableByteArray();
        if (initializationData != null && !initializationData.isEmpty()) {
            this.haveInitializationData = true;
            String formatLine = Util.fromUtf8Bytes(initializationData.get(0));
            Assertions.checkArgument(formatLine.startsWith(FORMAT_LINE_PREFIX));
            this.dialogueFormatFromInitializationData = (SsaDialogueFormat) Assertions.checkNotNull(SsaDialogueFormat.fromFormatLine(formatLine));
            parseHeader(new ParsableByteArray(initializationData.get(1)), Charsets.UTF_8);
            return;
        }
        this.haveInitializationData = false;
        this.dialogueFormatFromInitializationData = null;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] data, int offset, int length, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> output) {
        List<CuesWithTiming> cuesWithTimingBeforeRequestedStartTimeUs;
        List<List<Cue>> cues = new ArrayList<>();
        List<Long> startTimesUs = new ArrayList<>();
        this.parsableByteArray.reset(data, offset + length);
        this.parsableByteArray.setPosition(offset);
        Charset charset = detectUtfCharset(this.parsableByteArray);
        if (!this.haveInitializationData) {
            parseHeader(this.parsableByteArray, charset);
        }
        parseEventBody(this.parsableByteArray, cues, startTimesUs, charset);
        long j = outputOptions.startTimeUs;
        long j2 = C.TIME_UNSET;
        if (j != C.TIME_UNSET && outputOptions.outputAllCues) {
            cuesWithTimingBeforeRequestedStartTimeUs = new ArrayList<>();
        } else {
            cuesWithTimingBeforeRequestedStartTimeUs = null;
        }
        int i = 0;
        while (i < cues.size()) {
            List<Cue> cuesForThisStartTime = cues.get(i);
            if (!cuesForThisStartTime.isEmpty() || i == 0) {
                if (i == cues.size() - 1) {
                    throw new IllegalStateException();
                }
                long startTimeUs = startTimesUs.get(i).longValue();
                long durationUs = startTimesUs.get(i + 1).longValue() - startTimesUs.get(i).longValue();
                if (outputOptions.startTimeUs == j2 || startTimeUs >= outputOptions.startTimeUs) {
                    output.accept(new CuesWithTiming(cuesForThisStartTime, startTimeUs, durationUs));
                } else if (cuesWithTimingBeforeRequestedStartTimeUs != null) {
                    cuesWithTimingBeforeRequestedStartTimeUs.add(new CuesWithTiming(cuesForThisStartTime, startTimeUs, durationUs));
                }
            }
            i++;
            j2 = C.TIME_UNSET;
        }
        if (cuesWithTimingBeforeRequestedStartTimeUs != null) {
            for (CuesWithTiming cuesWithTiming : cuesWithTimingBeforeRequestedStartTimeUs) {
                output.accept(cuesWithTiming);
            }
        }
    }

    private Charset detectUtfCharset(ParsableByteArray data) {
        Charset charset = data.readUtfCharsetFromBom();
        return charset != null ? charset : Charsets.UTF_8;
    }

    private void parseHeader(ParsableByteArray data, Charset charset) {
        while (true) {
            String currentLine = data.readLine(charset);
            if (currentLine != null) {
                if ("[Script Info]".equalsIgnoreCase(currentLine)) {
                    parseScriptInfo(data, charset);
                } else if ("[V4+ Styles]".equalsIgnoreCase(currentLine)) {
                    this.styles = parseStyles(data, charset);
                } else if ("[V4 Styles]".equalsIgnoreCase(currentLine)) {
                    Log.i(TAG, "[V4 Styles] are not supported");
                } else if ("[Events]".equalsIgnoreCase(currentLine)) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0047, code lost:
    
        if (r3.equals("playresx") != false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void parseScriptInfo(androidx.media3.common.util.ParsableByteArray r7, java.nio.charset.Charset r8) {
        /*
            r6 = this;
        L1:
            java.lang.String r0 = r7.readLine(r8)
            r1 = r0
            if (r0 == 0) goto L6e
            int r0 = r7.bytesLeft()
            if (r0 == 0) goto L16
            char r0 = r7.peekChar(r8)
            r2 = 91
            if (r0 == r2) goto L6e
        L16:
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r1.split(r0)
            int r2 = r0.length
            r3 = 2
            if (r2 == r3) goto L21
            goto L1
        L21:
            r2 = 0
            r3 = r0[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase(r3)
            int r4 = r3.hashCode()
            r5 = 1
            switch(r4) {
                case 1879649548: goto L40;
                case 1879649549: goto L35;
                default: goto L34;
            }
        L34:
            goto L4a
        L35:
            java.lang.String r2 = "playresy"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L34
            r2 = r5
            goto L4b
        L40:
            java.lang.String r4 = "playresx"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L34
            goto L4b
        L4a:
            r2 = -1
        L4b:
            switch(r2) {
                case 0: goto L5e;
                case 1: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L6d
        L4f:
            r2 = r0[r5]     // Catch: java.lang.NumberFormatException -> L5c
            java.lang.String r2 = r2.trim()     // Catch: java.lang.NumberFormatException -> L5c
            float r2 = java.lang.Float.parseFloat(r2)     // Catch: java.lang.NumberFormatException -> L5c
            r6.screenHeight = r2     // Catch: java.lang.NumberFormatException -> L5c
            goto L6d
        L5c:
            r2 = move-exception
            goto L6d
        L5e:
            r2 = r0[r5]     // Catch: java.lang.NumberFormatException -> L6b
            java.lang.String r2 = r2.trim()     // Catch: java.lang.NumberFormatException -> L6b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch: java.lang.NumberFormatException -> L6b
            r6.screenWidth = r2     // Catch: java.lang.NumberFormatException -> L6b
            goto L6d
        L6b:
            r2 = move-exception
        L6d:
            goto L1
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaParser.parseScriptInfo(androidx.media3.common.util.ParsableByteArray, java.nio.charset.Charset):void");
    }

    private static Map<String, SsaStyle> parseStyles(ParsableByteArray data, Charset charset) {
        Map<String, SsaStyle> styles = new LinkedHashMap<>();
        SsaStyle.Format formatInfo = null;
        while (true) {
            String currentLine = data.readLine(charset);
            if (currentLine == null || (data.bytesLeft() != 0 && data.peekChar(charset) == '[')) {
                break;
            }
            if (currentLine.startsWith(FORMAT_LINE_PREFIX)) {
                formatInfo = SsaStyle.Format.fromFormatLine(currentLine);
            } else if (currentLine.startsWith(STYLE_LINE_PREFIX)) {
                if (formatInfo == null) {
                    Log.w(TAG, "Skipping 'Style:' line before 'Format:' line: " + currentLine);
                } else {
                    SsaStyle style = SsaStyle.fromStyleLine(currentLine, formatInfo);
                    if (style != null) {
                        styles.put(style.name, style);
                    }
                }
            }
        }
        return styles;
    }

    private void parseEventBody(ParsableByteArray data, List<List<Cue>> cues, List<Long> cueTimesUs, Charset charset) {
        SsaDialogueFormat format = this.haveInitializationData ? this.dialogueFormatFromInitializationData : null;
        while (true) {
            String currentLine = data.readLine(charset);
            if (currentLine != null) {
                if (currentLine.startsWith(FORMAT_LINE_PREFIX)) {
                    format = SsaDialogueFormat.fromFormatLine(currentLine);
                } else if (currentLine.startsWith(DIALOGUE_LINE_PREFIX)) {
                    if (format == null) {
                        Log.w(TAG, "Skipping dialogue line before complete format: " + currentLine);
                    } else {
                        parseDialogueLine(currentLine, format, cues, cueTimesUs);
                    }
                }
            } else {
                return;
            }
        }
    }

    private void parseDialogueLine(String dialogueLine, SsaDialogueFormat format, List<List<Cue>> cues, List<Long> cueTimesUs) {
        SsaStyle style;
        Assertions.checkArgument(dialogueLine.startsWith(DIALOGUE_LINE_PREFIX));
        String[] lineValues = dialogueLine.substring(DIALOGUE_LINE_PREFIX.length()).split(",", format.length);
        if (lineValues.length != format.length) {
            Log.w(TAG, "Skipping dialogue line with fewer columns than format: " + dialogueLine);
            return;
        }
        long startTimeUs = parseTimecodeUs(lineValues[format.startTimeIndex]);
        if (startTimeUs == C.TIME_UNSET) {
            Log.w(TAG, "Skipping invalid timing: " + dialogueLine);
            return;
        }
        long endTimeUs = parseTimecodeUs(lineValues[format.endTimeIndex]);
        if (endTimeUs == C.TIME_UNSET) {
            Log.w(TAG, "Skipping invalid timing: " + dialogueLine);
            return;
        }
        if (this.styles != null && format.styleIndex != -1) {
            style = this.styles.get(lineValues[format.styleIndex].trim());
        } else {
            style = null;
        }
        String rawText = lineValues[format.textIndex];
        SsaStyle.Overrides styleOverrides = SsaStyle.Overrides.parseFromDialogue(rawText);
        String text = SsaStyle.Overrides.stripStyleOverrides(rawText).replace("\\N", "\n").replace("\\n", "\n").replace("\\h", " ");
        Cue cue = createCue(text, style, styleOverrides, this.screenWidth, this.screenHeight);
        int startTimeIndex = addCuePlacerholderByTime(startTimeUs, cueTimesUs, cues);
        int i = startTimeIndex;
        for (int endTimeIndex = addCuePlacerholderByTime(endTimeUs, cueTimesUs, cues); i < endTimeIndex; endTimeIndex = endTimeIndex) {
            cues.get(i).add(cue);
            i++;
        }
    }

    private static long parseTimecodeUs(String timeString) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(timeString.trim());
        if (!matcher.matches()) {
            return C.TIME_UNSET;
        }
        long timestampUs = Long.parseLong((String) Util.castNonNull(matcher.group(1))) * 60 * 60 * 1000000;
        return timestampUs + (Long.parseLong((String) Util.castNonNull(matcher.group(2))) * 60 * 1000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(3))) * 1000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(4))) * 10000);
    }

    private static Cue createCue(String text, SsaStyle style, SsaStyle.Overrides styleOverrides, float screenWidth, float screenHeight) {
        int alignment;
        SpannableString spannableText = new SpannableString(text);
        Cue.Builder cue = new Cue.Builder().setText(spannableText);
        if (style != null) {
            if (style.primaryColor != null) {
                spannableText.setSpan(new ForegroundColorSpan(style.primaryColor.intValue()), 0, spannableText.length(), 33);
            }
            if (style.borderStyle == 3 && style.outlineColor != null) {
                spannableText.setSpan(new BackgroundColorSpan(style.outlineColor.intValue()), 0, spannableText.length(), 33);
            }
            if (style.fontSize != -3.4028235E38f && screenHeight != -3.4028235E38f) {
                cue.setTextSize(style.fontSize / screenHeight, 1);
            }
            if (style.bold && style.italic) {
                spannableText.setSpan(new StyleSpan(3), 0, spannableText.length(), 33);
            } else if (style.bold) {
                spannableText.setSpan(new StyleSpan(1), 0, spannableText.length(), 33);
            } else if (style.italic) {
                spannableText.setSpan(new StyleSpan(2), 0, spannableText.length(), 33);
            }
            if (style.underline) {
                spannableText.setSpan(new UnderlineSpan(), 0, spannableText.length(), 33);
            }
            if (style.strikeout) {
                spannableText.setSpan(new StrikethroughSpan(), 0, spannableText.length(), 33);
            }
        }
        if (styleOverrides.alignment != -1) {
            alignment = styleOverrides.alignment;
        } else if (style != null) {
            alignment = style.alignment;
        } else {
            alignment = -1;
        }
        cue.setTextAlignment(toTextAlignment(alignment)).setPositionAnchor(toPositionAnchor(alignment)).setLineAnchor(toLineAnchor(alignment));
        if (styleOverrides.position != null && screenHeight != -3.4028235E38f && screenWidth != -3.4028235E38f) {
            cue.setPosition(styleOverrides.position.x / screenWidth);
            cue.setLine(styleOverrides.position.y / screenHeight, 0);
        } else {
            cue.setPosition(computeDefaultLineOrPosition(cue.getPositionAnchor()));
            cue.setLine(computeDefaultLineOrPosition(cue.getLineAnchor()), 0);
        }
        return cue.build();
    }

    private static Layout.Alignment toTextAlignment(int alignment) {
        switch (alignment) {
            case -1:
                return null;
            case 0:
            default:
                Log.w(TAG, "Unknown alignment: " + alignment);
                return null;
            case 1:
            case 4:
            case 7:
                return Layout.Alignment.ALIGN_NORMAL;
            case 2:
            case 5:
            case 8:
                return Layout.Alignment.ALIGN_CENTER;
            case 3:
            case 6:
            case 9:
                return Layout.Alignment.ALIGN_OPPOSITE;
        }
    }

    private static int toLineAnchor(int alignment) {
        switch (alignment) {
            case -1:
                return Integer.MIN_VALUE;
            case 0:
            default:
                Log.w(TAG, "Unknown alignment: " + alignment);
                return Integer.MIN_VALUE;
            case 1:
            case 2:
            case 3:
                return 2;
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 9:
                return 0;
        }
    }

    private static int toPositionAnchor(int alignment) {
        switch (alignment) {
            case -1:
                return Integer.MIN_VALUE;
            case 0:
            default:
                Log.w(TAG, "Unknown alignment: " + alignment);
                return Integer.MIN_VALUE;
            case 1:
            case 4:
            case 7:
                return 0;
            case 2:
            case 5:
            case 8:
                return 1;
            case 3:
            case 6:
            case 9:
                return 2;
        }
    }

    private static float computeDefaultLineOrPosition(int anchor) {
        switch (anchor) {
            case 0:
                return DEFAULT_MARGIN;
            case 1:
                return 0.5f;
            case 2:
                return 0.95f;
            default:
                return -3.4028235E38f;
        }
    }

    private static int addCuePlacerholderByTime(long timeUs, List<Long> sortedCueTimesUs, List<List<Cue>> cues) {
        int insertionIndex = 0;
        int i = sortedCueTimesUs.size() - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            if (sortedCueTimesUs.get(i).longValue() == timeUs) {
                return i;
            }
            if (sortedCueTimesUs.get(i).longValue() >= timeUs) {
                i--;
            } else {
                insertionIndex = i + 1;
                break;
            }
        }
        sortedCueTimesUs.add(insertionIndex, Long.valueOf(timeUs));
        cues.add(insertionIndex, insertionIndex == 0 ? new ArrayList() : new ArrayList(cues.get(insertionIndex - 1)));
        return insertionIndex;
    }
}
