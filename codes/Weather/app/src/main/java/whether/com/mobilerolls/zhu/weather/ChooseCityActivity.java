package whether.com.mobilerolls.zhu.weather;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import whether.com.mobilerolls.zhu.weather.models.City;


public class ChooseCityActivity extends ActionBarActivity implements ProvinceFragment.OnFragmentInteractionListener, CityFragment.OnCityFragmentInteractionListener{
    private ArrayList<City> allCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ProvinceFragment fragment = new ProvinceFragment();
        getFragmentManager().beginTransaction().replace(R.id.choose_city_parent, fragment).commit();
//        allCities = City.loadAllCities();
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        for(City city: allCities){
//            CityFragment fragment = new CityFragment();
//            fragment.setCity(city);
//            transaction.add(R.id.city_list, fragment);
//        }
//        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void choosedCity(City city){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("city", city.getName());
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void onFragmentInteraction(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        CityFragment fragment = new CityFragment();
        fragment.setProvince(name);
        getFragmentManager().beginTransaction().replace(R.id.choose_city_parent, fragment).commit();
    }

    @Override
    public void onCityFragmentInteraction(String name) {
        City city = new City(name);
        choosedCity(city);
    }
}
