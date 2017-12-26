package kindergarten.validate.library.verifiers;


import kindergarten.validate.library.Loader1A;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.7
 */
public class NotEqualsVerifier extends EqualsVerifier {

    public NotEqualsVerifier(Loader1A<String> valueLoader) {
        super(valueLoader);
    }

    public NotEqualsVerifier(String fixedValue) {
        super(fixedValue);
    }

    @Override
    protected boolean performTyped(String typedInput) {
        return !super.performTyped(typedInput);
    }

}
