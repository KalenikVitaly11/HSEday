package org.styleru.hseday2017_2;

import org.styleru.hseday2017_2.ApiClasses.ApiAboutHSE;
import org.styleru.hseday2017_2.ApiClasses.ApiComments;
import org.styleru.hseday2017_2.ApiClasses.ApiDepartment;
import org.styleru.hseday2017_2.ApiClasses.ApiEvents;
import org.styleru.hseday2017_2.ApiClasses.ApiFaculties;
import org.styleru.hseday2017_2.ApiClasses.ApiLectures;
import org.styleru.hseday2017_2.ApiClasses.ApiMics;
import org.styleru.hseday2017_2.ApiClasses.ApiOrganisations;
import org.styleru.hseday2017_2.ApiClasses.ApiPostComment;
import org.styleru.hseday2017_2.ApiClasses.ApiQuest;
import org.styleru.hseday2017_2.ApiClasses.ApiSports;
import org.styleru.hseday2017_2.ApiClasses.ApiTents;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("/api/lectures")
    Call<List<ApiLectures>> getLectures();

    @GET("/api/mics")
    Call<List<ApiMics>> getMics();

    @GET("/api/events")
    Call<List<ApiEvents>> getEvents();

    @GET("/api/general")
    Call<List<ApiAboutHSE>> getAboutHSE();

    @GET("/api/eduprogrammes")
    Call<List<ApiDepartment>> getDepartments();

    @GET("/api/comments")
    Call<List<ApiComments>> getComments();

    //@FormUrlEncoded
    //@POST("/api/comments/add/text")
    //Call<ResponseBody> postComment(@Body ApiPostComment comment);
    @FormUrlEncoded
    @POST("/api/comments/add/text")
    Call<ResponseBody> postComment(@Field("author") String author, @Field("content") String content, @Field("eventid") int eventid);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://dayhse.styleru.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
