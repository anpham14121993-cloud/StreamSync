package com.streamsync.app.data.model;

import androidx.autofill.HintConstants;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MediaItem.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000f¢\u0006\u0002\u0010\u0017J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u000fHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u00104\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015HÆ\u0003J\t\u00105\u001a\u00020\u000fHÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÑ\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u000fHÆ\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\u000fHÖ\u0001J\t\u0010C\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u0016\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-¨\u0006D"}, d2 = {"Lcom/streamsync/app/data/model/StreamSource;", "", "mediaId", "", "streamUrl", "mimeType", "subtitles", "", "Lcom/streamsync/app/data/model/SubtitleTrack;", HintConstants.AUTOFILL_HINT_NAME, "quality", "provider", "spec", "size", "qualityValue", "", "addonId", "addonName", "addonLogo", "releaseName", "requestHeaders", "", "index", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;I)V", "getAddonId", "()Ljava/lang/String;", "getAddonLogo", "getAddonName", "displayTitle", "getDisplayTitle", "getIndex", "()I", "getMediaId", "getMimeType", "getName", "getProvider", "getQuality", "getQualityValue", "getReleaseName", "getRequestHeaders", "()Ljava/util/Map;", "getSize", "getSpec", "getStreamUrl", "getSubtitles", "()Ljava/util/List;", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final /* data */ class StreamSource {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonLogo;
    private final String addonName;
    private final int index;
    private final String mediaId;
    private final String mimeType;
    private final String name;
    private final String provider;
    private final String quality;
    private final int qualityValue;
    private final String releaseName;
    private final Map<String, String> requestHeaders;
    private final String size;
    private final String spec;
    private final String streamUrl;
    private final List<SubtitleTrack> subtitles;

    /* renamed from: component1, reason: from getter */
    public final String getMediaId() {
        return this.mediaId;
    }

    /* renamed from: component10, reason: from getter */
    public final int getQualityValue() {
        return this.qualityValue;
    }

    /* renamed from: component11, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* renamed from: component13, reason: from getter */
    public final String getAddonLogo() {
        return this.addonLogo;
    }

    /* renamed from: component14, reason: from getter */
    public final String getReleaseName() {
        return this.releaseName;
    }

    public final Map<String, String> component15() {
        return this.requestHeaders;
    }

    /* renamed from: component16, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* renamed from: component2, reason: from getter */
    public final String getStreamUrl() {
        return this.streamUrl;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    public final List<SubtitleTrack> component4() {
        return this.subtitles;
    }

    /* renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component6, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* renamed from: component7, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSpec() {
        return this.spec;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSize() {
        return this.size;
    }

    public final StreamSource copy(String mediaId, String streamUrl, String mimeType, List<SubtitleTrack> subtitles, String name, String quality, String provider, String spec, String size, int qualityValue, String addonId, String addonName, String addonLogo, String releaseName, Map<String, String> requestHeaders, int index) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(streamUrl, "streamUrl");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        return new StreamSource(mediaId, streamUrl, mimeType, subtitles, name, quality, provider, spec, size, qualityValue, addonId, addonName, addonLogo, releaseName, requestHeaders, index);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamSource)) {
            return false;
        }
        StreamSource streamSource = (StreamSource) other;
        return Intrinsics.areEqual(this.mediaId, streamSource.mediaId) && Intrinsics.areEqual(this.streamUrl, streamSource.streamUrl) && Intrinsics.areEqual(this.mimeType, streamSource.mimeType) && Intrinsics.areEqual(this.subtitles, streamSource.subtitles) && Intrinsics.areEqual(this.name, streamSource.name) && Intrinsics.areEqual(this.quality, streamSource.quality) && Intrinsics.areEqual(this.provider, streamSource.provider) && Intrinsics.areEqual(this.spec, streamSource.spec) && Intrinsics.areEqual(this.size, streamSource.size) && this.qualityValue == streamSource.qualityValue && Intrinsics.areEqual(this.addonId, streamSource.addonId) && Intrinsics.areEqual(this.addonName, streamSource.addonName) && Intrinsics.areEqual(this.addonLogo, streamSource.addonLogo) && Intrinsics.areEqual(this.releaseName, streamSource.releaseName) && Intrinsics.areEqual(this.requestHeaders, streamSource.requestHeaders) && this.index == streamSource.index;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.mediaId.hashCode() * 31) + this.streamUrl.hashCode()) * 31) + (this.mimeType == null ? 0 : this.mimeType.hashCode())) * 31) + this.subtitles.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.quality == null ? 0 : this.quality.hashCode())) * 31) + (this.provider == null ? 0 : this.provider.hashCode())) * 31) + (this.spec == null ? 0 : this.spec.hashCode())) * 31) + (this.size == null ? 0 : this.size.hashCode())) * 31) + Integer.hashCode(this.qualityValue)) * 31) + (this.addonId == null ? 0 : this.addonId.hashCode())) * 31) + (this.addonName == null ? 0 : this.addonName.hashCode())) * 31) + (this.addonLogo == null ? 0 : this.addonLogo.hashCode())) * 31) + (this.releaseName == null ? 0 : this.releaseName.hashCode())) * 31) + (this.requestHeaders != null ? this.requestHeaders.hashCode() : 0)) * 31) + Integer.hashCode(this.index);
    }

    public String toString() {
        return "StreamSource(mediaId=" + this.mediaId + ", streamUrl=" + this.streamUrl + ", mimeType=" + this.mimeType + ", subtitles=" + this.subtitles + ", name=" + this.name + ", quality=" + this.quality + ", provider=" + this.provider + ", spec=" + this.spec + ", size=" + this.size + ", qualityValue=" + this.qualityValue + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", addonLogo=" + this.addonLogo + ", releaseName=" + this.releaseName + ", requestHeaders=" + this.requestHeaders + ", index=" + this.index + ")";
    }

    public StreamSource(String mediaId, String streamUrl, String mimeType, List<SubtitleTrack> subtitles, String name, String quality, String provider, String spec, String size, int qualityValue, String addonId, String addonName, String addonLogo, String releaseName, Map<String, String> map, int index) {
        Intrinsics.checkNotNullParameter(mediaId, "mediaId");
        Intrinsics.checkNotNullParameter(streamUrl, "streamUrl");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        this.mediaId = mediaId;
        this.streamUrl = streamUrl;
        this.mimeType = mimeType;
        this.subtitles = subtitles;
        this.name = name;
        this.quality = quality;
        this.provider = provider;
        this.spec = spec;
        this.size = size;
        this.qualityValue = qualityValue;
        this.addonId = addonId;
        this.addonName = addonName;
        this.addonLogo = addonLogo;
        this.releaseName = releaseName;
        this.requestHeaders = map;
        this.index = index;
    }

    public /* synthetic */ StreamSource(String str, String str2, String str3, List list, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, String str12, Map map, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? CollectionsKt.emptyList() : list, (i3 & 16) != 0 ? null : str4, (i3 & 32) != 0 ? null : str5, (i3 & 64) != 0 ? null : str6, (i3 & 128) != 0 ? null : str7, (i3 & 256) != 0 ? null : str8, (i3 & 512) != 0 ? 0 : i, (i3 & 1024) != 0 ? null : str9, (i3 & 2048) != 0 ? null : str10, (i3 & 4096) != 0 ? null : str11, (i3 & 8192) != 0 ? null : str12, (i3 & 16384) != 0 ? null : map, (i3 & 32768) != 0 ? 0 : i2);
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final String getStreamUrl() {
        return this.streamUrl;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final List<SubtitleTrack> getSubtitles() {
        return this.subtitles;
    }

    public final String getName() {
        return this.name;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getSpec() {
        return this.spec;
    }

    public final String getSize() {
        return this.size;
    }

    public final int getQualityValue() {
        return this.qualityValue;
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final String getAddonLogo() {
        return this.addonLogo;
    }

    public final String getReleaseName() {
        return this.releaseName;
    }

    public final Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getDisplayTitle() {
        String rawName = this.name;
        String label = null;
        if (rawName == null || !(!StringsKt.isBlank(rawName))) {
            rawName = null;
        }
        String it = this.quality;
        if (it != null && (!StringsKt.isBlank(it))) {
            label = it;
        }
        List $this$_get_displayTitle__u24lambda_u243 = CollectionsKt.createListBuilder();
        if (rawName != null) {
            $this$_get_displayTitle__u24lambda_u243.add(rawName);
        }
        if (label != null) {
            boolean z = false;
            if (rawName != null && StringsKt.contains((CharSequence) rawName, (CharSequence) label, true)) {
                z = true;
            }
            if (!z) {
                $this$_get_displayTitle__u24lambda_u243.add(label);
            }
        }
        List parts = CollectionsKt.build($this$_get_displayTitle__u24lambda_u243);
        String joinToString$default = CollectionsKt.joinToString$default(parts, " • ", null, null, 0, null, null, 62, null);
        if (StringsKt.isBlank(joinToString$default)) {
            joinToString$default = "Nguồn phát " + (this.index + 1);
        }
        return joinToString$default;
    }
}
