package com.example.health.PostPic;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

//public class PostPic {
//}
//public boolean uploadFile(String path,String filename)
//{
//    OkHttpClient okhttp = new OkHttpClient();
//    File file = new File(path);
//    showMessage(path);
//    if(path.isEmpty())
//        return false;
//    if(!file.exists())
//        return false;
//    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//            .addFormDataPart("images",filename,RequestBody.create(new File(path), MediaType.parse("multipart/form-data")))
////                .addFormDataPart("filename",filename)
//            .build();
//    FutureTask<Boolean> task = new FutureTask<>(()->
//    {
//        try
//        {
//            ResponseBody responseBody = okhttp.newCall(
//                    new Request.Builder().post(body).url("http://10.70.140.3:8080/upload").build()
//            ).execute().body();
//
//            if(responseBody != null)
//                return true;
//            return false;
//        }
//        catch (IOException e)
//        {
//            return false;
//        }
//    });
//    try
//    {
//        new Thread(task).start();
//        return task.get();
//    }
//    catch (ExecutionException | InterruptedException e)
//    {
//        e.printStackTrace();
//        return false;
//    }
//}
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUploader {
    private static final String BOUNDARY = "--------------------------899676027522088183484028";
    private static final String LINE_FEED = "\r\n";

    public static void main(String[] args) {
        File fileToUpload = new File("/Users/admin/Downloads/GUET/fun/cats/cat3.gif");
        String urlString = "http://10.70.140.3:8080/upload";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Host", "10.70.140.3:8080");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            connection.setDoOutput(true);
            connection.setConnectTimeout(0); // 设置连接超时为无限制
            connection.setReadTimeout(0); // 设置读取超时为无限制

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes("--" + BOUNDARY + LINE_FEED);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileToUpload.getName() + "\"" + LINE_FEED);
                outputStream.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(fileToUpload.getName()) + LINE_FEED);
                outputStream.writeBytes(LINE_FEED);

                // 读取文件并写入输出流
                FileInputStream fileInputStream = new FileInputStream(fileToUpload);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.writeBytes(LINE_FEED);
                outputStream.writeBytes("--" + BOUNDARY + "--" + LINE_FEED);
                outputStream.flush();
                fileInputStream.close();
            }

            // 获取响应
            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("Upload failed: " + responseCode);
            }

            System.out.println("Response: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}