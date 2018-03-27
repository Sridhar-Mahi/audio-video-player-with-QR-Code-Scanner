package com.example.application2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import java.io.File;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    @Override
    protected void onPause() {

        super.onPause();
        mScannerView.stopCamera();

    }

    @Override
    public void handleResult(Result result) {

        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        File musicFile2Play = new File("/sdcard/hai/"+result.getText()+".mp3");
        Intent i = new Intent();
        i.setAction(android.content.Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(musicFile2Play), "audio/mp3");
        startActivity(i);

        File videoFile2Play = new File("/sdcard/hai/"+result.getText()+".mp4");
        Intent i1 = new Intent();
        i1.setAction(android.content.Intent.ACTION_VIEW);
        i1.setDataAndType(Uri.fromFile(videoFile2Play), "video/mp4");
        startActivity(i1);

    }

}
