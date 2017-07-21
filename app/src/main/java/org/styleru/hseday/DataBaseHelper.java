package org.styleru.hseday;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hsedayDB";

    // База данных организаций
    public static final String TABLE_ORGANISATIONS_NAME = "organisations";
    public static final int TABLE_ORGANISATIONS_VERSION = 1;

    public static final String ORGANISATION_ID = "id";
    public static final String ORGANISATION_NAME = "organisation_name";
    public static final String ORGANISATION_DESCRIPTION = "organisation_description";
    public static final String ORGANISATION_IMAGE_URL= "organisation_image_url";
    public static final String ORGANISATION_CONTACTS= "organisation_contacts";

    private static final String CREATE_TABLE_ORGANISATIONS = "create table "
            + TABLE_ORGANISATIONS_NAME
            + " (" + ORGANISATION_ID + " integer primary key autoincrement,"
            + ORGANISATION_NAME + " text,"
            + ORGANISATION_DESCRIPTION + " text,"
            + ORGANISATION_IMAGE_URL + " text,"
            + ORGANISATION_CONTACTS + " text"
            + ");";


    // База данных факультетов
    public static final String TABLE_FACULTIES_NAME = "faculties";
    public static final int TABLE_FACULTIES_VERSION = 1;

    public static final String FACULTIES_ID = "id";
    public static final String FACULTIES_NAME = "faculty_name";
    public static final String FACULTIES_DESCRIPTION = "faculty_description";
    public static final String FACULTIES_IMAGE_URL= "faculty_image_url";
    public static final String FACULTIES_CONTACTS= "faculty_contacts";

    private static final String CREATE_TABLE_FACULTIES = "create table "
            + TABLE_FACULTIES_NAME
            + " (" + FACULTIES_ID + " integer primary key autoincrement,"
            + FACULTIES_NAME + " text,"
            + FACULTIES_DESCRIPTION + " text,"
            + FACULTIES_IMAGE_URL + " text,"
            + FACULTIES_CONTACTS + " text"
            + ");";



    public DataBaseHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ORGANISATIONS);
        //db.execSQL(CREATE_TABLE_FACULTIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}