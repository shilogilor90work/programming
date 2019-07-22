/*

in order to compile we need  slf4j-simple-1.7.26.jar   ,   slf4j-api-1.7.26.jar  , amqp-client-5.7.2.jar
in the directory of file.

to run we need to compile and then run Recv , then Send

javac -cp amqp-client-5.7.2.jar ReceiveLogsDirect.java EmitLogDirect.java

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar ReceiveLogsDirect

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar EmitLogDirect

*/


import com.rabbitmq.client.*;

public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();

        if (argv.length < 1) {
            System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
            System.exit(1);
        }

        for (String severity : argv) {
            channel.queueBind(queueName, EXCHANGE_NAME, severity);
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
