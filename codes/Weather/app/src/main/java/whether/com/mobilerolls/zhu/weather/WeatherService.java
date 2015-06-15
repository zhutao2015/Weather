package whether.com.mobilerolls.zhu.weather;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by yy on 6/12/15.
 */
public class WeatherService {
    private static final String APPID = "52c247c918bb9af33d30382bb4bfbfd2";
    private static final String LANG = "zh_cn";

    public static String appendSpec(String url) {
        return url + "&APPID=" + APPID + "&lang=" + LANG + "&units=metric&mode=json";
    }

    public static JSONObject getTodayWeather(String cityName) {
        try {
            cityName = URLEncoder.encode(cityName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + ",cn";
        url = appendSpec(url);
        URL theurl = null;
        try {
            theurl = new URL(null, url);
            HttpURLConnection conn = (HttpURLConnection) theurl.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream cont = conn.getInputStream();
                String encoding = conn.getContentEncoding();
                encoding = encoding == null ? "UTF-8" : encoding;
                String ret = IOUtils.toString(cont, encoding);
                JSONObject object = new JSONObject(ret);
                return object;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject get5DaysWeather(String cityName) {
        try {
            cityName = URLEncoder.encode(cityName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + cityName + ",cn&cnt=5";
        url = appendSpec(url);
        URL theurl = null;
        try {
            theurl = new URL(null, url);
            HttpURLConnection conn = (HttpURLConnection) theurl.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream cont = conn.getInputStream();
                String encoding = conn.getContentEncoding();
                encoding = encoding == null ? "UTF-8" : encoding;
                String ret = IOUtils.toString(cont, encoding);
                JSONObject object = new JSONObject(ret);
                return object;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
