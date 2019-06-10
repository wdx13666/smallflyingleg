package com.smallflyingleg.service.impl.workflow;

import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowEventUser;
import com.smallflyingleg.mapper.WorkflowEventUserMapper;
import com.smallflyingleg.service.workflow.WorkflowEventUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 工作流轨迹用户表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Service
@Transactional
public class WorkflowEventUserServiceImpl extends ServiceImpl<WorkflowEventUserMapper, WorkflowEventUser> implements WorkflowEventUserService {

    @Override
    public Set<WorkflowEventUser> save(WorkflowEvent event, Set<SysUser> users) {
        Set<WorkflowEventUser> eventUsers = new HashSet<>();
//        event.setUsers(eventUsers);
        if (users != null) {
            WorkflowEventUser eventUser;
            for (SysUser user : users) {
                eventUser = new WorkflowEventUser(event.getEventId(), user.getUserId());
                baseMapper.insert(eventUser);
                eventUsers.add(eventUser);
            }
        }
        return eventUsers;
    }

    @Override
    public Set<WorkflowEventUser> update(WorkflowEvent event, Set<SysUser> users) {
        deleteByEvent(event.getEventId());
        return save(event, users);
    }

    @Override
    public void deleteByEvent(Integer eventId) {
        baseMapper.deleteById(eventId);
    }
}
