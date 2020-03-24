package org.sang.controller.TruckingOrder;

import org.sang.bean.Department;
import org.sang.bean.Employee;
import org.sang.bean.RespBean;
import org.sang.bean.TruckingOrder;
import org.sang.service.DepartmentService;
import org.sang.service.EmpService;
import org.sang.service.TruckingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author: zwj
 * @className: TruckingOrderController
 * @packageName: org.sang.controller.TruckingOrder
 * @description:
 * @data: 2020/2/24
 **/
@RestController
@RequestMapping("/truckingOrder")
public class TruckingOrderController {
    @Autowired
    TruckingOrderService truckingOrderService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmpService empService;

    @RequestMapping(value = "/addTruckingOrder", method = RequestMethod.POST)
    public RespBean addTruckingOrder(TruckingOrder truckingOrder) {
        if (truckingOrderService.addTruckingOrder(truckingOrder) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/getTruckingOrder", method = RequestMethod.GET)
    public List<TruckingOrder> getTruckingOrder() {
        return truckingOrderService.getTruckingOrder();
    }

    @RequestMapping(value = "/getTruckingOrderByState", method = RequestMethod.GET)
    public List<TruckingOrder> getTruckingOrderByState() {
        return truckingOrderService.getTruckingOrderByState();
    }

    @RequestMapping(value = "/getTruckingOrderByCondition", method = RequestMethod.GET)
    public List<TruckingOrder> getTruckingOrderByCondition(
            @RequestParam(defaultValue = "") String transportDepartment,
            @RequestParam(defaultValue = "") String user,
            @RequestParam(defaultValue = "") String busNumber,
            @RequestParam(defaultValue = "") Date transportTime,
            @RequestParam(defaultValue = "") String destination,
            @RequestParam(defaultValue = "") String reason,
            @RequestParam(defaultValue = "") String licensePlateNumber,
            @RequestParam(defaultValue = "") String driver,
            @RequestParam(defaultValue = "") Date writeTime,
            @RequestParam(defaultValue = "") String state) {
        return truckingOrderService.getTruckingOrderByCondition(transportDepartment,
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


    @RequestMapping(value = "/updateTruckingOrder", method = RequestMethod.PUT)
    public RespBean updateTruckingOrder(TruckingOrder truckingOrder) {
        if (truckingOrderService.updateTruckingOrder(truckingOrder) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/deleteTruckingOrderById/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteTruckingOrderById(@PathVariable String ids) {
        if (truckingOrderService.deleteTruckingOrderById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/getAllDeps", method = RequestMethod.GET)
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<Employee> getAllUsers() {
        return empService.getAllUsers();
    }
}
