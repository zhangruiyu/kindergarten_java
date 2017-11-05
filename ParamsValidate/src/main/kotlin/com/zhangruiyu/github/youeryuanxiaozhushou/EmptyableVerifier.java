package com.zhangruiyu.github.youeryuanxiaozhushou;


import static com.zhangruiyu.github.youeryuanxiaozhushou.Texts.isEmpty;

/**
 * Allow empty input Verifier
 * @author 陈小锅 (yoojia.chen@gmail.com)
 */
public abstract class EmptyableVerifier implements Verifier {

    @Override
    public final boolean perform(String rawInput) throws Exception {
        if (isEmpty(rawInput)) {
            return true;
        }else{
            return performTestNotEmpty(rawInput);
        }
    }

    public abstract boolean performTestNotEmpty(String notEmptyInput) throws Exception;

}
