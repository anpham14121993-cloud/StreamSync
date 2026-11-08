package androidx.compose.runtime;

import androidx.collection.MutableIntIntMap;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.changelist.FixupList;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.IntMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.streamsync.app.settings.BufferCeiling;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Composer.kt */
@Metadata(d1 = {"\u0000ó\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b)*\u0001A\b\u0000\u0018\u00002\u00020\u0001:\u0004Õ\u0002Ö\u0002BG\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\n\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0002J\n\u0010\u0092\u0001\u001a\u00030\u0091\u0001H\u0002JM\u0010\u0093\u0001\u001a\u00030\u0091\u0001\"\u0005\b\u0000\u0010\u0094\u0001\"\u0005\b\u0001\u0010\u0095\u00012\b\u0010\u0096\u0001\u001a\u0003H\u0094\u00012#\u0010\u0097\u0001\u001a\u001e\u0012\u0005\u0012\u0003H\u0095\u0001\u0012\u0005\u0012\u0003H\u0094\u0001\u0012\u0005\u0012\u00030\u0091\u00010\u0098\u0001¢\u0006\u0003\b\u0099\u0001H\u0016¢\u0006\u0003\u0010\u009a\u0001J\t\u0010\u009b\u0001\u001a\u00020\u0005H\u0016J2\u0010\u009c\u0001\u001a\u0003H\u0095\u0001\"\u0005\b\u0000\u0010\u0095\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u00182\u000f\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0095\u00010\u009e\u0001H\u0087\b¢\u0006\u0003\u0010\u009f\u0001J\u0014\u0010 \u0001\u001a\u00020\u00182\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0017J\u0012\u0010 \u0001\u001a\u00020\u00182\u0007\u0010\u0096\u0001\u001a\u00020\u0018H\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030¡\u0001H\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030¢\u0001H\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030£\u0001H\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030¤\u0001H\u0017J\u0012\u0010 \u0001\u001a\u00020\u00182\u0007\u0010\u0096\u0001\u001a\u00020\u001cH\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030¥\u0001H\u0017J\u0013\u0010 \u0001\u001a\u00020\u00182\b\u0010\u0096\u0001\u001a\u00030¦\u0001H\u0017J\u0014\u0010§\u0001\u001a\u00020\u00182\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0017J\u0010\u0010¨\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\b©\u0001J\n\u0010ª\u0001\u001a\u00030\u0091\u0001H\u0002J\n\u0010«\u0001\u001a\u00030\u0091\u0001H\u0002J\n\u0010¬\u0001\u001a\u00030\u0091\u0001H\u0016JI\u0010\u00ad\u0001\u001a\u00030\u0091\u00012\u001d\u0010®\u0001\u001a\u0018\u0012\u0004\u0012\u000205\u0012\r\u0012\u000b\u0012\u0004\u0012\u00020\u007f\u0018\u00010°\u00010¯\u00012\u0015\u0010±\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0091\u00010\u009e\u0001¢\u0006\u0003\b²\u0001H\u0000¢\u0006\u0006\b³\u0001\u0010´\u0001J$\u0010µ\u0001\u001a\u00020\u001c2\u0007\u0010¶\u0001\u001a\u00020\u001c2\u0007\u0010·\u0001\u001a\u00020\u001c2\u0007\u0010¸\u0001\u001a\u00020\u001cH\u0002J(\u0010¹\u0001\u001a\u0003H\u0095\u0001\"\u0005\b\u0000\u0010\u0095\u00012\u000f\u0010º\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0095\u00010»\u0001H\u0017¢\u0006\u0003\u0010¼\u0001J\n\u0010½\u0001\u001a\u00030\u0091\u0001H\u0002J\"\u0010¾\u0001\u001a\u00030\u0091\u0001\"\u0005\b\u0000\u0010\u0095\u00012\u000f\u0010¿\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0095\u00010\u009e\u0001H\u0016J\t\u0010À\u0001\u001a\u00020kH\u0002J\u0012\u0010À\u0001\u001a\u00020k2\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0002J\u0010\u0010Á\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\bÂ\u0001J\u0013\u0010Ã\u0001\u001a\u00030\u0091\u00012\u0007\u0010 \u0001\u001a\u00020\u0018H\u0017J\n\u0010Ä\u0001\u001a\u00030\u0091\u0001H\u0016J\n\u0010Å\u0001\u001a\u00030\u0091\u0001H\u0016J\u0010\u0010Æ\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\bÇ\u0001JH\u0010È\u0001\u001a\u00030\u0091\u00012\u001d\u0010®\u0001\u001a\u0018\u0012\u0004\u0012\u000205\u0012\r\u0012\u000b\u0012\u0004\u0012\u00020\u007f\u0018\u00010°\u00010¯\u00012\u0017\u0010±\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u0091\u0001\u0018\u00010\u009e\u0001¢\u0006\u0003\b²\u0001H\u0002¢\u0006\u0003\u0010´\u0001J\u001c\u0010É\u0001\u001a\u00030\u0091\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\u0007\u0010Ê\u0001\u001a\u00020\u001cH\u0002J\n\u0010Ë\u0001\u001a\u00030\u0091\u0001H\u0016J\u0013\u0010Ì\u0001\u001a\u00030\u0091\u00012\u0007\u0010Í\u0001\u001a\u00020\u0018H\u0002J\n\u0010Î\u0001\u001a\u00030\u0091\u0001H\u0017J\n\u0010Ï\u0001\u001a\u00030\u0091\u0001H\u0002J\n\u0010Ð\u0001\u001a\u00030\u0091\u0001H\u0017J\n\u0010Ñ\u0001\u001a\u00030\u0091\u0001H\u0016J\n\u0010Ò\u0001\u001a\u00030\u0091\u0001H\u0017J\n\u0010Ó\u0001\u001a\u00030\u0091\u0001H\u0017J\n\u0010Ô\u0001\u001a\u00030\u0091\u0001H\u0017J\f\u0010Õ\u0001\u001a\u0005\u0018\u00010Ö\u0001H\u0017J\n\u0010×\u0001\u001a\u00030\u0091\u0001H\u0016J\b\u0010Ø\u0001\u001a\u00030\u0091\u0001J\n\u0010Ù\u0001\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010Ú\u0001\u001a\u00030\u0091\u00012\u0007\u0010Û\u0001\u001a\u00020\u001cH\u0016J\n\u0010Ü\u0001\u001a\u00030\u0091\u0001H\u0002J\u001e\u0010Ý\u0001\u001a\u00030\u0091\u00012\u0007\u0010Í\u0001\u001a\u00020\u00182\t\u0010Þ\u0001\u001a\u0004\u0018\u00010mH\u0002J\u001b\u0010ß\u0001\u001a\u00030\u0091\u00012\u0007\u0010à\u0001\u001a\u00020\u001c2\u0006\u0010W\u001a\u00020\u0018H\u0002J\n\u0010á\u0001\u001a\u00030\u0091\u0001H\u0002J\u000e\u0010E\u001a\u00020\u0018H\u0000¢\u0006\u0003\bâ\u0001J#\u0010ã\u0001\u001a\u00030\u0091\u00012\f\u0010\u0096\u0001\u001a\u0007\u0012\u0002\b\u00030ä\u00012\t\u0010å\u0001\u001a\u0004\u0018\u00010\u007fH\u0017J+\u0010æ\u0001\u001a\u00030\u0091\u00012\u001f\u0010ç\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030ê\u0001\u0012\u0007\u0012\u0005\u0018\u00010ê\u00010é\u00010è\u0001H\u0002J+\u0010ë\u0001\u001a\u00030\u0091\u00012\u001f\u0010ç\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030ê\u0001\u0012\u0007\u0012\u0005\u0018\u00010ê\u00010é\u00010è\u0001H\u0017J\u0012\u0010ì\u0001\u001a\u00020\u001c2\u0007\u0010í\u0001\u001a\u00020\u001cH\u0002J9\u0010î\u0001\u001a\u00030\u0091\u00012\u0010\u0010±\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u007f0ä\u00012\u0007\u0010ï\u0001\u001a\u00020k2\t\u0010å\u0001\u001a\u0004\u0018\u00010\u007f2\u0007\u0010ð\u0001\u001a\u00020\u0018H\u0002J\u001f\u0010ñ\u0001\u001a\u00020\u007f2\t\u0010ò\u0001\u001a\u0004\u0018\u00010\u007f2\t\u0010ó\u0001\u001a\u0004\u0018\u00010\u007fH\u0017J\u000b\u0010ô\u0001\u001a\u0004\u0018\u00010\u007fH\u0001J\u000b\u0010õ\u0001\u001a\u0004\u0018\u00010\u007fH\u0001J-\u0010ö\u0001\u001a\u00020\u001c2\u0007\u0010÷\u0001\u001a\u00020\u001c2\u0007\u0010¶\u0001\u001a\u00020\u001c2\u0007\u0010·\u0001\u001a\u00020\u001c2\u0007\u0010ø\u0001\u001a\u00020\u001cH\u0002J\u000f\u0010ù\u0001\u001a\u00020\u001cH\u0001¢\u0006\u0003\bú\u0001J!\u0010û\u0001\u001a\u00030\u0091\u00012\u000f\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u00030\u0091\u00010\u009e\u0001H\u0000¢\u0006\u0003\bü\u0001J.\u0010ý\u0001\u001a\u00020\u00182\u001d\u0010®\u0001\u001a\u0018\u0012\u0004\u0012\u000205\u0012\r\u0012\u000b\u0012\u0004\u0012\u00020\u007f\u0018\u00010°\u00010¯\u0001H\u0000¢\u0006\u0003\bþ\u0001Jv\u0010ÿ\u0001\u001a\u0003H\u0080\u0002\"\u0005\b\u0000\u0010\u0080\u00022\u000b\b\u0002\u0010\u0081\u0002\u001a\u0004\u0018\u00010\u000f2\u000b\b\u0002\u0010\u0082\u0002\u001a\u0004\u0018\u00010\u000f2\u000b\b\u0002\u0010í\u0001\u001a\u0004\u0018\u00010\u001c2%\b\u0002\u0010\\\u001a\u001f\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u000205\u0012\r\u0012\u000b\u0012\u0004\u0012\u00020\u007f\u0018\u00010°\u00010é\u00010è\u00012\u000f\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0080\u00020\u009e\u0001H\u0002¢\u0006\u0003\u0010\u0083\u0002J\n\u0010\u0084\u0002\u001a\u00030\u0091\u0001H\u0002J\n\u0010\u0085\u0002\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010\u0086\u0002\u001a\u00030\u0091\u00012\u0007\u0010\u0087\u0002\u001a\u00020OH\u0002J\u0013\u0010\u0088\u0002\u001a\u00030\u0091\u00012\u0007\u0010\u0089\u0002\u001a\u00020kH\u0002J\u001b\u0010\u008a\u0002\u001a\u00030\u0091\u00012\u000f\u0010\u008b\u0002\u001a\n\u0012\u0005\u0012\u00030\u0091\u00010\u009e\u0001H\u0016J%\u0010\u008c\u0002\u001a\u00030\u0091\u00012\u0007\u0010\u008d\u0002\u001a\u00020\u001c2\u0007\u0010\u008e\u0002\u001a\u00020\u001c2\u0007\u0010\u008f\u0002\u001a\u00020\u001cH\u0002J\u0013\u0010\u0090\u0002\u001a\u00030\u0091\u00012\u0007\u0010\u0091\u0002\u001a\u00020{H\u0016J\u000b\u0010\u0092\u0002\u001a\u0004\u0018\u00010\u007fH\u0016J\n\u0010\u0093\u0002\u001a\u00030\u0091\u0001H\u0002J\u0013\u0010\u0094\u0002\u001a\u00030\u0091\u00012\u0007\u0010\u0095\u0002\u001a\u00020\u001cH\u0002J\n\u0010\u0096\u0002\u001a\u00030\u0091\u0001H\u0017J\n\u0010\u0097\u0002\u001a\u00030\u0091\u0001H\u0002J\n\u0010\u0098\u0002\u001a\u00030\u0091\u0001H\u0002J\n\u0010\u0099\u0002\u001a\u00030\u0091\u0001H\u0017J\u0014\u0010\u009a\u0002\u001a\u00030\u0091\u00012\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002H\u0017J\n\u0010\u009c\u0002\u001a\u00030\u0091\u0001H\u0017J\u001d\u0010\u009d\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001c2\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002H\u0017J?\u0010\u009e\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001c2\t\u0010\u009f\u0002\u001a\u0004\u0018\u00010\u007f2\b\u0010 \u0002\u001a\u00030¡\u00022\t\u0010¢\u0002\u001a\u0004\u0018\u00010\u007fH\u0002ø\u0001\u0000¢\u0006\u0006\b£\u0002\u0010¤\u0002J\n\u0010¥\u0002\u001a\u00030\u0091\u0001H\u0017J\u0013\u0010¦\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001cH\u0002J\u001e\u0010¦\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001c2\t\u0010§\u0002\u001a\u0004\u0018\u00010\u007fH\u0002J\u001e\u0010¨\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001c2\t\u0010§\u0002\u001a\u0004\u0018\u00010\u007fH\u0017J\n\u0010©\u0002\u001a\u00030\u0091\u0001H\u0016J\u0018\u0010ª\u0002\u001a\u00030\u0091\u00012\f\u0010\u0096\u0001\u001a\u0007\u0012\u0002\b\u00030«\u0002H\u0017J'\u0010¬\u0002\u001a\u00030\u0091\u00012\u0015\u0010\u00ad\u0002\u001a\u0010\u0012\u000b\b\u0001\u0012\u0007\u0012\u0002\b\u00030«\u00020®\u0002H\u0017¢\u0006\u0003\u0010¯\u0002J\u001e\u0010°\u0002\u001a\u00030\u0091\u00012\u0007\u0010Í\u0001\u001a\u00020\u00182\t\u0010¢\u0002\u001a\u0004\u0018\u00010\u007fH\u0002J\u0013\u0010±\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001cH\u0017J\u0012\u0010²\u0002\u001a\u00020\u00012\u0007\u0010º\u0001\u001a\u00020\u001cH\u0017J\u001e\u0010³\u0002\u001a\u00030\u0091\u00012\u0007\u0010º\u0001\u001a\u00020\u001c2\t\u0010§\u0002\u001a\u0004\u0018\u00010\u007fH\u0016J\n\u0010´\u0002\u001a\u00030\u0091\u0001H\u0016J\b\u0010µ\u0002\u001a\u00030\u0091\u0001J\n\u0010¶\u0002\u001a\u00030\u0091\u0001H\u0002J#\u0010·\u0002\u001a\u00020\u00182\u0007\u0010\u0091\u0002\u001a\u0002052\t\u0010¸\u0002\u001a\u0004\u0018\u00010\u007fH\u0000¢\u0006\u0003\b¹\u0002J\u0015\u0010º\u0002\u001a\u00030\u0091\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0001J)\u0010»\u0002\u001a\u00030\u0091\u00012\u0007\u0010¼\u0002\u001a\u00020\u001c2\t\u0010§\u0002\u001a\u0004\u0018\u00010\u007f2\t\u0010¢\u0002\u001a\u0004\u0018\u00010\u007fH\u0002J\u0013\u0010½\u0002\u001a\u00030\u0091\u00012\u0007\u0010¾\u0002\u001a\u00020\u001cH\u0002J)\u0010¿\u0002\u001a\u00030\u0091\u00012\u0007\u0010¼\u0002\u001a\u00020\u001c2\t\u0010§\u0002\u001a\u0004\u0018\u00010\u007f2\t\u0010¢\u0002\u001a\u0004\u0018\u00010\u007fH\u0002J\u0013\u0010À\u0002\u001a\u00030\u0091\u00012\u0007\u0010¼\u0002\u001a\u00020\u001cH\u0002J\u001c\u0010Á\u0002\u001a\u00030\u0091\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\u0007\u0010Â\u0002\u001a\u00020\u001cH\u0002J\u001c\u0010Ã\u0002\u001a\u00030\u0091\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\u0007\u0010Ä\u0002\u001a\u00020\u001cH\u0002J\u001b\u0010Å\u0002\u001a\u00020k2\u0007\u0010Æ\u0002\u001a\u00020k2\u0007\u0010Ç\u0002\u001a\u00020kH\u0002J\u0015\u0010È\u0002\u001a\u00030\u0091\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0016J\u0015\u0010É\u0002\u001a\u00030\u0091\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0002J\u0015\u0010Ê\u0002\u001a\u00030\u0091\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u007fH\u0001J\u0012\u0010Ë\u0002\u001a\u00020\u001c2\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0002J\n\u0010Ì\u0002\u001a\u00030\u0091\u0001H\u0016J\n\u0010Í\u0002\u001a\u00030\u0091\u0001H\u0002J\n\u0010Î\u0002\u001a\u00030\u0091\u0001H\u0002J\u0010\u0010Ï\u0002\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\bÐ\u0002J1\u0010Ñ\u0002\u001a\u0003H\u0080\u0002\"\u0005\b\u0000\u0010\u0080\u00022\u0006\u0010t\u001a\u00020u2\u000f\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0080\u00020\u009e\u0001H\u0082\b¢\u0006\u0003\u0010Ò\u0002J\u0016\u0010Ó\u0002\u001a\u00020\u001c*\u00020u2\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0002J\u0018\u0010Ô\u0002\u001a\u0004\u0018\u00010\u007f*\u00020u2\u0007\u0010í\u0001\u001a\u00020\u001cH\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010*\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c8\u0016@RX\u0097\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010\u001eR\u0014\u0010.\u001a\u00020/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u001eR\u0016\u00104\u001a\u0004\u0018\u0001058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u00188VX\u0097\u0004¢\u0006\f\u0012\u0004\b9\u0010,\u001a\u0004\b:\u0010\u001aR\u001c\u0010;\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0010\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0004\n\u0002\u0010BR\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010I\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\bJ\u0010\u001aR\u0014\u0010K\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bL\u0010\u001aR\u000e\u0010M\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020OX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010R\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR&\u0010W\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u00188\u0016@RX\u0097\u000e¢\u0006\u000e\n\u0000\u0012\u0004\bX\u0010,\u001a\u0004\bY\u0010\u001aR\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u0002050[X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020^0]X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010_\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u001aR\u001e\u0010a\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u001aR\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u0004\u0018\u00010dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010e\u001a\u0004\u0018\u00010fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020kX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010l\u001a\u0004\u0018\u00010mX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010m0[X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010o\u001a\u0004\u0018\u00010kX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010p\u001a\n\u0012\u0004\u0012\u00020k\u0018\u00010qX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010t\u001a\u00020uX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR\u0016\u0010z\u001a\u0004\u0018\u00010{8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b|\u0010}R\u0018\u0010~\u001a\u0004\u0018\u00010\u007f8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u000f\u0010\u0082\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0084\u0001\u001a\u00020\u00188VX\u0097\u0004¢\u0006\u000e\u0012\u0005\b\u0085\u0001\u0010,\u001a\u0005\b\u0086\u0001\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0088\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u008a\u0001\u001a\u00030\u008b\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008c\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u007f*\u00020u8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006×\u0002"}, d2 = {"Landroidx/compose/runtime/ComposerImpl;", "Landroidx/compose/runtime/Composer;", "applier", "Landroidx/compose/runtime/Applier;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "composition", "Landroidx/compose/runtime/ControlledComposition;", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/SlotTable;Ljava/util/Set;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/ControlledComposition;)V", "getApplier", "()Landroidx/compose/runtime/Applier;", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "areChildrenComposing", "", "getAreChildrenComposing$runtime_release", "()Z", "changeCount", "", "getChangeCount$runtime_release", "()I", "changeListWriter", "Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "childrenComposing", "getComposition", "()Landroidx/compose/runtime/ControlledComposition;", "compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "compositionToken", "<set-?>", "compoundKeyHash", "getCompoundKeyHash$annotations", "()V", "getCompoundKeyHash", "currentCompositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "currentMarker", "getCurrentMarker", "currentRecomposeScope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getCurrentRecomposeScope$runtime_release", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "defaultsInvalid", "getDefaultsInvalid$annotations", "getDefaultsInvalid", "deferredChanges", "getDeferredChanges$runtime_release", "()Landroidx/compose/runtime/changelist/ChangeList;", "setDeferredChanges$runtime_release", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "derivedStateObserver", "androidx/compose/runtime/ComposerImpl$derivedStateObserver$1", "Landroidx/compose/runtime/ComposerImpl$derivedStateObserver$1;", "entersStack", "Landroidx/compose/runtime/IntStack;", "forceRecomposeScopes", "forciblyRecompose", "groupNodeCount", "groupNodeCountStack", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges$runtime_release", "implicitRootStart", "insertAnchor", "Landroidx/compose/runtime/Anchor;", "insertFixups", "Landroidx/compose/runtime/changelist/FixupList;", "insertTable", "getInsertTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "setInsertTable$runtime_release", "(Landroidx/compose/runtime/SlotTable;)V", "inserting", "getInserting$annotations", "getInserting", "invalidateStack", "Landroidx/compose/runtime/Stack;", "invalidations", "", "Landroidx/compose/runtime/Invalidation;", "isComposing", "isComposing$runtime_release", "isDisposed", "isDisposed$runtime_release", "nodeCountOverrides", "", "nodeCountVirtualOverrides", "Landroidx/collection/MutableIntIntMap;", "nodeExpected", "nodeIndex", "nodeIndexStack", "parentProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "pending", "Landroidx/compose/runtime/Pending;", "pendingStack", "providerCache", "providerUpdates", "Landroidx/compose/runtime/collection/IntMap;", "providersInvalid", "providersInvalidStack", "reader", "Landroidx/compose/runtime/SlotReader;", "getReader$runtime_release", "()Landroidx/compose/runtime/SlotReader;", "setReader$runtime_release", "(Landroidx/compose/runtime/SlotReader;)V", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScopeIdentity", "", "getRecomposeScopeIdentity", "()Ljava/lang/Object;", "reusing", "reusingGroup", "skipping", "getSkipping$annotations", "getSkipping", "sourceInformationEnabled", "startedGroup", "startedGroups", "writer", "Landroidx/compose/runtime/SlotWriter;", "writerHasAProvider", "node", "getNode", "(Landroidx/compose/runtime/SlotReader;)Ljava/lang/Object;", "abortRoot", "", "addRecomposeScope", "apply", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.GPS_DIRECTION_TRUE, "value", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "buildContext", "cache", "invalid", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "changed", "", "", "", "", "", "", "changedInstance", "changesApplied", "changesApplied$runtime_release", "cleanUpCompose", "clearUpdatedNodeCounts", "collectParameterInformation", "composeContent", "invalidationsRequested", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/collection/IdentityArraySet;", "content", "Landroidx/compose/runtime/Composable;", "composeContent$runtime_release", "(Landroidx/compose/runtime/collection/IdentityArrayMap;Lkotlin/jvm/functions/Function2;)V", "compoundKeyOf", "group", "recomposeGroup", "recomposeKey", "consume", "key", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "createFreshInsertTable", "createNode", "factory", "currentCompositionLocalScope", "deactivate", "deactivate$runtime_release", "deactivateToEndGroup", "disableReusing", "disableSourceInformation", "dispose", "dispose$runtime_release", "doCompose", "doRecordDownsFor", "nearestCommonRoot", "enableReusing", TtmlNode.END, "isNode", "endDefaults", "endGroup", "endMovableGroup", "endNode", "endProvider", "endProviders", "endReplaceableGroup", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "endReusableGroup", "endReuseFromRoot", "endRoot", "endToMarker", "marker", "ensureWriter", "enterGroup", "newPending", "exitGroup", "expectedNodeCount", "finalizeCompose", "forceRecomposeScopes$runtime_release", "insertMovableContent", "Landroidx/compose/runtime/MovableContent;", "parameter", "insertMovableContentGuarded", "references", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContentReferences", "insertedGroupVirtualIndex", "index", "invokeMovableContentLambda", "locals", "force", "joinKey", TtmlNode.LEFT, TtmlNode.RIGHT, "nextSlot", "nextSlotForCache", "nodeIndexOf", "groupLocation", "recomposeIndex", "parentKey", "parentKey$runtime_release", "prepareCompose", "prepareCompose$runtime_release", "recompose", "recompose$runtime_release", "recomposeMovableContent", "R", "from", "to", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;Ljava/lang/Integer;Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recomposeToGroupEnd", "recordDelete", "recordInsert", "anchor", "recordProviderUpdate", "providers", "recordSideEffect", "effect", "recordUpsAndDowns", "oldGroup", "newGroup", "commonRoot", "recordUsed", "scope", "rememberedValue", "reportAllMovableContent", "reportFreeMovableContent", "groupBeingRemoved", "skipCurrentGroup", "skipGroup", "skipReaderToGroupEnd", "skipToGroupEnd", "sourceInformation", "", "sourceInformationMarkerEnd", "sourceInformationMarkerStart", TtmlNode.START, "objectKey", "kind", "Landroidx/compose/runtime/GroupKind;", "data", "start-BaiHCIY", "(ILjava/lang/Object;ILjava/lang/Object;)V", "startDefaults", "startGroup", "dataKey", "startMovableGroup", "startNode", "startProvider", "Landroidx/compose/runtime/ProvidedValue;", "startProviders", "values", "", "([Landroidx/compose/runtime/ProvidedValue;)V", "startReaderGroup", "startReplaceableGroup", "startRestartGroup", "startReusableGroup", "startReusableNode", "startReuseFromRoot", "startRoot", "tryImminentInvalidation", "instance", "tryImminentInvalidation$runtime_release", "updateCachedValue", "updateCompoundKeyWhenWeEnterGroup", "groupKey", "updateCompoundKeyWhenWeEnterGroupKeyHash", "keyHash", "updateCompoundKeyWhenWeExitGroup", "updateCompoundKeyWhenWeExitGroupKeyHash", "updateNodeCount", "count", "updateNodeCountOverrides", "newCount", "updateProviderMapGroup", "parentScope", "currentProviders", "updateRememberedValue", "updateSlot", "updateValue", "updatedNodeCount", "useNode", "validateNodeExpected", "validateNodeNotExpected", "verifyConsistent", "verifyConsistent$runtime_release", "withReader", "(Landroidx/compose/runtime/SlotReader;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "groupCompoundKeyPart", "nodeAt", "CompositionContextHolder", "CompositionContextImpl", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes.dex */
public final class ComposerImpl implements Composer {
    public static final int $stable = 8;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ComposerChangeListWriter changeListWriter;
    private ChangeList changes;
    private int childrenComposing;
    private final ControlledComposition composition;
    private int compositionToken;
    private int compoundKeyHash;
    private ChangeList deferredChanges;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private boolean implicitRootStart;
    private Anchor insertAnchor;
    private FixupList insertFixups;
    private SlotTable insertTable;
    private boolean inserting;
    private boolean isComposing;
    private boolean isDisposed;
    private ChangeList lateChanges;
    private int[] nodeCountOverrides;
    private MutableIntIntMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionContext parentContext;
    private Pending pending;
    private PersistentCompositionLocalMap providerCache;
    private IntMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private SlotReader reader;
    private boolean reusing;
    private final SlotTable slotTable;
    private boolean sourceInformationEnabled;
    private boolean startedGroup;
    private final IntStack startedGroups;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private final Stack<Pending> pendingStack = new Stack<>();
    private IntStack nodeIndexStack = new IntStack();
    private IntStack groupNodeCountStack = new IntStack();
    private final List<Invalidation> invalidations = new ArrayList();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap parentProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;
    private final ComposerImpl$derivedStateObserver$1 derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.ComposerImpl$derivedStateObserver$1
        @Override // androidx.compose.runtime.DerivedStateObserver
        public void start(DerivedState<?> derivedState) {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.DerivedStateObserver
        public void done(DerivedState<?> derivedState) {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.childrenComposing--;
        }
    };
    private final Stack<RecomposeScopeImpl> invalidateStack = new Stack<>();

    public static /* synthetic */ void getCompoundKeyHash$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getDefaultsInvalid$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getInserting$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getSkipping$annotations() {
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.compose.runtime.ComposerImpl$derivedStateObserver$1] */
    public ComposerImpl(Applier<?> applier, CompositionContext parentContext, SlotTable slotTable, Set<RememberObserver> set, ChangeList changes, ChangeList lateChanges, ControlledComposition composition) {
        this.applier = applier;
        this.parentContext = parentContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = changes;
        this.lateChanges = lateChanges;
        this.composition = composition;
        SlotReader it = this.slotTable.openReader();
        it.close();
        this.reader = it;
        this.insertTable = new SlotTable();
        SlotWriter it2 = this.insertTable.openWriter();
        it2.close();
        this.writer = it2;
        this.changeListWriter = new ComposerChangeListWriter(this, this.changes);
        SlotTable this_$iv = this.insertTable;
        SlotReader reader$iv = this_$iv.openReader();
        try {
            Anchor anchor = reader$iv.anchor(0);
            reader$iv.close();
            this.insertAnchor = anchor;
            this.insertFixups = new FixupList();
            this.implicitRootStart = true;
            this.startedGroups = new IntStack();
        } catch (Throwable th) {
            reader$iv.close();
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public ControlledComposition getComposition() {
        return this.composition;
    }

    /* renamed from: isComposing$runtime_release, reason: from getter */
    public final boolean getIsComposing() {
        return this.isComposing;
    }

    /* renamed from: isDisposed$runtime_release, reason: from getter */
    public final boolean getIsDisposed() {
        return this.isDisposed;
    }

    public final boolean getAreChildrenComposing$runtime_release() {
        return this.childrenComposing > 0;
    }

    public final boolean getHasPendingChanges$runtime_release() {
        return this.changes.isNotEmpty();
    }

    /* renamed from: getReader$runtime_release, reason: from getter */
    public final SlotReader getReader() {
        return this.reader;
    }

    public final void setReader$runtime_release(SlotReader slotReader) {
        this.reader = slotReader;
    }

    /* renamed from: getInsertTable$runtime_release, reason: from getter */
    public final SlotTable getInsertTable() {
        return this.insertTable;
    }

    public final void setInsertTable$runtime_release(SlotTable slotTable) {
        this.insertTable = slotTable;
    }

    /* renamed from: getDeferredChanges$runtime_release, reason: from getter */
    public final ChangeList getDeferredChanges() {
        return this.deferredChanges;
    }

    public final void setDeferredChanges$runtime_release(ChangeList changeList) {
        this.deferredChanges = changeList;
    }

    @Override // androidx.compose.runtime.Composer
    public CoroutineContext getApplyCoroutineContext() {
        return this.parentContext.getEffectCoroutineContext();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceableGroup(int key) {
        m2917startBaiHCIY(key, null, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startDefaults() {
        m2917startBaiHCIY(-127, null, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl scope = getCurrentRecomposeScope$runtime_release();
        if (scope != null && scope.getUsed()) {
            scope.setDefaultsInScope(true);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getDefaultsInvalid() {
        if (!getSkipping() || this.providersInvalid) {
            return true;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        return currentRecomposeScope$runtime_release != null && currentRecomposeScope$runtime_release.getDefaultsInvalid();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startMovableGroup(int key, Object dataKey) {
        m2917startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endMovableGroup() {
        endGroup();
    }

    private final void startRoot() {
        int asInt;
        this.reader = this.slotTable.openReader();
        startGroup(100);
        this.parentContext.startComposing$runtime_release();
        this.parentProvider = this.parentContext.getCompositionLocalScope$runtime_release();
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = this.parentContext.getCollectingParameterInformation();
        }
        if (!this.sourceInformationEnabled) {
            this.sourceInformationEnabled = this.parentContext.getCollectingSourceInformation();
        }
        Set it = (Set) CompositionLocalMapKt.read(this.parentProvider, InspectionTablesKt.getLocalInspectionTables());
        if (it != null) {
            it.add(this.slotTable);
            this.parentContext.recordInspectionTable$runtime_release(it);
        }
        startGroup(this.parentContext.getCompoundHashKey());
    }

    private final void endRoot() {
        endGroup();
        this.parentContext.doneComposing$runtime_release();
        endGroup();
        this.changeListWriter.endRoot();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
    }

    private final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.nodeIndexStack.clear();
        this.groupNodeCountStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates = null;
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (!this.writer.getClosed()) {
            this.writer.close();
        }
        this.insertFixups.clear();
        createFreshInsertTable();
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.forciblyRecompose = false;
        this.reusingGroup = -1;
    }

    public final void changesApplied$runtime_release() {
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getSkipping() {
        if (getInserting() || this.reusing || this.providersInvalid) {
            return false;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        return (currentRecomposeScope$runtime_release != null && !currentRecomposeScope$runtime_release.getRequiresRecompose()) && !this.forciblyRecompose;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCompoundKeyHash() {
        return this.compoundKeyHash;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        this.sourceInformationEnabled = true;
    }

    public final void dispose$runtime_release() {
        Object token$iv = Trace.INSTANCE.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.unregisterComposer$runtime_release(this);
            deactivate$runtime_release();
            getApplier().clear();
            this.isDisposed = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    public final void deactivate$runtime_release() {
        this.invalidateStack.clear();
        this.invalidations.clear();
        this.changes.clear();
        this.providerUpdates = null;
    }

    public final boolean forceRecomposeScopes$runtime_release() {
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = true;
            this.forciblyRecompose = true;
            return true;
        }
        return false;
    }

    private final void startGroup(int key) {
        m2917startBaiHCIY(key, null, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
    }

    private final void startGroup(int key, Object dataKey) {
        m2917startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
    }

    private final void endGroup() {
        end(false);
    }

    private final void skipGroup() {
        this.groupNodeCount += this.reader.skipGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m2917startBaiHCIY(125, null, GroupKind.INSTANCE.m2928getNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m2917startBaiHCIY(125, null, GroupKind.INSTANCE.m2929getReusableNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(Function0<? extends T> factory) {
        validateNodeExpected();
        boolean value$iv = getInserting();
        if (value$iv) {
            int insertIndex = this.nodeIndexStack.peek();
            Anchor groupAnchor = this.writer.anchor(this.writer.getParent());
            this.groupNodeCount++;
            this.insertFixups.createAndInsertNode(factory, insertIndex, groupAnchor);
            return;
        }
        ComposerKt.composeRuntimeError("createNode() can only be called when inserting".toString());
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        boolean value$iv = !getInserting();
        if (value$iv) {
            Object node = getNode(this.reader);
            this.changeListWriter.moveDown(node);
            if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
                this.changeListWriter.useNode(node);
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("useNode() called while inserting".toString());
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !Intrinsics.areEqual(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrent();
            this.reusing = true;
        }
        m2917startBaiHCIY(key, null, GroupKind.INSTANCE.m2927getGroupULZAiWs(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    public final void startReuseFromRoot() {
        this.reusingGroup = 100;
        this.reusing = true;
    }

    public final void endReuseFromRoot() {
        if (!(!this.isComposing && this.reusingGroup == 100)) {
            throw new IllegalArgumentException("Cannot disable reuse from root if it was caused by other groups".toString());
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.writer.getParent() : this.reader.getParent();
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int writerLocation = -marker;
            SlotWriter writer = this.writer;
            while (true) {
                int parent = writer.getParent();
                if (parent > writerLocation) {
                    end(writer.isNode(parent));
                } else {
                    return;
                }
            }
        } else {
            if (getInserting()) {
                SlotWriter writer2 = this.writer;
                while (getInserting()) {
                    end(writer2.isNode(writer2.getParent()));
                }
            }
            SlotReader reader = this.reader;
            while (true) {
                int parent2 = reader.getParent();
                if (parent2 > marker) {
                    end(reader.isNode(parent2));
                } else {
                    return;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.Composer
    public <V, T> void apply(V value, Function2<? super T, ? super V, Unit> block) {
        if (getInserting()) {
            this.insertFixups.updateNode(value, block);
        } else {
            this.changeListWriter.updateNode(value, block);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Object joinKey(Object left, Object right) {
        Object key;
        key = ComposerKt.getKey(this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    public final Object nextSlot() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        return (!this.reusing || (it instanceof ReusableRememberObserver)) ? it : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache() {
        Object it;
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it2 = this.reader.next();
        if (!this.reusing || (it2 instanceof ReusableRememberObserver)) {
            it = it2 instanceof RememberObserverHolder ? ((RememberObserverHolder) it2).getWrapped() : it2;
        } else {
            it = Composer.INSTANCE.getEmpty();
        }
        return it;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(Object value) {
        if (!Intrinsics.areEqual(nextSlot(), value)) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changedInstance(Object value) {
        if (nextSlot() != value) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(char value) {
        Object next = nextSlot();
        if (next instanceof Character) {
            char nextPrimitive = ((Character) next).charValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Character.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(byte value) {
        Object next = nextSlot();
        if (next instanceof Byte) {
            byte nextPrimitive = ((Number) next).byteValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Byte.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(short value) {
        Object next = nextSlot();
        if (next instanceof Short) {
            short nextPrimitive = ((Number) next).shortValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Short.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(boolean value) {
        Object next = nextSlot();
        if (next instanceof Boolean) {
            boolean nextPrimitive = ((Boolean) next).booleanValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Boolean.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(float value) {
        Object next = nextSlot();
        if (next instanceof Float) {
            float nextPrimitive = ((Number) next).floatValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(long value) {
        Object next = nextSlot();
        if (next instanceof Long) {
            long nextPrimitive = ((Number) next).longValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(double value) {
        Object next = nextSlot();
        if (next instanceof Double) {
            double nextPrimitive = ((Number) next).doubleValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(int value) {
        Object next = nextSlot();
        if (next instanceof Integer) {
            int nextPrimitive = ((Number) next).intValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Integer.valueOf(value));
        return true;
    }

    @ComposeCompilerApi
    public final <T> T cache(boolean invalid, Function0<? extends T> block) {
        T t = (T) nextSlotForCache();
        if (t == Composer.INSTANCE.getEmpty() || invalid) {
            T invoke = block.invoke();
            updateCachedValue(invoke);
            return invoke;
        }
        return t;
    }

    private final void updateSlot(Object value) {
        nextSlot();
        updateValue(value);
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.writer.update(value);
        } else {
            int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
            this.changeListWriter.updateValue(value, groupSlotIndex);
        }
    }

    public final void updateCachedValue(Object value) {
        Object toStore;
        if (value instanceof RememberObserver) {
            if (getInserting()) {
                this.changeListWriter.remember((RememberObserver) value);
            }
            this.abandonSet.add(value);
            toStore = new RememberObserverHolder((RememberObserver) value);
        } else {
            toStore = value;
        }
        updateValue(toStore);
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        return this.slotTable;
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(Function0<Unit> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap it = this.providerCache;
        if (it != null) {
            return it;
        }
        return currentCompositionLocalScope(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap persistentCompositionLocalMap;
        if (getInserting() && this.writerHasAProvider) {
            int current = this.writer.getParent();
            while (current > 0) {
                if (this.writer.groupKey(current) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(current), ComposerKt.getCompositionLocalMap())) {
                    Object groupAux = this.writer.groupAux(current);
                    Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                    PersistentCompositionLocalMap providers = (PersistentCompositionLocalMap) groupAux;
                    this.providerCache = providers;
                    return providers;
                }
                current = this.writer.parent(current);
            }
        }
        if (this.reader.getGroupsSize() > 0) {
            int current2 = group;
            while (current2 > 0) {
                if (this.reader.groupKey(current2) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(current2), ComposerKt.getCompositionLocalMap())) {
                    IntMap<PersistentCompositionLocalMap> intMap = this.providerUpdates;
                    if (intMap == null || (persistentCompositionLocalMap = intMap.get(current2)) == null) {
                        Object groupAux2 = this.reader.groupAux(current2);
                        Intrinsics.checkNotNull(groupAux2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        persistentCompositionLocalMap = (PersistentCompositionLocalMap) groupAux2;
                    }
                    PersistentCompositionLocalMap providers2 = persistentCompositionLocalMap;
                    this.providerCache = providers2;
                    return providers2;
                }
                current2 = this.reader.parent(current2);
            }
        }
        this.providerCache = this.parentProvider;
        return this.parentProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    private final PersistentCompositionLocalMap updateProviderMapGroup(PersistentCompositionLocalMap parentScope, PersistentCompositionLocalMap currentProviders) {
        PersistentMap.Builder<CompositionLocal<Object>, State<? extends Object>> builder2 = parentScope.builder2();
        PersistentMap.Builder<CompositionLocal<Object>, State<? extends Object>> it = builder2;
        it.putAll(currentProviders);
        ?? build2 = builder2.build2();
        startGroup(204, ComposerKt.getProviderMaps());
        updateSlot(build2);
        updateSlot(currentProviders);
        endGroup();
        return build2;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProvider(ProvidedValue<?> value) {
        State state;
        PersistentCompositionLocalMap putValue;
        PersistentCompositionLocalMap providers;
        boolean invalid;
        int asInt;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(201, ComposerKt.getProvider());
        Object it = rememberedValue();
        if (Intrinsics.areEqual(it, Composer.INSTANCE.getEmpty())) {
            state = null;
        } else {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type androidx.compose.runtime.State<kotlin.Any?>");
            state = (State) it;
        }
        State oldState = state;
        CompositionLocal local = value.getCompositionLocal();
        Intrinsics.checkNotNull(local, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        State state2 = local.updatedStateOf$runtime_release(value.getValue(), oldState);
        boolean z = true;
        boolean change = !Intrinsics.areEqual(state2, oldState);
        if (change) {
            updateRememberedValue(state2);
        }
        if (getInserting()) {
            providers = parentScope.putValue(local, state2);
            invalid = false;
            this.writerHasAProvider = true;
        } else {
            Object groupAux = this.reader.groupAux(this.reader.getCurrent());
            Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope = (PersistentCompositionLocalMap) groupAux;
            if ((!getSkipping() || change) && (value.getCanOverride() || !CompositionLocalMapKt.contains(parentScope, local))) {
                putValue = parentScope.putValue(local, state2);
            } else {
                putValue = oldScope;
            }
            providers = putValue;
            if (!this.reusing && oldScope == providers) {
                z = false;
            }
            invalid = z;
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(providers);
        }
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = invalid;
        this.providerCache = providers;
        m2917startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2927getGroupULZAiWs(), providers);
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        IntMap providerUpdates = this.providerUpdates;
        if (providerUpdates == null) {
            ComposerImpl $this$recordProviderUpdate_u24lambda_u2413 = this;
            IntMap newProviderUpdates = new IntMap(0, 1, null);
            $this$recordProviderUpdate_u24lambda_u2413.providerUpdates = newProviderUpdates;
            providerUpdates = newProviderUpdates;
        }
        providerUpdates.set(this.reader.getCurrent(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        boolean asBool;
        endGroup();
        endGroup();
        asBool = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providersInvalid = asBool;
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProviders(ProvidedValue<?>[] values) {
        PersistentCompositionLocalMap providers;
        boolean invalid;
        int asInt;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(201, ComposerKt.getProvider());
        boolean z = true;
        if (getInserting()) {
            providers = updateProviderMapGroup(parentScope, CompositionLocalMapKt.updateCompositionMap$default(values, parentScope, null, 4, null));
            invalid = false;
            this.writerHasAProvider = true;
        } else {
            Object groupGet = this.reader.groupGet(0);
            Intrinsics.checkNotNull(groupGet, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope = (PersistentCompositionLocalMap) groupGet;
            Object groupGet2 = this.reader.groupGet(1);
            Intrinsics.checkNotNull(groupGet2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldValues = (PersistentCompositionLocalMap) groupGet2;
            PersistentCompositionLocalMap currentProviders = CompositionLocalMapKt.updateCompositionMap(values, parentScope, oldValues);
            if (!getSkipping() || this.reusing || !Intrinsics.areEqual(oldValues, currentProviders)) {
                providers = updateProviderMapGroup(parentScope, currentProviders);
                if (!this.reusing && Intrinsics.areEqual(providers, oldScope)) {
                    z = false;
                }
                invalid = z;
            } else {
                skipGroup();
                providers = oldScope;
                invalid = false;
            }
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(providers);
        }
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = invalid;
        this.providerCache = providers;
        m2917startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2927getGroupULZAiWs(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        boolean asBool;
        endGroup();
        endGroup();
        asBool = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providersInvalid = asBool;
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        startGroup(206, ComposerKt.getReference());
        if (getInserting()) {
            SlotWriter.markGroup$default(this.writer, 0, 1, null);
        }
        Object nextSlot = nextSlot();
        CompositionContextHolder holder = nextSlot instanceof CompositionContextHolder ? (CompositionContextHolder) nextSlot : null;
        if (holder == null) {
            int compoundKeyHash = getCompoundKeyHash();
            boolean z = this.forceRecomposeScopes;
            boolean z2 = this.sourceInformationEnabled;
            ControlledComposition composition = getComposition();
            CompositionImpl compositionImpl = composition instanceof CompositionImpl ? (CompositionImpl) composition : null;
            holder = new CompositionContextHolder(new CompositionContextImpl(compoundKeyHash, z, z2, compositionImpl != null ? compositionImpl.getObserverHolder() : null));
            updateValue(holder);
        }
        holder.getRef().updateCompositionLocalScope(currentCompositionLocalScope());
        endGroup();
        return holder.getRef();
    }

    public final int getChangeCount$runtime_release() {
        return this.changes.getSize();
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        Stack it = this.invalidateStack;
        if (this.childrenComposing == 0 && it.isNotEmpty()) {
            return it.peek();
        }
        return null;
    }

    private final void ensureWriter() {
        if (this.writer.getClosed()) {
            this.writer = this.insertTable.openWriter();
            this.writer.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void createFreshInsertTable() {
        ComposerKt.runtimeCheck(this.writer.getClosed());
        this.insertTable = new SlotTable();
        SlotWriter it = this.insertTable.openWriter();
        it.close();
        this.writer = it;
    }

    private final void startReaderGroup(boolean isNode, Object data) {
        if (isNode) {
            this.reader.startNode();
            return;
        }
        if (data != null && this.reader.getGroupAux() != data) {
            this.changeListWriter.updateAuxData(data);
        }
        this.reader.startGroup();
    }

    /* renamed from: start-BaiHCIY, reason: not valid java name */
    private final void m2917startBaiHCIY(int key, Object objectKey, int kind, Object data) {
        Pending newPending;
        validateNodeNotExpected();
        updateCompoundKeyWhenWeEnterGroup(key, objectKey, data);
        boolean isNode = kind != GroupKind.INSTANCE.m2927getGroupULZAiWs();
        if (getInserting()) {
            this.reader.beginEmpty();
            int startIndex = this.writer.getCurrentGroup();
            if (isNode) {
                this.writer.startNode(key, Composer.INSTANCE.getEmpty());
            } else if (data != null) {
                this.writer.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
            } else {
                this.writer.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
            }
            Pending pending = this.pending;
            if (pending != null) {
                KeyInfo insertKeyInfo = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex), -1, 0);
                pending.registerInsert(insertKeyInfo, this.nodeIndex - pending.getStartIndex());
                pending.recordUsed(insertKeyInfo);
            }
            enterGroup(isNode, null);
            return;
        }
        boolean forceReplace = (kind != GroupKind.INSTANCE.m2928getNodeULZAiWs() ? 1 : 0) == 0 && this.reusing;
        if (this.pending == null) {
            int slotKey = this.reader.getGroupKey();
            if (forceReplace || slotKey != key || !Intrinsics.areEqual(objectKey, this.reader.getGroupObjectKey())) {
                this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
            } else {
                startReaderGroup(isNode, data);
            }
        }
        Pending pending2 = this.pending;
        if (pending2 != null) {
            KeyInfo keyInfo = pending2.getNext(key, objectKey);
            if (!forceReplace && keyInfo != null) {
                pending2.recordUsed(keyInfo);
                int location = keyInfo.getLocation();
                this.nodeIndex = pending2.nodePositionOf(keyInfo) + pending2.getStartIndex();
                int relativePosition = pending2.slotPositionOf(keyInfo);
                int currentRelativePosition = relativePosition - pending2.getGroupIndex();
                pending2.registerMoveSlot(relativePosition, pending2.getGroupIndex());
                this.changeListWriter.moveReaderRelativeTo(location);
                this.reader.reposition(location);
                if (currentRelativePosition > 0) {
                    this.changeListWriter.moveCurrentGroup(currentRelativePosition);
                }
                startReaderGroup(isNode, data);
            } else {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureWriter();
                this.writer.beginInsert();
                int startIndex2 = this.writer.getCurrentGroup();
                if (isNode) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else if (data != null) {
                    this.writer.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
                } else {
                    this.writer.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
                }
                this.insertAnchor = this.writer.anchor(startIndex2);
                KeyInfo insertKeyInfo2 = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex2), -1, 0);
                pending2.registerInsert(insertKeyInfo2, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(insertKeyInfo2);
                Pending newPending2 = new Pending(new ArrayList(), isNode ? 0 : this.nodeIndex);
                newPending = newPending2;
                enterGroup(isNode, newPending);
            }
        }
        newPending = null;
        enterGroup(isNode, newPending);
    }

    private final void enterGroup(boolean isNode, Pending newPending) {
        this.pendingStack.push(this.pending);
        this.pending = newPending;
        this.nodeIndexStack.push(this.nodeIndex);
        if (isNode) {
            this.nodeIndex = 0;
        }
        this.groupNodeCountStack.push(this.groupNodeCount);
        this.groupNodeCount = 0;
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        Pending previousPending = this.pendingStack.pop();
        if (previousPending != null && !inserting) {
            previousPending.setGroupIndex(previousPending.getGroupIndex() + 1);
        }
        this.pending = previousPending;
        this.nodeIndex = this.nodeIndexStack.pop() + expectedNodeCount;
        this.groupNodeCount = this.groupNodeCountStack.pop() + expectedNodeCount;
    }

    private final void end(boolean isNode) {
        List current;
        if (getInserting()) {
            int parent = this.writer.getParent();
            updateCompoundKeyWhenWeExitGroup(this.writer.groupKey(parent), this.writer.groupObjectKey(parent), this.writer.groupAux(parent));
        } else {
            int parent2 = this.reader.getParent();
            updateCompoundKeyWhenWeExitGroup(this.reader.groupKey(parent2), this.reader.groupObjectKey(parent2), this.reader.groupAux(parent2));
        }
        int expectedNodeCount = this.groupNodeCount;
        Pending pending = this.pending;
        if (pending != null && pending.getKeyInfos().size() > 0) {
            List previous = pending.getKeyInfos();
            List current2 = pending.getUsed();
            Set usedKeys = ListUtilsKt.fastToSet(current2);
            Set placedKeys = new LinkedHashSet();
            int currentIndex = 0;
            int currentEnd = current2.size();
            int previousIndex = 0;
            int previousEnd = previous.size();
            int nodeOffset = 0;
            while (previousIndex < previousEnd) {
                KeyInfo previousInfo = previous.get(previousIndex);
                if (!usedKeys.contains(previousInfo)) {
                    int deleteOffset = pending.nodePositionOf(previousInfo);
                    Set usedKeys2 = usedKeys;
                    this.changeListWriter.removeNode(deleteOffset + pending.getStartIndex(), previousInfo.getNodes());
                    pending.updateNodeCount(previousInfo.getLocation(), 0);
                    this.changeListWriter.moveReaderRelativeTo(previousInfo.getLocation());
                    this.reader.reposition(previousInfo.getLocation());
                    recordDelete();
                    this.reader.skipGroup();
                    List<Invalidation> list = this.invalidations;
                    int location = previousInfo.getLocation();
                    int location2 = previousInfo.getLocation();
                    int previousEnd2 = previousEnd;
                    SlotReader slotReader = this.reader;
                    int deleteOffset2 = previousInfo.getLocation();
                    ComposerKt.removeRange(list, location, location2 + slotReader.groupSize(deleteOffset2));
                    previousIndex++;
                    usedKeys = usedKeys2;
                    previousEnd = previousEnd2;
                } else {
                    Set usedKeys3 = usedKeys;
                    int previousEnd3 = previousEnd;
                    if (placedKeys.contains(previousInfo)) {
                        previousIndex++;
                        usedKeys = usedKeys3;
                        previousEnd = previousEnd3;
                    } else if (currentIndex < currentEnd) {
                        KeyInfo currentInfo = current2.get(currentIndex);
                        if (currentInfo != previousInfo) {
                            int nodePosition = pending.nodePositionOf(currentInfo);
                            placedKeys.add(currentInfo);
                            if (nodePosition == nodeOffset) {
                                current = current2;
                            } else {
                                int updatedCount = pending.updatedNodeCountOf(currentInfo);
                                current = current2;
                                this.changeListWriter.moveNode(pending.getStartIndex() + nodePosition, nodeOffset + pending.getStartIndex(), updatedCount);
                                pending.registerMoveNode(nodePosition, nodeOffset, updatedCount);
                            }
                        } else {
                            current = current2;
                            previousIndex++;
                        }
                        currentIndex++;
                        nodeOffset += pending.updatedNodeCountOf(currentInfo);
                        usedKeys = usedKeys3;
                        previousEnd = previousEnd3;
                        current2 = current;
                    } else {
                        usedKeys = usedKeys3;
                        previousEnd = previousEnd3;
                    }
                }
            }
            this.changeListWriter.endNodeMovement();
            if (previous.size() > 0) {
                this.changeListWriter.moveReaderRelativeTo(this.reader.getGroupEnd());
                this.reader.skipToGroupEnd();
            }
        }
        int removeIndex = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int startSlot = this.reader.getCurrent();
            recordDelete();
            int nodesToRemove = this.reader.skipGroup();
            this.changeListWriter.removeNode(removeIndex, nodesToRemove);
            ComposerKt.removeRange(this.invalidations, startSlot, this.reader.getCurrent());
        }
        boolean inserting = getInserting();
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                expectedNodeCount = 1;
            }
            this.reader.endEmpty();
            int parentGroup = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int virtualIndex = insertedGroupVirtualIndex(parentGroup);
                this.writer.endInsert();
                this.writer.close();
                recordInsert(this.insertAnchor);
                this.inserting = false;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(virtualIndex, 0);
                    updateNodeCountOverrides(virtualIndex, expectedNodeCount);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            this.changeListWriter.endCurrentGroup();
            int parentGroup2 = this.reader.getParent();
            int parentNodeCount = updatedNodeCount(parentGroup2);
            if (expectedNodeCount != parentNodeCount) {
                updateNodeCountOverrides(parentGroup2, expectedNodeCount);
            }
            if (isNode) {
                expectedNodeCount = 1;
            }
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(expectedNodeCount, inserting);
    }

    private final void recomposeToGroupEnd() {
        Invalidation firstInRange;
        boolean wasComposing = this.isComposing;
        this.isComposing = true;
        boolean recomposed = false;
        int parent = this.reader.getParent();
        int end = this.reader.groupSize(parent) + parent;
        int recomposeIndex = this.nodeIndex;
        int recomposeCompoundKey = getCompoundKeyHash();
        int oldGroupNodeCount = this.groupNodeCount;
        int oldGroup = parent;
        firstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        while (firstInRange != null) {
            int location = firstInRange.getLocation();
            ComposerKt.removeLocation(this.invalidations, location);
            if (firstInRange.isInvalid()) {
                recomposed = true;
                this.reader.reposition(location);
                int newGroup = this.reader.getCurrent();
                recordUpsAndDowns(oldGroup, newGroup, parent);
                oldGroup = newGroup;
                this.nodeIndex = nodeIndexOf(location, newGroup, parent, recomposeIndex);
                this.compoundKeyHash = compoundKeyOf(this.reader.parent(newGroup), parent, recomposeCompoundKey);
                this.providerCache = null;
                firstInRange.getScope().compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
            } else {
                this.invalidateStack.push(firstInRange.getScope());
                firstInRange.getScope().rereadTrackedInstances();
                this.invalidateStack.pop();
            }
            firstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        }
        if (recomposed) {
            recordUpsAndDowns(oldGroup, parent, parent);
            this.reader.skipToGroupEnd();
            int parentGroupNodes = updatedNodeCount(parent);
            this.nodeIndex = recomposeIndex + parentGroupNodes;
            this.groupNodeCount = oldGroupNodeCount + parentGroupNodes;
        } else {
            skipReaderToGroupEnd();
        }
        this.compoundKeyHash = recomposeCompoundKey;
        this.isComposing = wasComposing;
    }

    private final int insertedGroupVirtualIndex(int index) {
        return (-2) - index;
    }

    private final void updateNodeCountOverrides(int group, int newCount) {
        int currentCount = updatedNodeCount(group);
        if (currentCount != newCount) {
            int delta = newCount - currentCount;
            int current = group;
            int minPending = this.pendingStack.getSize() - 1;
            while (current != -1) {
                int newCurrentNodes = updatedNodeCount(current) + delta;
                updateNodeCount(current, newCurrentNodes);
                int pendingIndex = minPending;
                while (true) {
                    if (-1 < pendingIndex) {
                        Pending pending = this.pendingStack.peek(pendingIndex);
                        if (pending == null || !pending.updateNodeCount(current, newCurrentNodes)) {
                            pendingIndex--;
                        } else {
                            minPending = pendingIndex - 1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (current < 0) {
                    current = this.reader.getParent();
                } else if (!this.reader.isNode(current)) {
                    current = this.reader.parent(current);
                } else {
                    return;
                }
            }
        }
    }

    private final int nodeIndexOf(int groupLocation, int group, int recomposeGroup, int recomposeIndex) {
        int anchorGroup = this.reader.parent(group);
        while (anchorGroup != recomposeGroup && !this.reader.isNode(anchorGroup)) {
            anchorGroup = this.reader.parent(anchorGroup);
        }
        int index = this.reader.isNode(anchorGroup) ? 0 : recomposeIndex;
        if (anchorGroup == group) {
            return index;
        }
        int current = anchorGroup;
        int nodeIndexLimit = (updatedNodeCount(anchorGroup) - this.reader.nodeCount(group)) + index;
        loop1: while (index < nodeIndexLimit && current != groupLocation) {
            current++;
            while (current < groupLocation) {
                int end = this.reader.groupSize(current) + current;
                if (groupLocation >= end) {
                    index += updatedNodeCount(current);
                    current = end;
                }
            }
            break loop1;
        }
        return index;
    }

    private final int updatedNodeCount(int group) {
        int override;
        if (group < 0) {
            MutableIntIntMap it = this.nodeCountVirtualOverrides;
            if (it == null || !it.contains(group)) {
                return 0;
            }
            return it.get(group);
        }
        int[] nodeCounts = this.nodeCountOverrides;
        return (nodeCounts == null || (override = nodeCounts[group]) < 0) ? this.reader.nodeCount(group) : override;
    }

    private final void updateNodeCount(int group, int count) {
        if (updatedNodeCount(group) != count) {
            if (group < 0) {
                MutableIntIntMap virtualCounts = this.nodeCountVirtualOverrides;
                if (virtualCounts == null) {
                    ComposerImpl $this$updateNodeCount_u24lambda_u2418 = this;
                    MutableIntIntMap newCounts = new MutableIntIntMap(0, 1, null);
                    $this$updateNodeCount_u24lambda_u2418.nodeCountVirtualOverrides = newCounts;
                    virtualCounts = newCounts;
                }
                virtualCounts.set(group, count);
                return;
            }
            int[] nodeCounts = this.nodeCountOverrides;
            if (nodeCounts == null) {
                ComposerImpl $this$updateNodeCount_u24lambda_u2419 = this;
                int[] newCounts2 = new int[$this$updateNodeCount_u24lambda_u2419.reader.getGroupsSize()];
                ArraysKt.fill$default(newCounts2, -1, 0, 0, 6, (Object) null);
                $this$updateNodeCount_u24lambda_u2419.nodeCountOverrides = newCounts2;
                nodeCounts = newCounts2;
            }
            nodeCounts[group] = count;
        }
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final void recordUpsAndDowns(int oldGroup, int newGroup, int commonRoot) {
        int nearestCommonRoot;
        SlotReader reader = this.reader;
        nearestCommonRoot = ComposerKt.nearestCommonRootOf(reader, oldGroup, newGroup, commonRoot);
        for (int current = oldGroup; current > 0 && current != nearestCommonRoot; current = reader.parent(current)) {
            if (reader.isNode(current)) {
                this.changeListWriter.moveUp();
            }
        }
        doRecordDownsFor(newGroup, nearestCommonRoot);
    }

    private final void doRecordDownsFor(int group, int nearestCommonRoot) {
        if (group > 0 && group != nearestCommonRoot) {
            doRecordDownsFor(this.reader.parent(group), nearestCommonRoot);
            if (this.reader.isNode(group)) {
                this.changeListWriter.moveDown(nodeAt(this.reader, group));
            }
        }
    }

    private final int compoundKeyOf(int group, int recomposeGroup, int recomposeKey) {
        if (group == recomposeGroup) {
            return recomposeKey;
        }
        ComposerImpl $this$compoundKeyOf_u24lambda_u2420 = this;
        int groupKey = $this$compoundKeyOf_u24lambda_u2420.groupCompoundKeyPart($this$compoundKeyOf_u24lambda_u2420.reader, group);
        if (groupKey == 126665345) {
            return groupKey;
        }
        int $this$rol$iv = $this$compoundKeyOf_u24lambda_u2420.compoundKeyOf($this$compoundKeyOf_u24lambda_u2420.reader.parent(group), recomposeGroup, recomposeKey);
        return Integer.rotateLeft($this$rol$iv, 3) ^ groupKey;
    }

    private final int groupCompoundKeyPart(SlotReader $this$groupCompoundKeyPart, int group) {
        Object aux;
        if ($this$groupCompoundKeyPart.hasObjectKey(group)) {
            Object it = $this$groupCompoundKeyPart.groupObjectKey(group);
            if (it != null) {
                return it instanceof Enum ? ((Enum) it).ordinal() : it instanceof MovableContent ? MovableContentKt.movableContentKey : it.hashCode();
            }
            return 0;
        }
        int it2 = $this$groupCompoundKeyPart.groupKey(group);
        if (it2 != 207 || (aux = $this$groupCompoundKeyPart.groupAux(group)) == null) {
            return it2;
        }
        return Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty()) ? it2 : aux.hashCode();
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl scope, Object instance) {
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        SlotTable slotTable = this.reader.getTable();
        int location = anchor.toIndexFor(slotTable);
        if (!this.isComposing || location < this.reader.getCurrent()) {
            return false;
        }
        ComposerKt.insertIfMissing(this.invalidations, location, scope, instance);
        return true;
    }

    public final int parentKey$runtime_release() {
        if (getInserting()) {
            return this.writer.groupKey(this.writer.getParent());
        }
        return this.reader.groupKey(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipCurrentGroup() {
        if (this.invalidations.isEmpty()) {
            skipGroup();
            return;
        }
        SlotReader reader = this.reader;
        int key = reader.getGroupKey();
        Object dataKey = reader.getGroupObjectKey();
        Object aux = reader.getGroupAux();
        updateCompoundKeyWhenWeEnterGroup(key, dataKey, aux);
        startReaderGroup(reader.isNode(), null);
        recomposeToGroupEnd();
        reader.endGroup();
        updateCompoundKeyWhenWeExitGroup(key, dataKey, aux);
    }

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodes();
        this.reader.skipToGroupEnd();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipToGroupEnd() {
        boolean value$iv = this.groupNodeCount == 0;
        if (value$iv) {
            RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
            if (currentRecomposeScope$runtime_release != null) {
                currentRecomposeScope$runtime_release.scopeSkipped();
            }
            if (this.invalidations.isEmpty()) {
                skipReaderToGroupEnd();
                return;
            } else {
                recomposeToGroupEnd();
                return;
            }
        }
        ComposerKt.composeRuntimeError("No nodes can be emitted before calling skipAndEndGroup".toString());
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void deactivateToEndGroup(boolean changed) {
        boolean value$iv = this.groupNodeCount == 0;
        if (value$iv) {
            boolean value$iv2 = getInserting();
            if (!value$iv2) {
                if (!changed) {
                    skipReaderToGroupEnd();
                    return;
                }
                int start = this.reader.getCurrent();
                int end = this.reader.getEnd();
                this.changeListWriter.deactivateCurrentGroup();
                ComposerKt.removeRange(this.invalidations, start, end);
                this.reader.skipToGroupEnd();
                return;
            }
            return;
        }
        ComposerKt.composeRuntimeError("No nodes can be emitted before calling dactivateToEndGroup".toString());
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Composer startRestartGroup(int key) {
        m2917startBaiHCIY(key, null, GroupKind.INSTANCE.m2927getGroupULZAiWs(), null);
        addRecomposeScope();
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0074, code lost:
    
        if (r3 != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addRecomposeScope() {
        /*
            r7 = this;
            boolean r0 = r7.getInserting()
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl"
            if (r0 == 0) goto L27
            androidx.compose.runtime.RecomposeScopeImpl r0 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r2 = r7.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            androidx.compose.runtime.CompositionImpl r2 = (androidx.compose.runtime.CompositionImpl) r2
            androidx.compose.runtime.RecomposeScopeOwner r2 = (androidx.compose.runtime.RecomposeScopeOwner) r2
            r0.<init>(r2)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r1 = r7.invalidateStack
            r1.push(r0)
            r7.updateValue(r0)
            int r1 = r7.compositionToken
            r0.start(r1)
            goto L84
        L27:
            java.util.List<androidx.compose.runtime.Invalidation> r0 = r7.invalidations
            androidx.compose.runtime.SlotReader r2 = r7.reader
            int r2 = r2.getParent()
            androidx.compose.runtime.Invalidation r0 = androidx.compose.runtime.ComposerKt.access$removeLocation(r0, r2)
            androidx.compose.runtime.SlotReader r2 = r7.reader
            java.lang.Object r2 = r2.next()
            androidx.compose.runtime.Composer$Companion r3 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r3 = r3.getEmpty()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L5b
            androidx.compose.runtime.RecomposeScopeImpl r3 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r4 = r7.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r1)
            androidx.compose.runtime.CompositionImpl r4 = (androidx.compose.runtime.CompositionImpl) r4
            androidx.compose.runtime.RecomposeScopeOwner r4 = (androidx.compose.runtime.RecomposeScopeOwner) r4
            r3.<init>(r4)
            r1 = r3
            r7.updateValue(r1)
            goto L64
        L5b:
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            r1 = r2
            androidx.compose.runtime.RecomposeScopeImpl r1 = (androidx.compose.runtime.RecomposeScopeImpl) r1
        L64:
            if (r0 != 0) goto L76
            boolean r3 = r1.getForcedRecompose()
            r4 = r3
            r5 = 0
            r6 = 0
            if (r4 == 0) goto L73
            r1.setForcedRecompose(r6)
        L73:
            if (r3 == 0) goto L77
        L76:
            r6 = 1
        L77:
            r1.setRequiresRecompose(r6)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r3 = r7.invalidateStack
            r3.push(r1)
            int r3 = r7.compositionToken
            r1.start(r3)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.addRecomposeScope():void");
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public ScopeUpdateScope endRestartGroup() {
        Anchor anchor;
        Function1 it;
        RecomposeScopeImpl result = null;
        RecomposeScopeImpl scope = this.invalidateStack.isNotEmpty() ? this.invalidateStack.pop() : null;
        if (scope != null) {
            scope.setRequiresRecompose(false);
        }
        if (scope != null && (it = scope.end(this.compositionToken)) != null) {
            this.changeListWriter.endCompositionScope(it, getComposition());
        }
        if (scope != null && !scope.getSkipped$runtime_release() && (scope.getUsed() || this.forceRecomposeScopes)) {
            if (scope.getAnchor() == null) {
                if (getInserting()) {
                    anchor = this.writer.anchor(this.writer.getParent());
                } else {
                    anchor = this.reader.anchor(this.reader.getParent());
                }
                scope.setAnchor(anchor);
            }
            scope.setDefaultsInvalid(false);
            result = scope;
        }
        end(false);
        return result;
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeMovableContentLambda(final MovableContent<Object> content, PersistentCompositionLocalMap locals, final Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        int savedCompoundKeyHash = getCompoundKeyHash();
        try {
            this.compoundKeyHash = MovableContentKt.movableContentKey;
            boolean z = false;
            if (getInserting()) {
                SlotWriter.markGroup$default(this.writer, 0, 1, null);
            }
            if (!getInserting() && !Intrinsics.areEqual(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            boolean providersChanged = z;
            if (providersChanged) {
                recordProviderUpdate(locals);
            }
            m2917startBaiHCIY(202, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m2927getGroupULZAiWs(), locals);
            this.providerCache = null;
            if (getInserting() && !force) {
                this.writerHasAProvider = true;
                Anchor anchor = this.writer.anchor(this.writer.parent(this.writer.getParent()));
                MovableContentStateReference reference = new MovableContentStateReference(content, parameter, getComposition(), this.insertTable, anchor, CollectionsKt.emptyList(), currentCompositionLocalScope());
                this.parentContext.insertMovableContent$runtime_release(reference);
            } else {
                boolean savedProvidersInvalid = this.providersInvalid;
                this.providersInvalid = providersChanged;
                ActualJvm_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(316014703, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "C3004@113980L18:Composer.kt#9igjgp");
                        if (($changed & 11) == 2 && $composer.getSkipping()) {
                            $composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(316014703, $changed, -1, "androidx.compose.runtime.ComposerImpl.invokeMovableContentLambda.<anonymous> (Composer.kt:3004)");
                        }
                        content.getContent().invoke(parameter, $composer, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                this.providersInvalid = savedProvidersInvalid;
            }
        } finally {
            endGroup();
            this.providerCache = null;
            this.compoundKeyHash = savedCompoundKeyHash;
            endMovableGroup();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        try {
            insertMovableContentGuarded(references);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    private final void insertMovableContentGuarded(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        int i;
        ChangeList newChangeList$iv;
        List $this$fastForEach$iv;
        int $i$f$withChangeList;
        int i2;
        SlotTable slotTable;
        Anchor anchor;
        List nodesToInsert;
        Anchor anchor2;
        SlotReader savedReader$iv;
        int[] savedCountOverrides$iv;
        IntMap savedProviderUpdates$iv;
        ChangeList offsetChanges;
        ComposerChangeListWriter this_$iv;
        ChangeList previousChangeList$iv;
        ChangeList previousChangeList$iv2;
        boolean previousImplicitRootStart$iv;
        SlotTable slotTable2;
        ComposerChangeListWriter this_$iv2 = this.changeListWriter;
        ChangeList newChangeList$iv2 = this.lateChanges;
        int $i$f$withChangeList2 = 0;
        ChangeList previousChangeList$iv3 = this_$iv2.getChangeList();
        try {
            this_$iv2.setChangeList(newChangeList$iv2);
            int i3 = 0;
            this.changeListWriter.resetSlots();
            List $this$fastForEach$iv2 = references;
            int size = $this$fastForEach$iv2.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = $this$fastForEach$iv2.get(index$iv);
                Pair<MovableContentStateReference, MovableContentStateReference> pair = (Pair) item$iv;
                final MovableContentStateReference to = pair.component1();
                MovableContentStateReference from = pair.component2();
                Anchor anchor3 = to.getAnchor();
                int location = to.getSlotTable().anchorIndex(anchor3);
                int index$iv2 = index$iv;
                IntRef effectiveNodeIndex = new IntRef(0, 1, null);
                this.changeListWriter.determineMovableContentNodeIndex(effectiveNodeIndex, anchor3);
                if (from != null) {
                    i = size;
                    MovableContentState resolvedState = this.parentContext.movableContentStateResolve$runtime_release(from);
                    if (resolvedState == null || (slotTable = resolvedState.getSlotTable()) == null) {
                        slotTable = from.getSlotTable();
                    }
                    SlotTable fromTable = slotTable;
                    if (resolvedState == null || (slotTable2 = resolvedState.getSlotTable()) == null || (anchor = slotTable2.anchor(0)) == null) {
                        anchor = from.getAnchor();
                    }
                    Anchor fromAnchor = anchor;
                    nodesToInsert = ComposerKt.collectNodesFrom(fromTable, fromAnchor);
                    if (!(!nodesToInsert.isEmpty())) {
                        anchor2 = anchor3;
                    } else {
                        this.changeListWriter.copyNodesToNewAnchorLocation(nodesToInsert, effectiveNodeIndex);
                        if (!Intrinsics.areEqual(to.getSlotTable(), this.slotTable)) {
                            anchor2 = anchor3;
                        } else {
                            anchor2 = anchor3;
                            int group = this.slotTable.anchorIndex(anchor2);
                            updateNodeCount(group, updatedNodeCount(group) + nodesToInsert.size());
                        }
                    }
                    this.changeListWriter.copySlotTableToAnchorLocation(resolvedState, this.parentContext, from, to);
                    SlotReader reader$iv = fromTable.openReader();
                    try {
                        SlotReader savedReader$iv2 = getReader();
                        int[] savedCountOverrides$iv2 = this.nodeCountOverrides;
                        IntMap savedProviderUpdates$iv2 = this.providerUpdates;
                        newChangeList$iv = newChangeList$iv2;
                        try {
                            this.nodeCountOverrides = null;
                            this.providerUpdates = null;
                            try {
                                setReader$runtime_release(reader$iv);
                                int newLocation = fromTable.anchorIndex(fromAnchor);
                                $i$f$withChangeList = $i$f$withChangeList2;
                                try {
                                    reader$iv.reposition(newLocation);
                                    this.changeListWriter.moveReaderToAbsolute(newLocation);
                                    offsetChanges = new ChangeList();
                                    this_$iv = this.changeListWriter;
                                    previousChangeList$iv = this_$iv.getChangeList();
                                    i2 = i3;
                                } catch (Throwable th) {
                                    th = th;
                                    savedReader$iv = savedReader$iv2;
                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                    savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                savedReader$iv = savedReader$iv2;
                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                savedProviderUpdates$iv = savedProviderUpdates$iv2;
                            }
                            try {
                                this_$iv.setChangeList(offsetChanges);
                                ComposerChangeListWriter this_$iv3 = this.changeListWriter;
                                boolean previousImplicitRootStart$iv2 = this_$iv3.getImplicitRootStart();
                                $this$fastForEach$iv = $this$fastForEach$iv2;
                                try {
                                    this_$iv3.setImplicitRootStart(false);
                                    try {
                                        recomposeMovableContent(from.getComposition(), to.getComposition(), Integer.valueOf(reader$iv.getCurrent()), from.getInvalidations$runtime_release(), new Function0<Unit>() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$2$1$1$1$1
                                            /* JADX INFO: Access modifiers changed from: package-private */
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                ComposerImpl.this.invokeMovableContentLambda(to.getContent$runtime_release(), to.getLocals(), to.getParameter(), true);
                                            }
                                        });
                                        try {
                                            this_$iv3.setImplicitRootStart(previousImplicitRootStart$iv2);
                                            try {
                                                this_$iv.setChangeList(previousChangeList$iv);
                                                this.changeListWriter.includeOperationsIn(offsetChanges, effectiveNodeIndex);
                                                Unit unit = Unit.INSTANCE;
                                                try {
                                                    setReader$runtime_release(savedReader$iv2);
                                                    this.nodeCountOverrides = savedCountOverrides$iv2;
                                                    this.providerUpdates = savedProviderUpdates$iv2;
                                                    Unit unit2 = Unit.INSTANCE;
                                                    try {
                                                        reader$iv.close();
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        this_$iv2.setChangeList(previousChangeList$iv3);
                                                        throw th;
                                                    }
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    reader$iv.close();
                                                    throw th;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                savedReader$iv = savedReader$iv2;
                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                                setReader$runtime_release(savedReader$iv);
                                                this.nodeCountOverrides = savedCountOverrides$iv;
                                                this.providerUpdates = savedProviderUpdates$iv;
                                                throw th;
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            savedReader$iv = savedReader$iv2;
                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                            savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                            previousChangeList$iv2 = previousChangeList$iv;
                                            try {
                                                this_$iv.setChangeList(previousChangeList$iv2);
                                                throw th;
                                            } catch (Throwable th7) {
                                                th = th7;
                                                setReader$runtime_release(savedReader$iv);
                                                this.nodeCountOverrides = savedCountOverrides$iv;
                                                this.providerUpdates = savedProviderUpdates$iv;
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        savedReader$iv = savedReader$iv2;
                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                        savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                        previousChangeList$iv2 = previousChangeList$iv;
                                        previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                        try {
                                            this_$iv3.setImplicitRootStart(previousImplicitRootStart$iv);
                                            throw th;
                                        } catch (Throwable th9) {
                                            th = th9;
                                            this_$iv.setChangeList(previousChangeList$iv2);
                                            throw th;
                                        }
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    savedReader$iv = savedReader$iv2;
                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                    savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                    previousChangeList$iv2 = previousChangeList$iv;
                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                savedReader$iv = savedReader$iv2;
                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                previousChangeList$iv2 = previousChangeList$iv;
                            }
                        } catch (Throwable th12) {
                            th = th12;
                        }
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } else {
                    try {
                        SlotTable toSlotTable = to.getSlotTable();
                        if (Intrinsics.areEqual(toSlotTable, this.insertTable)) {
                            createFreshInsertTable();
                        }
                        final SlotReader reader$iv2 = to.getSlotTable().openReader();
                        try {
                            reader$iv2.reposition(location);
                            this.changeListWriter.moveReaderToAbsolute(location);
                            final ChangeList offsetChanges2 = new ChangeList();
                            try {
                                i = size;
                                try {
                                    recomposeMovableContent$default(this, null, null, null, null, new Function0<Unit>() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$1$1
                                        /* JADX INFO: Access modifiers changed from: package-private */
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(0);
                                        }

                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            ComposerChangeListWriter this_$iv4;
                                            ComposerChangeListWriter this_$iv5;
                                            boolean previousImplicitRootStart$iv3;
                                            this_$iv4 = ComposerImpl.this.changeListWriter;
                                            ChangeList newChangeList$iv3 = offsetChanges2;
                                            ComposerImpl this_$iv6 = ComposerImpl.this;
                                            SlotReader slotReader = reader$iv2;
                                            MovableContentStateReference movableContentStateReference = to;
                                            ChangeList previousChangeList$iv4 = this_$iv4.getChangeList();
                                            try {
                                                try {
                                                    this_$iv4.setChangeList(newChangeList$iv3);
                                                    SlotReader savedReader$iv3 = this_$iv6.getReader();
                                                    int[] savedCountOverrides$iv3 = this_$iv6.nodeCountOverrides;
                                                    IntMap savedProviderUpdates$iv3 = this_$iv6.providerUpdates;
                                                    this_$iv6.nodeCountOverrides = null;
                                                    this_$iv6.providerUpdates = null;
                                                    try {
                                                        this_$iv6.setReader$runtime_release(slotReader);
                                                        this_$iv5 = this_$iv6.changeListWriter;
                                                        boolean previousImplicitRootStart$iv4 = this_$iv5.getImplicitRootStart();
                                                        try {
                                                            try {
                                                                this_$iv5.setImplicitRootStart(false);
                                                                try {
                                                                } catch (Throwable th14) {
                                                                    th = th14;
                                                                    previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                                                }
                                                            } catch (Throwable th15) {
                                                                th = th15;
                                                                this_$iv6.setReader$runtime_release(savedReader$iv3);
                                                                this_$iv6.nodeCountOverrides = savedCountOverrides$iv3;
                                                                this_$iv6.providerUpdates = savedProviderUpdates$iv3;
                                                                throw th;
                                                            }
                                                        } catch (Throwable th16) {
                                                            th = th16;
                                                            previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                                        }
                                                        try {
                                                            this_$iv6.invokeMovableContentLambda(movableContentStateReference.getContent$runtime_release(), movableContentStateReference.getLocals(), movableContentStateReference.getParameter(), true);
                                                            this_$iv5.setImplicitRootStart(previousImplicitRootStart$iv4);
                                                            Unit unit3 = Unit.INSTANCE;
                                                            this_$iv6.setReader$runtime_release(savedReader$iv3);
                                                            this_$iv6.nodeCountOverrides = savedCountOverrides$iv3;
                                                            this_$iv6.providerUpdates = savedProviderUpdates$iv3;
                                                            this_$iv4.setChangeList(previousChangeList$iv4);
                                                        } catch (Throwable th17) {
                                                            th = th17;
                                                            previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                                            this_$iv5.setImplicitRootStart(previousImplicitRootStart$iv3);
                                                            throw th;
                                                        }
                                                    } catch (Throwable th18) {
                                                        th = th18;
                                                    }
                                                } catch (Throwable th19) {
                                                    th = th19;
                                                    this_$iv4.setChangeList(previousChangeList$iv4);
                                                    throw th;
                                                }
                                            } catch (Throwable th20) {
                                                th = th20;
                                                this_$iv4.setChangeList(previousChangeList$iv4);
                                                throw th;
                                            }
                                        }
                                    }, 15, null);
                                    try {
                                        this.changeListWriter.includeOperationsIn(offsetChanges2, effectiveNodeIndex);
                                        Unit unit3 = Unit.INSTANCE;
                                        reader$iv2.close();
                                        newChangeList$iv = newChangeList$iv2;
                                        $this$fastForEach$iv = $this$fastForEach$iv2;
                                        $i$f$withChangeList = $i$f$withChangeList2;
                                        i2 = i3;
                                    } catch (Throwable th14) {
                                        th = th14;
                                        reader$iv2.close();
                                        throw th;
                                    }
                                } catch (Throwable th15) {
                                    th = th15;
                                }
                            } catch (Throwable th16) {
                                th = th16;
                            }
                        } catch (Throwable th17) {
                            th = th17;
                        }
                    } catch (Throwable th18) {
                        th = th18;
                        this_$iv2.setChangeList(previousChangeList$iv3);
                        throw th;
                    }
                }
                this.changeListWriter.skipToEndOfCurrentGroup();
                index$iv = index$iv2 + 1;
                size = i;
                i3 = i2;
                newChangeList$iv2 = newChangeList$iv;
                $i$f$withChangeList2 = $i$f$withChangeList;
                $this$fastForEach$iv2 = $this$fastForEach$iv;
            }
            this.changeListWriter.endMovableContentPlacement();
            this.changeListWriter.moveReaderToAbsolute(0);
            this_$iv2.setChangeList(previousChangeList$iv3);
        } catch (Throwable th19) {
            th = th19;
        }
    }

    private final <R> R withReader(SlotReader reader, Function0<? extends R> block) {
        SlotReader savedReader = getReader();
        int[] savedCountOverrides = this.nodeCountOverrides;
        IntMap savedProviderUpdates = this.providerUpdates;
        this.nodeCountOverrides = null;
        this.providerUpdates = null;
        try {
            setReader$runtime_release(reader);
            return block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setReader$runtime_release(savedReader);
            this.nodeCountOverrides = savedCountOverrides;
            this.providerUpdates = savedProviderUpdates;
            InlineMarker.finallyEnd(1);
        }
    }

    static /* synthetic */ Object recomposeMovableContent$default(ComposerImpl composerImpl, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, Function0 function0, int i, Object obj) {
        return composerImpl.recomposeMovableContent((i & 1) != 0 ? null : controlledComposition, (i & 2) != 0 ? null : controlledComposition2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? CollectionsKt.emptyList() : list, function0);
    }

    private final <R> R recomposeMovableContent(ControlledComposition from, ControlledComposition to, Integer index, List<Pair<RecomposeScopeImpl, IdentityArraySet<Object>>> invalidations, Function0<? extends R> block) {
        R r;
        List<Pair<RecomposeScopeImpl, IdentityArraySet<Object>>> list;
        int i;
        boolean z = this.isComposing;
        int i2 = this.nodeIndex;
        try {
            this.isComposing = true;
            this.nodeIndex = 0;
            List<Pair<RecomposeScopeImpl, IdentityArraySet<Object>>> list2 = invalidations;
            int i3 = 0;
            int i4 = 0;
            int size = list2.size();
            while (i4 < size) {
                Pair<RecomposeScopeImpl, IdentityArraySet<Object>> pair = list2.get(i4);
                RecomposeScopeImpl component1 = pair.component1();
                IdentityArraySet<Object> component2 = pair.component2();
                if (component2 == null) {
                    list = list2;
                    i = i3;
                    tryImminentInvalidation$runtime_release(component1, null);
                } else {
                    Object[] values = component2.getValues();
                    int i5 = 0;
                    list = list2;
                    int size2 = component2.size();
                    while (i5 < size2) {
                        int i6 = size2;
                        Object obj = values[i5];
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                        tryImminentInvalidation$runtime_release(component1, obj);
                        i5++;
                        size2 = i6;
                        i3 = i3;
                    }
                    i = i3;
                }
                i4++;
                list2 = list;
                i3 = i;
            }
            if (from != null) {
                try {
                    r = (R) from.delegateInvalidations(to, index != null ? index.intValue() : -1, block);
                    if (r == null) {
                    }
                    this.isComposing = z;
                    this.nodeIndex = i2;
                    return r;
                } catch (Throwable th) {
                    th = th;
                    this.isComposing = z;
                    this.nodeIndex = i2;
                    throw th;
                }
            }
            r = block.invoke();
            this.isComposing = z;
            this.nodeIndex = i2;
            return r;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && this.sourceInformationEnabled) {
            this.writer.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && this.sourceInformationEnabled) {
            this.writer.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerEnd() {
        if (getInserting() && this.sourceInformationEnabled) {
            this.writer.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        this.sourceInformationEnabled = false;
    }

    public final void composeContent$runtime_release(IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        boolean value$iv = this.changes.isEmpty();
        if (value$iv) {
            doCompose(invalidationsRequested, content);
        } else {
            ComposerKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
            throw new KotlinNothingValueException();
        }
    }

    public final void prepareCompose$runtime_release(Function0<Unit> block) {
        boolean value$iv = !this.isComposing;
        if (value$iv) {
            this.isComposing = true;
            try {
                block.invoke();
                return;
            } finally {
                this.isComposing = false;
            }
        }
        ComposerKt.composeRuntimeError("Preparing a composition while composing is not supported".toString());
        throw new KotlinNothingValueException();
    }

    public final boolean recompose$runtime_release(IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidationsRequested) {
        boolean value$iv = this.changes.isEmpty();
        if (value$iv) {
            if (invalidationsRequested.isNotEmpty() || (!this.invalidations.isEmpty()) || this.forciblyRecompose) {
                doCompose(invalidationsRequested, null);
                return this.changes.isNotEmpty();
            }
            return false;
        }
        ComposerKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Finally extract failed */
    private final void doCompose(IdentityArrayMap<RecomposeScopeImpl, IdentityArraySet<Object>> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        Comparator comparator;
        boolean value$iv = !this.isComposing;
        if (!value$iv) {
            ComposerKt.composeRuntimeError("Reentrant composition is not supported".toString());
            throw new KotlinNothingValueException();
        }
        Object token$iv = Trace.INSTANCE.beginSection("Compose:recompose");
        try {
            this.compositionToken = SnapshotKt.currentSnapshot().getId();
            this.providerUpdates = null;
            int size = invalidationsRequested.getSize();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object obj = invalidationsRequested.getKeys()[index$iv];
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
                IdentityArraySet set = (IdentityArraySet) invalidationsRequested.getValues()[index$iv];
                RecomposeScopeImpl scope = (RecomposeScopeImpl) obj;
                Anchor anchor = scope.getAnchor();
                if (anchor == null) {
                    return;
                }
                int location = anchor.getLocation();
                this.invalidations.add(new Invalidation(scope, location, set));
            }
            List<Invalidation> list = this.invalidations;
            comparator = ComposerKt.InvalidationLocationAscending;
            CollectionsKt.sortWith(list, comparator);
            this.nodeIndex = 0;
            this.isComposing = true;
            try {
                startRoot();
                Object savedContent = nextSlot();
                if (savedContent != content && content != null) {
                    updateValue(content);
                }
                DerivedStateObserver observer$iv = this.derivedStateObserver;
                MutableVector observers$iv = SnapshotStateKt.derivedStateObservers();
                try {
                    observers$iv.add(observer$iv);
                    if (content != null) {
                        startGroup(200, ComposerKt.getInvocation());
                        ActualJvm_jvmKt.invokeComposable(this, content);
                        endGroup();
                    } else if (!(this.forciblyRecompose || this.providersInvalid) || savedContent == null || Intrinsics.areEqual(savedContent, Composer.INSTANCE.getEmpty())) {
                        skipCurrentGroup();
                    } else {
                        startGroup(200, ComposerKt.getInvocation());
                        ActualJvm_jvmKt.invokeComposable(this, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(savedContent, 2));
                        endGroup();
                    }
                    observers$iv.removeAt(observers$iv.getSize() - 1);
                    endRoot();
                    this.isComposing = false;
                    this.invalidations.clear();
                    createFreshInsertTable();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    observers$iv.removeAt(observers$iv.getSize() - 1);
                    throw th;
                }
            } catch (Throwable th2) {
                this.isComposing = false;
                this.invalidations.clear();
                abortRoot();
                createFreshInsertTable();
                throw th2;
            }
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    public final boolean getHasInvalidations() {
        return !this.invalidations.isEmpty();
    }

    private final Object getNode(SlotReader $this$node) {
        return $this$node.node($this$node.getParent());
    }

    private final Object nodeAt(SlotReader $this$nodeAt, int index) {
        return $this$nodeAt.node(index);
    }

    private final void validateNodeExpected() {
        boolean value$iv = this.nodeExpected;
        if (value$iv) {
            this.nodeExpected = false;
        } else {
            ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected".toString());
            throw new KotlinNothingValueException();
        }
    }

    private final void validateNodeNotExpected() {
        boolean value$iv = !this.nodeExpected;
        if (value$iv) {
            return;
        }
        ComposerKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected".toString());
        throw new KotlinNothingValueException();
    }

    private final void recordInsert(Anchor anchor) {
        if (this.insertFixups.isEmpty()) {
            this.changeListWriter.insertSlots(anchor, this.insertTable);
        } else {
            this.changeListWriter.insertSlots(anchor, this.insertTable, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.getCurrent());
        this.changeListWriter.removeCurrentGroup();
    }

    private static final int reportFreeMovableContent$reportGroup(ComposerImpl this$0, int group, boolean needsNodeDelete, int nodeIndex) {
        List $this$fastMap$iv;
        SlotReader reader = this$0.reader;
        if (reader.hasMark(group)) {
            int key = reader.groupKey(group);
            Object objectKey = reader.groupObjectKey(group);
            if (key == 126665345 && (objectKey instanceof MovableContent)) {
                MovableContent movableContent = (MovableContent) objectKey;
                Object parameter = reader.groupGet(group, 0);
                Anchor anchor = reader.anchor(group);
                int end = group + reader.groupSize(group);
                $this$fastMap$iv = ComposerKt.filterToRange(this$0.invalidations, group, end);
                int $i$f$fastMap = 0;
                List target$iv = new ArrayList($this$fastMap$iv.size());
                int index$iv$iv = 0;
                int size = $this$fastMap$iv.size();
                while (index$iv$iv < size) {
                    Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                    Invalidation it = (Invalidation) item$iv$iv;
                    target$iv.add(TuplesKt.to(it.getScope(), it.getInstances()));
                    index$iv$iv++;
                    size = size;
                    $this$fastMap$iv = $this$fastMap$iv;
                    $i$f$fastMap = $i$f$fastMap;
                }
                List invalidations = target$iv;
                MovableContentStateReference reference = new MovableContentStateReference(movableContent, parameter, this$0.getComposition(), this$0.slotTable, anchor, invalidations, this$0.currentCompositionLocalScope(group));
                this$0.parentContext.deletedMovableContent$runtime_release(reference);
                this$0.changeListWriter.recordSlotEditing();
                this$0.changeListWriter.releaseMovableGroupAtCurrent(this$0.getComposition(), this$0.parentContext, reference);
                if (needsNodeDelete) {
                    this$0.changeListWriter.endNodeMovementAndDeleteNode(nodeIndex, group);
                    return 0;
                }
                return reader.nodeCount(group);
            }
            if (key == 206 && Intrinsics.areEqual(objectKey, ComposerKt.getReference())) {
                Object groupGet = reader.groupGet(group, 0);
                CompositionContextHolder contextHolder = groupGet instanceof CompositionContextHolder ? (CompositionContextHolder) groupGet : null;
                if (contextHolder != null) {
                    CompositionContextImpl compositionContext = contextHolder.getRef();
                    Iterable $this$forEach$iv = compositionContext.getComposers();
                    for (Object element$iv : $this$forEach$iv) {
                        ComposerImpl composer = (ComposerImpl) element$iv;
                        composer.reportAllMovableContent();
                        this$0.parentContext.reportRemovedComposition$runtime_release(composer.getComposition());
                    }
                }
                return reader.nodeCount(group);
            }
            if (reader.isNode(group)) {
                return 1;
            }
            return reader.nodeCount(group);
        }
        if (reader.containsMark(group)) {
            int size2 = reader.groupSize(group);
            int end2 = group + size2;
            int runningNodeCount = 0;
            for (int current = group + 1; current < end2; current += reader.groupSize(current)) {
                boolean isNode = reader.isNode(current);
                if (isNode) {
                    this$0.changeListWriter.endNodeMovement();
                    this$0.changeListWriter.moveDown(reader.node(current));
                }
                runningNodeCount += reportFreeMovableContent$reportGroup(this$0, current, isNode || needsNodeDelete, isNode ? 0 : nodeIndex + runningNodeCount);
                if (isNode) {
                    this$0.changeListWriter.endNodeMovement();
                    this$0.changeListWriter.moveUp();
                }
            }
            if (reader.isNode(group)) {
                return 1;
            }
            return runningNodeCount;
        }
        if (reader.isNode(group)) {
            return 1;
        }
        return reader.nodeCount(group);
    }

    private final void reportFreeMovableContent(int groupBeingRemoved) {
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, false, 0);
        this.changeListWriter.endNodeMovement();
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsMark()) {
            ChangeList changes = new ChangeList();
            this.deferredChanges = changes;
            SlotReader reader$iv = this.slotTable.openReader();
            try {
                this.reader = reader$iv;
                ComposerChangeListWriter this_$iv = this.changeListWriter;
                ChangeList previousChangeList$iv = this_$iv.getChangeList();
                try {
                    this_$iv.setChangeList(changes);
                    reportFreeMovableContent(0);
                    this.changeListWriter.releaseMovableContent();
                    this_$iv.setChangeList(previousChangeList$iv);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    this_$iv.setChangeList(previousChangeList$iv);
                    throw th;
                }
            } finally {
                reader$iv.close();
            }
        }
    }

    private final void finalizeCompose() {
        this.changeListWriter.finalizeComposition();
        boolean value$iv = this.pendingStack.isEmpty();
        if (value$iv) {
            cleanUpCompose();
        } else {
            ComposerKt.composeRuntimeError("Start/end imbalance".toString());
            throw new KotlinNothingValueException();
        }
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        this.changeListWriter.resetTransientState();
        this.invalidateStack.clear();
        clearUpdatedNodeCounts();
    }

    public final void verifyConsistent$runtime_release() {
        this.insertTable.verifyWellFormed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Composer.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextHolder;", "Landroidx/compose/runtime/ReusableRememberObserver;", "ref", "Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/ComposerImpl;", "(Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;)V", "getRef", "()Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "onAbandoned", "", "onForgotten", "onRemembered", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes.dex */
    public static final class CompositionContextHolder implements ReusableRememberObserver {
        private final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl ref) {
            this.ref = ref;
        }

        public final CompositionContextImpl getRef() {
            return this.ref;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onRemembered() {
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onForgotten() {
            this.ref.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Composer.kt */
    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0082\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ*\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020-01¢\u0006\u0002\b2H\u0010¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0010¢\u0006\u0002\b8J\u0006\u00109\u001a\u00020-J\r\u0010:\u001a\u00020-H\u0010¢\u0006\u0002\b;J\r\u0010\u0015\u001a\u00020\u0013H\u0010¢\u0006\u0002\b<J\u0015\u0010=\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0010¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\b@J\u0015\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020CH\u0010¢\u0006\u0002\bDJ\u001d\u0010E\u001a\u00020-2\u0006\u00106\u001a\u0002072\u0006\u0010F\u001a\u00020GH\u0010¢\u0006\u0002\bHJ\u0017\u0010I\u001a\u0004\u0018\u00010G2\u0006\u00106\u001a\u000207H\u0010¢\u0006\u0002\bJJ\u001b\u0010K\u001a\u00020-2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\"0\u000eH\u0010¢\u0006\u0002\bMJ\u0015\u0010N\u001a\u00020-2\u0006\u0010O\u001a\u00020PH\u0010¢\u0006\u0002\bQJ\u0015\u0010R\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\bSJ\u0015\u0010T\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\bUJ\r\u0010V\u001a\u00020-H\u0010¢\u0006\u0002\bWJ\u0015\u0010X\u001a\u00020-2\u0006\u0010O\u001a\u00020PH\u0010¢\u0006\u0002\bYJ\u0015\u0010Z\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\b[J\u000e\u0010\\\u001a\u00020-2\u0006\u0010B\u001a\u00020\u0013R\u0014\u0010\u0004\u001a\u00020\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R(\u0010!\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u000e\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0011\"\u0004\b$\u0010%R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u001e8PX\u0090\u0004¢\u0006\f\u0012\u0004\b)\u0010*\u001a\u0004\b+\u0010 ¨\u0006]"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "compoundHashKey", "", "collectingParameterInformation", "", "collectingSourceInformation", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "(Landroidx/compose/runtime/ComposerImpl;IZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "getCollectingParameterInformation$runtime_release", "()Z", "getCollectingSourceInformation$runtime_release", "composers", "", "Landroidx/compose/runtime/ComposerImpl;", "getComposers", "()Ljava/util/Set;", "<set-?>", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "compositionLocalScope", "getCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "setCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "getCompoundHashKey$runtime_release", "()I", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "inspectionTables", "Landroidx/compose/runtime/tooling/CompositionData;", "getInspectionTables", "setInspectionTables", "(Ljava/util/Set;)V", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime_release$annotations", "()V", "getRecomposeCoroutineContext$runtime_release", "composeInitial", "", "composition", "Landroidx/compose/runtime/ControlledComposition;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "deletedMovableContent", "reference", "Landroidx/compose/runtime/MovableContentStateReference;", "deletedMovableContent$runtime_release", "dispose", "doneComposing", "doneComposing$runtime_release", "getCompositionLocalScope$runtime_release", "insertMovableContent", "insertMovableContent$runtime_release", "invalidate", "invalidate$runtime_release", "invalidateScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "invalidateScope$runtime_release", "movableContentStateReleased", "data", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateReleased$runtime_release", "movableContentStateResolve", "movableContentStateResolve$runtime_release", "recordInspectionTable", "table", "recordInspectionTable$runtime_release", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime_release", "registerComposition", "registerComposition$runtime_release", "reportRemovedComposition", "reportRemovedComposition$runtime_release", "startComposing", "startComposing$runtime_release", "unregisterComposer", "unregisterComposer$runtime_release", "unregisterComposition", "unregisterComposition$runtime_release", "updateCompositionLocalScope", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes.dex */
    public final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final Set<ComposerImpl> composers = new LinkedHashSet();

        /* renamed from: compositionLocalScope$delegate, reason: from kotlin metadata */
        private final MutableState compositionLocalScope = SnapshotStateKt.mutableStateOf(PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf(), SnapshotStateKt.referentialEqualityPolicy());
        private final int compoundHashKey;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;

        public static /* synthetic */ void getRecomposeCoroutineContext$runtime_release$annotations() {
        }

        public CompositionContextImpl(int compoundHashKey, boolean collectingParameterInformation, boolean collectingSourceInformation, CompositionObserverHolder observerHolder) {
            this.compoundHashKey = compoundHashKey;
            this.collectingParameterInformation = collectingParameterInformation;
            this.collectingSourceInformation = collectingSourceInformation;
            this.observerHolder = observerHolder;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCompoundHashKey$runtime_release, reason: from getter */
        public int getCompoundHashKey() {
            return this.compoundHashKey;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCollectingParameterInformation$runtime_release, reason: from getter */
        public boolean getCollectingParameterInformation() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCollectingSourceInformation$runtime_release, reason: from getter */
        public boolean getCollectingSourceInformation() {
            return this.collectingSourceInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getObserverHolder$runtime_release, reason: from getter */
        public CompositionObserverHolder getObserverHolder() {
            return this.observerHolder;
        }

        public final Set<Set<CompositionData>> getInspectionTables() {
            return this.inspectionTables;
        }

        public final void setInspectionTables(Set<Set<CompositionData>> set) {
            this.inspectionTables = set;
        }

        public final Set<ComposerImpl> getComposers() {
            return this.composers;
        }

        public final void dispose() {
            if (!this.composers.isEmpty()) {
                Set it = this.inspectionTables;
                if (it != null) {
                    for (ComposerImpl composer : this.composers) {
                        for (Set table : it) {
                            table.remove(composer.slotTable);
                        }
                    }
                }
                this.composers.clear();
            }
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime_release(Composer composer) {
            Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl");
            super.registerComposer$runtime_release((ComposerImpl) composer);
            this.composers.add(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime_release(Composer composer) {
            Iterable iterable = this.inspectionTables;
            if (iterable != null) {
                Iterable $this$forEach$iv = iterable;
                for (Object element$iv : $this$forEach$iv) {
                    Set it = (Set) element$iv;
                    Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl");
                    it.remove(((ComposerImpl) composer).slotTable);
                }
            }
            Iterable $this$forEach$iv2 = this.composers;
            TypeIntrinsics.asMutableCollection((Collection) $this$forEach$iv2).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.registerComposition$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.unregisterComposition$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getEffectCoroutineContext() {
            return ComposerImpl.this.parentContext.getEffectCoroutineContext();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getRecomposeCoroutineContext$runtime_release() {
            return CompositionKt.getRecomposeCoroutineContext(ComposerImpl.this.getComposition());
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime_release(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
            ComposerImpl.this.parentContext.composeInitial$runtime_release(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.invalidate$runtime_release(ComposerImpl.this.getComposition());
            ComposerImpl.this.parentContext.invalidate$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime_release(RecomposeScopeImpl scope) {
            ComposerImpl.this.parentContext.invalidateScope$runtime_release(scope);
        }

        private final PersistentCompositionLocalMap getCompositionLocalScope() {
            State $this$getValue$iv = this.compositionLocalScope;
            return (PersistentCompositionLocalMap) $this$getValue$iv.getValue();
        }

        private final void setCompositionLocalScope(PersistentCompositionLocalMap persistentCompositionLocalMap) {
            MutableState $this$setValue$iv = this.compositionLocalScope;
            $this$setValue$iv.setValue(persistentCompositionLocalMap);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
            return getCompositionLocalScope();
        }

        public final void updateCompositionLocalScope(PersistentCompositionLocalMap scope) {
            setCompositionLocalScope(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
            HashSet hashSet = this.inspectionTables;
            if (hashSet == null) {
                HashSet it = new HashSet();
                this.inspectionTables = it;
                hashSet = it;
            }
            hashSet.add(table);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void startComposing$runtime_release() {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime_release() {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.insertMovableContent$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.deletedMovableContent$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
            return ComposerImpl.this.parentContext.movableContentStateResolve$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime_release(MovableContentStateReference reference, MovableContentState data) {
            ComposerImpl.this.parentContext.movableContentStateReleased$runtime_release(reference, data);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.reportRemovedComposition$runtime_release(composition);
        }
    }

    private final void updateCompoundKeyWhenWeEnterGroup(int groupKey, Object dataKey, Object data) {
        if (dataKey == null) {
            if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                updateCompoundKeyWhenWeEnterGroupKeyHash(data.hashCode());
                return;
            } else {
                updateCompoundKeyWhenWeEnterGroupKeyHash(groupKey);
                return;
            }
        }
        if (dataKey instanceof Enum) {
            updateCompoundKeyWhenWeEnterGroupKeyHash(((Enum) dataKey).ordinal());
        } else {
            updateCompoundKeyWhenWeEnterGroupKeyHash(dataKey.hashCode());
        }
    }

    private final void updateCompoundKeyWhenWeEnterGroupKeyHash(int keyHash) {
        int $this$rol$iv = getCompoundKeyHash();
        this.compoundKeyHash = Integer.rotateLeft($this$rol$iv, 3) ^ keyHash;
    }

    private final void updateCompoundKeyWhenWeExitGroup(int groupKey, Object dataKey, Object data) {
        if (dataKey == null) {
            if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                updateCompoundKeyWhenWeExitGroupKeyHash(data.hashCode());
                return;
            } else {
                updateCompoundKeyWhenWeExitGroupKeyHash(groupKey);
                return;
            }
        }
        if (dataKey instanceof Enum) {
            updateCompoundKeyWhenWeExitGroupKeyHash(((Enum) dataKey).ordinal());
        } else {
            updateCompoundKeyWhenWeExitGroupKeyHash(dataKey.hashCode());
        }
    }

    private final void updateCompoundKeyWhenWeExitGroupKeyHash(int groupKey) {
        int $this$ror$iv = getCompoundKeyHash() ^ Integer.hashCode(groupKey);
        this.compoundKeyHash = Integer.rotateRight($this$ror$iv, 3);
    }

    @Override // androidx.compose.runtime.Composer
    public RecomposeScope getRecomposeScope() {
        return getCurrentRecomposeScope$runtime_release();
    }

    @Override // androidx.compose.runtime.Composer
    public Object getRecomposeScopeIdentity() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            return currentRecomposeScope$runtime_release.getAnchor();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public Object rememberedValue() {
        return nextSlotForCache();
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue(value);
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl == null) {
            return;
        }
        recomposeScopeImpl.setUsed(true);
    }
}
