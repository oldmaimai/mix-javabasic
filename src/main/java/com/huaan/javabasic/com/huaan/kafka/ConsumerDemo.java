package com.huaan.javabasic.com.huaan.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class ConsumerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.149.131:9092");
        //props.put("group.id", "testa");
        props.put("group.id", UUID.randomUUID().toString());
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session,timout.ms", "30000");
        //Equivalent to --from-beginning)
        props.put("auto.offset.reset", "earliest");//lastest

        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 订阅两个主题
        //consumer.subscribe(Arrays.asList("test1", "test2"));
        consumer.subscribe(Arrays.asList("test"));
        try {
            while (true)
            {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                {
                    System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            consumer.close();
        }

    }
}
