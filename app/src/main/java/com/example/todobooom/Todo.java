package com.example.todobooom;
import java.util.Calendar;
import java.util.Date;

import java.util.UUID;

public class Todo {
    private String content ;
    private Boolean is_done;
    private Date creation_timestamp;
    private Date edit_timestamp;
    private String id;

    public Todo(){}

    public Todo(String description, Boolean isDone){
        this.content  = description;
        this.is_done = isDone;
        this.id = UUID.randomUUID().toString();
        this.creation_timestamp = Calendar.getInstance().getTime();
        this.edit_timestamp = creation_timestamp;
    }
    public void set_edit_time_stamp()
    {
        this.edit_timestamp = Calendar.getInstance().getTime();
    }

    public Boolean getIs_done() {
        return is_done;
    }

    public Date getCreation_timestamp() {
        return creation_timestamp;
    }

    public Date getEdit_timestamp() {
        return edit_timestamp;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setIs_done(Boolean is_done) {
        this.is_done = is_done;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEdit_timestamp(Date edit_timestamp) {
        this.edit_timestamp = edit_timestamp;
    }

}

