package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindShipTemplateDTO;import cn.licoy.wdog.core.dto.aiship.ShipTemplateAddDTO;import cn.licoy.wdog.core.dto.aiship.ShipTemplateUpdateDTO;import cn.licoy.wdog.core.vo.aiship.ShipTemplateVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.ShipTemplate;import cn.licoy.wdog.core.mapper.aiship.ShipTemplateMapper;import cn.licoy.wdog.core.service.aiship.ShipTemplateService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class ShipTemplateServiceImpl extends ServiceImpl<ShipTemplateMapper, ShipTemplate> implements ShipTemplateService {    @Override
    public ShipTemplate findShipTemplateById(String id) {
        ShipTemplate user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<ShipTemplateVO> getAllShipTemplateBySplitPage(FindShipTemplateDTO findShipTemplateDTO) {
        EntityWrapper<ShipTemplate> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findShipTemplateDTO.getAsc());
        Page<ShipTemplate> userPage = this.selectPage(new Page<>(findShipTemplateDTO.getPage(),findShipTemplateDTO.getPageSize()), wrapper);
        Page<ShipTemplateVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<ShipTemplateVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            ShipTemplateVO vo = new ShipTemplateVO();
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
        ShipTemplate user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("ShipTemplate不存在");
        }
        ShipTemplate ShipTemplate = new ShipTemplate();
    }

    @Override
    public void removeShipTemplate(String userId) {
        ShipTemplate user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("ShipTemplate不存在！");
        }
        ShipTemplate ShipTemplate = new ShipTemplate();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(ShipTemplateAddDTO addDTO) {
        try {
            ShipTemplate ShipTemplate = new ShipTemplate();
            BeanUtils.copyProperties(addDTO,ShipTemplate);
            ShipTemplate.setCreateDate(new Date());
            this.insert(ShipTemplate);
        }catch (Exception e){
            throw RequestException.fail("添加ShipTemplate失败",e);
        }
    }

    @Override
    public void update(String id, ShipTemplateUpdateDTO updateDTO) {
        ShipTemplate user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的ShipTemplate",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("ShipTemplate信息更新失败",e);
        }
    }
}