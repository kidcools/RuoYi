package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Customer;

/**
 * 客户账号管理Service接口
 * 
 * @author milestar
 * @date 2021-09-14
 */
public interface ICustomerService 
{
    /**
     * 查询客户账号管理
     * 
     * @param id 客户账号管理主键
     * @return 客户账号管理
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户账号管理列表
     * 
     * @param customer 客户账号管理
     * @return 客户账号管理集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户账号管理
     * 
     * @param customer 客户账号管理
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户账号管理
     * 
     * @param customer 客户账号管理
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量删除客户账号管理
     * 
     * @param ids 需要删除的客户账号管理主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(String ids);

    /**
     * 删除客户账号管理信息
     * 
     * @param id 客户账号管理主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);
}
