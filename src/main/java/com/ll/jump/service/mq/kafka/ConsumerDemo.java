package com.ll.jump.service.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Properties;

/**
 * @author LiLin
 * @desc 消费者demo
 * @create 2021-05-12 21:47
 **/
public class ConsumerDemo implements Runnable{
    private final KafkaConsumer<String, String> kafkaConsumer;
    private final String topic;
    private static final String GROUP_ID = "group_test_1";
    public ConsumerDemo(String topic){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", GROUP_ID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        kafkaConsumer = new KafkaConsumer<String, String>(props);
        this.topic = topic;
        this.kafkaConsumer.subscribe(Collections.singletonList(topic));
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            if (consumerRecords != null && consumerRecords.count() > 0){
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println("消费了一条消息,key:" + consumerRecord.key() + ";value:" + consumerRecord.value());
                }
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConsumerDemo consumerDemo = new ConsumerDemo("topic_test_1");
        Thread thread1 = new Thread(consumerDemo);
        thread1.start();
    }
}
