package cn.licoy.wdog.core.controller.aiship;
    @ApiOperation(value = "分页获取Doc数据")
    @SysLogs("分页获取Doc数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "Doc获取过滤条件") FindDocDTO findDocDTO){
        return ResponseResult.e(ResponseCode.OK,DocService.getAllDocBySplitPage(findDocDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取Doc信息")
    @SysLogs("根据ID获取Doc信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "DocID") String id){
        return ResponseResult.e(ResponseCode.OK,DocService.findDocById(id));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定Doc")
    @SysLogs("锁定Doc")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "Doc标识ID") String id){
        DocService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除Doc")
    @SysLogs("删除Doc")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "Doc标识ID") String id){
        DocService.removeDoc(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加Doc")
    @SysLogs("添加Doc")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "Doc数据") DocAddDTO addDTO){
        DocService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新Doc")
    @SysLogs("更新Doc")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "Doc标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "Doc数据") DocUpdateDTO updateDTO){
        DocService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}