/**
 * 
 */
package kafka.demo;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class Start {
	public static void main(String[] args) {
		String brokerUrl = "192.168.125.134:9092";
		String zookeeperUrl = "192.168.125.134:2181";
		ConsumerTest c = new ConsumerTest();
		c.setTopic("topic1");
		c.setZooUrl(zookeeperUrl);
		c.start();
		System.out.println("---------------------");
		ProducerTest p = new ProducerTest();
		p.setTopic("topic1");
		p.setZooUrl(zookeeperUrl);
		p.setBrokerUrl(brokerUrl);
		p.start();
		System.out.println("=====================");
	}
}
