package com.huaan.javabasic.com.huaan.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemo {
    public static void main(String[] args) {
        boolean isAsync = false;
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // kafka 核心类
        KafkaProducer producer = new KafkaProducer(props);
        String topic = "test";

        int messageNo = 1;
        while (true)
        {
            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            if (isAsync)
            {
                producer.send(new ProducerRecord<>(topic, messageNo, messageStr),
                        new DemoCallBack(startTime, messageNo, messageStr)
                        );
            }
            else
            {
                try {
                    producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get();
                    System.out.println("send message : (" + messageNo + ", " + messageStr + ")");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ++messageNo;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class DemoCallBack implements Callback {
        private final long startTime;
        private final int key;
        private final String message;
        public DemoCallBack(long startTime, int messageNo, String messageStr) {
            this.startTime = startTime;
            this.key = messageNo;
            this.message = messageStr;
        }

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (recordMetadata != null)
            {
                System.out.println("message(" + key + ", " + message + ") send to patition (" + recordMetadata.partition() + ")"
                + ", offset(" + recordMetadata.offset() + ") in " + elapsedTime + " ms");
            }
            else
            {
                e.printStackTrace();
            }
        }
    }
}
