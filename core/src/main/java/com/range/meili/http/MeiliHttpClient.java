package com.range.meili.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MeiliHttpClient {

    private final OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new IOException("HTTP request failed: " + response.code());
            }
            return response.body().string();
        }
    }

    public boolean isOk(String url) {
        try {
            get(url);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
