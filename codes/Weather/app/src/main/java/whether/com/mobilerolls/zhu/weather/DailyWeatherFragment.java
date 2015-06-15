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
    private View rootView;
    private int index;

    public DailyWeatherFragment() {
        // Required empty public constructor
    }

    public void setWeatherModel(WeatherModel weatherModel, int index){
        this.mWeatherModel = weatherModel;
        this.index = index;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView");

//        if (mWeatherModel == null){
//            Log.d(TAG, "reGenerate the weather");
//            mWeatherModel = WeatherModel.dummyWeather();
//        }
//
        rootView = inflater.inflate(R.layout.fragment_daily_weather, container, false);
        mDailyTitle = (TextView) rootView.findViewById(R.id.daily_title);

        mDailyIcon = (ImageView) rootView.findViewById(R.id.daily_icon);

        mDailyDesc = (TextView) rootView.findViewById(R.id.daily_desc);

        mDailyTemp = (TextView) rootView.findViewById(R.id.daily_temp);

        return rootView;
    }

    public void updateUI(){
        if (rootView!= null && this.mWeatherModel != null){
            mDailyTitle.setText(Helper.getCapture(index));
            mDailyDesc.setText(mWeatherModel.getDesciption());
            mDailyIcon.setImageResource(Helper.getDrawableID(getActivity(), mWeatherModel.getIconResource()));
            mDailyTemp.setText(mWeatherModel.getFullTemp());
        }
    }



}
