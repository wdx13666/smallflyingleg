package com.smallflyingleg.controller.workflow;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.smallflyingleg.pojo.PageResult;
import com.smallflyingleg.pojo.workflow.Workflow;
import com.smallflyingleg.service.workflow.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>
 * 工作流 前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-06-03
 */
@RestController
@RequestMapping("/workflow")
public class WorkflowController {



    @Autowired
    private WorkflowService workflowService;

    @GetMapping
    public PageResult<Workflow> list(Integer pageNo, HttpServletRequest request, ModelMap model) {
        Page<Workflow> pagination = workflowService.getPage(null, 1, 10);
        return new PageResult<>(pagination.getTotal(),pagination.getRecords());
    }

    /**
     * 新增工作流
     * @param bean
     * @param roleIds
     * @param countersigns
     * @param request
     * @param model
     * @return
     */
    @PostMapping
    public Boolean save(Workflow bean, Integer[] roleIds, Integer[] countersigns, HttpServletRequest request, ModelMap model) {
        //会签 流转 状态按顺序存到集合
        countersigns = getCountersignsParam(request);
        Boolean save = workflowService.save(bean, roleIds, countersigns);
        return save;
    }


    @GetMapping("{id}")
    public Workflow edit(@PathVariable("id") Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
        Workflow workflow = workflowService.findById(id);
        return workflow;
    }


    /**
     * 修改工作流
     * @param bean
     * @param roleIds
     * @param countersigns
     * @param pageNo
     * @param request
     * @param model
     * @return
     */
    @PutMapping
    public Boolean update(Workflow bean, Integer[] roleIds, Integer[] countersigns, Integer pageNo,
                         HttpServletRequest request, ModelMap model) {
        //会签 流转 状态按顺序存到集合
        countersigns = getCountersignsParam(request);
        Boolean update = workflowService.update(bean, roleIds, countersigns);
        return update;
    }



    /**
     *  根据主键删除工作流
     * @param ids
     * @param pageNo
     * @param request
     * @param model
     * @return
     */
    @DeleteMapping("{ids}")
    public Boolean delete(@PathVariable("ids") Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
        Workflow[] beans = workflowService.deleteByIds(ids);
        return true;
    }

    /**
     * 修改排序
     * @param wids
     * @param priority
     * @param queryCtgId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/workflow/o_priority.do")
    public Boolean priority(Integer[] wids, Integer[] priority, Integer queryCtgId, HttpServletRequest request, ModelMap model) {
        workflowService.updatePriority(wids, priority);
        return true;
    }

    /**
     * 会签 流转 状态按顺序存到集合
     * @param request
     * @return
     */
    private Integer[] getCountersignsParam(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();
        List<Integer> countersigns = new ArrayList<Integer>();
        String paramName;
        while (paramNames.hasMoreElements()) {
            paramName = (String) paramNames.nextElement();
            if (paramName.startsWith("countersign")) {
                countersigns.add(Integer.parseInt(request.getParameter(paramName)));
            }
        }
//		Collections.reverse(countersigns);
        Integer[] countersignsArray = new Integer[countersigns.size()];
        return countersigns.toArray(countersignsArray);
    }

}

