package androidx.media3.extractor.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes24.dex */
public final class SubripParser implements SubtitleParser {
    private static final String ALIGN_BOTTOM_LEFT = "{\\an1}";
    private static final String ALIGN_BOTTOM_MID = "{\\an2}";
    private static final String ALIGN_BOTTOM_RIGHT = "{\\an3}";
    private static final String ALIGN_MID_LEFT = "{\\an4}";
    private static final String ALIGN_MID_MID = "{\\an5}";
    private static final String ALIGN_MID_RIGHT = "{\\an6}";
    private static final String ALIGN_TOP_LEFT = "{\\an7}";
    private static final String ALIGN_TOP_MID = "{\\an8}";
    private static final String ALIGN_TOP_RIGHT = "{\\an9}";
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    private static final float END_FRACTION = 0.92f;
    private static final float MID_FRACTION = 0.5f;
    private static final float START_FRACTION = 0.08f;
    private static final String SUBRIP_ALIGNMENT_TAG = "\\{\\\\an[1-9]\\}";
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?";
    private static final String TAG = "SubripParser";
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    private final StringBuilder textBuilder = new StringBuilder();
    private final ArrayList<String> tags = new ArrayList<>();
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] data, int offset, int length, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> output) {
        List<CuesWithTiming> list;
        String alignmentTag;
        this.parsableByteArray.reset(data, offset + length);
        this.parsableByteArray.setPosition(offset);
        Charset charset = detectUtfCharset(this.parsableByteArray);
        long j = outputOptions.startTimeUs;
        long j2 = C.TIME_UNSET;
        if (j != C.TIME_UNSET && outputOptions.outputAllCues) {
            list = new ArrayList<>();
        } else {
            list = null;
        }
        List<CuesWithTiming> cuesWithTimingBeforeRequestedStartTimeUs = list;
        while (true) {
            String currentLine = this.parsableByteArray.readLine(charset);
            if (currentLine == null) {
                break;
            }
            if (currentLine.length() != 0) {
                try {
                    Integer.parseInt(currentLine);
                    String currentLine2 = this.parsableByteArray.readLine(charset);
                    if (currentLine2 == null) {
                        Log.w(TAG, "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(currentLine2);
                    if (matcher.matches()) {
                        long startTimeUs = parseTimecode(matcher, 1);
                        long endTimeUs = parseTimecode(matcher, 6);
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        String currentLine3 = this.parsableByteArray.readLine(charset);
                        while (!TextUtils.isEmpty(currentLine3)) {
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(currentLine3, this.tags));
                            currentLine3 = this.parsableByteArray.readLine(charset);
                        }
                        Spanned text = Html.fromHtml(this.textBuilder.toString());
                        int i = 0;
                        while (true) {
                            if (i >= this.tags.size()) {
                                alignmentTag = null;
                                break;
                            }
                            String tag = this.tags.get(i);
                            if (!tag.matches(SUBRIP_ALIGNMENT_TAG)) {
                                i++;
                            } else {
                                alignmentTag = tag;
                                break;
                            }
                        }
                        if (outputOptions.startTimeUs == C.TIME_UNSET || startTimeUs >= outputOptions.startTimeUs) {
                            output.accept(new CuesWithTiming(ImmutableList.of(buildCue(text, alignmentTag)), startTimeUs, endTimeUs - startTimeUs));
                        } else if (cuesWithTimingBeforeRequestedStartTimeUs != null) {
                            cuesWithTimingBeforeRequestedStartTimeUs.add(new CuesWithTiming(ImmutableList.of(buildCue(text, alignmentTag)), startTimeUs, endTimeUs - startTimeUs));
                        }
                        j2 = -9223372036854775807L;
                    } else {
                        Log.w(TAG, "Skipping invalid timing: " + currentLine2);
                        j2 = j2;
                    }
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Skipping invalid index: " + currentLine);
                    j2 = j2;
                }
            }
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

    private String processLine(String line, ArrayList<String> tags) {
        String line2 = line.trim();
        int removedCharacterCount = 0;
        StringBuilder processedLine = new StringBuilder(line2);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(line2);
        while (matcher.find()) {
            String tag = matcher.group();
            tags.add(tag);
            int start = matcher.start() - removedCharacterCount;
            int tagLength = tag.length();
            processedLine.replace(start, start + tagLength, "");
            removedCharacterCount += tagLength;
        }
        return processedLine.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Cue buildCue(Spanned text, String alignmentTag) {
        char c;
        char c2;
        Cue.Builder cue = new Cue.Builder().setText(text);
        if (alignmentTag != null) {
            switch (alignmentTag.hashCode()) {
                case -685620710:
                    if (alignmentTag.equals(ALIGN_BOTTOM_LEFT)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620679:
                    if (alignmentTag.equals(ALIGN_BOTTOM_MID)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620648:
                    if (alignmentTag.equals(ALIGN_BOTTOM_RIGHT)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620617:
                    if (alignmentTag.equals(ALIGN_MID_LEFT)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620586:
                    if (alignmentTag.equals(ALIGN_MID_MID)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620555:
                    if (alignmentTag.equals(ALIGN_MID_RIGHT)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620524:
                    if (alignmentTag.equals(ALIGN_TOP_LEFT)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -685620493:
                    if (alignmentTag.equals(ALIGN_TOP_MID)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -685620462:
                    if (alignmentTag.equals(ALIGN_TOP_RIGHT)) {
                        c = 5;
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
                case 2:
                    cue.setPositionAnchor(0);
                    break;
                case 3:
                case 4:
                case 5:
                    cue.setPositionAnchor(2);
                    break;
                default:
                    cue.setPositionAnchor(1);
                    break;
            }
            switch (alignmentTag.hashCode()) {
                case -685620710:
                    if (alignmentTag.equals(ALIGN_BOTTOM_LEFT)) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620679:
                    if (alignmentTag.equals(ALIGN_BOTTOM_MID)) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620648:
                    if (alignmentTag.equals(ALIGN_BOTTOM_RIGHT)) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620617:
                    if (alignmentTag.equals(ALIGN_MID_LEFT)) {
                        c2 = 6;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620586:
                    if (alignmentTag.equals(ALIGN_MID_MID)) {
                        c2 = 7;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620555:
                    if (alignmentTag.equals(ALIGN_MID_RIGHT)) {
                        c2 = '\b';
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620524:
                    if (alignmentTag.equals(ALIGN_TOP_LEFT)) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620493:
                    if (alignmentTag.equals(ALIGN_TOP_MID)) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -685620462:
                    if (alignmentTag.equals(ALIGN_TOP_RIGHT)) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                case 2:
                    cue.setLineAnchor(2);
                    break;
                case 3:
                case 4:
                case 5:
                    cue.setLineAnchor(0);
                    break;
                default:
                    cue.setLineAnchor(1);
                    break;
            }
            return cue.setPosition(getFractionalPositionForAnchorType(cue.getPositionAnchor())).setLine(getFractionalPositionForAnchorType(cue.getLineAnchor()), 0).build();
        }
        return cue.build();
    }

    private static long parseTimecode(Matcher matcher, int groupOffset) {
        String hours = matcher.group(groupOffset + 1);
        long timestampMs = (hours != null ? Long.parseLong(hours) * 60 * 60 * 1000 : 0L) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(groupOffset + 2))) * 60 * 1000) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(groupOffset + 3))) * 1000);
        String millis = matcher.group(groupOffset + 4);
        if (millis != null) {
            timestampMs += Long.parseLong(millis);
        }
        return 1000 * timestampMs;
    }

    public static float getFractionalPositionForAnchorType(int anchorType) {
        switch (anchorType) {
            case 0:
                return 0.08f;
            case 1:
                return 0.5f;
            case 2:
                return END_FRACTION;
            default:
                throw new IllegalArgumentException();
        }
    }
}
