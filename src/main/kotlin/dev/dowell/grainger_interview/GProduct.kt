package dev.dowell.grainger_interview

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
class GProduct(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String,
)

@Repository
interface GProductRepository : CrudRepository<GProduct, Long>

data class GProductCreateRequest(val name: String)

@RestController
@RequestMapping("/products")
@CrossOrigin
class GProductController(val productRepository: GProductRepository) {

    @PostMapping
    fun createProduct(@RequestBody productBody: GProductCreateRequest): ResponseEntity<GProduct> {
        val saved = productRepository.save(GProduct(name = productBody.name))
        return ResponseEntity.status(200).body(saved)
    }

    @GetMapping
    fun getProducts(): ResponseEntity<List<GProduct>> {
        val products = productRepository.findAll().toList()
        return ResponseEntity.status(200).body(products)
    }
}
