package com.zhangruiyu.github.youeryuanxiaozhushou;

import java.util.HashSet;
import java.util.Set;

public class ParamsValidate extends NextInputs {
    private final Set<StringInput> inputs = new HashSet<>();

    public ParamsValidate() {
//        setMessageDisplay(new AndroidMessageDisplay());
    }

    public ParamsValidate add(String input, String failMessage, Scheme... schemes) {
        addStringInput(new StringInput(input, failMessage), schemes);
        return this;
    }

    private void addStringInput(StringInput input, Scheme... schemes) {
        inputs.add(input);
        super.add(input, schemes);
    }
}
