package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.android.gms.internal.mlkit_common.zzah;
import com.google.android.gms.internal.mlkit_common.zzai;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Tasks;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class OptionalModuleUtils {
    public static final String BARCODE = "barcode";
    public static final String BARCODE_MODULE_ID = "com.google.android.gms.vision.barcode";
    public static final String CUSTOM_ICA = "custom_ica";
    public static final String CUSTOM_ICA_MODULE_ID = "com.google.android.gms.vision.custom.ica";
    public static final String DEPRECATED_DYNAMITE_MODULE_ID = "com.google.android.gms.vision.dynamite";
    public static final String DOCSCAN_CROP_MODULE_ID = "com.google.android.gms.mlkit_docscan_crop";
    public static final String DOCSCAN_DETECT_MODULE_ID = "com.google.android.gms.mlkit_docscan_detect";
    public static final String DOCSCAN_ENHANCE_MODULE_ID = "com.google.android.gms.mlkit_docscan_enhance";
    public static final String DOCSCAN_SHADOW_REMOVAL_MODULE_ID = "com.google.android.gms.mlkit_docscan_shadow";
    public static final String DOCSCAN_STAIN_REMOVAL_MODULE_ID = "com.google.android.gms.mlkit_docscan_stain";
    public static final String FACE = "face";
    public static final String FACE_MODULE_ID = "com.google.android.gms.vision.face";
    public static final String ICA = "ica";
    public static final String ICA_MODULE_ID = "com.google.android.gms.vision.ica";
    public static final String IMAGE_CAPTION_MODULE_ID = "com.google.android.gms.mlkit_image_caption";
    public static final String IMAGE_QUALITY_AESTHETIC_MODULE_ID = "com.google.android.gms.mlkit_quality_aesthetic";
    public static final String IMAGE_QUALITY_TECHNICAL_MODULE_ID = "com.google.android.gms.mlkit_quality_technical";
    public static final String LANGID = "langid";
    public static final String LANGID_MODULE_ID = "com.google.android.gms.mlkit.langid";
    public static final String MLKIT_BARCODE_UI = "barcode_ui";
    public static final String NLCLASSIFIER = "nlclassifier";
    public static final String NLCLASSIFIER_MODULE_ID = "com.google.android.gms.mlkit.nlclassifier";
    public static final String OCR = "ocr";
    public static final String OCR_CHINESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_chinese";
    public static final String OCR_COMMON_MODULE_ID = "com.google.android.gms.mlkit_ocr_common";
    public static final String OCR_DEVANAGARI_MODULE_ID = "com.google.android.gms.mlkit_ocr_devanagari";
    public static final String OCR_JAPANESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_japanese";
    public static final String OCR_KOREAN_MODULE_ID = "com.google.android.gms.mlkit_ocr_korean";
    public static final String OCR_MODULE_ID = "com.google.android.gms.vision.ocr";
    public static final String SMART_REPLY = "smart_reply";
    public static final String SMART_REPLY_MODULE_ID = "com.google.android.gms.mlkit_smartreply";
    public static final String SUBJECT_SEGMENTATION_MODULE_ID = "com.google.android.gms.mlkit_subject_segmentation";
    public static final String TFLITE_DYNAMITE_MODULE_ID = "com.google.android.gms.tflite_dynamite";
    private static final zzai zza;
    private static final zzai zzb;
    public static final Feature[] EMPTY_FEATURES = new Feature[0];
    public static final Feature FEATURE_BARCODE = new Feature("vision.barcode", 1);
    public static final Feature FEATURE_CUSTOM_ICA = new Feature("vision.custom.ica", 1);
    public static final Feature FEATURE_FACE = new Feature("vision.face", 1);
    public static final Feature FEATURE_ICA = new Feature("vision.ica", 1);
    public static final Feature FEATURE_OCR = new Feature("vision.ocr", 1);
    public static final Feature FEATURE_OCR_CHINESE = new Feature("mlkit.ocr.chinese", 1);
    public static final Feature FEATURE_OCR_COMMON = new Feature("mlkit.ocr.common", 1);
    public static final Feature FEATURE_OCR_DEVANAGARI = new Feature("mlkit.ocr.devanagari", 1);
    public static final Feature FEATURE_OCR_JAPANESE = new Feature("mlkit.ocr.japanese", 1);
    public static final Feature FEATURE_OCR_KOREAN = new Feature("mlkit.ocr.korean", 1);
    public static final Feature FEATURE_LANGID = new Feature("mlkit.langid", 1);
    public static final Feature FEATURE_NLCLASSIFIER = new Feature("mlkit.nlclassifier", 1);
    public static final String TFLITE_DYNAMITE = "tflite_dynamite";
    public static final Feature FEATURE_TFLITE_DYNAMITE = new Feature(TFLITE_DYNAMITE, 1);
    public static final Feature FEATURE_MLKIT_BARCODE_UI = new Feature("mlkit.barcode.ui", 1);
    public static final Feature FEATURE_SMART_REPLY = new Feature("mlkit.smartreply", 1);
    public static final Feature FEATURE_IMAGE_CAPTION = new Feature("mlkit.image.caption", 1);
    public static final Feature FEATURE_DOCSCAN_DETECT = new Feature("mlkit.docscan.detect", 1);
    public static final Feature FEATURE_DOCSCAN_CROP = new Feature("mlkit.docscan.crop", 1);
    public static final Feature FEATURE_DOCSCAN_ENHANCE = new Feature("mlkit.docscan.enhance", 1);
    public static final Feature FEATURE_DOCSCAN_UI = new Feature("mlkit.docscan.ui", 1);
    public static final Feature FEATURE_DOCSCAN_STAIN_REMOVAL = new Feature("mlkit.docscan.stain", 1);
    public static final Feature FEATURE_DOCSCAN_SHADOW_REMOVAL = new Feature("mlkit.docscan.shadow", 1);
    public static final Feature FEATURE_IMAGE_QUALITY_AESTHETIC = new Feature("mlkit.quality.aesthetic", 1);
    public static final Feature FEATURE_IMAGE_QUALITY_TECHNICAL = new Feature("mlkit.quality.technical", 1);
    public static final Feature FEATURE_SUBJECT_SEGMENTATION = new Feature("mlkit.segmentation.subject", 1);

    static {
        zzah zzahVar = new zzah();
        zzahVar.zza(BARCODE, FEATURE_BARCODE);
        zzahVar.zza(CUSTOM_ICA, FEATURE_CUSTOM_ICA);
        zzahVar.zza(FACE, FEATURE_FACE);
        zzahVar.zza(ICA, FEATURE_ICA);
        zzahVar.zza(OCR, FEATURE_OCR);
        zzahVar.zza(LANGID, FEATURE_LANGID);
        zzahVar.zza(NLCLASSIFIER, FEATURE_NLCLASSIFIER);
        zzahVar.zza(TFLITE_DYNAMITE, FEATURE_TFLITE_DYNAMITE);
        zzahVar.zza(MLKIT_BARCODE_UI, FEATURE_MLKIT_BARCODE_UI);
        zzahVar.zza(SMART_REPLY, FEATURE_SMART_REPLY);
        zza = zzahVar.zzb();
        zzah zzahVar2 = new zzah();
        zzahVar2.zza(BARCODE_MODULE_ID, FEATURE_BARCODE);
        zzahVar2.zza(CUSTOM_ICA_MODULE_ID, FEATURE_CUSTOM_ICA);
        zzahVar2.zza(FACE_MODULE_ID, FEATURE_FACE);
        zzahVar2.zza(ICA_MODULE_ID, FEATURE_ICA);
        zzahVar2.zza(OCR_MODULE_ID, FEATURE_OCR);
        zzahVar2.zza(LANGID_MODULE_ID, FEATURE_LANGID);
        zzahVar2.zza(NLCLASSIFIER_MODULE_ID, FEATURE_NLCLASSIFIER);
        zzahVar2.zza(TFLITE_DYNAMITE_MODULE_ID, FEATURE_TFLITE_DYNAMITE);
        zzahVar2.zza(SMART_REPLY_MODULE_ID, FEATURE_SMART_REPLY);
        zzb = zzahVar2.zzb();
    }

    private OptionalModuleUtils() {
    }

    @Deprecated
    public static boolean areAllRequiredModulesAvailable(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            return areAllRequiredModulesAvailable(context, zza(zzb, list));
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, it.next());
            }
            return true;
        } catch (DynamiteModule.LoadingException e) {
            return false;
        }
    }

    @Deprecated
    public static void requestDownload(Context context, String moduleName) {
        requestDownload(context, zzaf.zzh(moduleName));
    }

    private static Feature[] zza(Map map, List list) {
        Feature[] featureArr = new Feature[list.size()];
        for (int i = 0; i < list.size(); i++) {
            featureArr[i] = (Feature) Preconditions.checkNotNull((Feature) map.get(list.get(i)));
        }
        return featureArr;
    }

    @Deprecated
    public static void requestDownload(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            requestDownload(context, zza(zza, list));
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }

    public static boolean areAllRequiredModulesAvailable(Context context, final Feature[] features) {
        try {
            return ((ModuleAvailabilityResponse) Tasks.await(ModuleInstall.getClient(context).areModulesAvailable(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzq
                @Override // com.google.android.gms.common.api.OptionalModuleApi
                public final Feature[] getOptionalFeatures() {
                    Feature[] featureArr = OptionalModuleUtils.EMPTY_FEATURES;
                    return features;
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzr
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Log.e("OptionalModuleUtils", "Failed to check feature availability", exc);
                }
            }))).areModulesAvailable();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("OptionalModuleUtils", "Failed to complete the task of features availability check", e);
            return false;
        }
    }

    public static void requestDownload(Context context, final Feature[] features) {
        ModuleInstall.getClient(context).installModules(ModuleInstallRequest.newBuilder().addApi(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzo
            @Override // com.google.android.gms.common.api.OptionalModuleApi
            public final Feature[] getOptionalFeatures() {
                Feature[] featureArr = OptionalModuleUtils.EMPTY_FEATURES;
                return features;
            }
        }).build()).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzp
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
            }
        });
    }
}
