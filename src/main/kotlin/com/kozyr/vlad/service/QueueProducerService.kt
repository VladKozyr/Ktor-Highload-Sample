package com.kozyr.vlad.service

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class QueueProducerService {
    private val factory = ConnectionFactory()
    private val connection: Connection = factory.apply {
        host = "rabbitmq"
        username = "guest"
        password = "guest"
        virtualHost = "/"
        port = 5672
    }.newConnection()

    private val channel: Channel = connection.createChannel()
    private val channelIds = mutableListOf<String>()

    internal fun sendMessage(message: ByteArray, queueName: String) {

        if (!channelIds.contains(queueName)) {
            channel.queueDeclare(
                queueName,
                false,
                false,
                false,
                null
            )
            channelIds.add(queueName)
        }

        channel.basicPublish(
            "",
            queueName,
            null,
            message
        )
    }
}