package com.basit.pdfviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.basit.pdfviewer.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<PdfClass> pdfClassArrayList;
    private PdfAdapter pdfAdapter;

    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pdfClassArrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("pdf");


        getData();
    }

    private void getData() {

        databaseReference.child("ClassNine").child("Chemistry").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {

                    for (DataSnapshot dataSnapshot :snapshot.getChildren())
                    {
                        PdfClass pdfClass = dataSnapshot.getValue(PdfClass.class);

                        pdfClassArrayList.add(pdfClass);
                    }

                    pdfAdapter = new PdfAdapter(MainActivity.this, pdfClassArrayList);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    binding.recycler.setAdapter(pdfAdapter);
                    pdfAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Exist", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "no " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}