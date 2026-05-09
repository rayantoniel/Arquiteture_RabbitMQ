package org.ifal.publisher;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.ifal.model.SaqueEvent;

public class SaquePublisher {
    private static final String QUEUE_NAME = "fila-saques";

    public static void main(String[] args) throws Exception {

        CurrentAccount account = new CurrentAccount("Ray", 1000);

        double saqueValue = 200;

        boolean sucess = account.sacar(saqueValue);

        if (!sucess) {
            return;
        }

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        SaqueEvent event = new SaqueEvent(
                account.getClient(),
                saqueValue,
                account.getBalance()
        );

        Gson gson = new Gson();

        String message = gson.toJson(event);

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("Evento enviado para fila:");
        System.out.println(message);

        channel.close();
        connection.close();
    }
}
