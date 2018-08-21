package com.example.yunus.pointofsale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRActivity extends AppCompatActivity {
    ImageView imageQR;
    String text2Qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        imageQR = (ImageView) findViewById(R.id.imageQR);
        Intent intent = getIntent();
        text2Qr = intent.getStringExtra("serverResp");
        generateQR(text2Qr);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //Geriye basıldığında, QR'dan Main'e geçerken ekranda progress bar kalmaması için bu kontrol eklendi.
        Intent intent = new Intent(QRActivity.this, MainActivity.class);
        intent.putExtra("isBackPressed", "true");
        startActivity(intent);

    }

    public void generateQR (String text2Qr){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageQR.setImageBitmap(bitmap);
        }
        catch (WriterException e){
            e.printStackTrace();
        }
    }


}
