package com.smallflyingleg.pojo.workflow;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 工作流日志记录
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@Data
@TableName("sf_workflow_record")
public class WorkflowRecord extends Model<WorkflowRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;
    /**
     * 操作人
     */
    private Long userId;
    /**
     * 站点
     */
    private Integer siteId;
    /**
     * 轨迹
     */
    private Integer eventId;
    /**
     * 创建时间
     */
    private Date recordTime;
    /**
     * 意见
     */
    private String opinion;
    /**
     * 类型(1:通过  2退回 3保持)
     */
    private Integer type;




    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}
