package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycle;
    MyListAdapter adapter;
    private  ArrayList<String> ls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      database();

        FloatingActionButton fab = findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              

                Intent in=new Intent(MainActivity.this,EmText.class);
                startActivity(in);
            }
        });

       /* recycle=findViewById(R.id.recycle);
        adapter = new MyListAdapter(MainActivity.this,ls);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(adapter);*/
    }

public void database()
{

    DatabaseReference refernce1 = FirebaseDatabase.getInstance().getReference().child("message");
    ls=new ArrayList<>();
    refernce1.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for( DataSnapshot snap:snapshot.getChildren())

            {
                String s=snap.getValue().toString();
                ls.add(s);

            }
            recycle=findViewById(R.id.recycle);


            adapter = new MyListAdapter(MainActivity.this,ls);
            recycle.setHasFixedSize(true);
            recycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recycle.setAdapter(adapter);


        }



        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("Error");

        }
    });


}
}



