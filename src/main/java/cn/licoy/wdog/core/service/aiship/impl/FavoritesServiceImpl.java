package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindFavoritesDTO;import cn.licoy.wdog.core.dto.aiship.FavoritesAddDTO;import cn.licoy.wdog.core.dto.aiship.FavoritesUpdateDTO;import cn.licoy.wdog.core.vo.aiship.FavoritesVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.Favorites;import cn.licoy.wdog.core.mapper.aiship.FavoritesMapper;import cn.licoy.wdog.core.service.aiship.FavoritesService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {    @Override
    public Favorites findFavoritesById(String id) {
        Favorites user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<FavoritesVO> getAllFavoritesBySplitPage(FindFavoritesDTO findFavoritesDTO) {
        EntityWrapper<Favorites> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findFavoritesDTO.getAsc());
        Page<Favorites> userPage = this.selectPage(new Page<>(findFavoritesDTO.getPage(),findFavoritesDTO.getPageSize()), wrapper);
        Page<FavoritesVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<FavoritesVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            FavoritesVO vo = new FavoritesVO();
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
        Favorites user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Favorites不存在");
        }
        Favorites Favorites = new Favorites();
    }

    @Override
    public void removeFavorites(String userId) {
        Favorites user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Favorites不存在！");
        }
        Favorites Favorites = new Favorites();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(FavoritesAddDTO addDTO) {
        try {
            Favorites Favorites = new Favorites();
            BeanUtils.copyProperties(addDTO,Favorites);
            Favorites.setCreateDate(new Date());
            this.insert(Favorites);
        }catch (Exception e){
            throw RequestException.fail("添加Favorites失败",e);
        }
    }

    @Override
    public void update(String id, FavoritesUpdateDTO updateDTO) {
        Favorites user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Favorites",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Favorites信息更新失败",e);
        }
    }
}