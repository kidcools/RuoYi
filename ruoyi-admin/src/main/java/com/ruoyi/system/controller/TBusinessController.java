package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TBusiness;
import com.ruoyi.system.service.ITBusinessService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商务账号管理Controller
 * 
 * @author milestar
 * @date 2021-09-14
 */
@Controller
@RequestMapping("/business/business")
public class TBusinessController extends BaseController
{
    private String prefix = "business/business";

    @Autowired
    private ITBusinessService tBusinessService;

    @RequiresPermissions("business:business:view")
    @GetMapping()
    public String business()
    {
        return prefix + "/business";
    }

    /**
     * 查询商务账号管理列表
     */
    @RequiresPermissions("business:business:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TBusiness tBusiness)
    {
        startPage();
        List<TBusiness> list = tBusinessService.selectTBusinessList(tBusiness);
        return getDataTable(list);
    }

    /**
     * 导出商务账号管理列表
     */
    @RequiresPermissions("business:business:export")
    @Log(title = "商务账号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TBusiness tBusiness)
    {
        List<TBusiness> list = tBusinessService.selectTBusinessList(tBusiness);
        ExcelUtil<TBusiness> util = new ExcelUtil<TBusiness>(TBusiness.class);
        return util.exportExcel(list, "商务账号管理数据");
    }

    /**
     * 新增商务账号管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商务账号管理
     */
    @RequiresPermissions("business:business:add")
    @Log(title = "商务账号管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TBusiness tBusiness)
    {
        return toAjax(tBusinessService.insertTBusiness(tBusiness));
    }

    /**
     * 修改商务账号管理
     */
    @GetMapping("/edit/{businessId}")
    public String edit(@PathVariable("businessId") Long businessId, ModelMap mmap)
    {
        TBusiness tBusiness = tBusinessService.selectTBusinessByBusinessId(businessId);
        mmap.put("tBusiness", tBusiness);
        return prefix + "/edit";
    }

    /**
     * 修改保存商务账号管理
     */
    @RequiresPermissions("business:business:edit")
    @Log(title = "商务账号管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TBusiness tBusiness)
    {
        return toAjax(tBusinessService.updateTBusiness(tBusiness));
    }

    /**
     * 删除商务账号管理
     */
    @RequiresPermissions("business:business:remove")
    @Log(title = "商务账号管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tBusinessService.deleteTBusinessByBusinessIds(ids));
    }
}
