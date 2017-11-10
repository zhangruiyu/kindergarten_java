package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class MaxLengthVerifier extends SingleVerifier<Long> {

    public MaxLengthVerifier(Loader1A<Long> valueLoader) {
        super(valueLoader);
    }

    public MaxLengthVerifier(long fixedValue) {
        super(fixedValue);
    }

    @Override
    protected boolean performTyped(Long typedInput) {
        return typedInput <= getBenchmarkValue();
    }

    @Override
    protected Long typedCast(String notEmptyInput) {
        return (long)notEmptyInput.length();
    }
}
