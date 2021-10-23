package blb.clc.bean;

public class OrderDetailsBean {
    private Integer orderDetailsId;
    private String productName;
    private Integer productNum;
    private double productMoney;
    private Integer orderId;


    public OrderDetailsBean() {
    }

    public OrderDetailsBean(Integer orderDetailsId, String productName, Integer productNum, double productMoney, Integer orderId) {
        this.orderDetailsId = orderDetailsId;
        this.productName = productName;
        this.productNum = productNum;
        this.productMoney = productMoney;
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "orderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", productName='" + productName + '\'' +
                ", productNum=" + productNum +
                ", productMoney=" + productMoney +
                ", orderId=" + orderId +
                '}';
    }

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(double productMoney) {
        this.productMoney = productMoney;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
