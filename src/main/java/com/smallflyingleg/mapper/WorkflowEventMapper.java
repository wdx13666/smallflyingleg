package com.smallflyingleg.mapper;

import com.smallflyingleg.pojo.workflow.WorkflowEvent;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工作流轨迹 Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowEventMapper extends BaseMapper<WorkflowEvent> {

    public List<WorkflowEvent> find(@Param("dateTypeId") Integer dateTypeId, @Param("dataId") Integer dataId);
}
