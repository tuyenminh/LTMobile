package com.example.appquanlidiem.util_tkb;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.example.appquanlidiem.MyApplication_tkb;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static final byte[] EMPTY_BYTES = new byte[0];
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    private static class Inner {
        private static final CookieJar cookieJar = new PersistentCookieJar(
                new SetCookieCache(),
                new SharedPrefsCookiePersistor(MyApplication_tkb.getApplication()));
        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .followRedirects(true)
                .build();
    }

    public static OkHttpClient getOkHttpClient() {
        return Inner.okHttpClient;
    }


    public static String downloadText(String url) {
        return downloadText(createRequest(url));
    }


    public static String downloadText(Request request) {
        return downloadText(request, "UTF-8");
    }



    public static String downloadText(Request request, String encoding) {
        try {
            return new String(downloadRaw(request), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] downloadRaw(Request request) {
        try (Response response = getOkHttpClient().newCall(request).execute()) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    return Objects.requireNonNull(response.body()).bytes();
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return EMPTY_BYTES;
    }

    public static Request createRequest(String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }
}
