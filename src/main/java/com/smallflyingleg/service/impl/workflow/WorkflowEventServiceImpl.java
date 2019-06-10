package com.smallflyingleg.service.impl.workflow;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.smallflyingleg.mapper.WorkflowEventUserMapper;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.mapper.WorkflowEventMapper;
import com.smallflyingleg.service.workflow.WorkflowEventService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 工作流轨迹 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Transactional
@Service
public class WorkflowEventServiceImpl extends ServiceImpl<WorkflowEventMapper, WorkflowEvent> implements WorkflowEventService {

    @Autowired
    private WorkflowEventService workflowEventService;
    @Autowired
    private WorkflowEventUserMapper workflowEventUserMapper;

    @Transactional(readOnly = true)
    public List<WorkflowEvent> getListByWorkFlowId(Integer workflowId) {
        return baseMapper.selectList(new EntityWrapper<WorkflowEvent>().eq("workflow_id",workflowId));
    }

    @Transactional(readOnly = true)
    public List<WorkflowEvent> getListByUserId(Integer userId) {
        return baseMapper.selectList(new EntityWrapper<WorkflowEvent>().eq("user_id",userId));
    }

    @Transactional(readOnly = true)
    public WorkflowEvent findById(Integer id) {
        WorkflowEvent entity = baseMapper.selectById(id);
        return entity;
    }

    @Transactional(readOnly = true)
    public WorkflowEvent find(Integer dataTypeId, Integer dataId) {
        List<WorkflowEvent> events = baseMapper.selectList(new EntityWrapper<WorkflowEvent>().eq("date_type",dataTypeId).eq("date_id",dataId));
        if (events != null && events.size() > 0) {
            return events.get(0);
        } else {
            return null;
        }
    }

    public WorkflowEvent end(Integer eventId) {
        WorkflowEvent event = findById(eventId);
        event.setEndTime(Calendar.getInstance().getTime());
        event.setFinish(true);
        event.setUserId(null);
        event.setNextStep(-1);
        return event;
    }

    public WorkflowEvent save(Workflow workflow, Long initiator, Set<SysUser> nextUsers, Integer dateTypeId,
                                 Integer dateId, Integer step, Boolean hasFinish) {
        WorkflowEvent bean = new WorkflowEvent();
        bean.setWorkflowId(workflow.getWorkflowId());
        bean.setUserId(initiator);
        bean.setDateType(dateTypeId);
        bean.setDateId(dateId);
        bean.setNextStep(step);
        bean.setFinish(hasFinish);
        bean.init();
        if (hasFinish != null && hasFinish) {
            bean.setEndTime(Calendar.getInstance().getTime());
        }
        Integer insert = baseMapper.insert(bean);
        return bean;
    }

    public Integer deleteById(Integer id) {
        // 清除待审用户列表
        workflowEventUserMapper.deleteById(id);
        Integer bean = baseMapper.deleteById(id);
        return bean;
    }

    public Integer [] deleteByIds(Integer[] ids) {
        Integer[] beans = new Integer[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(ids[i]);
        }
        return beans;
    }

    public void deleteByDate(Integer dataTypeId, Integer dataId) {
        WorkflowEvent event = find(dataTypeId, dataId);
        if (event != null) {
            deleteById(event.getDateId());
        }
    }
}
