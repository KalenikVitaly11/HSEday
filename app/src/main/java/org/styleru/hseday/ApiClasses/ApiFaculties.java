package org.styleru.hseday.ApiClasses;

/**
 * Created by Виталий on 21.07.2017.
 */

public class ApiFaculties {
    public String name;
    public String description;
    public String contacts;
    public String imageurl;


    @Override
    public String toString() {
        return name + " (" + description + ")";
    }


    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getContacts(){
        return contacts;
    }

    public String getImageurl(){
        return imageurl;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setContacts(String contacts){
        this.contacts = contacts;
    }
    public void setImageurl(String imageurl){
        this.imageurl = imageurl;
    }
}
