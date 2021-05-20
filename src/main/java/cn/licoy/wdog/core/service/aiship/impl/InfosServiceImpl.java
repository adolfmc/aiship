package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindInfosDTO;import cn.licoy.wdog.core.dto.aiship.InfosAddDTO;import cn.licoy.wdog.core.dto.aiship.InfosUpdateDTO;import cn.licoy.wdog.core.vo.aiship.InfosVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.Infos;import cn.licoy.wdog.core.mapper.aiship.InfosMapper;import cn.licoy.wdog.core.service.aiship.InfosService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class InfosServiceImpl extends ServiceImpl<InfosMapper, Infos> implements InfosService {    @Override
    public Infos findInfosById(String id) {
        Infos user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<InfosVO> getAllInfosBySplitPage(FindInfosDTO findInfosDTO) {
        EntityWrapper<Infos> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findInfosDTO.getAsc());
        Page<Infos> userPage = this.selectPage(new Page<>(findInfosDTO.getPage(),findInfosDTO.getPageSize()), wrapper);
        Page<InfosVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<InfosVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            InfosVO vo = new InfosVO();
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
        Infos user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Infos不存在");
        }
        Infos Infos = new Infos();
    }

    @Override
    public void removeInfos(String userId) {
        Infos user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Infos不存在！");
        }
        Infos Infos = new Infos();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(InfosAddDTO addDTO) {
        try {
            Infos Infos = new Infos();
            BeanUtils.copyProperties(addDTO,Infos);
            Infos.setCreateDate(new Date());
            this.insert(Infos);
        }catch (Exception e){
            throw RequestException.fail("添加Infos失败",e);
        }
    }

    @Override
    public void update(String id, InfosUpdateDTO updateDTO) {
        Infos user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Infos",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Infos信息更新失败",e);
        }
    }
}