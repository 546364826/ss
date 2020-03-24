package org.sang.controller.car;

import org.sang.bean.Car;
import org.sang.bean.RespBean;
import org.sang.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: zwj
 * @className: CarController
 * @packageName: org.sang.controller.car
 * @description:
 * @data: 2020/2/10
 **/
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public RespBean addCar(Car car) {
        if (carService.addCar(car) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/getCar", method = RequestMethod.GET)
    public List<Car> getCar() {
        return carService.getCar();
    }

    @RequestMapping(value = "/getCarByPage", method = RequestMethod.GET)
    public List<Car> getCarByPage(int page, int limit, String vehicleType) {
        return carService.getCarByPage(page, limit, vehicleType);
    }

    @RequestMapping(value = "/getAvailableCar", method = RequestMethod.GET)
    public List<Car> getAvailableCar() {
        //获取可用车辆
        return carService.getAvailableCar();
    }

    @RequestMapping(value = "/getCarByLicensePlateNumber", method = RequestMethod.GET)
    public List<Car> getCarByLicensePlateNumber(@RequestParam(defaultValue = "") String licensePlateNumber) {
        return carService.getCarByLicensePlateNumber(licensePlateNumber);
    }


    @RequestMapping(value = "/updateCar", method = RequestMethod.PUT)
    public RespBean updateCar(Car car) {
        if (carService.updateCar(car) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/deleteCar/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteCar(@PathVariable String ids) {
        if (carService.deleteCar(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
