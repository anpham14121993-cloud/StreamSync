.class Lcom/streamsync/app/auth/SupabaseSyncService$2;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService;->login(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$email:Ljava/lang/String;

.field final synthetic val$password:Ljava/lang/String;

.field final synthetic val$serverUrl:Ljava/lang/String;


# direct methods
.method constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
    .registers 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 100
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$serverUrl:Ljava/lang/String;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$email:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$password:Ljava/lang/String;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$context:Landroid/content/Context;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 11

    .line 104
    const-string v0, "message"

    const-string v1, "error_description"

    const-string v2, "msg"

    const-string v3, "refresh_token"

    :try_start_8
    iget-object v4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$serverUrl:Ljava/lang/String;

    invoke-static {v4}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 105
    invoke-virtual {v4}, Ljava/lang/String;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_16

    const-string v4, "https://api.nuvio.tv"

    .line 107
    :cond_16
    invoke-static {v4}, Lcom/streamsync/app/auth/SupabaseSyncService;->fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    .line 109
    new-instance v6, Ljava/net/URL;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, "/auth/v1/token?grant_type=password"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v6, v7}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 110
    invoke-virtual {v6}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v6

    check-cast v6, Ljava/net/HttpURLConnection;

    .line 111
    const-string v7, "POST"

    invoke-virtual {v6, v7}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 112
    const-string v7, "Content-Type"

    const-string v8, "application/json"

    invoke-virtual {v6, v7, v8}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 113
    const-string v7, "apikey"

    invoke-virtual {v6, v7, v5}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 114
    const/16 v7, 0x2710

    invoke-virtual {v6, v7}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 115
    invoke-virtual {v6, v7}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 116
    const/4 v7, 0x1

    invoke-virtual {v6, v7}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 118
    new-instance v7, Lorg/json/JSONObject;

    invoke-direct {v7}, Lorg/json/JSONObject;-><init>()V

    .line 119
    const-string v8, "email"

    iget-object v9, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$email:Ljava/lang/String;

    invoke-virtual {v9}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v7, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 120
    const-string v8, "password"

    iget-object v9, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$password:Ljava/lang/String;

    invoke-virtual {v7, v8, v9}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 122
    invoke-virtual {v7}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v7

    sget-object v8, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v7, v8}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v7

    .line 123
    invoke-virtual {v6}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v8
    :try_end_7a
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_7a} :catch_174

    .line 124
    :try_start_7a
    invoke-virtual {v8, v7}, Ljava/io/OutputStream;->write([B)V
    :try_end_7d
    .catchall {:try_start_7a .. :try_end_7d} :catchall_168

    .line 125
    if-eqz v8, :cond_82

    :try_start_7f
    invoke-virtual {v8}, Ljava/io/OutputStream;->close()V

    .line 127
    :cond_82
    invoke-virtual {v6}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v7

    .line 128
    const/16 v8, 0xc8

    if-lt v7, v8, :cond_93

    const/16 v9, 0x12c

    if-ge v7, v9, :cond_93

    invoke-virtual {v6}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v6

    goto :goto_97

    :cond_93
    invoke-virtual {v6}, Ljava/net/HttpURLConnection;->getErrorStream()Ljava/io/InputStream;

    move-result-object v6
    :try_end_97
    .catch Ljava/lang/Exception; {:try_start_7f .. :try_end_97} :catch_174

    .line 129
    :goto_97
    const-string v9, ""

    if-eqz v6, :cond_a0

    :try_start_9b
    invoke-static {v6}, Lcom/streamsync/app/auth/SupabaseSyncService;->-$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v6

    goto :goto_a1

    :cond_a0
    move-object v6, v9

    .line 131
    :goto_a1
    if-ne v7, v8, :cond_116

    .line 132
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v6}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 133
    const-string v1, "access_token"

    invoke-virtual {v0, v1, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 134
    invoke-virtual {v0, v3, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 135
    const-string v6, "user"

    invoke-virtual {v0, v6}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 136
    if-eqz v0, :cond_c0

    const-string v6, "id"

    invoke-virtual {v0, v6, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 138
    :cond_c0
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$context:Landroid/content/Context;

    const-string v6, "nuvio_supabase_auth"

    const/4 v7, 0x0

    invoke-virtual {v0, v6, v7}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 139
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v6, "server_url"

    .line 140
    invoke-interface {v0, v6, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v4, "anon_key"

    .line 141
    invoke-interface {v0, v4, v5}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v4, "auth_token"

    .line 142
    invoke-interface {v0, v4, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 143
    invoke-interface {v0, v3, v2}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_email"

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$email:Ljava/lang/String;

    .line 144
    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_user_id"

    .line 145
    invoke-interface {v0, v2, v9}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "is_anonymous"

    .line 146
    invoke-interface {v0, v2, v7}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "last_login_time"

    .line 147
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-interface {v0, v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 148
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 150
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_115

    .line 151
    nop

    .line 152
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$2$1;

    invoke-direct {v0, p0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$2$1;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 159
    :cond_115
    goto :goto_167

    .line 160
    :cond_116
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u0110\u0103ng nh\u1eadp th\u1ea5t b\u1ea1i ("

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ")"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3
    :try_end_12f
    .catch Ljava/lang/Exception; {:try_start_9b .. :try_end_12f} :catch_174

    .line 162
    :try_start_12f
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v6}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 163
    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_140

    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_159

    .line 164
    :cond_140
    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_14c

    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_159

    .line 165
    :cond_14c
    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_159

    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_156
    .catchall {:try_start_12f .. :try_end_156} :catchall_158

    move-object v3, v0

    goto :goto_159

    .line 166
    :catchall_158
    move-exception v0

    :cond_159
    :goto_159
    nop

    .line 168
    nop

    .line 169
    :try_start_15b
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_167

    .line 170
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;

    invoke-direct {v0, p0, v3}, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V
    :try_end_167
    .catch Ljava/lang/Exception; {:try_start_15b .. :try_end_167} :catch_174

    .line 187
    :cond_167
    :goto_167
    goto :goto_181

    .line 123
    :catchall_168
    move-exception v0

    if-eqz v8, :cond_173

    :try_start_16b
    invoke-virtual {v8}, Ljava/io/OutputStream;->close()V
    :try_end_16e
    .catchall {:try_start_16b .. :try_end_16e} :catchall_16f

    goto :goto_173

    :catchall_16f
    move-exception v1

    :try_start_170
    invoke-static {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_173
    :goto_173
    throw v0
    :try_end_174
    .catch Ljava/lang/Exception; {:try_start_170 .. :try_end_174} :catch_174

    .line 178
    :catch_174
    move-exception v0

    .line 179
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v1, :cond_181

    .line 180
    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/Exception;)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 188
    :cond_181
    :goto_181
    return-void
.end method
