package boss.xtrain.util.convert;



import org.springframework.beans.*;



/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @create 2020-07-05
 * @since 1.0
 */
public class BeanUtil extends BeanUtils {

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        copyProperties(src, dest);
    }

    /**
     * 类型转化
     *
     * @param source    源对象
     * @param targetClass 目标对象类型
     * @return 目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) throws BeanException {
        try{
            if (source == null || targetClass == null) {
                return null;
            }
            T doInstance = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, doInstance);
            return doInstance;
        }catch (IllegalAccessException|InstantiationException e){
            throw new BeanException("BEAN_CONVERT","200903","BEAN_ILLEGAL_OR_INSTANCE");
        }
    }
}
