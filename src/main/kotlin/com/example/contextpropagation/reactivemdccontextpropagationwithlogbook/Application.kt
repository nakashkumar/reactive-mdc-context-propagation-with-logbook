package com.example.contextpropagation.reactivemdccontextpropagationwithlogbook

import io.micrometer.context.ContextRegistry
import org.slf4j.MDC
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
	Hooks.enableAutomaticContextPropagation()
	ContextRegistry.getInstance()
		.registerThreadLocalAccessor("mdc", MDC::getCopyOfContextMap, MDC::setContextMap, MDC::clear)
}
