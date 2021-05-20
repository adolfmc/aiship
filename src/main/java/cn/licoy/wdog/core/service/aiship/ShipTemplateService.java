package cn.licoy.wdog.core.service.aiship;
     * 根据ID查找ShipTemplate
     * @param id ID
     * @return ShipTemplate
     */
    ShipTemplate findShipTemplateById(String id);


    /**
     * 获取所有ShipTemplate（分页）
     * @param findShipTemplateDTO 过滤条件
     * @return RequestResult
     */
    Page<ShipTemplateVO> getAllShipTemplateBySplitPage(FindShipTemplateDTO findShipTemplateDTO);

    /**
     * ShipTemplate状态改变
     * @param userId ShipTemplateID
     * @param status 状态码
     */
    void statusChange(String userId, Integer status);

    /**
     * 删除ShipTemplate
     * @param userId ShipTemplateID
     */
    void removeShipTemplate(String userId);

    /**
     * 添加ShipTemplate
     * @param addDTO ShipTemplate数据DTO
     */
    void add(ShipTemplateAddDTO addDTO);

    /**
     * 更新ShipTemplate数据
     * @param id ShipTemplateid
     * @param updateDTO ShipTemplate数据DTO
     */
    void update(String id, ShipTemplateUpdateDTO updateDTO);
};