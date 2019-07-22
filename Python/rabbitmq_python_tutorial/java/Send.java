/*

in order to compile we need  slf4j-simple-1.7.26.jar   ,   slf4j-api-1.7.26.jar  , amqp-client-5.7.2.jar
in the directory of file.

to run we need to compile and then run Recv , then Send

javac -cp amqp-client-5.7.2.jar Send.java Recv.java

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar Recv

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar Send

*/


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
