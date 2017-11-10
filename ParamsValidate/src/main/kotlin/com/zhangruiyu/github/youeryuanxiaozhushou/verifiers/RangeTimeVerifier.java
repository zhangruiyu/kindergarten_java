package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader2A;
import com.zhangruiyu.github.youeryuanxiaozhushou.Loader2B;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.7
 */
public class RangeTimeVerifier extends PairedDateTimeVerifier {

    public RangeTimeVerifier(Loader2B<String> valueLoader) {
        super(valueLoader, SingleDateTimeVerifier.TIME_FORMAT);
    }

    public RangeTimeVerifier(Loader2B<String> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public RangeTimeVerifier(Loader2A<Date> valueLoader) {
        super(valueLoader, SingleDateTimeVerifier.TIME_FORMAT);
    }

    public RangeTimeVerifier(Loader2A<Date> valueLoader, SimpleDateFormat format) {
        super(valueLoader, format);
    }

    public RangeTimeVerifier(Date fixedFirstValue, Date fixedSecondValue, SimpleDateFormat format) {
        super(fixedFirstValue, fixedSecondValue, format);
    }

    public RangeTimeVerifier(Date fixedFirstValue, Date fixedSecondValue) {
        super(fixedFirstValue, fixedSecondValue, SingleDateTimeVerifier.TIME_FORMAT);
    }

    public RangeTimeVerifier(String fixedFirstValue, String fixedSecondValue, SimpleDateFormat format) {
        super(fixedFirstValue, fixedSecondValue, format);
    }

    public RangeTimeVerifier(String fixedFirstValue, String fixedSecondValue) {
        super(fixedFirstValue, fixedSecondValue, SingleDateTimeVerifier.TIME_FORMAT);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        return benchmark1stValue() <= typedInput && typedInput <= benchmark2ndValue();
    }
}
