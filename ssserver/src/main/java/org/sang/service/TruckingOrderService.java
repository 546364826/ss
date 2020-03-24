package org.sang.service;

import org.sang.bean.TruckingOrder;
import org.sang.mapper.TruckingOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: zwj
 * @className: TruckingOrderService
 * @packageName: org.sang.service
 * @description:
 * @data: 2020/2/24
 **/
@Service
@Transactional
public class TruckingOrderService {
    @Autowired
    TruckingOrderMapper truckingOrderMapper;

    public int addTruckingOrder(TruckingOrder truckingOrder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = null;
        try {
            date = df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        truckingOrder.setWriteTime(date);
        return truckingOrderMapper.addTruckingOrder(truckingOrder);
    }

    public List<TruckingOrder> getTruckingOrder() {
        return truckingOrderMapper.getTruckingOrder();
    }

    public List<TruckingOrder> getTruckingOrderByState() {
        return truckingOrderMapper.getTruckingOrderByState();
    }

    public int updateTruckingOrder(TruckingOrder truckingOrder) {
        return truckingOrderMapper.updateTruckingOrder(truckingOrder);
    }

    public boolean deleteTruckingOrderById(String ids) {
        String[] split = ids.split(",");
        return truckingOrderMapper.deleteTruckingOrderById(split) == split.length;
    }

    public List<TruckingOrder> getTruckingOrderByCondition(
            String transportDepartment,
            String user,
            String busNumber,
            Date transportTime,
            String destination,
            String reason,
            String licensePlateNumber,
            String driver,
            Date writeTime,
            String state
    ) {
        return truckingOrderMapper.getTruckingOrderByCondition(transportDepartment,
                user,
                busNumber,
                transportTime,
                destination,
                reason,
                licensePlateNumber,
                driver,
                writeTime,
                state);
    }
}
