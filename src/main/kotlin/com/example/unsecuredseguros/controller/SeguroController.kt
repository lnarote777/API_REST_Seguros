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
    fun getSegurosList(): ResponseEntity<List<Seguro>> {

        val listSeguros = seguroService.getAll()

        return ResponseEntity(listSeguros, HttpStatus.OK)
    }

    @GetMapping("/seguros/{id}")
    fun getSeguroById(
        @PathVariable id: String
    ): ResponseEntity<Seguro>? {

        if (id.isBlank()) {
            throw ValidationException("El id no puede estar vacío")
        }

        val seguro = seguroService.getById(id)

        return ResponseEntity(seguro, HttpStatus.OK)
    }

    @PostMapping("/seguros")
    fun insertSeguro(
        @RequestBody nuevoSeguro: Seguro?
    ): ResponseEntity<Seguro>? {

        if (nuevoSeguro == null){
            throw ValidationException("No puede dejar el seguro vacío")
        }

        val seguro = seguroService.insertSeguro(nuevoSeguro)

        return ResponseEntity(seguro, HttpStatus.OK)
    }

    @PutMapping("/seguros")
    fun updateSeguro(
        @RequestBody seguro: Seguro
    ): ResponseEntity<Seguro>? {

        val seguroUp = seguroService.updateSeguro(seguro)

        return ResponseEntity(seguroUp, HttpStatus.OK)
    }

    @DeleteMapping("/seguros/{id}")
    fun deleteSeguroById(
        @PathVariable id: String
    ): ResponseEntity<Seguro>?{

        if (id.isBlank()) {
            throw ValidationException("El id no puede estar vacío")
        }

        val seguro = seguroService.deleteById(id)

        return ResponseEntity(seguro, HttpStatus.OK)
    }
}