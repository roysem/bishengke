package blb.clc.bean;

public class ProductBean {
    private Integer productId;
    private String productName;
    private String productPic;
    private double productPrice;
    private String productDescribe;
    private Integer categoryId;
    private String productStatus;
    private String categoryName;

    public ProductBean() {
    }

    public ProductBean(Integer productId, String productName, String productPic, double productPrice, String productDescribe, Integer categoryId, String productStatus, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.productPic = productPic;
        this.productPrice = productPrice;
        this.productDescribe = productDescribe;
        this.categoryId = categoryId;
        this.productStatus = productStatus;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPic='" + productPic + '\'' +
                ", productPrice=" + productPrice +
                ", productDescribe='" + productDescribe + '\'' +
                ", categoryId=" + categoryId +
                ", productStatus='" + productStatus + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}