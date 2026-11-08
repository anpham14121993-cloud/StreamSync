package com.google.android.gms.common.server.response;

import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
import okio.internal.Buffer;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes24.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaa = {'u', 'l', 'l'};
    private static final char[] zab = {'r', 'u', 'e'};
    private static final char[] zac = {'r', 'u', 'e', Typography.quote};
    private static final char[] zad = {'a', 'l', 's', 'e'};
    private static final char[] zae = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zaf = {'\n'};
    private static final zai zag = new zaa();
    private static final zai zah = new zab();
    private static final zai zai = new zac();
    private static final zai zaj = new zad();
    private static final zai zak = new zae();
    private static final zai zal = new zaf();
    private static final zai zam = new zag();
    private static final zai zan = new zah();
    private final char[] zao = new char[1];
    private final char[] zap = new char[32];
    private final char[] zaq = new char[1024];
    private final StringBuilder zar = new StringBuilder(32);
    private final StringBuilder zas = new StringBuilder(1024);
    private final Stack zat = new Stack();

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    /* loaded from: classes24.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super("Error instantiating inner object", th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    private static final String zaA(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                int i = 0;
                while (i < read) {
                    char c = cArr[i];
                    if (!Character.isISOControl(c) || (cArr2 != null && cArr2[0] == c)) {
                        int i2 = i + 1;
                        if (c == '\"') {
                            if (!z) {
                                sb.append(cArr, 0, i);
                                bufferedReader.reset();
                                bufferedReader.skip(i2);
                                return z2 ? JsonUtils.unescapeString(sb.toString()) : sb.toString();
                            }
                        } else if (c == '\\') {
                            z = !z;
                            z2 = true;
                            i = i2;
                        }
                        z = false;
                        i = i2;
                    }
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
        throw new ParseException("Unexpected control character while reading string");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0021, code lost:
    
        return r3.zao[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0008, code lost:
    
        if (r4.read(r3.zao) != (-1)) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
    
        if (java.lang.Character.isWhitespace(r3.zao[0]) == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x001a, code lost:
    
        if (r4.read(r3.zao) != (-1)) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final char zai(java.io.BufferedReader r4) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r3 = this;
            char[] r0 = r3.zao
            int r0 = r4.read(r0)
            r1 = 0
            r2 = -1
            if (r0 == r2) goto L22
        La:
            char[] r0 = r3.zao
            char r0 = r0[r1]
            boolean r0 = java.lang.Character.isWhitespace(r0)
            if (r0 == 0) goto L1d
            char[] r0 = r3.zao
            int r0 = r4.read(r0)
            if (r0 != r2) goto La
            goto L22
        L1d:
            char[] r4 = r3.zao
            char r4 = r4[r1]
            return r4
        L22:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zai(java.io.BufferedReader):char");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zak(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zal(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        int i3;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0;
        }
        char[] cArr = this.zaq;
        if (zam2 > 0) {
            char c = cArr[0];
            int i4 = c == '-' ? Integer.MIN_VALUE : C.RATE_UNSET_INT;
            if (c == '-') {
                i = 1;
            } else {
                i = 0;
            }
            if (i < zam2) {
                i3 = i + 1;
                int digit = Character.digit(cArr[i], 10);
                if (digit >= 0) {
                    i2 = -digit;
                } else {
                    throw new ParseException("Unexpected non-digit character");
                }
            } else {
                i2 = 0;
                i3 = i;
            }
            while (i3 < zam2) {
                int i5 = i3 + 1;
                int digit2 = Character.digit(cArr[i3], 10);
                if (digit2 >= 0) {
                    if (i2 >= -214748364) {
                        int i6 = i2 * 10;
                        if (i6 >= i4 + digit2) {
                            i2 = i6 - digit2;
                            i3 = i5;
                        } else {
                            throw new ParseException("Number too large");
                        }
                    } else {
                        throw new ParseException("Number too large");
                    }
                } else {
                    throw new ParseException("Unexpected non-digit character");
                }
            }
            if (i == 0) {
                return -i2;
            }
            if (i3 > 1) {
                return i2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    @ResultIgnorabilityUnspecified
    private final int zam(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zai2 = zai(bufferedReader);
        if (zai2 != 0) {
            if (zai2 == ',') {
                throw new ParseException("Missing value");
            }
            if (zai2 == 'n') {
                zax(bufferedReader, zaa);
                return 0;
            }
            bufferedReader.mark(1024);
            if (zai2 == '\"') {
                i = 0;
                boolean z = false;
                while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        throw new ParseException("Unexpected control character while reading string");
                    }
                    int i2 = i + 1;
                    if (c != '\"') {
                        z = c == '\\' ? !z : false;
                    } else {
                        if (!z) {
                            bufferedReader.reset();
                            bufferedReader.skip(i2);
                            return i;
                        }
                        z = false;
                    }
                    i = i2;
                }
            } else {
                cArr[0] = zai2;
                i = 1;
                while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                    char c2 = cArr[i];
                    if (c2 == '}' || c2 == ',' || Character.isWhitespace(c2) || cArr[i] == ']') {
                        bufferedReader.reset();
                        bufferedReader.skip(i - 1);
                        cArr[i] = 0;
                        return i;
                    }
                    i++;
                }
            }
            if (i == 1024) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
        throw new ParseException("Unexpected EOF");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zan(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        int i;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaq;
        if (zam2 <= 0) {
            throw new ParseException("No number to parse");
        }
        char c = cArr[0];
        long j2 = c == '-' ? Long.MIN_VALUE : C.TIME_UNSET;
        int i2 = c == '-' ? 1 : 0;
        if (i2 < zam2) {
            i = i2 + 1;
            int digit = Character.digit(cArr[i2], 10);
            if (digit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j = -digit;
        } else {
            j = 0;
            i = i2;
        }
        while (i < zam2) {
            int i3 = i + 1;
            int digit2 = Character.digit(cArr[i], 10);
            if (digit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j < Buffer.OVERFLOW_ZONE) {
                throw new ParseException("Number too large");
            }
            long j3 = j * 10;
            int i4 = zam2;
            long j4 = digit2;
            if (j3 < j2 + j4) {
                throw new ParseException("Number too large");
            }
            j = j3 - j4;
            zam2 = i4;
            i = i3;
        }
        if (i2 == 0) {
            return -j;
        }
        if (i > 1) {
            return j;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zao(BufferedReader bufferedReader) throws ParseException, IOException {
        return zap(bufferedReader, this.zap, this.zar, null);
    }

    private final String zap(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        switch (zai(bufferedReader)) {
            case '\"':
                return zaA(bufferedReader, cArr, sb, cArr2);
            case 'n':
                zax(bufferedReader, zaa);
                return null;
            default:
                throw new ParseException("Expected string");
        }
    }

    @ResultIgnorabilityUnspecified
    private final String zaq(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zat.push(2);
        char zai2 = zai(bufferedReader);
        switch (zai2) {
            case '\"':
                this.zat.push(3);
                String zaA = zaA(bufferedReader, this.zap, this.zar, null);
                zaw(3);
                if (zai(bufferedReader) != ':') {
                    throw new ParseException("Expected key/value separator");
                }
                return zaA;
            case ']':
                zaw(2);
                zaw(1);
                zaw(5);
                return null;
            case '}':
                zaw(2);
                return null;
            default:
                throw new ParseException("Unexpected token: " + zai2);
        }
    }

    private final String zar(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        int i = 1;
        switch (zai(bufferedReader)) {
            case '\"':
                if (bufferedReader.read(this.zao) != -1) {
                    char c = this.zao[0];
                    boolean z = false;
                    do {
                        if (c == '\"') {
                            if (!z) {
                                break;
                            } else {
                                c = '\"';
                                z = true;
                            }
                        }
                        z = c == '\\' ? !z : false;
                        if (bufferedReader.read(this.zao) != -1) {
                            c = this.zao[0];
                        } else {
                            throw new ParseException("Unexpected EOF while parsing string");
                        }
                    } while (!Character.isISOControl(c));
                    throw new ParseException("Unexpected control character while reading string");
                }
                throw new ParseException("Unexpected EOF while parsing string");
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                throw new ParseException("Missing value");
            case '[':
                this.zat.push(5);
                bufferedReader.mark(32);
                if (zai(bufferedReader) == ']') {
                    zaw(5);
                    break;
                } else {
                    bufferedReader.reset();
                    boolean z2 = false;
                    boolean z3 = false;
                    while (i > 0) {
                        char zai2 = zai(bufferedReader);
                        if (zai2 == 0) {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                        if (Character.isISOControl(zai2)) {
                            throw new ParseException("Unexpected control character while reading array");
                        }
                        if (zai2 == '\"') {
                            if (!z3) {
                                z2 = !z2;
                            }
                            zai2 = '\"';
                        }
                        if (zai2 == '[') {
                            if (!z2) {
                                i++;
                            }
                            zai2 = '[';
                        }
                        if (zai2 == ']' && !z2) {
                            i--;
                        }
                        z3 = (zai2 == '\\' && z2) ? !z3 : false;
                    }
                    zaw(5);
                    break;
                }
                break;
            case '{':
                this.zat.push(1);
                bufferedReader.mark(32);
                char zai3 = zai(bufferedReader);
                if (zai3 == '}') {
                    zaw(1);
                    break;
                } else if (zai3 == '\"') {
                    bufferedReader.reset();
                    zaq(bufferedReader);
                    do {
                    } while (zar(bufferedReader) != null);
                    zaw(1);
                    break;
                } else {
                    throw new ParseException("Unexpected token " + zai3);
                }
            default:
                bufferedReader.reset();
                zam(bufferedReader, this.zaq);
                break;
        }
        char zai4 = zai(bufferedReader);
        switch (zai4) {
            case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                zaw(2);
                return zaq(bufferedReader);
            case '}':
                zaw(2);
                return null;
            default:
                throw new ParseException("Unexpected token " + zai4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zas(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zat(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaq, 0, zam2));
    }

    private final ArrayList zau(BufferedReader bufferedReader, zai zaiVar) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return null;
        }
        if (zai2 != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zat.push(5);
        ArrayList arrayList = new ArrayList();
        while (true) {
            bufferedReader.mark(1024);
            switch (zai(bufferedReader)) {
                case 0:
                    throw new ParseException("Unexpected EOF");
                case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                    break;
                case ']':
                    zaw(5);
                    return arrayList;
                default:
                    bufferedReader.reset();
                    arrayList.add(zaiVar.zaa(this, bufferedReader));
                    break;
            }
        }
    }

    private final ArrayList zav(BufferedReader bufferedReader, FastJsonResponse.Field field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char zai2 = zai(bufferedReader);
        switch (zai2) {
            case ']':
                zaw(5);
                return arrayList;
            case 'n':
                zax(bufferedReader, zaa);
                zaw(5);
                return null;
            case '{':
                this.zat.push(1);
                while (true) {
                    try {
                        FastJsonResponse zad2 = field.zad();
                        if (zaz(bufferedReader, zad2)) {
                            arrayList.add(zad2);
                            char zai3 = zai(bufferedReader);
                            switch (zai3) {
                                case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                                    if (zai(bufferedReader) != '{') {
                                        throw new ParseException("Expected start of next object in array");
                                    }
                                    this.zat.push(1);
                                case ']':
                                    zaw(5);
                                    return arrayList;
                                default:
                                    throw new ParseException("Unexpected token: " + zai3);
                            }
                        } else {
                            return arrayList;
                        }
                    } catch (IllegalAccessException e) {
                        throw new ParseException("Error instantiating inner object", e);
                    } catch (InstantiationException e2) {
                        throw new ParseException("Error instantiating inner object", e2);
                    }
                }
            default:
                throw new ParseException("Unexpected token: " + zai2);
        }
    }

    private final void zaw(int i) throws ParseException {
        if (this.zat.isEmpty()) {
            throw new ParseException("Expected state " + i + " but had empty stack");
        }
        int intValue = ((Integer) this.zat.pop()).intValue();
        if (intValue == i) {
            return;
        }
        throw new ParseException("Expected state " + i + " but had " + intValue);
    }

    private final void zax(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (true) {
            int length = cArr.length;
            if (i >= length) {
                return;
            }
            int read = bufferedReader.read(this.zap, 0, length - i);
            if (read != -1) {
                for (int i2 = 0; i2 < read; i2++) {
                    if (cArr[i2 + i] != this.zap[i2]) {
                        throw new ParseException("Unexpected character");
                    }
                }
                i += read;
            } else {
                throw new ParseException("Unexpected EOF");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zay(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        switch (zai2) {
            case '\"':
                if (z) {
                    throw new ParseException("No boolean value found in string");
                }
                return zay(bufferedReader, true);
            case 'f':
                zax(bufferedReader, z ? zae : zad);
                return false;
            case 'n':
                zax(bufferedReader, zaa);
                return false;
            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /* 116 */:
                zax(bufferedReader, z ? zac : zab);
                return true;
            default:
                throw new ParseException("Unexpected token: " + zai2);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:57:0x00dd. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    @ResultIgnorabilityUnspecified
    private final boolean zaz(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        HashMap hashMap;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaq = zaq(bufferedReader);
        if (zaq != null) {
            while (zaq != null) {
                FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaq);
                if (field == null) {
                    zaq = zar(bufferedReader);
                } else {
                    this.zat.push(4);
                    int i = field.zaa;
                    switch (i) {
                        case 0:
                            if (field.zab) {
                                fastJsonResponse.zav(field, zau(bufferedReader, zag));
                                break;
                            } else {
                                fastJsonResponse.zau(field, zal(bufferedReader));
                                break;
                            }
                        case 1:
                            if (field.zab) {
                                fastJsonResponse.zag(field, zau(bufferedReader, zam));
                                break;
                            } else {
                                fastJsonResponse.zae(field, zat(bufferedReader));
                                break;
                            }
                        case 2:
                            if (field.zab) {
                                fastJsonResponse.zay(field, zau(bufferedReader, zah));
                                break;
                            } else {
                                fastJsonResponse.zax(field, zan(bufferedReader));
                                break;
                            }
                        case 3:
                            if (field.zab) {
                                fastJsonResponse.zas(field, zau(bufferedReader, zai));
                                break;
                            } else {
                                fastJsonResponse.zaq(field, zak(bufferedReader));
                                break;
                            }
                        case 4:
                            if (field.zab) {
                                fastJsonResponse.zao(field, zau(bufferedReader, zaj));
                                break;
                            } else {
                                fastJsonResponse.zam(field, zaj(bufferedReader));
                                break;
                            }
                        case 5:
                            if (field.zab) {
                                fastJsonResponse.zac(field, zau(bufferedReader, zan));
                                break;
                            } else {
                                fastJsonResponse.zaa(field, zas(bufferedReader));
                                break;
                            }
                        case 6:
                            if (field.zab) {
                                fastJsonResponse.zaj(field, zau(bufferedReader, zak));
                                break;
                            } else {
                                fastJsonResponse.zai(field, zay(bufferedReader, false));
                                break;
                            }
                        case 7:
                            if (field.zab) {
                                fastJsonResponse.zaC(field, zau(bufferedReader, zal));
                                break;
                            } else {
                                fastJsonResponse.zaA(field, zao(bufferedReader));
                                break;
                            }
                        case 8:
                            fastJsonResponse.zal(field, Base64Utils.decode(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            break;
                        case 9:
                            fastJsonResponse.zal(field, Base64Utils.decodeUrlSafe(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            break;
                        case 10:
                            char zai2 = zai(bufferedReader);
                            if (zai2 == 'n') {
                                zax(bufferedReader, zaa);
                                hashMap = null;
                            } else if (zai2 == '{') {
                                this.zat.push(1);
                                hashMap = new HashMap();
                                while (true) {
                                    switch (zai(bufferedReader)) {
                                        case 0:
                                            throw new ParseException("Unexpected EOF");
                                        case '\"':
                                            String zaA = zaA(bufferedReader, this.zap, this.zar, null);
                                            if (zai(bufferedReader) == ':') {
                                                if (zai(bufferedReader) == '\"') {
                                                    hashMap.put(zaA, zaA(bufferedReader, this.zap, this.zar, null));
                                                    char zai3 = zai(bufferedReader);
                                                    if (zai3 != ',') {
                                                        if (zai3 == '}') {
                                                            zaw(1);
                                                            break;
                                                        } else {
                                                            throw new ParseException("Unexpected character while parsing string map: " + zai3);
                                                        }
                                                    }
                                                } else {
                                                    throw new ParseException("Expected String value for key ".concat(String.valueOf(zaA)));
                                                }
                                            } else {
                                                throw new ParseException("No map value found for key ".concat(String.valueOf(zaA)));
                                            }
                                        case '}':
                                            zaw(1);
                                            break;
                                    }
                                }
                            } else {
                                throw new ParseException("Expected start of a map object");
                            }
                            fastJsonResponse.zaB(field, hashMap);
                            break;
                        case 11:
                            if (field.zab) {
                                char zai4 = zai(bufferedReader);
                                if (zai4 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                                    break;
                                } else {
                                    this.zat.push(5);
                                    if (zai4 == '[') {
                                        fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, zav(bufferedReader, field));
                                        break;
                                    } else {
                                        throw new ParseException("Expected array start");
                                    }
                                }
                            } else {
                                char zai5 = zai(bufferedReader);
                                if (zai5 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                                    break;
                                } else {
                                    this.zat.push(1);
                                    if (zai5 != '{') {
                                        throw new ParseException("Expected start of object");
                                    }
                                    try {
                                        FastJsonResponse zad2 = field.zad();
                                        zaz(bufferedReader, zad2);
                                        fastJsonResponse.addConcreteTypeInternal(field, field.zae, zad2);
                                        break;
                                    } catch (IllegalAccessException e) {
                                        throw new ParseException("Error instantiating inner object", e);
                                    } catch (InstantiationException e2) {
                                        throw new ParseException("Error instantiating inner object", e2);
                                    }
                                }
                            }
                        default:
                            throw new ParseException("Invalid field type " + i);
                    }
                    zaw(4);
                    zaw(2);
                    char zai6 = zai(bufferedReader);
                    switch (zai6) {
                        case MotionEventCompat.AXIS_GENERIC_13 /* 44 */:
                            zaq = zaq(bufferedReader);
                            break;
                        case '}':
                            zaq = null;
                            break;
                        default:
                            throw new ParseException("Expected end of object or field separator, but found: " + zai6);
                    }
                }
            }
            zaw(1);
            return true;
        }
        zaw(1);
        return false;
    }

    public void parse(InputStream is, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is), 1024);
        try {
            try {
                this.zat.push(0);
                char zai2 = zai(bufferedReader);
                switch (zai2) {
                    case 0:
                        throw new ParseException("No data to parse");
                    case '[':
                        this.zat.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                        if (fieldMappings.size() != 1) {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                        FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                        t.addConcreteTypeArrayInternal(value, value.zae, zav(bufferedReader, value));
                        break;
                    case '{':
                        this.zat.push(1);
                        zaz(bufferedReader, t);
                        break;
                    default:
                        throw new ParseException("Unexpected token: " + zai2);
                }
                zaw(0);
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e2) {
                throw new ParseException(e2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }
}
