package com.example.weatherapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class ApplicationClass extends Application {

    private Context context;
    private Typeface typeface;
    public ApplicationClass(Context context){
        this.context = context;
    }

    public Typeface getFont(){
        if (typeface == null){
            typeface = Typeface.createFromAsset(context.getAssets(),"fonts/font.ttf");
        }
        return typeface;
    }

}
