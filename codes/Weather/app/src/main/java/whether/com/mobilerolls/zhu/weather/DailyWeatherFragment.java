package whether.com.mobilerolls.zhu.weather;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import whether.com.mobilerolls.zhu.weather.models.WeatherModel;


public class DailyWeatherFragment extends Fragment {


    private static final String TAG = "Weather.DailyWeatherF" ;
    private WeatherModel mWeatherModel;
    private TextView mDailyTitle;
    private ImageView mDailyIcon;
    private TextView mDailyDesc;
    private TextView mDailyTemp;
    private String mTitle;

    public DailyWeatherFragment() {
        // Required empty public constructor
    }

    public void setWeatherModel(WeatherModel weatherModel, String title){
        this.mWeatherModel = weatherModel;
        mTitle = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView");

        if (mWeatherModel == null){
            Log.d(TAG, "reGenerate the weather");
            mWeatherModel = WeatherModel.dummyWeather();
        }

        View rootView = inflater.inflate(R.layout.fragment_daily_weather, container, false);
        mDailyTitle = (TextView) rootView.findViewById(R.id.daily_title);
        mDailyTitle.setText(mTitle);

        mDailyIcon = (ImageView) rootView.findViewById(R.id.daily_icon);
        mDailyIcon.setImageResource(mWeatherModel.getIcon());

        mDailyDesc = (TextView) rootView.findViewById(R.id.daily_desc);
        mDailyDesc.setText(mWeatherModel.getDesc());

        mDailyTemp = (TextView) rootView.findViewById(R.id.daily_temp);
        mDailyTemp.setText(mWeatherModel.getTempRange());

        return rootView;
    }



}
