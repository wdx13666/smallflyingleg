package com.smallflyingleg.service;

import com.smallflyingleg.pojo.Small;
import com.baomidou.mybatisplus.service.IService;
import com.smallflyingleg.pojo.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
public interface SmallService extends IService<Small> {

    public Boolean save(Small small, Long userId);

    public Small[] check(Integer[] ids, Long userId, String opinion);

    /**
     * 退回
     * @param ids
     * @param userId  (审核用户id)
     * @param opinion (审核意见)
     * @return
     */
    public Small[] reject(Integer[] ids, Long userId, String opinion);

}
