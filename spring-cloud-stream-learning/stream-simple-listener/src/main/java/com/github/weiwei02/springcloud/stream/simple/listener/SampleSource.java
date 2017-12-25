package com.github.weiwei02.springcloud.stream.simple.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;

/** 简单消息源定义
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/26
 */
@EnableBinding(SampleSource.Source.class)
public class SampleSource {

    /**
     * 定义消息源发送适配器
     * */
    @Bean
    @InboundChannelAdapter(value = Source.SAMPLE, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return new MessageSource<String>() {
            public Message<String> receive() {
                System.out.println("******************");
                System.out.println("At the Source");
                System.out.println("******************");
                String value = "{\"value\":\"hello i am message\"}";
                System.out.println("Sending value: " + value);
                return MessageBuilder.withPayload(value).setHeader(MessageHeaders.CONTENT_TYPE, "application/json").build();
            }
        };
    }

    public interface Source {
        String SAMPLE = "sample-source";

        @Output(SAMPLE)
        MessageChannel sampleSource();
    }
}
