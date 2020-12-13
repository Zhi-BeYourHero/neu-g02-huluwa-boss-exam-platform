package boss.xtrain.core.data.convention.base.dao.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 使用MySqlMapper主要是自增主键和批量插入
 * @create 2020-07-08
 * @since
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}