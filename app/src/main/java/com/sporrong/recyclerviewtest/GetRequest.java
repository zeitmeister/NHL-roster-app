package com.sporrong.recyclerviewtest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest {
    OkHttpClient client = new OkHttpClient();
    String mResponse;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void run(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    mResponse = response.body().string();


                }
            });
        };
        /*try (Response response = client.newCall(request).execute()){
            return response.body().string();
        }*/
    }

