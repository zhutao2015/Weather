package whether.com.mobilerolls.zhu.weather;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private WeatherModel mTodayWether;
    private WeatherModel mTomorrowWeather;
    private WeatherModel mDay3Weather;
    private LinearLayout mBottomWeathers;
//    private LinearLayout mBottomWeathers;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView()");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mWeather = WeatherModel.dummyWeather();
        mCityName = (TextView) rootView.findViewById(R.id.cityname);
        mCityName.setText("+" + mWeather.getCity());
        mCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseCityActivity.class);
                startActivity(intent);
            }
        });
        mCurrentTemp = (TextView) rootView.findViewById(R.id.current_temp);
        mCurrentTemp.setText("" + mWeather.getCurrentDegree());

        mHumidity = (TextView) rootView.findViewById(R.id.humidity);
        mHumidity.setText(mWeather.getHumidityAndWind());

        mLastUpdatedAt = (TextView) rootView.findViewById(R.id.last_updated_at);
        mLastUpdatedAt.setText("最近更新于:" + mWeather.getLastUpdatedAt());

        if (getFragmentManager().findFragmentById(R.id.bottom_weathers) == null) {
            DailyWeatherFragment today = new DailyWeatherFragment();
            mTodayWether = WeatherModel.dummyWeather();
            today.setWeatherModel(mTodayWether, "今天");
            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, today).commit();

            DailyWeatherFragment tomorrow = new DailyWeatherFragment();
            mTomorrowWeather = WeatherModel.dummyWeather();
            tomorrow.setWeatherModel(mTomorrowWeather, "明天");
            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, tomorrow).commit();

            DailyWeatherFragment day3 = new DailyWeatherFragment();
            mDay3Weather = WeatherModel.dummyWeather();
            day3.setWeatherModel(mDay3Weather, "后天");
            getFragmentManager().beginTransaction().add(R.id.bottom_weathers, day3).commit();
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState == null){

        }
    }
}
