package kindergarten.validate.library.verifiers;

import kindergarten.validate.library.EmptyableVerifier;

import static kindergarten.validate.library.Texts.regexMatch;


/**
 * 主机地址校验器
 * @author YOOJIA CHEN (yoojiachen@gmail.com)
 * @since 1.1
 */
public class HostVerifier extends EmptyableVerifier {

    @Override
    public boolean performTestNotEmpty(String notEmptyInput) throws Exception {
        return IPv4Verifier.isIPv4(notEmptyInput) ||
                regexMatch(notEmptyInput.toLowerCase(), "^([a-z0-9]([a-z0-9\\-]{0,65}[a-z0-9])?\\.)+[a-z]{2,6}$") ;
    }

}
