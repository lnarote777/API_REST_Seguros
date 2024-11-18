package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.exception.NotFoundException
import com.example.unsecuredseguros.exception.ValidationException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.SeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    fun insertSeguro(seguro: Seguro): Seguro? {
        if (seguro.nif.isEmpty() || seguro.nif.length > 9 || seguro.nif.length < 9 || seguro.nif.substring(0, 8).all{it.isDigit()}) {
            throw ValidationException("El NIF debe estar compuesto por 8 digitos y 1 letra.")
        } else if (!validarNif(seguro.nif)) {throw ValidationException("NIF invalido.")}

        if (seguro.nombre.isEmpty() || seguro.ape1.isEmpty()) throw ValidationException("El nombre y el apellido no pueden estar vacíos.")

        if (seguro.edad < 0){
            throw ValidationException("")
        }else if (seguro.edad in 0..17){
            throw ValidationException("No es posible ser menor de edad para hacer un seguro")
        }

        if (seguro.sexo.isEmpty()) throw ValidationException("El campo sexo no debe estar vacío.")

        if (seguro.numHijos < 0) throw ValidationException("Los hijos no pueden ser negativos.")

        if (!seguro.casado) seguro.numHijos = 0

        if (seguro.embarazada) seguro.sexo = "Mujer"

        return seguroRepository.save(seguro)
    }

    fun getById(seguroId: String): Seguro? {
        var idInt : Int

        try {
            idInt = seguroId.toInt()
        }catch (e: Exception){
            throw ValidationException("El id debe ser un numero entero.")
        }

        val seguro = seguroRepository.findByIdOrNull(idInt)

        if (seguro != null) {
            return seguro
        }else{
            throw NotFoundException("El seguro con id $idInt no ha sido encontrado.")
        }
    }

    fun getAll(): List<Seguro> {

        val list = seguroRepository.findAll()

        if (list.isEmpty()){
            throw NotFoundException("No se encontró ningun seguro.")
        }else{
            return list
        }
    }

    fun updateSeguro(nuevoSeguro: Seguro): Seguro? {
        val seguro = seguroRepository.findByIdOrNull(nuevoSeguro.idSeguro)
        if (seguro != null) {
            seguro.nif= nuevoSeguro.nif
            seguro.nombre = nuevoSeguro.nombre
            seguro.ape1 = nuevoSeguro.ape1
            seguro.ape2 = nuevoSeguro.ape2
            seguro.edad = nuevoSeguro.edad
            seguro.sexo = nuevoSeguro.sexo
            seguro.numHijos = nuevoSeguro.numHijos
            seguro.casado = nuevoSeguro.casado
            seguro.embarazada = nuevoSeguro.embarazada
            return seguroRepository.save(seguro)
        }else {
            throw NotFoundException("No se encontró ningún seguro que actualizar.")
        }
    }

    fun deleteById(id: String): Seguro? {
        try {
            val idInt = id.toInt()
            val  seguro = seguroRepository.findByIdOrNull(idInt)
            if (seguro != null) {
                seguroRepository.delete(seguro)
                return seguro
            }else{
               throw NotFoundException("El id debe ser un numero entero.")
            }
        }catch (e: Exception){
            throw ValidationException("El id debe ser un numero entero.")
        }
    }

    fun validarNif(nif: String): Boolean {
        val letrasValidas = listOf('T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E')

        val numero = nif.substring(0, 8).toInt()
        val letraNif = nif.last()

        val resto= numero % 23

        return if (letraNif != letrasValidas[resto]){
            false
        }else{
            true
        }
    }
}
