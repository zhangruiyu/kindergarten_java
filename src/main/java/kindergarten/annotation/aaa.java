package kindergarten.annotation;

/**
 * Created by zhangruiyu on 2017/4/26.
 */
public @interface aaa {
    String name();
    int age() default 10;
}
