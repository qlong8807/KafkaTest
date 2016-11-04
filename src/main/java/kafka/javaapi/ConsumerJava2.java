/**
 * 
 */
package kafka.javaapi;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class ConsumerJava2 extends Thread{

	private String broker_url;
	private String topic;
	private String groupId;

	public void setGroupId(String groupId){
		this.groupId = groupId;
	}
	public void setBrokerUrl(String brokerUrl){
		this.broker_url = brokerUrl;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public void run() {
		KafkaConsumer<String,String> consumer = createConsumer();
		System.out.println("consumer2 创建完成...");
		consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("2收到：offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
        }
	}

	private KafkaConsumer<String,String> createConsumer() {
		Properties props = new Properties();
        props.put("bootstrap.servers", broker_url);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
	}
}
