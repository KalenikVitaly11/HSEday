package org.styleru.hseday2017_2.ApiClasses;

/**
 * Created by Виталий on 25.08.2017.
 */

public class ApiEvents {
    private Integer id;
    private String name;
    private String description;
    private String starttime;
    private String endtime;
    private String pointtype;
    private Integer pointid;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public String getPointtype() {
        return this.pointtype;
    }

    public Integer getPointid() {
        return this.pointid;
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

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setPointtype(String pointtype) {
        this.pointtype = pointtype;
    }

    public void setPointid(Integer pointid) {
        this.pointid = pointid;
    }
}
