/**
 * 
 */
package kafka.javaapi;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 */
public class Start {
	public static void main(String[] args) {
		String brokerUrl = "192.168.0.92:9092";
//		String zookeeperUrl = "centos01:2181,centos02:2181,centos03:2181";
		ConsumerJava c = new ConsumerJava();
		c.setTopic("topic111");
		c.setBrokerUrl(brokerUrl);
		c.start();
		System.out.println("---------------------");
		ProducerJava p = new ProducerJava();
		p.setTopic("topic111");
		p.setBrokerUrl(brokerUrl);
		p.start();
		System.out.println("=====================");
	}
}
