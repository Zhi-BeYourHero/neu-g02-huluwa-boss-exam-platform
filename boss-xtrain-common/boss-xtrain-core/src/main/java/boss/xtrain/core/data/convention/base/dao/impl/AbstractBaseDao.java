package boss.xtrain.core.data.convention.base.dao.impl;

import boss.xtrain.core.data.convention.base.dao.IBaseDao;
import boss.xtrain.core.data.convention.base.dao.mapper.CommonMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 该类适配底层tk.mybatis的mapper的基本的增删除该查方法
 * <br> 统一常规的操作接口是的之类不再关注基本的方法同时隔离了底层tk.mybatis
 * <br> 子类dao需要其他的dao方法需要另外实现userDao接口
 * T是Entity实体，Q是Query查询条件，M是mapper
 * @create 2020-07-08
 * @since
 *
 */
public abstract class AbstractBaseDao<T,M extends CommonMapper<T>,Q> implements IBaseDao<T,Q> {

    /**
     *  注入框架隔离的Mappper,后面的数据操作依赖此mapper
     */
    protected M myMapper;

    @Override
    public int save(T entity) {
        return myMapper.insert(entity);
    }

    @Override
    public int delete(T entity) {
        return myMapper.delete(entity);
    }

    @Override
    public int update(T entity) {
        return myMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<T> queryByCondition(Q query) {
        return new ArrayList<>();
    }
}