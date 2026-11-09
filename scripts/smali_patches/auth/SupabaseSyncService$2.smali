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

    .line 93
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
    .registers 10

    .line 97
    const-string v0, "message"

    const-string v1, "error_description"

    const-string v2, "msg"

    :try_start_6
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$serverUrl:Ljava/lang/String;

    invoke-static {v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 98
    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_14

    const-string v3, "https://api.nuvio.tv"

    .line 100
    :cond_14
    invoke-static {v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 102
    new-instance v5, Ljava/net/URL;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, "/auth/v1/token?grant_type=password"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v5, v6}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 103
    invoke-virtual {v5}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v5

    check-cast v5, Ljava/net/HttpURLConnection;

    .line 104
    const-string v6, "POST"

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 105
    const-string v6, "Content-Type"

    const-string v7, "application/json"

    invoke-virtual {v5, v6, v7}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 106
    const-string v6, "apikey"

    invoke-virtual {v5, v6, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 107
    const/16 v6, 0x2710

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 108
    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 109
    const/4 v6, 0x1

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 111
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6}, Lorg/json/JSONObject;-><init>()V

    .line 112
    const-string v7, "email"

    iget-object v8, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$email:Ljava/lang/String;

    invoke-virtual {v8}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v6, v7, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 113
    const-string v7, "password"

    iget-object v8, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$password:Ljava/lang/String;

    invoke-virtual {v6, v7, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 115
    invoke-virtual {v6}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v6

    sget-object v7, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v6, v7}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v6

    .line 116
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v7
    :try_end_78
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_78} :catch_16a

    .line 117
    :try_start_78
    invoke-virtual {v7, v6}, Ljava/io/OutputStream;->write([B)V
    :try_end_7b
    .catchall {:try_start_78 .. :try_end_7b} :catchall_15e

    .line 118
    if-eqz v7, :cond_80

    :try_start_7d
    invoke-virtual {v7}, Ljava/io/OutputStream;->close()V

    .line 120
    :cond_80
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v6

    .line 121
    const/16 v7, 0xc8

    if-lt v6, v7, :cond_91

    const/16 v8, 0x12c

    if-ge v6, v8, :cond_91

    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v5

    goto :goto_95

    :cond_91
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getErrorStream()Ljava/io/InputStream;

    move-result-object v5
    :try_end_95
    .catch Ljava/lang/Exception; {:try_start_7d .. :try_end_95} :catch_16a

    .line 122
    :goto_95
    const-string v8, ""

    if-eqz v5, :cond_9e

    :try_start_99
    invoke-static {v5}, Lcom/streamsync/app/auth/SupabaseSyncService;->-$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v5

    goto :goto_9f

    :cond_9e
    move-object v5, v8

    .line 124
    :goto_9f
    if-ne v6, v7, :cond_10c

    .line 125
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 126
    const-string v1, "access_token"

    invoke-virtual {v0, v1, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 127
    const-string v2, "user"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 128
    if-eqz v0, :cond_ba

    const-string v2, "id"

    invoke-virtual {v0, v2, v8}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 130
    :cond_ba
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$context:Landroid/content/Context;

    const-string v2, "nuvio_supabase_auth"

    const/4 v5, 0x0

    invoke-virtual {v0, v2, v5}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 131
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "server_url"

    .line 132
    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "anon_key"

    .line 133
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_token"

    .line 134
    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_email"

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$email:Ljava/lang/String;

    .line 135
    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_user_id"

    .line 136
    invoke-interface {v0, v2, v8}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "is_anonymous"

    .line 137
    invoke-interface {v0, v2, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "last_login_time"

    .line 138
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-interface {v0, v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 139
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 141
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_10b

    .line 142
    nop

    .line 143
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$2$1;

    invoke-direct {v0, p0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$2$1;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 150
    :cond_10b
    goto :goto_15d

    .line 151
    :cond_10c
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u0110\u0103ng nh\u1eadp th\u1ea5t b\u1ea1i ("

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ")"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3
    :try_end_125
    .catch Ljava/lang/Exception; {:try_start_99 .. :try_end_125} :catch_16a

    .line 153
    :try_start_125
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 154
    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_136

    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_14f

    .line 155
    :cond_136
    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_142

    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_14f

    .line 156
    :cond_142
    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_14f

    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_14c
    .catchall {:try_start_125 .. :try_end_14c} :catchall_14e

    move-object v3, v0

    goto :goto_14f

    .line 157
    :catchall_14e
    move-exception v0

    :cond_14f
    :goto_14f
    nop

    .line 159
    nop

    .line 160
    :try_start_151
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_15d

    .line 161
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;

    invoke-direct {v0, p0, v3}, Lcom/streamsync/app/auth/SupabaseSyncService$2$2;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V
    :try_end_15d
    .catch Ljava/lang/Exception; {:try_start_151 .. :try_end_15d} :catch_16a

    .line 178
    :cond_15d
    :goto_15d
    goto :goto_177

    .line 116
    :catchall_15e
    move-exception v0

    if-eqz v7, :cond_169

    :try_start_161
    invoke-virtual {v7}, Ljava/io/OutputStream;->close()V
    :try_end_164
    .catchall {:try_start_161 .. :try_end_164} :catchall_165

    goto :goto_169

    :catchall_165
    move-exception v1

    :try_start_166
    invoke-static {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_169
    :goto_169
    throw v0
    :try_end_16a
    .catch Ljava/lang/Exception; {:try_start_166 .. :try_end_16a} :catch_16a

    .line 169
    :catch_16a
    move-exception v0

    .line 170
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$2;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v1, :cond_177

    .line 171
    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$2$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$2;Ljava/lang/Exception;)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 179
    :cond_177
    :goto_177
    return-void
.end method
