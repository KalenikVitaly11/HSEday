package org.styleru.hseday2017_2.ApiClasses;

public class ApiOrganisations {
    private Integer id;
    private String name;
    private String description;
    private String contacts;
    private String imageurl;

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}