/**
 * 
 */
package kafka.javaapi;

/**
 * @author zyl
 * @date 2016年7月6日
 * 
 * 如果多个consumer的groupId相同，则会均分同一个topic消息。
 * 如果多个consumer的groupId不同，则每个consumer都会收到一份消息。
 */
public class Start {
	public static void main(String[] args) {
		String brokerUrl = "192.168.0.92:9092";
		ConsumerJava c = new ConsumerJava();
		c.setTopic("topic111");
		c.setBrokerUrl(brokerUrl);
		c.setGroupId("g1");
		c.start();
		ConsumerJava2 c2 = new ConsumerJava2();
		c2.setTopic("topic111");
		c2.setBrokerUrl(brokerUrl);
		c2.setGroupId("g2");
		c2.start();
		System.out.println("---------------------");
		ProducerJava p = new ProducerJava();
		p.setTopic("topic111");
		p.setBrokerUrl(brokerUrl);
		p.start();
		System.out.println("=====================");
	}
}
