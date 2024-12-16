package com.example.demo

import org.apache.kafka.clients.consumer.ConsumerRecords
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MyKafkaListener {

    val records: MutableList<String> = mutableListOf()

    @KafkaListener(
        topics = ["com.example.foo"],
        groupId = "my-group-id",
        batch = "true",
    )
    fun listen(records: ConsumerRecords<String, String>) {
        for (record in records) {
            println("${record.key()}: ${record.value()}")
            this.records.add(record.value())
        }
    }
}
