package com.quorg.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by valkesh patel on 18/7/16.
 */
public class GGTextView extends TextView {

    public GGTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GGTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GGTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Montserrat-Light_0.otf");
        setTypeface(tf);
    }

}