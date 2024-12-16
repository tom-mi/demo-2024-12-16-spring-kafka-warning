package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest
@EmbeddedKafka(
    topics = ["com.example.foo"],
    bootstrapServersProperty = "spring.kafka.bootstrap-servers",
    brokerProperties = ["log.dir=build/embedded-kafka/MyKafkaListenerTest"]
)
class MyKafkaListenerTest {


    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @Autowired
    private lateinit var myKafkaListener: MyKafkaListener

    @Test
    fun `receives messages`() {
        // given
        val message = "Hello, World!"
        kafkaTemplate.send("com.example.foo", message)

        // when
        Thread.sleep(1000)

        // then
        assertThat(myKafkaListener.records).contains(message)
    }
}
