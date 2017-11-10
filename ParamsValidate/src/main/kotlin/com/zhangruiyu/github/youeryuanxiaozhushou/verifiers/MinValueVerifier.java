package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;

import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class MinValueVerifier extends SingleVerifier<Double> {

    public MinValueVerifier(Loader1A<Double> valueLoader) {
        super(valueLoader);
    }

    public MinValueVerifier(double fixedValue) {
        super(fixedValue);
    }

    @Override
    protected Double typedCast(String notEmptyInput) {
        return Double.parseDouble(notEmptyInput);
    }

    @Override
    protected boolean performTyped(Double typedInput) {
        return typedInput >= getBenchmarkValue();
    }

}
