package com.streamsync.app.ui.player;

import com.google.common.net.HttpHeaders;
import com.streamsync.app.settings.BufferCeiling;
import com.streamsync.app.subtitle.SubtitleParser;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PlayerScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
@DebugMetadata(c = "com.streamsync.app.ui.player.PlayerViewModel$fetchSubtitleText$2", f = "PlayerScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class PlayerViewModel$fetchSubtitleText$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $languageHint;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$fetchSubtitleText$2(PlayerViewModel playerViewModel, String str, String str2, Continuation<? super PlayerViewModel$fetchSubtitleText$2> continuation) {
        super(2, continuation);
        this.this$0 = playerViewModel;
        this.$url = str;
        this.$languageHint = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerViewModel$fetchSubtitleText$2(this.this$0, this.$url, this.$languageHint, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((PlayerViewModel$fetchSubtitleText$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OkHttpClient subtitleHttpClient;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                subtitleHttpClient = this.this$0.getSubtitleHttpClient();
                Response response = subtitleHttpClient.newCall(new Request.Builder().url(this.$url).header(HttpHeaders.USER_AGENT, "StreamSync/1.0 (Android)").header(HttpHeaders.ACCEPT, "*/*").build()).execute();
                Response response2 = response;
                String str = this.$languageHint;
                try {
                    Response it = response2;
                    if (!it.isSuccessful()) {
                        throw new IllegalStateException("HTTP " + it.code());
                    }
                    ResponseBody body = it.body();
                    if (body == null) {
                        CloseableKt.closeFinally(response2, null);
                        return "";
                    }
                    byte[] bytes = body.bytes();
                    String decode = SubtitleParser.INSTANCE.decode(bytes, Response.header$default(it, HttpHeaders.CONTENT_TYPE, null, 2, null), str);
                    CloseableKt.closeFinally(response2, null);
                    return decode;
                } finally {
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
