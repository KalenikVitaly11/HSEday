package org.styleru.hseday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiOrganisations {
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