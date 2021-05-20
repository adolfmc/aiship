package cn.licoy.wdog.core.controller.aiship;
    @ApiOperation(value = "分页获取Infos数据")
    @SysLogs("分页获取Infos数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "Infos获取过滤条件") FindInfosDTO findInfosDTO){
        return ResponseResult.e(ResponseCode.OK,InfosService.getAllInfosBySplitPage(findInfosDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取Infos信息")
    @SysLogs("根据ID获取Infos信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "InfosID") String id){
        return ResponseResult.e(ResponseCode.OK,InfosService.findInfosById(id));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定Infos")
    @SysLogs("锁定Infos")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "Infos标识ID") String id){
        InfosService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除Infos")
    @SysLogs("删除Infos")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "Infos标识ID") String id){
        InfosService.removeInfos(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加Infos")
    @SysLogs("添加Infos")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "Infos数据") InfosAddDTO addDTO){
        InfosService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新Infos")
    @SysLogs("更新Infos")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "Infos标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "Infos数据") InfosUpdateDTO updateDTO){
        InfosService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}