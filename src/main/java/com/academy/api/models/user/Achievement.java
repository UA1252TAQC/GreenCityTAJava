package com.academy.api.models.user;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class Achievement {
    private int id;
    private String title;
    private String name;
    private String nameEng;
    private AchievementCategory achievementCategory;
    private String habit;
    private int condition;
    private int progress;

    @Data
    public static class AchievementCategory{
        private int id;
        private String name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
