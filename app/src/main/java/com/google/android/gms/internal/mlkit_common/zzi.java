package com.google.android.gms.internal.mlkit_common;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes24.dex */
public final class zzi {
    public static final /* synthetic */ int zza = 0;
    private static final String[] zzb = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};
    private static final String[] zzc;
    private static final String[] zzd;

    static {
        String[] strArr = new String[2];
        strArr[0] = "media";
        strArr[1] = true != (Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("ranchu")) ? "" : "androidx.test.services.storage.runfiles";
        zzc = strArr;
        String[] strArr2 = new String[3];
        strArr2[0] = Build.VERSION.SDK_INT <= 25 ? "com.google.android.inputmethod.latin.inputcontent" : "";
        strArr2[1] = Build.VERSION.SDK_INT <= 25 ? "com.google.android.inputmethod.latin.dev.inputcontent" : "";
        strArr2[2] = "com.google.android.apps.docs.storage.legacy";
        zzd = strArr2;
    }

    public static AssetFileDescriptor zza(Context context, Uri uri, String str) throws FileNotFoundException {
        zzh zzhVar = zzh.zza;
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openAssetFileDescriptor(zzc2, "r");
        }
        if ("content".equals(scheme)) {
            if (!zzj(context, zzc2, 1, zzhVar)) {
                throw new FileNotFoundException("Can't open content uri.");
            }
            AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(zzc2, "r");
            zzd(openAssetFileDescriptor);
            return openAssetFileDescriptor;
        }
        if ("file".equals(scheme)) {
            AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(zzc2, "r");
            zzd(openAssetFileDescriptor2);
            try {
                zzi(context, openAssetFileDescriptor2.getParcelFileDescriptor(), zzc2, zzhVar);
                return openAssetFileDescriptor2;
            } catch (FileNotFoundException e) {
                zzg(openAssetFileDescriptor2, e);
                throw e;
            } catch (IOException e2) {
                FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                fileNotFoundException.initCause(e2);
                zzg(openAssetFileDescriptor2, fileNotFoundException);
                throw fileNotFoundException;
            }
        }
        throw new FileNotFoundException("Unsupported scheme");
    }

    public static InputStream zzb(Context context, Uri uri, zzh zzhVar) throws FileNotFoundException {
        ContentResolver contentResolver = context.getContentResolver();
        Uri zzc2 = zzc(uri);
        String scheme = zzc2.getScheme();
        if ("android.resource".equals(scheme)) {
            return contentResolver.openInputStream(zzc2);
        }
        if ("content".equals(scheme)) {
            if (!zzj(context, zzc2, 1, zzhVar)) {
                throw new FileNotFoundException("Can't open content uri.");
            }
            InputStream openInputStream = contentResolver.openInputStream(zzc2);
            zzd(openInputStream);
            return openInputStream;
        }
        if ("file".equals(scheme)) {
            try {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(Uri.fromFile(new File(zzc2.getPath()).getCanonicalFile()), "r");
                try {
                    zzi(context, openFileDescriptor, zzc2, zzhVar);
                    return new ParcelFileDescriptor.AutoCloseInputStream(openFileDescriptor);
                } catch (FileNotFoundException e) {
                    zzh(openFileDescriptor, e);
                    throw e;
                } catch (IOException e2) {
                    FileNotFoundException fileNotFoundException = new FileNotFoundException("Validation failed.");
                    fileNotFoundException.initCause(e2);
                    zzh(openFileDescriptor, fileNotFoundException);
                    throw fileNotFoundException;
                }
            } catch (IOException e3) {
                FileNotFoundException fileNotFoundException2 = new FileNotFoundException("Canonicalization failed.");
                fileNotFoundException2.initCause(e3);
                throw fileNotFoundException2;
            }
        }
        throw new FileNotFoundException("Unsupported scheme");
    }

    private static Uri zzc(Uri uri) {
        return Build.VERSION.SDK_INT < 30 ? Uri.parse(uri.toString()) : uri;
    }

    private static Object zzd(Object obj) throws FileNotFoundException {
        if (obj != null) {
            return obj;
        }
        throw new FileNotFoundException("Content resolver returned null value.");
    }

    private static String zze(File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        return !canonicalPath.endsWith("/") ? String.valueOf(canonicalPath).concat("/") : canonicalPath;
    }

    private static void zzf(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
        try {
            StructStat fstat = Os.fstat(parcelFileDescriptor.getFileDescriptor());
            try {
                StructStat lstat = Os.lstat(str);
                if (OsConstants.S_ISLNK(lstat.st_mode)) {
                    throw new FileNotFoundException("Can't open file: ".concat(String.valueOf(str)));
                }
                if (fstat.st_dev == lstat.st_dev && fstat.st_ino == lstat.st_ino) {
                } else {
                    throw new FileNotFoundException("Can't open file: ".concat(String.valueOf(str)));
                }
            } catch (ErrnoException e) {
                throw new IOException(e);
            }
        } catch (ErrnoException e2) {
            throw new IOException(e2);
        }
    }

    private static void zzg(AssetFileDescriptor assetFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            assetFileDescriptor.close();
        } catch (IOException e) {
            fileNotFoundException.addSuppressed(e);
        }
    }

    private static void zzh(ParcelFileDescriptor parcelFileDescriptor, FileNotFoundException fileNotFoundException) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            fileNotFoundException.addSuppressed(e);
        }
    }

    private static void zzi(final Context context, ParcelFileDescriptor parcelFileDescriptor, Uri uri, zzh zzhVar) throws IOException {
        boolean z;
        File dataDir;
        String canonicalPath = new File(uri.getPath()).getCanonicalPath();
        zzf(parcelFileDescriptor, canonicalPath);
        if (!canonicalPath.startsWith("/proc/") && !canonicalPath.startsWith("/data/misc/")) {
            zzh.zza(zzhVar);
            File dataDir2 = ContextCompat.getDataDir(context);
            boolean z2 = true;
            if (dataDir2 == null ? !canonicalPath.startsWith(zze(Environment.getDataDirectory())) : !canonicalPath.startsWith(zze(dataDir2))) {
                Context createDeviceProtectedStorageContext = ContextCompat.createDeviceProtectedStorageContext(context);
                if (createDeviceProtectedStorageContext == null || (dataDir = ContextCompat.getDataDir(createDeviceProtectedStorageContext)) == null || !canonicalPath.startsWith(zze(dataDir))) {
                    File[] zzk = zzk(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzb
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            int i = zzi.zza;
                            return ContextCompat.getExternalFilesDirs(context, null);
                        }
                    });
                    int length = zzk.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            File file = zzk[i];
                            if (file != null && canonicalPath.startsWith(zze(file))) {
                                break;
                            } else {
                                i++;
                            }
                        } else {
                            File[] zzk2 = zzk(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzc
                                @Override // java.util.concurrent.Callable
                                public final Object call() {
                                    int i2 = zzi.zza;
                                    return ContextCompat.getExternalCacheDirs(context);
                                }
                            });
                            int length2 = zzk2.length;
                            int i2 = 0;
                            while (true) {
                                if (i2 < length2) {
                                    File file2 = zzk2[i2];
                                    if (file2 != null && canonicalPath.startsWith(zze(file2))) {
                                        break;
                                    } else {
                                        i2++;
                                    }
                                } else {
                                    z2 = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            z = zzhVar.zzb;
            if (z2 == z) {
                return;
            }
        }
        throw new FileNotFoundException("Can't open file: ".concat(String.valueOf(canonicalPath)));
    }

    private static boolean zzj(Context context, Uri uri, int i, zzh zzhVar) {
        boolean z;
        boolean z2;
        boolean z3;
        String authority = uri.getAuthority();
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
        if (resolveContentProvider == null) {
            int lastIndexOf = authority.lastIndexOf(64);
            if (lastIndexOf >= 0) {
                authority = authority.substring(lastIndexOf + 1);
                resolveContentProvider = context.getPackageManager().resolveContentProvider(authority, 0);
            }
            if (resolveContentProvider == null) {
                z3 = zzhVar.zzb;
                return !z3;
            }
        }
        switch (zzh.zzc(zzhVar, context, new zzj(uri, resolveContentProvider, authority)) - 1) {
            case 1:
                return false;
            default:
                if (context.getPackageName().equals(resolveContentProvider.packageName)) {
                    z2 = zzhVar.zzb;
                    return z2;
                }
                z = zzhVar.zzb;
                if (z) {
                    return false;
                }
                if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), 1) != 0 && resolveContentProvider.exported) {
                    String[] strArr = zzc;
                    int length = strArr.length;
                    for (int i2 = 0; i2 < 2; i2++) {
                        if (strArr[i2].equals(authority)) {
                            return true;
                        }
                    }
                    String[] strArr2 = zzd;
                    int length2 = strArr2.length;
                    for (int i3 = 0; i3 < 3; i3++) {
                        if (strArr2[i3].equals(authority)) {
                            return true;
                        }
                    }
                    String[] strArr3 = zzb;
                    for (int i4 = 0; i4 < 6; i4++) {
                        String str = strArr3[i4];
                        if (str.charAt(str.length() - 1) == '.') {
                            if (resolveContentProvider.packageName.startsWith(str)) {
                                return false;
                            }
                        } else if (resolveContentProvider.packageName.equals(str)) {
                            return false;
                        }
                    }
                }
                return true;
        }
    }

    private static File[] zzk(Callable callable) {
        try {
            return (File[]) callable.call();
        } catch (NullPointerException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
