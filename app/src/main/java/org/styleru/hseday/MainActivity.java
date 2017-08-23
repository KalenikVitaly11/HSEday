package org.styleru.hseday;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.styleru.hseday.ApiClasses.ApiFaculties;
import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.ApiClasses.ApiQuest;
import org.styleru.hseday.ApiClasses.ApiSports;
import org.styleru.hseday.ApiClasses.ApiTents;
import org.styleru.hseday.NavigationFragments.FragmentAboutHSE;
import org.styleru.hseday.NavigationFragments.FragmentDedication;
import org.styleru.hseday.NavigationFragments.FragmentFaculties;
import org.styleru.hseday.NavigationFragments.FragmentMap;
import org.styleru.hseday.NavigationFragments.FragmentOrganisations;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentFaculties FragmentFaculties;
    FragmentOrganisations FragmentOrganisations;
    org.styleru.hseday.NavigationFragments.FragmentAboutHSE FragmentAboutHSE;

    FragmentDedication FragmentDedication;
    FragmentMap FragmentMap;
    NavigationView navigationView;
    public ArrayList<ApiOrganisations> dataOrganisations;
    public ArrayList<ApiFaculties> dataFaculties;
    public ArrayList<ApiQuest> dataQuests;
    public ArrayList<ApiTents> dataTents;
    public ArrayList<ApiSports> dataSports;
    TextView UserName;
    DataBaseHelper dbHelper;
    ImageView UserImage;

    public static Integer questsPassed = 0;
    public static Integer questsNumber = 20;
    SharedPreferences questCount;

    VKRequest.VKRequestListener mRequestListener;
    SharedPreferences sPref;
    private Handler mHandler = new Handler();
    private String[] scope = new String[]{};
    private final static String FIELDS = "photo, photo_50, photo_100, photo_200";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Карта");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.map);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        View hView = navigationView.getHeaderView(0);

        UserName = (TextView) hView.findViewById(R.id.user_name);
        UserImage = (ImageView) hView.findViewById(R.id.user_image);


        FragmentFaculties = new FragmentFaculties();
        FragmentOrganisations = new FragmentOrganisations();
        FragmentAboutHSE = new FragmentAboutHSE();
        FragmentDedication = new FragmentDedication();
        FragmentMap = new FragmentMap();




        sPref = getPreferences(MODE_PRIVATE);
        if (sPref.getInt("questsPassed", -1) == -1) {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("questsPassed", 0);
            ed.apply();
        }


        dbHelper = new DataBaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor organisationsCursor = database.query(DataBaseHelper.TABLE_ORGANISATIONS_NAME, null, null, null, null, null, null);
        organisationsCursor.moveToFirst();
        if (!organisationsCursor.moveToFirst()) { // Проверяем пустоту базы данных
            //Toast.makeText(getApplicationContext(), "Запрос организаций успешно сделан", Toast.LENGTH_LONG).show();
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
            //Toast.makeText(getApplicationContext(), "Запрос факультетов успешно сделан", Toast.LENGTH_LONG).show();
            // Запрос к ресурсу факультетов
            dataFaculties = new ArrayList<ApiFaculties>();
            //HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            HseDayApi hseDayApi = HseDayApi.retrofit.create(HseDayApi.class);
            Call<List<ApiFaculties>> callFaculties = hseDayApi.getFaculties();
            callFaculties.enqueue(new Callback<List<ApiFaculties>>() { // Запрос к серверу
                @Override
                public void onResponse(Call<List<ApiFaculties>> call, Response<List<ApiFaculties>> response) {
                    //Toast.makeText(getApplicationContext(), "Запрос успешно сделан", Toast.LENGTH_LONG).show();
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    //database.delete(DataBaseHelper.TABLE_FACULTIES_NAME, null, null);
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
            //Toast.makeText(getApplicationContext(), "Запрос квестов успешно сделан", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "ТРИ ТРИ ТРИ", Toast.LENGTH_LONG).show();
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
            //Toast.makeText(getApplicationContext(), "Запрос тентов успешно сделан", Toast.LENGTH_LONG).show();
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
                        //Toast.makeText(getApplicationContext(), dataTents.get(i).getDescription(), Toast.LENGTH_SHORT).show();
                        myContent.put(DataBaseHelper.TENTS_DESCRIPTION, dataTents.get(i).getDescription());
                        myContent.put(DataBaseHelper.TENTS_XCOORDINATE, dataTents.get(i).getXposition());
                        myContent.put(DataBaseHelper.TENTS_YCOORDINATE, dataTents.get(i).getYposition());
                        Toast.makeText(getApplicationContext(), "ДВА ДВА ДВА ", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), "РАЗ ДВА ТРИ", Toast.LENGTH_LONG).show();
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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_map, FragmentMap);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
                if (VKSdk.isLoggedIn()) {
                    if (sharedPref.getString("VKname", "").equals("")) {
                        Toast.makeText(getApplicationContext(), "fillNameIfLoggedIn", Toast.LENGTH_SHORT).show();
                        mHandler.postDelayed(new Runnable() {
                            public void run() {
                                fillNameIfLoggedIn();
                            }
                        }, 2300);
                    } else {
                        Toast.makeText(getApplicationContext(), "sharedPreferences", Toast.LENGTH_SHORT).show();
                        UserName.setText(sharedPref.getString("VKname", ""));
                        Glide.with(getApplicationContext()).load(sharedPref.getString("VKavatar", "")).into(UserImage);
                        navigationView.getMenu().findItem(R.id.nav_login_vk).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                    }
                } else if (isLoggedIn()) {
                    if (sharedPref.getString("FBname", "").equals("")) {
                        Toast.makeText(getApplicationContext(), "fillNameIfLoggedIn", Toast.LENGTH_SHORT).show();
                        mHandler.postDelayed(new Runnable() {
                            public void run() {
                                fillNameIfLoggedIn();
                            }
                        }, 1500);
                    } else {
                        Toast.makeText(getApplicationContext(), "sharedPreferences", Toast.LENGTH_SHORT).show();
                        UserName.setText(sharedPref.getString("FBname", ""));
                        Glide.with(getApplicationContext()).load(sharedPref.getString("FBavatar", "")).into(UserImage);
                        navigationView.getMenu().findItem(R.id.nav_login_vk).setVisible(false);
                        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                    }
                }
            }
        }, 1);
    }

    public void fillNameIfLoggedIn() {
        if (VKSdk.isLoggedIn()) {
            navigationView.getMenu().findItem(R.id.nav_login_vk).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            if (VKSdk.isLoggedIn()) {
                VKRequest profileInfo = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_50, photo_100, photo_200"));
                profileInfo.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        VKList<VKApiUser> userList = (VKList<VKApiUser>) response.parsedModel;
                        VKApiUser user = userList.get(0);
                        UserName.setText(user.first_name + " " + user.last_name);

                        String photo = null;
                        if (!user.photo_200.equals("http://vk.com/images/camera_a.gif"))
                            photo = user.photo_200;
                        else if (!user.photo_100.equals("http://vk.com/images/camera_b.gif"))
                            photo = user.photo_100;
                        else
                            photo = user.photo_50;

                        Glide.with(getApplicationContext()).load(photo).into(UserImage);

                        SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("VKname", user.first_name + " " + user.last_name);
                        editor.putString("VKavatar", photo);
                        editor.apply();
                    }
                });
            }

        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_map) {
            transaction.replace(R.id.content_map, FragmentMap);
        } else if (id == R.id.nav_faculties) {
            transaction.replace(R.id.content_map, FragmentFaculties);
        } else if (id == R.id.nav_organisations) {
            transaction.replace(R.id.content_map, FragmentOrganisations);
        } else if (id == R.id.nav_aboutHSE) {
            transaction.replace(R.id.content_map, FragmentAboutHSE);
        } else if (id == R.id.nav_posvyashenie) {
            transaction.replace(R.id.content_map, FragmentDedication);
        } else if (id == R.id.nav_login_vk) {
            Intent intent = new Intent(this, ActivityLogin.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_logout) {
            VKSdk.logout();
            LoginManager.getInstance().logOut();
            if (!isLoggedIn() && !VKSdk.isLoggedIn()) {
                Toast.makeText(this, "Logged out succesfully", Toast.LENGTH_SHORT).show();
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
                navigationView.getMenu().findItem(R.id.nav_login_vk).setVisible(true);
                UserName.setText("День вышки");
                UserImage.setImageResource(0);
            }
            SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.apply();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }, 350);
        return true;
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
