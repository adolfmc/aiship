package cn.licoy.wdog.core.controller.aiship;import cn.licoy.wdog.common.annotation.SysLogs;import io.swagger.annotations.ApiImplicitParam;import cn.licoy.wdog.common.bean.ResponseCode;import cn.licoy.wdog.common.bean.ResponseResult;import cn.licoy.wdog.common.controller.AppotBaseController;import cn.licoy.wdog.core.dto.aiship.FavoritesAddDTO;import cn.licoy.wdog.core.dto.aiship.FindFavoritesDTO;import cn.licoy.wdog.core.dto.aiship.FavoritesUpdateDTO;import cn.licoy.wdog.core.service.aiship.FavoritesService;import io.swagger.annotations.Api;import com.alibaba.fastjson.JSONObject;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiParam;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.validation.annotation.Validated;import org.springframework.web.bind.annotation.*;/** * @author mc * @version Fri May 14 18:51:01 2021 */@RestController@RequestMapping(value="/Favorites")public class FavoritesController  extends AppotBaseController{   @Autowired   private FavoritesService FavoritesService;   public FavoritesService getFavoritesService() {      return FavoritesService;   }   @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取Favorites数据")
    @SysLogs("分页获取Favorites数据")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "Favorites获取过滤条件") FindFavoritesDTO findFavoritesDTO){
        return ResponseResult.e(ResponseCode.OK,FavoritesService.getAllFavoritesBySplitPage(findFavoritesDTO));
    }

    @PostMapping(value = {"/get/id/{id}"})
    @ApiOperation(value = "根据ID获取Favorites信息")
    @SysLogs("根据ID获取Favorites信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "FavoritesID") String id){
        return ResponseResult.e(ResponseCode.OK,FavoritesService.findFavoritesById(id));
    }

    @PostMapping(value = {"/lock/{id}"})
    @ApiOperation(value = "锁定Favorites")
    @SysLogs("锁定Favorites")
    public ResponseResult lock(@PathVariable("id") @ApiParam(value = "Favorites标识ID") String id){
        FavoritesService.statusChange(id, 0);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除Favorites")
    @SysLogs("删除Favorites")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "Favorites标识ID") String id){
        FavoritesService.removeFavorites(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加Favorites")
    @SysLogs("添加Favorites")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "Favorites数据") FavoritesAddDTO addDTO){
        FavoritesService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新Favorites")
    @SysLogs("更新Favorites")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "Favorites标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "Favorites数据") FavoritesUpdateDTO updateDTO){
        FavoritesService.update(id,updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }
}