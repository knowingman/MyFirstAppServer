package com.hb.app.entity;

/**
 * 创建者 ： HouBin
 * 时间   ： 2016/10/6
 * 项目   :  FirstAppServer
 * 功能   ：
 */
public class UserEntity {
    private int uid;
    private String userName;
    private String userNickname;
    private int userSex;
    private String userPhoto;
    private int userLevel;
    private String userSignature;
    private int userAge;
    private int isRealNameAuthentication;
    private String userBackup;
    private String userTel;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getIsRealNameAuthentication() {
        return isRealNameAuthentication;
    }

    public void setIsRealNameAuthentication(int isRealNameAuthentication) {
        this.isRealNameAuthentication = isRealNameAuthentication;
    }

    public String getUserBackup() {
        return userBackup;
    }

    public void setUserBackup(String userBackup) {
        this.userBackup = userBackup;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userSex=" + userSex +
                ", userPhoto='" + userPhoto + '\'' +
                ", userLevel=" + userLevel +
                ", userSignature='" + userSignature + '\'' +
                ", userAge=" + userAge +
                ", isRealNameAuthentication=" + isRealNameAuthentication +
                ", userBackup='" + userBackup + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
