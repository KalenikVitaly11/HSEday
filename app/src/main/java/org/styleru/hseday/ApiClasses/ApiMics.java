package org.styleru.hseday.ApiClasses;

/**
 * Created by Виталий on 20.08.2017.
 */

public class ApiMics {
    public String name;
    public String description;
    public Float xposition;
    public Float yposition;

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

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setXposition(Float xposition){
        this.xposition = xposition;
    }
    public void setYposition(Float yposition){
        this.yposition = yposition;
    }
}
