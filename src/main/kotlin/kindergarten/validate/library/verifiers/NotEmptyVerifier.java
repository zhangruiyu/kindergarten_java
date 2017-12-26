package kindergarten.validate.library.verifiers;

import kindergarten.validate.library.Verifier;

import static kindergarten.validate.library.Texts.isEmpty;


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
