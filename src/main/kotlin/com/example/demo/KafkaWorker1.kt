package com.example.demo

import org.apache.kafka.streams.kstream.KStream
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.SendTo

@EnableBinding(KafkaWorker1.StreamProcessor::class)
open class KafkaWorker1 {

    @StreamListener
    @SendTo("output-1")
    fun process(@Input("input-1") inputStream: KStream<String, String>): KStream<String, String> {
        return inputStream
            .mapValues { _, value -> value.toLowerCase() }
    }

    interface StreamProcessor {
        @Input("input-1")
        fun inputStream1(): KStream<String, String>

        @Output("output-1")
        fun outputStream1(): KStream<String, String>
    }
}

