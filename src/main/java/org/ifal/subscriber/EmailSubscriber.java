package org.ifal.subscriber;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.ifal.model.SaqueEvent;

public class EmailSubscriber {
    private static final String QUEUE_NAME = "fila-saques";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println("Aguardando eventos de saque...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody());

            Gson gson = new Gson();

            SaqueEvent event = gson.fromJson(message, SaqueEvent.class);

            System.out.println("\nEMAIL ENVIADO");
            System.out.println("Cliente: " + event.getClient());
            System.out.println("Valor do saque: R$ " + event.getValue());
            System.out.println("Saldo restante: R$ " + event.restBalance());
        };

        channel.basicConsume(
                QUEUE_NAME,
                true,
                deliverCallback,
                consumerTag -> {}
        );
    }
}
