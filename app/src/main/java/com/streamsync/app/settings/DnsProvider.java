package com.streamsync.app.settings;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CLOUDFLARE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: DnsProvider.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B1\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nj\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u001a"}, d2 = {"Lcom/streamsync/app/settings/DnsProvider;", "", "label", "", "dohHost", "privateDnsHost", "note", "dohJsonPath", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDohHost", "()Ljava/lang/String;", "getDohJsonPath", "getLabel", "getNote", "getPrivateDnsHost", "dohJsonUrlBuilder", "Lokhttp3/HttpUrl$Builder;", "hostname", "SYSTEM", "GOOGLE", "CLOUDFLARE", "MULLVAD", "QUAD9", "ADGUARD", "OPENDNS", "Companion", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class DnsProvider {
    public static final DnsProvider ADGUARD;
    public static final DnsProvider CLOUDFLARE;
    public static final DnsProvider MULLVAD;
    public static final DnsProvider OPENDNS;
    public static final DnsProvider QUAD9;
    private final String dohHost;
    private final String dohJsonPath;
    private final String label;
    private final String note;
    private final String privateDnsHost;
    public static final DnsProvider SYSTEM = new DnsProvider("SYSTEM", 0, "Theo hệ thống", "", "", "Dùng đúng DNS của máy. Không mã hoá.", null, 16, null);
    public static final DnsProvider GOOGLE = new DnsProvider("GOOGLE", 1, "Google", "dns.google", "dns.google", "Nhanh, phổ biến nhất.", "/resolve");
    private static final /* synthetic */ DnsProvider[] $VALUES = $values();
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    private static final /* synthetic */ DnsProvider[] $values() {
        return new DnsProvider[]{SYSTEM, GOOGLE, CLOUDFLARE, MULLVAD, QUAD9, ADGUARD, OPENDNS};
    }

    public static EnumEntries<DnsProvider> getEntries() {
        return $ENTRIES;
    }

    public static DnsProvider valueOf(String str) {
        return (DnsProvider) Enum.valueOf(DnsProvider.class, str);
    }

    public static DnsProvider[] values() {
        return (DnsProvider[]) $VALUES.clone();
    }

    private DnsProvider(String $enum$name, int $enum$ordinal, String label, String dohHost, String privateDnsHost, String note, String dohJsonPath) {
        this.label = label;
        this.dohHost = dohHost;
        this.privateDnsHost = privateDnsHost;
        this.note = note;
        this.dohJsonPath = dohJsonPath;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ DnsProvider(java.lang.String r10, int r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 16
            if (r0 == 0) goto L8
            java.lang.String r0 = "/dns-query"
            r8 = r0
            goto La
        L8:
            r8 = r16
        La:
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.settings.DnsProvider.<init>(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getDohHost() {
        return this.dohHost;
    }

    public final String getPrivateDnsHost() {
        return this.privateDnsHost;
    }

    public final String getNote() {
        return this.note;
    }

    public final String getDohJsonPath() {
        return this.dohJsonPath;
    }

    static {
        int i = 16;
        DefaultConstructorMarker defaultConstructorMarker = null;
        String str = null;
        CLOUDFLARE = new DnsProvider("CLOUDFLARE", 2, "Cloudflare", "cloudflare-dns.com", "one.one.one.one", "Ưu tiên chống ghi lại, có bộ lọc phần mềm độc hại nếu bật.", str, i, defaultConstructorMarker);
        int i2 = 16;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        String str2 = null;
        MULLVAD = new DnsProvider("MULLVAD", 3, "Mullvad", "dns.mullvad.net", "dns.mullvad.net", "Không ghi nhận truy vấn, không chặn nội dung.", str2, i2, defaultConstructorMarker2);
        QUAD9 = new DnsProvider("QUAD9", 4, "Quad9", "dns.quad9.net", "dns.quad9.net", "Lọc nội dung độc hại, ưu tiên quyền riêng tư.", str, i, defaultConstructorMarker);
        ADGUARD = new DnsProvider("ADGUARD", 5, "AdGuard", "dns.adguard-dns.com", "dns.adguard.com", "Chặn quảng cáo và theo dõi.", str2, i2, defaultConstructorMarker2);
        OPENDNS = new DnsProvider("OPENDNS", 6, "OpenDNS", "doh.opendns.com", "dns.opendns.com", "Bộ lọc theo danh mục, lâu đời.", str, i, defaultConstructorMarker);
    }

    public final HttpUrl.Builder dohJsonUrlBuilder(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return HttpUrl.INSTANCE.get("https://" + this.dohHost + this.dohJsonPath).newBuilder();
    }

    /* compiled from: DnsProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/streamsync/app/settings/DnsProvider$Companion;", "", "()V", "fromNameOrDefault", "Lcom/streamsync/app/settings/DnsProvider;", HintConstants.AUTOFILL_HINT_NAME, "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DnsProvider fromNameOrDefault(String name) {
            Object m6717constructorimpl;
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                m6717constructorimpl = Result.m6717constructorimpl(DnsProvider.valueOf(name == null ? "" : name));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m6717constructorimpl = Result.m6717constructorimpl(ResultKt.createFailure(th));
            }
            DnsProvider dnsProvider = DnsProvider.SYSTEM;
            if (Result.m6723isFailureimpl(m6717constructorimpl)) {
                m6717constructorimpl = dnsProvider;
            }
            return (DnsProvider) m6717constructorimpl;
        }
    }
}
