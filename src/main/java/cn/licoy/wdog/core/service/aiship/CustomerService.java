package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.CustomerAddDTO;import cn.licoy.wdog.core.dto.aiship.FindCustomerDTO;import cn.licoy.wdog.core.vo.aiship.CustomerVO;import cn.licoy.wdog.core.dto.aiship.CustomerUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Customer;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface CustomerService extends IService<Customer>{    /**
     * 根据ID查找Customer
     * @param id ID
     * @return Customer
     */
    Customer findCustomerById(String id);


    /**
     * 获取所有Customer（分页）
     * @param findCustomerDTO 过滤条件
     * @return RequestResult
     */
    Page<CustomerVO> getAllCustomerBySplitPage(FindCustomerDTO findCustomerDTO);

    /**
     * Customer状态改变
     * @param userId CustomerID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Customer
     * @param userId CustomerID
     */
    void removeCustomer(String userId);

    /**
     * 添加Customer
     * @param addDTO Customer数据DTO
     */
    void add(CustomerAddDTO addDTO);

    /**
     * 更新Customer数据
     * @param id Customerid
     * @param updateDTO Customer数据DTO
     */
    void update(String id, CustomerUpdateDTO updateDTO);
};