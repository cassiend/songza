package com.project.songza.api;

import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.io.InputStream;
import java.net.URL;

public class ImageTask implements Runnable {
    private static final int IMAGE_READY = 0;

    private Handler onCompleteHandler;
    private final String urlString;


    public ImageTask(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlString);
            InputStream inputStream = (InputStream) url.getContent();
            complete(Drawable.createFromStream(inputStream, "src name"));
        } catch (Exception e) {
        }

    }

    public void setOnCompleteHandler(Handler handler) {
        onCompleteHandler = handler;
    }

    public void complete(Drawable image) {
        onCompleteHandler.sendMessage(onCompleteHandler.obtainMessage(IMAGE_READY, image));
    }
}