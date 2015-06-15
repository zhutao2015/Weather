package whether.com.mobilerolls.zhu.weather;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import whether.com.mobilerolls.zhu.weather.models.City;
import whether.com.mobilerolls.zhu.weather.models.WeatherModel;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "Weather.MainActivityF";
    private WeatherModel mWeather;
    private TextView mCityName;
    private TextView mCurrentTemp;
    private TextView mHumidity;
    private TextView mLastUpdatedAt;
    private City city;

    private View rootView;
    private ImageView mIcon;
    private Button mFreshButton;
    private ArrayList<DailyWeatherFragment> fragments;
//    private LinearLayout mBottomWeathers;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView()");
        fragments = new ArrayList<>();
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        updateUI();

//        if (getFragmentManager().findFragmentById(R.id.bottom_weathers) == null) {
//            DailyWeatherFragment today = new DailyWeatherFragment();
//            mTodayWether = WeatherModel.dummyWeather();
//            today.setWeatherModel(mTodayWether, "今天");
//            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, today).commit();
//
//            DailyWeatherFragment tomorrow = new DailyWeatherFragment();
//            mTomorrowWeather = WeatherModel.dummyWeather();
//            tomorrow.setWeatherModel(mTomorrowWeather, "明天");
//            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, tomorrow).commit();
//
//            DailyWeatherFragment day3 = new DailyWeatherFragment();
//            mDay3Weather = WeatherModel.dummyWeather();
//            day3.setWeatherModel(mDay3Weather, "后天");
//            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, day3).commit();
//        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null){

        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void updateUI(){
        if (rootView != null && this.city != null) {
            mCityName = (TextView) rootView.findViewById(R.id.cityname);
            mCityName.setText("+" + city.getName());
            mCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.chooseCity();
                }
            });

            mFreshButton = (Button) rootView.findViewById(R.id.button_refresh);
            mFreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.updateWeather();
                }
            });

            mCurrentTemp = (TextView) rootView.findViewById(R.id.current_temp);
            mCurrentTemp.setText("" + city.getTodayWeather().getCurrentDegree());

            mHumidity = (TextView) rootView.findViewById(R.id.current_humidity);
            mHumidity.setText(city.getTodayWeather().getHumidity());

            mIcon = (ImageView) rootView.findViewById(R.id.current_icon);
            mIcon.setImageResource(Helper.getDrawableID(getActivity(), city.getTodayWeather().getIconResource()));

            mLastUpdatedAt = (TextView) rootView.findViewById(R.id.current_desc);
            mLastUpdatedAt.setText(city.getTodayWeather().getDesciption());

            MainActivity activity = (MainActivity) getActivity();

            if (fragments.size() > 0){
                int i = 0;
                for(DailyWeatherFragment fragment : fragments){
                    fragment.setWeatherModel(this.city.getDaysWeathers().get(i), i);
                    fragment.updateUI();
                    i ++;
                }
            }
            else {
                int i = 0;
                FragmentManager manager = activity.getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                for(WeatherModel weatherModel : this.city.getDaysWeathers()){
                    DailyWeatherFragment fragment = new DailyWeatherFragment();
                    fragment.setWeatherModel(weatherModel, i);
                    fragments.add(fragment);
                    transaction.add(R.id.bottom_weathers, fragment);
                    i ++;
                }
                transaction.commit();

            }

        }

    }
}
