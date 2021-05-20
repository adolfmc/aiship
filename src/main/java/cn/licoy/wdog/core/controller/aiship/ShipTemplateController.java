package cn.licoy.wdog.core.controller.aiship;

import cn.licoy.wdog.common.annotation.SysLogs;
import io.swagger.annotations.ApiImplicitParam;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.controller.AppotBaseController;
import cn.licoy.wdog.core.dto.aiship.ShipTemplateAddDTO;
import cn.licoy.wdog.core.dto.aiship.FindShipTemplateDTO;
import cn.licoy.wdog.core.dto.aiship.ShipTemplateUpdateDTO;
import cn.licoy.wdog.core.service.aiship.ShipTemplateService;
import io.swagger.annotations.Api;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author mc
 * @version Fri May 14 18:51:01 2021
 */
@RestController
@RequestMapping(value="/ShipTemplate")
public class ShipTemplateController  extends AppotBaseController{

   @Autowired
   private ShipTemplateService ShipTemplateService;
   public ShipTemplateService getShipTemplateService() {
      return ShipTemplateService;
   }
   @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取ShipTemplate数据")
    @SysLogs("分页获取ShipTemplate数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "ShipTemplate获取过滤条件") FindShipTemplateDTO findShipTemplateDTO){
        return ResponseResult.e(ResponseCode.OK,ShipTemplateService.getAllShipTemplateBySplitPage(findShipTemplateDTO));
    }

    @RequestMapping(value = {"/queryForlist"})
    @ApiOperation(value = "分页获取ShipTemplate数据")
    @SysLogs("分页获取ShipTemplate数据")
    public ResponseResult queryForlist(@RequestBody @Validated @ApiParam(value = "ShipTemplate获取过滤条件") FindShipTemplateDTO findShipTemplateDTO){
       String sql = " select * from biz_ship_template ";
        return ResponseResult.e(ResponseCode.OK,jdbcQueryForList(sql));
    }


    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取ShipTemplate信息")
    @SysLogs("根据ID获取ShipTemplate信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "ShipTemplateID") String id){
        return ResponseResult.e(ResponseCode.OK,ShipTemplateService.findShipTemplateById(id));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定ShipTemplate")
    @SysLogs("锁定ShipTemplate")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "ShipTemplate标识ID") String id){
        ShipTemplateService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除ShipTemplate")
    @SysLogs("删除ShipTemplate")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "ShipTemplate标识ID") String id){
        ShipTemplateService.removeShipTemplate(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加ShipTemplate")
    @SysLogs("添加ShipTemplate")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "ShipTemplate数据") ShipTemplateAddDTO addDTO){
        ShipTemplateService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新ShipTemplate")
    @SysLogs("更新ShipTemplate")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "ShipTemplate标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "ShipTemplate数据") ShipTemplateUpdateDTO updateDTO){
        ShipTemplateService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}
