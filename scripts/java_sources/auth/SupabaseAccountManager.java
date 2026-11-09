package com.streamsync.app.auth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SupabaseAccountManager {

    private static final String PREFS_NAME = "nuvio_supabase_auth";

    public static boolean isLoggedIn(Context context) {
        if (context == null) return false;
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.getBoolean("is_anonymous", false)) return true;
        String token = prefs.getString("auth_token", "");
        return !TextUtils.isEmpty(token);
    }

    public static void show(final Context context) {
        if (context == null) return;
        if (isLoggedIn(context)) {
            showLoggedInDialog(context);
        } else {
            showLoginDialog(context);
        }
    }

    private static void showLoggedInDialog(final Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String email = prefs.getString("auth_email", "Tài khoản Nuvio");
        int syncedAddons = prefs.getInt("synced_addons", 0);
        int syncedProgress = prefs.getInt("synced_progress", 0);

        ScrollView scrollView = new ScrollView(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 36, 48, 36);

        TextView tvTitle = new TextView(context);
        tvTitle.setText("TÀI KHOẢN");
        tvTitle.setTextSize(18);
        tvTitle.setTypeface(null, Typeface.BOLD);
        tvTitle.setTextColor(Color.parseColor("#4CAF50"));
        tvTitle.setGravity(Gravity.CENTER);
        layout.addView(tvTitle);

        TextView tvInfo = new TextView(context);
        String info = "\nTrạng thái: Đã đăng nhập\n" +
                "Email: " + email + "\n" +
                "Tự động đồng bộ: Đang bật\n" +
                "Dữ liệu: " + syncedAddons + " tiện ích • " + syncedProgress + " mục xem\n";
        tvInfo.setText(info);
        tvInfo.setTextSize(14);
        tvInfo.setTextColor(Color.WHITE);
        layout.addView(tvInfo);

        Button btnSync = createStyledButton(context, "ĐỒNG BỘ LẠI NGAY", "#00897B");
        layout.addView(btnSync);

        Button btnLogout = createStyledButton(context, "Đăng xuất", "#D32F2F");
        layout.addView(btnLogout);

        scrollView.addView(layout);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setView(scrollView);
        builder.setNegativeButton("Đóng", null);
        final AlertDialog dialog = builder.create();

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Đang đồng bộ...", Toast.LENGTH_SHORT).show();
                SupabaseSyncService.syncLive(context, new SupabaseSyncService.SyncCallback() {
                    @Override
                    public void onSuccess(int addonsCount, int progressCount) {
                        dialog.dismiss();
                        showLoggedInDialog(context);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(context, "Lỗi đồng bộ: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().clear().apply();
                Toast.makeText(context, "Đã đăng xuất tài khoản", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                showLoginDialog(context);
            }
        });

        if (syncedAddons == 0 && syncedProgress == 0) {
            SupabaseSyncService.syncLive(context, null);
        }

        dialog.show();
    }

    private static void showLoginDialog(final Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedEmail = prefs.getString("auth_email", "");

        ScrollView scrollView = new ScrollView(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(48, 36, 48, 36);

        TextView tvTitle = new TextView(context);
        tvTitle.setText("TÀI KHOẢN");
        tvTitle.setTextSize(18);
        tvTitle.setTypeface(null, Typeface.BOLD);
        tvTitle.setTextColor(Color.parseColor("#4CAF50"));
        tvTitle.setGravity(Gravity.CENTER);
        layout.addView(tvTitle);

        TextView tvDesc = new TextView(context);
        tvDesc.setText("\nĐăng nhập tài khoản Nuvio để tự động đồng bộ tiện ích và tiến độ xem.\n");
        tvDesc.setTextSize(13);
        tvDesc.setTextColor(Color.parseColor("#B0BEC5"));
        layout.addView(tvDesc);

        TextView lblEmail = new TextView(context);
        lblEmail.setText("Email:");
        lblEmail.setTextColor(Color.LTGRAY);
        lblEmail.setTextSize(12);
        layout.addView(lblEmail);

        final EditText etEmail = new EditText(context);
        etEmail.setHint("email@example.com");
        etEmail.setText(savedEmail);
        etEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etEmail.setTextColor(Color.WHITE);
        etEmail.setSingleLine(true);
        layout.addView(etEmail);

        TextView lblPass = new TextView(context);
        lblPass.setText("Mật khẩu:");
        lblPass.setTextColor(Color.LTGRAY);
        lblPass.setTextSize(12);
        layout.addView(lblPass);

        final EditText etPassword = new EditText(context);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setTextColor(Color.WHITE);
        etPassword.setSingleLine(true);
        layout.addView(etPassword);

        Button btnLogin = createStyledButton(context, "ĐĂNG NHẬP", "#4CAF50");
        layout.addView(btnLogin);

        Button btnQrLogin = createStyledButton(context, "Đăng nhập bằng mã QR / Liên kết", "#00897B");
        layout.addView(btnQrLogin);

        scrollView.addView(layout);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setView(scrollView);
        builder.setNegativeButton("Hủy", null);
        final AlertDialog dialog = builder.create();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString().trim();
                final String pass = etPassword.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập Email và Mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context, "Đang đăng nhập...", Toast.LENGTH_SHORT).show();
                SupabaseSyncService.login(context, SupabaseSyncService.DEFAULT_SERVER_URL, email, pass, new SupabaseSyncService.AuthCallback() {
                    @Override
                    public void onSuccess(String email, String token) {
                        dialog.dismiss();
                        // Automatically sync silently upon login
                        SupabaseSyncService.syncLive(context, new SupabaseSyncService.SyncCallback() {
                            @Override
                            public void onSuccess(int addonsCount, int progressCount) {
                                showLoggedInDialog(context);
                            }

                            @Override
                            public void onError(String message) {
                                showLoggedInDialog(context);
                            }
                        });
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(context, "Lỗi đăng nhập: " + message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnQrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDeviceLinkDialog(context);
            }
        });

        dialog.show();
    }

    private static void showDeviceLinkDialog(final Context context) {
        Toast.makeText(context, "Đang khởi tạo liên kết thiết bị...", Toast.LENGTH_SHORT).show();

        SupabaseSyncService.startDeviceLink(context, new SupabaseSyncService.DeviceLinkStartCallback() {
            @Override
            public void onSuccess(final String userCode, final String deviceCode, final String verificationUrl, final String nonce, final int pollInterval) {
                final boolean[] isCancelled = new boolean[]{false};

                ScrollView scrollView = new ScrollView(context);
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(40, 32, 40, 32);
                layout.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView tvTitle = new TextView(context);
                tvTitle.setText("LIÊN KẾT THIẾT BỊ NUVIO");
                tvTitle.setTextSize(18);
                tvTitle.setTypeface(null, Typeface.BOLD);
                tvTitle.setTextColor(Color.parseColor("#4CAF50"));
                tvTitle.setGravity(Gravity.CENTER);
                layout.addView(tvTitle);

                TextView tvInstructions = new TextView(context);
                tvInstructions.setText("\n1. Quét mã QR bên dưới hoặc truy cập: nuvio.tv/link\n2. Nhập mã liên kết để hoàn tất đăng nhập:\n");
                tvInstructions.setTextSize(13);
                tvInstructions.setTextColor(Color.LTGRAY);
                tvInstructions.setGravity(Gravity.CENTER);
                layout.addView(tvInstructions);

                // Code display
                TextView tvCode = new TextView(context);
                tvCode.setText(userCode);
                tvCode.setTextSize(28);
                tvCode.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
                tvCode.setTextColor(Color.parseColor("#FFD54F"));
                tvCode.setGravity(Gravity.CENTER);
                tvCode.setBackgroundColor(Color.parseColor("#263238"));
                tvCode.setPadding(24, 16, 24, 16);
                layout.addView(tvCode);

                // QR Code Image
                final ImageView ivQr = new ImageView(context);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(400, 400);
                ivParams.gravity = Gravity.CENTER_HORIZONTAL;
                ivParams.topMargin = 24;
                ivParams.bottomMargin = 24;
                ivQr.setLayoutParams(ivParams);
                ivQr.setBackgroundColor(Color.WHITE);
                layout.addView(ivQr);

                // Load QR image in background
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String qrApi = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + URLEncoder.encode(verificationUrl, "UTF-8");
                            URL u = new URL(qrApi);
                            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                            conn.setConnectTimeout(8000);
                            conn.setReadTimeout(8000);
                            if (conn.getResponseCode() == 200) {
                                InputStream is = conn.getInputStream();
                                final Bitmap bmp = BitmapFactory.decodeStream(is);
                                is.close();
                                SupabaseSyncService.runOnMain(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!isCancelled[0] && bmp != null) {
                                            ivQr.setImageBitmap(bmp);
                                        }
                                    }
                                });
                            }
                        } catch (Throwable ignored) {}
                    }
                }).start();

                // Button: Open link in browser
                Button btnOpenLink = createStyledButton(context, "Mở trình duyệt (nuvio.tv/link)", "#1976D2");
                btnOpenLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(verificationUrl));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } catch (Throwable t) {
                            Toast.makeText(context, "Không thể mở trình duyệt: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                layout.addView(btnOpenLink);

                scrollView.addView(layout);

                AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setView(scrollView);
                builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {
                        isCancelled[0] = true;
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface d) {
                        isCancelled[0] = true;
                    }
                });
                final AlertDialog qrDialog = builder.create();

                // Background Polling Thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int attempts = 0;
                        int sleepMs = Math.max(2000, pollInterval * 1000);
                        while (!isCancelled[0] && attempts < 100) {
                            try {
                                Thread.sleep(sleepMs);
                            } catch (InterruptedException e) {
                                break;
                            }
                            if (isCancelled[0]) break;

                            attempts++;
                            String status = SupabaseSyncService.pollDeviceStatus(deviceCode, nonce);
                            if ("approved".equals(status)) {
                                boolean exchanged = SupabaseSyncService.exchangeAndSaveSession(context, deviceCode, nonce);
                                if (exchanged) {
                                    SupabaseSyncService.runOnMain(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (!isCancelled[0]) {
                                                qrDialog.dismiss();
                                                Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                                // Trigger auto sync
                                                SupabaseSyncService.syncLive(context, new SupabaseSyncService.SyncCallback() {
                                                    @Override
                                                    public void onSuccess(int addonsCount, int progressCount) {
                                                        showLoggedInDialog(context);
                                                    }

                                                    @Override
                                                    public void onError(String message) {
                                                        showLoggedInDialog(context);
                                                    }
                                                });
                                            }
                                        }
                                    });
                                    break;
                                }
                            } else if ("expired".equals(status)) {
                                SupabaseSyncService.runOnMain(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!isCancelled[0]) {
                                            qrDialog.dismiss();
                                            Toast.makeText(context, "Mã liên kết đã hết hạn", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;
                            }
                        }
                    }
                }).start();

                qrDialog.show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, "Lỗi tạo phiên liên kết: " + message, Toast.LENGTH_LONG).show();
                showLoginDialog(context);
            }
        });
    }

    private static Button createStyledButton(Context context, String text, String hexColor) {
        Button btn = new Button(context);
        btn.setText(text);
        btn.setTextColor(Color.WHITE);
        btn.setTextSize(13);
        btn.setBackgroundColor(Color.parseColor(hexColor));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(0, 16, 0, 0);
        btn.setLayoutParams(lp);
        return btn;
    }
}
