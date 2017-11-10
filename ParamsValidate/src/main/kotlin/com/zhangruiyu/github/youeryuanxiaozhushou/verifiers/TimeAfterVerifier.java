package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1B;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.7
 */
public class TimeAfterVerifier extends SingleDateTimeVerifier {
    
    public TimeAfterVerifier(Loader1A<Date> valueLoader) {
        super(valueLoader, TIME_FORMAT);
    }

    public TimeAfterVerifier(Loader1A<Date> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public TimeAfterVerifier(Loader1B<String> valueLoader) {
        super(valueLoader, TIME_FORMAT);
    }

    public TimeAfterVerifier(Loader1B<String> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public TimeAfterVerifier(Date fixedValue) {
        super(fixedValue, TIME_FORMAT);
    }

    public TimeAfterVerifier(Date fixedValue, SimpleDateFormat format) {
        super(fixedValue, format);
    }

    public TimeAfterVerifier(String fixedValue) {
        super(fixedValue, TIME_FORMAT);
    }

    public TimeAfterVerifier(String fixedValue, SimpleDateFormat format) {
        super(fixedValue, format);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        return getBenchmarkValue() < typedInput;
    }

}
