package com.example.demo

import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.SendTo

@EnableBinding(KafkaWorker3.StreamProcessor::class)
open class KafkaWorker3 {

    @StreamListener
    @SendTo("output-3")
    fun process(@Input("input-3") inputStream: KStream<String, String>): KStream<String, String> {
        return inputStream
            .mapValues { _, value -> value.plus(" bar ") }
    }

    interface StreamProcessor {
        @Input("input-3")
        fun inputStream(): KStream<String, String>

        @Output("output-3")
        fun outputStream(): KStream<String, String>
    }
}
