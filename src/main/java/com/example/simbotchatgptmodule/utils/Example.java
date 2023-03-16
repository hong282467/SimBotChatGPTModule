package com.example.simbotchatgpt.utils;


import net.mamoe.mirai.utils.BotConfiguration;
import xyz.cssxsh.mirai.tool.FixProtocolVersion;

import java.util.Map;

public class Example {
    // 升级协议版本
    public static void update() {
        FixProtocolVersion.update();
    }
    // 获取协议版本信息 你可以用这个来检查update是否正常工作
    public static Map<BotConfiguration.MiraiProtocol, String> info() {
        return FixProtocolVersion.info();
    }
}
