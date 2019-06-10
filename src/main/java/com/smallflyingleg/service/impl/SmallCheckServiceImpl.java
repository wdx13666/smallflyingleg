package com.smallflyingleg.service.impl;

import com.smallflyingleg.pojo.Small;
import com.smallflyingleg.pojo.SmallCheck;
import com.smallflyingleg.mapper.SmallCheckMapper;
import com.smallflyingleg.service.SmallCheckService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * CMS内容审核信息表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
@Service
public class SmallCheckServiceImpl extends ServiceImpl<SmallCheckMapper, SmallCheck> implements SmallCheckService {

    @Override
    public SmallCheck save(SmallCheck check, Small small) {
        check.setId(small.getId());
        check.init();
        baseMapper.insert(check);
//        content.setContentCheck(check);
        return check;
    }
}
