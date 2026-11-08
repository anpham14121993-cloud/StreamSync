package com.streamsync.app.ui.player;

import android.net.Uri;
import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DolbyVisionExtractorsFactory.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¢\u0006\u0002\u0010\u000bJ5\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00110\u000fH\u0016¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0015J\u0012\u0010\u0016\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0002J)\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0019\u001a\u00020\u0003H\u0002¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/streamsync/app/ui/player/DolbyVisionExtractorsFactory;", "Landroidx/media3/extractor/ExtractorsFactory;", "stripDolbyVision", "", "injectHdrColorInfo", "(ZZ)V", "delegate", "Landroidx/media3/extractor/DefaultExtractorsFactory;", "createExtractors", "", "Landroidx/media3/extractor/Extractor;", "()[Landroidx/media3/extractor/Extractor;", "uri", "Landroid/net/Uri;", "responseHeaders", "", "", "", "(Landroid/net/Uri;Ljava/util/Map;)[Landroidx/media3/extractor/Extractor;", "shouldApply", "mimeType", "shouldApply$app_mobileDebug", "supports", "wrap", "extractors", "enabled", "([Landroidx/media3/extractor/Extractor;Z)[Landroidx/media3/extractor/Extractor;", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes23.dex */
public final class DolbyVisionExtractorsFactory implements ExtractorsFactory {
    public static final int $stable = 8;
    private final DefaultExtractorsFactory delegate;
    private final boolean injectHdrColorInfo;
    private final boolean stripDolbyVision;

    public DolbyVisionExtractorsFactory(boolean stripDolbyVision, boolean injectHdrColorInfo) {
        this.stripDolbyVision = stripDolbyVision;
        this.injectHdrColorInfo = injectHdrColorInfo;
        this.delegate = new DefaultExtractorsFactory();
    }

    public /* synthetic */ DolbyVisionExtractorsFactory(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? false : z2);
    }

    public final boolean shouldApply$app_mobileDebug(String mimeType) {
        return supports(mimeType);
    }

    private final boolean supports(String mimeType) {
        if (!this.stripDolbyVision) {
            return false;
        }
        if (mimeType == null) {
            return true;
        }
        String m = mimeType.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(m, "toLowerCase(...)");
        return (StringsKt.startsWith$default(m, "application/vnd.apple.mpegurl", false, 2, (Object) null) || StringsKt.startsWith$default(m, "application/x-mpegurl", false, 2, (Object) null) || StringsKt.startsWith$default(m, MimeTypes.APPLICATION_MPD, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) m, (CharSequence) "mpegurl", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) m, (CharSequence) "dash+xml", false, 2, (Object) null)) ? false : true;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors() {
        Extractor[] createExtractors = this.delegate.createExtractors();
        Intrinsics.checkNotNullExpressionValue(createExtractors, "createExtractors(...)");
        return wrap(createExtractors, this.stripDolbyVision);
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public Extractor[] createExtractors(Uri uri, Map<String, ? extends List<String>> responseHeaders) {
        String str;
        String substringBefore$default;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        List<String> list = responseHeaders.get(HttpHeaders.CONTENT_TYPE);
        String str2 = null;
        if (list != null && (str = (String) CollectionsKt.firstOrNull((List) list)) != null && (substringBefore$default = StringsKt.substringBefore$default(str, ';', (String) null, 2, (Object) null)) != null) {
            str2 = StringsKt.trim((CharSequence) substringBefore$default).toString();
        }
        String mimeType = str2;
        Extractor[] extractors = this.delegate.createExtractors(uri, responseHeaders);
        Intrinsics.checkNotNullExpressionValue(extractors, "createExtractors(...)");
        return wrap(extractors, supports(mimeType));
    }

    private final Extractor[] wrap(Extractor[] extractors, boolean enabled) {
        if (!enabled) {
            return extractors;
        }
        int length = extractors.length;
        Extractor[] extractorArr = new Extractor[length];
        for (int i = 0; i < length; i++) {
            extractorArr[i] = new DolbyVisionRewritingExtractor(extractors[i], true, this.injectHdrColorInfo);
        }
        return extractorArr;
    }
}
