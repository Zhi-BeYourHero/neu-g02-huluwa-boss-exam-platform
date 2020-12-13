package boss.xtrain.security.service;

import boss.xtrain.core.constant.CacheConstants;
import boss.xtrain.core.constant.SecurityConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 重写原生方法支持redis缓存
 * @create 2020-07-06
 * @since
 */
public class RedisClientDetailsService extends JdbcClientDetailsService {

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
        //根据源码可知默认是获取获取oauth_client_details表的全部字段信息，包括了client_id、client_secret，所以我们这里进行去除
        super.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        //同理，这个是order by client_id，其实我也不知道有用...
        super.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
    }

    /**
     * 从缓存中获取客户端信息
     * @param clientId
     * @return
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId)
    {
        return super.loadClientByClientId(clientId);
    }
}
