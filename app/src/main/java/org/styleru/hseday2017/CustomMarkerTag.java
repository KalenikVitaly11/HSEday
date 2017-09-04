package org.styleru.hseday2017;


public class CustomMarkerTag {
    public String pointType;
    public Integer pointId;
    public String lectureName;
    public String lectureInfo;
    public String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLectureInfo() {
        return lectureInfo;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureInfo(String lectureInfo) {
        this.lectureInfo = lectureInfo;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
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
