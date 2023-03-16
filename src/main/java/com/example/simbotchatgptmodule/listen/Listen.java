package com.example.simbotchatgpt.listen;

import com.example.simbotchatgpt.utils.GPT3;
import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simboot.filter.MatchType;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.MessagesBuilder;
import love.forte.simbot.resources.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class Listen {

    @Listener
    @ContentTrim
    @Filter(targets = @Filter.Targets(atBot = true))
    public void listenGroup(GroupMessageEvent event){
        String text=event.getMessageContent().getPlainText();
        if(text.contains("色色")){
            MessagesBuilder builder = new MessagesBuilder();
            File file = new File("C:\\Users\\hong\\Pictures\\QQ图片20230315211602.jpg");
            try {
                builder.image(Resource.of(file));//转换发送
                event.getSource().sendBlocking(builder.build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            event.replyBlocking( new GPT3().send(text));
        }

    }

    @Listener
    @ContentTrim
    @Filter(value = "验证码" , matchType = MatchType.TEXT_CONTAINS)
    public void code(GroupMessageEvent event){
        event.replyBlocking("验证码为66666");
    }
}
