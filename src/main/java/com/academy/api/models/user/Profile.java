package com.academy.api.models.user;

import java.util.ArrayList;

public class Profile {
    public Object profilePicturePath;
    public String name;
    public Object userCredo;
    public ArrayList<Object> socialNetworks;
    public boolean showLocation;
    public boolean showEcoPlace;
    public boolean showShoppingList;
    public int rating;
    public String role;
    public Object userLocationDto;
    public ArrayList<NotificationPreference> notificationPreferences;
}
