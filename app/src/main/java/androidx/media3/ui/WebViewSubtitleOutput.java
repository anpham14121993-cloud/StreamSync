package androidx.media3.ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.SpannedToHtmlConverter;
import androidx.media3.ui.SubtitleView;
import com.google.common.base.Charsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes24.dex */
final class WebViewSubtitleOutput extends FrameLayout implements SubtitleView.Output {
    private static final float CSS_LINE_HEIGHT = 1.2f;
    private static final String DEFAULT_BACKGROUND_CSS_CLASS = "default_bg";
    private float bottomPaddingFraction;
    private final CanvasSubtitleOutput canvasSubtitleOutput;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private CaptionStyleCompat style;
    private List<Cue> textCues;
    private final WebView webView;

    public WebViewSubtitleOutput(Context context) {
        this(context, null);
    }

    public WebViewSubtitleOutput(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.textCues = Collections.emptyList();
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSize = 0.0533f;
        this.defaultTextSizeType = 0;
        this.bottomPaddingFraction = 0.08f;
        this.canvasSubtitleOutput = new CanvasSubtitleOutput(context, attrs);
        this.webView = new WebView(context, attrs) { // from class: androidx.media3.ui.WebViewSubtitleOutput.1
            @Override // android.webkit.WebView, android.view.View
            public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
                return false;
            }

            @Override // android.view.View
            public boolean performClick() {
                super.performClick();
                return false;
            }
        };
        this.webView.setBackgroundColor(0);
        addView(this.canvasSubtitleOutput);
        addView(this.webView);
    }

    @Override // androidx.media3.ui.SubtitleView.Output
    public void update(List<Cue> cues, CaptionStyleCompat style, float textSize, int textSizeType, float bottomPaddingFraction) {
        this.style = style;
        this.defaultTextSize = textSize;
        this.defaultTextSizeType = textSizeType;
        this.bottomPaddingFraction = bottomPaddingFraction;
        List<Cue> bitmapCues = new ArrayList<>();
        List<Cue> textCues = new ArrayList<>();
        for (int i = 0; i < cues.size(); i++) {
            Cue cue = cues.get(i);
            if (cue.bitmap != null) {
                bitmapCues.add(cue);
            } else {
                textCues.add(cue);
            }
        }
        if (!this.textCues.isEmpty() || !textCues.isEmpty()) {
            this.textCues = textCues;
            updateWebView();
        }
        this.canvasSubtitleOutput.update(bitmapCues, style, textSize, textSizeType, bottomPaddingFraction);
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed && !this.textCues.isEmpty()) {
            updateWebView();
        }
    }

    public void destroy() {
        this.webView.destroy();
    }

    private void updateWebView() {
        String lineValue;
        String size;
        String lineProperty;
        String positionProperty;
        String sizeProperty;
        int horizontalTranslatePercent;
        int verticalTranslatePercent;
        String str;
        boolean z;
        int anchorTypeToTranslatePercent;
        WebViewSubtitleOutput webViewSubtitleOutput = this;
        StringBuilder html = new StringBuilder();
        String cssRgba = HtmlUtils.toCssRgba(webViewSubtitleOutput.style.foregroundColor);
        String convertTextSizeToCss = webViewSubtitleOutput.convertTextSizeToCss(webViewSubtitleOutput.defaultTextSizeType, webViewSubtitleOutput.defaultTextSize);
        float f = CSS_LINE_HEIGHT;
        html.append(Util.formatInvariant("<body><div style='-webkit-user-select:none;position:fixed;top:0;bottom:0;left:0;right:0;color:%s;font-size:%s;line-height:%.2f;text-shadow:%s;'>", cssRgba, convertTextSizeToCss, Float.valueOf(CSS_LINE_HEIGHT), convertCaptionStyleToCssTextShadow(webViewSubtitleOutput.style)));
        Map<String, String> cssRuleSets = new HashMap<>();
        String str2 = DEFAULT_BACKGROUND_CSS_CLASS;
        cssRuleSets.put(HtmlUtils.cssAllClassDescendantsSelector(DEFAULT_BACKGROUND_CSS_CLASS), Util.formatInvariant("background-color:%s;", HtmlUtils.toCssRgba(webViewSubtitleOutput.style.backgroundColor)));
        int i = 0;
        while (i < webViewSubtitleOutput.textCues.size()) {
            Cue cue = webViewSubtitleOutput.textCues.get(i);
            float positionPercent = cue.position != -3.4028235E38f ? cue.position * 100.0f : 50.0f;
            int positionAnchorTranslatePercent = anchorTypeToTranslatePercent(cue.positionAnchor);
            boolean lineMeasuredFromEnd = false;
            int lineAnchorTranslatePercent = 0;
            if (cue.line != -3.4028235E38f) {
                switch (cue.lineType) {
                    case 1:
                        if (cue.line >= 0.0f) {
                            lineValue = Util.formatInvariant("%.2fem", Float.valueOf(cue.line * f));
                            break;
                        } else {
                            lineValue = Util.formatInvariant("%.2fem", Float.valueOf(((-cue.line) - 1.0f) * f));
                            lineMeasuredFromEnd = true;
                            break;
                        }
                    default:
                        lineValue = Util.formatInvariant("%.2f%%", Float.valueOf(cue.line * 100.0f));
                        if (cue.verticalType == 1) {
                            anchorTypeToTranslatePercent = -anchorTypeToTranslatePercent(cue.lineAnchor);
                        } else {
                            anchorTypeToTranslatePercent = anchorTypeToTranslatePercent(cue.lineAnchor);
                        }
                        lineAnchorTranslatePercent = anchorTypeToTranslatePercent;
                        break;
                }
            } else {
                lineValue = Util.formatInvariant("%.2f%%", Float.valueOf((1.0f - webViewSubtitleOutput.bottomPaddingFraction) * 100.0f));
                lineAnchorTranslatePercent = -100;
            }
            if (cue.size != -3.4028235E38f) {
                size = Util.formatInvariant("%.2f%%", Float.valueOf(cue.size * 100.0f));
            } else {
                size = "fit-content";
            }
            String textAlign = convertAlignmentToCss(cue.textAlignment);
            String writingMode = convertVerticalTypeToCss(cue.verticalType);
            String cueTextSizeCssPx = webViewSubtitleOutput.convertTextSizeToCss(cue.textSizeType, cue.textSize);
            String windowCssColor = HtmlUtils.toCssRgba(cue.windowColorSet ? cue.windowColor : webViewSubtitleOutput.style.windowColor);
            int i2 = cue.verticalType;
            String str3 = TtmlNode.LEFT;
            switch (i2) {
                case 1:
                    if (!lineMeasuredFromEnd) {
                        str3 = TtmlNode.RIGHT;
                    }
                    lineProperty = str3;
                    positionProperty = "top";
                    break;
                case 2:
                    if (lineMeasuredFromEnd) {
                        str3 = TtmlNode.RIGHT;
                    }
                    lineProperty = str3;
                    positionProperty = "top";
                    break;
                default:
                    lineProperty = lineMeasuredFromEnd ? "bottom" : "top";
                    positionProperty = TtmlNode.LEFT;
                    break;
            }
            if (cue.verticalType == 2 || cue.verticalType == 1) {
                sizeProperty = "height";
                horizontalTranslatePercent = lineAnchorTranslatePercent;
                verticalTranslatePercent = positionAnchorTranslatePercent;
            } else {
                sizeProperty = "width";
                horizontalTranslatePercent = positionAnchorTranslatePercent;
                verticalTranslatePercent = lineAnchorTranslatePercent;
            }
            SpannedToHtmlConverter.HtmlAndCss htmlAndCss = SpannedToHtmlConverter.convert(cue.text, getContext().getResources().getDisplayMetrics().density);
            Iterator<String> it = cssRuleSets.keySet().iterator();
            while (it.hasNext()) {
                Iterator<String> it2 = it;
                String cssSelector = it.next();
                SpannedToHtmlConverter.HtmlAndCss htmlAndCss2 = htmlAndCss;
                String previousCssDeclarationBlock = cssRuleSets.put(cssSelector, cssRuleSets.get(cssSelector));
                if (previousCssDeclarationBlock == null) {
                    str = str2;
                } else {
                    str = str2;
                    if (!previousCssDeclarationBlock.equals(cssRuleSets.get(cssSelector))) {
                        z = false;
                        Assertions.checkState(z);
                        it = it2;
                        htmlAndCss = htmlAndCss2;
                        str2 = str;
                    }
                }
                z = true;
                Assertions.checkState(z);
                it = it2;
                htmlAndCss = htmlAndCss2;
                str2 = str;
            }
            String str4 = str2;
            SpannedToHtmlConverter.HtmlAndCss htmlAndCss3 = htmlAndCss;
            html.append(Util.formatInvariant("<div style='position:absolute;z-index:%s;%s:%.2f%%;%s:%s;%s:%s;text-align:%s;writing-mode:%s;font-size:%s;background-color:%s;transform:translate(%s%%,%s%%)%s;'>", Integer.valueOf(i), positionProperty, Float.valueOf(positionPercent), lineProperty, lineValue, sizeProperty, size, textAlign, writingMode, cueTextSizeCssPx, windowCssColor, Integer.valueOf(horizontalTranslatePercent), Integer.valueOf(verticalTranslatePercent), getBlockShearTransformFunction(cue))).append(Util.formatInvariant("<span class='%s'>", str4));
            if (cue.multiRowAlignment != null) {
                StringBuilder append = html.append(Util.formatInvariant("<span style='display:inline-block; text-align:%s;'>", convertAlignmentToCss(cue.multiRowAlignment)));
                String sizeProperty2 = htmlAndCss3.html;
                append.append(sizeProperty2).append("</span>");
            } else {
                String sizeProperty3 = htmlAndCss3.html;
                html.append(sizeProperty3);
            }
            html.append("</span>").append("</div>");
            i++;
            f = CSS_LINE_HEIGHT;
            webViewSubtitleOutput = this;
            str2 = str4;
        }
        html.append("</div></body></html>");
        StringBuilder htmlHead = new StringBuilder();
        htmlHead.append("<html><head><style>");
        for (String cssSelector2 : cssRuleSets.keySet()) {
            htmlHead.append(cssSelector2).append("{").append(cssRuleSets.get(cssSelector2)).append("}");
        }
        htmlHead.append("</style></head>");
        html.insert(0, htmlHead.toString());
        this.webView.loadData(Base64.encodeToString(html.toString().getBytes(Charsets.UTF_8), 1), "text/html", "base64");
    }

    private static String getBlockShearTransformFunction(Cue cue) {
        String direction;
        if (cue.shearDegrees != 0.0f) {
            if (cue.verticalType == 2 || cue.verticalType == 1) {
                direction = "skewY";
            } else {
                direction = "skewX";
            }
            return Util.formatInvariant("%s(%.2fdeg)", direction, Float.valueOf(cue.shearDegrees));
        }
        return "";
    }

    private String convertTextSizeToCss(int type, float size) {
        float sizePx = SubtitleViewUtils.resolveTextSize(type, size, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        if (sizePx == -3.4028235E38f) {
            return "unset";
        }
        float sizeDp = sizePx / getContext().getResources().getDisplayMetrics().density;
        return Util.formatInvariant("%.2fpx", Float.valueOf(sizeDp));
    }

    private static String convertCaptionStyleToCssTextShadow(CaptionStyleCompat style) {
        switch (style.edgeType) {
            case 1:
                return Util.formatInvariant("1px 1px 0 %1$s, 1px -1px 0 %1$s, -1px 1px 0 %1$s, -1px -1px 0 %1$s", HtmlUtils.toCssRgba(style.edgeColor));
            case 2:
                return Util.formatInvariant("0.1em 0.12em 0.15em %s", HtmlUtils.toCssRgba(style.edgeColor));
            case 3:
                return Util.formatInvariant("0.06em 0.08em 0.15em %s", HtmlUtils.toCssRgba(style.edgeColor));
            case 4:
                return Util.formatInvariant("-0.05em -0.05em 0.15em %s", HtmlUtils.toCssRgba(style.edgeColor));
            default:
                return "unset";
        }
    }

    private static String convertVerticalTypeToCss(int verticalType) {
        switch (verticalType) {
            case 1:
                return "vertical-rl";
            case 2:
                return "vertical-lr";
            default:
                return "horizontal-tb";
        }
    }

    private static String convertAlignmentToCss(Layout.Alignment alignment) {
        if (alignment == null) {
            return TtmlNode.CENTER;
        }
        switch (AnonymousClass2.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()]) {
            case 1:
                return TtmlNode.START;
            case 2:
                return TtmlNode.END;
            default:
                return TtmlNode.CENTER;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.media3.ui.WebViewSubtitleOutput$2, reason: invalid class name */
    /* loaded from: classes24.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment = new int[Layout.Alignment.values().length];

        static {
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static int anchorTypeToTranslatePercent(int anchorType) {
        switch (anchorType) {
            case 1:
                return -50;
            case 2:
                return -100;
            default:
                return 0;
        }
    }
}
