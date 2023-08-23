package no.kristiania

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
class ItemServiceApplication

fun main(args: Array<String>) {
    runApplication<ItemServiceApplication>(*args)
}
