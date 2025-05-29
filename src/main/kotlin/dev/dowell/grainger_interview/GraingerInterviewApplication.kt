package dev.dowell.grainger_interview

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class GraingerInterviewApplication : CommandLineRunner {

	@Autowired
	lateinit var productRepository: GProductRepository

	@Bean
	fun objectMapper(): ObjectMapper {
		return jacksonObjectMapper()
	}

	override fun run(vararg args: String?) {
        repeat(10) {
            productRepository.save(GProduct(name = "real-fake-product-${UUID.randomUUID()}"))
        }
    }
}

fun main(args: Array<String>) {
	runApplication<GraingerInterviewApplication>(*args)
}
