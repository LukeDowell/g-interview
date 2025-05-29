package dev.dowell.grainger_interview

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.*

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ProductWebTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `create a product`() {
        mockMvc.post("/products") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "name": "product-${UUID.randomUUID()}"
                }
            """.trimIndent()
        }.andExpect {
            status { isOk() }
            jsonPath("$.id") { exists() }
        }
    }

    companion object {

        @Container
        @ServiceConnection
        @JvmStatic
        val postgres = PostgreSQLContainer(DockerImageName.parse("postgres:17.5-alpine"))
    }
}
