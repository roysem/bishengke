package blb.clc.bean;

import java.util.List;

public class OrderBean {
    private Integer orderId;
    private String addTime;
    private String updateTime;
    private Integer userId;
    private String addressDetails;
    private Integer orderType;
    private String userName;
    private List<OrderDetailsBean> orderDetailsBeans;
    private double count;

    private String beginTime;
    private String endTime;


    public OrderBean() {
    }

    public OrderBean(Integer orderId, String addTime, String updateTime, Integer userId, String addressDetails, Integer orderType, String userName, List<OrderDetailsBean> orderDetailsBeans, String beginTime, String endTime) {
        this.orderId = orderId;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.addressDetails = addressDetails;
        this.orderType = orderType;
        this.userName = userName;
        this.orderDetailsBeans = orderDetailsBeans;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId=" + orderId +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId=" + userId +
                ", addressDetails='" + addressDetails + '\'' +
                ", orderType=" + orderType +
                ", userName='" + userName + '\'' +
                ", orderDetailsBeans=" + orderDetailsBeans +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderDetailsBean> getOrderDetailsBeans() {
        return orderDetailsBeans;
    }

    public void setOrderDetailsBeans(List<OrderDetailsBean> orderDetailsBeans) {
        this.orderDetailsBeans = orderDetailsBeans;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
