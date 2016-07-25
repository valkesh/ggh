package com.quorg.widgets;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class GGEditText extends EditText {


 private Context context;
 private AttributeSet attrs;
 private int defStyle;

 public GGEditText(Context context) {
  super(context);
  this.context = context;
  init();
 }

 public GGEditText(Context context, AttributeSet attrs) {
  super(context, attrs);
  this.context = context;
  this.attrs = attrs;
  init();
 }

 public GGEditText(Context context, AttributeSet attrs, int defStyle) {
  super(context, attrs, defStyle);
  this.context = context;
  this.attrs = attrs;
  this.defStyle = defStyle;
  init();
 }

 private void init() {
  Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/Montserrat-Light_0.otf");
  this.setTypeface(font);
 }


}