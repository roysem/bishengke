package blb.clc.bean;

public class CartBean {
    private Integer cartId;
    private Integer productId;
    private Integer productNum;
    private Integer userId;
    private String productName;
    private String productPic;
    private Double productPrice;

    public CartBean() {
    }

    public CartBean(Integer cartId, Integer productId, Integer productNum, Integer userId, String productName, String productPic, Double productPrice) {
        this.cartId = cartId;
        this.productId = productId;
        this.productNum = productNum;
        this.userId = userId;
        this.productName = productName;
        this.productPic = productPic;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                ", userId=" + userId +
                ", productName='" + productName + '\'' +
                ", productPic='" + productPic + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
