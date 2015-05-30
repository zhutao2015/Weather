package whether.com.mobilerolls.zhu.weather.models;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by zhu on 15-5-30.
 */
public class WeatherModel {
    private String city;
    private int currentDegree;
    private int minDegree;
    private int maxDegree;
    private String humidity;
    private String lastUpdatedAt;
    private String wind;
    private Condition condition;
    private String desciption;

    public WeatherModel(String city, int currentDegree, int minDegree, int maxDegree, String humidity, String lastUpdatedAt, String wind, int fromCond, int toCond) {
        this.city = city;
        this.currentDegree = currentDegree;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.humidity = humidity;
        this.lastUpdatedAt = lastUpdatedAt;
        this.wind = wind;
        condition = new Condition(fromCond, toCond);
    }

    public WeatherModel(String city, int currentDegree, int minDegree, int maxDegree, String humidity, String lastUpdatedAt, String wind, int fromCond) {
        this.city = city;
        this.currentDegree = currentDegree;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.humidity = humidity;
        this.lastUpdatedAt = lastUpdatedAt;
        this.wind = wind;
        condition = new Condition(fromCond);
    }

    public static WeatherModel dummyWeather(){
        Random random = new Random();
        String city = Constant.CITIES[random.nextInt(Constant.CITIES.length)];
        int minD = random.nextInt(10) + 10;
        int maxD = random.nextInt(10) + minD;
        int currentD = (minD + maxD) / 2;
        int hum = random.nextInt(100);
        int fCond = random.nextInt(11);
        int tCond = random.nextInt(11);
        WeatherModel m;
        if (fCond == tCond){
            m = new WeatherModel(city, currentD, minD, maxD, hum + "%", "06:30", "东风2级", fCond);
        }
        else {
            m = new WeatherModel(city, currentD, minD, maxD, hum + "%", "06:30", "东风2级", fCond, tCond);
        }

        return m;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCurrentDegree() {
        return currentDegree;
    }

    public void setCurrentDegree(int currentDegree) {
        this.currentDegree = currentDegree;
    }

    public int getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(int minDegree) {
        this.minDegree = minDegree;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(int maxDegree) {
        this.maxDegree = maxDegree;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getDesc() {
        return condition.getFullDesc();
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public int getIcon(){
        return condition.getFromIcon();
    }

    public String getTempRange() {
        return minDegree + " - " + getMaxDegree() + "C";
    }

    public String getHumidityAndWind() {
        return "湿度: "+getHumidity()+ " " + getWind();

    }

    public static void refresh(WeatherModel[] weatherModels) throws IOException, JSONException {
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=xian,cn&mode=json&units=metric&cnt=3&APPID=52c247c918bb9af33d30382bb4bfbfd2&lang=zh_cn";
        URL theurl = new URL(null, url);
        HttpURLConnection conn = (HttpURLConnection) theurl.openConnection();
        conn.setConnectTimeout(3000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200){
            InputStream cont = conn.getInputStream();
            String encoding = conn.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String ret = IOUtils.toString(cont, encoding);
            JSONObject object = new JSONObject(ret);
            JSONArray weathers = object.getJSONArray("list");
            for(int i=0; i<weathers.length(); i++){
                JSONObject w = (JSONObject)weathers.get(i);
                weatherModels[i].setHumidity(w.getInt("humidity") + "%");
                JSONObject temp = (JSONObject) w.get("temp");
                weatherModels[i].setMinDegree((int) temp.getDouble("min"));
                weatherModels[i].setMaxDegree((int) temp.getDouble("max"));
//                weatherModels[i].setCurrentDegree((int)temp.getDouble("min"));
                weatherModels[i].setWind((int) w.getDouble("speed") + "级");
                JSONObject _weather = (JSONObject) w.getJSONObject("weather");
                weatherModels[i].setDesciption(_weather.getString("description"));
            }
        }

    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
