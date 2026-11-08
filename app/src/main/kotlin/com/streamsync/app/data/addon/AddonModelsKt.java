package com.streamsync.app.data.addon;

import androidx.autofill.HintConstants;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AddonModels.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\u001a0\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002\u001a\"\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u001a\u0012\u0010\u0011\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0001\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u000b*\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000b\u001a)\u0010\u0017\u001a\u0004\u0018\u00010\u000b*\u00020\t2\u0016\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\u0019\"\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u001a\u001a\u0012\u0010\u001b\u001a\u00020\b*\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000b\u001a\u0012\u0010\u001d\u001a\u00020\b*\u00020\t2\u0006\u0010\r\u001a\u00020\u000b\u001a\u0012\u0010\u001e\u001a\u00020\b*\u00020\t2\u0006\u0010\f\u001a\u00020\u000b\u001a\u001a\u0010\u001f\u001a\u00020\b*\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"DEFAULT_SKIP_STEP", "", "mergeLinks", "", "Lcom/streamsync/app/data/addon/AddonMetaLink;", TtmlNode.RUBY_BASE, "overrides", "acceptsId", "", "Lcom/streamsync/app/data/addon/AddonManifest;", "resource", "", "type", "id", "mergedWith", "Lcom/streamsync/app/data/addon/AddonMeta;", "other", "provides", "skipStepOrDefault", "Lcom/streamsync/app/data/addon/AddonCatalogDef;", "defaultStep", "streamResourceSkipReason", "videoId", "supportedCandidateType", "types", "", "(Lcom/streamsync/app/data/addon/AddonManifest;[Ljava/lang/String;)Ljava/lang/String;", "supportsExtra", HintConstants.AUTOFILL_HINT_NAME, "supportsMetaId", "supportsMetaType", "supportsStreamResource", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonModelsKt {
    public static final int DEFAULT_SKIP_STEP = 100;

    public static final boolean provides(AddonManifest $this$provides, String resource) {
        Intrinsics.checkNotNullParameter($this$provides, "<this>");
        Intrinsics.checkNotNullParameter(resource, "resource");
        List declared = $this$provides.getResources();
        if (declared == null) {
            declared = CollectionsKt.emptyList();
        }
        if (declared.isEmpty()) {
            return true;
        }
        Iterable $this$any$iv = declared;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            AddonResourceDef it = (AddonResourceDef) element$iv;
            if (StringsKt.equals(it.getName(), resource, true)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean acceptsId(AddonManifest $this$acceptsId, String resource, String type, String id) {
        Object element$iv;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter($this$acceptsId, "<this>");
        Intrinsics.checkNotNullParameter(resource, "resource");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(id, "id");
        boolean hardTypeFilter = !StringsKt.equals(resource, "stream", true);
        if (hardTypeFilter) {
            List<String> types = $this$acceptsId.getTypes();
            if (types == null) {
                types = CollectionsKt.emptyList();
            }
            if (!types.isEmpty()) {
                List<String> types2 = $this$acceptsId.getTypes();
                if (types2 == null) {
                    types2 = CollectionsKt.emptyList();
                }
                if (!types2.contains(type)) {
                    return false;
                }
            }
        }
        Iterable resources = $this$acceptsId.getResources();
        if (resources == null) {
            resources = CollectionsKt.emptyList();
        }
        Iterable $this$firstOrNull$iv = resources;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                AddonResourceDef it2 = (AddonResourceDef) element$iv;
                if (StringsKt.equals(it2.getName(), resource, true)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        AddonResourceDef def = (AddonResourceDef) element$iv;
        if (def != null) {
            if (hardTypeFilter && (!def.getTypes().isEmpty()) && !def.getTypes().contains(type)) {
                return false;
            }
            List idPrefixes = def.getIdPrefixes();
            if (idPrefixes.isEmpty()) {
                List idPrefixes2 = $this$acceptsId.getIdPrefixes();
                if (idPrefixes2 == null) {
                    idPrefixes2 = CollectionsKt.emptyList();
                }
                idPrefixes = idPrefixes2;
            }
            List prefixes = idPrefixes;
            if (!prefixes.isEmpty()) {
                List $this$none$iv = prefixes;
                if (!($this$none$iv instanceof Collection) || !$this$none$iv.isEmpty()) {
                    Iterator it3 = $this$none$iv.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            Object element$iv2 = it3.next();
                            String it4 = (String) element$iv2;
                            if (StringsKt.startsWith$default(id, it4, false, 2, (Object) null)) {
                                z2 = false;
                                break;
                            }
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = true;
                }
                if (z2) {
                    return false;
                }
            }
        } else {
            List prefixes2 = $this$acceptsId.getIdPrefixes();
            if (prefixes2 == null) {
                prefixes2 = CollectionsKt.emptyList();
            }
            if (!prefixes2.isEmpty()) {
                List $this$none$iv2 = prefixes2;
                if (!($this$none$iv2 instanceof Collection) || !$this$none$iv2.isEmpty()) {
                    Iterator it5 = $this$none$iv2.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            Object element$iv3 = it5.next();
                            String it6 = (String) element$iv3;
                            if (StringsKt.startsWith$default(id, it6, false, 2, (Object) null)) {
                                z = false;
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = true;
                }
                if (z) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final boolean supportsMetaType(AddonManifest $this$supportsMetaType, String type) {
        Object element$iv;
        Iterable $this$any$iv;
        Intrinsics.checkNotNullParameter($this$supportsMetaType, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        String wanted = type.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(wanted, "toLowerCase(...)");
        Iterable resources = $this$supportsMetaType.getResources();
        if (resources == null) {
            resources = CollectionsKt.emptyList();
        }
        Iterable $this$firstOrNull$iv = resources;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                AddonResourceDef it2 = (AddonResourceDef) element$iv;
                if (StringsKt.equals(it2.getName(), "meta", true)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        AddonResourceDef def = (AddonResourceDef) element$iv;
        if (def != null) {
            if (!(!def.getTypes().isEmpty())) {
                return true;
            }
            Iterable $this$map$iv = def.getTypes();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it3 = (String) item$iv$iv;
                String lowerCase = it3.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                destination$iv$iv.add(lowerCase);
            }
            return ((List) destination$iv$iv).contains(wanted);
        }
        Iterable resources2 = $this$supportsMetaType.getResources();
        if (resources2 == null) {
            resources2 = CollectionsKt.emptyList();
        }
        Iterable $this$any$iv2 = resources2;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it4 = $this$any$iv2.iterator();
            while (true) {
                if (it4.hasNext()) {
                    Object element$iv2 = it4.next();
                    AddonResourceDef it5 = (AddonResourceDef) element$iv2;
                    if (StringsKt.equals(it5.getName(), "meta", true)) {
                        $this$any$iv = 1;
                        break;
                    }
                } else {
                    $this$any$iv = null;
                    break;
                }
            }
        } else {
            $this$any$iv = null;
        }
        if ($this$any$iv != null) {
            return true;
        }
        List<String> types = $this$supportsMetaType.getTypes();
        if (types == null) {
            types = CollectionsKt.emptyList();
        }
        if (!types.isEmpty()) {
            Iterable types2 = $this$supportsMetaType.getTypes();
            if (types2 == null) {
                types2 = CollectionsKt.emptyList();
            }
            Iterable $this$map$iv2 = types2;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
            for (Object item$iv$iv2 : $this$map$iv2) {
                String it6 = (String) item$iv$iv2;
                String lowerCase2 = it6.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                destination$iv$iv2.add(lowerCase2);
            }
            if (!((List) destination$iv$iv2).contains(wanted)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x005c, code lost:
    
        if (r2 == null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean supportsMetaId(com.streamsync.app.data.addon.AddonManifest r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r0 = r13
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            r1 = 1
            if (r0 == 0) goto L15
            return r1
        L15:
            java.util.List r0 = r12.getResources()
            if (r0 != 0) goto L1f
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L1f:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r2 = 0
            java.util.Iterator r3 = r0.iterator()
        L26:
            boolean r4 = r3.hasNext()
            r5 = 0
            if (r4 == 0) goto L42
            java.lang.Object r4 = r3.next()
            r6 = r4
            com.streamsync.app.data.addon.AddonResourceDef r6 = (com.streamsync.app.data.addon.AddonResourceDef) r6
            r7 = 0
            java.lang.String r8 = r6.getName()
            java.lang.String r9 = "meta"
            boolean r6 = kotlin.text.StringsKt.equals(r8, r9, r1)
            if (r6 == 0) goto L26
            goto L43
        L42:
            r4 = r5
        L43:
            r0 = r4
            com.streamsync.app.data.addon.AddonResourceDef r0 = (com.streamsync.app.data.addon.AddonResourceDef) r0
            if (r0 == 0) goto L5e
            java.util.List r2 = r0.getIdPrefixes()
            if (r2 == 0) goto L5e
            r3 = r2
            r4 = 0
            r6 = r3
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r1
            if (r6 == 0) goto L5b
            goto L5c
        L5b:
            r2 = r5
        L5c:
            if (r2 != 0) goto L79
        L5e:
            java.util.List r2 = r12.getIdPrefixes()
            if (r2 != 0) goto L68
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L68:
            r3 = r2
            r4 = 0
            r6 = r3
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r1
            if (r6 == 0) goto L75
            goto L76
        L75:
            r2 = r5
        L76:
            if (r2 != 0) goto L79
            return r1
        L79:
            r3 = r2
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            boolean r6 = r3 instanceof java.util.Collection
            r7 = 0
            if (r6 == 0) goto L8d
            r6 = r3
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L8d
            r1 = r7
            goto La8
        L8d:
            java.util.Iterator r6 = r3.iterator()
        L91:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto La7
            java.lang.Object r8 = r6.next()
            r9 = r8
            java.lang.String r9 = (java.lang.String) r9
            r10 = 0
            r11 = 2
            boolean r9 = kotlin.text.StringsKt.startsWith$default(r13, r9, r7, r11, r5)
            if (r9 == 0) goto L91
            goto La8
        La7:
            r1 = r7
        La8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonModelsKt.supportsMetaId(com.streamsync.app.data.addon.AddonManifest, java.lang.String):boolean");
    }

    public static final boolean supportsStreamResource(AddonManifest $this$supportsStreamResource, String type, String videoId) {
        Intrinsics.checkNotNullParameter($this$supportsStreamResource, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        return streamResourceSkipReason($this$supportsStreamResource, videoId) == null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        if (r1 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String streamResourceSkipReason(com.streamsync.app.data.addon.AddonManifest r12, java.lang.String r13) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "videoId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.util.List r0 = r12.getResources()
            if (r0 != 0) goto L14
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L14:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r1 = 0
            java.util.Iterator r2 = r0.iterator()
        L1b:
            boolean r3 = r2.hasNext()
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L38
            java.lang.Object r3 = r2.next()
            r6 = r3
            com.streamsync.app.data.addon.AddonResourceDef r6 = (com.streamsync.app.data.addon.AddonResourceDef) r6
            r7 = 0
            java.lang.String r8 = r6.getName()
            java.lang.String r9 = "stream"
            boolean r6 = kotlin.text.StringsKt.equals(r8, r9, r4)
            if (r6 == 0) goto L1b
            goto L39
        L38:
            r3 = r5
        L39:
            r0 = r3
            com.streamsync.app.data.addon.AddonResourceDef r0 = (com.streamsync.app.data.addon.AddonResourceDef) r0
            if (r0 == 0) goto L54
            java.util.List r1 = r0.getIdPrefixes()
            if (r1 == 0) goto L54
            r2 = r1
            r3 = 0
            r6 = r2
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r4
            if (r6 == 0) goto L51
            goto L52
        L51:
            r1 = r5
        L52:
            if (r1 != 0) goto L6f
        L54:
            java.util.List r1 = r12.getIdPrefixes()
            if (r1 != 0) goto L5e
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L5e:
            r2 = r1
            r3 = 0
            r6 = r2
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            r6 = r6 ^ r4
            if (r6 == 0) goto L6b
            goto L6c
        L6b:
            r1 = r5
        L6c:
            if (r1 != 0) goto L6f
            return r5
        L6f:
            r2 = r1
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r3 = 0
            boolean r6 = r2 instanceof java.util.Collection
            r7 = 0
            if (r6 == 0) goto L83
            r6 = r2
            java.util.Collection r6 = (java.util.Collection) r6
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L83
            r4 = r7
            goto L9e
        L83:
            java.util.Iterator r6 = r2.iterator()
        L87:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L9d
            java.lang.Object r8 = r6.next()
            r9 = r8
            java.lang.String r9 = (java.lang.String) r9
            r10 = 0
            r11 = 2
            boolean r9 = kotlin.text.StringsKt.startsWith$default(r13, r9, r7, r11, r5)
            if (r9 == 0) goto L87
            goto L9e
        L9d:
            r4 = r7
        L9e:
            if (r4 == 0) goto La1
            goto Lc4
        La1:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "id '"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r13)
            java.lang.String r3 = "' khong khop idPrefixes ("
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r5 = r2.toString()
        Lc4:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonModelsKt.streamResourceSkipReason(com.streamsync.app.data.addon.AddonManifest, java.lang.String):java.lang.String");
    }

    public static final String supportedCandidateType(AddonManifest $this$supportedCandidateType, String... types) {
        String obj;
        Intrinsics.checkNotNullParameter($this$supportedCandidateType, "<this>");
        Intrinsics.checkNotNullParameter(types, "types");
        int length = types.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            String candidate = types[i];
            if (candidate != null && (obj = StringsKt.trim((CharSequence) candidate).toString()) != null) {
                String it = obj.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(it, "toLowerCase(...)");
                if (it == null) {
                    continue;
                } else {
                    String t = it.length() > 0 ? it : null;
                    if (t != null && supportsMetaType($this$supportedCandidateType, t)) {
                        return t;
                    }
                }
            }
            i++;
        }
    }

    public static final boolean supportsExtra(AddonCatalogDef $this$supportsExtra, String name) {
        Iterable $this$any$iv;
        Iterable $this$any$iv2;
        Iterable $this$any$iv3;
        Intrinsics.checkNotNullParameter($this$supportsExtra, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Iterable $this$any$iv4 = $this$supportsExtra.getExtra();
        if (!($this$any$iv4 instanceof Collection) || !((Collection) $this$any$iv4).isEmpty()) {
            Iterator it = $this$any$iv4.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object element$iv = it.next();
                    AddonExtraDef it2 = (AddonExtraDef) element$iv;
                    if (StringsKt.equals(it2.getName(), name, true)) {
                        $this$any$iv = 1;
                        break;
                    }
                } else {
                    $this$any$iv = null;
                    break;
                }
            }
        } else {
            $this$any$iv = null;
        }
        if ($this$any$iv != null) {
            return true;
        }
        Iterable $this$any$iv5 = $this$supportsExtra.getExtraSupported();
        if (!($this$any$iv5 instanceof Collection) || !((Collection) $this$any$iv5).isEmpty()) {
            Iterator it3 = $this$any$iv5.iterator();
            while (true) {
                if (it3.hasNext()) {
                    Object element$iv2 = it3.next();
                    String it4 = (String) element$iv2;
                    if (StringsKt.equals(it4, name, true)) {
                        $this$any$iv2 = 1;
                        break;
                    }
                } else {
                    $this$any$iv2 = null;
                    break;
                }
            }
        } else {
            $this$any$iv2 = null;
        }
        if ($this$any$iv2 != null) {
            return true;
        }
        Iterable $this$any$iv6 = $this$supportsExtra.getExtraRequired();
        if (!($this$any$iv6 instanceof Collection) || !((Collection) $this$any$iv6).isEmpty()) {
            Iterator it5 = $this$any$iv6.iterator();
            while (true) {
                if (it5.hasNext()) {
                    Object element$iv3 = it5.next();
                    String it6 = (String) element$iv3;
                    if (StringsKt.equals(it6, name, true)) {
                        $this$any$iv3 = 1;
                        break;
                    }
                } else {
                    $this$any$iv3 = null;
                    break;
                }
            }
        } else {
            $this$any$iv3 = null;
        }
        return $this$any$iv3 != null;
    }

    public static /* synthetic */ int skipStepOrDefault$default(AddonCatalogDef addonCatalogDef, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 100;
        }
        return skipStepOrDefault(addonCatalogDef, i);
    }

    public static final int skipStepOrDefault(AddonCatalogDef $this$skipStepOrDefault, int defaultStep) {
        int i;
        Object element$iv;
        Intrinsics.checkNotNullParameter($this$skipStepOrDefault, "<this>");
        if ($this$skipStepOrDefault.getPageSize() != null && $this$skipStepOrDefault.getPageSize().intValue() > 0) {
            return $this$skipStepOrDefault.getPageSize().intValue();
        }
        Iterable $this$firstOrNull$iv = $this$skipStepOrDefault.getExtra();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            AddonExtraDef it2 = (AddonExtraDef) element$iv;
            if (StringsKt.equals(it2.getName(), "skip", true)) {
                break;
            }
        }
        AddonExtraDef addonExtraDef = (AddonExtraDef) element$iv;
        Iterable options = addonExtraDef != null ? addonExtraDef.getOptions() : null;
        if (options == null) {
            options = CollectionsKt.emptyList();
        }
        Iterable $this$mapNotNull$iv = options;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            String it3 = (String) element$iv$iv$iv;
            Integer intOrNull = StringsKt.toIntOrNull(StringsKt.trim((CharSequence) it3).toString());
            if (intOrNull != null) {
                destination$iv$iv.add(intOrNull);
            }
        }
        Iterable $this$filter$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList();
        Iterator it4 = $this$filter$iv.iterator();
        while (true) {
            if (!it4.hasNext()) {
                break;
            }
            Object element$iv$iv = it4.next();
            int it5 = ((Number) element$iv$iv).intValue();
            if (it5 >= 0) {
                destination$iv$iv2.add(element$iv$iv);
            }
        }
        List options2 = CollectionsKt.sorted(CollectionsKt.distinct((List) destination$iv$iv2));
        if (options2.size() >= 2) {
            Iterable $this$mapNotNull$iv2 = CollectionsKt.zipWithNext(options2);
            Collection destination$iv$iv3 = new ArrayList();
            for (Object element$iv$iv$iv2 : $this$mapNotNull$iv2) {
                Pair pair = (Pair) element$iv$iv$iv2;
                int a = ((Number) pair.component1()).intValue();
                int b = ((Number) pair.component2()).intValue();
                Integer valueOf = Integer.valueOf(b - a);
                int it6 = valueOf.intValue();
                int it7 = it6 > 0 ? i : 0;
                if (it7 == 0) {
                    valueOf = null;
                }
                if (valueOf != null) {
                    destination$iv$iv3.add(valueOf);
                }
                i = 1;
            }
            Integer gap = (Integer) CollectionsKt.minOrNull(destination$iv$iv3);
            if (gap != null && gap.intValue() > 0) {
                return gap.intValue();
            }
        }
        return defaultStep;
    }

    public static final AddonMeta mergedWith(AddonMeta $this$mergedWith, AddonMeta other) {
        Intrinsics.checkNotNullParameter($this$mergedWith, "<this>");
        if (other == null) {
            return $this$mergedWith;
        }
        String id = $this$mergedWith.getId();
        if (id == null) {
            id = other.getId();
        }
        String str = id;
        String type = $this$mergedWith.getType();
        if (type == null) {
            type = other.getType();
        }
        String str2 = type;
        String name = $this$mergedWith.getName();
        if (name == null) {
            name = other.getName();
        }
        String str3 = name;
        String poster = $this$mergedWith.getPoster();
        if (poster == null) {
            poster = other.getPoster();
        }
        String str4 = poster;
        String posterShape = $this$mergedWith.getPosterShape();
        if (posterShape == null) {
            posterShape = other.getPosterShape();
        }
        String str5 = posterShape;
        String description = $this$mergedWith.getDescription();
        if (description == null) {
            description = other.getDescription();
        }
        String str6 = description;
        String background = $this$mergedWith.getBackground();
        if (background == null) {
            background = other.getBackground();
        }
        String str7 = background;
        String mergedWith$pick = mergedWith$pick($this$mergedWith.getLogo(), other.getLogo());
        String icon = $this$mergedWith.getIcon();
        if (icon == null) {
            icon = other.getIcon();
        }
        String str8 = icon;
        String mergedWith$pick2 = mergedWith$pick($this$mergedWith.getReleaseInfo(), other.getReleaseInfo());
        String mergedWith$pick3 = mergedWith$pick($this$mergedWith.getReleased(), other.getReleased());
        String mergedWith$pick4 = mergedWith$pick($this$mergedWith.getImdbRating(), other.getImdbRating());
        List<AddonVideo> videos = other.getVideos();
        if (videos == null) {
            videos = $this$mergedWith.getVideos();
        }
        List<AddonVideo> list = videos;
        List<String> mergedWith$pickList = mergedWith$pickList($this$mergedWith.getGenres(), other.getGenres());
        List<String> mergedWith$pickList2 = mergedWith$pickList($this$mergedWith.getDirector(), other.getDirector());
        List<String> mergedWith$pickList3 = mergedWith$pickList($this$mergedWith.getCast(), other.getCast());
        String mergedWith$pick5 = mergedWith$pick($this$mergedWith.getRuntime(), other.getRuntime());
        String mergedWith$pick6 = mergedWith$pick($this$mergedWith.getLanguage(), other.getLanguage());
        String mergedWith$pick7 = mergedWith$pick($this$mergedWith.getCountry(), other.getCountry());
        String mergedWith$pick8 = mergedWith$pick($this$mergedWith.getAwards(), other.getAwards());
        String website = $this$mergedWith.getWebsite();
        if (website == null) {
            website = other.getWebsite();
        }
        String str9 = website;
        Iterable $this$map$iv = $this$mergedWith.getTrailers();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            AddonTrailer it = (AddonTrailer) item$iv$iv;
            destination$iv$iv.add(it.getYoutubeId());
        }
        ArrayList arrayList = (List) destination$iv$iv;
        Iterable $this$map$iv2 = other.getTrailers();
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            AddonTrailer it2 = (AddonTrailer) item$iv$iv2;
            destination$iv$iv2.add(it2.getYoutubeId());
            $this$map$iv2 = $this$map$iv2;
        }
        Iterable $this$map$iv3 = mergedWith$pickList(arrayList, (List) destination$iv$iv2);
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
        for (Object item$iv$iv3 : $this$map$iv3) {
            String it3 = (String) item$iv$iv3;
            destination$iv$iv3.add(new AddonTrailer(it3, null, null, 6, null));
            $this$map$iv3 = $this$map$iv3;
        }
        ArrayList arrayList2 = (List) destination$iv$iv3;
        List<AddonMetaLink> mergeLinks = mergeLinks($this$mergedWith.getLinks(), other.getLinks());
        AddonMetaBehaviorHints behaviorHints = $this$mergedWith.getBehaviorHints();
        if (behaviorHints == null) {
            behaviorHints = other.getBehaviorHints();
        }
        AddonAppExtras appExtras = $this$mergedWith.getAppExtras();
        if (appExtras == null) {
            appExtras = other.getAppExtras();
        }
        return new AddonMeta(str, str2, str3, str4, str5, str6, str7, mergedWith$pick, str8, mergedWith$pick2, mergedWith$pick3, mergedWith$pick4, list, mergedWith$pickList, mergedWith$pickList2, mergedWith$pickList3, mergedWith$pick5, mergedWith$pick6, mergedWith$pick7, mergedWith$pick8, str9, arrayList2, mergeLinks, behaviorHints, appExtras);
    }

    private static final String mergedWith$pick(String a, String b) {
        if (b != null) {
            String str = StringsKt.isBlank(b) ^ true ? b : null;
            if (str != null) {
                return str;
            }
        }
        return a;
    }

    private static final List<String> mergedWith$pickList(List<String> list, List<String> list2) {
        List<String> list3 = list2.isEmpty() ^ true ? list2 : null;
        return list3 == null ? list : list3;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0153 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.List<com.streamsync.app.data.addon.AddonMetaLink> mergeLinks(java.util.List<com.streamsync.app.data.addon.AddonMetaLink> r19, java.util.List<com.streamsync.app.data.addon.AddonMetaLink> r20) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamsync.app.data.addon.AddonModelsKt.mergeLinks(java.util.List, java.util.List):java.util.List");
    }
}
