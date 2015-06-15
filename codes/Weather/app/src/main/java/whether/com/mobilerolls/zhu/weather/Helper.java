package whether.com.mobilerolls.zhu.weather;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import whether.com.mobilerolls.zhu.weather.models.City;

/**
 * Created by yy on 6/12/15.
 */
public class Helper {
    public static int getResIDfromName(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

    public static int getDrawableID(Context context, String name) {
        return getResIDfromName(context, name, "drawable");
    }

    public static  JSONObject readRawJson(Context context){
        InputStream inputStream = context.getResources().openRawResource(R.raw.cities);
        String ret = null;
        try {
            ret = IOUtils.toString(inputStream, "UTF-8");
            JSONObject object = new JSONObject(ret);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getCapture(int i){
        if (i > 6 || i < 0) return "";
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int num = calendar.get(Calendar.DAY_OF_WEEK);
        String[] captures = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        if (i == 0){
            return "今天";
        }
        else if (i == 1){
            return "明天";
        }
        else{
            return captures[(num+i-1)%7];
        }
    }

}
