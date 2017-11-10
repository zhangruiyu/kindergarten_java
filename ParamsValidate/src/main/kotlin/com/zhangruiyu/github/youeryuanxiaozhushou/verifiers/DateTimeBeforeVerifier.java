package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1B;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.7
 */
public class DateTimeBeforeVerifier extends DateTimeAfterVerifier{

    public DateTimeBeforeVerifier(Loader1A<Date> valueLoader) {
        super(valueLoader);
    }

    public DateTimeBeforeVerifier(Loader1A<Date> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public DateTimeBeforeVerifier(Loader1B<String> valueLoader) {
        super(valueLoader);
    }

    public DateTimeBeforeVerifier(Loader1B<String> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public DateTimeBeforeVerifier(Date fixedValue) {
        super(fixedValue);
    }

    public DateTimeBeforeVerifier(Date fixedValue, SimpleDateFormat format) {
        super(fixedValue, format);
    }

    public DateTimeBeforeVerifier(String fixedValue) {
        super(fixedValue);
    }

    public DateTimeBeforeVerifier(String fixedValue, SimpleDateFormat format) {
        super(fixedValue, format);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        return getBenchmarkValue() > typedInput;
    }
}
