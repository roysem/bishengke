package blb.clc.bean;

/**
 * t_user表封装类
 * @author Administrator
 *
 */
public class UserBean{
    private Integer userId;
    private String userTel;
    private String userPwd;
    private String userName;
    private String userSex;
    private String addTime;
    private String userStatus;
    private String userRole;

    private String beginTime;
    private String endTime;

    public UserBean() {
        super();
    }

    public UserBean(String userTel, String userPwd, String userName, String userSex, String userStatus, String userRole) {
        this.userTel = userTel;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userSex = userSex;
        this.userStatus = userStatus;
        this.userRole = userRole;
    }

    public UserBean(Integer userId, String userTel, String userPwd, String userName, String userSex, String addTime,
                    String userStatus, String userRole, String beginTime, String endTime) {
        super();
        this.userId = userId;
        this.userTel = userTel;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userSex = userSex;
        this.addTime = addTime;
        this.userStatus = userStatus;
        this.userRole = userRole;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userTel=" + userTel + ", userPwd=" + userPwd + ", userName=" + userName
                + ", userSex=" + userSex + ", addTime=" + addTime + ", userStatus=" + userStatus + ", userRole="
                + userRole + ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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



}
