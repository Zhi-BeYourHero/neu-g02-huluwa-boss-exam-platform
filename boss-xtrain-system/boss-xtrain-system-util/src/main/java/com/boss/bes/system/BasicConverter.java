package com.boss.bes.system;

import boss.xtrain.security.util.StringUtils;
import org.springframework.cglib.core.Converter;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since
 */
public class BasicConverter implements Converter {

    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof Long && String.class == target ) {
            return value.toString();
        }else if (value instanceof String && Long.class == target){
            if(StringUtils.isNotEmpty(value.toString())){
                return Long.valueOf(value.toString());
            }else{
                return null;
            }

        }
        return value;
    }
}
