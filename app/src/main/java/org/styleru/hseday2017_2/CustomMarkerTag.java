package org.styleru.hseday2017_2;


public class CustomMarkerTag {
    private String pointType;
    private Integer pointId;
    private String name;
    private String info;
    private String imageUrl;
    private int isActive;

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPointType() {
        return pointType;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }
}
