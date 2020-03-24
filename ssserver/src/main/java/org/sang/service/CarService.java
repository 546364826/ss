package org.sang.service;

import org.sang.bean.Car;
import org.sang.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zwj
 * @className: CarService
 * @packageName: org.sang.service
 * @description:
 * @data: 2020/2/10
 **/
@Service
@Transactional
public class CarService {
    @Autowired
    CarMapper carMapper;

    public int addCar(Car car) {
        return carMapper.addCar(car);
    }

    public List<Car> getCar() {
        return carMapper.getCar();
    }

    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    public boolean deleteCar(String ids) {
        String[] split = ids.split(",");
        return carMapper.deleteCarById(split) == split.length;
    }

    public List<Car> getCarByPage(int page, int limit, String vehicleType) {
        return carMapper.getCarByPage(page, limit, vehicleType);
    }

    public  List<Car> getCarByLicensePlateNumber(String licensePlateNumber){
        return carMapper.getCarByLicensePlateNumber(licensePlateNumber);
    }

    public List<Car> getAvailableCar(){
        return carMapper.getAvailableCar();
    }
}
