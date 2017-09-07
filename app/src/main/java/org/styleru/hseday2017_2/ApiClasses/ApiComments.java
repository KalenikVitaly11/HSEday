package org.styleru.hseday2017_2.ApiClasses;


public class ApiComments {
    public int id;
    public int eventid;
    public String author;
    public String content;
    public String time;
    public String imageurl;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getEventid() {
        return eventid;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTime() {
        return time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
