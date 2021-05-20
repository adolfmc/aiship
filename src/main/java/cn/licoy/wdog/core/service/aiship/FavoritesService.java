package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.FavoritesAddDTO;import cn.licoy.wdog.core.dto.aiship.FindFavoritesDTO;import cn.licoy.wdog.core.vo.aiship.FavoritesVO;import cn.licoy.wdog.core.dto.aiship.FavoritesUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Favorites;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface FavoritesService extends IService<Favorites>{    /**
     * 根据ID查找Favorites
     * @param id ID
     * @return Favorites
     */
    Favorites findFavoritesById(String id);


    /**
     * 获取所有Favorites（分页）
     * @param findFavoritesDTO 过滤条件
     * @return RequestResult
     */
    Page<FavoritesVO> getAllFavoritesBySplitPage(FindFavoritesDTO findFavoritesDTO);

    /**
     * Favorites状态改变
     * @param userId FavoritesID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Favorites
     * @param userId FavoritesID
     */
    void removeFavorites(String userId);

    /**
     * 添加Favorites
     * @param addDTO Favorites数据DTO
     */
    void add(FavoritesAddDTO addDTO);

    /**
     * 更新Favorites数据
     * @param id Favoritesid
     * @param updateDTO Favorites数据DTO
     */
    void update(String id, FavoritesUpdateDTO updateDTO);
};