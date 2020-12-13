package boss.xtrain.util.file;

import boss.xtrain.core.exception.BaseException;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-09
 * @since 1.0
 */
public class FileException extends BaseException {
    public FileException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }
}
