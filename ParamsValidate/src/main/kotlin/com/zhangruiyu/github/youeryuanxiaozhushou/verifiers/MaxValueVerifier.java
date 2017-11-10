package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class MaxValueVerifier extends SingleVerifier<Double> {

    public MaxValueVerifier(Loader1A<Double> valueLoader) {
        super(valueLoader);
    }

    public MaxValueVerifier(double fixedValue) {
        super(fixedValue);
    }

    @Override
    protected Double typedCast(String notEmptyInput) {
        return Double.parseDouble(notEmptyInput);
    }

    @Override
    protected boolean performTyped(Double typedInput) {
        final Double based = getBenchmarkValue();
        return typedInput < based || typedInput.equals(based);
    }

}
