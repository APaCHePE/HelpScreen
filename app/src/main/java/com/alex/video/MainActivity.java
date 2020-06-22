package com.alex.video;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private final int VIDEO_REQUEST_CAPTURE = 1;
    private Uri videoUri = null;
    private VideoView videoView;
    public static Bitmap bitmap, decodebitmop, decode;
    private ImageView imageView;
    private EditText textView;
    public static String encodeImage, encodeVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest
                        .permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {Manifest.permission.SEND_SMS,}, 1000);
        } else {
        }

        init();
        imageView = findViewById(R.id.imagenView);
        textView = findViewById(R.id.textView);
        Log.e("CORREO",textView.getText().toString());

        //bitmap = BitmapFactory.decodeResource(getResources(), R.raw.prueba);



        //ByteArrayOutputStream bacs = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.PNG, 100, bacs);
        //byte[] b = bacs.toByteArray();
        //encodeImage =  Base64.encodeToString(b, Base64.DEFAULT);
        //textView.setText(encodeImage);
        //Log.e("BASE64", encodeImage);
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                ByteArrayOutputStream bacs = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.MP4, 100, bacs);
                byte[] b = bacs.toByteArray();
                encodeImage = Base64.encodeToString(b, Base64.DEFAULT);
                byte[] decodebyte = Base64.decode(encodeImage, Base64.DEFAULT);
                decode = BitmapFactory.decodeByteArray(decodebyte, 0, decodebyte.length);
               return encodeImage;
            }

            protected void onPostExecute(String s) {
                //textView.setText(s);
                //videoView.setVideoPath(decode);
                Log.e("BASE64", s);
            }
        }.execute();
        //byte [] decodebyte = Base64.decode(  encodeImage  ,Base64.DEFAULT);
        //Bitmap decode = BitmapFactory.decodeByteArray(decodebyte, 0, decodebyte.length );


    }

    public byte[] readBytes(Uri uri) throws IOException {
        // this dynamically extends to take the bytes you read
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        Log.e("SE LEYO EL BYTE", "finish");
        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    private void init() {
        this.videoView = (VideoView) findViewById(R.id.videoViewMain);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST_CAPTURE && resultCode == RESULT_OK) {
            videoUri = data.getData();
            if (videoUri != null) {
                //videoView.setVideoURI(videoUri);
                //videoView.start();
                try {

                    encodeVideo = Base64.encodeToString(readBytes(videoUri), Base64.DEFAULT);
                    //textView.setText(encodeImage);
                    Log.e("BASE64", encodeVideo);
                    enviarCorreo(encodeVideo);
                    byte[] decodeVideobyte = Base64.decode(encodeVideo, Base64.DEFAULT);


                    videoView.setVideoURI(videoUri);
                    videoView.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void convertBytesToFile(byte[] bytearray) {
        try {

            File outputFile = File.createTempFile("file", "mp3", getCacheDir());
            outputFile.deleteOnExit();
            FileOutputStream fileoutputstream = new FileOutputStream(outputFile);
            fileoutputstream.write(bytearray);
            fileoutputstream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void grabar(View view) {
        if (textView.getText().toString().length() <= 4){
            Toast.makeText(this, "Ingresa un correo", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, VIDEO_REQUEST_CAPTURE);
        }
    }

    public void NEXT(View view) {
        Intent intento = new Intent(this, Main2Activity.class);
        intento.putExtra("videoUri", videoUri.toString());
        startActivity(intento);
    }

    public void enviar(View view) {
        enviarMensaje("952297301", "Hola alex");
    }

    public void enviarCorreo(String body){

        String correo = "alexanderspradochoquepata@gmail.com";
        JavaMailAPI javaMailAPI = new JavaMailAPI(MainActivity.this, correo,"BASE64 VIDEO", body);
        javaMailAPI.execute();
    }
    private void enviarMensaje(String numero, String mensaje) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero, null, mensaje, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    private void mapa(View view){

    }

}

/*SelectedImagePath
        FileInputStream objFileIS = null;
        try
        {
            System.out.println("file = >>>> <<<<<" + selectedImagePath);
            objFileIS = new FileInputStream(selectedImagePath);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream objByteArrayOS = new ByteArrayOutputStream();
        byte[] byteBufferString = new byte[1024];
        try
        {
            for (int readNum; (readNum = objFileIS.read(byteBufferString)) != -1;)
            {
                objByteArrayOS.write(byteBufferString, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        videodata = Base64.encodeToString(byteBufferString, Base64.DEFAULT);
        Log.d("VideoData**>  " , videodata);*/