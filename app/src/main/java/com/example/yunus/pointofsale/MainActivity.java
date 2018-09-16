package com.example.yunus.pointofsale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Random;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.Header;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView text3;
    private TextView mTextMessage;
    ArrayList<String> listViewItems;
    ArrayList<Product> productList;
    ArrayAdapter<String> adapter;
    private boolean progressStatus = false;
    private boolean isBackPressed = false;
    String isBackPressedString = "";
    private Handler handler = new Handler();
    Double totalPrice = 0.0;
    TextView totalPriceView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isBackPressed = false;
        listViewItems = new ArrayList<String>();
        productList = new ArrayList<Product>();
        progressStatus = false;


        Intent intent = getIntent();//Geriye basıldığında, QR'dan Main'e geçerken ekranda progress bar kalmaması için bu kontrol eklendi.
        isBackPressedString = intent.getStringExtra("isBackPressed");
        if (isBackPressedString != null && isBackPressedString.equalsIgnoreCase("true") == true)
            isBackPressed = true;
        else
            isBackPressed = false;


        //InputStream caInput = new BufferedInputStream(getResources().openRawResource(R.raw.test));

        String[] products =
                {"product1", "product2", "product3", "product4", "product5",
                        "product6", "product7", "product8", "product9", "product10", "product11",
                        "product12", "product13", "product14", "product15", "product16"};


        for (int i = 0; i < 17; i++) {
            Product p = new Product();
            p.name = "Product" + Integer.toString(i + 1);
            Random r = new Random();
            p.price = (double) r.nextInt(60 - 35) + 35;
            productList.add(p);
        }


        listViewItems.add("  Item Name                                  Price   ");
        ListView itemList = (ListView) findViewById(R.id.listView);
        text3 = (TextView) findViewById(R.id.textView3);
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.activity_list_item, android.R.id.text1, listViewItems);
        itemList.setAdapter(adapter);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        totalPriceView = (TextView) findViewById(R.id.totalPrice);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewItems.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return sslSocketFactory;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }

    }

    public void RelativeLayoutOnClick(View v) throws IOException {
        switch (v.getId() /*to get clicked view id**/) {

            case R.id.buttonSend:
                //retrofit = builder.client(client).build();

                SaleRequest saleRequest = new SaleRequest(totalPrice.toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();


                //creating the api interface
                Api api = retrofit.create(Api.class);

                Call<SaleResponse> call = api.Sale(saleRequest);


                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                // Set the progress dialog title and message
                pd.setTitle("Waiting for the Service response !!!");
                pd.setMessage("Transaction is sending to YKB.........");
                pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
                pd.setIndeterminate(false);

                // Finally, show the progress dialog
                pd.show();
                // Set the progress status zero on each button click
                progressStatus = false;

                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus){

                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(20);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the progress status
                                    //Servis call bittiğinde ve QR ekranında geri basıldığında progressBar dialogunu kapat.
                                    if(progressStatus == true || isBackPressed == true){
                                        // Dismiss/hide the progress dialog
                                        pd.dismiss();
                                    }
                                }
                            });
                        }
                    }
                }).start(); // Start the operation

                call.enqueue(new Callback<SaleResponse>() {
                    @Override
                    public void onResponse(Call<SaleResponse> call, Response<SaleResponse> response) {
                        progressStatus = true;
                        Log.d("girdi 1", response.body().getToken_data());
                        //qr bassssss
                        //Call QRActivity with serverResp
                        Intent intent = new Intent(MainActivity.this, QRActivity.class);
                        intent.putExtra("serverResp", response.body().getToken_data());
                        startActivity(intent);

                    }
                    @Override
                    public void onFailure(Call<SaleResponse> call, Throwable t) {
                        progressStatus = true;
                        Log.d("onFailure Error ",t.getMessage());
                        String error = t.getMessage();

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            case R.id.btn_Clear:
                listViewItems.clear();
                listViewItems.add("  Item Name                                  Price   ");
                adapter.notifyDataSetChanged();
                totalPrice = 0.0;
                totalPriceView.setText(Double.toString(totalPrice));
                break;

            case R.id.relative00:
                listViewItems.add("   "+productList.get(0).name + "                                     " + productList.get(0).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(0).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative01:
                listViewItems.add("   "+productList.get(1).name + "                                     " + productList.get(1).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(1).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative02:
                listViewItems.add("   "+productList.get(2).name + "                                     " + productList.get(2).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(2).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative03:
                listViewItems.add("   "+productList.get(3).name + "                                     " + productList.get(3).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(3).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative04:
                listViewItems.add("   "+productList.get(4).name + "                                     " + productList.get(4).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(4).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative05:
                listViewItems.add("   "+productList.get(5).name + "                                     " + productList.get(5).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(5).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative06:
                listViewItems.add("   "+productList.get(6).name + "                                     " + productList.get(6).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(6).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative10:
                listViewItems.add("   "+productList.get(7).name + "                                     " + productList.get(7).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(7).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative11:
                listViewItems.add("   "+productList.get(8).name + "                                     " + productList.get(8).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(8).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative12:
                listViewItems.add("   "+productList.get(9).name + "                                   " + productList.get(9).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(9).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative13:
                listViewItems.add("   "+productList.get(10).name + "                                   " + productList.get(10).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(10).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative14:
                listViewItems.add("   "+productList.get(11).name + "                                   " + productList.get(11).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(11).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative15:
                listViewItems.add("   "+productList.get(12).name + "                                   " + productList.get(12).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(12).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative16:
                listViewItems.add("   "+productList.get(13).name + "                                   " + productList.get(13).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(13).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative20:
                listViewItems.add("   "+productList.get(14).name + "                                   " + productList.get(14).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(14).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relative21:
                listViewItems.add("   "+productList.get(15).name + "                                   " + productList.get(15).price);
                adapter.notifyDataSetChanged();
                totalPrice += productList.get(15).price;
                totalPriceView.setText(Double.toString(totalPrice));
                break;
            case R.id.relativePlus:

                break;



            default:
                break;
        }
    }

}
