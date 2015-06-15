package whether.com.mobilerolls.zhu.weather.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by yy on 6/12/15.
 */
public class Storage {
    private static final String NAME = "weather";
    private static final String CITIES = "CITIES";

    public static ArrayList<City> loadCities(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, 0);
        ArrayList<City> cities = new ArrayList<>();
        Set<String> _cities = new LinkedHashSet<>();
        _cities = sharedPreferences.getStringSet(CITIES, _cities);
        for(String city : _cities){
            City c = new City(city);
            cities.add(c);
        }
        return cities;
    }

    public static void saveCity(Context context, City city, boolean isDefault){
        ArrayList<City> cities = loadCities(context);
        if (isDefault){
            cities.add(0, city);
        }
        else{
            cities.add(city);
        }
        ArrayList<String> names = new ArrayList<>();
        for(City c : cities){
            names.add(c.getName());
        }
        Set<String> _cities = new LinkedHashSet<>(names);
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME, 0).edit();
        editor.putStringSet(CITIES, _cities);
        editor.commit();
    }
}
