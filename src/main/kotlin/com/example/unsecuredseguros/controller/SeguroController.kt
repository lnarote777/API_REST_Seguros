package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
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
    ): Seguro? {

        if (id.isEmpty()){
            return null
        }

        return seguroService.getById(id)
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