package org.styleru.hseday.ApiClasses;

/**
 * Created by Виталий on 29.08.2017.
 */

public class ApiAboutHSE {
    public String name;
    public String description;
    public String imageurl;
    public String contacts;
    public String code;

    public String getCode() {
        return code;
    }

    public String getContacts() {
        return contacts;
    }

    public String getDescription() {
        return description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setName(String name) {
        this.name = name;
    }

}