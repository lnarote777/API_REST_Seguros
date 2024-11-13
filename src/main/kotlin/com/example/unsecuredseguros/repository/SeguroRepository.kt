package com.example.unsecuredseguros.repository

import com.example.unsecuredseguros.model.Seguro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface SeguroRepository: JpaRepository<Seguro, Int>, CrudRepository<Seguro, Int> {
}