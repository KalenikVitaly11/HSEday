package org.styleru.hseday2017_2.ApiClasses;

/**
 * Created by Виталий on 30.08.2017.
 */

public class ApiDepartment {
    private int id;
    private String name;
    private int faculty;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFacultyId() {
        return faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacultyId(int facultyId) {
        this.faculty = facultyId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
