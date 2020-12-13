package boss.xtrain.core.data.convention.base.dao;

import java.util.List;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08
 * @since
 */
public interface CommonQuery<T,Q> {
    /**
     * @param: 组合的查询条件
     * @return: 满足条件的用户数据集合
     * @see
     * @since
     */
    List<T> queryByCondition(Q query);
}