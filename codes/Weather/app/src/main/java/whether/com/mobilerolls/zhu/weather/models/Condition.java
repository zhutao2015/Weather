package whether.com.mobilerolls.zhu.weather.models;
import whether.com.mobilerolls.zhu.weather.R;

/**
 * Created by zhu on 15-5-30.
 */
public class Condition {
    public static final int SUNNY = 0;
    public static final int CLOUD = 1;
    public static final int ZHEN_RAIN = 2;
    public static final int BIG_RAIN = 3;
    public static final int MODERATE_RAIN = 4;
    public static final int SMALL_RAIN = 5;
    public static final int BIG_SNOW = 6;
    public static final int SMALL_SNOW = 7;
    public static final int MODERATE_SNOW = 8;
    public static final int ZHEN_SNOW = 9;
    public static final int YIN = 10;
    private int fromCondition;
    private int toCondition;
    private int fromIcon;
    private int toIcon;
    private String fromDesc=null;
    private String toDesc=null;

    public Condition(int fromCondition, int toCondition) {
        this.fromCondition = fromCondition;
        this.toCondition = toCondition;
        this.fromDesc = getDescFromCond(this.fromCondition);
        this.toDesc = getDescFromCond(this.toCondition);
        this.fromIcon = getIconFromCond(this.fromCondition);
        this.toIcon = getIconFromCond(this.toCondition);
    }

    public Condition(int fromCondition) {
        this.fromCondition = fromCondition;
        this.fromDesc = getDescFromCond(this.fromCondition);
        this.fromIcon = getIconFromCond(this.fromCondition);
    }

    public String getFullDesc(){
        if(this.toDesc == null){
            return this.fromDesc;
        }
        else{
            return this.fromDesc + "转" + this.toDesc;
        }
    }


    public String getDescFromCond(int cond){
        String desc;
        switch (cond) {
            case SUNNY:
                desc = "晴";
                break;
            case CLOUD:
                desc = "多云";
                break;
            case YIN:
                desc = "阴";
                break;
            case BIG_RAIN:
                desc = "大雨";
                break;
            case MODERATE_RAIN:
                desc = "中雨";
                break;
            case SMALL_RAIN:
                desc = "小雨";
                break;
            case ZHEN_RAIN:
                desc = "阵雨";
                break;
            case SMALL_SNOW:
                desc = "小雪";
                break;
            case MODERATE_SNOW:
                desc = "中雪";
                break;
            case BIG_SNOW:
                desc = "大雪";
                break;
            case ZHEN_SNOW:
                desc = "阵雪";
                break;
            default:
                desc = "晴";
                break;
        }
        return desc;

    }

    public int getIconFromCond(int cond) {
        int icon;
        switch (cond) {
            case SUNNY:
                icon = R.drawable.sun;
                break;
            case CLOUD:
            case YIN:
                icon = R.drawable.cloud;
                break;
            case BIG_RAIN:
            case MODERATE_RAIN:
            case SMALL_RAIN:
            case ZHEN_RAIN:
                icon = R.drawable.rain;
                break;
            case SMALL_SNOW:
            case MODERATE_SNOW:
            case BIG_SNOW:
            case ZHEN_SNOW:
                icon = R.drawable.snow;
                break;
            default:
                icon = R.drawable.sun;
                break;
        }
        return icon;
    }

    public int getFromIcon() {
        return fromIcon;
    }

    public void setFromIcon(int fromIcon) {
        this.fromIcon = fromIcon;
    }
}
