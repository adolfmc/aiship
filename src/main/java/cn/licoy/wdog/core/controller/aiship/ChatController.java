package cn.licoy.wdog.core.controller.aiship;import cn.licoy.wdog.common.annotation.SysLogs;import io.swagger.annotations.ApiImplicitParam;import cn.licoy.wdog.common.bean.ResponseCode;import cn.licoy.wdog.common.bean.ResponseResult;import cn.licoy.wdog.common.controller.AppotBaseController;import cn.licoy.wdog.core.dto.aiship.ChatAddDTO;import cn.licoy.wdog.core.dto.aiship.FindChatDTO;import cn.licoy.wdog.core.dto.aiship.ChatUpdateDTO;import cn.licoy.wdog.core.service.aiship.ChatService;import io.swagger.annotations.Api;import com.alibaba.fastjson.JSONObject;import io.swagger.annotations.ApiOperation;import io.swagger.annotations.ApiParam;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.validation.annotation.Validated;import org.springframework.web.bind.annotation.*;/** * @author mc * @version Fri May 14 18:51:01 2021 */@RestController@RequestMapping(value="/Chat")public class ChatController  extends AppotBaseController{   @Autowired   private ChatService ChatService;   public ChatService getChatService() {      return ChatService;   }   @PostMapping(value = {"/list"})
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