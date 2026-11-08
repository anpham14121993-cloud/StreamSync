package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.Xml;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppLocalesStorageHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final String APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME = "androidx.appcompat.app.AppLocalesMetadataHolderService";
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";

    private AppLocalesStorageHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0046, code lost:
    
        r2 = r4.getAttributeValue(null, androidx.appcompat.app.AppLocalesStorageHelper.LOCALE_RECORD_ATTRIBUTE_TAG);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String readLocales(android.content.Context r10) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r10.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L8d
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
        L1a:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            r7 = r6
            r8 = 1
            if (r6 == r8) goto L49
            r6 = 3
            if (r7 != r6) goto L2b
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            if (r8 <= r5) goto L49
        L2b:
            if (r7 == r6) goto L1a
            r6 = 4
            if (r7 != r6) goto L31
            goto L1a
        L31:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            java.lang.String r8 = "locales"
            boolean r8 = r6.equals(r8)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            if (r8 == 0) goto L48
            java.lang.String r8 = "application_locales"
            r9 = 0
            java.lang.String r8 = r4.getAttributeValue(r9, r8)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L53 org.xmlpull.v1.XmlPullParserException -> L55
            r2 = r8
            goto L49
        L48:
            goto L1a
        L49:
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L4f
        L4e:
            goto L63
        L4f:
            r4 = move-exception
            goto L4e
        L51:
            r0 = move-exception
            goto L85
        L53:
            r4 = move-exception
            goto L56
        L55:
            r4 = move-exception
        L56:
            java.lang.String r5 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r5)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L4f
            goto L4e
        L63:
            boolean r4 = r2.isEmpty()
            if (r4 != 0) goto L81
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            goto L84
        L81:
            r10.deleteFile(r0)
        L84:
            return r2
        L85:
            if (r3 == 0) goto L8c
            r3.close()     // Catch: java.io.IOException -> L8b
            goto L8c
        L8b:
            r1 = move-exception
        L8c:
            throw r0
        L8d:
            r0 = move-exception
            java.lang.String r3 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0080 -> B:14:0x0082). Please report as a decompilation issue!!! */
    public static void persistLocales(Context context, String locales) {
        if (locales.equals("")) {
            context.deleteFile(APPLICATION_LOCALES_RECORD_FILE);
            return;
        }
        try {
            FileOutputStream fos = context.openFileOutput(APPLICATION_LOCALES_RECORD_FILE, 0);
            XmlSerializer serializer = Xml.newSerializer();
            try {
                try {
                    try {
                        serializer.setOutput(fos, null);
                        serializer.startDocument("UTF-8", true);
                        serializer.startTag(null, LOCALE_RECORD_FILE_TAG);
                        serializer.attribute(null, LOCALE_RECORD_ATTRIBUTE_TAG, locales);
                        serializer.endTag(null, LOCALE_RECORD_FILE_TAG);
                        serializer.endDocument();
                        Log.d(TAG, "Storing App Locales : app-locales: " + locales + " persisted successfully.");
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e) {
                        Log.w(TAG, "Storing App Locales : Failed to persist app-locales: " + locales, e);
                        if (fos != null) {
                            fos.close();
                        }
                    }
                } catch (IOException e2) {
                }
            } catch (Throwable th) {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e4) {
            Log.w(TAG, String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", APPLICATION_LOCALES_RECORD_FILE));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void syncLocalesToFramework(Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            ComponentName app_locales_component = new ComponentName(context, APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME);
            if (context.getPackageManager().getComponentEnabledSetting(app_locales_component) != 1) {
                if (AppCompatDelegate.getApplicationLocales().isEmpty()) {
                    String appLocales = readLocales(context);
                    Object localeManager = context.getSystemService("locale");
                    if (localeManager != null) {
                        AppCompatDelegate.Api33Impl.localeManagerSetApplicationLocales(localeManager, AppCompatDelegate.Api24Impl.localeListForLanguageTags(appLocales));
                    }
                }
                context.getPackageManager().setComponentEnabledSetting(app_locales_component, 1, 1);
            }
        }
    }

    /* loaded from: classes.dex */
    static class ThreadPerTaskExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue<Runnable> mTasks = new ArrayDeque();

        /* JADX INFO: Access modifiers changed from: package-private */
        public SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable r) {
            synchronized (this.mLock) {
                this.mTasks.add(new Runnable() { // from class: androidx.appcompat.app.AppLocalesStorageHelper$SerialExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppLocalesStorageHelper.SerialExecutor.this.m9xd188c474(r);
                    }
                });
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$execute$0$androidx-appcompat-app-AppLocalesStorageHelper$SerialExecutor, reason: not valid java name */
        public /* synthetic */ void m9xd188c474(Runnable r) {
            try {
                r.run();
            } finally {
                scheduleNext();
            }
        }

        protected void scheduleNext() {
            synchronized (this.mLock) {
                Runnable poll = this.mTasks.poll();
                this.mActive = poll;
                if (poll != null) {
                    this.mExecutor.execute(this.mActive);
                }
            }
        }
    }
}
