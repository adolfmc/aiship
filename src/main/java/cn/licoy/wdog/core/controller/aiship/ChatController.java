package cn.licoy.wdog.core.controller.aiship;
    @ApiOperation(value = "分页获取Chat数据")
    @SysLogs("分页获取Chat数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "Chat获取过滤条件") FindChatDTO findChatDTO){
        return ResponseResult.e(ResponseCode.OK,ChatService.getAllChatBySplitPage(findChatDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取Chat信息")
    @SysLogs("根据ID获取Chat信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "ChatID") String id){
        return ResponseResult.e(ResponseCode.OK,ChatService.findChatById(id));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定Chat")
    @SysLogs("锁定Chat")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "Chat标识ID") String id){
        ChatService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除Chat")
    @SysLogs("删除Chat")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "Chat标识ID") String id){
        ChatService.removeChat(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加Chat")
    @SysLogs("添加Chat")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "Chat数据") ChatAddDTO addDTO){
        ChatService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新Chat")
    @SysLogs("更新Chat")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "Chat标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "Chat数据") ChatUpdateDTO updateDTO){
        ChatService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}