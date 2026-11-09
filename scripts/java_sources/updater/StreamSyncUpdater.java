package com.streamsync.app.updater;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StreamSyncUpdater {

    private static final String DEFAULT_VERSION = "v1.9.1";
    private static final String GITHUB_REPO_API = "https://api.github.com/repos/anpham14121993-cloud/StreamSync/releases/latest";

    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());

    private static String getAppVersion(Context context) {
        try {
            String v = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (v != null && !v.isEmpty()) {
                return v.startsWith("v") ? v : ("v" + v);
            }
        } catch (Throwable ignored) {}
        return DEFAULT_VERSION;
    }

    public static void checkUpdate(final Context context) {
        if (context == null) return;

        try {
            Toast.makeText(context, "Đang kiểm tra bản cập nhật...", Toast.LENGTH_SHORT).show();
        } catch (Throwable ignored) {}

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String currentVersion = getAppVersion(context);
                    URL url = new URL(GITHUB_REPO_API);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    conn.setRequestProperty("User-Agent", "StreamSync-Updater");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);

                    int code = conn.getResponseCode();
                    if (code == 200) {
                        String resp = readStream(conn.getInputStream());
                        JSONObject release = new JSONObject(resp);
                        final String tag = release.optString("tag_name", "").trim();
                        final String bodyNotes = release.optString("body", "");
                        JSONArray assets = release.optJSONArray("assets");

                        // Find the best APK asset for this architecture
                        String selectedUrl = "";
                        String selectedName = "";

                        String preferredAbi = "universal";
                        if (Build.SUPPORTED_ABIS != null && Build.SUPPORTED_ABIS.length > 0) {
                            for (String abi : Build.SUPPORTED_ABIS) {
                                if ("arm64-v8a".equalsIgnoreCase(abi)) {
                                    preferredAbi = "arm64-v8a";
                                    break;
                                } else if ("armeabi-v7a".equalsIgnoreCase(abi)) {
                                    preferredAbi = "armeabi-v7a";
                                    break;
                                }
                            }
                        }

                        if (assets != null && assets.length() > 0) {
                            // Try exact ABI match first
                            for (int i = 0; i < assets.length(); i++) {
                                JSONObject asset = assets.getJSONObject(i);
                                String name = asset.optString("name", "");
                                if (name.endsWith(".apk") && name.contains(preferredAbi)) {
                                    selectedUrl = asset.optString("browser_download_url", "");
                                    selectedName = name;
                                    break;
                                }
                            }
                            // Fallback to universal
                            if (selectedUrl.isEmpty()) {
                                for (int i = 0; i < assets.length(); i++) {
                                    JSONObject asset = assets.getJSONObject(i);
                                    String name = asset.optString("name", "");
                                    if (name.endsWith(".apk") && name.contains("universal")) {
                                        selectedUrl = asset.optString("browser_download_url", "");
                                        selectedName = name;
                                        break;
                                    }
                                }
                            }
                            // Fallback to any APK
                            if (selectedUrl.isEmpty()) {
                                for (int i = 0; i < assets.length(); i++) {
                                    JSONObject asset = assets.getJSONObject(i);
                                    String name = asset.optString("name", "");
                                    if (name.endsWith(".apk")) {
                                        selectedUrl = asset.optString("browser_download_url", "");
                                        selectedName = name;
                                        break;
                                    }
                                }
                            }
                        }

                        final String finalDownloadUrl = selectedUrl;
                        final String finalFileName = selectedName.isEmpty() ? "StreamSync-update.apk" : selectedName;

                        if (isNewerVersion(tag, currentVersion)) {
                            sMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    showUpdatePrompt(context, tag, finalDownloadUrl, finalFileName, false);
                                }
                            });
                        } else {
                            sMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    showUpdatePrompt(context, tag.isEmpty() ? currentVersion : tag, finalDownloadUrl, finalFileName, true);
                                }
                            });
                        }
                    } else {
                        sMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                showErrorDialog(context);
                            }
                        });
                    }
                } catch (final Throwable e) {
                    sMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showErrorDialog(context);
                        }
                    });
                }
            }
        }).start();
    }

    private static boolean isNewerVersion(String latestTag, String currentTag) {
        if (latestTag == null || latestTag.isEmpty()) return false;
        String v1 = latestTag.replaceAll("[^0-9.]", "");
        String v2 = currentTag.replaceAll("[^0-9.]", "");
        if (v1.isEmpty() || v2.isEmpty()) return !latestTag.equalsIgnoreCase(currentTag);

        String[] p1 = v1.split("\\.");
        String[] p2 = v2.split("\\.");
        int len = Math.max(p1.length, p2.length);
        for (int i = 0; i < len; i++) {
            int n1 = i < p1.length ? Integer.parseInt(p1[i]) : 0;
            int n2 = i < p2.length ? Integer.parseInt(p2[i]) : 0;
            if (n1 > n2) return true;
            if (n1 < n2) return false;
        }
        return false;
    }

    private static void showUpdatePrompt(final Context context, final String tag, final String downloadUrl, final String fileName, final boolean isLatest) {
        if (!(context instanceof Activity)) {
            Toast.makeText(context, (isLatest ? "Bạn đang dùng bản: " : "Đã có bản mới: ") + tag, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            String title = isLatest ? "Phiên bản hiện tại" : "Cập nhật ứng dụng";
            String msg = isLatest
                    ? ("Bạn đang sử dụng phiên bản: " + tag + " (" + fileName + ").\n\nBạn có muốn tải lại và cài đặt trực tiếp bản này (OTA) không?")
                    : ("Đã có phiên bản mới: " + tag + " (" + fileName + ").\n\nBạn có muốn tải về và cài đặt trực tiếp (OTA) không?");
            String btnText = isLatest ? "Cài đè lại (OTA)" : "Tải & Cài đặt (OTA)";

            new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startInAppDownloadAndInstall((Activity) context, downloadUrl, fileName);
                        }
                    })
                    .setNegativeButton(isLatest ? "Đóng" : "Để sau", null)
                    .show();
        } catch (Throwable ignored) {}
    }

    private static void startInAppDownloadAndInstall(final Activity activity, final String downloadUrl, final String fileName) {
        if (downloadUrl == null || downloadUrl.isEmpty()) {
            Toast.makeText(activity, "Không tìm thấy đường dẫn tải gói cập nhật", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(activity, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setTitle("Cập nhật OTA");
        progressDialog.setMessage("Đang tải bản cập nhật " + fileName + "...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                File outputFile = null;
                try {
                    File updatesDir = new File(activity.getCacheDir(), "updates");
                    if (!updatesDir.exists()) updatesDir.mkdirs();
                    outputFile = new File(updatesDir, fileName);
                    if (outputFile.exists()) outputFile.delete();

                    URL url = new URL(downloadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setInstanceFollowRedirects(true);
                    conn.setConnectTimeout(15000);
                    conn.setReadTimeout(15000);

                    // Handle redirects manually if needed
                    int status = conn.getResponseCode();
                    if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == 307 || status == 308) {
                        String newUrl = conn.getHeaderField("Location");
                        conn = (HttpURLConnection) new URL(newUrl).openConnection();
                        conn.setInstanceFollowRedirects(true);
                    }

                    int contentLength = conn.getContentLength();
                    InputStream input = conn.getInputStream();
                    FileOutputStream output = new FileOutputStream(outputFile);

                    byte[] buffer = new byte[8192];
                    long total = 0;
                    int count;
                    while ((count = input.read(buffer)) != -1) {
                        total += count;
                        output.write(buffer, 0, count);
                        if (contentLength > 0) {
                            final int progressPercent = (int) (total * 100 / contentLength);
                            sMainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progressPercent);
                                }
                            });
                        }
                    }

                    output.flush();
                    output.close();
                    input.close();

                    final File finalFile = outputFile;
                    sMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            launchPackageInstaller(activity, finalFile);
                        }
                    });

                } catch (final Throwable e) {
                    if (outputFile != null && outputFile.exists()) {
                        outputFile.delete();
                    }
                    sMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(activity, "Lỗi tải bản cập nhật: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    private static void launchPackageInstaller(Activity activity, File apkFile) {
        try {
            if (!apkFile.exists()) {
                Toast.makeText(activity, "File cập nhật không tồn tại", Toast.LENGTH_LONG).show();
                return;
            }

            // Check Unknown Sources Permission for Android O+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    if (!activity.getPackageManager().canRequestPackageInstalls()) {
                        Toast.makeText(activity, "Vui lòng cho phép cài đặt từ nguồn này để tiếp tục", Toast.LENGTH_LONG).show();
                        Intent reqIntent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        reqIntent.setData(Uri.parse("package:" + activity.getPackageName()));
                        reqIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(reqIntent);
                    }
                } catch (Throwable ignored) {}
            }

            // Launch package installer via FileProvider (using reflection for compilation independence)
            Class<?> fpClass = Class.forName("androidx.core.content.FileProvider");
            java.lang.reflect.Method getUriMethod = fpClass.getMethod("getUriForFile", Context.class, String.class, File.class);
            Uri apkUri = (Uri) getUriMethod.invoke(null, activity, activity.getPackageName() + ".fileprovider", apkFile);

            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(installIntent);

        } catch (Throwable t) {
            Toast.makeText(activity, "Không thể mở bộ cài đặt: " + t.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private static void showLatestDialog(Context context, String version) {
        if (!(context instanceof Activity)) return;
        try {
            new AlertDialog.Builder(context)
                    .setTitle("Cập nhật ứng dụng")
                    .setMessage("Bạn đang sử dụng phiên bản mới nhất (" + version + ").")
                    .setPositiveButton("Đã hiểu", null)
                    .show();
        } catch (Throwable ignored) {}
    }

    private static void showErrorDialog(Context context) {
        if (!(context instanceof Activity)) return;
        try {
            new AlertDialog.Builder(context)
                    .setTitle("Cập nhật ứng dụng")
                    .setMessage("Không thể kết nối đến máy chủ cập nhật. Vui lòng kiểm tra lại kết nối mạng.")
                    .setPositiveButton("Đóng", null)
                    .show();
        } catch (Throwable ignored) {}
    }

    private static String readStream(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();
        return sb.toString().trim();
    }
}
