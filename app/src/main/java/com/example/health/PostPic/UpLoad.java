//package com.example.health.PostPic;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.FutureTask;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//
//public class UpLoad {
//    OkHttpClient client = new OkHttpClient().newBuilder().build();
//    File file = new File("/Users/admin/Documents/0e4e07d3032ce6ff1a.jpg");
//
//if (!file.exists()) {
//        throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
//    }
//
//    RequestBody body = new MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("images", file.getName(),
//                    RequestBody.create(MediaType.parse("application/octet-stream"), file))
//            .build();
//
//    Request request = new Request.Builder()
//            .url("http://10.70.140.3:8080/upload")
//            .method("POST", body)
//            .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
//            .addHeader("Accept", "*/*")
//            .addHeader("Host", "10.70.140.3:8080")
//            .addHeader("Connection", "keep-alive")
//            .build();
//
//    Response response = client.newCall(request).execute();
//if (!response.isSuccessful()) {
//        throw new IOException("Unexpected code " + response);
//    }
//
//}
