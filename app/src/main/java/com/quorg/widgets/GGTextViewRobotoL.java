/**
 * @author saltinteractive
 */

package com.quorg.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.quorg.R;


public class GGTextViewRobotoL extends TextView {

    public GGTextViewRobotoL(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public GGTextViewRobotoL(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public GGTextViewRobotoL(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.MyTextView);
            String fontName = a.getString(R.styleable.MyTextView_fontName);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext()
                        .getAssets(), "" + fontName);
                setTypeface(myTypeface);
            } else {
                Typeface myTypeface = Typeface.createFromAsset(getContext()
                        .getAssets(), "font/livefont/Roboto-Light.ttf");
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

}