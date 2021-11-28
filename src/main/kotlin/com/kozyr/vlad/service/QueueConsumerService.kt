package com.kozyr.vlad.service

import com.rabbitmq.client.*

private const val CONSUMER_TAG = "consumer"

class QueueConsumerService {
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

    internal fun consumeMessages(queueName: String, block: (ByteArray) -> Unit) {
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

        val deliverCallback = DeliverCallback { _, message -> block(message.body) }
        val cancelCallback = CancelCallback {}

        channel.basicConsume(queueName, true, CONSUMER_TAG, deliverCallback, cancelCallback)
    }
}