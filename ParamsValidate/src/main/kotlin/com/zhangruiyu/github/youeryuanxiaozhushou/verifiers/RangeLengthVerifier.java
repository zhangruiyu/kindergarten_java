package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;

import com.zhangruiyu.github.youeryuanxiaozhushou.Loader2A;
import com.zhangruiyu.github.youeryuanxiaozhushou.PairedVerifier;

/**
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class RangeLengthVerifier extends PairedVerifier<Long> {

    public RangeLengthVerifier(Loader2A<Long> valueLoader) {
        super(valueLoader);
    }

    public RangeLengthVerifier(long fixedFirstValue, long fixedSecondValue) {
        super(fixedFirstValue, fixedSecondValue);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        final long minLength  = benchmark1stValue();
        final long maxLength  = benchmark2ndValue();
        return minLength <= typedInput && typedInput <= maxLength;
    }

    @Override
    protected Long typedCast(String notEmptyInput) {
        return (long)notEmptyInput.length();
    }
}
