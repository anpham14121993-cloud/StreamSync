package androidx.media3.exoplayer.dash.manifest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class UrlTemplate {
    private static final String BANDWIDTH = "Bandwidth";
    private static final int BANDWIDTH_ID = 3;
    private static final String DEFAULT_FORMAT_TAG = "%01d";
    private static final String ESCAPED_DOLLAR = "$$";
    private static final String NUMBER = "Number";
    private static final int NUMBER_ID = 2;
    private static final String REPRESENTATION = "RepresentationID";
    private static final int REPRESENTATION_ID = 1;
    private static final String TIME = "Time";
    private static final int TIME_ID = 4;
    private final List<String> identifierFormatTags;
    private final List<Integer> identifiers;
    private final List<String> urlPieces;

    public static UrlTemplate compile(String template) {
        List<String> urlPieces = new ArrayList<>();
        List<Integer> identifiers = new ArrayList<>();
        List<String> identifierFormatTags = new ArrayList<>();
        parseTemplate(template, urlPieces, identifiers, identifierFormatTags);
        return new UrlTemplate(urlPieces, identifiers, identifierFormatTags);
    }

    private UrlTemplate(List<String> urlPieces, List<Integer> identifiers, List<String> identifierFormatTags) {
        this.urlPieces = urlPieces;
        this.identifiers = identifiers;
        this.identifierFormatTags = identifierFormatTags;
    }

    public String buildUri(String representationId, long segmentNumber, int bandwidth, long time) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.identifiers.size(); i++) {
            builder.append(this.urlPieces.get(i));
            if (this.identifiers.get(i).intValue() == 1) {
                builder.append(representationId);
            } else if (this.identifiers.get(i).intValue() == 2) {
                builder.append(String.format(Locale.US, this.identifierFormatTags.get(i), Long.valueOf(segmentNumber)));
            } else if (this.identifiers.get(i).intValue() == 3) {
                builder.append(String.format(Locale.US, this.identifierFormatTags.get(i), Integer.valueOf(bandwidth)));
            } else if (this.identifiers.get(i).intValue() == 4) {
                builder.append(String.format(Locale.US, this.identifierFormatTags.get(i), Long.valueOf(time)));
            }
        }
        builder.append(this.urlPieces.get(this.identifiers.size()));
        return builder.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x011f, code lost:
    
        switch(r4) {
            case 0: goto L43;
            case 1: goto L42;
            case 2: goto L41;
            default: goto L54;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x013b, code lost:
    
        r14.add(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0155, code lost:
    
        r15.set(r14.size() - 1, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0144, code lost:
    
        r14.add(3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x014d, code lost:
    
        r14.add(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x013a, code lost:
    
        throw new java.lang.IllegalArgumentException("Invalid template: " + r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void parseTemplate(java.lang.String r12, java.util.List<java.lang.String> r13, java.util.List<java.lang.Integer> r14, java.util.List<java.lang.String> r15) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.UrlTemplate.parseTemplate(java.lang.String, java.util.List, java.util.List, java.util.List):void");
    }
}
