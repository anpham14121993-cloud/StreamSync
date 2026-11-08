package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import android.util.Xml;
import androidx.compose.material3.TextFieldImplKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.streamsync.app.settings.LastStreamStore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes3.dex */
public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final String TAG = "MpdParser";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final int[] MPEG_CHANNEL_CONFIGURATION_MAPPING = {-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};

    public DashManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xpp = this.xmlParserFactory.newPullParser();
            xpp.setInput(inputStream, null);
            int eventType = xpp.next();
            if (eventType != 2 || !"MPD".equals(xpp.getName())) {
                throw ParserException.createForMalformedManifest("inputStream does not contain a valid media presentation description", null);
            }
            return parseMediaPresentationDescription(xpp, uri);
        } catch (XmlPullParserException e) {
            throw ParserException.createForMalformedManifest(null, e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0284  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected androidx.media3.exoplayer.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r49, android.net.Uri r50) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 681
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, android.net.Uri):androidx.media3.exoplayer.dash.manifest.DashManifest");
    }

    protected DashManifest buildMediaPresentationDescription(long availabilityStartTime, long durationMs, long minBufferTimeMs, boolean dynamic, long minUpdateTimeMs, long timeShiftBufferDepthMs, long suggestedPresentationDelayMs, long publishTimeMs, ProgramInformation programInformation, UtcTimingElement utcTiming, ServiceDescriptionElement serviceDescription, Uri location, List<Period> periods) {
        return new DashManifest(availabilityStartTime, durationMs, minBufferTimeMs, dynamic, minUpdateTimeMs, timeShiftBufferDepthMs, suggestedPresentationDelayMs, publishTimeMs, programInformation, utcTiming, serviceDescription, location, periods);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xpp) {
        String schemeIdUri = xpp.getAttributeValue(null, "schemeIdUri");
        String value = xpp.getAttributeValue(null, "value");
        return buildUtcTimingElement(schemeIdUri, value);
    }

    protected UtcTimingElement buildUtcTimingElement(String schemeIdUri, String value) {
        return new UtcTimingElement(schemeIdUri, value);
    }

    protected ServiceDescriptionElement parseServiceDescription(XmlPullParser xpp) throws XmlPullParserException, IOException {
        long targetOffsetMs = C.TIME_UNSET;
        long minOffsetMs = C.TIME_UNSET;
        long maxOffsetMs = C.TIME_UNSET;
        float minPlaybackSpeed = -3.4028235E38f;
        float maxPlaybackSpeed = -3.4028235E38f;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Latency")) {
                targetOffsetMs = parseLong(xpp, "target", C.TIME_UNSET);
                minOffsetMs = parseLong(xpp, "min", C.TIME_UNSET);
                maxOffsetMs = parseLong(xpp, "max", C.TIME_UNSET);
            } else if (XmlPullParserUtil.isStartTag(xpp, "PlaybackRate")) {
                minPlaybackSpeed = parseFloat(xpp, "min", -3.4028235E38f);
                maxPlaybackSpeed = parseFloat(xpp, "max", -3.4028235E38f);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "ServiceDescription"));
        return new ServiceDescriptionElement(targetOffsetMs, minOffsetMs, maxOffsetMs, minPlaybackSpeed, maxPlaybackSpeed);
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xpp, List<BaseUrl> parentBaseUrls, long defaultStartMs, long baseUrlAvailabilityTimeOffsetUs, long availabilityStartTimeMs, long timeShiftBufferDepthMs, boolean dvbProfileDeclared) throws XmlPullParserException, IOException {
        long baseUrlAvailabilityTimeOffsetUs2;
        ArrayList<BaseUrl> baseUrls;
        XmlPullParser xmlPullParser;
        List<EventStream> eventStreams;
        List<EventStream> eventStreams2;
        DashManifestParser dashManifestParser;
        List<AdaptationSet> adaptationSets;
        long baseUrlAvailabilityTimeOffsetUs3;
        DashManifestParser dashManifestParser2 = this;
        XmlPullParser xmlPullParser2 = xpp;
        List<EventStream> list = null;
        String id = xmlPullParser2.getAttributeValue(null, "id");
        long startMs = parseDuration(xmlPullParser2, TtmlNode.START, defaultStartMs);
        long periodStartUnixTimeMs = availabilityStartTimeMs != C.TIME_UNSET ? availabilityStartTimeMs + startMs : -9223372036854775807L;
        long durationMs = parseDuration(xmlPullParser2, LastStreamStore.KEY_DURATION, C.TIME_UNSET);
        List<AdaptationSet> adaptationSets2 = new ArrayList<>();
        List<EventStream> eventStreams3 = new ArrayList<>();
        ArrayList<BaseUrl> baseUrls2 = new ArrayList<>();
        long segmentBaseAvailabilityTimeOffsetUs = C.TIME_UNSET;
        SegmentBase segmentBase = null;
        Descriptor assetIdentifier = null;
        boolean seenFirstBaseUrl = false;
        long baseUrlAvailabilityTimeOffsetUs4 = baseUrlAvailabilityTimeOffsetUs;
        while (true) {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "BaseURL")) {
                if (!seenFirstBaseUrl) {
                    baseUrlAvailabilityTimeOffsetUs4 = dashManifestParser2.parseAvailabilityTimeOffsetUs(xmlPullParser2, baseUrlAvailabilityTimeOffsetUs4);
                    seenFirstBaseUrl = true;
                }
                baseUrls2.addAll(dashManifestParser2.parseBaseUrl(xmlPullParser2, parentBaseUrls, dvbProfileDeclared));
                baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                eventStreams = eventStreams3;
                baseUrls = baseUrls2;
                eventStreams2 = list;
                adaptationSets = adaptationSets2;
                xmlPullParser = xmlPullParser2;
                dashManifestParser = dashManifestParser2;
                baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AdaptationSet")) {
                baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                baseUrls = baseUrls2;
                List<AdaptationSet> adaptationSets3 = adaptationSets2;
                adaptationSets3.add(parseAdaptationSet(xpp, !baseUrls2.isEmpty() ? baseUrls2 : parentBaseUrls, segmentBase, durationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs, periodStartUnixTimeMs, timeShiftBufferDepthMs, dvbProfileDeclared));
                dashManifestParser = this;
                xmlPullParser = xpp;
                adaptationSets = adaptationSets3;
                eventStreams = eventStreams3;
                eventStreams2 = null;
                baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
            } else {
                baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                List<EventStream> eventStreams4 = eventStreams3;
                baseUrls = baseUrls2;
                List<AdaptationSet> adaptationSets4 = adaptationSets2;
                xmlPullParser = xpp;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "EventStream")) {
                    eventStreams4.add(parseEventStream(xpp));
                    dashManifestParser = this;
                    eventStreams = eventStreams4;
                    adaptationSets = adaptationSets4;
                    eventStreams2 = null;
                    baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                    eventStreams = eventStreams4;
                    eventStreams2 = null;
                    dashManifestParser = this;
                    segmentBase = parseSegmentBase(xmlPullParser, null);
                    adaptationSets = adaptationSets4;
                    baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
                } else {
                    eventStreams = eventStreams4;
                    eventStreams2 = null;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                        baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
                        long segmentBaseAvailabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser, C.TIME_UNSET);
                        dashManifestParser = this;
                        adaptationSets = adaptationSets4;
                        segmentBase = parseSegmentList(xpp, null, periodStartUnixTimeMs, durationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs2, timeShiftBufferDepthMs);
                        segmentBaseAvailabilityTimeOffsetUs = segmentBaseAvailabilityTimeOffsetUs2;
                    } else {
                        dashManifestParser = this;
                        adaptationSets = adaptationSets4;
                        baseUrlAvailabilityTimeOffsetUs3 = C.TIME_UNSET;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                            long segmentBaseAvailabilityTimeOffsetUs3 = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser, C.TIME_UNSET);
                            segmentBase = parseSegmentTemplate(xpp, null, ImmutableList.of(), periodStartUnixTimeMs, durationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs3, timeShiftBufferDepthMs);
                            segmentBaseAvailabilityTimeOffsetUs = segmentBaseAvailabilityTimeOffsetUs3;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AssetIdentifier")) {
                            assetIdentifier = parseDescriptor(xmlPullParser, "AssetIdentifier");
                        } else {
                            maybeSkipTag(xpp);
                        }
                    }
                }
            }
            if (!XmlPullParserUtil.isEndTag(xmlPullParser, "Period")) {
                list = eventStreams2;
                xmlPullParser2 = xmlPullParser;
                eventStreams3 = eventStreams;
                dashManifestParser2 = dashManifestParser;
                baseUrls2 = baseUrls;
                adaptationSets2 = adaptationSets;
                baseUrlAvailabilityTimeOffsetUs4 = baseUrlAvailabilityTimeOffsetUs2;
            } else {
                return Pair.create(buildPeriod(id, startMs, adaptationSets, eventStreams, assetIdentifier), Long.valueOf(durationMs));
            }
        }
    }

    protected Period buildPeriod(String id, long startMs, List<AdaptationSet> adaptationSets, List<EventStream> eventStreams, Descriptor assetIdentifier) {
        return new Period(id, startMs, adaptationSets, eventStreams, assetIdentifier);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser xpp, List<BaseUrl> parentBaseUrls, SegmentBase segmentBase, long periodDurationMs, long baseUrlAvailabilityTimeOffsetUs, long segmentBaseAvailabilityTimeOffsetUs, long periodStartUnixTimeMs, long timeShiftBufferDepthMs, boolean dvbProfileDeclared) throws XmlPullParserException, IOException {
        String language;
        Object obj;
        ArrayList<Descriptor> supplementalProperties;
        ArrayList<Descriptor> essentialProperties;
        ArrayList<Descriptor> roleDescriptors;
        ArrayList<Descriptor> accessibilityDescriptors;
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        String str;
        long baseUrlAvailabilityTimeOffsetUs2;
        List<RepresentationInfo> representationInfos;
        long segmentBaseAvailabilityTimeOffsetUs2;
        int contentType;
        ArrayList<Descriptor> accessibilityDescriptors2;
        ArrayList<Descriptor> inbandEventStreams;
        long segmentBaseAvailabilityTimeOffsetUs3;
        String language2;
        DashManifestParser dashManifestParser = this;
        XmlPullParser xmlPullParser = xpp;
        long id = parseLong(xmlPullParser, "id", -1L);
        int contentType2 = parseContentType(xpp);
        String mimeType = xmlPullParser.getAttributeValue(null, "mimeType");
        String codecs = xmlPullParser.getAttributeValue(null, "codecs");
        int width = parseInt(xmlPullParser, "width", -1);
        int height = parseInt(xmlPullParser, "height", -1);
        float frameRate = parseFrameRate(xmlPullParser, -1.0f);
        int audioSamplingRate = parseInt(xmlPullParser, "audioSamplingRate", -1);
        String str2 = "lang";
        String language3 = xmlPullParser.getAttributeValue(null, "lang");
        String label = xmlPullParser.getAttributeValue(null, "label");
        ArrayList<Descriptor> labels = new ArrayList<>();
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas2 = new ArrayList<>();
        ArrayList<Descriptor> inbandEventStreams2 = new ArrayList<>();
        ArrayList<Descriptor> inbandEventStreams3 = new ArrayList<>();
        ArrayList<Descriptor> roleDescriptors2 = new ArrayList<>();
        ArrayList<Descriptor> essentialProperties2 = new ArrayList<>();
        ArrayList<Descriptor> supplementalProperties2 = new ArrayList<>();
        List<RepresentationInfo> representationInfos2 = new ArrayList<>();
        ArrayList<BaseUrl> baseUrls = new ArrayList<>();
        SegmentBase segmentBase2 = segmentBase;
        int contentType3 = contentType2;
        int audioChannels = -1;
        String language4 = language3;
        String drmSchemeType = null;
        boolean seenFirstBaseUrl = false;
        long baseUrlAvailabilityTimeOffsetUs3 = baseUrlAvailabilityTimeOffsetUs;
        long segmentBaseAvailabilityTimeOffsetUs4 = segmentBaseAvailabilityTimeOffsetUs;
        while (true) {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!seenFirstBaseUrl) {
                    baseUrlAvailabilityTimeOffsetUs3 = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser, baseUrlAvailabilityTimeOffsetUs3);
                    seenFirstBaseUrl = true;
                }
                baseUrls.addAll(dashManifestParser.parseBaseUrl(xmlPullParser, parentBaseUrls, dvbProfileDeclared));
                baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs3;
                representationInfos = representationInfos2;
                essentialProperties = essentialProperties2;
                roleDescriptors = roleDescriptors2;
                accessibilityDescriptors = inbandEventStreams3;
                accessibilityDescriptors2 = inbandEventStreams2;
                drmSchemeDatas = drmSchemeDatas2;
                str = str2;
                inbandEventStreams = labels;
                language2 = language4;
                obj = null;
                segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs4;
                supplementalProperties = supplementalProperties2;
            } else {
                long segmentBaseAvailabilityTimeOffsetUs5 = segmentBaseAvailabilityTimeOffsetUs4;
                ArrayList<Descriptor> labels2 = labels;
                ArrayList<BaseUrl> baseUrls2 = baseUrls;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "ContentProtection")) {
                    Pair<String, DrmInitData.SchemeData> contentProtection = parseContentProtection(xpp);
                    long baseUrlAvailabilityTimeOffsetUs4 = baseUrlAvailabilityTimeOffsetUs3;
                    if (contentProtection.first != null) {
                        drmSchemeType = (String) contentProtection.first;
                    }
                    if (contentProtection.second != null) {
                        drmSchemeDatas2.add((DrmInitData.SchemeData) contentProtection.second);
                    }
                    baseUrls = baseUrls2;
                    representationInfos = representationInfos2;
                    essentialProperties = essentialProperties2;
                    roleDescriptors = roleDescriptors2;
                    accessibilityDescriptors = inbandEventStreams3;
                    accessibilityDescriptors2 = inbandEventStreams2;
                    drmSchemeDatas = drmSchemeDatas2;
                    str = str2;
                    inbandEventStreams = labels2;
                    baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                    language2 = language4;
                    obj = null;
                    segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs5;
                    supplementalProperties = supplementalProperties2;
                } else {
                    long baseUrlAvailabilityTimeOffsetUs5 = baseUrlAvailabilityTimeOffsetUs3;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "ContentComponent")) {
                        String language5 = checkLanguageConsistency(language4, xmlPullParser.getAttributeValue(null, str2));
                        baseUrls = baseUrls2;
                        contentType3 = checkContentTypeConsistency(contentType3, parseContentType(xpp));
                        obj = null;
                        representationInfos = representationInfos2;
                        supplementalProperties = supplementalProperties2;
                        essentialProperties = essentialProperties2;
                        roleDescriptors = roleDescriptors2;
                        accessibilityDescriptors = inbandEventStreams3;
                        accessibilityDescriptors2 = inbandEventStreams2;
                        drmSchemeDatas = drmSchemeDatas2;
                        str = str2;
                        inbandEventStreams = labels2;
                        baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                        segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs5;
                        language2 = language5;
                    } else {
                        int contentType4 = contentType3;
                        String language6 = language4;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser, "Role")) {
                            roleDescriptors2.add(parseDescriptor(xmlPullParser, "Role"));
                            baseUrls = baseUrls2;
                            language = language6;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            contentType = contentType4;
                            inbandEventStreams = labels2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs5;
                            representationInfos = representationInfos2;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                            audioChannels = parseAudioChannelConfiguration(xpp);
                            baseUrls = baseUrls2;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            contentType3 = contentType4;
                            inbandEventStreams = labels2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs5;
                            language2 = language6;
                            representationInfos = representationInfos2;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Accessibility")) {
                            inbandEventStreams3.add(parseDescriptor(xmlPullParser, "Accessibility"));
                            baseUrls = baseUrls2;
                            language = language6;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            contentType = contentType4;
                            inbandEventStreams = labels2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs5;
                            representationInfos = representationInfos2;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "EssentialProperty")) {
                            essentialProperties2.add(parseDescriptor(xmlPullParser, "EssentialProperty"));
                            baseUrls = baseUrls2;
                            language = language6;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            contentType = contentType4;
                            inbandEventStreams = labels2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs5;
                            representationInfos = representationInfos2;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SupplementalProperty")) {
                            supplementalProperties2.add(parseDescriptor(xmlPullParser, "SupplementalProperty"));
                            baseUrls = baseUrls2;
                            language = language6;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            contentType = contentType4;
                            inbandEventStreams = labels2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs5;
                            representationInfos = representationInfos2;
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Representation")) {
                            baseUrls = baseUrls2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            drmSchemeDatas = drmSchemeDatas2;
                            language2 = language6;
                            str = str2;
                            obj = null;
                            RepresentationInfo representationInfo = parseRepresentation(xpp, !baseUrls2.isEmpty() ? baseUrls2 : parentBaseUrls, mimeType, codecs, width, height, frameRate, audioChannels, audioSamplingRate, language2, roleDescriptors, accessibilityDescriptors, essentialProperties, supplementalProperties, segmentBase2, periodStartUnixTimeMs, periodDurationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs5, timeShiftBufferDepthMs, dvbProfileDeclared);
                            int contentType5 = checkContentTypeConsistency(contentType4, MimeTypes.getTrackType(representationInfo.format.sampleMimeType));
                            representationInfos = representationInfos2;
                            representationInfos.add(representationInfo);
                            xmlPullParser = xpp;
                            segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs5;
                            accessibilityDescriptors2 = inbandEventStreams2;
                            inbandEventStreams = labels2;
                            contentType3 = contentType5;
                        } else {
                            baseUrls = baseUrls2;
                            language = language6;
                            obj = null;
                            supplementalProperties = supplementalProperties2;
                            essentialProperties = essentialProperties2;
                            roleDescriptors = roleDescriptors2;
                            accessibilityDescriptors = inbandEventStreams3;
                            ArrayList<Descriptor> inbandEventStreams4 = inbandEventStreams2;
                            drmSchemeDatas = drmSchemeDatas2;
                            str = str2;
                            baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs5;
                            representationInfos = representationInfos2;
                            if (XmlPullParserUtil.isStartTag(xpp, "SegmentBase")) {
                                segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs5;
                                segmentBase2 = parseSegmentBase(xpp, (SegmentBase.SingleSegmentBase) segmentBase2);
                                language2 = language;
                                accessibilityDescriptors2 = inbandEventStreams4;
                                inbandEventStreams = labels2;
                                contentType3 = contentType4;
                                xmlPullParser = xpp;
                            } else if (XmlPullParserUtil.isStartTag(xpp, "SegmentList")) {
                                segmentBaseAvailabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xpp, segmentBaseAvailabilityTimeOffsetUs5);
                                segmentBase2 = parseSegmentList(xpp, (SegmentBase.SegmentList) segmentBase2, periodStartUnixTimeMs, periodDurationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs3, timeShiftBufferDepthMs);
                                xmlPullParser = xpp;
                                language2 = language;
                                accessibilityDescriptors2 = inbandEventStreams4;
                                inbandEventStreams = labels2;
                                contentType3 = contentType4;
                            } else {
                                segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs5;
                                contentType = contentType4;
                                if (XmlPullParserUtil.isStartTag(xpp, "SegmentTemplate")) {
                                    segmentBaseAvailabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xpp, segmentBaseAvailabilityTimeOffsetUs2);
                                    xmlPullParser = xpp;
                                    segmentBase2 = parseSegmentTemplate(xpp, (SegmentBase.SegmentTemplate) segmentBase2, supplementalProperties, periodStartUnixTimeMs, periodDurationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs3, timeShiftBufferDepthMs);
                                    language2 = language;
                                    accessibilityDescriptors2 = inbandEventStreams4;
                                    inbandEventStreams = labels2;
                                    contentType3 = contentType;
                                } else {
                                    xmlPullParser = xpp;
                                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "InbandEventStream")) {
                                        accessibilityDescriptors2 = inbandEventStreams4;
                                        accessibilityDescriptors2.add(parseDescriptor(xmlPullParser, "InbandEventStream"));
                                        inbandEventStreams = labels2;
                                    } else {
                                        accessibilityDescriptors2 = inbandEventStreams4;
                                        if (XmlPullParserUtil.isStartTag(xmlPullParser, TextFieldImplKt.LabelId)) {
                                            inbandEventStreams = labels2;
                                            inbandEventStreams.add(parseLabel(xpp));
                                        } else {
                                            inbandEventStreams = labels2;
                                            if (XmlPullParserUtil.isStartTag(xpp)) {
                                                parseAdaptationSetChild(xpp);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs2;
                        language2 = language;
                        contentType3 = contentType;
                    }
                }
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "AdaptationSet")) {
                break;
            }
            labels = inbandEventStreams;
            representationInfos2 = representationInfos;
            segmentBaseAvailabilityTimeOffsetUs4 = segmentBaseAvailabilityTimeOffsetUs3;
            supplementalProperties2 = supplementalProperties;
            baseUrlAvailabilityTimeOffsetUs3 = baseUrlAvailabilityTimeOffsetUs2;
            essentialProperties2 = essentialProperties;
            roleDescriptors2 = roleDescriptors;
            str2 = str;
            dashManifestParser = this;
            inbandEventStreams2 = accessibilityDescriptors2;
            language4 = language2;
            inbandEventStreams3 = accessibilityDescriptors;
            drmSchemeDatas2 = drmSchemeDatas;
        }
        List<Representation> representations = new ArrayList<>(representationInfos.size());
        for (int i = 0; i < representationInfos.size(); i++) {
            representations.add(buildRepresentation(representationInfos.get(i), label, inbandEventStreams, drmSchemeType, drmSchemeDatas, accessibilityDescriptors2));
        }
        return buildAdaptationSet(id, contentType3, representations, accessibilityDescriptors, essentialProperties, supplementalProperties);
    }

    protected AdaptationSet buildAdaptationSet(long id, int contentType, List<Representation> representations, List<Descriptor> accessibilityDescriptors, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties) {
        return new AdaptationSet(id, contentType, representations, accessibilityDescriptors, essentialProperties, supplementalProperties);
    }

    protected int parseContentType(XmlPullParser xpp) {
        String contentType = xpp.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(contentType)) {
            return -1;
        }
        if (MimeTypes.BASE_TYPE_AUDIO.equals(contentType)) {
            return 1;
        }
        if (MimeTypes.BASE_TYPE_VIDEO.equals(contentType)) {
            return 2;
        }
        if ("text".equals(contentType)) {
            return 3;
        }
        return "image".equals(contentType) ? 4 : -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected Pair<String, DrmInitData.SchemeData> parseContentProtection(XmlPullParser xpp) throws XmlPullParserException, IOException {
        char c;
        String schemeType = null;
        String licenseServerUrl = null;
        byte[] data = null;
        UUID uuid = null;
        String schemeIdUri = xpp.getAttributeValue(null, "schemeIdUri");
        if (schemeIdUri != null) {
            String lowerCase = Ascii.toLowerCase(schemeIdUri);
            switch (lowerCase.hashCode()) {
                case -1980789791:
                    if (lowerCase.equals("urn:uuid:e2719d58-a985-b3c9-781a-b030af78d30e")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 489446379:
                    if (lowerCase.equals("urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 755418770:
                    if (lowerCase.equals("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1812765994:
                    if (lowerCase.equals("urn:mpeg:dash:mp4protection:2011")) {
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
                    schemeType = xpp.getAttributeValue(null, "value");
                    String defaultKid = XmlPullParserUtil.getAttributeValueIgnorePrefix(xpp, "default_KID");
                    if (TextUtils.isEmpty(defaultKid) || "00000000-0000-0000-0000-000000000000".equals(defaultKid)) {
                        Log.w(TAG, "Ignoring <ContentProtection> with schemeIdUri=\"urn:mpeg:dash:mp4protection:2011\" (ClearKey) due to missing required default_KID attribute.");
                        break;
                    } else {
                        String[] defaultKidStrings = defaultKid.split("\\s+");
                        UUID[] defaultKids = new UUID[defaultKidStrings.length];
                        for (int i = 0; i < defaultKidStrings.length; i++) {
                            defaultKids[i] = UUID.fromString(defaultKidStrings[i]);
                        }
                        data = PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID, defaultKids, null);
                        uuid = C.COMMON_PSSH_UUID;
                        break;
                    }
                    break;
                case 1:
                    uuid = C.PLAYREADY_UUID;
                    break;
                case 2:
                    uuid = C.WIDEVINE_UUID;
                    break;
                case 3:
                    uuid = C.CLEARKEY_UUID;
                    break;
            }
        }
        do {
            xpp.next();
            if ((XmlPullParserUtil.isStartTag(xpp, "clearkey:Laurl") || XmlPullParserUtil.isStartTag(xpp, "dashif:Laurl")) && xpp.next() == 4) {
                licenseServerUrl = xpp.getText();
            } else if (XmlPullParserUtil.isStartTag(xpp, "ms:laurl")) {
                licenseServerUrl = xpp.getAttributeValue(null, "licenseUrl");
            } else if (data == null && XmlPullParserUtil.isStartTagIgnorePrefix(xpp, "pssh") && xpp.next() == 4) {
                data = Base64.decode(xpp.getText(), 0);
                uuid = PsshAtomUtil.parseUuid(data);
                if (uuid == null) {
                    Log.w(TAG, "Skipping malformed cenc:pssh data");
                    data = null;
                }
            } else if (data == null && C.PLAYREADY_UUID.equals(uuid) && XmlPullParserUtil.isStartTag(xpp, "mspr:pro") && xpp.next() == 4) {
                data = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(xpp.getText(), 0));
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "ContentProtection"));
        DrmInitData.SchemeData schemeData = uuid != null ? new DrmInitData.SchemeData(uuid, licenseServerUrl, MimeTypes.VIDEO_MP4, data) : null;
        return Pair.create(schemeType, schemeData);
    }

    protected void parseAdaptationSetChild(XmlPullParser xpp) throws XmlPullParserException, IOException {
        maybeSkipTag(xpp);
    }

    protected RepresentationInfo parseRepresentation(XmlPullParser xpp, List<BaseUrl> parentBaseUrls, String adaptationSetMimeType, String adaptationSetCodecs, int adaptationSetWidth, int adaptationSetHeight, float adaptationSetFrameRate, int adaptationSetAudioChannels, int adaptationSetAudioSamplingRate, String adaptationSetLanguage, List<Descriptor> adaptationSetRoleDescriptors, List<Descriptor> adaptationSetAccessibilityDescriptors, List<Descriptor> adaptationSetEssentialProperties, List<Descriptor> adaptationSetSupplementalProperties, SegmentBase segmentBase, long periodStartUnixTimeMs, long periodDurationMs, long baseUrlAvailabilityTimeOffsetUs, long segmentBaseAvailabilityTimeOffsetUs, long timeShiftBufferDepthMs, boolean dvbProfileDeclared) throws XmlPullParserException, IOException {
        long baseUrlAvailabilityTimeOffsetUs2;
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        ArrayList<Descriptor> inbandEventStreams;
        ArrayList<Descriptor> essentialProperties;
        ArrayList<Descriptor> supplementalProperties;
        long segmentBaseAvailabilityTimeOffsetUs2;
        XmlPullParser xmlPullParser = xpp;
        String id = xmlPullParser.getAttributeValue(null, "id");
        int bandwidth = parseInt(xmlPullParser, "bandwidth", -1);
        String mimeType = parseString(xmlPullParser, "mimeType", adaptationSetMimeType);
        String codecs = parseString(xmlPullParser, "codecs", adaptationSetCodecs);
        int width = parseInt(xmlPullParser, "width", adaptationSetWidth);
        int height = parseInt(xmlPullParser, "height", adaptationSetHeight);
        float frameRate = parseFrameRate(xmlPullParser, adaptationSetFrameRate);
        int audioSamplingRate = parseInt(xmlPullParser, "audioSamplingRate", adaptationSetAudioSamplingRate);
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas2 = new ArrayList<>();
        ArrayList<Descriptor> inbandEventStreams2 = new ArrayList<>();
        ArrayList<Descriptor> essentialProperties2 = new ArrayList<>(adaptationSetEssentialProperties);
        ArrayList<Descriptor> supplementalProperties2 = new ArrayList<>(adaptationSetSupplementalProperties);
        ArrayList<BaseUrl> baseUrls = new ArrayList<>();
        int audioChannels = adaptationSetAudioChannels;
        String drmSchemeType = null;
        ArrayList<Descriptor> supplementalProperties3 = supplementalProperties2;
        ArrayList<Descriptor> essentialProperties3 = essentialProperties2;
        boolean seenFirstBaseUrl = false;
        SegmentBase segmentBase2 = segmentBase;
        long baseUrlAvailabilityTimeOffsetUs3 = baseUrlAvailabilityTimeOffsetUs;
        long segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs;
        while (true) {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!seenFirstBaseUrl) {
                    baseUrlAvailabilityTimeOffsetUs3 = parseAvailabilityTimeOffsetUs(xmlPullParser, baseUrlAvailabilityTimeOffsetUs3);
                    seenFirstBaseUrl = true;
                }
                baseUrls.addAll(parseBaseUrl(xmlPullParser, parentBaseUrls, dvbProfileDeclared));
                baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs3;
                inbandEventStreams = inbandEventStreams2;
                drmSchemeDatas = drmSchemeDatas2;
                essentialProperties = essentialProperties3;
                supplementalProperties = supplementalProperties3;
                segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs3;
            } else {
                long baseUrlAvailabilityTimeOffsetUs4 = baseUrlAvailabilityTimeOffsetUs3;
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                    baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                    audioChannels = parseAudioChannelConfiguration(xpp);
                    inbandEventStreams = inbandEventStreams2;
                    drmSchemeDatas = drmSchemeDatas2;
                    essentialProperties = essentialProperties3;
                    supplementalProperties = supplementalProperties3;
                    segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs3;
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                    baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                    segmentBase2 = parseSegmentBase(xmlPullParser, (SegmentBase.SingleSegmentBase) segmentBase2);
                    inbandEventStreams = inbandEventStreams2;
                    drmSchemeDatas = drmSchemeDatas2;
                    essentialProperties = essentialProperties3;
                    supplementalProperties = supplementalProperties3;
                    segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs3;
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                    long segmentBaseAvailabilityTimeOffsetUs4 = parseAvailabilityTimeOffsetUs(xmlPullParser, segmentBaseAvailabilityTimeOffsetUs3);
                    baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                    ArrayList<Descriptor> essentialProperties4 = essentialProperties3;
                    segmentBase2 = parseSegmentList(xpp, (SegmentBase.SegmentList) segmentBase2, periodStartUnixTimeMs, periodDurationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs4, timeShiftBufferDepthMs);
                    segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs4;
                    essentialProperties = essentialProperties4;
                    supplementalProperties = supplementalProperties3;
                    inbandEventStreams = inbandEventStreams2;
                    drmSchemeDatas = drmSchemeDatas2;
                } else {
                    baseUrlAvailabilityTimeOffsetUs2 = baseUrlAvailabilityTimeOffsetUs4;
                    ArrayList<Descriptor> inbandEventStreams3 = inbandEventStreams2;
                    ArrayList<DrmInitData.SchemeData> drmSchemeDatas3 = drmSchemeDatas2;
                    ArrayList<Descriptor> essentialProperties5 = essentialProperties3;
                    ArrayList<Descriptor> supplementalProperties4 = supplementalProperties3;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                        segmentBaseAvailabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser, segmentBaseAvailabilityTimeOffsetUs3);
                        segmentBase2 = parseSegmentTemplate(xpp, (SegmentBase.SegmentTemplate) segmentBase2, adaptationSetSupplementalProperties, periodStartUnixTimeMs, periodDurationMs, baseUrlAvailabilityTimeOffsetUs2, segmentBaseAvailabilityTimeOffsetUs2, timeShiftBufferDepthMs);
                        essentialProperties = essentialProperties5;
                        supplementalProperties = supplementalProperties4;
                        inbandEventStreams = inbandEventStreams3;
                        drmSchemeDatas = drmSchemeDatas3;
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "ContentProtection")) {
                        Pair<String, DrmInitData.SchemeData> contentProtection = parseContentProtection(xpp);
                        if (contentProtection.first != null) {
                            drmSchemeType = (String) contentProtection.first;
                        }
                        if (contentProtection.second == null) {
                            drmSchemeDatas = drmSchemeDatas3;
                        } else {
                            drmSchemeDatas = drmSchemeDatas3;
                            drmSchemeDatas.add((DrmInitData.SchemeData) contentProtection.second);
                        }
                        segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs3;
                        essentialProperties = essentialProperties5;
                        supplementalProperties = supplementalProperties4;
                        inbandEventStreams = inbandEventStreams3;
                    } else {
                        drmSchemeDatas = drmSchemeDatas3;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser, "InbandEventStream")) {
                            inbandEventStreams = inbandEventStreams3;
                            inbandEventStreams.add(parseDescriptor(xmlPullParser, "InbandEventStream"));
                            essentialProperties = essentialProperties5;
                            supplementalProperties = supplementalProperties4;
                        } else {
                            inbandEventStreams = inbandEventStreams3;
                            if (XmlPullParserUtil.isStartTag(xmlPullParser, "EssentialProperty")) {
                                essentialProperties = essentialProperties5;
                                essentialProperties.add(parseDescriptor(xmlPullParser, "EssentialProperty"));
                                supplementalProperties = supplementalProperties4;
                            } else {
                                essentialProperties = essentialProperties5;
                                if (XmlPullParserUtil.isStartTag(xmlPullParser, "SupplementalProperty")) {
                                    supplementalProperties = supplementalProperties4;
                                    supplementalProperties.add(parseDescriptor(xmlPullParser, "SupplementalProperty"));
                                } else {
                                    supplementalProperties = supplementalProperties4;
                                    maybeSkipTag(xpp);
                                }
                            }
                        }
                        segmentBaseAvailabilityTimeOffsetUs2 = segmentBaseAvailabilityTimeOffsetUs3;
                    }
                }
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "Representation")) {
                break;
            }
            xmlPullParser = xpp;
            segmentBaseAvailabilityTimeOffsetUs3 = segmentBaseAvailabilityTimeOffsetUs2;
            supplementalProperties3 = supplementalProperties;
            essentialProperties3 = essentialProperties;
            baseUrlAvailabilityTimeOffsetUs3 = baseUrlAvailabilityTimeOffsetUs2;
            inbandEventStreams2 = inbandEventStreams;
            drmSchemeDatas2 = drmSchemeDatas;
        }
        ArrayList<Descriptor> supplementalProperties5 = supplementalProperties;
        ArrayList<Descriptor> essentialProperties6 = essentialProperties;
        ArrayList<Descriptor> inbandEventStreams4 = inbandEventStreams;
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas4 = drmSchemeDatas;
        Format format = buildFormat(id, mimeType, width, height, frameRate, audioChannels, audioSamplingRate, bandwidth, adaptationSetLanguage, adaptationSetRoleDescriptors, adaptationSetAccessibilityDescriptors, codecs, essentialProperties6, supplementalProperties5);
        return new RepresentationInfo(format, !baseUrls.isEmpty() ? baseUrls : parentBaseUrls, segmentBase2 != null ? segmentBase2 : new SegmentBase.SingleSegmentBase(), drmSchemeType, drmSchemeDatas4, inbandEventStreams4, essentialProperties6, supplementalProperties5, -1L);
    }

    protected Format buildFormat(String id, String containerMimeType, int width, int height, float frameRate, int audioChannels, int audioSamplingRate, int bitrate, String language, List<Descriptor> roleDescriptors, List<Descriptor> accessibilityDescriptors, String codecs, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties) {
        int accessibilityChannel;
        String codecs2 = codecs;
        String sampleMimeType = getSampleMimeType(containerMimeType, codecs2);
        if (MimeTypes.AUDIO_E_AC3.equals(sampleMimeType)) {
            sampleMimeType = parseEac3SupplementalProperties(supplementalProperties);
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(sampleMimeType)) {
                codecs2 = MimeTypes.CODEC_E_AC3_JOC;
            }
        }
        int selectionFlags = parseSelectionFlagsFromRoleDescriptors(roleDescriptors);
        int roleFlags = parseRoleFlagsFromRoleDescriptors(roleDescriptors);
        int roleFlags2 = roleFlags | parseRoleFlagsFromAccessibilityDescriptors(accessibilityDescriptors) | parseRoleFlagsFromProperties(essentialProperties) | parseRoleFlagsFromProperties(supplementalProperties);
        Pair<Integer, Integer> tileCounts = parseTileCountFromProperties(essentialProperties);
        Format.Builder formatBuilder = new Format.Builder().setId(id).setContainerMimeType(containerMimeType).setSampleMimeType(sampleMimeType).setCodecs(codecs2).setPeakBitrate(bitrate).setSelectionFlags(selectionFlags).setRoleFlags(roleFlags2).setLanguage(language).setTileCountHorizontal(tileCounts != null ? ((Integer) tileCounts.first).intValue() : -1).setTileCountVertical(tileCounts != null ? ((Integer) tileCounts.second).intValue() : -1);
        if (MimeTypes.isVideo(sampleMimeType)) {
            formatBuilder.setWidth(width).setHeight(height).setFrameRate(frameRate);
        } else if (MimeTypes.isAudio(sampleMimeType)) {
            formatBuilder.setChannelCount(audioChannels).setSampleRate(audioSamplingRate);
        } else if (MimeTypes.isText(sampleMimeType)) {
            if (MimeTypes.APPLICATION_CEA608.equals(sampleMimeType)) {
                accessibilityChannel = parseCea608AccessibilityChannel(accessibilityDescriptors);
            } else if (!MimeTypes.APPLICATION_CEA708.equals(sampleMimeType)) {
                accessibilityChannel = -1;
            } else {
                accessibilityChannel = parseCea708AccessibilityChannel(accessibilityDescriptors);
            }
            formatBuilder.setAccessibilityChannel(accessibilityChannel);
        } else if (MimeTypes.isImage(sampleMimeType)) {
            formatBuilder.setWidth(width).setHeight(height);
        }
        return formatBuilder.build();
    }

    protected Representation buildRepresentation(RepresentationInfo representationInfo, String label, List<Label> labels, String extraDrmSchemeType, ArrayList<DrmInitData.SchemeData> extraDrmSchemeDatas, ArrayList<Descriptor> extraInbandEventStreams) {
        Format.Builder formatBuilder = representationInfo.format.buildUpon();
        if (label != null && labels.isEmpty()) {
            formatBuilder.setLabel(label);
        } else {
            formatBuilder.setLabels(labels);
        }
        String drmSchemeType = representationInfo.drmSchemeType;
        if (drmSchemeType == null) {
            drmSchemeType = extraDrmSchemeType;
        }
        ArrayList<DrmInitData.SchemeData> drmSchemeDatas = representationInfo.drmSchemeDatas;
        drmSchemeDatas.addAll(extraDrmSchemeDatas);
        if (!drmSchemeDatas.isEmpty()) {
            fillInClearKeyInformation(drmSchemeDatas);
            filterRedundantIncompleteSchemeDatas(drmSchemeDatas);
            formatBuilder.setDrmInitData(new DrmInitData(drmSchemeType, drmSchemeDatas));
        }
        ArrayList<Descriptor> inbandEventStreams = representationInfo.inbandEventStreams;
        inbandEventStreams.addAll(extraInbandEventStreams);
        return Representation.newInstance(representationInfo.revisionId, formatBuilder.build(), representationInfo.baseUrls, representationInfo.segmentBase, inbandEventStreams, representationInfo.essentialProperties, representationInfo.supplementalProperties, null);
    }

    protected SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xpp, SegmentBase.SingleSegmentBase parent) throws XmlPullParserException, IOException {
        long indexLength;
        long indexStart;
        long timescale = parseLong(xpp, "timescale", parent != null ? parent.timescale : 1L);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", parent != null ? parent.presentationTimeOffset : 0L);
        long indexStart2 = parent != null ? parent.indexStart : 0L;
        long indexLength2 = parent != null ? parent.indexLength : 0L;
        String indexRangeText = xpp.getAttributeValue(null, "indexRange");
        if (indexRangeText == null) {
            indexLength = indexLength2;
            indexStart = indexStart2;
        } else {
            String[] indexRange = indexRangeText.split("-");
            long indexStart3 = Long.parseLong(indexRange[0]);
            long indexLength3 = (Long.parseLong(indexRange[1]) - indexStart3) + 1;
            indexLength = indexLength3;
            indexStart = indexStart3;
        }
        RangedUri initialization = parent != null ? parent.initialization : null;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Initialization")) {
                initialization = parseInitialization(xpp);
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentBase"));
        return buildSingleSegmentBase(initialization, timescale, presentationTimeOffset, indexStart, indexLength);
    }

    protected SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri initialization, long timescale, long presentationTimeOffset, long indexStart, long indexLength) {
        return new SegmentBase.SingleSegmentBase(initialization, timescale, presentationTimeOffset, indexStart, indexLength);
    }

    protected SegmentBase.SegmentList parseSegmentList(XmlPullParser xpp, SegmentBase.SegmentList parent, long periodStartUnixTimeMs, long periodDurationMs, long baseUrlAvailabilityTimeOffsetUs, long segmentBaseAvailabilityTimeOffsetUs, long timeShiftBufferDepthMs) throws XmlPullParserException, IOException {
        RangedUri initialization;
        List<SegmentBase.SegmentTimelineElement> timeline;
        List<RangedUri> segments;
        long timescale = parseLong(xpp, "timescale", parent != null ? parent.timescale : 1L);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", parent != null ? parent.presentationTimeOffset : 0L);
        long duration = parseLong(xpp, LastStreamStore.KEY_DURATION, parent != null ? parent.duration : C.TIME_UNSET);
        long startNumber = parseLong(xpp, "startNumber", parent != null ? parent.startNumber : 1L);
        long availabilityTimeOffsetUs = getFinalAvailabilityTimeOffset(baseUrlAvailabilityTimeOffsetUs, segmentBaseAvailabilityTimeOffsetUs);
        RangedUri initialization2 = null;
        List<SegmentBase.SegmentTimelineElement> timeline2 = null;
        List<RangedUri> segments2 = null;
        do {
            xpp.next();
            if (!XmlPullParserUtil.isStartTag(xpp, "Initialization")) {
                if (!XmlPullParserUtil.isStartTag(xpp, "SegmentTimeline")) {
                    if (XmlPullParserUtil.isStartTag(xpp, "SegmentURL")) {
                        if (segments2 == null) {
                            segments2 = new ArrayList<>();
                        }
                        segments2.add(parseSegmentUrl(xpp));
                    } else {
                        maybeSkipTag(xpp);
                    }
                } else {
                    timeline2 = parseSegmentTimeline(xpp, timescale, periodDurationMs);
                }
            } else {
                initialization2 = parseInitialization(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentList"));
        if (parent == null) {
            initialization = initialization2;
            timeline = timeline2;
            segments = segments2;
        } else {
            RangedUri initialization3 = initialization2 != null ? initialization2 : parent.initialization;
            initialization = initialization3;
            timeline = timeline2 != null ? timeline2 : parent.segmentTimeline;
            segments = segments2 != null ? segments2 : parent.mediaSegments;
        }
        return buildSegmentList(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, availabilityTimeOffsetUs, segments, timeShiftBufferDepthMs, periodStartUnixTimeMs);
    }

    protected SegmentBase.SegmentList buildSegmentList(RangedUri initialization, long timescale, long presentationTimeOffset, long startNumber, long duration, List<SegmentBase.SegmentTimelineElement> timeline, long availabilityTimeOffsetUs, List<RangedUri> segments, long timeShiftBufferDepthMs, long periodStartUnixTimeMs) {
        return new SegmentBase.SegmentList(initialization, timescale, presentationTimeOffset, startNumber, duration, timeline, availabilityTimeOffsetUs, segments, Util.msToUs(timeShiftBufferDepthMs), Util.msToUs(periodStartUnixTimeMs));
    }

    protected SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xpp, SegmentBase.SegmentTemplate parent, List<Descriptor> adaptationSetSupplementalProperties, long periodStartUnixTimeMs, long periodDurationMs, long baseUrlAvailabilityTimeOffsetUs, long segmentBaseAvailabilityTimeOffsetUs, long timeShiftBufferDepthMs) throws XmlPullParserException, IOException {
        RangedUri initialization;
        List<SegmentBase.SegmentTimelineElement> timeline;
        XmlPullParser xmlPullParser = xpp;
        SegmentBase.SegmentTemplate segmentTemplate = parent;
        long timescale = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long presentationTimeOffset = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long duration = parseLong(xmlPullParser, LastStreamStore.KEY_DURATION, segmentTemplate != null ? segmentTemplate.duration : C.TIME_UNSET);
        long startNumber = parseLong(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1L);
        long endNumber = parseLastSegmentNumberSupplementalProperty(adaptationSetSupplementalProperties);
        long availabilityTimeOffsetUs = getFinalAvailabilityTimeOffset(baseUrlAvailabilityTimeOffsetUs, segmentBaseAvailabilityTimeOffsetUs);
        UrlTemplate mediaTemplate = parseUrlTemplate(xmlPullParser, "media", segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate initializationTemplate = parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        RangedUri initialization2 = null;
        List<SegmentBase.SegmentTimelineElement> timeline2 = null;
        while (true) {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization2 = parseInitialization(xpp);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                timeline2 = parseSegmentTimeline(xpp, timescale, periodDurationMs);
            } else {
                maybeSkipTag(xpp);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTemplate")) {
                break;
            }
            xmlPullParser = xpp;
            segmentTemplate = parent;
        }
        if (segmentTemplate == null) {
            initialization = initialization2;
            timeline = timeline2;
        } else {
            initialization = initialization2 != null ? initialization2 : segmentTemplate.initialization;
            timeline = timeline2 != null ? timeline2 : segmentTemplate.segmentTimeline;
        }
        return buildSegmentTemplate(initialization, timescale, presentationTimeOffset, startNumber, endNumber, duration, timeline, availabilityTimeOffsetUs, initializationTemplate, mediaTemplate, timeShiftBufferDepthMs, periodStartUnixTimeMs);
    }

    protected SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri initialization, long timescale, long presentationTimeOffset, long startNumber, long endNumber, long duration, List<SegmentBase.SegmentTimelineElement> timeline, long availabilityTimeOffsetUs, UrlTemplate initializationTemplate, UrlTemplate mediaTemplate, long timeShiftBufferDepthMs, long periodStartUnixTimeMs) {
        return new SegmentBase.SegmentTemplate(initialization, timescale, presentationTimeOffset, startNumber, endNumber, duration, timeline, availabilityTimeOffsetUs, initializationTemplate, mediaTemplate, Util.msToUs(timeShiftBufferDepthMs), Util.msToUs(periodStartUnixTimeMs));
    }

    protected EventStream parseEventStream(XmlPullParser xpp) throws XmlPullParserException, IOException {
        String schemeIdUri = parseString(xpp, "schemeIdUri", "");
        String value = parseString(xpp, "value", "");
        long timescale = parseLong(xpp, "timescale", 1L);
        long presentationTimeOffset = parseLong(xpp, "presentationTimeOffset", 0L);
        List<Pair<Long, EventMessage>> eventMessages = new ArrayList<>();
        ByteArrayOutputStream scratchOutputStream = new ByteArrayOutputStream(512);
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Event")) {
                eventMessages.add(parseEvent(xpp, schemeIdUri, value, timescale, presentationTimeOffset, scratchOutputStream));
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "EventStream"));
        long[] presentationTimesUs = new long[eventMessages.size()];
        EventMessage[] events = new EventMessage[eventMessages.size()];
        for (int i = 0; i < eventMessages.size(); i++) {
            Pair<Long, EventMessage> event = eventMessages.get(i);
            presentationTimesUs[i] = ((Long) event.first).longValue();
            events[i] = (EventMessage) event.second;
        }
        return buildEventStream(schemeIdUri, value, timescale, presentationTimesUs, events);
    }

    protected EventStream buildEventStream(String schemeIdUri, String value, long timescale, long[] presentationTimesUs, EventMessage[] events) {
        return new EventStream(schemeIdUri, value, timescale, presentationTimesUs, events);
    }

    protected Pair<Long, EventMessage> parseEvent(XmlPullParser xpp, String schemeIdUri, String value, long timescale, long presentationTimeOffset, ByteArrayOutputStream scratchOutputStream) throws IOException, XmlPullParserException {
        long id = parseLong(xpp, "id", 0L);
        long duration = parseLong(xpp, LastStreamStore.KEY_DURATION, C.TIME_UNSET);
        long presentationTime = parseLong(xpp, "presentationTime", 0L);
        long durationMs = Util.scaleLargeTimestamp(duration, 1000L, timescale);
        long presentationTimesUs = Util.scaleLargeTimestamp(presentationTime - presentationTimeOffset, 1000000L, timescale);
        String messageData = parseString(xpp, "messageData", null);
        byte[] eventObject = parseEventObject(xpp, scratchOutputStream);
        return Pair.create(Long.valueOf(presentationTimesUs), buildEvent(schemeIdUri, value, id, durationMs, messageData == null ? eventObject : Util.getUtf8Bytes(messageData)));
    }

    protected byte[] parseEventObject(XmlPullParser xpp, ByteArrayOutputStream scratchOutputStream) throws XmlPullParserException, IOException {
        scratchOutputStream.reset();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        xmlSerializer.setOutput(scratchOutputStream, Charsets.UTF_8.name());
        xpp.nextToken();
        while (!XmlPullParserUtil.isEndTag(xpp, "Event")) {
            switch (xpp.getEventType()) {
                case 0:
                    xmlSerializer.startDocument(null, false);
                    break;
                case 1:
                    xmlSerializer.endDocument();
                    break;
                case 2:
                    xmlSerializer.startTag(xpp.getNamespace(), xpp.getName());
                    for (int i = 0; i < xpp.getAttributeCount(); i++) {
                        xmlSerializer.attribute(xpp.getAttributeNamespace(i), xpp.getAttributeName(i), xpp.getAttributeValue(i));
                    }
                    break;
                case 3:
                    xmlSerializer.endTag(xpp.getNamespace(), xpp.getName());
                    break;
                case 4:
                    xmlSerializer.text(xpp.getText());
                    break;
                case 5:
                    xmlSerializer.cdsect(xpp.getText());
                    break;
                case 6:
                    xmlSerializer.entityRef(xpp.getText());
                    break;
                case 7:
                    xmlSerializer.ignorableWhitespace(xpp.getText());
                    break;
                case 8:
                    xmlSerializer.processingInstruction(xpp.getText());
                    break;
                case 9:
                    xmlSerializer.comment(xpp.getText());
                    break;
                case 10:
                    xmlSerializer.docdecl(xpp.getText());
                    break;
            }
            xpp.nextToken();
        }
        xmlSerializer.flush();
        return scratchOutputStream.toByteArray();
    }

    protected EventMessage buildEvent(String schemeIdUri, String value, long id, long durationMs, byte[] messageData) {
        return new EventMessage(schemeIdUri, value, durationMs, id, messageData);
    }

    protected List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xpp, long timescale, long periodDurationMs) throws XmlPullParserException, IOException {
        long startTime;
        long startTime2;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = new ArrayList<>();
        long startTime3 = 0;
        long elementDuration = -9223372036854775807L;
        int elementRepeatCount = 0;
        boolean havePreviousTimelineElement = false;
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, ExifInterface.LATITUDE_SOUTH)) {
                long newStartTime = parseLong(xpp, "t", C.TIME_UNSET);
                if (!havePreviousTimelineElement) {
                    long startTime4 = startTime3;
                    startTime = -9223372036854775807L;
                    startTime2 = startTime4;
                } else {
                    startTime = -9223372036854775807L;
                    startTime2 = addSegmentTimelineElementsToList(segmentTimeline, startTime3, elementDuration, elementRepeatCount, newStartTime);
                }
                if (newStartTime != startTime) {
                    startTime2 = newStartTime;
                }
                long elementDuration2 = parseLong(xpp, "d", startTime);
                int elementRepeatCount2 = parseInt(xpp, "r", 0);
                startTime3 = startTime2;
                elementDuration = elementDuration2;
                elementRepeatCount = elementRepeatCount2;
                havePreviousTimelineElement = true;
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "SegmentTimeline"));
        if (havePreviousTimelineElement) {
            long periodDuration = Util.scaleLargeTimestamp(periodDurationMs, timescale, 1000L);
            addSegmentTimelineElementsToList(segmentTimeline, startTime3, elementDuration, elementRepeatCount, periodDuration);
        }
        return segmentTimeline;
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> segmentTimeline, long startTime, long elementDuration, int elementRepeatCount, long endTime) {
        int count;
        if (elementRepeatCount >= 0) {
            count = elementRepeatCount + 1;
        } else {
            count = (int) Util.ceilDivide(endTime - startTime, elementDuration);
        }
        for (int i = 0; i < count; i++) {
            segmentTimeline.add(buildSegmentTimelineElement(startTime, elementDuration));
            startTime += elementDuration;
        }
        return startTime;
    }

    protected SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long startTime, long duration) {
        return new SegmentBase.SegmentTimelineElement(startTime, duration);
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser xpp, String name, UrlTemplate defaultValue) {
        String valueString = xpp.getAttributeValue(null, name);
        if (valueString != null) {
            return UrlTemplate.compile(valueString);
        }
        return defaultValue;
    }

    protected RangedUri parseInitialization(XmlPullParser xpp) {
        return parseRangedUrl(xpp, "sourceURL", "range");
    }

    protected RangedUri parseSegmentUrl(XmlPullParser xpp) {
        return parseRangedUrl(xpp, "media", "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xpp, String urlAttribute, String rangeAttribute) {
        long rangeStart;
        long rangeLength;
        String urlText = xpp.getAttributeValue(null, urlAttribute);
        String rangeText = xpp.getAttributeValue(null, rangeAttribute);
        if (rangeText == null) {
            rangeStart = 0;
            rangeLength = -1;
        } else {
            String[] rangeTextArray = rangeText.split("-");
            long rangeStart2 = Long.parseLong(rangeTextArray[0]);
            if (rangeTextArray.length != 2) {
                rangeStart = rangeStart2;
                rangeLength = -1;
            } else {
                long rangeLength2 = (Long.parseLong(rangeTextArray[1]) - rangeStart2) + 1;
                rangeStart = rangeStart2;
                rangeLength = rangeLength2;
            }
        }
        return buildRangedUri(urlText, rangeStart, rangeLength);
    }

    protected RangedUri buildRangedUri(String urlText, long rangeStart, long rangeLength) {
        return new RangedUri(urlText, rangeStart, rangeLength);
    }

    protected ProgramInformation parseProgramInformation(XmlPullParser xpp) throws IOException, XmlPullParserException {
        String title = null;
        String source = null;
        String copyright = null;
        String moreInformationURL = parseString(xpp, "moreInformationURL", null);
        String lang = parseString(xpp, "lang", null);
        do {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp, "Title")) {
                title = xpp.nextText();
            } else if (XmlPullParserUtil.isStartTag(xpp, "Source")) {
                source = xpp.nextText();
            } else if (XmlPullParserUtil.isStartTag(xpp, ExifInterface.TAG_COPYRIGHT)) {
                copyright = xpp.nextText();
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, "ProgramInformation"));
        return new ProgramInformation(title, source, copyright, moreInformationURL, lang);
    }

    protected Label parseLabel(XmlPullParser xpp) throws XmlPullParserException, IOException {
        String lang = xpp.getAttributeValue(null, "lang");
        String value = parseText(xpp, TextFieldImplKt.LabelId);
        return new Label(lang, value);
    }

    protected List<BaseUrl> parseBaseUrl(XmlPullParser xpp, List<BaseUrl> parentBaseUrls, boolean dvbProfileDeclared) throws XmlPullParserException, IOException {
        int priority;
        String priorityValue = xpp.getAttributeValue(null, "dvb:priority");
        if (priorityValue != null) {
            priority = Integer.parseInt(priorityValue);
        } else {
            priority = dvbProfileDeclared ? 1 : Integer.MIN_VALUE;
        }
        String weightValue = xpp.getAttributeValue(null, "dvb:weight");
        int weight = weightValue != null ? Integer.parseInt(weightValue) : 1;
        String serviceLocation = xpp.getAttributeValue(null, "serviceLocation");
        String baseUrl = parseText(xpp, "BaseURL");
        if (UriUtil.isAbsolute(baseUrl)) {
            if (serviceLocation == null) {
                serviceLocation = baseUrl;
            }
            return Lists.newArrayList(new BaseUrl(baseUrl, serviceLocation, priority, weight));
        }
        List<BaseUrl> baseUrls = new ArrayList<>();
        for (int i = 0; i < parentBaseUrls.size(); i++) {
            BaseUrl parentBaseUrl = parentBaseUrls.get(i);
            String resolvedBaseUri = UriUtil.resolve(parentBaseUrl.url, baseUrl);
            String resolvedServiceLocation = serviceLocation == null ? resolvedBaseUri : serviceLocation;
            if (dvbProfileDeclared) {
                priority = parentBaseUrl.priority;
                weight = parentBaseUrl.weight;
                resolvedServiceLocation = parentBaseUrl.serviceLocation;
            }
            baseUrls.add(new BaseUrl(resolvedBaseUri, resolvedServiceLocation, priority, weight));
        }
        return baseUrls;
    }

    protected long parseAvailabilityTimeOffsetUs(XmlPullParser xpp, long parentAvailabilityTimeOffsetUs) {
        String value = xpp.getAttributeValue(null, "availabilityTimeOffset");
        if (value == null) {
            return parentAvailabilityTimeOffsetUs;
        }
        if ("INF".equals(value)) {
            return Long.MAX_VALUE;
        }
        return Float.parseFloat(value) * 1000000.0f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected int parseAudioChannelConfiguration(XmlPullParser xpp) throws XmlPullParserException, IOException {
        char c;
        int audioChannels;
        String schemeIdUri = parseString(xpp, "schemeIdUri", null);
        switch (schemeIdUri.hashCode()) {
            case -2128649360:
                if (schemeIdUri.equals("urn:dts:dash:audio_channel_configuration:2012")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1352850286:
                if (schemeIdUri.equals("urn:mpeg:dash:23003:3:audio_channel_configuration:2011")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1138141449:
                if (schemeIdUri.equals("tag:dolby.com,2014:dash:audio_channel_configuration:2011")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -986633423:
                if (schemeIdUri.equals("urn:mpeg:mpegB:cicp:ChannelConfiguration")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -79006963:
                if (schemeIdUri.equals("tag:dts.com,2014:dash:audio_channel_configuration:2012")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 312179081:
                if (schemeIdUri.equals("tag:dts.com,2018:uhd:audio_channel_configuration")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2036691300:
                if (schemeIdUri.equals("urn:dolby:dash:audio_channel_configuration:2011")) {
                    c = 6;
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
                audioChannels = parseInt(xpp, "value", -1);
                break;
            case 1:
                audioChannels = parseMpegChannelConfiguration(xpp);
                break;
            case 2:
            case 3:
                audioChannels = parseDtsChannelConfiguration(xpp);
                break;
            case 4:
                audioChannels = parseDtsxChannelConfiguration(xpp);
                break;
            case 5:
            case 6:
                audioChannels = parseDolbyChannelConfiguration(xpp);
                break;
            default:
                audioChannels = -1;
                break;
        }
        do {
            xpp.next();
        } while (!XmlPullParserUtil.isEndTag(xpp, "AudioChannelConfiguration"));
        return audioChannels;
    }

    protected int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> roleDescriptors) {
        int result = 0;
        for (int i = 0; i < roleDescriptors.size(); i++) {
            Descriptor descriptor = roleDescriptors.get(i);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                result |= parseSelectionFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return result;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected int parseSelectionFlagsFromDashRoleScheme(String value) {
        char c;
        if (value == null) {
            return 0;
        }
        switch (value.hashCode()) {
            case -1574842690:
                if (value.equals("forced_subtitle")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1396432756:
                if (value.equals("forced-subtitle")) {
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
                return 2;
            default:
                return 0;
        }
    }

    protected int parseRoleFlagsFromRoleDescriptors(List<Descriptor> roleDescriptors) {
        int result = 0;
        for (int i = 0; i < roleDescriptors.size(); i++) {
            Descriptor descriptor = roleDescriptors.get(i);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                result |= parseRoleFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return result;
    }

    protected int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> accessibilityDescriptors) {
        int result = 0;
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            Descriptor descriptor = accessibilityDescriptors.get(i);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                result |= parseRoleFlagsFromDashRoleScheme(descriptor.value);
            } else if (Ascii.equalsIgnoreCase("urn:tva:metadata:cs:AudioPurposeCS:2007", descriptor.schemeIdUri)) {
                result |= parseTvaAudioPurposeCsValue(descriptor.value);
            }
        }
        return result;
    }

    protected int parseRoleFlagsFromProperties(List<Descriptor> accessibilityDescriptors) {
        int result = 0;
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            Descriptor descriptor = accessibilityDescriptors.get(i);
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/trickmode", descriptor.schemeIdUri)) {
                result |= 16384;
            }
        }
        return result;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected int parseRoleFlagsFromDashRoleScheme(String value) {
        char c;
        if (value == null) {
            return 0;
        }
        switch (value.hashCode()) {
            case -2060497896:
                if (value.equals("subtitle")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1724546052:
                if (value.equals("description")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1580883024:
                if (value.equals("enhanced-audio-intelligibility")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1574842690:
                if (value.equals("forced_subtitle")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1408024454:
                if (value.equals("alternate")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1396432756:
                if (value.equals("forced-subtitle")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 99825:
                if (value.equals("dub")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3343801:
                if (value.equals("main")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3530173:
                if (value.equals("sign")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 552573414:
                if (value.equals("caption")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 899152809:
                if (value.equals("commentary")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1629013393:
                if (value.equals("emergency")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1855372047:
                if (value.equals("supplementary")) {
                    c = 2;
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
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
            case '\b':
            case '\t':
                return 128;
            case '\n':
                return 256;
            case 11:
                return 512;
            case '\f':
                return 2048;
            default:
                return 0;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected int parseTvaAudioPurposeCsValue(String value) {
        char c;
        if (value == null) {
            return 0;
        }
        switch (value.hashCode()) {
            case 49:
                if (value.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (value.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (value.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (value.equals("4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 53:
            default:
                c = 65535;
                break;
            case 54:
                if (value.equals("6")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return 512;
            case 1:
                return 2048;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 1;
            default:
                return 0;
        }
    }

    protected String[] parseProfiles(XmlPullParser xpp, String attributeName, String[] defaultValue) {
        String attributeValue = xpp.getAttributeValue(null, attributeName);
        if (attributeValue == null) {
            return defaultValue;
        }
        return attributeValue.split(",");
    }

    protected Pair<Integer, Integer> parseTileCountFromProperties(List<Descriptor> essentialProperties) {
        for (int i = 0; i < essentialProperties.size(); i++) {
            Descriptor descriptor = essentialProperties.get(i);
            if ((Ascii.equalsIgnoreCase("http://dashif.org/thumbnail_tile", descriptor.schemeIdUri) || Ascii.equalsIgnoreCase("http://dashif.org/guidelines/thumbnail_tile", descriptor.schemeIdUri)) && descriptor.value != null) {
                String size = descriptor.value;
                String[] sizeSplit = Util.split(size, "x");
                if (sizeSplit.length != 2) {
                    continue;
                } else {
                    try {
                        int tileCountHorizontal = Integer.parseInt(sizeSplit[0]);
                        int tileCountVertical = Integer.parseInt(sizeSplit[1]);
                        return Pair.create(Integer.valueOf(tileCountHorizontal), Integer.valueOf(tileCountVertical));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
        return null;
    }

    public static void maybeSkipTag(XmlPullParser xpp) throws IOException, XmlPullParserException {
        if (!XmlPullParserUtil.isStartTag(xpp)) {
            return;
        }
        int depth = 1;
        while (depth != 0) {
            xpp.next();
            if (XmlPullParserUtil.isStartTag(xpp)) {
                depth++;
            } else if (XmlPullParserUtil.isEndTag(xpp)) {
                depth--;
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> schemeDatas) {
        for (int i = schemeDatas.size() - 1; i >= 0; i--) {
            DrmInitData.SchemeData schemeData = schemeDatas.get(i);
            if (!schemeData.hasData()) {
                int j = 0;
                while (true) {
                    if (j >= schemeDatas.size()) {
                        break;
                    }
                    if (!schemeDatas.get(j).canReplace(schemeData)) {
                        j++;
                    } else {
                        schemeDatas.remove(i);
                        break;
                    }
                }
            }
        }
    }

    private static void fillInClearKeyInformation(ArrayList<DrmInitData.SchemeData> schemeDatas) {
        String clearKeyLicenseServerUrl = null;
        int i = 0;
        while (true) {
            if (i >= schemeDatas.size()) {
                break;
            }
            DrmInitData.SchemeData schemeData = schemeDatas.get(i);
            if (!C.CLEARKEY_UUID.equals(schemeData.uuid) || schemeData.licenseServerUrl == null) {
                i++;
            } else {
                clearKeyLicenseServerUrl = schemeData.licenseServerUrl;
                schemeDatas.remove(i);
                break;
            }
        }
        if (clearKeyLicenseServerUrl == null) {
            return;
        }
        for (int i2 = 0; i2 < schemeDatas.size(); i2++) {
            DrmInitData.SchemeData schemeData2 = schemeDatas.get(i2);
            if (C.COMMON_PSSH_UUID.equals(schemeData2.uuid) && schemeData2.licenseServerUrl == null) {
                schemeDatas.set(i2, new DrmInitData.SchemeData(C.CLEARKEY_UUID, clearKeyLicenseServerUrl, schemeData2.mimeType, schemeData2.data));
            }
        }
    }

    private static String getSampleMimeType(String containerMimeType, String codecs) {
        if (MimeTypes.isAudio(containerMimeType)) {
            return MimeTypes.getAudioMediaMimeType(codecs);
        }
        if (MimeTypes.isVideo(containerMimeType)) {
            return MimeTypes.getVideoMediaMimeType(codecs);
        }
        if (MimeTypes.isText(containerMimeType)) {
            return containerMimeType;
        }
        if (MimeTypes.isImage(containerMimeType)) {
            return containerMimeType;
        }
        if (MimeTypes.APPLICATION_MP4.equals(containerMimeType)) {
            String mimeType = MimeTypes.getMediaMimeType(codecs);
            return MimeTypes.TEXT_VTT.equals(mimeType) ? MimeTypes.APPLICATION_MP4VTT : mimeType;
        }
        return null;
    }

    private static String checkLanguageConsistency(String firstLanguage, String secondLanguage) {
        if (firstLanguage == null) {
            return secondLanguage;
        }
        if (secondLanguage == null) {
            return firstLanguage;
        }
        Assertions.checkState(firstLanguage.equals(secondLanguage));
        return firstLanguage;
    }

    private static int checkContentTypeConsistency(int firstType, int secondType) {
        if (firstType == -1) {
            return secondType;
        }
        if (secondType == -1) {
            return firstType;
        }
        Assertions.checkState(firstType == secondType);
        return firstType;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xpp, String tag) throws XmlPullParserException, IOException {
        String schemeIdUri = parseString(xpp, "schemeIdUri", "");
        String value = parseString(xpp, "value", null);
        String id = parseString(xpp, "id", null);
        do {
            xpp.next();
        } while (!XmlPullParserUtil.isEndTag(xpp, tag));
        return new Descriptor(schemeIdUri, value, id);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> accessibilityDescriptors) {
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            Descriptor descriptor = accessibilityDescriptors.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher accessibilityValueMatcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (accessibilityValueMatcher.matches()) {
                    return Integer.parseInt(accessibilityValueMatcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> accessibilityDescriptors) {
        for (int i = 0; i < accessibilityDescriptors.size(); i++) {
            Descriptor descriptor = accessibilityDescriptors.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher accessibilityValueMatcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (accessibilityValueMatcher.matches()) {
                    return Integer.parseInt(accessibilityValueMatcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> supplementalProperties) {
        for (int i = 0; i < supplementalProperties.size(); i++) {
            Descriptor descriptor = supplementalProperties.get(i);
            String schemeIdUri = descriptor.schemeIdUri;
            if (!"tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(schemeIdUri) || !"JOC".equals(descriptor.value)) {
                if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(schemeIdUri) && MimeTypes.CODEC_E_AC3_JOC.equals(descriptor.value)) {
                    return MimeTypes.AUDIO_E_AC3_JOC;
                }
            } else {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        return MimeTypes.AUDIO_E_AC3;
    }

    protected static float parseFrameRate(XmlPullParser xpp, float defaultValue) {
        String frameRateAttribute = xpp.getAttributeValue(null, "frameRate");
        if (frameRateAttribute == null) {
            return defaultValue;
        }
        Matcher frameRateMatcher = FRAME_RATE_PATTERN.matcher(frameRateAttribute);
        if (!frameRateMatcher.matches()) {
            return defaultValue;
        }
        int numerator = Integer.parseInt(frameRateMatcher.group(1));
        String denominatorString = frameRateMatcher.group(2);
        if (!TextUtils.isEmpty(denominatorString)) {
            float frameRate = numerator / Integer.parseInt(denominatorString);
            return frameRate;
        }
        float frameRate2 = numerator;
        return frameRate2;
    }

    protected static long parseDuration(XmlPullParser xpp, String name, long defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        if (value == null) {
            return defaultValue;
        }
        return Util.parseXsDuration(value);
    }

    protected static long parseDateTime(XmlPullParser xpp, String name, long defaultValue) throws ParserException {
        String value = xpp.getAttributeValue(null, name);
        if (value == null) {
            return defaultValue;
        }
        return Util.parseXsDateTime(value);
    }

    protected static String parseText(XmlPullParser xpp, String label) throws XmlPullParserException, IOException {
        String text = "";
        do {
            xpp.next();
            if (xpp.getEventType() == 4) {
                text = xpp.getText();
            } else {
                maybeSkipTag(xpp);
            }
        } while (!XmlPullParserUtil.isEndTag(xpp, label));
        return text;
    }

    protected static int parseInt(XmlPullParser xpp, String name, int defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    protected static long parseLong(XmlPullParser xpp, String name, long defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Long.parseLong(value);
    }

    protected static float parseFloat(XmlPullParser xpp, String name, float defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : Float.parseFloat(value);
    }

    protected static String parseString(XmlPullParser xpp, String name, String defaultValue) {
        String value = xpp.getAttributeValue(null, name);
        return value == null ? defaultValue : value;
    }

    protected static int parseMpegChannelConfiguration(XmlPullParser xpp) {
        int index = parseInt(xpp, "value", -1);
        if (index < 0 || index >= MPEG_CHANNEL_CONFIGURATION_MAPPING.length) {
            return -1;
        }
        return MPEG_CHANNEL_CONFIGURATION_MAPPING[index];
    }

    protected static int parseDtsChannelConfiguration(XmlPullParser xpp) {
        int channelCount = parseInt(xpp, "value", -1);
        if (channelCount <= 0 || channelCount >= 33) {
            return -1;
        }
        return channelCount;
    }

    protected static int parseDtsxChannelConfiguration(XmlPullParser xpp) {
        int channelCount;
        String value = xpp.getAttributeValue(null, "value");
        if (value == null || (channelCount = Integer.bitCount(Integer.parseInt(value, 16))) == 0) {
            return -1;
        }
        return channelCount;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected static int parseDolbyChannelConfiguration(XmlPullParser xpp) {
        char c;
        String value = xpp.getAttributeValue(null, "value");
        if (value == null) {
            return -1;
        }
        String lowerCase = Ascii.toLowerCase(value);
        switch (lowerCase.hashCode()) {
            case 1596796:
                if (lowerCase.equals("4000")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 2937391:
                if (lowerCase.equals("a000")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3094034:
                if (lowerCase.equals("f800")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3094035:
                if (lowerCase.equals("f801")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3133436:
                if (lowerCase.equals("fa01")) {
                    c = 4;
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
                return 1;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 8;
            default:
                return -1;
        }
    }

    protected static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> supplementalProperties) {
        for (int i = 0; i < supplementalProperties.size(); i++) {
            Descriptor descriptor = supplementalProperties.get(i);
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/last-segment-number", descriptor.schemeIdUri)) {
                return Long.parseLong(descriptor.value);
            }
        }
        return -1L;
    }

    private static long getFinalAvailabilityTimeOffset(long baseUrlAvailabilityTimeOffsetUs, long segmentBaseAvailabilityTimeOffsetUs) {
        long availabilityTimeOffsetUs = segmentBaseAvailabilityTimeOffsetUs;
        if (availabilityTimeOffsetUs == C.TIME_UNSET) {
            availabilityTimeOffsetUs = baseUrlAvailabilityTimeOffsetUs;
        }
        if (availabilityTimeOffsetUs == Long.MAX_VALUE) {
            return C.TIME_UNSET;
        }
        return availabilityTimeOffsetUs;
    }

    private boolean isDvbProfileDeclared(String[] profiles) {
        for (String profile : profiles) {
            if (profile.startsWith("urn:dvb:dash:profile:dvb-dash:")) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public static final class RepresentationInfo {
        public final ImmutableList<BaseUrl> baseUrls;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final List<Descriptor> essentialProperties;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;
        public final List<Descriptor> supplementalProperties;

        public RepresentationInfo(Format format, List<BaseUrl> baseUrls, SegmentBase segmentBase, String drmSchemeType, ArrayList<DrmInitData.SchemeData> drmSchemeDatas, ArrayList<Descriptor> inbandEventStreams, List<Descriptor> essentialProperties, List<Descriptor> supplementalProperties, long revisionId) {
            this.format = format;
            this.baseUrls = ImmutableList.copyOf((Collection) baseUrls);
            this.segmentBase = segmentBase;
            this.drmSchemeType = drmSchemeType;
            this.drmSchemeDatas = drmSchemeDatas;
            this.inbandEventStreams = inbandEventStreams;
            this.essentialProperties = essentialProperties;
            this.supplementalProperties = supplementalProperties;
            this.revisionId = revisionId;
        }
    }
}
