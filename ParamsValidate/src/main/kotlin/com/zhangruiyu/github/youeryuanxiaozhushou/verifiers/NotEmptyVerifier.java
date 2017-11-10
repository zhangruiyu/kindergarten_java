package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;

import com.zhangruiyu.github.youeryuanxiaozhushou.Verifier;

import static com.zhangruiyu.github.youeryuanxiaozhushou.Texts.isEmpty;


/**
 * 非空校验器
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class NotEmptyVerifier implements Verifier {
    @Override
    public boolean perform(String rawInput) throws Exception {
        return !isEmpty(rawInput);
    }
}
