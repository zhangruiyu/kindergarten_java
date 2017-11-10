package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.7
 */
public class EqualsVerifier extends SingleVerifier<String> {

    public EqualsVerifier(Loader1A<String> valueLoader) {
        super(valueLoader);
    }

    public EqualsVerifier(String fixedValue) {
        super(fixedValue);
    }

    @Override
    protected String typedCast(String notEmptyInput) {
        return notEmptyInput;
    }

    @Override
    protected boolean performTyped(String typedInput) {
        return typedInput.equals(getBenchmarkValue());
    }
}
