package com.ruoyi.system.controller;

import java.util.*;

import com.ruoyi.system.domain.TBusiness;
import com.ruoyi.system.service.ITBusinessService;
import com.ruoyi.utils.PinYinUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.service.ICustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户账号管理Controller
 * 
 * @author milestar
 * @date 2021-09-14
 */
@Controller
@RequestMapping("/customer/customer")
public class CustomerController extends BaseController
{
    private String prefix = "customer/customer";

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ITBusinessService businessService;

    @RequiresPermissions("customer:customer:view")
    @GetMapping()
    public String customer(ModelMap mmap)
    {
        //获得所有商务
        List<TBusiness> opposites = businessService.selectTBusinessList(null);
        mmap.addAttribute("bussinesses",opposites);
        return prefix + "/customer";
    }

    /**
     * 查询客户账号管理列表
     */
    @RequiresPermissions("customer:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        //获得所有商务
        List<TBusiness> opposites = businessService.selectTBusinessList(null);
        //客户商务封装
        list.stream().forEach(item->{
            item.setStatus(item.getExpireTime().getTime()>new Date().getTime()?"正常使用":"已过期");
            opposites.stream().forEach(opposite->{
                //发现opposite
                if(opposite.getBusinessId()==(item.getOpposite())){
                    item.setBussiness(opposite);
                }
            });
            if(item.getBussiness()==null){
                TBusiness business = new TBusiness();
                business.setBusinessId(Long.parseLong(-1+""));
                business.setBusinessName("无归属");
                item.setBussiness(business);
            }
        });

        return getDataTable(list);
    }

    /**
     * 导出客户账号管理列表
     */
    @RequiresPermissions("customer:customer:export")
    @Log(title = "客户账号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "客户账号管理数据");
    }

    /**
     * 新增客户账号管理
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        //获得所有商务
        List<TBusiness> opposites = businessService.selectTBusinessList(null);
        model.addAttribute("opposites",opposites);
        return prefix + "/add";
    }

    /**
     * 新增保存客户账号管理
     */
    @RequiresPermissions("customer:customer:add")
    @Log(title = "客户账号管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Customer customer)
    {

        //TODO 补齐参数
        customer.setCreateTime(new Date());
        Date date = new Date();
        Calendar calendar  =   Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 3);
        date=calendar.getTime();
        //过期时间
        customer.setExpireTime(date);
        //账号
        customer.setUsername(PinYinUtil.getPinyin(customer.getName())+new Random().nextInt(9999));
        //设置密码
        customer.setPassword(PinYinUtil.getPinyin(customer.getName())+new Random().nextInt(9999));
        try {
            customerService.insertCustomer(customer);
            return AjaxResult.success("恭喜您添加成功\n" +
                    "账号:"+customer.getUsername()+"\n" +
                    "密码:"+customer.getPassword());
        }catch(Exception e){
            return AjaxResult.error();
        }

    }

    /**
     * 修改客户账号管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Customer customer = customerService.selectCustomerById(id);
        List<TBusiness> opposites = businessService.selectTBusinessList(null);
        opposites.stream().forEach(opposite->{
            //发现opposite
            if(opposite.getBusinessId()==(customer.getOpposite())){
                customer.setBussiness(opposite);
            }
        });
        //if(!opposites.contains(customer.getOpposite())){customer.setOpposite(-1L);}
        mmap.addAttribute("opposites",opposites);
        mmap.put("customer", customer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户账号管理
     */
    @RequiresPermissions("customer:customer:edit")
    @Log(title = "客户账号管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Customer customer)
    {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除客户账号管理
     */
    @RequiresPermissions("customer:customer:remove")
    @Log(title = "客户账号管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }
}
