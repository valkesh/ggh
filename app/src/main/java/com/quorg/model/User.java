/**
 * @author valkesh patel
 */
package com.quorg.model;

public class User
{
   private String email,password,userid;

    public User(String email, String password,String userid) {
        this.email = email;
        this.password = password;
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
