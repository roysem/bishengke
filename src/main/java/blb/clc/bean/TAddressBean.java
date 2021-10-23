package blb.clc.bean;

public class TAddressBean {
    private Integer addressId;
    private String addressProvince;
    private String addressCity;
    private String addressDistrict;
    private String addressDescribe;
    private String userId;
    private Integer isDefault;


    public TAddressBean() {
    }

    public TAddressBean(Integer addressId, String addressProvince, String addressCity, String addressDistrict, String addressDescribe, String userId, Integer isDefault) {
        this.addressId = addressId;
        this.addressProvince = addressProvince;
        this.addressCity = addressCity;
        this.addressDistrict = addressDistrict;
        this.addressDescribe = addressDescribe;
        this.userId = userId;
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "TAddressBean{" +
                "addressId=" + addressId +
                ", addressProvince='" + addressProvince + '\'' +
                ", addressCity='" + addressCity + '\'' +
                ", addressDistrict='" + addressDistrict + '\'' +
                ", addressDescribe='" + addressDescribe + '\'' +
                ", userId='" + userId + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressDescribe() {
        return addressDescribe;
    }

    public void setAddressDescribe(String addressDescribe) {
        this.addressDescribe = addressDescribe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
