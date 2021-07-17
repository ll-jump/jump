package com.ll.jump.service.mq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author LiLin
 * @desc 生产者demo
 * @create 2021-05-12 21:39
 **/
public class ProducerDemo {
    private final KafkaProducer<String, String> kafkaProducer;
    private final String topic;

    public ProducerDemo(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.kafkaProducer = new KafkaProducer<String, String>(props);
        this.topic = topicName;notifyAll();
    }

    public void run() {
        try {
            String messageStr = "我是一条测试消息1";
            kafkaProducer.send(new ProducerRecord<>(topic, "testMessage", messageStr));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }

    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo("topic_test_1");
        producerDemo.run();
    }
}
