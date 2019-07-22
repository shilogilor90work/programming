/*

in order to compile we need  slf4j-simple-1.7.26.jar   ,   slf4j-api-1.7.26.jar  , amqp-client-5.7.2.jar
in the directory of file.

to run we need to compile and then run Recv , then Send

javac -cp amqp-client-5.7.2.jar ReceiveLogs.java EmitLog.java

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar ReceiveLogs

java -cp .:amqp-client-5.7.2.jar:slf4j-api-1.7.26.jar:slf4j-simple-1.7.26.jar EmitLog

*/
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class EmitLog {

  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = argv.length < 1 ? "info: Hello World!" :
                            String.join(" ", argv);

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
    }
  }
}
