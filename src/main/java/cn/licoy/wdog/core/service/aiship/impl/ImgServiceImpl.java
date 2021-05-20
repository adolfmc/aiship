package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindImgDTO;import cn.licoy.wdog.core.dto.aiship.ImgAddDTO;import cn.licoy.wdog.core.dto.aiship.ImgUpdateDTO;import cn.licoy.wdog.core.vo.aiship.ImgVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.Img;import cn.licoy.wdog.core.mapper.aiship.ImgMapper;import cn.licoy.wdog.core.service.aiship.ImgService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {    @Override
    public Img findImgById(String id) {
        Img user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<ImgVO> getAllImgBySplitPage(FindImgDTO findImgDTO) {
        EntityWrapper<Img> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findImgDTO.getAsc());
        Page<Img> userPage = this.selectPage(new Page<>(findImgDTO.getPage(),findImgDTO.getPageSize()), wrapper);
        Page<ImgVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<ImgVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            ImgVO vo = new ImgVO();
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
        Img user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Img不存在");
        }
        Img Img = new Img();
    }

    @Override
    public void removeImg(String userId) {
        Img user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Img不存在！");
        }
        Img Img = new Img();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(ImgAddDTO addDTO) {
        try {
            Img Img = new Img();
            BeanUtils.copyProperties(addDTO,Img);
            Img.setCreateDate(new Date());
            this.insert(Img);
        }catch (Exception e){
            throw RequestException.fail("添加Img失败",e);
        }
    }

    @Override
    public void update(String id, ImgUpdateDTO updateDTO) {
        Img user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Img",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Img信息更新失败",e);
        }
    }
}