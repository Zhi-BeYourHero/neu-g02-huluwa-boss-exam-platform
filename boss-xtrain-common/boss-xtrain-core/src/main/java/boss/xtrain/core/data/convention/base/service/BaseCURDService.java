package boss.xtrain.core.data.convention.base.service;

import boss.xtrain.core.data.convention.base.dao.impl.AbstractBaseDao;
import boss.xtrain.core.exception.BusinessException;
import boss.xtrain.core.exception.error.CommonErrorCode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 如果子类需要继承父service基本的增删改查方法则可以继承该类，子类需要重写查询 query方法和
 * dto到entity转化的方法 doObjectTransf
 * @create 2020-07-08
 * @since
 */
public abstract class BaseCURDService<D,T,Q,M>  {

    protected AbstractBaseDao<T,? extends Mapper<T>,Q> myDao;

    public Integer save(D dto){
        try {
            //把dto转为entity
            T entity=doDTOTransf2Entity(dto);
            return  myDao.save(entity);
        }catch (Exception ex){
            throw new BusinessException(CommonErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_CREATE,ex);
        }
    }

    public Integer remove(D dto){
        try {
            T entity=doDTOTransf2Entity(dto);
            return  myDao.delete(entity);
        }catch (Exception ex){
            throw new BusinessException(CommonErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_DELETE,ex);
        }
    }

    public Integer update(D dto){
        try {
            T entity=doDTOTransf2Entity(dto);
            return  myDao.update(entity);
        }catch (Exception ex){
            throw new BusinessException(CommonErrorCode.BASE_CRUD_SERVICE_ERROR_CODE_UPDATE,ex);
        }
    }

    /**
     *  子类决定如何查询以及返回查询对象
     * @param query
     * @return
     */
    public abstract List<?> query(Q query);

    /**
     * 子类决定如何做增删改过程中 dto到entity对象转化
     * @param dto
     * @return
     */
    protected abstract T doDTOTransf2Entity(D dto);

    /**
     * 子类决定如何做增删改过程中 entity到dto对象转化
     * @param t
     * @return
     */
    protected abstract D doEntityTransf2DTO(T t);
}
