package org.styleru.hseday2017_2.ApiClasses;

/**
 * Created by Виталий on 20.08.2017.
 */

public class ApiTents {
    public Integer id;
    public String name;
    public String description;
    public Float xposition;
    public Float yposition;
    public String shortdesc;
    public int isactive;

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setXposition(Float xposition) {
        this.xposition = xposition;
    }

    public void setYposition(Float yposition) {
        this.yposition = yposition;
    }
}
