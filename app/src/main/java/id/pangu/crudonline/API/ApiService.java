package id.pangu.crudonline.API;

import id.pangu.crudonline.Model.ApiResponse;
import id.pangu.crudonline.Model.ResultItem;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ApiResponse> sendBiodata(@Field("nama") String nama,
                                  @Field("usia") String usia,
                                  @Field("domisili") String domisili);

    @GET("read.php")
    Call<ApiResponse> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ApiResponse> updateData( @Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("usia") String usia,
                                   @Field("domisili") String domisili);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ApiResponse> deleteData(@Field("id") String id);
}
