package com.example.contextpropagation.reactivemdccontextpropagationwithlogbook

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
class DebugController(
    private val webClientBuilder: WebClient.Builder
) {

    @GetMapping("/test")
    fun test(): Mono<String> {
        return Mono.just("Hello, world")
            .delayElement(Duration.ofMillis(1))
            .flatMap {
                webClientBuilder.baseUrl("https://www.example.com").build()
                    .get()
                    .retrieve()
                    .bodyToMono(String::class.java)
            }
            .contextCapture()
    }

}
