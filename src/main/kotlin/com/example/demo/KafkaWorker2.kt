package com.example.demo

import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.SendTo

@EnableBinding(KafkaWorker2.StreamProcessor::class)
open class KafkaWorker2 {

    @StreamListener
    @SendTo("output-2")
    fun process(@Input("input-2") inputStream: KStream<String, String>): KStream<String, String> {
        return inputStream
            .mapValues { _, value -> value.plus(" foo ") }
    }

    interface StreamProcessor {
        @Input("input-2")
        fun inputStream(): KStream<String, String>

        @Output("output-2")
        fun outputStream(): KStream<String, String>
    }
}
