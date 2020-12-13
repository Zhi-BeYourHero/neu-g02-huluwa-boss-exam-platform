package com.boss.bes.system;

import boss.xtrain.security.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-18
 * @since
 */
public final class TypeConverterUtil {

    private TypeConverterUtil() {
    }

    public static List<String> listLongToListString(List<Long> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        String ids = StringUtils.join(list.toArray(),",");
        return Arrays.asList(ids.split(","));
    }

    public static List<Long> listStringToListLong(List<String> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        String ids = StringUtils.join(list.toArray(), ",");
        return Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
    }

}
