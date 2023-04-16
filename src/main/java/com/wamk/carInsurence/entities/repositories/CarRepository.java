package com.wamk.carInsurence.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wamk.carInsurence.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
}
