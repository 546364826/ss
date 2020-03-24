package org.sang.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zwj
 * @className: Car
 * @packageName: org.sang.bean
 * @description:
 * @data: 2020/2/10
 **/
public class Car implements Serializable {
    private int id;//主键
    private String vehicleType;//车辆类型
    private String licensePlateNumber;//车牌号
    private String seating;//座位数
    private String speedometer;//里程表
    private String manufacturer;//生产厂家
    private String state;//状态
    private String brandName;//品牌名称
    private String engineNo;//发动机号
    private Date purchaseTime;//购置时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public String getSpeedometer() {
        return speedometer;
    }

    public void setSpeedometer(String speedometer) {
        this.speedometer = speedometer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
