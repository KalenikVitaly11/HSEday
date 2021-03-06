package org.styleru.hseday2017_2.ApiClasses;



public class ApiSports {
    private Integer id;
    private String name;
    private String description;
    private String imageurl;
    private Float xposition;
    private Float yposition;
    private String shortdesc;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setXposition(Float xposition) {
        this.xposition = xposition;
    }

    public void setYposition(Float yposition) {
        this.yposition = yposition;
    }
}
