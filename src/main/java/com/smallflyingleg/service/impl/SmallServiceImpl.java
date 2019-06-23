package com.smallflyingleg.service.impl;

import com.smallflyingleg.mapper.SmallCheckMapper;
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

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
    private WorkflowService workflowService;
    @Autowired
    private SmallCheckService smallCheckService;
    @Autowired
    private SmallCheckMapper smallCheckMapper;

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
        baseMapper.insert(small);
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
        baseMapper.updateById(small);
        smallCheckService.save(check, small);
        return true;

    }

    @Override
    public Small[] check(Integer[] ids, Long userId, String opinion) {
        Small[] beans = new Small[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = check(ids[i], userId, opinion);
        }
        return beans;
    }

    @Transactional
    public Small check(Integer id, Long userId, String opinion) {
        Small small = baseMapper.selectById(id);
        SmallCheck check = smallCheckMapper.selectById(id);
        Workflow workflow = workflowService.findById(small.getWorkflowId());
        // 终审不能审核
        if (small.getStatus().equals(Constants.CHECKED)) {
            return small;
        }
        int workflowstep = workflowService.check(workflow, small.getUserId(), userId, Constants.SMALL_TYPE, small.getId(),
                opinion);
        if (workflowstep == -1) {
            small.setStatus(Constants.CHECKED);
            // 终审，清除退回意见
            check.setCheckOpinion(null);
            check.setRejected(false);
            check.setCheckStep((byte) workflowstep);
            // 终审，设置审核者
            check.setReviewer(userId);
            check.setCheckDate(Calendar.getInstance().getTime());
        } else if (workflowstep > 0) {
            small.setStatus(Constants.CHECKING);
            // 终审，清除退回意见
            check.setCheckOpinion(null);
            check.setRejected(false);
            check.setCheckStep((byte) workflowstep);
        }
        baseMapper.updateById(small);
        smallCheckMapper.updateById(check);
        return small;
    }

    @Override
    public Small[] reject(Integer[] ids, Long userId, String opinion) {
        Small[] beans = new Small[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = reject(ids[i], userId, opinion);
        }
        return beans;
    }


    @Transactional
    public Small reject(Integer id, Long userId, String opinion) {
        Small small = baseMapper.selectById(id);
        SmallCheck check = smallCheckMapper.selectById(id);
        Workflow workflow = workflowService.findById(small.getWorkflowId());

        if (small.getStatus().equals(Constants.CHECKING) || small.getStatus().equals(Constants.CHECKED)) {
            int workflowstep = workflowService.reject(workflow, small.getUserId(),userId , Constants.SMALL_TYPE,
                    small.getId(), opinion);
            // 退回
            if (workflowstep == -1) {
                small.setStatus(Constants.REJECT);
                check.setCheckStep((byte) workflowstep);
                check.setCheckOpinion(opinion);
                check.setRejected(true);
            } else if (workflowstep > 0) {
                small.setStatus(Constants.CHECKING);
                check.setCheckStep((byte) workflowstep);
                check.setCheckOpinion(opinion);
                check.setRejected(true);
            }
        }
        baseMapper.updateById(small);
        smallCheckMapper.updateById(check);
        return small;
    }

}
