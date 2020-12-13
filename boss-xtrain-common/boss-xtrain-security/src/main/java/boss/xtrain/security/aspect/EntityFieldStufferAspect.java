package boss.xtrain.security.aspect;

import boss.xtrain.core.data.convention.base.dto.BaseDTO;
import boss.xtrain.core.data.convention.base.entity.BaseEntity;
import boss.xtrain.core.data.convention.base.vo.BaseVO;
import boss.xtrain.security.domain.LoginUser;
import boss.xtrain.security.util.SecurityUtils;
import boss.xtrain.security.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

/**
 * @description: 实体字段填充切面，插入表公用字段 org_id, company_id , created_by,created_time,updated_by,updaed_time ,
 * @author: WenZhi Luo
 * @create: 2020-07-01
 * @since
 */
@Aspect
@Component
@Configuration
public class EntityFieldStufferAspect {

    private static final String ORG_ID = "orgId";
    private static final String COMPANY_ID = "companyId";
    private static final String CREATED_BY = "createdBy";
    private static final String CREATED_TIME = "createdTime";
    private static final String UPDATED_BY = "updatedBy";
    private static final String UPDATED_TIME = "updatedTime";
    private static final String VERSION = "version";

    /**
     * 获取当前登录的用户信息
     */
    private LoginUser loginUser = loginUser();

    /**
     * 加了@AutoStuff注解的类会进行自动填充
     */
    @Pointcut("@annotation(boss.xtrain.security.annotation.stuffer.AutoStuff)")
    public void doAutoStuff(){

    }

    /**
     * 执行update时，切面增加属性
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("doAutoStuff()")
    public Object doAroundUpdate(ProceedingJoinPoint pjp) throws Throwable {

        // 获取连接点上的参数，获取对象
        Object[] objects = pjp.getArgs();
        if (objects != null && objects.length > 0) {
            for (Object arg : objects) {
                if (arg instanceof BaseDTO || arg instanceof BaseVO || arg instanceof BaseEntity) {
                    fillObject(arg);
                    break;
                }else if (arg instanceof List && !((List) arg).isEmpty()){
                    Object obj = ((List)arg).get(0);
                    if (obj instanceof BaseDTO || obj instanceof BaseVO || obj instanceof BaseEntity){
                        List<Object> objList = (List<Object>) arg;
                        for (Object object : objList) {
                            fillObject(object);
                        }
                    }
                }
            }
        }
        return pjp.proceed();
    }

    private void fillObject(Object object) throws Throwable {
        String createdBy = BeanUtils.getProperty(object, CREATED_BY);
        //如果对象是第一次被创建
        if (StringUtils.isEmpty(createdBy)){
            BeanUtils.setProperty(object,CREATED_BY,loginUser.getUserId());
            BeanUtils.setProperty(object,CREATED_TIME,new Date());
        }
        BeanUtils.setProperty(object,ORG_ID,loginUser.getOrgId());
        BeanUtils.setProperty(object,COMPANY_ID,loginUser.getCompanyId());
        //update
        BeanUtils.setProperty(object, UPDATED_BY, loginUser.getUserId());
        BeanUtils.setProperty(object, UPDATED_TIME, new Date());
        //版本号+1
        String property = BeanUtils.getProperty(object, VERSION);
        BeanUtils.setProperty(object,VERSION,Long.parseLong(property) + 1);
    }

    /**
     * 获取当前登录的对象
     * @return
     */
    private LoginUser loginUser() {
        return SecurityUtils.getLoginUser();
    }
}