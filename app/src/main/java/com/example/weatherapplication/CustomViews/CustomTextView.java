package com.example.weatherapplication.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.weatherapplication.ApplicationClass;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
        setFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }
    public void setFont(Context context){
        ApplicationClass applicationClass = new ApplicationClass(context);
        setTypeface(applicationClass.getFont());
    }
}
