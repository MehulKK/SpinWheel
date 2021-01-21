package com.macv.wheeldemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.macv.mwheel.MSpinWheel;
import com.macv.mwheel.WheelListener;
import com.macv.mwheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MSpinWheel lw;
    List<WheelItem> wheelItems ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateWheelItems();

        lw = findViewById(R.id.lwv);
        lw.addWheelItems(wheelItems);


        lw.setWheelListener(new WheelListener() {
            @Override
            public void onOpen() {

            }

            @Override
            public void onClose() {

            }

            @Override
            public void onReward(int pos) {
                Toast.makeText(MainActivity.this, "Target Reached "+wheelItems.get(pos).text, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStart() {
                Toast.makeText(MainActivity.this, "Start " , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError() {

            }
        });

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lw.rotateWheelTo(new Random().nextInt(wheelItems.size()));
            }
        });

    }

    private void generateWheelItems() {
        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.chat) , "100 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.coupon) , "0 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.ice_cream), "30 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.lemonade), "6000 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#141A1E"), BitmapFactory.decodeResource(getResources(),R.drawable.orange), "9 $"));
        wheelItems.add(new WheelItem(Color.parseColor("#040D14"), BitmapFactory.decodeResource(getResources(),R.drawable.shop), "20 $"));
    }
}
