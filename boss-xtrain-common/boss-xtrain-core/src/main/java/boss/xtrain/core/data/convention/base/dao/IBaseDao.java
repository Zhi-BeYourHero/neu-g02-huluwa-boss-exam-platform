package boss.xtrain.core.data.convention.base.dao;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08
 * @since
 */
public interface IBaseDao<T,Q> extends CommonQuery<T,Q> {

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    int save(T entity);

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    int delete(T entity);

    /**
     * @param:
     * @return:
     * @see
     * @since
     */
    int update(T entity);
}

