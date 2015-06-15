package whether.com.mobilerolls.zhu.weather.models;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import whether.com.mobilerolls.zhu.weather.Helper;
import whether.com.mobilerolls.zhu.weather.WeatherService;

/**
 * Created by yy on 6/12/15.
 */
public class City {
    private String name;
    private WeatherModel todayWeather;
    private ArrayList<WeatherModel> daysWeathers;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherModel getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(WeatherModel todayWeather) {
        this.todayWeather = todayWeather;
    }

    public ArrayList<WeatherModel> getDaysWeathers() {
        return daysWeathers;
    }

    public void setDaysWeathers(ArrayList<WeatherModel> daysWeathers) {
        this.daysWeathers = daysWeathers;
    }

    public boolean updateTodayWeather(){
        JSONObject object = WeatherService.getTodayWeather(this.getName());
        try {
            JSONObject weather = object.getJSONArray("weather").getJSONObject(0);
            String desc = weather.getString("description");
            String icon = weather.getString("icon");
            JSONObject temp = object.getJSONObject("main");
            String currentTemp = String.valueOf(Math.round(temp.getDouble("temp")));
            String humidity = String.valueOf(temp.getInt("humidity"));
            JSONObject wind = object.getJSONObject("wind");
            String w = String.valueOf(wind.getDouble("deg")) + " " +
                    String.valueOf(wind.getDouble("speed"));
            this.setTodayWeather(new WeatherModel(currentTemp, humidity, w, icon, desc));
            return true;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean update5DaysWeather(){
        JSONObject object = WeatherService.get5DaysWeather(this.getName());
        ArrayList<WeatherModel> weatherModels = new ArrayList<>();
        try {
            JSONArray weathers = object.getJSONArray("list");
            for(int i=0; i<weathers.length(); i++){
                JSONObject weather = weathers.getJSONObject(i);
                JSONObject temp = weather.getJSONObject("temp");
                String minDegree = String.valueOf(Math.round(temp.getDouble("min")));
                String maxDegree = String.valueOf(Math.round(temp.getDouble("max")));
                String humidity = String.valueOf(weather.getInt("humidity"));
                String desc = weather.getJSONArray("weather").getJSONObject(0).getString("description");
                String icon = weather.getJSONArray("weather").getJSONObject(0).getString("icon");
                String wind = String.valueOf(weather.getDouble("deg")) + " " +
                        String.valueOf(weather.getDouble("speed"));
                weatherModels.add(new WeatherModel(minDegree, maxDegree, humidity, wind, icon, desc));
            }
            this.setDaysWeathers(weatherModels);
            return true;
        }
        catch (JSONException e) {
            e.printStackTrace();
            this.setDaysWeathers(weatherModels);
            return false;
        }

    }

    public static ArrayList<String> loadCities(Context context, String province){
        JSONObject jsonObject = Helper.readRawJson(context);
        ArrayList<String> cities = new ArrayList<>();
        try {


            JSONArray _cities = jsonObject.getJSONArray(province);

            for (int i = 0; i < _cities.length(); i++) {
                String city = _cities.getString(i);
                cities.add(city);
            }
            return cities;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return cities;
        }
    }

    public static ArrayList<String> loadProvinces(Context context){
        JSONObject cities = Helper.readRawJson(context);
        Iterator<String> provinces = cities.keys();
        ArrayList<String> p = new ArrayList<>();
        while (provinces.hasNext()){
            p.add(provinces.next());
        }
        return p;
    }

}
