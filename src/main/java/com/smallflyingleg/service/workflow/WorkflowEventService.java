package com.smallflyingleg.service.workflow;

import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 工作流轨迹 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowEventService extends IService<WorkflowEvent> {

    public List<WorkflowEvent> getListByWorkFlowId(Integer workflowId);

    public List<WorkflowEvent> getListByUserId(Integer userId);

    public WorkflowEvent findById(Integer id);

    /**
     * 根据
     * @param dataTypeId
     * @param dataId
     * @return
     */
    public WorkflowEvent find(Integer dataTypeId, Integer dataId);

    public WorkflowEvent end(Integer eventId);

    public WorkflowEvent save(Workflow workflow, Long initiator, Set<SysUser> nextUsers, Integer dateTypeId, Integer dateId, Integer step, Boolean hasFinish);

    public Integer deleteById(Integer id);

    public Integer[] deleteByIds(Integer[] ids);

    public void deleteByDate(Integer dataTypeId, Integer dataId);



}
