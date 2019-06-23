package com.smallflyingleg.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * CMS内容审核信息表
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
@Data
@TableName("sf_small_check")
public class SmallCheck extends Model<SmallCheck> {

    private static final long serialVersionUID = 1L;

    public void init() {
        byte zero = 0;
        if (getCheckStep() == null) {
            setCheckStep(zero);
        }
        if (getRejected() == null) {
            setRejected(false);
        }
    }

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 审核步数
     */
    private Byte checkStep;
    /**
     * 审核意见
     */
    private String checkOpinion;
    /**
     * 是否退回
     */
    @TableField(value = "is_rejected")
    private Boolean rejected;
    /**
     * 终审者
     */
    private Long reviewer;
    /**
     * 终审时间
     */
    private Date checkDate;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
