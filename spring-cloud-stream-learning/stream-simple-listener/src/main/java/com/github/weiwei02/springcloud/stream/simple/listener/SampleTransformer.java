package com.github.weiwei02.springcloud.stream.simple.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/** 简单消息转换器
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/26
 */

@EnableBinding(Processor.class)
public class SampleTransformer {
    private static final String TRANSFORMATION_VALUE = "HI";

    // 消息转换器定义  ， 作为处理器，应同时拥有消息输入和输出通道

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Bar receive(Bar barMessage) {
        System.out.println("******************");
        System.out.println("At the transformer");
        System.out.println("******************");
        System.out.println("Received value "+ barMessage.getValue() + " of type " + barMessage.getClass());
        System.out.println("Transforming the value to " + TRANSFORMATION_VALUE + " and with the type " + barMessage.getClass());
        barMessage.setValue(TRANSFORMATION_VALUE);
        return barMessage;
    }
}
