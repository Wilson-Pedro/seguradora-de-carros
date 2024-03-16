package com.wamk.carInsurence.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wamk.carInsurence.entities.Car;
import com.wamk.carInsurence.entities.repositories.CarRepository;
import com.wamk.carInsurence.exceptionhandle.exceptions.EntityNotFoundException;
import com.wamk.carInsurence.exceptionhandle.exceptions.ExistingPlateException;

import jakarta.transaction.Transactional;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	@Transactional
	public Car save(Car car) {
		if(carRepository.existsByPlate(car.getPlate()))
			throw new ExistingPlateException();
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
		return carRepository.save(carUpdated);
	}

	@Transactional
	public void delete(Long id) {
		carRepository.delete(carRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException()));
	}
}
