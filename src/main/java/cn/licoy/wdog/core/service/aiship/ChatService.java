package cn.licoy.wdog.core.service.aiship;import cn.licoy.wdog.common.service.QueryService;import cn.licoy.wdog.core.dto.aiship.ChatAddDTO;import cn.licoy.wdog.core.dto.aiship.FindChatDTO;import cn.licoy.wdog.core.vo.aiship.ChatVO;import cn.licoy.wdog.core.dto.aiship.ChatUpdateDTO;import cn.licoy.wdog.core.entity.aiship.Chat;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.IService;/** * @author mc * @version Fri May 14 18:51:01 2021 */public interface ChatService extends IService<Chat>{    /**
     * 根据ID查找Chat
     * @param id ID
     * @return Chat
     */
    Chat findChatById(String id);


    /**
     * 获取所有Chat（分页）
     * @param findChatDTO 过滤条件
     * @return RequestResult
     */
    Page<ChatVO> getAllChatBySplitPage(FindChatDTO findChatDTO);

    /**
     * Chat状态改变
     * @param userId ChatID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除Chat
     * @param userId ChatID
     */
    void removeChat(String userId);

    /**
     * 添加Chat
     * @param addDTO Chat数据DTO
     */
    void add(ChatAddDTO addDTO);

    /**
     * 更新Chat数据
     * @param id Chatid
     * @param updateDTO Chat数据DTO
     */
    void update(String id, ChatUpdateDTO updateDTO);
};