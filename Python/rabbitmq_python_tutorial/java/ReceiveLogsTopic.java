/*

in order to compile we need  slf4j-simple-1.7.26.jar   ,   slf4j-api-1.7.26.jar  , amqp-client-5.7.2.jar
in the directory of file.

to run we need to compile and then run Recv , then Send

javac -cp amqp-client-5.7.2.jar ReceiveLogsTopic.java EmitLogTopic.java

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar ReceiveLogsTopic

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar EmitLogTopic

*/


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        if (argv.length < 1) {
            System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
            System.exit(1);
        }

        for (String bindingKey : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
