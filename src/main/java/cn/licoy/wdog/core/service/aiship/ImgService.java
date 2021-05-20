package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.ImgAddDTO;import cn.licoy.wdog.core.dto.aiship.FindImgDTO;import cn.licoy.wdog.core.vo.aiship.ImgVO;import cn.licoy.wdog.core.dto.aiship.ImgUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Img;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface ImgService extends IService<Img>{    /**
     * 根据ID查找Img
     * @param id ID
     * @return Img
     */
    Img findImgById(String id);


    /**
     * 获取所有Img（分页）
     * @param findImgDTO 过滤条件
     * @return RequestResult
     */
    Page<ImgVO> getAllImgBySplitPage(FindImgDTO findImgDTO);

    /**
     * Img状态改变
     * @param userId ImgID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Img
     * @param userId ImgID
     */
    void removeImg(String userId);

    /**
     * 添加Img
     * @param addDTO Img数据DTO
     */
    void add(ImgAddDTO addDTO);

    /**
     * 更新Img数据
     * @param id Imgid
     * @param updateDTO Img数据DTO
     */
    void update(String id, ImgUpdateDTO updateDTO);
};