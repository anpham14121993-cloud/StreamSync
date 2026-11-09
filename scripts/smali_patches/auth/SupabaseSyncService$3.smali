.class Lcom/streamsync/app/auth/SupabaseSyncService$3;
.super Ljava/lang/Object;
.source "SupabaseSyncService.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/streamsync/app/auth/SupabaseSyncService;->signUp(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;)V
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

    .line 184
    iput-object p1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$serverUrl:Ljava/lang/String;

    iput-object p2, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$email:Ljava/lang/String;

    iput-object p3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$password:Ljava/lang/String;

    iput-object p4, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$context:Landroid/content/Context;

    iput-object p5, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 11

    .line 188
    const-string v0, "message"

    const-string v1, "error_description"

    const-string v2, "msg"

    :try_start_6
    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$serverUrl:Ljava/lang/String;

    invoke-static {v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->normalizeUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 189
    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_14

    const-string v3, "https://api.nuvio.tv"

    .line 191
    :cond_14
    invoke-static {v3}, Lcom/streamsync/app/auth/SupabaseSyncService;->fetchDiscoveryKey(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 193
    new-instance v5, Ljava/net/URL;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    const-string v7, "/auth/v1/signup"

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v5, v6}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 194
    invoke-virtual {v5}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v5

    check-cast v5, Ljava/net/HttpURLConnection;

    .line 195
    const-string v6, "POST"

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setRequestMethod(Ljava/lang/String;)V

    .line 196
    const-string v6, "Content-Type"

    const-string v7, "application/json"

    invoke-virtual {v5, v6, v7}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 197
    const-string v6, "apikey"

    invoke-virtual {v5, v6, v4}, Ljava/net/HttpURLConnection;->setRequestProperty(Ljava/lang/String;Ljava/lang/String;)V

    .line 198
    const/16 v6, 0x2710

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setConnectTimeout(I)V

    .line 199
    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setReadTimeout(I)V

    .line 200
    const/4 v6, 0x1

    invoke-virtual {v5, v6}, Ljava/net/HttpURLConnection;->setDoOutput(Z)V

    .line 202
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6}, Lorg/json/JSONObject;-><init>()V

    .line 203
    const-string v7, "email"

    iget-object v8, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$email:Ljava/lang/String;

    invoke-virtual {v8}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v6, v7, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 204
    const-string v7, "password"

    iget-object v8, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$password:Ljava/lang/String;

    invoke-virtual {v6, v7, v8}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 206
    invoke-virtual {v6}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object v6

    sget-object v7, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v6, v7}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v6

    .line 207
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getOutputStream()Ljava/io/OutputStream;

    move-result-object v7
    :try_end_78
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_78} :catch_175

    .line 208
    :try_start_78
    invoke-virtual {v7, v6}, Ljava/io/OutputStream;->write([B)V
    :try_end_7b
    .catchall {:try_start_78 .. :try_end_7b} :catchall_169

    .line 209
    if-eqz v7, :cond_80

    :try_start_7d
    invoke-virtual {v7}, Ljava/io/OutputStream;->close()V

    .line 211
    :cond_80
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getResponseCode()I

    move-result v6

    .line 212
    const/16 v7, 0x12c

    const/16 v8, 0xc8

    if-lt v6, v8, :cond_91

    if-ge v6, v7, :cond_91

    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getInputStream()Ljava/io/InputStream;

    move-result-object v5

    goto :goto_95

    :cond_91
    invoke-virtual {v5}, Ljava/net/HttpURLConnection;->getErrorStream()Ljava/io/InputStream;

    move-result-object v5
    :try_end_95
    .catch Ljava/lang/Exception; {:try_start_7d .. :try_end_95} :catch_175

    .line 213
    :goto_95
    const-string v9, ""

    if-eqz v5, :cond_9e

    :try_start_99
    invoke-static {v5}, Lcom/streamsync/app/auth/SupabaseSyncService;->-$$Nest$smreadStream(Ljava/io/InputStream;)Ljava/lang/String;

    move-result-object v5

    goto :goto_9f

    :cond_9e
    move-object v5, v9

    .line 215
    :goto_9f
    if-lt v6, v8, :cond_117

    if-ge v6, v7, :cond_117

    .line 216
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 217
    const-string v1, "access_token"

    invoke-virtual {v0, v1, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 218
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_10a

    .line 219
    const-string v2, "user"

    invoke-virtual {v0, v2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    .line 220
    if-eqz v0, :cond_c2

    const-string v2, "id"

    invoke-virtual {v0, v2, v9}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    .line 222
    :cond_c2
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$context:Landroid/content/Context;

    const-string v2, "nuvio_supabase_auth"

    const/4 v5, 0x0

    invoke-virtual {v0, v2, v5}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 223
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "server_url"

    .line 224
    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "anon_key"

    .line 225
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_token"

    .line 226
    invoke-interface {v0, v2, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_email"

    iget-object v3, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$email:Ljava/lang/String;

    .line 227
    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "auth_user_id"

    .line 228
    invoke-interface {v0, v2, v9}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v2, "is_anonymous"

    .line 229
    invoke-interface {v0, v2, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 230
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 232
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_109

    .line 233
    nop

    .line 234
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;

    invoke-direct {v0, p0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$3$1;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 241
    :cond_109
    goto :goto_116

    .line 242
    :cond_10a
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_116

    .line 243
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3$2;

    invoke-direct {v0, p0}, Lcom/streamsync/app/auth/SupabaseSyncService$3$2;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 251
    :cond_116
    :goto_116
    goto :goto_168

    .line 252
    :cond_117
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "\u0110\u0103ng k\u00fd th\u1ea5t b\u1ea1i ("

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ")"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3
    :try_end_130
    .catch Ljava/lang/Exception; {:try_start_99 .. :try_end_130} :catch_175

    .line 254
    :try_start_130
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 255
    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_141

    invoke-virtual {v4, v2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_15a

    .line 256
    :cond_141
    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_14d

    invoke-virtual {v4, v1}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    move-object v3, v0

    goto :goto_15a

    .line 257
    :cond_14d
    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_15a

    invoke-virtual {v4, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0
    :try_end_157
    .catchall {:try_start_130 .. :try_end_157} :catchall_159

    move-object v3, v0

    goto :goto_15a

    .line 258
    :catchall_159
    move-exception v0

    :cond_15a
    :goto_15a
    nop

    .line 260
    nop

    .line 261
    :try_start_15c
    iget-object v0, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v0, :cond_168

    .line 262
    new-instance v0, Lcom/streamsync/app/auth/SupabaseSyncService$3$3;

    invoke-direct {v0, p0, v3}, Lcom/streamsync/app/auth/SupabaseSyncService$3$3;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;Ljava/lang/String;)V

    invoke-static {v0}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V
    :try_end_168
    .catch Ljava/lang/Exception; {:try_start_15c .. :try_end_168} :catch_175

    .line 279
    :cond_168
    :goto_168
    goto :goto_182

    .line 207
    :catchall_169
    move-exception v0

    if-eqz v7, :cond_174

    :try_start_16c
    invoke-virtual {v7}, Ljava/io/OutputStream;->close()V
    :try_end_16f
    .catchall {:try_start_16c .. :try_end_16f} :catchall_170

    goto :goto_174

    :catchall_170
    move-exception v1

    :try_start_171
    invoke-static {v0, v1}, Lcom/streamsync/app/auth/SupabaseSyncService$$ExternalSyntheticBackport0;->m(Ljava/lang/Throwable;Ljava/lang/Throwable;)V

    :cond_174
    :goto_174
    throw v0
    :try_end_175
    .catch Ljava/lang/Exception; {:try_start_171 .. :try_end_175} :catch_175

    .line 270
    :catch_175
    move-exception v0

    .line 271
    iget-object v1, p0, Lcom/streamsync/app/auth/SupabaseSyncService$3;->val$callback:Lcom/streamsync/app/auth/SupabaseSyncService$AuthCallback;

    if-eqz v1, :cond_182

    .line 272
    new-instance v1, Lcom/streamsync/app/auth/SupabaseSyncService$3$4;

    invoke-direct {v1, p0, v0}, Lcom/streamsync/app/auth/SupabaseSyncService$3$4;-><init>(Lcom/streamsync/app/auth/SupabaseSyncService$3;Ljava/lang/Exception;)V

    invoke-static {v1}, Lcom/streamsync/app/auth/SupabaseSyncService;->runOnMain(Ljava/lang/Runnable;)V

    .line 280
    :cond_182
    :goto_182
    return-void
.end method
