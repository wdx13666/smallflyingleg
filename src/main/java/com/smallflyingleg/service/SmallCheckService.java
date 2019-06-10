package com.smallflyingleg.service;

import com.smallflyingleg.pojo.Small;
import com.smallflyingleg.pojo.SmallCheck;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * CMS内容审核信息表 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
public interface SmallCheckService extends IService<SmallCheck> {

    public SmallCheck save(SmallCheck check, Small small);

}
