package com.sray.httplibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016-5-31.
 */
public abstract class OkHttpTask{
    private static OkHttpClient okHttpClient;
    public static final String URL_REGEX = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";

    public static OkHttpTaskBuidler newInstance(Context context){
        if (okHttpClient == null){
            File cacheDir = context.getCacheDir();
            okHttpClient = new OkHttpClient.Builder()
                    .cache(new Cache(cacheDir,4 * 1024 * 1024))
                    .build();
        }
        OkHttpTaskBuidler okHttpTask = new OkHttpTaskBuidler();
        return okHttpTask;
    }

    public static class OkHttpTaskBuidler extends AsyncTask<String,Integer,String>{

        private IOkTaskCallback iOkTaskCallback;
        private boolean post;
        private RequestBody formBody;

        public enum OkType{
            form,json
        }
        public void start(String url){
            if (url == null){
                throw new NullPointerException("url is null");
            }
            if (!url.matches(URL_REGEX)){
                throw new IllegalArgumentException("URL is wrong");
            }
            this.execute(url);
        }

        public OkHttpTaskBuidler enqueue(IOkTaskCallback iOkTaskCallback){
            this.iOkTaskCallback = iOkTaskCallback;
            return OkHttpTaskBuidler.this;
        }

        public OkHttpTaskBuidler post (Map<String,String> param,OkType type){
            if (param == null){
                throw new NullPointerException("param is null");
            }
            post = true;
            switch (type){
                case form:
                    FormBody.Builder builder = new FormBody.Builder();
                    Set<String> strings = param.keySet();
                    for (String str : strings) {
                        builder.add(str,param.get(str));
                    }
                    formBody = builder.build();
                    break;
                case json:
                    try {
                        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                        JSONObject jsonObject = new JSONObject();
                        Set<String> keySet = param.keySet();
                        for (String key : keySet) {
                            jsonObject.put(key, param.get(key));
                        }
                        formBody = RequestBody.create(mediaType,jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return OkHttpTaskBuidler.this;
        }
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            Request.Builder builder = new Request.Builder();
            Response response = null;
            try {
                if (post){
                    builder.post(formBody);
                }
                Request request = builder.url(url).build();
                response = okHttpClient.newCall(request).execute();
                String string = response.body().string();
                return string;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (iOkTaskCallback != null){
                iOkTaskCallback.onSuccess(s);
            }
        }
    }
}
