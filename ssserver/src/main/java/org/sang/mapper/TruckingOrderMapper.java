package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.TruckingOrder;

import java.util.Date;
import java.util.List;

public interface TruckingOrderMapper {
    int addTruckingOrder(TruckingOrder truckingOrder);

    int updateTruckingOrder(TruckingOrder truckingOrder);

    int deleteTruckingOrderById(@Param("ids") String[] ids);

    List<TruckingOrder> getTruckingOrder();

    List<TruckingOrder> getTruckingOrderByState();

    List<TruckingOrder> getTruckingOrderByCondition(
            @Param("transportDepartment") String transportDepartment,
            @Param("user") String user,
            @Param("busNumber") String busNumber,
            @Param("transportTime") Date transportTime,
            @Param("destination") String destination,
            @Param("reason") String reason,
            @Param("licensePlateNumber") String licensePlateNumber,
            @Param("driver") String driver,
            @Param("writeTime") Date writeTime,
            @Param("state") String state);
}
