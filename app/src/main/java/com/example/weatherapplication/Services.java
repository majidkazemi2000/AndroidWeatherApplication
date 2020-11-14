package com.example.weatherapplication;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Services {

    public static void getWeather(final GetInformation getInformation, Context context, String city_name){
        String SERVER = "http://api.openweathermap.org/data/2.5/weather?q="+city_name+"&APPID=<APP_KEY>";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SERVER, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0){
                    try {
                        Weather weather = new Weather();
                        weather.setWeather_id(response.getJSONArray("weather").getJSONObject(0).getInt("id"));
                        JSONObject main = response.getJSONObject("main");
                        weather.setTemp(Integer.valueOf((int) (main.getDouble("temp") - 272.5)));
                        weather.setMin_temp(Integer.valueOf((int) (main.getDouble("temp_min")-272.5)));
                        weather.setMax_temp(Integer.valueOf((int) (main.getDouble("temp_max") - 272.5)));
                        weather.setHumidity(main.getInt("humidity"));
                        weather.setSpeed_wind(Integer.valueOf((int) response.getJSONObject("wind").getDouble("speed")));

                        int weather_id =response.getJSONArray("weather").getJSONObject(0).getInt("id");
                        switch (weather_id/100){
                            case 2:
                                weather.setWeather_text("رعد و برق");
                                weather.setWeather_icon(R.drawable.thunder);
                                break;
                            case 3:
                                weather.setWeather_text("بارش کم باران");
                                weather.setWeather_icon(R.drawable.rain);
                                break;
                            case 5:
                                weather.setWeather_text("بارش باران");
                                weather.setWeather_icon(R.drawable.rain);
                                break;
                            case 6:
                                weather.setWeather_text("بارش برف");
                                weather.setWeather_icon(R.drawable.snow);
                                break;
                            case 8:
                                if (weather_id == 800){
                                    weather.setWeather_text("صاف");
                                    weather.setWeather_icon(R.drawable.sunny);
                                }else{
                                    weather.setWeather_text("ابری");
                                    weather.setWeather_icon(R.drawable.cloud);
                                }
                                break;

                            case 7:
                                switch (weather_id){
                                    case 701:
                                        weather.setWeather_text("گرد و غبار");
                                        weather.setWeather_icon(R.drawable.mist);
                                        break;
                                    case 711:
                                    case 731:
                                        weather.setWeather_text("گرد و خاک");
                                        weather.setWeather_icon(R.drawable.mist);
                                        break;
                                    case 721:
                                    case 741:
                                        weather.setWeather_text("مه آلود");
                                        weather.setWeather_icon(R.drawable.cloudy);
                                        break;
                                    case 781:
                                        weather.setWeather_text("گردباد");
                                        weather.setWeather_icon(R.drawable.tornado);
                                        break;

                                    default:
                                        weather.setWeather_text("گرد و خاک");
                                        weather.setWeather_icon(R.drawable.mist);
                                        break;
                                }
                            default:
                                weather.setWeather_text("صاف");
                                weather.setWeather_icon(R.drawable.sunny);
                        }



                        getInformation.getData(weather);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }else{
                    getInformation.getData(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getInformation.getData(null);
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }

    public interface GetInformation{
        void getData(Weather weather);
    }

}
