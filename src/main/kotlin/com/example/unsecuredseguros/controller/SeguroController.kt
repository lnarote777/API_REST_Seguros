package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.exception.ValidationException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SeguroController {

    @Autowired
    private lateinit var seguroService: SeguroService

    @GetMapping("/seguros")
    fun getSegurosList(): List<Seguro> {
        return seguroService.getAll()
    }

    @GetMapping("/seguros/{id}")
    fun getSeguroById(
        @PathVariable id: String
    ): ResponseEntity<Seguro>? {

        if (id.isBlank() || id == "a") {
            throw ValidationException("El id no puede estar vacío")
        }

        val seguro = seguroService.getById(id)

        return ResponseEntity(seguro, HttpStatus.OK)
    }

    @PostMapping("/seguros")
    fun insertSeguro(
        @RequestBody seguro: Seguro?
    ): Seguro? {

        if (seguro == null){
            return null
        }

        return seguroService.insertSeguro(seguro)
    }

    @PutMapping("/seguros")
    fun updateSeguro(
        @RequestBody seguro: Seguro
    ): Seguro? {


        return seguroService.updateSeguro(seguro)
    }

    @DeleteMapping("/seguros/{id}")
    fun deleteSeguroById(
        @PathVariable id: String
    ): Seguro?{

        if (id.isEmpty()){
            return null
        }

        return seguroService.deleteById(id)
    }
}