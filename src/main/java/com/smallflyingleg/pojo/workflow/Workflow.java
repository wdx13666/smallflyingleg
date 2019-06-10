package com.smallflyingleg.pojo.workflow;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.smallflyingleg.pojo.Site;
import com.smallflyingleg.pojo.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 工作流
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Data
@TableName("sf_workflow")
public class Workflow extends Model<Workflow> {

    private static final long serialVersionUID = 1L;



    /**
     * 通过
     */
    public static int PASS = 1;

    /**
     * 不通过
     */
    public static int NOPASS = -1;
    /**
     * 退回
     */
    public static int BACK = 2;
    /**
     * 保持
     */
    public static int KEEP = 3;

    @TableId(value = "workflow_id", type = IdType.AUTO)
    private Integer workflowId;
    /**
     * 站点
     */
    private Integer siteId;

    @TableField(exist = false)
    private Site site;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 是否禁用
     */
    @TableField(value = "is_disabled")
    private Boolean disabled;

    /**
     * 工作流节点
     */
    @TableField(exist = false)
    private List<WorkflowNode> workflowNodes;


    public Boolean getDisabled() {
        if (disabled == null){
            disabled = false;
        }
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
            this.disabled = disabled;
    }

    @Override
    protected Serializable pkVal() {
        return this.workflowId;
    }


}
