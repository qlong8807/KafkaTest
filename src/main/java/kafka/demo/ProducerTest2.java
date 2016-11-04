/**
 * 
 */
package kafka.demo;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import org.apache.commons.io.FileUtils;
import org.jboss.netty.util.CharsetUtil;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class ProducerTest2 extends Thread{

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
		Producer<String, String> producer = createProducer();
		System.out.println("producer2创建完成...");
		while (true) {
			Collection<File> listFiles = FileUtils.listFiles(new File("/"), new String[]{"log","xml"}, false);
			for(File file : listFiles){
				try {
					List<String> readLines = FileUtils.readLines(file,CharsetUtil.UTF_8);
					for(String line : readLines){
						Date d = new Date();
						System.out.println(d+" send message:"+line);
						KeyedMessage<String, String> keyedMessage = new KeyedMessage<String,String>(topic, line);
						producer.send(keyedMessage);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Producer<String, String> createProducer() {
		Properties properties = new Properties();
//		properties.put("zookeeper.connect",
//				"192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181");// 声明zk
		properties.put("zookeeper.connect",zoo_url);// 声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
//		properties.put("metadata.broker.list",
//				"192.168.1.110:9092,192.168.1.111:9093,192.168.1.112:9094");// 声明kafka
		properties.put("metadata.broker.list",broker_url);// 声明kafka broker
		return new Producer<String, String>(new ProducerConfig(properties));
	}
}
