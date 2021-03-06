package org.styleru.hseday2017_2;

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
    public static final String ORGANISATION_IMAGE_URL = "organisation_image_url";
    public static final String ORGANISATION_CONTACTS = "organisation_contacts";

    private static final String CREATE_TABLE_ORGANISATIONS = "create table "
            + TABLE_ORGANISATIONS_NAME
            + " (" + ORGANISATION_ID + " integer,"
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
    public static final String FACULTIES_IMAGE_URL = "faculty_image_url";
    public static final String FACULTIES_CONTACTS = "faculty_contacts";

    private static final String CREATE_TABLE_FACULTIES = "create table "
            + TABLE_FACULTIES_NAME
            + " (" + FACULTIES_ID + " integer,"
            + FACULTIES_NAME + " text,"
            + FACULTIES_DESCRIPTION + " text,"
            + FACULTIES_IMAGE_URL + " text,"
            + FACULTIES_CONTACTS + " text"
            + ");";


    // База данных квестов
    public static final String TABLE_QUESTS_NAME = "quests";
    public static final int TABLE_QUESTS_VERSION = 1;

    public static final String QUESTS_ID = "id";
    public static final String QUESTS_NAME = "quests_name";
    public static final String QUESTS_DESCRIPTION = "quests_description";
    public static final String QUESTS_SHORT_DESCRIPTION = "quests_short_description";
    public static final String QUESTS_NUMBER = "quests_number";
    public static final String QUESTS_PASSCODE = "quests_passcode";
    public static final String QUESTS_PASSED = "quests_passed";
    public static final String QUESTS_IMAGE_URL = "quest_image_url";
    public static final String QUESTS_XCOORDINATE = "quests_xcoordinate";
    public static final String QUESTS_YCOORDINATE = "quests_ycoordinate";

    private static final String CREATE_TABLE_QUESTS = "create table "
            + TABLE_QUESTS_NAME
            + " (" + QUESTS_ID + " integer,"
            + QUESTS_NUMBER + " text,"
            + QUESTS_NAME + " text,"
            + QUESTS_DESCRIPTION + " text,"
            + QUESTS_SHORT_DESCRIPTION + " text,"
            + QUESTS_PASSCODE + " text,"
            + QUESTS_IMAGE_URL + " text,"
            + QUESTS_PASSED + " integer default 0,"
            + QUESTS_XCOORDINATE + " real,"
            + QUESTS_YCOORDINATE + " real"
            + ");";


    // База данных тентов
    public static final String TABLE_TENTS_NAME = "tents";
    public static final int TABLE_TENTS_VERSION = 1;

    public static final String TENTS_ID = "id";
    public static final String TENTS_NAME = "tents_name";
    public static final String TENTS_DESCRIPTION = "tents_description";
    public static final String TENTS_SHORT_DESCRIPTION = "tents_short_description";
    public static final String TENTS_ISACTIVE = "tents_isactive";
    public static final String TENTS_XCOORDINATE = "tents_xcoordinate";
    public static final String TENTS_YCOORDINATE = "tents_ycoordinate";

    private static final String CREATE_TABLE_TENTS = "create table "
            + TABLE_TENTS_NAME
            + " (" + TENTS_ID + " integer,"
            + TENTS_NAME + " text,"
            + TENTS_DESCRIPTION + " text,"
            + TENTS_SHORT_DESCRIPTION + " text,"
            + TENTS_ISACTIVE + " text,"
            + TENTS_XCOORDINATE + " real,"
            + TENTS_YCOORDINATE + " real"
            + ");";

    // База данных спортивных мероприятий (мячей)
    public static final String TABLE_SPORTS_NAME = "sports";
    public static final int TABLE_SPORTS_VERSION = 1;

    public static final String SPORTS_ID = "id";
    public static final String SPORTS_NAME = "sports_name";
    public static final String SPORTS_DESCRIPTION = "sports_description";
    public static final String SPORTS_SHORT_DESCRIPTION = "sports_short_description";
    public static final String SPORTS_IMAGE_URL = "sports_image_url";
    public static final String SPORTS_XCOORDINATE = "sports_xcoordinate";
    public static final String SPORTS_YCOORDINATE = "sports_ycoordinate";

    private static final String CREATE_TABLE_SPORTS = "create table "
            + TABLE_SPORTS_NAME
            + " (" + SPORTS_ID + " integer,"
            + SPORTS_NAME + " text,"
            + SPORTS_SHORT_DESCRIPTION + " text,"
            + SPORTS_DESCRIPTION + " text,"
            + SPORTS_IMAGE_URL + " text,"
            + SPORTS_XCOORDINATE + " real,"
            + SPORTS_YCOORDINATE + " real"
            + ");";


    // База данных лекций (желтых листочков)
    public static final String TABLE_LECTIONS_NAME = "lections";
    public static final int TABLE_LECTIONS_VERSION = 1;

    public static final String LECTURES_ID = "id";
    public static final String LECTURES_NAME = "lections_name";
    public static final String LECTURES_DESCRIPTION = "lections_description";;
    public static final String LECTURES_SHORT_DESCRIPTION = "lections_short_description";
    public static final String LECTURES_ISACTIVE = "lections_isactive";
    public static final String LECTURES_XCOORDINATE = "lections_xcoordinate";
    public static final String LECTURES_YCOORDINATE = "lections_ycoordinate";

    private static final String CREATE_TABLE_LECTIONS = "create table "
            + TABLE_LECTIONS_NAME
            + " (" + LECTURES_ID + " integer,"
            + LECTURES_NAME + " text,"
            + LECTURES_DESCRIPTION + " text,"
            + LECTURES_SHORT_DESCRIPTION + " text,"
            + LECTURES_ISACTIVE + " text,"
            + LECTURES_XCOORDINATE + " real,"
            + LECTURES_YCOORDINATE + " real"
            + ");";

    // Базы данных микрофонов
    public static final String TABLE_MICROPHONES_NAME = "microphones";
    public static final int TABLE_MICROPHONES_VERSION = 1;

    public static final String MICROPHONES_ID = "id";
    public static final String MICROPHONES_NAME = "microphones_name";
    public static final String MICROPHONES_DESCRIPTION = "microphones_description";
    public static final String MICROPHONES_SHORT_DESCRIPTION = "microphones_short_description";
    public static final String MICROPHONES_ISACTIVE = "microphones_isactive";
    public static final String MICROPHONES_XCOORDINATE = "microphones_xcoordinate";
    public static final String MICROPHONES_YCOORDINATE = "microphones_ycoordinate";

    private static final String CREATE_TABLE_MICROPHONES = "create table "
            + TABLE_MICROPHONES_NAME
            + " (" + MICROPHONES_ID + " integer,"
            + MICROPHONES_NAME + " text,"
            + MICROPHONES_DESCRIPTION + " text,"
            + MICROPHONES_SHORT_DESCRIPTION + " text,"
            + MICROPHONES_ISACTIVE + " text,"
            + MICROPHONES_XCOORDINATE + " real,"
            + MICROPHONES_YCOORDINATE + " real"
            + ");";

    // Таблица всех событий
    public static final String TABLE_EVENTS_NAME = "events";
    public static final int TABLE_EVENTS_VERSION = 1;
    public static final String EVENTS_ID = "id";
    public static final String EVENTS_NAME = "events_name";
    public static final String EVENTS_DESCRIPTION = "events_description";
    public static final String EVENTS_STARTTIME = "events_starttime";
    public static final String EVENTS_ENDTIME = "events_endtime";
    public static final String EVENTS_POINTTYPE = "events_pointtype";
    public static final String EVENTS_POINTID = "events_pointid";

    private static final String CREATE_TABLE_EVENTS = "create table "
            + TABLE_EVENTS_NAME
            + " (" + EVENTS_ID + " integer,"
            + EVENTS_NAME + " text,"
            + EVENTS_DESCRIPTION + " text,"
            + EVENTS_STARTTIME + " text,"
            + EVENTS_ENDTIME + " text,"
            + EVENTS_POINTTYPE + " text,"
            + EVENTS_POINTID + " integer"
            + ");";
    // Таблица о ВШЭ
    public static final String TABLE_ABOUT_HSE_NAME = "about_hse";
    public static final int TABLE_ABOUT_HSE_VERSION = 1;
    private static final String ABOUT_HSE_ID = "about_hse_id";
    public static final String ABOUT_HSE_NAME = "about_hse_name";
    public static final String ABOUT_HSE_DESCRIPTION = "about_hse_description";
    public static final String ABOUT_HSE_IMAGE_URL = "about_hse_image_url";
    public static final String ABOUT_HSE_CONTACTS = "about_hse_contacts";
    public static final String ABOUT_HSE_CODE =  "about_hse_code";

    private static final String CREATE_TABLE_ABOUT_HSE = "create table "
            + TABLE_ABOUT_HSE_NAME
            + " (" + ABOUT_HSE_ID + " integer,"
            + ABOUT_HSE_NAME + " text,"
            + ABOUT_HSE_DESCRIPTION + " text,"
            + ABOUT_HSE_CONTACTS + " text,"
            + ABOUT_HSE_IMAGE_URL + " text,"
            + ABOUT_HSE_CODE + " text"
            + ");";
    // Таблица образовательных организаций
    public static final String TABLE_DEPARTMENTS_NAME = "departments";
    public static final int TABLE_DEPARTMENTS_VERSION = 1;
    public static final String DEPARTMENTS_ID = "department_id";
    public static final String DEPARTMENTS_NAME = "department_name";
    public static final String DEPARTMENTS_FACULTY_ID = "department_faculty_id";

    private static final String CREATE_TABLE_DEPARTMENTS = "create table "
            + TABLE_DEPARTMENTS_NAME
            + " (" + DEPARTMENTS_ID + " integer,"
            + DEPARTMENTS_NAME + " text,"
            + DEPARTMENTS_FACULTY_ID + " integer"
            + ");";

    public static final String TABLE_COMMENTS_NAME = "comments";
    public static final int TABLE_COMMENTS_VERSION = 1;
    public static final String COMMENTS_ID = "comments_id";
    public static final String COMMENTS_EVENT_ID = "comments_event_id";
    public static final String COMMENTS_CONTENT = "comments_content";
    public static final String COMMENTS_AUTHOR = "comments_author";
    public static final String COMMENTS_TIME = "comments_time";
    public static final String COMMENTS_IMAGE_URL = "comments_image_url";
    public static final String COMMENTS_TYPE= "comments_type";

    private static final String CREATE_TABLE_COMMENTS = "create table "
            + TABLE_COMMENTS_NAME
            + " (" + COMMENTS_ID + " integer,"
            + COMMENTS_CONTENT + " text,"
            + COMMENTS_AUTHOR + " text,"
            + COMMENTS_TIME + " text,"
            + COMMENTS_EVENT_ID + " integer,"
            + COMMENTS_TYPE + " text,"
            + COMMENTS_IMAGE_URL + " text"
            + ");";

    public DataBaseHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ORGANISATIONS);
        db.execSQL(CREATE_TABLE_FACULTIES);
        db.execSQL(CREATE_TABLE_QUESTS);
        db.execSQL(CREATE_TABLE_TENTS);
        db.execSQL(CREATE_TABLE_SPORTS);
        db.execSQL(CREATE_TABLE_MICROPHONES);
        db.execSQL(CREATE_TABLE_LECTIONS);
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_ABOUT_HSE);
        db.execSQL(CREATE_TABLE_DEPARTMENTS);
        db.execSQL(CREATE_TABLE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}