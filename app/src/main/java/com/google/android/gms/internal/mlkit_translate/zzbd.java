package com.google.android.gms.internal.mlkit_translate;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzbd extends zzbh {
    public static final /* synthetic */ int zza = 0;
    private static final zzbb zzc;

    static {
        zzbb zzbbVar;
        Set<String> supportedFileAttributeViews = FileSystems.getDefault().supportedFileAttributeViews();
        if (supportedFileAttributeViews.contains("posix")) {
            zzc = new zzbb() { // from class: com.google.android.gms.internal.mlkit_translate.zzaw
                @Override // com.google.android.gms.internal.mlkit_translate.zzbb
                public final FileAttribute zza() {
                    int i = zzbd.zza;
                    return PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
                }
            };
            return;
        }
        if (supportedFileAttributeViews.contains("acl")) {
            try {
                UserPrincipalLookupService userPrincipalLookupService = FileSystems.getDefault().getUserPrincipalLookupService();
                String str = (String) Objects.requireNonNull(zzh.USER_NAME.zza());
                try {
                    Class<?> cls = Class.forName("java.lang.ProcessHandle");
                    Class<?> cls2 = Class.forName("java.lang.ProcessHandle$Info");
                    Class<?> cls3 = Class.forName("java.util.Optional");
                    Method method = cls.getMethod("current", new Class[0]);
                    str = (String) Objects.requireNonNull(cls3.getMethod("orElse", Object.class).invoke(cls2.getMethod("user", new Class[0]).invoke(cls.getMethod("info", new Class[0]).invoke(method.invoke(null, new Object[0]), new Object[0]), new Object[0]), str));
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e2) {
                } catch (NoSuchMethodException e3) {
                } catch (InvocationTargetException e4) {
                    Throwable cause = e4.getCause();
                    int i = zzj.zza;
                    if (cause != null) {
                        if (cause instanceof RuntimeException) {
                            throw ((RuntimeException) cause);
                        }
                        if (cause instanceof Error) {
                            throw ((Error) cause);
                        }
                    } else {
                        throw null;
                    }
                }
                final zzba zzbaVar = new zzba(zzt.zzi(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(userPrincipalLookupService.lookupPrincipalByName(str)).setPermissions(EnumSet.allOf(AclEntryPermission.class)).setFlags(AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT).build()));
                zzbbVar = new zzbb() { // from class: com.google.android.gms.internal.mlkit_translate.zzay
                    @Override // com.google.android.gms.internal.mlkit_translate.zzbb
                    public final FileAttribute zza() {
                        int i2 = zzbd.zza;
                        return zzbaVar;
                    }
                };
            } catch (IOException e5) {
                zzbbVar = new zzbb() { // from class: com.google.android.gms.internal.mlkit_translate.zzaz
                    @Override // com.google.android.gms.internal.mlkit_translate.zzbb
                    public final FileAttribute zza() {
                        throw new IOException("Could not find user", e5);
                    }
                };
            }
        } else {
            zzbbVar = new zzbb() { // from class: com.google.android.gms.internal.mlkit_translate.zzax
                @Override // com.google.android.gms.internal.mlkit_translate.zzbb
                public final FileAttribute zza() {
                    throw new IOException("unrecognized FileSystem type ".concat(String.valueOf(String.valueOf(FileSystems.getDefault()))));
                }
            };
        }
        zzc = zzbbVar;
    }

    private zzbd() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbd(zzbc zzbcVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_translate.zzbh
    public final File zza() {
        try {
            return Files.createTempDirectory(Paths.get(zzh.JAVA_IO_TMPDIR.zza(), new String[0]), null, zzc.zza()).toFile();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory", e);
        }
    }
}
