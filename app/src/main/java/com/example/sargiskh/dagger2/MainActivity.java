package com.example.sargiskh.dagger2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sargiskh.dagger2.advanced.MainActivityFeature.DaggerMainActivityComponent;
import com.example.sargiskh.dagger2.advanced.MainActivityFeature.MainActivityComponent;
import com.example.sargiskh.dagger2.advanced.MainActivityFeature.MainActivityModule;
import com.example.sargiskh.dagger2.advanced.application.RandomUserApplication;
import com.example.sargiskh.dagger2.advanced.component.DaggerRandomUserComponent;
import com.example.sargiskh.dagger2.advanced.component.RandomUserComponent;
import com.example.sargiskh.dagger2.advanced.module.ContextModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.sargiskh.dagger2.advanced.adapter.RandomUserAdapter;
import com.example.sargiskh.dagger2.advanced.interfaces.RandomUsersApi;
import com.example.sargiskh.dagger2.advanced.model.RandomUsers;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;

    @Inject
    RandomUserAdapter mAdapter;

    @Inject
    RandomUsersApi randomUsersApi;

    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
//        beforeDagger2();
//        afterDagger2();
        afterActivityLevelComponent();
        populateUsers();
    }

    private void afterActivityLevelComponent() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

    private void afterDagger2() {
        RandomUserComponent daggerRandomUserComponent = DaggerRandomUserComponent.builder().contextModule(new ContextModule(this)).build();

        picasso = daggerRandomUserComponent.getPicasso();
        randomUsersApi = daggerRandomUserComponent.getRandomUsersService();

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .randomUserComponent(RandomUserApplication.get(this).getRandomUserComponent())
                .build();


//        randomUsersApi = mainActivityComponent.getRandomUsersService();
//        mAdapter = mainActivityComponent.getRandomUserAdapter();
    }

    private void beforeDagger2() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

//        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.e("LOG_TAG", "message: " + message);
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // --> start - version_2
        File cacheFile = new File(this.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
        // <-- end - version_2

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache) // version_2
                .addInterceptor(httpLoggingInterceptor)
                .build();

        // --> start - version_2
        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);

        picasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();
        // <-- end - version_2

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = getRandomUserService().getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, @NonNull Response<RandomUsers> response) {
                if(response.isSuccessful()) {
                    Log.e("LOG_TAG", "response.isSuccessful");
                    mAdapter = new RandomUserAdapter(picasso);
                    mAdapter.setItems(response.body().getResults());
                    recyclerView.setAdapter(mAdapter);
                } else {
                    Log.e("LOG_TAG", "!!! response.isSuccessful");
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Log.e("LOG_TAG", "onFailure: " + t.getMessage());
//                Timber.i(t.getMessage());
            }
        });
    }

    public RandomUsersApi getRandomUserService(){
//        return retrofit.create(RandomUsersApi.class); // Before Dagger2
        return randomUsersApi; // After Dagger2
    }

}