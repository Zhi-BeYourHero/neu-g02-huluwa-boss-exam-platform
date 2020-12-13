package boss.xtrain.log.exception.resolver;

import boss.xtrain.core.data.convention.common.CommonResponse;
import boss.xtrain.core.exception.BusinessException;
import boss.xtrain.core.exception.error.CommonErrorCode;
import boss.xtrain.core.util.CommonResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Xx
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 全局处理响应数据
 * @create 2020-07-08 23:31
 * @since 1.0
 */
@RestControllerAdvice(basePackages = {"com.boss.bes"})
public class GlobalResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().toString().startsWith(CommonResponse.class.getName());
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在CommonResponse里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(CommonResponseUtil.success(data));
            } catch (JsonProcessingException e) {
                throw new BusinessException(CommonErrorCode.JSON_CONVERT_ERROR.getCode(), CommonErrorCode.JSON_CONVERT_ERROR.getMessage());
            }
        }
        // 将原本的数据包装在CommonResponse里
        return CommonResponseUtil.success(data);
    }
}
