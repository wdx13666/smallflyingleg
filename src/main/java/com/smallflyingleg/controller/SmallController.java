package com.smallflyingleg.controller;


import com.smallflyingleg.pojo.Small;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowRecord;
import com.smallflyingleg.service.SmallService;
import com.smallflyingleg.service.SysUserService;
import com.smallflyingleg.service.workflow.WorkflowEventService;
import com.smallflyingleg.service.workflow.WorkflowRecordService;
import com.smallflyingleg.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  工作流
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/small")
public class SmallController {

    @Autowired
    private SmallService smallService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private WorkflowEventService workflowEventService;
    @Autowired
    private WorkflowRecordService workflowRecordService;

    /**
     * 添加一条记录  关联工作流
     * @param small
     * @param userId
     * @return
     */
    @PostMapping
    public Boolean saveSmall(Small small,Long userId){
        Boolean save = smallService.save(small, userId);
        return save;
    }

    /**
     * 审核
     * @param ids
     * @param userId
     * @param opinion
     * @return
     */
    @PostMapping("/check")
    public Small[] check(Integer[] ids, Long userId, String opinion) {
        Small[] beans = smallService.check(ids, userId, opinion);
        return beans;
    }

    /**
     * 退回
     * @param ids
     * @param userId
     * @param opinion
     * @return
     */
    @PostMapping("/reject")
    public Small[] reject(Integer[] ids, Long userId, String opinion) {
        Small[] beans = smallService.reject(ids, userId, opinion);
        return beans;
    }

    /**
     * 审核记录
     * @param id    (small --> id)
     * @return
     */
    @GetMapping(value = "/records")
    public List<WorkflowRecord> checkRecords(Integer id) {
        WorkflowEvent workflowEvent = workflowEventService.find(Constants.SMALL_TYPE, id);
        List<WorkflowRecord> records = new ArrayList<WorkflowRecord>();
        if (workflowEvent != null) {
            records = workflowRecordService.getList(workflowEvent.getEventId(), null);
        }
        return records;
    }


}

