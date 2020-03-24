package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Car;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CarMapper extends Mapper, BaseMapper {
    int addCar(Car car);

    int updateCar(Car car);

    int deleteCarById(@Param("ids") String[] ids);

    List<Car> getCar();

    List<Car> getCarByPage(@Param("page") int page, @Param("limit") int limit, String vehicleType);

    List<Car> getCarByLicensePlateNumber(@Param("licensePlateNumber") String licensePlateNumber);

    List<Car> getAvailableCar();
}
