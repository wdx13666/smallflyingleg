package com.smallflyingleg.pojo.workflow;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 工作流轨迹用户表
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Data
@TableName("sf_workflow_event_user")
public class WorkflowEventUser extends Model<WorkflowEventUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "event_user_id", type = IdType.AUTO)
    private Integer eventUserId;
    /**
     * 工作流转id
     */
    private Integer eventId;
    /**
     * 流转用户
     */
    private Long userId;

    public WorkflowEventUser(Integer eventId, Long userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.eventUserId;
    }


}
