package boss.xtrain.util.convert;

import boss.xtrain.core.exception.BaseException;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-09
 * @since
 */
public class BeanException extends BaseException {
    public BeanException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }
}
