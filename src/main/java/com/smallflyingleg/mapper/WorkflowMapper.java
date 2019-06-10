package com.smallflyingleg.mapper;

import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工作流 Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowMapper extends BaseMapper<Workflow> {

    public Workflow findById(Integer id);

//    public int check(@Param("workflow") Workflow workflow, @Param("owner") SysUser owner, @Param("operator") SysUser operator, @Param("dateTypeId") Integer dateTypeId, @Param("dataId") Integer dataId, @Param("opinion") String opinion);

   /* public int reject(Workflow workflow, SysUser owner, SysUser operator, Integer dateTypeId, Integer dataId, String opinion);

    public Page<Workflow> getPage(Integer siteId, int pageNo, int pageSize);

    public List<Workflow> getList(Integer siteId, Boolean disabled);



    public Workflow save(Workflow bean, Integer[] roleIds, Integer[] countersigns);

    public Workflow update(Workflow bean, Integer[] roleIds, Integer[] countersigns);

    public Workflow deleteById(Integer id);

    public Workflow[] deleteByIds(Integer[] ids);

    public void updatePriority(Integer[] ids, Integer[] priorities);*/


}
