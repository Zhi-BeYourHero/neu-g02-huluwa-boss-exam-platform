package boss.xtrain.util.date;

import boss.xtrain.core.exception.BaseException;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-09
 * @since
 */
public class DateException extends BaseException {
    public DateException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }
}
