package com.smallflyingleg.service.workflow;

import com.smallflyingleg.pojo.Site;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowRecord;
import com.baomidou.mybatisplus.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 工作流日志记录 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowRecordService extends IService<WorkflowRecord> {

    public List<WorkflowRecord> getList(Integer eventId, Integer userId, List<Integer> ids);

    public List<WorkflowRecord> getList(Integer eventId, Integer userId);

    public List<WorkflowRecord> getList(Integer eventId, Integer userId,Integer type);

    public WorkflowRecord save(Site site, WorkflowEvent event, Long user, String opinion, Date recordTime, Integer type);

    public void deleteByEventId(Integer id);

}
