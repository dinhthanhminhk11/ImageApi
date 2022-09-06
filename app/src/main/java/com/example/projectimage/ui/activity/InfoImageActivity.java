package com.example.projectimage.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectimage.R;
import com.example.projectimage.api.ImageApi;
import com.example.projectimage.model.Image;
import com.example.projectimage.model.Root;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoImageActivity extends AppCompatActivity {
    private ImageApi imageApi;
    private Image image;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_image);

        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.textView);

        image = (Image) getIntent().getSerializableExtra("image");
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
        getRoot();
    }

    public void getRoot() {
        Call<Root> callImage = imageApi.getRoot(image.getId());
        callImage.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    Log.e("Minh", response.code() + " Code ");
                }
                Root root = response.body();
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.noimage)
                        .error(R.drawable.noimage);
                Glide.with(imageView.getContext()).load(root.getUrl()).apply(options).into(imageView);
                textView.setText(root.toString());
                Log.e("Minh", root.toString());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e("Minh", t.getMessage() + " Code ");
            }
        });
    }
}