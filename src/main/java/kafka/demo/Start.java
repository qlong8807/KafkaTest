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
		String brokerUrl = "192.168.0.92:9092";
//		String zookeeperUrl = "centos01:2181,centos02:2181,centos03:2181";
		String zookeeperUrl = "192.168.0.92:2181";
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
//		ProducerTest2 p2 = new ProducerTest2();
//		p2.setTopic("topic1");
//		p2.setZooUrl(zookeeperUrl);
//		p2.setBrokerUrl(brokerUrl);
//		p2.start();
//		System.out.println("=====================");
	}
}
