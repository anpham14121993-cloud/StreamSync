package com.google.mlkit.nl.translate;

import android.content.Context;
import com.google.android.gms.internal.mlkit_translate.zzt;
import com.google.android.gms.internal.mlkit_translate.zzvy;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.common.sdkinternal.CloseGuard;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.nl.translate.internal.TranslatorImpl;
import com.google.mlkit.nl.translate.internal.zzad;
import com.google.mlkit.nl.translate.internal.zzae;
import com.google.mlkit.nl.translate.internal.zzam;
import com.google.mlkit.nl.translate.internal.zzp;
import com.google.mlkit.nl.translate.internal.zzq;
import com.google.mlkit.nl.translate.internal.zzy;
import com.google.mlkit.nl.translate.internal.zzz;
import java.util.List;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes25.dex */
public class NaturalLanguageTranslateRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        return zzt.zzm(Component.builder(zzam.class).add(Dependency.required(zzz.class)).add(Dependency.required(zzp.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zza
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = NaturalLanguageTranslateRegistrar.zza;
                return new zzam((zzz) componentContainer.get(zzz.class), (zzp) componentContainer.get(zzp.class));
            }
        }).build(), Component.intoSetBuilder(RemoteModelManager.RemoteModelManagerRegistration.class).add(Dependency.requiredProvider(zzam.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzb
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = NaturalLanguageTranslateRegistrar.zza;
                return new RemoteModelManager.RemoteModelManagerRegistration(TranslateRemoteModel.class, componentContainer.getProvider(zzam.class));
            }
        }).build(), Component.builder(zzp.class).add(Dependency.required(Context.class)).add(Dependency.required(ModelFileHelper.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzc
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = NaturalLanguageTranslateRegistrar.zza;
                zzp zzpVar = new zzp((Context) componentContainer.get(Context.class), (ModelFileHelper) componentContainer.get(ModelFileHelper.class));
                zzpVar.start();
                return zzpVar;
            }
        }).alwaysEager().build(), Component.builder(com.google.mlkit.nl.translate.internal.zzi.class).add(Dependency.required(zzae.class)).add(Dependency.required(ModelFileHelper.class)).add(Dependency.required(zzq.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzd
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                int i = NaturalLanguageTranslateRegistrar.zza;
                return new com.google.mlkit.nl.translate.internal.zzi((zzae) componentContainer.get(zzae.class), (ModelFileHelper) componentContainer.get(ModelFileHelper.class), (zzq) componentContainer.get(zzq.class));
            }
        }).build(), Component.builder(TranslatorImpl.Factory.class).add(Dependency.requiredProvider(zzz.class)).add(Dependency.required(com.google.mlkit.nl.translate.internal.zzi.class)).add(Dependency.required(zzq.class)).add(Dependency.required(zzae.class)).add(Dependency.required(ExecutorSelector.class)).add(Dependency.required(zzp.class)).add(Dependency.required(CloseGuard.Factory.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zze
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new TranslatorImpl.Factory(componentContainer.getProvider(zzz.class), (com.google.mlkit.nl.translate.internal.zzi) componentContainer.get(com.google.mlkit.nl.translate.internal.zzi.class), (zzq) componentContainer.get(zzq.class), (zzae) componentContainer.get(zzae.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class), (zzp) componentContainer.get(zzp.class), (CloseGuard.Factory) componentContainer.get(CloseGuard.Factory.class));
            }
        }).build(), Component.builder(zzq.class).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzf
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzq();
            }
        }).build(), Component.builder(zzae.class).add(Dependency.required(Context.class)).add(Dependency.required(zzq.class)).add(Dependency.required(ModelFileHelper.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzg
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzae(zzvy.zze((Context) componentContainer.get(Context.class)), new zzad(zzvy.zze((Context) componentContainer.get(Context.class))), (zzq) componentContainer.get(zzq.class), (ModelFileHelper) componentContainer.get(ModelFileHelper.class));
            }
        }).build(), Component.builder(zzy.class).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzh
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzy();
            }
        }).build(), Component.builder(com.google.mlkit.nl.translate.internal.zzg.class).add(Dependency.required(MlKitContext.class)).add(Dependency.required(Context.class)).add(Dependency.required(zzq.class)).add(Dependency.required(zzae.class)).add(Dependency.required(ModelFileHelper.class)).add(Dependency.required(SharedPrefManager.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzi
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new com.google.mlkit.nl.translate.internal.zzg((MlKitContext) componentContainer.get(MlKitContext.class), (Context) componentContainer.get(Context.class), (zzq) componentContainer.get(zzq.class), (zzae) componentContainer.get(zzae.class), (ModelFileHelper) componentContainer.get(ModelFileHelper.class), (SharedPrefManager) componentContainer.get(SharedPrefManager.class));
            }
        }).build(), Component.builder(zzz.class).add(Dependency.required(com.google.mlkit.nl.translate.internal.zzg.class)).add(Dependency.required(zzy.class)).factory(new ComponentFactory() { // from class: com.google.mlkit.nl.translate.zzj
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return new zzz((zzy) componentContainer.get(zzy.class), (com.google.mlkit.nl.translate.internal.zzg) componentContainer.get(com.google.mlkit.nl.translate.internal.zzg.class));
            }
        }).build());
    }
}
