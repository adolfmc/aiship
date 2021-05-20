package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.InfosAddDTO;import cn.licoy.wdog.core.dto.aiship.FindInfosDTO;import cn.licoy.wdog.core.vo.aiship.InfosVO;import cn.licoy.wdog.core.dto.aiship.InfosUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Infos;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface InfosService extends IService<Infos>{    /**
     * 根据ID查找Infos
     * @param id ID
     * @return Infos
     */
    Infos findInfosById(String id);


    /**
     * 获取所有Infos（分页）
     * @param findInfosDTO 过滤条件
     * @return RequestResult
     */
    Page<InfosVO> getAllInfosBySplitPage(FindInfosDTO findInfosDTO);

    /**
     * Infos状态改变
     * @param userId InfosID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Infos
     * @param userId InfosID
     */
    void removeInfos(String userId);

    /**
     * 添加Infos
     * @param addDTO Infos数据DTO
     */
    void add(InfosAddDTO addDTO);

    /**
     * 更新Infos数据
     * @param id Infosid
     * @param updateDTO Infos数据DTO
     */
    void update(String id, InfosUpdateDTO updateDTO);
};