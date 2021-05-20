package cn.licoy.wdog.core.service.aiship.impl;import cn.licoy.wdog.core.dto.aiship.FindChatDTO;import cn.licoy.wdog.core.dto.aiship.ChatAddDTO;import cn.licoy.wdog.core.dto.aiship.ChatUpdateDTO;import cn.licoy.wdog.core.vo.aiship.ChatVO;import org.springframework.beans.BeanUtils;import cn.licoy.wdog.common.exception.RequestException;import java.util.ArrayList;import java.util.List;import cn.licoy.wdog.core.entity.aiship.Chat;import cn.licoy.wdog.core.mapper.aiship.ChatMapper;import cn.licoy.wdog.core.service.aiship.ChatService;import com.alibaba.fastjson.JSONObject;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author mc * @version Fri May 14 18:51:01 2021 */@Servicepublic class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {    @Override
    public Chat findChatById(String id) {
        Chat user = this.selectById(id);
        if(user == null){
            return null;
        }
        return user;
    }



    @Override
    public Page<ChatVO> getAllChatBySplitPage(FindChatDTO findChatDTO) {
        EntityWrapper<Chat> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_date",findChatDTO.getAsc());
        Page<Chat> userPage = this.selectPage(new Page<>(findChatDTO.getPage(),findChatDTO.getPageSize()), wrapper);
        Page<ChatVO> userVOPage = new Page<>();
            try {
               BeanUtils.copyProperties(userPage,userVOPage);
            } catch (Exception e) {
               e.printStackTrace();
            }
        List<ChatVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v->{
            ChatVO vo = new ChatVO();
            try {
               BeanUtils.copyProperties(v,vo);
            } catch (Exception e) {
               e.printStackTrace();
            }
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        return userVOPage;
    }

    @Override
    public void statusChange(String userId, Integer status) {
        Chat user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Chat不存在");
        }
        Chat Chat = new Chat();
    }

    @Override
    public void removeChat(String userId) {
        Chat user = this.selectById(userId);
        if(user==null){
            throw RequestException.fail("Chat不存在！");
        }
        Chat Chat = new Chat();
        try {
            this.deleteById(userId);
        }catch (Exception e){
            throw RequestException.fail("删除失败",e);
        }
    }

    @Override
    public void add(ChatAddDTO addDTO) {
        try {
            Chat Chat = new Chat();
            BeanUtils.copyProperties(addDTO,Chat);
            Chat.setCreateDate(new Date());
            this.insert(Chat);
        }catch (Exception e){
            throw RequestException.fail("添加Chat失败",e);
        }
    }

    @Override
    public void update(String id, ChatUpdateDTO updateDTO) {
        Chat user = this.selectById(id);
        if(user==null){
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的Chat",id));
        }
        try {
            BeanUtils.copyProperties(updateDTO,user);
            this.updateById(user);
        }catch (Exception e){
            throw RequestException.fail("Chat信息更新失败",e);
        }
    }
}