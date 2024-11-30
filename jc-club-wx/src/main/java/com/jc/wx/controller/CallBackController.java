package com.jc.wx.controller;

import com.jc.wx.handler.WxChatMsgFactory;
import com.jc.wx.handler.WxChatMsgHandler;
import com.jc.wx.utils.MessageUtil;
import com.jc.wx.utils.SHA1;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/30
 */

@RestController
@Slf4j
public class CallBackController {

    private static final String token = "iamthejiesiking";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }

    /**
     * 回调消息校验
     *
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature{}, timestamp{}, nonce{}, echostr{}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr))
            return echostr;
        return "unknown";
    }

    /**
     * 回调消息校验
     *
     * @return
     */
    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接受到微信的请求请求参数：signature{}, timestamp{}, nonce{}, echostr{}", requestBody, signature, timestamp, nonce);
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msgType);
        if (!StringUtils.isEmpty(event)) {
            stringBuilder.append(".");
            stringBuilder.append(event);
        }

        log.info("msgType{}, event{}", msgType, event);

        String msgTypeKey = stringBuilder.toString();
        WxChatMsgHandler wxChatByMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        if (Objects.isNull(wxChatByMsgHandler)) {
            return "unknown";
        }
        String replyContent = wxChatByMsgHandler.dealMsg(msgMap);
        log.info(" replyContent{}", replyContent);
        return replyContent;
    }


}
