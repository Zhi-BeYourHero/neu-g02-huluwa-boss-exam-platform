package boss.xtrain.util.cdn;

import boss.xtrain.core.exception.BaseException;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description CDN异常
 * @create 2020-07-07 23:51
 * @since 1.0
 */

public class CdnException extends BaseException {

    public CdnException(String module, String code, String defaultMessage) {
        super(module, code, defaultMessage);
    }

    public CdnException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public CdnException(String defaultMessage) {
        super(defaultMessage);
    }
}