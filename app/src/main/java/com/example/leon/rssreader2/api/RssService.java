package com.example.leon.rssreader2.api;

import com.example.leon.rssreader2.content.Rss;

import retrofit.http.Path;

import retrofit.http.GET;

/**
 * Created by Leon on 20.11.2015.
 */
public interface RssService {

    @GET("/{id}")
    Rss feed(@Path("id") String id);

}
