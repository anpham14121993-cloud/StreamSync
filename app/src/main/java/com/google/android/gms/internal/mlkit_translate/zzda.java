package com.google.android.gms.internal.mlkit_translate;

import androidx.core.view.MotionEventCompat;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import kotlin.text.Typography;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzda implements Closeable {
    private final Reader zzb;
    private long zzi;
    private int zzj;
    private int zzl;
    private String[] zzm;
    private int[] zzn;
    private zzcj zzc = zzcj.LEGACY_STRICT;
    private final char[] zzd = new char[1024];
    private int zze = 0;
    private int zzf = 0;
    private int zzg = 0;
    private int zzh = 0;
    int zza = 0;
    private int[] zzk = new int[32];

    static {
        zzcl.zza = new zzcz();
    }

    public zzda(Reader reader) {
        int[] iArr = this.zzk;
        this.zzl = 1;
        iArr[0] = 6;
        this.zzm = new String[32];
        this.zzn = new int[32];
        this.zzb = (Reader) Objects.requireNonNull(reader, "in == null");
    }

    private final int zzo(boolean z) throws IOException {
        int i;
        int i2 = this.zze;
        int i3 = this.zzf;
        while (true) {
            if (i2 == i3) {
                this.zze = i2;
                if (!zzw(1)) {
                    if (z) {
                        throw new EOFException("End of input".concat(zzc()));
                    }
                    return -1;
                }
                i2 = this.zze;
                i3 = this.zzf;
            }
            char[] cArr = this.zzd;
            int i4 = i2 + 1;
            char c = cArr[i2];
            if (c == '\n') {
                this.zzg++;
                this.zzh = i4;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.zze = i4;
                    if (i4 == i3) {
                        this.zze = i4 - 1;
                        boolean zzw = zzw(2);
                        this.zze++;
                        if (!zzw) {
                            return 47;
                        }
                    }
                    zzt();
                    int i5 = this.zze;
                    switch (cArr[i5]) {
                        case '*':
                            this.zze = i5 + 1;
                            while (true) {
                                if (this.zze + 2 > this.zzf && !zzw(2)) {
                                    throw zzp("Unterminated comment");
                                }
                                char[] cArr2 = this.zzd;
                                int i6 = this.zze;
                                if (cArr2[i6] != '\n') {
                                    while (i < 2) {
                                        i = this.zzd[this.zze + i] == "*/".charAt(i) ? i + 1 : 0;
                                    }
                                    i2 = 2 + this.zze;
                                    i3 = this.zzf;
                                    break;
                                } else {
                                    this.zzg++;
                                    this.zzh = i6 + 1;
                                }
                                this.zze++;
                            }
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                            this.zze = i5 + 1;
                            zzv();
                            i2 = this.zze;
                            i3 = this.zzf;
                            break;
                        default:
                            return 47;
                    }
                } else {
                    if (c != '#') {
                        this.zze = i4;
                        return c;
                    }
                    this.zze = i4;
                    zzt();
                    zzv();
                    i2 = this.zze;
                    i3 = this.zzf;
                }
            }
            i2 = i4;
        }
    }

    private final zzdd zzp(String str) throws zzdd {
        throw new zzdd(str + zzc() + "\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#malformed-json");
    }

    private final IllegalStateException zzq(String str) throws IOException {
        int zzn = zzn();
        String zza = zzdb.zza(zzn());
        String zzc = zzc();
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        sb.append(str);
        sb.append(" but was ");
        sb.append(zza);
        sb.append(zzc);
        sb.append("\nSee ");
        sb.append("https://github.com/google/gson/blob/main/Troubleshooting.md#".concat(zzn == 9 ? "adapter-not-null-safe" : "unexpected-json-structure"));
        return new IllegalStateException(sb.toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00dc, code lost:
    
        throw zzp("Malformed Unicode escape \\u".concat(new java.lang.String(r5, r10.zze, 4)));
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x007a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:79:0x010f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String zzr(char r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzda.zzr(char):java.lang.String");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:30:0x000e. Please report as an issue. */
    private final String zzs() throws IOException {
        String sb;
        int i = 0;
        StringBuilder sb2 = null;
        int i2 = 0;
        while (true) {
            int i3 = this.zze + i2;
            if (i3 < this.zzf) {
                switch (this.zzd[i3]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                    case ';':
                    case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    case '\\':
                        zzt();
                        break;
                    default:
                        i2++;
                }
            } else if (i2 >= 1024) {
                if (sb2 == null) {
                    sb2 = new StringBuilder(Math.max(i2, 16));
                }
                sb2.append(this.zzd, this.zze, i2);
                this.zze += i2;
                if (zzw(1)) {
                    i2 = 0;
                }
            } else if (zzw(i2 + 1)) {
            }
        }
        i = i2;
        if (sb2 == null) {
            sb = new String(this.zzd, this.zze, i);
        } else {
            sb2.append(this.zzd, this.zze, i);
            sb = sb2.toString();
        }
        this.zze += i;
        return sb;
    }

    private final void zzt() throws zzdd {
        if (this.zzc != zzcj.LENIENT) {
            throw zzp("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        }
    }

    private final void zzu(int i) throws zzdd {
        int i2 = this.zzl;
        if (i2 - 1 >= 1280) {
            throw new zzdd("Nesting limit 1280 reached" + zzc());
        }
        int[] iArr = this.zzk;
        if (i2 == iArr.length) {
            int i3 = i2 + i2;
            this.zzk = Arrays.copyOf(iArr, i3);
            this.zzn = Arrays.copyOf(this.zzn, i3);
            this.zzm = (String[]) Arrays.copyOf(this.zzm, i3);
        }
        int[] iArr2 = this.zzk;
        int i4 = this.zzl;
        this.zzl = i4 + 1;
        iArr2[i4] = i;
    }

    private final void zzv() throws IOException {
        char c;
        do {
            if (this.zze >= this.zzf && !zzw(1)) {
                return;
            }
            char[] cArr = this.zzd;
            int i = this.zze;
            int i2 = i + 1;
            this.zze = i2;
            c = cArr[i];
            if (c == '\n') {
                this.zzg++;
                this.zzh = i2;
                return;
            }
        } while (c != '\r');
    }

    private final boolean zzw(int i) throws IOException {
        int i2;
        int i3 = this.zzh;
        int i4 = this.zze;
        this.zzh = i3 - i4;
        char[] cArr = this.zzd;
        int i5 = this.zzf;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.zzf = i6;
            System.arraycopy(cArr, i4, cArr, 0, i6);
        } else {
            this.zzf = 0;
        }
        this.zze = 0;
        do {
            Reader reader = this.zzb;
            int i7 = this.zzf;
            int read = reader.read(cArr, i7, 1024 - i7);
            if (read == -1) {
                return false;
            }
            i2 = this.zzf + read;
            this.zzf = i2;
            if (this.zzg == 0 && this.zzh == 0 && i2 > 0 && cArr[0] == 65279) {
                this.zze++;
                this.zzh = 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    private final boolean zzx(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            case '#':
            case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
            case ';':
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case '\\':
                zzt();
                return false;
            default:
                return true;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.zza = 0;
        this.zzk[0] = 8;
        this.zzl = 1;
        this.zzb.close();
    }

    public final String toString() {
        return String.valueOf(getClass().getSimpleName()).concat(zzc());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processFallThroughCases(RegionMaker.java:841)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:800)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:740)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:740)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:167:0x0022. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:216:0x02ad. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x00d6. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:62:0x01ca. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x029b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x019e  */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v31 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int zza() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_translate.zzda.zza():int");
    }

    public final zzcj zzb() {
        return this.zzc;
    }

    final String zzc() {
        int i = this.zzg + 1;
        int i2 = this.zze - this.zzh;
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        for (int i3 = 0; i3 < this.zzl; i3++) {
            int i4 = this.zzk[i3];
            switch (i4) {
                case 1:
                case 2:
                    int i5 = this.zzn[i3];
                    sb.append('[');
                    sb.append(i5);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String str = this.zzm[i3];
                    if (str != null) {
                        sb.append(str);
                        break;
                    } else {
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError("Unknown scope value: " + i4);
            }
        }
        return " at line " + i + " column " + (i2 + 1) + " path " + sb.toString();
    }

    public final String zzd() throws IOException {
        String zzr;
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 14) {
            zzr = zzs();
        } else if (i == 12) {
            zzr = zzr('\'');
        } else if (i == 13) {
            zzr = zzr(Typography.quote);
        } else {
            throw zzq("a name");
        }
        this.zza = 0;
        this.zzm[this.zzl - 1] = zzr;
        return zzr;
    }

    public final String zze() throws IOException {
        String str;
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 10) {
            str = zzs();
        } else if (i == 8) {
            str = zzr('\'');
        } else if (i == 9) {
            str = zzr(Typography.quote);
        } else if (i == 11) {
            str = null;
        } else if (i == 15) {
            str = Long.toString(this.zzi);
        } else {
            if (i != 16) {
                throw zzq("a string");
            }
            String str2 = new String(this.zzd, this.zze, this.zzj);
            this.zze += this.zzj;
            str = str2;
        }
        this.zza = 0;
        int[] iArr = this.zzn;
        int i2 = this.zzl - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void zzf() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 3) {
            zzu(1);
            this.zzn[this.zzl - 1] = 0;
            this.zza = 0;
            return;
        }
        throw zzq("BEGIN_ARRAY");
    }

    public final void zzg() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 1) {
            zzu(3);
            this.zza = 0;
            return;
        }
        throw zzq("BEGIN_OBJECT");
    }

    public final void zzh() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 4) {
            int i2 = this.zzl - 1;
            this.zzl = i2;
            int[] iArr = this.zzn;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw zzq("END_ARRAY");
    }

    public final void zzi() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 2) {
            int i2 = this.zzl - 1;
            this.zzl = i2;
            this.zzm[i2] = null;
            int[] iArr = this.zzn;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw zzq("END_OBJECT");
    }

    public final void zzj() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 7) {
            this.zza = 0;
            int[] iArr = this.zzn;
            int i2 = this.zzl - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw zzq("null");
    }

    public final void zzk(zzcj zzcjVar) {
        Objects.requireNonNull(zzcjVar);
        this.zzc = zzcjVar;
    }

    public final boolean zzl() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        return (i == 2 || i == 4 || i == 17) ? false : true;
    }

    public final boolean zzm() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 5) {
            this.zza = 0;
            int[] iArr = this.zzn;
            int i2 = this.zzl - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (i == 6) {
            this.zza = 0;
            int[] iArr2 = this.zzn;
            int i3 = this.zzl - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw zzq("a boolean");
    }

    public final int zzn() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        switch (i) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
                return 7;
            default:
                return 10;
        }
    }
}
