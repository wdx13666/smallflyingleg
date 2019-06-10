package com.smallflyingleg.service.impl.workflow;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.smallflyingleg.mapper.SysRoleMapper;
import com.smallflyingleg.mapper.WorkflowEventMapper;
import com.smallflyingleg.mapper.WorkflowMapper;
import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowNode;
import com.smallflyingleg.service.SysUserService;
import com.smallflyingleg.service.workflow.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 工作流 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Service
public class WorkflowServiceImpl extends ServiceImpl<WorkflowMapper, Workflow> implements WorkflowService {

    @Autowired
    private WorkflowEventMapper workflowEventMapper;

    @Autowired
    private WorkflowEventService workflowEventService;

    @Autowired
    private WorkflowRecordService workflowRecordService;

    @Autowired
    private WorkflowEventUserService workflowEventUserService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private WorkflowNodeService workflowNodeService;

    @Autowired
    private SysUserService sysUserService;

    public int check(Workflow workflow, Long owner, Long operator, Integer dateTypeId, Integer dataId,
                     String opinion) {

        WorkflowEvent event = workflowEventService.find(dateTypeId, dataId);
        SysUser sysUser = sysUserService.selectUserAndRoleById(operator);
        if (workflow == null) {
            // 没有工作流，存在流程轨迹 审核通过。
            return checkPass(operator, opinion, event);
        }
        // 流程轨迹存在并且已经结束（文章终审结束不能在审核）
      /*  if (event != null && event.getIsFinish()) {
            if (dateTypeId == Content.DATA_CONTENT) {
                Content c = contentMng.findById(dataId);
                if (c.getStatus().equals(Content.ContentStatus.checked)) {
                    return 0;
                }
            }
        }*/
        // 流程轨迹存在并且没有结束
        if (event != null && !event.getFinish()) {
            workflow = event.getWorkflow();
        }
        List<WorkflowNode> nodes = workflow.getWorkflowNodes();
        int size = nodes.size();
        if (size <= 0) {
            // 工作流没有定义节点，审核通过。
            return checkPass(operator, opinion, event);
        }
        // 操作人在工作流节点中的最后位置
        int step;
        WorkflowNode lastNode = nodes.get(nodes.size() - 1);
        for (step = size; step > 0; step--) {
            WorkflowNode node = nodes.get(step - 1);
            SysRole nodeRole = node.getSysRole();
            Set<SysRole> roles = new HashSet<SysRole>();
            roles.add(nodeRole);
            if (CollectionUtils.containsAny(roles, sysUser.getSysRoles())) {
                lastNode = node;
                break;
            }
        }
        // 是否在工作流节点中
        // 流程轨迹存在且没有结束且操作者步骤小于流程步骤，则不能操作，返回0（没有在工作流中）
        if (event != null && !event.getFinish() && step < event.getNextStep()) {
            return 0;
        }
        int nextStep;
        Set<SysUser> nextUsers;
        Set<SysUser> tempUsers;
        Date endDate;
        Boolean hasFinish;
        if (step == size) {
            if (lastNode.getCountersign()) {
                if (event == null) {
                    nextUsers = nodes.get(step - 1).getSysRole().getSysUsers();
                    tempUsers = new HashSet<SysUser>();
                    tempUsers.addAll(nextUsers);
                    tempUsers.remove(operator);
                    nextUsers = tempUsers;
                    nextStep = step;
                    endDate = null;
                    hasFinish = false;
                } else {
                    // 会签模式 全部同意
                    if (event.getPassNum() >= lastNode.getSysRole().getSysUsers().size() - 1) {
                        nextUsers = null;
                        nextStep = -1;
                        endDate = Calendar.getInstance().getTime();
                        hasFinish = true;
                        event.setPassNum(0);
                    } else {
                        // 会签模式 通过
                        nextUsers = nodes.get(step - 1).getSysRole().getSysUsers();
                        tempUsers = new HashSet<SysUser>();
                        tempUsers.addAll(nextUsers);
                        tempUsers.remove(operator);
                        nextUsers = tempUsers;
                        event.setPassNum(event.getPassNum() + 1);
                        nextStep = step;
                        endDate = null;
                        hasFinish = false;
                    }
                }
            } else {
                // 终审人员，审核结束。
                nextUsers = null;
                nextStep = -1;
                endDate = Calendar.getInstance().getTime();
                hasFinish = true;
            }
        } else {
            if (lastNode.getCountersign()) {
                if (event == null) {
                    nextUsers = nodes.get(step - 1).getSysRole().getSysUsers();
                    tempUsers = new HashSet<SysUser>();
                    tempUsers.addAll(nextUsers);
                    tempUsers.remove(operator);
                    nextUsers = tempUsers;
                    nextStep = step;
                    endDate = null;
                    hasFinish = false;
                } else {
                    // 会签模式 全部同意 之后流转下个节点
                    if (event.getPassNum() >= lastNode.getSysRole().getSysUsers().size() - 1) {
                        nextUsers = nodes.get(step).getSysRole().getSysUsers();
                        nextStep = step + 1;
                        endDate = null;
                        hasFinish = false;
                        // 流转下个节点处理当前节点处理用户数
                        event.setPassNum(0);
                    } else {
                        // 会签模式 通过
                        nextUsers = nodes.get(step - 1).getSysRole().getSysUsers();
                        tempUsers = new HashSet<SysUser>();
                        tempUsers.addAll(nextUsers);
                        tempUsers.remove(operator);
                        nextUsers = tempUsers;
                        event.setPassNum(event.getPassNum() + 1);
                        nextStep = step;
                        endDate = null;
                        hasFinish = false;
                    }
                }
            } else {
                // 普通流转 获得下一步审核人员
                nextUsers = nodes.get(step).getSysRole().getSysUsers();
                nextStep = step + 1;
                endDate = null;
                hasFinish = false;
            }
        }
        if (event != null) {
            event.setWorkflowId(workflow.getWorkflowId());
            event.setNextStep(nextStep);
            event.setEndTime(endDate);
            event.setFinish(hasFinish);
            // 处理工作流下个节点用户
            workflowEventUserService.update(event, nextUsers);
        } else {
            // 保存工作流轨迹
//            event = workflowEventService.save(workflow, owner, nextUsers, dateTypeId, dataId, nextStep, hasFinish);
             event = workflowEventService.save(workflow, owner, nextUsers, dateTypeId, dataId, nextStep, hasFinish);
            // 保存工作流下个节点用户
            workflowEventUserService.save(event, nextUsers);
        }
        // 审核记录
        workflowRecordService.save(null, event, operator, opinion,
                Calendar.getInstance().getTime(), Workflow.PASS);
        return nextStep;
    }




