package whether.com.mobilerolls.zhu.weather.models;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhu on 15-5-30.
 */
public class WeatherModel {
    private String currentDegree;
    private String minDegree;
    private String maxDegree;
    private String humidity;
    private String wind;
    private String icon;
    private String desciption;

    public WeatherModel(String currentDegree, String minDegree, String maxDegree, String humidity, String wind, String icon, String desciption) {
        this.currentDegree = currentDegree;
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.humidity = humidity;
        this.wind = wind;
        this.icon = icon;
        this.desciption = desciption;
    }

    public WeatherModel(String currentDegree, String humidity, String wind, String icon, String desciption){
        this.currentDegree = currentDegree;
        this.minDegree = "";
        this.maxDegree = "";
        this.humidity = humidity;
        this.wind = wind;
        this.icon = icon;
        this.desciption = desciption;
    }

    public WeatherModel(String minDegree, String maxDegree, String humidity, String wind, String icon, String desciption){
        this.currentDegree = "";
        this.minDegree = minDegree;
        this.maxDegree = maxDegree;
        this.humidity = humidity;
        this.wind = wind;
        this.icon = icon;
        this.desciption = desciption;
    }

    public String getCurrentDegree() {
        return currentDegree;
    }

    public void setCurrentDegree(String currentDegree) {
        this.currentDegree = currentDegree;
    }

    public String getMinDegree() {
        return minDegree;
    }

    public void setMinDegree(String minDegree) {
        this.minDegree = minDegree;
    }

    public String getMaxDegree() {
        return maxDegree;
    }

    public void setMaxDegree(String maxDegree) {
        this.maxDegree = maxDegree;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconResource(){
        return "w" + icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getFullTemp(){
        return minDegree + "-" + maxDegree + "C";
    }
}
