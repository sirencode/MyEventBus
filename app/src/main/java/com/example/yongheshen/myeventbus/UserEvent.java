package com.example.yongheshen.myeventbus;

/**
 * Created by yonghe.shen on 16/7/18.
 */
public class UserEvent {
    private String userName;

    public UserEvent() {
    }

    public UserEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
