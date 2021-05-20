package cn.licoy.wdog.core.service.aiship;
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