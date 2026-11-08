package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaceKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Connector;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: Color.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001d\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b\u001e\u0010\u000bJ\u0010\u0010\u001f\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b \u0010\u000bJ\u0010\u0010!\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b\"\u0010\u000bJ\u0010\u0010#\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b$\u0010\u000bJ\u0010\u0010%\u001a\u00020\u0010H\u0087\u0002¢\u0006\u0004\b&\u0010\u0013J\u001b\u0010'\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)J=\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u0007H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001a\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u000203HÖ\u0001¢\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109R\u001a\u0010\u0006\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u00108FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\t\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u000bR\u001a\u0010\u0017\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\t\u001a\u0004\b\u0019\u0010\u000bR\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001b\u0088\u0001\u0002\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006;"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "", "value", "Lkotlin/ULong;", "constructor-impl", "(J)J", "alpha", "", "getAlpha$annotations", "()V", "getAlpha-impl", "(J)F", "blue", "getBlue$annotations", "getBlue-impl", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getColorSpace$annotations", "getColorSpace-impl", "(J)Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "green", "getGreen$annotations", "getGreen-impl", "red", "getRed$annotations", "getRed-impl", "getValue-s-VKNKU", "()J", "J", "component1", "component1-impl", "component2", "component2-impl", "component3", "component3-impl", "component4", "component4-impl", "component5", "component5-impl", "convert", "convert-vNxB06k", "(JLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "copy", "copy-wmQWz5c", "(JFFFF)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@JvmInline
/* loaded from: classes24.dex */
public final class Color {
    private final long value;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Black = ColorKt.Color(4278190080L);
    private static final long DarkGray = ColorKt.Color(4282664004L);
    private static final long Gray = ColorKt.Color(4287137928L);
    private static final long LightGray = ColorKt.Color(4291611852L);
    private static final long White = ColorKt.Color(4294967295L);
    private static final long Red = ColorKt.Color(4294901760L);
    private static final long Green = ColorKt.Color(4278255360L);
    private static final long Blue = ColorKt.Color(4278190335L);
    private static final long Yellow = ColorKt.Color(4294967040L);
    private static final long Cyan = ColorKt.Color(4278255615L);
    private static final long Magenta = ColorKt.Color(4294902015L);
    private static final long Transparent = ColorKt.Color(0);
    private static final long Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.INSTANCE.getUnspecified$ui_graphics_release());

    /* renamed from: box-impl */
    public static final /* synthetic */ Color m3399boximpl(long j) {
        return new Color(j);
    }

    /* renamed from: constructor-impl */
    public static long m3405constructorimpl(long j) {
        return j;
    }

    /* renamed from: equals-impl */
    public static boolean m3409equalsimpl(long j, Object obj) {
        return (obj instanceof Color) && j == ((Color) obj).m3419unboximpl();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m3410equalsimpl0(long j, long j2) {
        return ULong.m6898equalsimpl0(j, j2);
    }

    public static /* synthetic */ void getAlpha$annotations() {
    }

    public static /* synthetic */ void getBlue$annotations() {
    }

    public static /* synthetic */ void getColorSpace$annotations() {
    }

    public static /* synthetic */ void getGreen$annotations() {
    }

    public static /* synthetic */ void getRed$annotations() {
    }

    /* renamed from: hashCode-impl */
    public static int m3416hashCodeimpl(long j) {
        return ULong.m6903hashCodeimpl(j);
    }

    public boolean equals(Object obj) {
        return m3409equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m3416hashCodeimpl(this.value);
    }

    /* renamed from: unbox-impl */
    public final /* synthetic */ long m3419unboximpl() {
        return this.value;
    }

    private /* synthetic */ Color(long value) {
        this.value = value;
    }

    /* renamed from: getValue-s-VKNKU, reason: from getter */
    public final long getValue() {
        return this.value;
    }

    /* renamed from: getColorSpace-impl */
    public static final ColorSpace m3413getColorSpaceimpl(long arg0) {
        ColorSpaces this_$iv = ColorSpaces.INSTANCE;
        int id$iv = (int) ULong.m6891constructorimpl(63 & arg0);
        return this_$iv.getColorSpacesArray$ui_graphics_release()[id$iv];
    }

    /* renamed from: convert-vNxB06k */
    public static final long m3406convertvNxB06k(long arg0, ColorSpace colorSpace) {
        ColorSpace thisColorSpace = m3413getColorSpaceimpl(arg0);
        if (Intrinsics.areEqual(colorSpace, thisColorSpace)) {
            return arg0;
        }
        Connector connector = ColorSpaceKt.m3828connectYBCOT_4$default(thisColorSpace, colorSpace, 0, 2, null);
        return connector.mo3830transformToColorwmQWz5c$ui_graphics_release(m3415getRedimpl(arg0), m3414getGreenimpl(arg0), m3412getBlueimpl(arg0), m3411getAlphaimpl(arg0));
    }

    /* renamed from: getRed-impl */
    public static final float m3415getRedimpl(long arg0) {
        if (ULong.m6891constructorimpl(63 & arg0) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 48) & 255))) / 255.0f;
        }
        return Float16.m3535toFloatimpl(Float16.m3519constructorimpl((short) ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    /* renamed from: getGreen-impl */
    public static final float m3414getGreenimpl(long arg0) {
        if (ULong.m6891constructorimpl(63 & arg0) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 40) & 255))) / 255.0f;
        }
        return Float16.m3535toFloatimpl(Float16.m3519constructorimpl((short) ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    /* renamed from: getBlue-impl */
    public static final float m3412getBlueimpl(long arg0) {
        if (ULong.m6891constructorimpl(63 & arg0) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 32) & 255))) / 255.0f;
        }
        return Float16.m3535toFloatimpl(Float16.m3519constructorimpl((short) ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    /* renamed from: getAlpha-impl */
    public static final float m3411getAlphaimpl(long arg0) {
        if (ULong.m6891constructorimpl(63 & arg0) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 56) & 255))) / 255.0f;
        }
        return ((float) UnsignedKt.ulongToDouble(ULong.m6891constructorimpl(ULong.m6891constructorimpl(arg0 >>> 6) & 1023))) / 1023.0f;
    }

    /* renamed from: component1-impl */
    public static final float m3400component1impl(long arg0) {
        return m3415getRedimpl(arg0);
    }

    /* renamed from: component2-impl */
    public static final float m3401component2impl(long arg0) {
        return m3414getGreenimpl(arg0);
    }

    /* renamed from: component3-impl */
    public static final float m3402component3impl(long arg0) {
        return m3412getBlueimpl(arg0);
    }

    /* renamed from: component4-impl */
    public static final float m3403component4impl(long arg0) {
        return m3411getAlphaimpl(arg0);
    }

    /* renamed from: component5-impl */
    public static final ColorSpace m3404component5impl(long arg0) {
        return m3413getColorSpaceimpl(arg0);
    }

    /* renamed from: copy-wmQWz5c */
    public static final long m3407copywmQWz5c(long arg0, float alpha, float red, float green, float blue) {
        return ColorKt.Color(red, green, blue, alpha, m3413getColorSpaceimpl(arg0));
    }

    public String toString() {
        return m3417toStringimpl(this.value);
    }

    /* renamed from: toString-impl */
    public static String m3417toStringimpl(long arg0) {
        return "Color(" + m3415getRedimpl(arg0) + ", " + m3414getGreenimpl(arg0) + ", " + m3412getBlueimpl(arg0) + ", " + m3411getAlphaimpl(arg0) + ", " + m3413getColorSpaceimpl(arg0).getName() + ')';
    }

    /* compiled from: Color.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J?\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0002\u00103\u001a\u000204ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106J(\u00107\u001a\u00020/2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020/H\u0002J?\u0010=\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010>\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0002\u00103\u001a\u000204ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u00106J(\u0010@\u001a\u00020/2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020/2\u0006\u0010A\u001a\u00020/H\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007R$\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u0007R$\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R$\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0007R$\u0010\u0015\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0007R$\u0010\u0018\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u0007R$\u0010\u001b\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001c\u0010\u0002\u001a\u0004\b\u001d\u0010\u0007R$\u0010\u001e\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\u0007R$\u0010!\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\"\u0010\u0002\u001a\u0004\b#\u0010\u0007R$\u0010$\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b%\u0010\u0002\u001a\u0004\b&\u0010\u0007R$\u0010'\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b(\u0010\u0002\u001a\u0004\b)\u0010\u0007R$\u0010*\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b+\u0010\u0002\u001a\u0004\b,\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/compose/ui/graphics/Color$Companion;", "", "()V", "Black", "Landroidx/compose/ui/graphics/Color;", "getBlack-0d7_KjU$annotations", "getBlack-0d7_KjU", "()J", "J", "Blue", "getBlue-0d7_KjU$annotations", "getBlue-0d7_KjU", "Cyan", "getCyan-0d7_KjU$annotations", "getCyan-0d7_KjU", "DarkGray", "getDarkGray-0d7_KjU$annotations", "getDarkGray-0d7_KjU", "Gray", "getGray-0d7_KjU$annotations", "getGray-0d7_KjU", "Green", "getGreen-0d7_KjU$annotations", "getGreen-0d7_KjU", "LightGray", "getLightGray-0d7_KjU$annotations", "getLightGray-0d7_KjU", "Magenta", "getMagenta-0d7_KjU$annotations", "getMagenta-0d7_KjU", "Red", "getRed-0d7_KjU$annotations", "getRed-0d7_KjU", "Transparent", "getTransparent-0d7_KjU$annotations", "getTransparent-0d7_KjU", "Unspecified", "getUnspecified-0d7_KjU$annotations", "getUnspecified-0d7_KjU", "White", "getWhite-0d7_KjU$annotations", "getWhite-0d7_KjU", "Yellow", "getYellow-0d7_KjU$annotations", "getYellow-0d7_KjU", "hsl", "hue", "", "saturation", "lightness", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "hsl-JlNiLsg", "(FFFFLandroidx/compose/ui/graphics/colorspace/Rgb;)J", "hslToRgbComponent", "n", "", CmcdData.Factory.STREAMING_FORMAT_HLS, CmcdData.Factory.STREAMING_FORMAT_SS, CmcdData.Factory.STREAM_TYPE_LIVE, "hsv", "value", "hsv-JlNiLsg", "hsvToRgbComponent", "v", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getBlack-0d7_KjU$annotations */
        public static /* synthetic */ void m3420getBlack0d7_KjU$annotations() {
        }

        /* renamed from: getBlue-0d7_KjU$annotations */
        public static /* synthetic */ void m3421getBlue0d7_KjU$annotations() {
        }

        /* renamed from: getCyan-0d7_KjU$annotations */
        public static /* synthetic */ void m3422getCyan0d7_KjU$annotations() {
        }

        /* renamed from: getDarkGray-0d7_KjU$annotations */
        public static /* synthetic */ void m3423getDarkGray0d7_KjU$annotations() {
        }

        /* renamed from: getGray-0d7_KjU$annotations */
        public static /* synthetic */ void m3424getGray0d7_KjU$annotations() {
        }

        /* renamed from: getGreen-0d7_KjU$annotations */
        public static /* synthetic */ void m3425getGreen0d7_KjU$annotations() {
        }

        /* renamed from: getLightGray-0d7_KjU$annotations */
        public static /* synthetic */ void m3426getLightGray0d7_KjU$annotations() {
        }

        /* renamed from: getMagenta-0d7_KjU$annotations */
        public static /* synthetic */ void m3427getMagenta0d7_KjU$annotations() {
        }

        /* renamed from: getRed-0d7_KjU$annotations */
        public static /* synthetic */ void m3428getRed0d7_KjU$annotations() {
        }

        /* renamed from: getTransparent-0d7_KjU$annotations */
        public static /* synthetic */ void m3429getTransparent0d7_KjU$annotations() {
        }

        /* renamed from: getUnspecified-0d7_KjU$annotations */
        public static /* synthetic */ void m3430getUnspecified0d7_KjU$annotations() {
        }

        /* renamed from: getWhite-0d7_KjU$annotations */
        public static /* synthetic */ void m3431getWhite0d7_KjU$annotations() {
        }

        /* renamed from: getYellow-0d7_KjU$annotations */
        public static /* synthetic */ void m3432getYellow0d7_KjU$annotations() {
        }

        private Companion() {
        }

        /* renamed from: getBlack-0d7_KjU */
        public final long m3435getBlack0d7_KjU() {
            return Color.Black;
        }

        /* renamed from: getDarkGray-0d7_KjU */
        public final long m3438getDarkGray0d7_KjU() {
            return Color.DarkGray;
        }

        /* renamed from: getGray-0d7_KjU */
        public final long m3439getGray0d7_KjU() {
            return Color.Gray;
        }

        /* renamed from: getLightGray-0d7_KjU */
        public final long m3441getLightGray0d7_KjU() {
            return Color.LightGray;
        }

        /* renamed from: getWhite-0d7_KjU */
        public final long m3446getWhite0d7_KjU() {
            return Color.White;
        }

        /* renamed from: getRed-0d7_KjU */
        public final long m3443getRed0d7_KjU() {
            return Color.Red;
        }

        /* renamed from: getGreen-0d7_KjU */
        public final long m3440getGreen0d7_KjU() {
            return Color.Green;
        }

        /* renamed from: getBlue-0d7_KjU */
        public final long m3436getBlue0d7_KjU() {
            return Color.Blue;
        }

        /* renamed from: getYellow-0d7_KjU */
        public final long m3447getYellow0d7_KjU() {
            return Color.Yellow;
        }

        /* renamed from: getCyan-0d7_KjU */
        public final long m3437getCyan0d7_KjU() {
            return Color.Cyan;
        }

        /* renamed from: getMagenta-0d7_KjU */
        public final long m3442getMagenta0d7_KjU() {
            return Color.Magenta;
        }

        /* renamed from: getTransparent-0d7_KjU */
        public final long m3444getTransparent0d7_KjU() {
            return Color.Transparent;
        }

        /* renamed from: getUnspecified-0d7_KjU */
        public final long m3445getUnspecified0d7_KjU() {
            return Color.Unspecified;
        }

        /* renamed from: hsv-JlNiLsg$default */
        public static /* synthetic */ long m3434hsvJlNiLsg$default(Companion companion, float f, float f2, float f3, float f4, Rgb rgb, int i, Object obj) {
            float f5;
            Rgb rgb2;
            if ((i & 8) == 0) {
                f5 = f4;
            } else {
                f5 = 1.0f;
            }
            if ((i & 16) == 0) {
                rgb2 = rgb;
            } else {
                rgb2 = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m3449hsvJlNiLsg(f, f2, f3, f5, rgb2);
        }

        /* renamed from: hsv-JlNiLsg */
        public final long m3449hsvJlNiLsg(float hue, float saturation, float value, float alpha, Rgb colorSpace) {
            boolean z = false;
            if (0.0f <= hue && hue <= 360.0f) {
                if (0.0f <= saturation && saturation <= 1.0f) {
                    if (0.0f <= value && value <= 1.0f) {
                        z = true;
                    }
                }
            }
            if (!z) {
                throw new IllegalArgumentException(("HSV (" + hue + ", " + saturation + ", " + value + ") must be in range (0..360, 0..1, 0..1)").toString());
            }
            float red = hsvToRgbComponent(5, hue, saturation, value);
            float green = hsvToRgbComponent(3, hue, saturation, value);
            float blue = hsvToRgbComponent(1, hue, saturation, value);
            return ColorKt.Color(red, green, blue, alpha, colorSpace);
        }

        private final float hsvToRgbComponent(int n, float r6, float r7, float v) {
            float k = (n + (r6 / 60.0f)) % 6.0f;
            return v - ((v * r7) * Math.max(0.0f, Math.min(k, Math.min(4 - k, 1.0f))));
        }

        /* renamed from: hsl-JlNiLsg$default */
        public static /* synthetic */ long m3433hslJlNiLsg$default(Companion companion, float f, float f2, float f3, float f4, Rgb rgb, int i, Object obj) {
            float f5;
            Rgb rgb2;
            if ((i & 8) == 0) {
                f5 = f4;
            } else {
                f5 = 1.0f;
            }
            if ((i & 16) == 0) {
                rgb2 = rgb;
            } else {
                rgb2 = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m3448hslJlNiLsg(f, f2, f3, f5, rgb2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x002c, code lost:
        
            if ((0.0f <= r8 && r8 <= 1.0f) != false) goto L55;
         */
        /* renamed from: hsl-JlNiLsg */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final long m3448hslJlNiLsg(float r6, float r7, float r8, float r9, androidx.compose.ui.graphics.colorspace.Rgb r10) {
            /*
                r5 = this;
                r0 = 0
                int r1 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                r2 = 1
                r3 = 0
                if (r1 > 0) goto Lf
                r1 = 1135869952(0x43b40000, float:360.0)
                int r1 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r1 > 0) goto Lf
                r1 = r2
                goto L10
            Lf:
                r1 = r3
            L10:
                if (r1 == 0) goto L2f
                int r1 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
                r4 = 1065353216(0x3f800000, float:1.0)
                if (r1 > 0) goto L1e
                int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
                if (r1 > 0) goto L1e
                r1 = r2
                goto L1f
            L1e:
                r1 = r3
            L1f:
                if (r1 == 0) goto L2f
                int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
                if (r0 > 0) goto L2b
                int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
                if (r0 > 0) goto L2b
                r0 = r2
                goto L2c
            L2b:
                r0 = r3
            L2c:
                if (r0 == 0) goto L2f
                goto L30
            L2f:
                r2 = r3
            L30:
                if (r2 == 0) goto L49
                float r0 = r5.hslToRgbComponent(r3, r6, r7, r8)
                r1 = 8
                float r1 = r5.hslToRgbComponent(r1, r6, r7, r8)
                r2 = 4
                float r2 = r5.hslToRgbComponent(r2, r6, r7, r8)
                r3 = r10
                androidx.compose.ui.graphics.colorspace.ColorSpace r3 = (androidx.compose.ui.graphics.colorspace.ColorSpace) r3
                long r3 = androidx.compose.ui.graphics.ColorKt.Color(r0, r1, r2, r9, r3)
                return r3
            L49:
                r0 = 0
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "HSL ("
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r6)
                java.lang.String r2 = ", "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r7)
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r8)
                java.lang.String r2 = ") must be in range (0..360, 0..1, 0..1)"
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r0 = r1.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.Color.Companion.m3448hslJlNiLsg(float, float, float, float, androidx.compose.ui.graphics.colorspace.Rgb):long");
        }

        private final float hslToRgbComponent(int n, float r7, float r8, float r9) {
            float k = (n + (r7 / 30.0f)) % 12.0f;
            float a = Math.min(r9, 1.0f - r9) * r8;
            return r9 - (Math.max(-1.0f, Math.min(k - 3, Math.min(9 - k, 1.0f))) * a);
        }
    }
}
