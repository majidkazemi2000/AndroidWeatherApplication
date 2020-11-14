package com.example.weatherapplication;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.weatherapplication.CustomViews.CustomButton;
import com.example.weatherapplication.CustomViews.CustomEditText;
import com.example.weatherapplication.CustomViews.CustomTextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout level1,level2,level3;
    CustomButton send_city_name;
    CustomEditText city_name_edit_text;
    SharedPreferences sharedPreferences;
    String city_name;
    CoordinatorLayout coordinatorLayout;
    CustomTextView temp,min_temp,max_temp,weather_text,humidity,wind_speed,name;
    ImageView settings,weather_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        sharedPreferences = getSharedPreferences("settings",MODE_PRIVATE);
        city_name = sharedPreferences.getString("city","");

        if (city_name == ""){
            // show level 3
            level1.setVisibility(View.INVISIBLE);
            level2.setVisibility(View.INVISIBLE);
            level3.setVisibility(View.VISIBLE);

            send_city_name.setEnabled(false);
            city_name_edit_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() >= 2){
                        send_city_name.setEnabled(true);
                    }else{
                        send_city_name.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            send_city_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Services.getWeather(new Services.GetInformation() {
                        @Override
                        public void getData(Weather weather) {
                            level3.setVisibility(View.INVISIBLE);
                            level2.setVisibility(View.INVISIBLE);
                            level1.setVisibility(View.VISIBLE);
                            city_name = city_name_edit_text.getText().toString();
                            showInformation(weather);
                        }
                    },MainActivity.this,city_name_edit_text.getText().toString());
                }
            });



        }else{
            // show level 1
            level3.setVisibility(View.INVISIBLE);
            level2.setVisibility(View.INVISIBLE);
            level1.setVisibility(View.VISIBLE);
            Services.getWeather(new Services.GetInformation() {
                @Override
                public void getData(Weather weather) {
                    showInformation(weather);
                }
            },this,city_name);
        }





    }

    private void showInformation(Weather weather) {

        if (weather != null){
            level3.setVisibility(View.INVISIBLE);
            level2.setVisibility(View.VISIBLE);
            level1.setVisibility(View.INVISIBLE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("city",city_name);
            editor.apply();

            name.setText(city_name);
            temp.setText(String.valueOf(weather.getTemp())  + Html.fromHtml("&#xb0;"));
            min_temp.setText(String.valueOf(weather.getMin_temp()) + Html.fromHtml("&#xb0;"));
            max_temp.setText(String.valueOf(weather.getMax_temp())  + Html.fromHtml("&#xb0;"));
            weather_text.setText(weather.getWeather_text());
            weather_icon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),weather.getWeather_icon(),null));
            humidity.setText(String.valueOf(weather.getHumidity()) + "%");
            wind_speed.setText(String.valueOf(weather.getSpeed_wind()) + "m/s");


        }else{
            //show error
            level2.setVisibility(View.INVISIBLE);
            level1.setVisibility(View.INVISIBLE);
            level3.setVisibility(View.VISIBLE);
            Snackbar snackbar = Snackbar.make(coordinatorLayout,"خطا در دریافت اطلاعات",Snackbar.LENGTH_SHORT);

            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
            snackbarLayout.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimary,null));
            snackbarLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ApplicationClass applicationClass = new ApplicationClass(this);
            TextView textView = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTypeface(applicationClass.getFont());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            textView.setTextColor(ResourcesCompat.getColor(getResources(),R.color.black,null));
            snackbar.show();
        }

    }

    private void initViews() {
        level1 = findViewById(R.id.level_1);
        level2 = findViewById(R.id.level_2);
        level3 = findViewById(R.id.level_3);

        city_name_edit_text = findViewById(R.id.edit_text_city_name);
        send_city_name = findViewById(R.id.get_information_button);
        coordinatorLayout = findViewById(R.id.root);
        settings = findViewById(R.id.setting_icon);

        temp = findViewById(R.id.temperature);
        min_temp = findViewById(R.id.min_temp);
        max_temp = findViewById(R.id.max_temp);
        weather_text = findViewById(R.id.weather_text);
        weather_icon = findViewById(R.id.weather_icon);
        humidity = findViewById(R.id.humidity);
        wind_speed = findViewById(R.id.wind_speed);
        name = findViewById(R.id.city_name);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level3.setVisibility(View.VISIBLE);
                level2.setVisibility(View.INVISIBLE);
                level1.setVisibility(View.INVISIBLE);

                send_city_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Services.getWeather(new Services.GetInformation() {
                            @Override
                            public void getData(Weather weather) {
                                level3.setVisibility(View.INVISIBLE);
                                level2.setVisibility(View.INVISIBLE);
                                level1.setVisibility(View.VISIBLE);
                                city_name = city_name_edit_text.getText().toString();
                                showInformation(weather);
                            }
                        },MainActivity.this,city_name_edit_text.getText().toString());
                    }
                });
            }
        });
    }
}
