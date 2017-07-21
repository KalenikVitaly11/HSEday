package org.styleru.hseday;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HseDayApi {
    @GET("/api/organizations")
    Call<List<ApiOrganisations>> getOrganisations();

    @GET("/api/faculties")
    Call<List<ApiFaculties>> getFaculties();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dayhse.styleru.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
