package org.styleru.hseday2017_2.ApiClasses;


class ApiPostComment {
    private int eventid;
    private String author;
    private String content;

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEventid() {
        return eventid;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
