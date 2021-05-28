package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Network;

import com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.User;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {
    @POST("/userfoodlist")
     Observable<User> getUserDetails(@Body User user);

    @POST("/uploadfoodtypephoto/<int:foodtype_id>")
    @Multipart
    Observable<ResponseBody> sendPhoto(@Path(value = "foodtype_id", encoded = true) int foodtype_id,
                                       @Part MultipartBody.Part photo,
                                       @Part RequestBody descriptionBody);
}
