package org.styleru.hseday;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import org.styleru.hseday.ApiClasses.ApiAboutHSE;
import org.styleru.hseday.ApiClasses.ApiDepartment;
import org.styleru.hseday.ApiClasses.ApiEvents;
import org.styleru.hseday.ApiClasses.ApiFaculties;
import org.styleru.hseday.ApiClasses.ApiLectures;
import org.styleru.hseday.ApiClasses.ApiMics;
import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.ApiClasses.ApiQuest;
import org.styleru.hseday.ApiClasses.ApiSports;
import org.styleru.hseday.ApiClasses.ApiTents;
import org.styleru.hseday.NavigationFragments.FragmentMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    public ArrayList<ApiOrganisations> dataOrganisations;
    public ArrayList<ApiFaculties> dataFaculties;
    public ArrayList<ApiQuest> dataQuests;
    public ArrayList<ApiTents> dataTents;
    public ArrayList<ApiMics> dataMics;
    public ArrayList<ApiEvents> dataEvents;
    public ArrayList<ApiSports> dataSports;
    public ArrayList<ApiLectures> dataLectures;
    public ArrayList<ApiAboutHSE> dataAboutHSE;
    public ArrayList<ApiDepartment> dataDepartments;
    DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor organisationsCursor = database.query(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null, null, null, null, null);
        organisationsCursor.moveToFirst();
        if (!organisationsCursor.moveToFirst()) { // Проверяем пустоту базы данных
            // Запрос к ресурсу организаций
            dataOrganisations = new ArrayList<ApiOrganisations>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiOrganisations>> callOrganisations = hseDayApi.getOrganisations();
            callOrganisations.enqueue(new Callback<List<ApiOrganisations>>() {
                @Override
                public void onResponse(Call<List<ApiOrganisations>> call, Response<List<ApiOrganisations>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    //database.delete(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null);
                    dataOrganisations.addAll(response.body());  // Список с инфой с сервера

                    for (int i = 0; i < dataOrganisations.size(); i++) {  // Загоняем список с инфой с сервера в базу данных организаций
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.ORGANISATION_ID, dataOrganisations.get(i).getId());
                        myContent.put(DataBaseHelper.ORGANISATION_NAME, dataOrganisations.get(i).getName());
                        myContent.put(DataBaseHelper.ORGANISATION_DESCRIPTION, dataOrganisations.get(i).getDescription());
                        myContent.put(DataBaseHelper.ORGANISATION_CONTACTS, dataOrganisations.get(i).getContacts());
                        myContent.put(DataBaseHelper.ORGANISATION_IMAGE_URL, dataOrganisations.get(i).getImageurl());
                        database.insert(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, myContent);
                        myContent.clear();
                    }
                }


                @Override
                public void onFailure(Call<List<ApiOrganisations>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        organisationsCursor.close();
        Cursor facultyCursor = database.query(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null, null, null, null, null);
        facultyCursor.moveToFirst();
        if (!facultyCursor.moveToFirst()) {  // Проверяем пустоту базы данных
            // Запрос к ресурсу факультетов
            dataFaculties = new ArrayList<ApiFaculties>();
            //HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiFaculties>> callFaculties = hseDayApi.getFaculties();
            callFaculties.enqueue(new Callback<List<ApiFaculties>>() { // Запрос к серверу
                @Override
                public void onResponse(Call<List<ApiFaculties>> call, Response<List<ApiFaculties>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataFaculties.addAll(response.body());  // Список с инфой с сервера
                    for (int i = 0; i < dataFaculties.size(); i++) {  // Загоняем список с инфой с сервера в базу данных факультетов
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.FACULTIES_ID, dataFaculties.get(i).getId());
                        myContent.put(DataBaseHelper.FACULTIES_NAME, dataFaculties.get(i).getName());
                        myContent.put(DataBaseHelper.FACULTIES_DESCRIPTION, dataFaculties.get(i).getDescription());
                        myContent.put(DataBaseHelper.FACULTIES_CONTACTS, dataFaculties.get(i).getContacts());
                        myContent.put(DataBaseHelper.FACULTIES_IMAGE_URL, dataFaculties.get(i).getImageurl());
                        database.insert(DataBaseHelper.TABLE_FACULTIES_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiFaculties>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        facultyCursor.close();


        Cursor questCursor = database.query(DataBaseHelper.TABLE_QUESTS_NAME, null, null, null, null, null, null); ////// ЗАПРОС К КВЕСТАМ
        questCursor.moveToFirst();
        if (!questCursor.moveToFirst()) {
            dataQuests = new ArrayList<ApiQuest>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiQuest>> callQuests = hseDayApi.getQuests();

            callQuests.enqueue(new Callback<List<ApiQuest>>() {
                @Override
                public void onResponse(Call<List<ApiQuest>> call, Response<List<ApiQuest>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataQuests.addAll(response.body());
                    for (int i = 0; i < dataQuests.size(); i++) {
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.QUESTS_ID, dataQuests.get(i).getId());
                        myContent.put(DataBaseHelper.QUESTS_NUMBER, dataQuests.get(i).getNumber());
                        myContent.put(DataBaseHelper.QUESTS_NAME, dataQuests.get(i).getName());
                        myContent.put(DataBaseHelper.QUESTS_DESCRIPTION, dataQuests.get(i).getDescription());
                        myContent.put(DataBaseHelper.QUESTS_PASSCODE, dataQuests.get(i).getPasscode());
                        myContent.put(DataBaseHelper.QUESTS_IMAGE_URL, dataQuests.get(i).getImageurl());
                        myContent.put(DataBaseHelper.QUESTS_XCOORDINATE, dataQuests.get(i).getXposition());
                        myContent.put(DataBaseHelper.QUESTS_YCOORDINATE, dataQuests.get(i).getYposition());
                        database.insert(DataBaseHelper.TABLE_QUESTS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiQuest>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });

        }
        questCursor.close();

        Cursor tentsCursor = database.query(DataBaseHelper.TABLE_TENTS_NAME, null, null, null, null, null, null); ////// ЗАПРОС К ТЕНТАМ
        tentsCursor.moveToFirst();
        if (!tentsCursor.moveToFirst()) {
            dataTents = new ArrayList<ApiTents>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiTents>> callTents = hseDayApi.getTents();

            callTents.enqueue(new Callback<List<ApiTents>>() {
                @Override
                public void onResponse(Call<List<ApiTents>> call, Response<List<ApiTents>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataTents.addAll(response.body());
                    for (int i = 0; i < dataTents.size(); i++) {
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.TENTS_ID, dataTents.get(i).getId());
                        myContent.put(DataBaseHelper.TENTS_NAME, dataTents.get(i).getName());
                        myContent.put(DataBaseHelper.TENTS_DESCRIPTION, dataTents.get(i).getDescription());
                        myContent.put(DataBaseHelper.TENTS_XCOORDINATE, dataTents.get(i).getXposition());
                        myContent.put(DataBaseHelper.TENTS_YCOORDINATE, dataTents.get(i).getYposition());
                        database.insert(DataBaseHelper.TABLE_TENTS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiTents>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        tentsCursor.close();


        Cursor sportsCursor = database.query(DataBaseHelper.TABLE_SPORTS_NAME, null, null, null, null, null, null);
        sportsCursor.moveToFirst();
        if(!sportsCursor.moveToFirst()){
            dataSports = new ArrayList<ApiSports>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiSports>> callSports = hseDayApi.getSports();
            callSports.enqueue(new Callback<List<ApiSports>>() {
                @Override
                public void onResponse(Call<List<ApiSports>> call, Response<List<ApiSports>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataSports.addAll(response.body());
                    for(int i = 0;i < dataSports.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.SPORTS_ID, dataSports.get(i).getId());
                        myContent.put(DataBaseHelper.SPORTS_NAME, dataSports.get(i).getName());
                        myContent.put(DataBaseHelper.SPORTS_DESCRIPTION, dataSports.get(i).getDescription());
                        myContent.put(DataBaseHelper.SPORTS_IMAGE_URL, dataSports.get(i).getImageurl());
                        myContent.put(DataBaseHelper.SPORTS_XCOORDINATE, dataSports.get(i).getXposition());
                        myContent.put(DataBaseHelper.SPORTS_YCOORDINATE, dataSports.get(i).getYposition());
                        database.insert(DataBaseHelper.TABLE_SPORTS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiSports>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        sportsCursor.close();

        Cursor lecturesCursor = database.query(DataBaseHelper.TABLE_LECTIONS_NAME, null, null, null, null, null, null);
        lecturesCursor.moveToFirst();
        if(!lecturesCursor.moveToFirst()){
            dataLectures = new ArrayList<ApiLectures>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiLectures>> callLectures = hseDayApi.getLectures();
            callLectures.enqueue(new Callback<List<ApiLectures>>() {
                @Override
                public void onResponse(Call<List<ApiLectures>> call, Response<List<ApiLectures>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataLectures.addAll(response.body());
                    for(int i = 0; i < dataLectures.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.LECTURES_ID, dataLectures.get(i).getId());
                        myContent.put(DataBaseHelper.LECTURES_NAME, dataLectures.get(i).getName());
                        myContent.put(DataBaseHelper.LECTURES_DESCRIPTION, dataLectures.get(i).getDescription());
                        myContent.put(DataBaseHelper.LECTURES_XCOORDINATE, dataLectures.get(i).getXposition());
                        myContent.put(DataBaseHelper.LECTURES_YCOORDINATE, dataLectures.get(i).getYposition());
                        database.insert(DataBaseHelper.TABLE_LECTIONS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiLectures>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись, пожалуйста, и перезапусти приложение", Toast.LENGTH_SHORT).show();

                }
            });
        }
        lecturesCursor.close();

        final Cursor micsCursor = database.query(DataBaseHelper.TABLE_MICROPHONES_NAME, null, null, null, null, null, null);
        micsCursor.moveToFirst();
        if(!micsCursor.moveToFirst()){
            dataMics = new ArrayList<ApiMics>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiMics>> callMics = hseDayApi.getMics();
            callMics.enqueue(new Callback<List<ApiMics>>() {
                @Override
                public void onResponse(Call<List<ApiMics>> call, Response<List<ApiMics>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataMics.addAll(response.body());
                    for(int i = 0;i < dataMics.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.MICROPHONES_ID, dataMics.get(i).getId());
                        myContent.put(DataBaseHelper.MICROPHONES_NAME, dataMics.get(i).getName());
                        myContent.put(DataBaseHelper.MICROPHONES_DESCRIPTION, dataMics.get(i).getDescription());
                        myContent.put(DataBaseHelper.MICROPHONES_XCOORDINATE, dataMics.get(i).getXposition());
                        myContent.put(DataBaseHelper.MICROPHONES_YCOORDINATE, dataMics.get(i).getYposition());
                        database.insert(DataBaseHelper.TABLE_MICROPHONES_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiMics>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        micsCursor.close();

        Cursor eventsCursor = database.query(DataBaseHelper.TABLE_EVENTS_NAME, null, null, null, null, null, null);
        eventsCursor.moveToFirst();
        if(!eventsCursor.moveToFirst()){
            dataEvents = new ArrayList<ApiEvents>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiEvents>> callEvents = hseDayApi.getEvents();
            callEvents.enqueue(new Callback<List<ApiEvents>>() {
                @Override
                public void onResponse(Call<List<ApiEvents>> call, Response<List<ApiEvents>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataEvents.addAll(response.body());
                    for(int i = 0;i < dataEvents.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.EVENTS_ID, dataEvents.get(i).getId());
                        myContent.put(DataBaseHelper.EVENTS_NAME, dataEvents.get(i).getName());
                        myContent.put(DataBaseHelper.EVENTS_DESCRIPTION, dataEvents.get(i).getDescription());
                        myContent.put(DataBaseHelper.EVENTS_STARTTIME, dataEvents.get(i).getStarttime());
                        myContent.put(DataBaseHelper.EVENTS_ENDTIME, dataEvents.get(i).getEndtime());
                        myContent.put(DataBaseHelper.EVENTS_POINTTYPE, dataEvents.get(i).getPointtype());
                        myContent.put(DataBaseHelper.EVENTS_POINTID, dataEvents.get(i).getPointid());
                        database.insert(DataBaseHelper.TABLE_EVENTS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiEvents>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        eventsCursor.close();

        Cursor abouthseCursor = database.query(DataBaseHelper.TABLE_ABOUT_HSE_NAME, null, null, null, null, null, null);
        abouthseCursor.moveToFirst();
        if(!abouthseCursor.moveToFirst()){
            dataAboutHSE = new ArrayList<ApiAboutHSE>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiAboutHSE>> callAboutHSE = hseDayApi.getAboutHSE();
            callAboutHSE.enqueue(new Callback<List<ApiAboutHSE>>() {
                @Override
                public void onResponse(Call<List<ApiAboutHSE>> call, Response<List<ApiAboutHSE>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataAboutHSE.addAll(response.body());
                    for(int i = 0;i < dataAboutHSE.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.ABOUT_HSE_NAME, dataAboutHSE.get(i).getName());
                        myContent.put(DataBaseHelper.ABOUT_HSE_DESCRIPTION, dataAboutHSE.get(i).getDescription());
                        myContent.put(DataBaseHelper.ABOUT_HSE_CONTACTS, dataAboutHSE.get(i).getContacts());
                        myContent.put(DataBaseHelper.ABOUT_HSE_IMAGE_URL, dataAboutHSE.get(i).getImageurl());
                        myContent.put(DataBaseHelper.ABOUT_HSE_CODE, dataAboutHSE.get(i).getCode());
                        database.insert(DataBaseHelper.TABLE_ABOUT_HSE_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiAboutHSE>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Cursor departmentsCursor = database.query(DataBaseHelper.TABLE_DEPARTMENTS_NAME, null, null, null, null, null, null);
        departmentsCursor.moveToFirst();
        if(!departmentsCursor.moveToFirst()){
            dataDepartments = new ArrayList<ApiDepartment>();
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiDepartment>> callDepartments = hseDayApi.getDepartments();
            callDepartments.enqueue(new Callback<List<ApiDepartment>>() {
                @Override
                public void onResponse(Call<List<ApiDepartment>> call, Response<List<ApiDepartment>> response) {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dataDepartments.addAll(response.body());
                    for(int i = 0;i < dataDepartments.size(); i++){
                        ContentValues myContent = new ContentValues();
                        myContent.put(DataBaseHelper.DEPARTMENTS_ID, dataDepartments.get(i).getId());
                        myContent.put(DataBaseHelper.DEPARTMENTS_NAME, dataDepartments.get(i).getName());
                        Toast.makeText(getApplicationContext(), String.valueOf(dataDepartments.get(i).getFacultyId()), Toast.LENGTH_LONG).show();
                        myContent.put(DataBaseHelper.DEPARTMENTS_FACULTY_ID, dataDepartments.get(i).getFacultyId());
                        database.insert(DataBaseHelper.TABLE_DEPARTMENTS_NAME, null, myContent);
                        myContent.clear();
                    }
                }

                @Override
                public void onFailure(Call<List<ApiDepartment>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Требуется подключение к интернету, подключись пожалуйста и перезапусти приложение", Toast.LENGTH_SHORT).show();
                }
            });
        }
        abouthseCursor.close();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }
}
