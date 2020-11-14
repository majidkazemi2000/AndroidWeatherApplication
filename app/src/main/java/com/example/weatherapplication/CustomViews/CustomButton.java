package com.example.weatherapplication.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.weatherapplication.ApplicationClass;

public class CustomButton extends android.support.v7.widget.AppCompatButton {
    public CustomButton(Context context) {
        super(context);
        setFont(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }
    public void setFont(Context context){
        ApplicationClass applicationClass = new ApplicationClass(context);
        setTypeface(applicationClass.getFont());
    }
}
