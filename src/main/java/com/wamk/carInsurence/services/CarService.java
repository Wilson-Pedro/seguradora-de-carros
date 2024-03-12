package com.wamk.carInsurence.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;

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

	public Car findById(Long id) {
		return carRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Transactional
	public Car update(Car car, Long id) {
		var carUpdated = findById(id);
		BeanUtils.copyProperties(car, carUpdated);
		carUpdated.setId(id);
		return carUpdated;
	}

	@Transactional
	public void delete(Long id) {
		carRepository.delete(carRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException()));
	}
}
