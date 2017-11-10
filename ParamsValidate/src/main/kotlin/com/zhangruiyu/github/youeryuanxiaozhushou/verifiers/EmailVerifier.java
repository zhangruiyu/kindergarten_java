package com.zhangruiyu.github.youeryuanxiaozhushou.verifiers;

import com.zhangruiyu.github.youeryuanxiaozhushou.EmptyableVerifier;

import static com.zhangruiyu.github.youeryuanxiaozhushou.Texts.regexMatch;

/**
 * 邮件地址校验器
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.1
 */
public class EmailVerifier extends EmptyableVerifier {
    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return regexMatch(notEmptyInput.toLowerCase(), "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
    }
}
