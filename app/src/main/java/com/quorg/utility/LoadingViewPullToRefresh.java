package com.quorg.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

import com.quorg.R;


public class LoadingViewPullToRefresh extends LinearLayout {
    boolean is_animation = false;

    public LoadingViewPullToRefresh(Context context, boolean is_animation) {
        super(context);
        this.is_animation = is_animation;
        init();
    }

    public LoadingViewPullToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.image_pager_pagination_item, this);
        if (is_animation) {
            RotateAnimation rotateanim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateanim.setDuration(1200);
            rotateanim.setRepeatCount(1000);
            rotateanim.setInterpolator(new LinearInterpolator());
            findViewById(R.id.loader1).startAnimation(rotateanim);
        }
    }

}
