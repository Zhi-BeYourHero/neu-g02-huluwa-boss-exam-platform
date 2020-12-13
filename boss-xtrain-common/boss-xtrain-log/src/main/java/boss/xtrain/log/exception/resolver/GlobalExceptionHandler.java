package boss.xtrain.log.exception.resolver;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.util.CommonResponseUtil;
import boss.xtrain.core.exception.BaseException;
import boss.xtrain.core.exception.error.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangziyi
 * @program boss-xtrain-log
 * @Description 统一异常处理类
 * @Date 2020/7/2 8:25
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数校验异常
     *
     * @param e 异常
     * @return  响应信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<Map<String, String>> handleIllegalParamException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>(fieldErrors.size());
        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
            log.error("field: {}, message: {}", field, message);
        }
        return CommonResponseUtil.error(CommonErrorCode.PARAM_ERROR.getCode(),
                CommonErrorCode.PARAM_ERROR.getMessage(),
                errors);
    }

    /**
     * 处理BaseException
     *
     * @param e 异常
     * @param request 请求
     * @return 响应信息
     */
    @ExceptionHandler(BaseException.class)
    public CommonResponse<String> handleResultException(BaseException e, HttpServletRequest request) {

        log.error("Request URI = {}", request.getRequestURI());
        log.error("module: {}, code: {}, defaultMessage: {}", e.getModule(), e.getCode(), e.getDefaultMessage());
        return CommonResponseUtil.error(e.getCode(),e.getDefaultMessage(), e.getModule());
    }


    /**
     * 处理其他异常
     *
     * @param e 异常
     * @param request 请求
     * @return 响应信息
     */
    @ExceptionHandler(Exception.class)
    public CommonResponse<String> handleException(Exception e, HttpServletRequest request) {
        log.error("Request URI = {}", request.getRequestURI());
        return CommonResponseUtil.error(CommonErrorCode.RPC_ERROR.getCode(),
                CommonErrorCode.RPC_ERROR.getMessage(), e.getMessage());
    }
}
