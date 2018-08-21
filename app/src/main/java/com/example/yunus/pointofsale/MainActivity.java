package com.example.yunus.pointofsale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        listViewItems = new ArrayList<String>();
        productList = new ArrayList<Product>();

        //InputStream caInput = new BufferedInputStream(getResources().openRawResource(R.raw.test));

        String[] products =
                {"product1", "product2", "product3", "product4","product5",
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
        adapter=new ArrayAdapter<String>
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
//                RequestParams rp = new RequestParams();
//                rp.add("merchant_no", "6706598320");
//                rp.add("terminal_no", "67001985");
//                rp.add("amount", totalPrice.toString());

/*                String MY_FILE_NAME = "asd.txt";
                // Create a new output file stream
                FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);
                // Create a new file input stream.
                FileInputStream fileis = openFileInput(MY_FILE_NAME);*/

//                OkHttpClient client = new OkHttpClient();
//                SSLContext sslContext = SslUtils.getSslContextForCertificateFile(getApplicationContext(), "raw/posnetictyapikredicomtr.cer");
//                client.setSslSocketFactory(sslContext.getSocketFactory());
//                Response response;
//                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://posnetict.yapikredi.com.tr/MerchantBE/api/Sale").newBuilder();
////                urlBuilder.addQueryParameter("merchant_no", "6706598320");
////                urlBuilder.addQueryParameter("terminal_no", "67001985");
////                urlBuilder.addQueryParameter("amount", totalPrice.toString());
//                String url = urlBuilder.build().toString();
//                RequestBody requestBody = new  MultipartBuilder()
//                        .type(MultipartBuilder.FORM)
//                        .addFormDataPart("merchant_no", "6706598320")
//                        .addFormDataPart("terminal_no", "67001985")
//                        .addFormDataPart("amount", totalPrice.toString())
//                        .build();
//
//
//                Request request = new Request.Builder()
//                        .url(url)
//                        .post(requestBody)
//                        .build();
//
////                try {
////                    response = client.newCall(request).execute();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.sslSocketFactory(getSSLSocketFactory());
                httpClient.hostnameVerifier(new HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

                OkHttpClient client = httpClient.build();
                //retrofit = builder.client(client).build();

                SaleRequest saleRequest = new SaleRequest(totalPrice.toString());

                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();


                //creating the api interface
                Api api = retrofit.create(Api.class);

                Call<SaleResponse> call = api.Sale(saleRequest);

                call.enqueue(new Callback<SaleResponse>() {
                    @Override
                    public void onResponse(Call<SaleResponse> call, Response<SaleResponse> response) {
                        Log.d("girdi 1", response.body().getToken_data());
                        //qr bassssss
                        //Call QRActivity with serverResp
                        Intent intent = new Intent(MainActivity.this, QRActivity.class);
                        intent.putExtra("serverResp", response.body().getToken_data());
                        startActivity(intent);

                    }
                    @Override
                    public void onFailure(Call<SaleResponse> call, Throwable t) {
                        Log.d("onFailure Error ",t.getMessage());
                        String error = t.getMessage();
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

//                HttpUtils.postByUrl("https://posnetict.yapikredi.com.tr/MerchantBE/api/Sale", rp, new JsonHttpResponseHandler()
//                {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject timeline) {
//                        // Pull out the first event on the public timeline
//                        Log.d("asd", "---------------- this is response : " + timeline.toString());
//                        //text3.setText(timeline.toString());
//                        try {
//                            JSONObject serverResp = new JSONObject(timeline.toString());
//                            //qr bassssss
//                            //Call QRActivity with serverResp
//                            Intent intent = new Intent(MainActivity.this, QRActivity.class);
//                            intent.putExtra("serverResp", timeline.toString());
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                        super.onFailure(statusCode, headers, responseString, throwable);
//                        Log.d("Failed: ", ""+statusCode);
//                        Log.d("Error : ", "" + throwable);
//                    }
//                });
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
