package com.example.yunus.pointofsale;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

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

        adapter=new ArrayAdapter<String>
                (this, android.R.layout.activity_list_item, android.R.id.text1, listViewItems);
        itemList.setAdapter(adapter);



        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        RelativeLayout rl_0 = (RelativeLayout) findViewById(R.id.relative00);
        RelativeLayout rl_1 = (RelativeLayout) findViewById(R.id.relative01);
        RelativeLayout rl_2 = (RelativeLayout) findViewById(R.id.relative02);
        RelativeLayout rl_3 = (RelativeLayout) findViewById(R.id.relative03);
        RelativeLayout rl_4 = (RelativeLayout) findViewById(R.id.relative04);
        RelativeLayout rl_5 = (RelativeLayout) findViewById(R.id.relative05);
        RelativeLayout rl_6 = (RelativeLayout) findViewById(R.id.relative06);
        RelativeLayout rl_7 = (RelativeLayout) findViewById(R.id.relative10);
        RelativeLayout rl_8 = (RelativeLayout) findViewById(R.id.relative11);
        RelativeLayout rl_9 = (RelativeLayout) findViewById(R.id.relative12);
        RelativeLayout rl_10 = (RelativeLayout) findViewById(R.id.relative13);
        RelativeLayout rl_11 = (RelativeLayout) findViewById(R.id.relative14);
        RelativeLayout rl_12 = (RelativeLayout) findViewById(R.id.relative15);
        RelativeLayout rl_13 = (RelativeLayout) findViewById(R.id.relative16);
        RelativeLayout rl_14 = (RelativeLayout) findViewById(R.id.relative20);
        RelativeLayout rl_15 = (RelativeLayout) findViewById(R.id.relative21);
        RelativeLayout rl_plus = (RelativeLayout) findViewById(R.id.relativePlus);
        ImageView image_0 = (ImageView) findViewById(R.id.imageLabel00);
        Button btn_Confirm = (Button) findViewById(R.id.button);
        totalPriceView = (TextView) findViewById(R.id.totalPrice);




    }

    public void RelativeLayoutOnClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {

            case R.id.button:
                //listViewItems.add(productList.get(0).name + " --- " + productList.get(0).price);
                Toast.makeText(getApplicationContext(),"button",Toast.LENGTH_LONG);
                Log.i("ClickListener", "Button Çalıştırıldı...");
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
