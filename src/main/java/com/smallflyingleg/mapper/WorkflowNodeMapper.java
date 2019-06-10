package com.smallflyingleg.mapper;

import com.smallflyingleg.pojo.workflow.WorkflowNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 工作流节点 Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowNodeMapper extends BaseMapper<WorkflowNode> {
    public List<WorkflowNode> selectByWorkflowId(Integer id);
}
