package com.wamk.carInsurence.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.CarRepository;

import jakarta.transaction.Transactional;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Transactional
	public Car save(Car car) {
		return carRepository.save(car);
	}

	public List<Car> findAll() {
		return carRepository.findAll();
	}

	public Optional<Car> findById(Long id) {
		return carRepository.findById(id);
	}

	@Transactional
	public void delete(Car car) {
		carRepository.delete(car);
	}

}
