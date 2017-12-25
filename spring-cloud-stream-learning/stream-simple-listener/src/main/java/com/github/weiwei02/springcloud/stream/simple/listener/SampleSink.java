package com.github.weiwei02.springcloud.stream.simple.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**简单消息接收者定义
 * @author Wang Weiwei <email>weiwei02@vip.qq.com / weiwei.wang@100credit.com</email>
 * @version 1.0
 * @sine 2017/12/26
 */

@EnableBinding(SampleSink.Sink.class)
public class SampleSink {
    // 消息接收者定义
    @StreamListener(Sink.SAMPLE)
    public void receive(Foo fooMessage) {
        System.out.println("******************");
        System.out.println("At the Sink");
        System.out.println("******************");
        System.out.println("Received transformed message " + fooMessage.getValue() + " of type " + fooMessage.getClass());
    }


    /**
     * 自定义消息通道接口
     * */
    public interface Sink {
        String SAMPLE = "sample-sink";

        @Input(SAMPLE)
        SubscribableChannel sampleSink();
    }
}
