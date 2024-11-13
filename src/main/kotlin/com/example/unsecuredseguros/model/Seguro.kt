package com.example.unsecuredseguros.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "seguros")
data class Seguro(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_seguro")
    val idSeguro: Int,

    @Column(length = 10, nullable = false)
    var nif: String,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(nullable = false, length = 100)
    var ape1: String,

    @Column(length = 100)
    var ape2: String?,

    @Column(nullable = false)
    var edad: Int,

    @Column(nullable = false, name = "num_hijos")
    var numHijos: Int,

    @Column(nullable = false, name = "fecha_creacion")
    val fechaCreacion: Date,

    @Column(nullable = false, length = 100)
    var sexo: String,

    @Column(nullable = false)
    var casado: Boolean,

    @Column(nullable = false)
    var embarazada: Boolean
)
