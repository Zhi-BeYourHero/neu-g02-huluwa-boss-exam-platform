package boss.xtrain.core.data.convention.base.controller;

import boss.xtrain.core.data.convention.base.service.BaseCURDService;
import boss.xtrain.core.data.convention.common.CommonResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author WenZhi Luo
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 基础的增删改查，正常情况下都成功
 * 以UserController为例<D,T,Q,M,V>，对应<UserDTO,User,UserQuery,UserMapper,UserVO>
 * @create 2020-07-08
 * @since
 */
@RestController
public abstract class BaseCRUDController<D,T,Q,M,V> extends AbstractController {
    protected BaseCURDService<D,T,Q,M> service;
    public CommonResponse<Integer> create(@RequestBody @Valid D dtoParam){
        Integer ret=this.service.save(dtoParam);
        return buildCommonResponseSuccess(ret);
    }
    public  CommonResponse<V> query(@RequestBody @Valid Q queryParam){
        Object returnDto= service.query(queryParam);
        V vo=doObjectTransf(returnDto);
        return  buildCommonResponseSuccess(vo);
    }

    /**
     * @param: service 层返回的dto对象,将dto转为vo
     * @return: dto转化为controller 层需要的VO对象
     * @see
     * @since
     */
    protected  abstract V doObjectTransf(Object returnDto);

    public  CommonResponse<Integer> update(@RequestBody @Valid D dtoParam){
        Integer ret=this.service.update(dtoParam);
        return buildCommonResponseSuccess(ret);
    }

    public CommonResponse<Integer> delete(@RequestBody @Valid D dtoParam){
        Integer ret=this.service.update(dtoParam);
        return buildCommonResponseSuccess(ret);
    }
}