    public int reject(Workflow workflow, Long owner, Long operator, Integer dateTypeId, Integer dataId,
                      String opinion) {
        WorkflowEvent event = workflowEventService.find(dateTypeId, dataId);
        SysUser sysUser = sysUserService.selectById(operator);

        if (workflow == null) {
            // 没有工作流，退回则直接打回。
            if (event != null) {
                workflowRecordService.save(event.getWorkflow().getSite(), event, operator, opinion,
                        Calendar.getInstance().getTime(), Workflow.BACK);
                workflowEventService.end(event.getEventId());
            }
            return -1;
        }
        // 流程轨迹存在且没有结束
        if (event != null && !event.getFinish()) {
            workflow = event.getWorkflow();
        }
        List<WorkflowNode> nodes = workflow.getWorkflowNodes();
        int size = nodes.size();
        if (size <= 0) {
            // 工作流没有定义节点
            if (event != null) {
                workflowRecordService.save(event.getWorkflow().getSite(), event, operator, opinion,
                        Calendar.getInstance().getTime(), Workflow.BACK);
                workflowEventService.end(event.getEventId());
            }
            return -1;
        }
        // 操作人在工作流节点中的首要位置
        int step;
        for (step = 0; step < size; step++) {
            WorkflowNode node = nodes.get(step);
            SysRole nodeRole = node.getSysRole();
            Set<SysRole> roles = new HashSet<SysRole>();
            roles.add(nodeRole);
            if (CollectionUtils.containsAny(roles, sysUser.getSysRoles())) {
                break;
            }
        }
        // 不在审核流程中=0 在审核流程中的步骤step+1
        if (step == size) {
            step = 0;
        } else {
            step++;
        }
        // 流程轨迹存在且未结束且非当前步骤人员
        if (event != null && !event.getFinish()) {
            if (step != event.getNextStep()) {
                return 0;
            }
        } else if ((event == null || event.getFinish()) && step != size) {
            // (流程轨迹不存在或者(流程轨迹已结束))&&非终审人员
            return 0;
        }
        boolean ownerRejected = false;
        if (step > 1) {
            for (int i = step - 2; i < size; i++) {
                WorkflowNode node = nodes.get(i);
                if (node.getSysRole().getSysUsers().contains(owner)) {
                    ownerRejected = true;
                    break;
                }
            }
        }
        int nextStep;
        Set<SysUser> nextUsers;
        Date endDate;
        Boolean hasFinish;
        // 第一个审核人员，退稿。
        if (step == 1 || ownerRejected) {
            nextUsers = null;
            nextStep = -1;
            endDate = Calendar.getInstance().getTime();
            hasFinish = true;
        } else {
            // 获得上个节点审核人员。
            nextUsers = nodes.get(step - 2).getSysRole().getSysUsers();
            nextStep = step - 1;
            endDate = null;
            hasFinish = false;
        }
        if (event != null) {
            event.setWorkflow(workflow);
            event.setNextStep(nextStep);
            event.setEndTime(endDate);
            event.setFinish(hasFinish);
            // 工作流下个节点用户数据更新
            workflowEventUserService.update(event, nextUsers);
        } else {
            // 保存工作流轨迹
            workflowEventService.save(workflow, owner, nextUsers, dateTypeId, dataId, step, hasFinish);
            // 保存工作流下个节点用户
            workflowEventUserService.save(event, nextUsers);
        }
        // 审核记录
        workflowRecordService.save(event.getWorkflow().getSite(), event, operator, opinion,
                Calendar.getInstance().getTime(), Workflow.BACK);
        return nextStep;

    }

