package com.google.common.io;

import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.io.TempFileCreator;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryFlag;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
abstract class TempFileCreator {
    static final TempFileCreator INSTANCE = pickSecureCreator();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract File createTempDir();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract File createTempFile(String prefix) throws IOException;

    private static TempFileCreator pickSecureCreator() {
        try {
            Class.forName("java.nio.file.Path");
            return new JavaNioCreator();
        } catch (ClassNotFoundException e) {
            try {
                int version = ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue();
                int jellyBean = ((Integer) Class.forName("android.os.Build$VERSION_CODES").getField("JELLY_BEAN").get(null)).intValue();
                if (version < jellyBean) {
                    return new ThrowingCreator();
                }
                return new JavaIoCreator();
            } catch (ClassNotFoundException e2) {
                return new ThrowingCreator();
            } catch (IllegalAccessException e3) {
                return new ThrowingCreator();
            } catch (NoSuchFieldException e4) {
                return new ThrowingCreator();
            }
        }
    }

    static void testMakingUserPermissionsFromScratch() throws IOException {
        JavaNioCreator.access$300().get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class JavaNioCreator extends TempFileCreator {
        private static final PermissionSupplier directoryPermissions;
        private static final PermissionSupplier filePermissions;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes25.dex */
        public interface PermissionSupplier {
            FileAttribute<?> get() throws IOException;
        }

        private JavaNioCreator() {
            super();
        }

        static /* synthetic */ PermissionSupplier access$300() {
            return userPermissions();
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempDir() {
            try {
                return java.nio.file.Files.createTempDirectory(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), null, directoryPermissions.get()).toFile();
            } catch (IOException e) {
                throw new IllegalStateException("Failed to create directory", e);
            }
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempFile(String prefix) throws IOException {
            return java.nio.file.Files.createTempFile(Paths.get(StandardSystemProperty.JAVA_IO_TMPDIR.value(), new String[0]), prefix, null, filePermissions.get()).toFile();
        }

        static {
            Set<String> views = FileSystems.getDefault().supportedFileAttributeViews();
            if (views.contains("posix")) {
                filePermissions = new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda0
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        FileAttribute asFileAttribute;
                        asFileAttribute = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));
                        return asFileAttribute;
                    }
                };
                directoryPermissions = new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda1
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        FileAttribute asFileAttribute;
                        asFileAttribute = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
                        return asFileAttribute;
                    }
                };
            } else if (views.contains("acl")) {
                PermissionSupplier userPermissions = userPermissions();
                directoryPermissions = userPermissions;
                filePermissions = userPermissions;
            } else {
                PermissionSupplier permissionSupplier = new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda2
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        return TempFileCreator.JavaNioCreator.lambda$static$2();
                    }
                };
                directoryPermissions = permissionSupplier;
                filePermissions = permissionSupplier;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ FileAttribute lambda$static$2() throws IOException {
            throw new IOException("unrecognized FileSystem type " + FileSystems.getDefault());
        }

        private static PermissionSupplier userPermissions() {
            try {
                UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(getUsername());
                final ImmutableList<AclEntry> acl = ImmutableList.of(AclEntry.newBuilder().setType(AclEntryType.ALLOW).setPrincipal(user).setPermissions(EnumSet.allOf(AclEntryPermission.class)).setFlags(AclEntryFlag.DIRECTORY_INHERIT, AclEntryFlag.FILE_INHERIT).build());
                final FileAttribute<ImmutableList<AclEntry>> attribute = new FileAttribute<ImmutableList<AclEntry>>() { // from class: com.google.common.io.TempFileCreator.JavaNioCreator.1
                    @Override // java.nio.file.attribute.FileAttribute
                    public String name() {
                        return "acl:acl";
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.nio.file.attribute.FileAttribute
                    public ImmutableList<AclEntry> value() {
                        return ImmutableList.this;
                    }
                };
                return new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda3
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        return TempFileCreator.JavaNioCreator.lambda$userPermissions$3(attribute);
                    }
                };
            } catch (IOException e) {
                return new PermissionSupplier() { // from class: com.google.common.io.TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda4
                    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
                    public final FileAttribute get() {
                        return TempFileCreator.JavaNioCreator.lambda$userPermissions$4(e);
                    }
                };
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ FileAttribute lambda$userPermissions$3(FileAttribute attribute) throws IOException {
            return attribute;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ FileAttribute lambda$userPermissions$4(IOException e) throws IOException {
            throw new IOException("Could not find user", e);
        }

        private static String getUsername() {
            String fromSystemProperty = (String) Objects.requireNonNull(StandardSystemProperty.USER_NAME.value());
            try {
                Class<?> processHandleClass = Class.forName("java.lang.ProcessHandle");
                Class<?> processHandleInfoClass = Class.forName("java.lang.ProcessHandle$Info");
                Class<?> optionalClass = Class.forName("java.util.Optional");
                Method currentMethod = processHandleClass.getMethod("current", new Class[0]);
                Method infoMethod = processHandleClass.getMethod("info", new Class[0]);
                Method userMethod = processHandleInfoClass.getMethod("user", new Class[0]);
                Method orElseMethod = optionalClass.getMethod("orElse", Object.class);
                Object current = currentMethod.invoke(null, new Object[0]);
                Object info = infoMethod.invoke(current, new Object[0]);
                Object user = userMethod.invoke(info, new Object[0]);
                return (String) Objects.requireNonNull(orElseMethod.invoke(user, fromSystemProperty));
            } catch (ClassNotFoundException e) {
                return fromSystemProperty;
            } catch (IllegalAccessException e2) {
                return fromSystemProperty;
            } catch (NoSuchMethodException e3) {
                return fromSystemProperty;
            } catch (InvocationTargetException e4) {
                Throwables.throwIfUnchecked(e4.getCause());
                return fromSystemProperty;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class JavaIoCreator extends TempFileCreator {
        private static final int TEMP_DIR_ATTEMPTS = 10000;

        private JavaIoCreator() {
            super();
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempDir() {
            File baseDir = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value());
            String baseName = System.currentTimeMillis() + "-";
            for (int counter = 0; counter < 10000; counter++) {
                File tempDir = new File(baseDir, baseName + counter);
                if (tempDir.mkdir()) {
                    return tempDir;
                }
            }
            throw new IllegalStateException("Failed to create directory within 10000 attempts (tried " + baseName + "0 to " + baseName + "9999)");
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempFile(String prefix) throws IOException {
            return File.createTempFile(prefix, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes25.dex */
    public static final class ThrowingCreator extends TempFileCreator {
        private static final String MESSAGE = "Guava cannot securely create temporary files or directories under SDK versions before Jelly Bean. You can create one yourself, either in the insecure default directory or in a more secure directory, such as context.getCacheDir(). For more information, see the Javadoc for Files.createTempDir().";

        private ThrowingCreator() {
            super();
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempDir() {
            throw new IllegalStateException(MESSAGE);
        }

        @Override // com.google.common.io.TempFileCreator
        File createTempFile(String prefix) throws IOException {
            throw new IOException(MESSAGE);
        }
    }

    private TempFileCreator() {
    }
}
