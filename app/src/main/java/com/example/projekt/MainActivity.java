package com.example.projekt;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private  static int ID =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonDlugiTekst = findViewById(R.id.button1);
        Button buttonObraz = findViewById(R.id.button2);
        Button buttonWieleLinijek = findViewById(R.id.button3);
        Notification.createNotificationChannels(this);
        buttonDlugiTekst.setOnClickListener(v->{
            Notification.sendNotification(ID,Notification.CHANNEL_ID_LOW,this,"Wiadomość z długim tekstem","Cras venenatis quam non dictum dictum. Nulla sit amet iaculis felis. Morbi interdum lacus leo, ac hendrerit urna viverra eget. Sed risus tortor, auctor eu fermentum ac, viverra sit amet turpis. Quisque non dui non mauris molestie laoreet. Donec nec gravida mi, ac suscipit felis. Aliquam eu vulputate ex. Proin luctus non ex in varius.",1,0);
            ID++;
        });

        buttonObraz.setOnClickListener(v->{
            Notification.sendNotification(ID,Notification.CHANNEL_ID_DEFAULT,this,"Wiadomość z Obrazkiem","Obrazek",2,R.drawable.obraz);
            ID++;
        });

        buttonWieleLinijek.setOnClickListener(v->{
            Notification.sendNotification(ID,Notification.CHANNEL_ID_HIGH,this,"Wiadomość Dodatkowymi liniami","Zwykła linia",3,1);
            ID++;
        });
    };

    }
