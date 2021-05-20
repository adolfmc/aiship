package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindCustomerDTO;import cn.licoy.wdog.core.dto.aiship.CustomerAddDTO;import cn.licoy.wdog.core.dto.aiship.CustomerUpdateDTO;import cn.licoy.wdog.core.vo.aiship.CustomerVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.Customer;import cn.licoy.wdog.core.mapper.aiship.CustomerMapper;import cn.licoy.wdog.core.service.aiship.CustomerService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {    @Override
    public Customer findCustomerById(String id) {
        Customer user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<CustomerVO> getAllCustomerBySplitPage(FindCustomerDTO findCustomerDTO) {
        EntityWrapper<Customer> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findCustomerDTO.getAsc());
        Page<Customer> userPage = this.selectPage(new Page<>(findCustomerDTO.getPage(),findCustomerDTO.getPageSize()), wrapper);
        Page<CustomerVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<CustomerVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            CustomerVO vo = new CustomerVO();
            try {
               BeanUtils.copyProperties(v,vo);
            } catch (Exception e) {
               e.printStackTrace();
            }
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        return userVOPage;
    }

    @Override
    public void statusChange(String userId, Integer status) {
        Customer user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Customer不存在");
        }
        Customer Customer = new Customer();
    }

    @Override
    public void removeCustomer(String userId) {
        Customer user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Customer不存在！");
        }
        Customer Customer = new Customer();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(CustomerAddDTO addDTO) {
        try {
            Customer Customer = new Customer();
            BeanUtils.copyProperties(addDTO,Customer);
            Customer.setCreateDate(new Date());
            this.insert(Customer);
        }catch (Exception e){
            throw RequestException.fail("添加Customer失败",e);
        }
    }

    @Override
    public void update(String id, CustomerUpdateDTO updateDTO) {
        Customer user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Customer",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Customer信息更新失败",e);
        }
    }
}