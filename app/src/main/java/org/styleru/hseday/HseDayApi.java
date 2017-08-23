package org.styleru.hseday;

import org.styleru.hseday.ApiClasses.ApiFaculties;
import org.styleru.hseday.ApiClasses.ApiOrganisations;
import org.styleru.hseday.ApiClasses.ApiQuest;
import org.styleru.hseday.ApiClasses.ApiSports;
import org.styleru.hseday.ApiClasses.ApiTents;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface HseDayApi {
    @GET("/api/organizations")
    Call<List<ApiOrganisations>> getOrganisations();

    @GET("/api/faculties")
    Call<List<ApiFaculties>> getFaculties();

    @GET("/api/quests")
    Call<List<ApiQuest>> getQuests();

    @GET("/api/tents")
    Call<List<ApiTents>> getTents();

    @GET("/api/sports")
    Call<List<ApiSports>> getSports();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dayhse.styleru.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
