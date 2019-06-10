package com.smallflyingleg.service.impl.workflow;

import com.smallflyingleg.pojo.workflow.WorkflowNode;
import com.smallflyingleg.mapper.WorkflowNodeMapper;
import com.smallflyingleg.service.workflow.WorkflowNodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工作流节点 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Service
public class WorkflowNodeServiceImpl extends ServiceImpl<WorkflowNodeMapper, WorkflowNode> implements WorkflowNodeService {

}
