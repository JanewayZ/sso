package com.sso.entity;


public class JsonRootBean {

   private UserInfo data;
   private int status;
   private String message;
   public void setData(UserInfo data) {
        this.data = data;
    }
    public UserInfo getData() {
        return data;
    }

   public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

   public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}