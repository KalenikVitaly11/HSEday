package org.styleru.hseday2017_2.ApiClasses;

/**
 * Created by Виталий on 29.08.2017.
 */

public class ApiAboutHSE {
    private String name;
    private String description;
    private String imageurl;
    private String contacts;
    private String code;

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
