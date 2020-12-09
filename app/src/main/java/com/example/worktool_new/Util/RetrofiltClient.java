package com.example.worktool_new.Util;

import android.util.Log;
import com.example.worktool_new.BuildConfig;
import com.example.worktool_new.Retrofit.Apis;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofiltClient {
    private Retrofit retrofit = null;

    public Apis getAPI() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(provideHttpLoggingInterceptor()).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS);
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder().baseUrl("http://devworktools.fr/contenu/conseiller/").addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        }
        return (Apis) this.retrofit.create(Apis.class);
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            public void log(String message) {
                Log.d("Injector", message);
            }
        });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;
    }
}
