package com.example.projectimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.projectimage.api.ImageApi;
import com.example.projectimage.model.Image;
import com.example.projectimage.ui.activity.InfoImageActivity;
import com.example.projectimage.ui.adapter.ImageAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private ImageAdapter imageAdapter;
    private ImageApi imageApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        recycleView = (RecyclerView) findViewById(R.id.recycleView);

        Gson gson = new GsonBuilder().serializeNulls().create();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        imageApi = retrofit.create(ImageApi.class);
        getListImage();
    }

    private void getListImage() {
        Call<List<Image>> callImage = imageApi.getImage(10, "beng", "J2PmlIizw");
        callImage.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Minh", response.code() + " Code ");
                }

                List<Image> listImage = new ArrayList<>();
                listImage = response.body();
                imageAdapter = new ImageAdapter(listImage, new ImageAdapter.Callback() {
                    @Override
                    public void onClick(Image image) {
                        Intent intent = new Intent(MainActivity.this, InfoImageActivity.class);
                        intent.putExtra("image", image);
                        startActivity(intent);
                    }
                });
                recycleView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                recycleView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                Log.e("Minh", t.getMessage() + " Code ");
            }
        });

    }
}