package org.jbebar

import com.rabbitmq.client.ConnectionFactory
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val numberOfMessages = args.firstOrNull()?.toInt() ?: 10

    val factory = ConnectionFactory()
    factory.host = "localhost"

    val connection = factory.newConnection()
    val channel = connection.createChannel()

    val queueName = "demo-queue"
    channel.queueDeclare(queueName, false, false, false, null)

    for (i in 1..numberOfMessages) {
        val message = "Message $i"
        channel.basicPublish("", queueName, null, message.toByteArray())
        println(" [x] Sent '$message'")
    }

    exitProcess(0)
}