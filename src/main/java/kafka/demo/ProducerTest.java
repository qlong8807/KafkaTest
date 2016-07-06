/**
 * 
 */
package kafka.demo;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class ProducerTest extends Thread{

	private String zoo_url;
	private String broker_url;
	private String topic;

	public void setZooUrl(String url){
		this.zoo_url = url;
	}
	public void setBrokerUrl(String url){
		this.broker_url = url;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public void run() {
		Producer producer = createProducer();
		System.out.println("producer创建完成...");
		int i = 0;
		while (true) {
			producer.send(new KeyedMessage<Integer, String>(topic, "message: "
					+ i++));
			System.out.println("发送-- topic:"+topic+"  message:"+i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Producer createProducer() {
		Properties properties = new Properties();
//		properties.put("zookeeper.connect",
//				"192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181");// 声明zk
		properties.put("zookeeper.connect",zoo_url);// 声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
//		properties.put("metadata.broker.list",
//				"192.168.1.110:9092,192.168.1.111:9093,192.168.1.112:9094");// 声明kafka
		properties.put("metadata.broker.list",broker_url);// 声明kafka broker
		return new Producer<Integer, String>(new ProducerConfig(properties));
	}
}