    @Transactional(readOnly = true)
    public Page getPage(Integer siteId, int pageNo, int pageSize) {
        Page<Workflow> page = workflowService.selectPage(new Page<>(pageNo,pageSize),new EntityWrapper<Workflow>().orderBy("priority",false));
        return page;
    }

    @Transactional(readOnly = true)
    public List<Workflow> getList(Integer siteId, Boolean disabled) {
        List<Workflow> workflows = workflowService.getList(siteId, disabled);
        return workflows;
    }


    @Transactional(readOnly = true)
    public Workflow findById(Integer id) {
        Workflow entity = baseMapper.findById(id);
        return entity;
    }

    @Transactional
    public Boolean save(Workflow bean, Integer[] roleIds, Integer[] countersigns) {
        workflowService.insert(bean);
        // 保存节点
        saveNodes(bean, roleIds, countersigns);
        return true;
    }

    /**
     * 保存节点
     * @param bean
     * @param roleIds
     * @param countersigns
     * @return
     */
    private Workflow saveNodes(Workflow bean, Integer[] roleIds, Integer[] countersigns) {
        if (roleIds != null && roleIds.length > 0) {
            for (int i = 0, len = roleIds.length; i < len; i++) {
                if (roleIds[i] != null) {
                    boolean countersign = false;
                    if (countersigns != null && countersigns[i] != null && countersigns[i].equals(1)) {
                        countersign = true;
                    }
                    WorkflowNode node = new WorkflowNode();
                    node.setRoleId(roleIds[i]);
                    node.setCountersign(countersign);
                    node.setPriority(i);
                    node.setWorkflowId(bean.getWorkflowId());
                    workflowNodeService.insert(node);
                }
            }
        }
        return bean;
    }

    public void addToNodes(SysRole role, boolean countersign) {

    }

    @Transactional
    public Boolean update(Workflow bean, Integer[] roleIds, Integer[] countersigns) {
        workflowService.updateById(bean);
        //删除nodes节点
        workflowNodeService.deleteById(bean.getWorkflowId());
        // 保存节点
        saveNodes(bean, roleIds, countersigns);
        return true;
    }


    @Transactional
    public Workflow deleteById(Integer id) {
        Workflow bean = findById(id);
        // 清除流程轨迹
        List<WorkflowEvent> events = workflowEventService.getListByWorkFlowId(id);
        for (WorkflowEvent event : events) {
            workflowEventService.deleteById(event.getEventId());
        }
        // 清空栏目设置流程
//        channelMng.initWorkFlow(id);
        baseMapper.deleteById(id);
        //删除关联的节点
        workflowNodeService.deleteById(id);
        return bean;
    }

    public Workflow[] deleteByIds(Integer[] ids) {
        Workflow[] beans = new Workflow[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(ids[i]);
        }
        return beans;
    }

    public void updatePriority(Integer[] ids, Integer[] priorities) {
        if (ids == null || priorities == null || ids.length <= 0 || ids.length != priorities.length) {
            return;
        }
        Workflow workflow;
        for (int i = 0, len = ids.length; i < len; i++) {
            workflow = findById(ids[i]);
            workflow.setPriority(priorities[i]);
        }
    }

    private int checkPass(Long operator, String opinion, WorkflowEvent event) {
        if (event != null) {
            workflowRecordService.save(event.getWorkflow().getSite(), event, operator, opinion,
                    Calendar.getInstance().getTime(), Workflow.PASS);
            workflowEventService.end(event.getEventId());
        }
        return -1;
    }


}
