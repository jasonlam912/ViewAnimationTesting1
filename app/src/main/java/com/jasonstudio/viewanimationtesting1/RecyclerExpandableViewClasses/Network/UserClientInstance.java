package com.jasonstudio.viewanimationtesting1.RecyclerExpandableViewClasses.Network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClientInstance {
    private static UserClientInstance instance;
    private Retrofit retrofit;
    private static UserService service;
    private final static String base_url = "http://192.168.0.7:5000";
    //private final static String base_url = "http://10.0.2.2:5000";

    private UserClientInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(UserService.class);
    }

    public static UserService getService(){
        if(service == null){
            synchronized (UserClientInstance.class){
                instance = new UserClientInstance();
            }
        }
        return service;
    }
}
