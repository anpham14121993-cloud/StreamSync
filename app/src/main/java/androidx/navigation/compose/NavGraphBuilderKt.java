package androidx.navigation.compose;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NamedNavArgument;
import androidx.navigation.NavArgument;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.compose.ComposeNavGraphNavigator;
import androidx.navigation.compose.ComposeNavigator;
import androidx.navigation.compose.DialogNavigator;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* compiled from: NavGraphBuilder.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001a\u0083\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062(\b\u0002\u0010\u000f\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0014\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0016\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132(\b\u0002\u0010\u0017\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u00132\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\r¢\u0006\u0002\b\u0013¢\u0006\u0002\u0010\u001a\u001aZ\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\r¢\u0006\u0002\u0010\u001e\u001aç\u0001\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062#\b\u0002\u0010\u000f\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0014\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0016\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u000b¢\u0006\u0002\b\u00132#\b\u0002\u0010\u0017\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u000b¢\u0006\u0002\b\u00132\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013\u001aU\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010 \u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\u0013H\u0007¨\u0006\""}, d2 = {ComposeNavigator.NAME, "", "Landroidx/navigation/NavGraphBuilder;", "route", "", "arguments", "", "Landroidx/navigation/NamedNavArgument;", "deepLinks", "Landroidx/navigation/NavDeepLink;", "content", "Lkotlin/Function1;", "Landroidx/navigation/NavBackStackEntry;", "Landroidx/compose/runtime/Composable;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function3;)V", "enterTransition", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/EnterTransition;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lkotlin/ExtensionFunctionType;", "exitTransition", "Landroidx/compose/animation/ExitTransition;", "popEnterTransition", "popExitTransition", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", DialogNavigator.NAME, "dialogProperties", "Landroidx/compose/ui/window/DialogProperties;", "(Landroidx/navigation/NavGraphBuilder;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;)V", NotificationCompat.CATEGORY_NAVIGATION, "startDestination", "builder", "navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class NavGraphBuilderKt {
    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function3 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        composable(navGraphBuilder, str, list, list2, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of composable builder that supports AnimatedContent")
    public static final /* synthetic */ void composable(NavGraphBuilder $this$composable, String route, List arguments, List deepLinks, final Function3 content) {
        NavigatorProvider $this$get$iv = $this$composable.getProvider();
        ComposeNavigator.Destination $this$composable_u24lambda_u242 = new ComposeNavigator.Destination((ComposeNavigator) $this$get$iv.getNavigator(ComposeNavigator.class), (Function4<? super AnimatedContentScope, NavBackStackEntry, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambdaInstance(484185514, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: androidx.navigation.compose.NavGraphBuilderKt$composable$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AnimatedContentScope $this$$receiver, NavBackStackEntry entry, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C52@1986L14:NavGraphBuilder.kt#opm8kd");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(484185514, $changed, -1, "androidx.navigation.compose.composable.<anonymous> (NavGraphBuilder.kt:52)");
                }
                content.invoke(entry, $composer, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        $this$composable_u24lambda_u242.setRoute(route);
        List $this$forEach$iv = arguments;
        for (Object element$iv : $this$forEach$iv) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) element$iv;
            String argumentName = namedNavArgument.getName();
            NavArgument argument = namedNavArgument.getArgument();
            $this$composable_u24lambda_u242.addArgument(argumentName, argument);
        }
        List $this$forEach$iv2 = deepLinks;
        for (Object element$iv2 : $this$forEach$iv2) {
            NavDeepLink deepLink = (NavDeepLink) element$iv2;
            $this$composable_u24lambda_u242.addDeepLink(deepLink);
        }
        $this$composable.addDestination($this$composable_u24lambda_u242);
    }

    public static /* synthetic */ void composable$default(NavGraphBuilder navGraphBuilder, String str, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function4 function4, int i, Object obj) {
        List emptyList = (i & 2) != 0 ? CollectionsKt.emptyList() : list;
        List emptyList2 = (i & 4) != 0 ? CollectionsKt.emptyList() : list2;
        Function1 function15 = (i & 8) != 0 ? null : function1;
        Function1 function16 = (i & 16) != 0 ? null : function12;
        composable(navGraphBuilder, str, emptyList, emptyList2, function15, function16, (i & 32) != 0 ? function15 : function13, (i & 64) != 0 ? function16 : function14, function4);
    }

    public static final void composable(NavGraphBuilder $this$composable, String route, List<NamedNavArgument> list, List<NavDeepLink> list2, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function1, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function12, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, EnterTransition> function13, Function1<AnimatedContentTransitionScope<NavBackStackEntry>, ExitTransition> function14, Function4<? super AnimatedContentScope, ? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function4) {
        NavigatorProvider $this$get$iv = $this$composable.getProvider();
        ComposeNavigator.Destination $this$composable_u24lambda_u245 = new ComposeNavigator.Destination((ComposeNavigator) $this$get$iv.getNavigator(ComposeNavigator.class), (Function4<? super AnimatedContentScope, NavBackStackEntry, ? super Composer, ? super Integer, Unit>) function4);
        $this$composable_u24lambda_u245.setRoute(route);
        List<NamedNavArgument> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) element$iv;
            String argumentName = namedNavArgument.getName();
            NavArgument argument = namedNavArgument.getArgument();
            $this$composable_u24lambda_u245.addArgument(argumentName, argument);
        }
        List<NavDeepLink> $this$forEach$iv2 = list2;
        for (Object element$iv2 : $this$forEach$iv2) {
            NavDeepLink deepLink = (NavDeepLink) element$iv2;
            $this$composable_u24lambda_u245.addDeepLink(deepLink);
        }
        $this$composable_u24lambda_u245.setEnterTransition$navigation_compose_release(function1);
        $this$composable_u24lambda_u245.setExitTransition$navigation_compose_release(function12);
        $this$composable_u24lambda_u245.setPopEnterTransition$navigation_compose_release(function13);
        $this$composable_u24lambda_u245.setPopExitTransition$navigation_compose_release(function14);
        $this$composable.addDestination($this$composable_u24lambda_u245);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, String str, String str2, List list, List list2, Function1 function1, int i, Object obj) {
        List list3;
        List list4;
        if ((i & 4) == 0) {
            list3 = list;
        } else {
            list3 = CollectionsKt.emptyList();
        }
        if ((i & 8) == 0) {
            list4 = list2;
        } else {
            list4 = CollectionsKt.emptyList();
        }
        navigation(navGraphBuilder, str, str2, list3, list4, function1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of navigation builder that supports AnimatedContent")
    public static final /* synthetic */ void navigation(NavGraphBuilder $this$navigation, String startDestination, String route, List arguments, List deepLinks, Function1 builder) {
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation.getProvider(), startDestination, route);
        builder.invoke(navGraphBuilder);
        NavGraph $this$navigation_u24lambda_u248 = navGraphBuilder.build();
        List $this$forEach$iv = arguments;
        for (Object element$iv : $this$forEach$iv) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) element$iv;
            String argumentName = namedNavArgument.getName();
            NavArgument argument = namedNavArgument.getArgument();
            $this$navigation_u24lambda_u248.addArgument(argumentName, argument);
        }
        List $this$forEach$iv2 = deepLinks;
        for (Object element$iv2 : $this$forEach$iv2) {
            NavDeepLink deepLink = (NavDeepLink) element$iv2;
            $this$navigation_u24lambda_u248.addDeepLink(deepLink);
        }
        $this$navigation.addDestination($this$navigation_u24lambda_u248);
    }

    public static /* synthetic */ void navigation$default(NavGraphBuilder navGraphBuilder, String str, String str2, List list, List list2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        List emptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        List emptyList2 = (i & 8) != 0 ? CollectionsKt.emptyList() : list2;
        Function1 function16 = (i & 16) != 0 ? null : function1;
        Function1 function17 = (i & 32) != 0 ? null : function12;
        navigation(navGraphBuilder, str, str2, emptyList, emptyList2, function16, function17, (i & 64) != 0 ? function16 : function13, (i & 128) != 0 ? function17 : function14, function15);
    }

    public static final void navigation(NavGraphBuilder $this$navigation, String startDestination, String route, List<NamedNavArgument> list, List<NavDeepLink> list2, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ? extends EnterTransition> function1, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ? extends ExitTransition> function12, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ? extends EnterTransition> function13, Function1<? super AnimatedContentTransitionScope<NavBackStackEntry>, ? extends ExitTransition> function14, Function1<? super NavGraphBuilder, Unit> function15) {
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder($this$navigation.getProvider(), startDestination, route);
        function15.invoke(navGraphBuilder);
        NavGraph $this$navigation_u24lambda_u2411 = navGraphBuilder.build();
        List<NamedNavArgument> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) element$iv;
            String argumentName = namedNavArgument.getName();
            NavArgument argument = namedNavArgument.getArgument();
            $this$navigation_u24lambda_u2411.addArgument(argumentName, argument);
        }
        List<NavDeepLink> $this$forEach$iv2 = list2;
        for (Object element$iv2 : $this$forEach$iv2) {
            NavDeepLink deepLink = (NavDeepLink) element$iv2;
            $this$navigation_u24lambda_u2411.addDeepLink(deepLink);
        }
        if ($this$navigation_u24lambda_u2411 instanceof ComposeNavGraphNavigator.ComposeNavGraph) {
            ((ComposeNavGraphNavigator.ComposeNavGraph) $this$navigation_u24lambda_u2411).setEnterTransition$navigation_compose_release(function1);
            ((ComposeNavGraphNavigator.ComposeNavGraph) $this$navigation_u24lambda_u2411).setExitTransition$navigation_compose_release(function12);
            ((ComposeNavGraphNavigator.ComposeNavGraph) $this$navigation_u24lambda_u2411).setPopEnterTransition$navigation_compose_release(function13);
            ((ComposeNavGraphNavigator.ComposeNavGraph) $this$navigation_u24lambda_u2411).setPopExitTransition$navigation_compose_release(function14);
        }
        $this$navigation.addDestination($this$navigation_u24lambda_u2411);
    }

    public static final void dialog(NavGraphBuilder $this$dialog, String route, List<NamedNavArgument> list, List<NavDeepLink> list2, DialogProperties dialogProperties, Function3<? super NavBackStackEntry, ? super Composer, ? super Integer, Unit> function3) {
        NavigatorProvider $this$get$iv = $this$dialog.getProvider();
        DialogNavigator.Destination $this$dialog_u24lambda_u2414 = new DialogNavigator.Destination((DialogNavigator) $this$get$iv.getNavigator(DialogNavigator.class), dialogProperties, function3);
        $this$dialog_u24lambda_u2414.setRoute(route);
        List<NamedNavArgument> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            NamedNavArgument namedNavArgument = (NamedNavArgument) element$iv;
            String argumentName = namedNavArgument.getName();
            NavArgument argument = namedNavArgument.getArgument();
            $this$dialog_u24lambda_u2414.addArgument(argumentName, argument);
        }
        List<NavDeepLink> $this$forEach$iv2 = list2;
        for (Object element$iv2 : $this$forEach$iv2) {
            NavDeepLink deepLink = (NavDeepLink) element$iv2;
            $this$dialog_u24lambda_u2414.addDeepLink(deepLink);
        }
        $this$dialog.addDestination($this$dialog_u24lambda_u2414);
    }
}
