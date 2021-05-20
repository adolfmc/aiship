package cn.licoy.wdog.core.controller.aiship;

import cn.licoy.wdog.common.annotation.SysLogs;
import io.swagger.annotations.ApiImplicitParam;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.controller.AppotBaseController;
import cn.licoy.wdog.core.dto.aiship.ImgAddDTO;
import cn.licoy.wdog.core.dto.aiship.FindImgDTO;
import cn.licoy.wdog.core.dto.aiship.ImgUpdateDTO;
import cn.licoy.wdog.core.service.aiship.ImgService;
import io.swagger.annotations.Api;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author mc
 * @version Fri May 14 18:51:01 2021
 */
@RestController
@RequestMapping(value="/Img")
public class ImgController  extends AppotBaseController{

   @Autowired
   private ImgService ImgService;
   public ImgService getImgService() {
      return ImgService;
   }
   @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取Img数据")
    @SysLogs("分页获取Img数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "Img获取过滤条件") FindImgDTO findImgDTO){
        return ResponseResult.e(ResponseCode.OK,ImgService.getAllImgBySplitPage(findImgDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取Img信息")
    @SysLogs("根据ID获取Img信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "ImgID") String id){
        return ResponseResult.e(ResponseCode.OK,ImgService.findImgById(id));
    }


    @RequestMapping(value = {"/getImages"})
    @ApiOperation(value = "根据ID获取Img信息")
    @SysLogs("根据ID获取Img信息")
    public ResponseResult getImages(){
        List<Map<String,Object>> images = jdbcTemplate.queryForList("select * from biz_img");
        return ResponseResult.e(ResponseCode.OK,images);
    }



    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定Img")
    @SysLogs("锁定Img")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "Img标识ID") String id){
        ImgService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除Img")
    @SysLogs("删除Img")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "Img标识ID") String id){
        ImgService.removeImg(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加Img")
    @SysLogs("添加Img")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "Img数据") ImgAddDTO addDTO){
        ImgService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新Img")
    @SysLogs("更新Img")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "Img标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "Img数据") ImgUpdateDTO updateDTO){
        ImgService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}
