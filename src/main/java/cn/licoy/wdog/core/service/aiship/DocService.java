package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.DocAddDTO;import cn.licoy.wdog.core.dto.aiship.FindDocDTO;import cn.licoy.wdog.core.vo.aiship.DocVO;import cn.licoy.wdog.core.dto.aiship.DocUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Doc;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface DocService extends IService<Doc>{    /**
     * 根据ID查找Doc
     * @param id ID
     * @return Doc
     */
    Doc findDocById(String id);


    /**
     * 获取所有Doc（分页）
     * @param findDocDTO 过滤条件
     * @return RequestResult
     */
    Page<DocVO> getAllDocBySplitPage(FindDocDTO findDocDTO);

    /**
     * Doc状态改变
     * @param userId DocID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Doc
     * @param userId DocID
     */
    void removeDoc(String userId);

    /**
     * 添加Doc
     * @param addDTO Doc数据DTO
     */
    void add(DocAddDTO addDTO);

    /**
     * 更新Doc数据
     * @param id Docid
     * @param updateDTO Doc数据DTO
     */
    void update(String id, DocUpdateDTO updateDTO);
};