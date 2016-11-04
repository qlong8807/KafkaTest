/**
 * 
 */
package kafka.javaapi;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class ProducerJava extends Thread{

	private String broker_url;
	private String topic;

	public void setBrokerUrl(String url){
		this.broker_url = url;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public void run() {
		Producer<String, String> producer = createProducer();
		System.out.println("producer创建完成...");
		int i = 0;
		while (true) {
			producer.send(new ProducerRecord<>(topic, Integer.toString(i), Integer.toString(i)));
			System.out.println("发送-- topic:"+topic+"  message:"+i++);
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Producer<String, String> createProducer() {
		Properties props = new Properties();
        props.put("bootstrap.servers", broker_url);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
	}
}
