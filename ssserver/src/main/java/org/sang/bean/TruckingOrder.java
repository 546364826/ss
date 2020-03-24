package org.sang.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zwj
 * @className: TruckingOrder 派车单
 * @packageName: org.sang.bean
 * @description:
 * @data: 2020/2/24
 **/
public class TruckingOrder implements Serializable {
    private int id;//主键
    private String transportDepartment;//用车部门
    private String user;//使用人
    private String busNumber;//乘车人数
    private Date transportTime;//用车时间
    private String destination;//目的地
    private String reason;//因由
    private String licensePlateNumber;//可用车辆(车牌号)
    private String driver;//可用司机
    private Date writeTime;//记录时间
    private String state;
    private String comment;//备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransportDepartment() {
        return transportDepartment;
    }

    public void setTransportDepartment(String transportDepartment) {
        this.transportDepartment = transportDepartment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Date getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(Date transportTime) {
        this.transportTime = transportTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
