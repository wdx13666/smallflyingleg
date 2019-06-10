package com.smallflyingleg.pojo.workflow;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Calendar;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.smallflyingleg.pojo.SysRole;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 工作流轨迹
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Data
@TableName("sf_workflow_event")
public class WorkflowEvent extends Model<WorkflowEvent> {

    private static final long serialVersionUID = 1L;


    public void init() {
        if (getStartTime() == null) {
            setStartTime(Calendar.getInstance().getTime());
        }
        if (getDateType() == null) {
            setDateType(0);
        }
        if (getFinish() == null) {
            setFinish(false);
        }
        // 会签第一人建立流程轨迹 通过数为1
        if (getPassNum() == null) {
            setPassNum(0);
        }
    }


    @TableId(value = "event_id", type = IdType.AUTO)
    private Integer eventId;
    /**
     * 工作流
     */
    private Integer workflowId;

    @TableField(exist = false)
    private Workflow workflow;

    @TableField(exist = false)
    private WorkflowNode workflowNode;

    @TableField(exist = false)
    private SysRole sysRole;

    /**
     * 数据标识
     */
    private Integer dateId;
    /**
     * 发起人
     */
    private Long userId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 下个步骤
     */
    private Integer nextStep;
    /**
     * 数据类型(0默认内容)
     */
    private Integer dateType;
    /**
     * 是否结束
     */
    @TableField(value = "is_finish")
    private Boolean finish;
    /**
     * 会签本节点通过人数
     */
    private Integer passNum;



    @Override
    protected Serializable pkVal() {
        return this.eventId;
    }


}
