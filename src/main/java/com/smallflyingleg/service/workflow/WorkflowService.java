package com.smallflyingleg.service.workflow;

import com.baomidou.mybatisplus.plugins.Page;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 工作流 服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
public interface WorkflowService extends IService<Workflow> {

    public int check(Workflow workflow, Long owner, Long operator, Integer dateTypeId, Integer dataId, String opinion);

    public int reject(Workflow workflow, Long owner, Long operator, Integer dateTypeId, Integer dataId, String opinion);

    public Page<Workflow> getPage(Integer siteId, int pageNo, int pageSize);

    public List<Workflow> getList(Integer siteId, Boolean disabled);

    public Workflow findById(Integer id);

    public Boolean save(Workflow bean, Integer[] roleIds, Integer[] countersigns);

    public Boolean update(Workflow bean, Integer[] roleIds, Integer[] countersigns);

    public Workflow deleteById(Integer id);

    public Workflow[] deleteByIds(Integer[] ids);

    public void updatePriority(Integer[] ids, Integer[] priorities);

}
