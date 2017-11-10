package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.6.1
 */
public class FixedLengthVerifier extends SingleVerifier<Long> {

    public FixedLengthVerifier(Loader1A<Long> valueLoader) {
        super(valueLoader);
    }

    public FixedLengthVerifier(long fixedValue) {
        super(fixedValue);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        return typedInput.longValue() == getBenchmarkValue();
    }

    @Override
    protected Long typedCast(String notEmptyInput) {
        return (long)notEmptyInput.length();
    }
}
