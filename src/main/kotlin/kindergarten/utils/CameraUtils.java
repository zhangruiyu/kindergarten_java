package kindergarten.utils;

public class CameraUtils {

    //判断是否可以观看
    public static int isUnWatch() {
        return (DateUtils.isBelong("09:00:00", "10:45:59") || DateUtils.isBelong("15:30:00", "17:35:59")) ? 1 : 0;
    }
}
