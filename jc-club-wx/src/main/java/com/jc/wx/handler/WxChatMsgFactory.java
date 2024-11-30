package com.jc.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/30
 */
@Component
public class WxChatMsgFactory implements InitializingBean {

    @Resource
    private List<WxChatMsgHandler> wxChatMsgHandlersList;

    private Map<WxChatMsgTypeEnum,WxChatMsgHandler> handlerMap=new HashMap<>();

    public WxChatMsgHandler getHandlerByMsgType(String msgType){
        WxChatMsgTypeEnum msgTypeEnum=WxChatMsgTypeEnum.getByMsgType(msgType);
        return handlerMap.get(msgTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (WxChatMsgHandler wxChatMsgHandler : wxChatMsgHandlersList) {
            handlerMap.put(wxChatMsgHandler.getMsgType(),wxChatMsgHandler);
        }
    }
}
