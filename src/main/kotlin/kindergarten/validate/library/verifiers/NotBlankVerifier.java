package kindergarten.validate.library.verifiers;


import kindergarten.validate.library.Verifier;

import static kindergarten.validate.library.Texts.isEmpty;
import static kindergarten.validate.library.Texts.regexMatch;

/**
 * 非空值(包含制表符等)校验器
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class NotBlankVerifier implements Verifier {
    @Override
    public boolean perform(String rawInput) throws Exception {
        if (isEmpty(rawInput)) {
            return false;
        }
        return !regexMatch(rawInput, "^\\s*$");
    }
}
