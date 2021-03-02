package com.alkhair.Models;


public class CommentRequestModel {


    /**
     * Email : s.sankari@e.net.kw
     * MessageDetails : From Test server- Kuwaitalkhair - post request
     * Name : shankari from test
     */

    private String Email;
    private String MessageDetails;
    private String Name;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMessageDetails() {
        return MessageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        MessageDetails = messageDetails;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
