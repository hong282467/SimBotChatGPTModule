package com.example.simbotchatgpt.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.unfbx.chatgpt.OpenAiClient;

import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.Completion;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import com.unfbx.chatgpt.sse.ConsoleEventSourceListener;
import okhttp3.logging.HttpLoggingInterceptor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class GPT3 {

    public  static final String API_KEY="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";
    public String send(String text) {
        //配置api keys
        //代理可以为null
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.111", 7890));
        //日志输出可以不添加
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(API_KEY)
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(null)
                .apiHost("https://api.openai.com/")
                .build();
        CompletionResponse completions = openAiClient.completions(text);
        return completions.getChoices()[0].getText().replaceAll("\\n","");
    }

}
