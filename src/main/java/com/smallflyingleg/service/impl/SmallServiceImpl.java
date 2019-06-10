package com.smallflyingleg.service.impl;

import com.smallflyingleg.mapper.WorkflowMapper;
import com.smallflyingleg.pojo.Small;
import com.smallflyingleg.mapper.SmallMapper;
import com.smallflyingleg.pojo.SmallCheck;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.smallflyingleg.service.SmallCheckService;
import com.smallflyingleg.service.SmallService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smallflyingleg.service.workflow.WorkflowService;
import com.smallflyingleg.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
@Service
public class SmallServiceImpl extends ServiceImpl<SmallMapper, Small> implements SmallService {

    @Autowired
    private SmallMapper smallMapper;
    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private SmallCheckService smallCheckService;

    @Transactional
    @Override
    public Boolean save(Small small, Long userId) {
        small.setUserId(userId);
        small.setWorkflowId(42);
        Byte userStep = 0;
      /*  if (forMember) {
            // 会员的审核级别按0处理
            userStep = 0;
        } else {
            CmsSite site = bean.getSite();
            userStep = user.getCheckStep(site.getId());
        }*/
        Workflow workflow = workflowService.findById(42);
        // 流程处理
         /*   workflow = small;
            if (workflow != null) {
                bean.setStatus(ContentCheck.CHECKING);
            } else {
                bean.setStatus(ContentCheck.CHECKED);
            }
*/
        smallMapper.insert(small);
        SmallCheck check = new SmallCheck();
        check.setCheckStep(userStep);
        if (workflow != null) {
            int step = workflowService.check(workflow, userId, userId, Constants.SMALL_TYPE, small.getId(), null);
            if (step >= 0) {
                small.setStatus(Constants.CHECKING);
                check.setCheckStep((byte) step);
            } else {
                small.setStatus(Constants.CHECKED);
            }
        }
        smallMapper.updateById(small);
        smallCheckService.save(check, small);
        return true;

    }
}
