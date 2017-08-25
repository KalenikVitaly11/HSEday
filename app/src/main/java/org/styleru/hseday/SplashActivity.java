package org.styleru.hseday;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    FragmentMap FragmentMap;
    public ArrayList<ApiOrganisations> dataOrganisations;
    public ArrayList<ApiFaculties> dataFaculties;
    public ArrayList<ApiQuest> dataQuests;
    public ArrayList<ApiTents> dataTents;
    public ArrayList<ApiMics> dataMics;
    public ArrayList<ApiEvents> dataEvents;
    public ArrayList<ApiSports> dataSports;
    public ArrayList<ApiLectures> dataLectures;
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
                    Toast.makeText(getApplicationContext(), "ОШИБКА В ЗАПРОСЕ", Toast.LENGTH_LONG).show();
                }
            });
        }
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
                    Toast.makeText(getApplicationContext(), "ОШИБКА В ЗАПРОСЕ", Toast.LENGTH_LONG).show();
                }
            });
        }


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

                }
            });

        }

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
                    Toast.makeText(getApplicationContext(), "Запрос тентов не удался", Toast.LENGTH_LONG).show();
                }
            });
        }


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
                    Toast.makeText(getApplicationContext(), "Запрос спортов не удался", Toast.LENGTH_LONG).show();
                }
            });
        }

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
                    Toast.makeText(getApplicationContext(), "ОШИБКА ЛЕКЦИЙ", Toast.LENGTH_SHORT).show();

                }
            });
        }

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
                    Toast.makeText(getApplicationContext(), "ОШИБКА МИКРОФОНОВ", Toast.LENGTH_SHORT).show();

                }
            });
        }

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

                }
            });
        }

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
