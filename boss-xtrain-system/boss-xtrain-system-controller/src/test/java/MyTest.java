import boss.xtrain.security.domain.SysUser;
import boss.xtrain.security.util.SecurityUtils;
import com.boss.bes.system.dto.TreeDto;
import com.boss.bes.system.service.ResourceService;
import com.boss.bes.system.service.RoleService;
import com.boss.bes.system.service.UserService;
import com.boss.bes.system.SystemSpringApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description
 * @create 2020-07-08
 * @since
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemSpringApplication.class)
@Slf4j
public class MyTest {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ResourceService resourceService;

    @Test
    public void testSelectUserByUserName(){
        SysUser admin = userService.selectUserByUserName("admin");
        log.info("{}",admin);
    }


    @Test
    public void testSelectRolePermissionByUserId(){
        Set<String> strings = roleService.selectRolePermissionByUserId(1L);
        for (String string : strings) {
            log.info("{}",string);
        }
    }

    @Test
    public void testSelectResourcePermsByUserId(){
        Set<String> strings = resourceService.selectResourcePermsByUserId(1L);
        strings.forEach(log::info);
    }

    @Test
    public void encryptPassword(){
        String s = SecurityUtils.encryptPassword("ruoyi:123456");
        System.out.println(s);
    }

    @Test
    public void test(){
        List<TreeDto> treeDtos = resourceService.queryRoutes();
        System.out.println(treeDtos);
    }
}
