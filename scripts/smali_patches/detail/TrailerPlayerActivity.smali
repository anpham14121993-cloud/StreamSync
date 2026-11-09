.class public Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;
.super Landroid/app/Activity;
.source "TrailerPlayerActivity.java"


# static fields
.field public static final EXTRA_TRAILER_KEY:Ljava/lang/String; = "trailer_key"


# instance fields
.field private mWebView:Landroid/webkit/WebView;


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 23
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method

.method private dpToPx(I)I
    .registers 3

    .line 124
    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->density:F

    .line 125
    int-to-float p1, p1

    mul-float p1, p1, v0

    const/high16 v0, 0x3f000000    # 0.5f

    add-float/2addr p1, v0

    float-to-int p1, p1

    return p1
.end method

.method private hideSystemUi()V
    .registers 3

    .line 129
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-lt v0, v1, :cond_13

    .line 130
    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    const/16 v1, 0x1706

    invoke-virtual {v0, v1}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 139
    :cond_13
    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .registers 13

    .line 29
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 31
    const/4 v0, 0x6

    invoke-virtual {p0, v0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->setRequestedOrientation(I)V

    .line 32
    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->requestWindowFeature(I)Z

    .line 33
    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    .line 34
    const/high16 v1, -0x1000000

    if-eqz v0, :cond_1e

    .line 35
    const/16 v2, 0x480

    invoke-virtual {v0, v2, v2}, Landroid/view/Window;->setFlags(II)V

    .line 39
    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackgroundColor(I)V

    .line 40
    invoke-direct {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->hideSystemUi()V

    .line 43
    :cond_1e
    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    if-eqz v0, :cond_2f

    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v2, "trailer_key"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    goto :goto_30

    :cond_2f
    const/4 v0, 0x0

    .line 44
    :goto_30
    if-eqz v0, :cond_101

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_3e

    goto/16 :goto_101

    .line 49
    :cond_3e
    new-instance v2, Landroid/widget/FrameLayout;

    invoke-direct {v2, p0}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 50
    invoke-virtual {v2, v1}, Landroid/widget/FrameLayout;->setBackgroundColor(I)V

    .line 51
    new-instance v3, Landroid/view/ViewGroup$LayoutParams;

    const/4 v4, -0x1

    invoke-direct {v3, v4, v4}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {v2, v3}, Landroid/widget/FrameLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 57
    new-instance v3, Landroid/webkit/WebView;

    invoke-direct {v3, p0}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V

    iput-object v3, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    .line 58
    iget-object v3, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v3, v1}, Landroid/webkit/WebView;->setBackgroundColor(I)V

    .line 59
    iget-object v1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    new-instance v3, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v3, v4, v4}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v1, v3}, Landroid/webkit/WebView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 64
    iget-object v1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v1}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v1

    .line 65
    invoke-virtual {v1, p1}, Landroid/webkit/WebSettings;->setJavaScriptEnabled(Z)V

    .line 66
    invoke-virtual {v1, p1}, Landroid/webkit/WebSettings;->setDomStorageEnabled(Z)V

    .line 67
    const/4 v3, 0x0

    invoke-virtual {v1, v3}, Landroid/webkit/WebSettings;->setMediaPlaybackRequiresUserGesture(Z)V

    .line 68
    invoke-virtual {v1, v3}, Landroid/webkit/WebSettings;->setAllowFileAccess(Z)V

    .line 69
    invoke-virtual {v1, v3}, Landroid/webkit/WebSettings;->setAllowContentAccess(Z)V

    .line 70
    invoke-virtual {v1, p1}, Landroid/webkit/WebSettings;->setLoadWithOverviewMode(Z)V

    .line 71
    invoke-virtual {v1, p1}, Landroid/webkit/WebSettings;->setUseWideViewPort(Z)V

    .line 73
    iget-object p1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    new-instance v1, Landroid/webkit/WebChromeClient;

    invoke-direct {v1}, Landroid/webkit/WebChromeClient;-><init>()V

    invoke-virtual {p1, v1}, Landroid/webkit/WebView;->setWebChromeClient(Landroid/webkit/WebChromeClient;)V

    .line 76
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "<!DOCTYPE html>\n<html>\n<head>\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">\n  <style>\n    * { margin:0; padding:0; box-sizing:border-box; }\n    html, body { width:100%; height:100%; background:#000; overflow:hidden; }\n    iframe { position:absolute; top:0; left:0; width:100%; height:100%; border:none; }\n  </style>\n</head>\n<body>\n  <iframe src=\"https://www.youtube-nocookie.com/embed/"

    invoke-virtual {p1, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    const-string v0, "?autoplay=1&controls=1&playsinline=0&rel=0&modestbranding=1&fs=1\"\n          allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; fullscreen\"\n          allowfullscreen></iframe>\n</body>\n</html>"

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    .line 93
    iget-object v5, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    const-string v9, "UTF-8"

    const/4 v10, 0x0

    const-string v6, "https://www.youtube-nocookie.com"

    const-string v8, "text/html"

    invoke-virtual/range {v5 .. v10}, Landroid/webkit/WebView;->loadDataWithBaseURL(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 94
    iget-object p1, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v2, p1}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;)V

    .line 97
    new-instance p1, Landroid/widget/TextView;

    invoke-direct {p1, p0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 98
    const-string v0, "\u2715"

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 99
    invoke-virtual {p1, v4}, Landroid/widget/TextView;->setTextColor(I)V

    .line 100
    const/high16 v0, 0x41a00000    # 20.0f

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setTextSize(F)V

    .line 101
    const/16 v0, 0x11

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setGravity(I)V

    .line 102
    const-string v0, "#80000000"

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setBackgroundColor(I)V

    .line 104
    const/16 v0, 0x2c

    invoke-direct {p0, v0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->dpToPx(I)I

    move-result v0

    .line 105
    const/16 v1, 0x10

    invoke-direct {p0, v1}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->dpToPx(I)I

    move-result v1

    .line 106
    new-instance v3, Landroid/widget/FrameLayout$LayoutParams;

    invoke-direct {v3, v0, v0}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    .line 107
    const v0, 0x800035

    iput v0, v3, Landroid/widget/FrameLayout$LayoutParams;->gravity:I

    .line 108
    iput v1, v3, Landroid/widget/FrameLayout$LayoutParams;->topMargin:I

    .line 109
    iput v1, v3, Landroid/widget/FrameLayout$LayoutParams;->rightMargin:I

    .line 110
    invoke-virtual {p1, v3}, Landroid/widget/TextView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 112
    new-instance v0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity$1;

    invoke-direct {v0, p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity$1;-><init>(Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;)V

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 119
    invoke-virtual {v2, p1}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;)V

    .line 120
    invoke-virtual {p0, v2}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->setContentView(Landroid/view/View;)V

    .line 121
    return-void

    .line 45
    :cond_101
    :goto_101
    invoke-virtual {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->finish()V

    .line 46
    return-void
.end method

.method protected onDestroy()V
    .registers 3

    .line 169
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    if-eqz v0, :cond_18

    .line 170
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    const-string v1, "about:blank"

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 171
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->stopLoading()V

    .line 172
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->destroy()V

    .line 173
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    .line 175
    :cond_18
    invoke-super {p0}, Landroid/app/Activity;->onDestroy()V

    .line 176
    return-void
.end method

.method protected onPause()V
    .registers 2

    .line 151
    invoke-super {p0}, Landroid/app/Activity;->onPause()V

    .line 152
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    if-eqz v0, :cond_11

    .line 153
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->onPause()V

    .line 154
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->pauseTimers()V

    .line 156
    :cond_11
    return-void
.end method

.method protected onResume()V
    .registers 2

    .line 160
    invoke-super {p0}, Landroid/app/Activity;->onResume()V

    .line 161
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    if-eqz v0, :cond_11

    .line 162
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->onResume()V

    .line 163
    iget-object v0, p0, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->mWebView:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->resumeTimers()V

    .line 165
    :cond_11
    return-void
.end method

.method public onWindowFocusChanged(Z)V
    .registers 2

    .line 143
    invoke-super {p0, p1}, Landroid/app/Activity;->onWindowFocusChanged(Z)V

    .line 144
    if-eqz p1, :cond_8

    .line 145
    invoke-direct {p0}, Lcom/streamsync/app/ui/detail/TrailerPlayerActivity;->hideSystemUi()V

    .line 147
    :cond_8
    return-void
.end method
