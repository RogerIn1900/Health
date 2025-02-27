package com.example.health.PostPic;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.health.ApiService.ApiService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageUploader {

    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    return cursor.getString(columnIndex);
                }
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    public static void uploadImage(Uri uri, Context context, OnStatusChangeListener listener) {
        String filePath = getRealPathFromURI(context, uri);
        if (filePath != null) {
            File file = new File(filePath);

            // 创建 RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // 创建 MultipartBody.Part
            MultipartBody.Part body = MultipartBody.Part.createFormData("images", file.getName(), requestFile);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.70.140.3:8080/") // 替换为您的 API 基础 URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);
            apiService.uploadImage(body).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        listener.onStatusChange("上传成功");
                    } else {
                        listener.onStatusChange("上传失败: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onStatusChange("上传错误: " + t.getMessage());
                }
            });
        } else {
            listener.onStatusChange("无法获取文件路径");
        }
    }

    public interface OnStatusChangeListener {
        void onStatusChange(String status);
    }
}