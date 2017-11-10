package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;


import com.zhangruiyu.github.youeryuanxiaozhushou.Loader1A;
import com.zhangruiyu.github.youeryuanxiaozhushou.SingleVerifier;

/**
 * 布尔值校验器
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class BoolVerifier extends SingleVerifier<String> {

    public BoolVerifier(final Loader1A<Boolean> valueLoader) {
        super(new Loader1A<String>() {
            @Override
            public String getValue() {
                return valueLoader.getValue().toString();
            }
        });
    }

    public BoolVerifier(Boolean fixedValue) {
        super(fixedValue.toString());
    }

    @Override
    protected String typedCast(String notEmptyInput) {
        return notEmptyInput.toLowerCase();
    }

    @Override
    protected boolean performTyped(String typedInput) {
        return typedInput.equals(getBenchmarkValue());
    }
}
