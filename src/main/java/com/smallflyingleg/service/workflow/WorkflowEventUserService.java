package com.smallflyingleg.service.workflow;

import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowEventUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

/**
 * <p>
 * 工作流轨迹用户表 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowEventUserService extends IService<WorkflowEventUser> {

    public Set<WorkflowEventUser> save(WorkflowEvent event, Set<SysUser> users);

    public Set<WorkflowEventUser> update(WorkflowEvent event, Set<SysUser> users);

    public void deleteByEvent(Integer eventId);

}
