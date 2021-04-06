package com.lxy.dyv.transform;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lxy.dyv.DyvConstant;
import com.lxy.dyv.DyvHelper;
import com.lxy.dyv.NativeViewCreator;
import com.lxy.dyv.transform.help.TransformHelper;
import com.lxy.dyv.view.Text;

/**
 * author: lxy
 * created on: 2021/2/20 3:45 PM
 * <p>
 */
public class TextTransform extends VTransform{

    TextView textView;

    public TextTransform(Context mCtx) {
        super(mCtx);
    }

    @Override
    void createView() {
        textView = NativeViewCreator.createNativeView(DyvConstant.NATIVE_VIEW_TYPE_TEXT, mCtx);
    }

    @Override
    void bindView() {

        if (DyvHelper.isNull(textView)) return;

        TransformHelper.bindGeneralProperties(textView, virtualView);

        if (virtualView instanceof Text) {

            Text textWidget = (Text) virtualView;

            textView.setTextColor(DyvHelper.parseColor(textWidget.textColor));

            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textWidget.textSize);

            if (DyvHelper.propertiesLegal(textWidget.maxLines)) {
                textView.setMaxLines(textWidget.maxLines);
                textView.setEllipsize(TextUtils.TruncateAt.END);
            }

            if (DyvHelper.propertiesLegal(textWidget.fontName)) {

                Typeface tf = Typeface.createFromAsset(mCtx.getAssets(), textWidget.fontName);
                textView.setTypeface(tf);
            }

        }
    }

    @Override
    void bindClick() {

        if (DyvHelper.isNull(textView)) return;

        if (DyvHelper.isNull(viewEvent)) return;

        if (!viewEvent.legal()) return;

        textView.setOnClickListener(this);
    }

    @Override
    void bindData() {

        if (DyvHelper.isNull(textView)) return;

        if (DyvHelper.isNull(virtualView)) return;

        if (virtualView instanceof Text) {
            TransformHelper.bindSpecialProperties(textView, virtualView, viewData, viewBindCallback);
        }
    }

    @Override
    View obtainView() {
        return textView;
    }

}
