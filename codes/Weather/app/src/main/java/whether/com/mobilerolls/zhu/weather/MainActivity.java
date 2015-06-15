package whether.com.mobilerolls.zhu.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;

import whether.com.mobilerolls.zhu.weather.models.City;
import whether.com.mobilerolls.zhu.weather.models.Storage;


public class MainActivity extends Activity {

    private static final String TAG = "Weather.MainActivity";
    private ArrayList<City> cities;
    private City currentCity;
    private UpdateWeatherTask task;
    private ProgressDialog progressDialog;
    MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate()");
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        fragment = (MainActivityFragment) getFragmentManager().findFragmentById(R.id.fragment);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("天气信息更新中...");

        if (loadCity()) {
            updateWeather();
            setContentView(R.layout.activity_main);
        }
        else{

        }

    }

    public void chooseCity(){
        Intent intent = new Intent(this, ChooseCityActivity.class);
        startActivityForResult(intent, 100);
    }

    private boolean loadCity(){
        cities = Storage.loadCities(this);
        if (cities.size() == 0){
            chooseCity();
            return false;
        }
        else{
            this.currentCity = cities.get(0);
            updateWeather();
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode){
            case RESULT_OK:
                String city = data.getStringExtra("city");
                City c = new City(city);
                Storage.saveCity(this, c, true);
                loadCity();
                break;
        }
    }

    public void updateWeather() {
        task = new UpdateWeatherTask();
        showProgress();
        task.execute();
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void dismissProgress(){
        progressDialog.dismiss();
    }

    public void refreshUI(){
        dismissProgress();
        if (fragment == null){
            fragment = (MainActivityFragment) getFragmentManager().findFragmentById(R.id.fragment);
        }
        if(fragment != null) {
            fragment.setCity(currentCity);
            fragment.updateUI();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public City getCurrentCity() {
        return currentCity;
    }

    private class UpdateWeatherTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            getCurrentCity().updateTodayWeather();
            getCurrentCity().update5DaysWeather();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            refreshUI();
        }
    }
}
