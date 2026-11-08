package com.google.mlkit.common.internal.model;

import android.content.Context;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class ModelUtils {
    private static final GmsLogger zza = new GmsLogger("ModelUtils", "");

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    /* loaded from: classes25.dex */
    public static abstract class AutoMLManifest {
        public abstract String getLabelsFile();

        public abstract String getModelFile();

        public abstract String getModelType();
    }

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    /* loaded from: classes25.dex */
    public static abstract class ModelLoggingInfo {
        static ModelLoggingInfo zza(long j, String str, boolean z) {
            return new AutoValue_ModelUtils_ModelLoggingInfo(j, zzu.zzb(str), z);
        }

        public abstract String getHash();

        public abstract long getSize();

        public abstract boolean isManifestModel();
    }

    private ModelUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0111 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo getModelLoggingInfo(android.content.Context r11, com.google.mlkit.common.model.LocalModel r12) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.getModelLoggingInfo(android.content.Context, com.google.mlkit.common.model.LocalModel):com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo");
    }

    public static String getSHA256(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                String zzc = zzc(fileInputStream);
                fileInputStream.close();
                return zzc;
            } finally {
            }
        } catch (IOException e) {
            zza.e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e.toString()));
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x002e, code lost:
    
        if (new java.io.File(r4).exists() == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest parseManifestFile(java.lang.String r4, boolean r5, android.content.Context r6) {
        /*
            java.lang.String r0 = java.lang.String.valueOf(r4)
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.mlkit.common.internal.model.ModelUtils.zza
            java.lang.String r2 = "Manifest file path: "
            java.lang.String r0 = r2.concat(r0)
            java.lang.String r2 = "ModelUtils"
            r1.d(r2, r0)
            r0 = 0
            if (r5 == 0) goto L25
            android.content.res.AssetManager r1 = r6.getAssets()     // Catch: java.io.IOException -> L23
            java.io.InputStream r1 = r1.open(r4)     // Catch: java.io.IOException -> L23
            if (r1 == 0) goto L38
            r1.close()     // Catch: java.io.IOException -> L23
            goto L38
        L23:
            r4 = move-exception
            goto L30
        L25:
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L38
        L30:
            com.google.android.gms.common.internal.GmsLogger r4 = com.google.mlkit.common.internal.model.ModelUtils.zza
            java.lang.String r5 = "Manifest file does not exist."
            r4.e(r2, r5)
            return r0
        L38:
            boolean r1 = r4.isEmpty()     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r3 = 0
            if (r1 == 0) goto L42
            byte[] r4 = new byte[r3]     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            goto L68
        L42:
            if (r5 == 0) goto L4d
            android.content.res.AssetManager r5 = r6.getAssets()     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.io.InputStream r4 = r5.open(r4)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            goto L58
        L4d:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.io.File r6 = new java.io.File     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r6.<init>(r4)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r5.<init>(r6)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r4 = r5
        L58:
            int r5 = r4.available()     // Catch: java.lang.Throwable -> La2
            byte[] r6 = new byte[r5]     // Catch: java.lang.Throwable -> La2
            r4.read(r6, r3, r5)     // Catch: java.lang.Throwable -> La2
            if (r4 == 0) goto L66
            r4.close()     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
        L66:
            r4 = r6
        L68:
            java.lang.String r5 = new java.lang.String     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r6 = "UTF-8"
            r5.<init>(r4, r6)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            com.google.android.gms.common.internal.GmsLogger r4 = com.google.mlkit.common.internal.model.ModelUtils.zza     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r6.<init>()     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r1 = "Json string from the manifest file: "
            r6.append(r1)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r6.append(r5)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r4.d(r2, r6)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r4.<init>(r5)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r5 = "modelType"
            java.lang.String r5 = r4.getString(r5)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r6 = "modelFile"
            java.lang.String r6 = r4.getString(r6)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            java.lang.String r1 = "labelsFile"
            java.lang.String r4 = r4.getString(r1)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest r1 = new com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            r1.<init>(r5, r6, r4)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
            return r1
        La2:
            r5 = move-exception
            if (r4 == 0) goto Lad
            r4.close()     // Catch: java.lang.Throwable -> La9
            goto Lad
        La9:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
        Lad:
            throw r5     // Catch: java.io.IOException -> Lae org.json.JSONException -> Lb0
        Lae:
            r4 = move-exception
            goto Lb1
        Lb0:
            r4 = move-exception
        Lb1:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.mlkit.common.internal.model.ModelUtils.zza
            java.lang.String r6 = "Error parsing the manifest file."
            r5.e(r2, r6, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.parseManifestFile(java.lang.String, boolean, android.content.Context):com.google.mlkit.common.internal.model.ModelUtils$AutoMLManifest");
    }

    public static boolean zza(File file, String str) {
        String sha256 = getSHA256(file);
        zza.d("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(sha256)));
        return str.equals(sha256);
    }

    private static String zzb(Context context, String str, boolean z) {
        AutoMLManifest parseManifestFile = parseManifestFile(str, z, context);
        if (parseManifestFile == null) {
            zza.e("ModelUtils", "Failed to parse manifest file.");
            return null;
        }
        return new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
    }

    private static String zzc(InputStream inputStream) {
        int i;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (IOException e) {
            zza.e("ModelUtils", "Failed to read model file");
            return null;
        } catch (NoSuchAlgorithmException e2) {
            zza.e("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        }
    }
}
