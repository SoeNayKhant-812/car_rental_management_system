package com.crs.main.service;

import com.crs.main.dto.CarDTO;
import com.crs.main.mapper.CarMapper;
import com.crs.main.model.Car;
import com.crs.main.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDTO save(CarDTO carDto) {
        Car car = CarMapper.toEntity(carDto);
        return CarMapper.toDTO(carRepository.save(car));
    }

    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(CarMapper::toDTO).toList();
    }

    public CarDTO findById(long id) {
        return CarMapper.toDTO(carRepository.findById(id).orElse(null));
    }

    public CarDTO update(Long id, CarDTO carDto) {
        Car existingCar = carRepository.findById(id).orElse(null);
        Car updatedCar = new Car();
        if (existingCar != null) {
            Car car = CarMapper.toEntity(carDto);
            car.setId(id);
            updatedCar = carRepository.save(car);
        }
        return CarMapper.toDTO(updatedCar);
    }

    public void deleteById(long id) {
        carRepository.deleteById(id);
    }
}
