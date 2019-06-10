package com.smallflyingleg.pojo.workflow;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.smallflyingleg.pojo.SysRole;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 工作流节点
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Data
@TableName("sf_workflow_node")
public class WorkflowNode extends Model<WorkflowNode> {

    private static final long serialVersionUID = 1L;

    /**
     * 工作流
     */
    private Integer id;

    private Integer workflowId;
    /**
     * 角色
     */
    private Integer roleId;

    private SysRole sysRole;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 1 会签 0普通流转
     */
    @TableField(value = "is_countersign")
    private Boolean countersign;



    @Override
    protected Serializable pkVal() {
        return this.workflowId;
    }


}
