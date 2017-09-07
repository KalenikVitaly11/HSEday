package org.styleru.hseday2017_2.ApiClasses;

/**
 * Created by Виталий on 18.08.2017.
 */

public class ApiQuest {
    public Integer id;
    public String number;
    public String name;
    public String description;
    public String imageurl;
    public String passcode;
    public int passed;
    public Float xposition;
    public Float yposition;
    public String shortdesc;

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNumber() {
        return number;
    }

    public String getPasscode() {
        return passcode;
    }

    public String getImageurl() {
        return imageurl;
    }

    public Float getXposition() {
        return xposition;
    }

    public Float getYposition() {
        return yposition;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public void setXposition(Float xposition) {
        this.xposition = xposition;
    }

    public void setYposition(Float yposition) {
        this.yposition = yposition;
    }

}
