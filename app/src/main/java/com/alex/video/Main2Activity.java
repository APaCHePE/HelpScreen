package com.alex.video;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {

    static final int VIDEO_CAPTURE = 1;
    static final int SELECT_VIDEO = 1;
    VideoView video2;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //encodeFileToBase64Binary( "D:\\s" );



        videoUri = Uri.parse(getIntent().getExtras().getString("videoUri"));

        video2 = findViewById(R.id.videoView2);

        //video2 = ;

        /*//String path = "android.resource://" + getPackageManager() + "/" + R.raw.small;*/
        video2.setVideoURI(videoUri);
        video2.start();
        findViewById(R.id.btnGrabar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, VIDEO_CAPTURE);
                }
            }
        });
    }
    private String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.encodeToString(bytes, Base64.DEFAULT).toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
/*'


        File originalFile = new File("prueba.mp4");//"signature.jpg"
        String encodedBase64 = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int) originalFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
            Log.e("BASE64 VIDEO", encodedBase64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Imagen este ", String.valueOf(data));
        if (data != null) {
            switch (requestCode) {
                case SELECT_VIDEO:
                    Uri selectedVideoUri = data.getData();
                    String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.DURATION};
                    Cursor cursor = managedQuery(selectedVideoUri, projection, null, null, null);

                    cursor.moveToFirst();
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                    Log.d("File Name:", filePath);

                    Bitmap thumb = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);
                    // Establecer la miniatura del video en la vista de imagen
                    msImage.setImageBitmap(thumb);
                    InputStream inputStream = null;
                    // Convertir el video a bytes
                    try {
                        inputStream = getContentResolver().openInputStream(selectedVideoUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    byteBuffer = new ByteArrayOutputStream();
                    int len = 0;
                    try {
                        while ((len = inputStream.read(buffer)) != -1) {
                            byteBuffer.write(buffer, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("converted!");

                    String videoData = "";
                    //
                    //28/5000
                    //Convertir bytes en base64
                    videoData = Base64.encodeToString(byteBuffer.toByteArray(), Base64.DEFAULT);
                    Log.d("VideoData**>  ", videoData);

                    String sinSaltoFinal2 = videoData.trim();
                    String sinsinSalto2 = sinSaltoFinal2.replaceAll("\n", "");
                    Log.d("VideoData**>  ", sinsinSalto2);

                    baseVideo = sinsinSalto2;
            }
        }
    }*/
}



/*
    <uses-permission android:name="android.permission.CAMERA" />

    <uses-permission android:name="android.permission.RECORD_AUDIO" />

    <uses-permission android:name="android.permission.RECORD_VIDEO" />

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />*/