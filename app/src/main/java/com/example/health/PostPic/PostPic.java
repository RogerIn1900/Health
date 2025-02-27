package com.example.health.PostPic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostPic {

    private OkHttpClient client;

    public PostPic() {
        client = new OkHttpClient.Builder().build();
    }

    public boolean uploadFile(String path, String filename) {
        File file = new File(path);

        // 检查文件是否存在
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return false;
        }

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("images", filename,
                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        Request request = new Request.Builder()
                .url("http://10.70.140.3:8080/upload")
                .post(body)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "10.70.140.3:8080")
                .addHeader("Connection", "keep-alive")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.err.println("Unexpected code " + response);
                return false;
            }
            return true; // 上传成功
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 上传失败
        }
    }

    public static void main(String[] args) {
        PostPic postPic = new PostPic();
        boolean result = postPic.uploadFile("/Users/admin/Documents/0e4e07d3032ce6ff1a.jpg", "0e4e07d3032ce6ff1a.jpg");
        System.out.println("Upload result: " + result);
    }
}