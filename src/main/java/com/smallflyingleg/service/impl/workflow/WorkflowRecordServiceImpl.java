package com.smallflyingleg.service.impl.workflow;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.smallflyingleg.pojo.Site;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.smallflyingleg.pojo.workflow.WorkflowRecord;
import com.smallflyingleg.mapper.WorkflowRecordMapper;
import com.smallflyingleg.service.workflow.WorkflowRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 工作流日志记录 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Service
@Transactional
public class WorkflowRecordServiceImpl extends ServiceImpl<WorkflowRecordMapper, WorkflowRecord> implements WorkflowRecordService {

    @Transactional(readOnly = true)
    public List<WorkflowRecord> getList(Integer eventId, Integer userId, List<Integer> ids) {
        return baseMapper.selectList(new EntityWrapper<WorkflowRecord>().eq("event_id",eventId).
                eq("user_id",userId).in("user_id",ids).orderBy("record_time",false).orderBy("record_id",false));
    }

    @Transactional(readOnly = true)
    public List<WorkflowRecord> getList(Integer eventId, Integer userId) {
        return baseMapper.selectList(new EntityWrapper<WorkflowRecord>().eq("event_id",eventId).
                eq("user_id",userId).orderBy("record_time",false).orderBy("record_id",false));
    }

    @Transactional(readOnly = true)
    public List<WorkflowRecord> getList(Integer eventId, Integer userId, Integer type) {
        return baseMapper.selectList(new EntityWrapper<WorkflowRecord>().eq("event_id",eventId).
                eq("user_id",userId).eq("type",type).orderBy("record_time",false).orderBy("record_id",false));
    }


    public WorkflowRecord save(Site site, WorkflowEvent event, Long userId, String opinion, Date recordTime,
                                  Integer type) {
        WorkflowRecord bean = new WorkflowRecord();
        bean.setEventId(event.getEventId());
        bean.setOpinion(opinion);
        bean.setRecordTime(recordTime);
//        bean.setSiteId(site.getSiteId());
        bean.setType(type);
        bean.setUserId(userId);
        baseMapper.insert(bean);
        return bean;
    }


    @Override
    public void deleteByEventId(Integer id) {
        baseMapper.delete(new EntityWrapper<WorkflowRecord>().eq("event_id",id));
    }
}
