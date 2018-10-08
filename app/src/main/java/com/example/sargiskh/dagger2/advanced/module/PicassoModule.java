package com.example.sargiskh.dagger2.advanced.module;

import android.content.Context;

import com.example.sargiskh.dagger2.advanced.annotation.ApplicationContext;
import com.example.sargiskh.dagger2.advanced.annotation.RandomUserApplicationScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @RandomUserApplicationScope
    @Provides
//    public Picasso picasso(@Named("application_context") Context context, OkHttp3Downloader okHttp3Downloader) {
//    public Picasso picasso(@ActivityContext Context context, OkHttp3Downloader okHttp3Downloader) {
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context).downloader(okHttp3Downloader).build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }

}
