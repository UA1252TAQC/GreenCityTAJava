package com.academy.api.models.user;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class ErrorAchievement{

    private String timeStamp;
    private int status;
    private String error;
    private String path;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
