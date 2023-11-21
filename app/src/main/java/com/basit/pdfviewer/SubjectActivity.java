package com.basit.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.basit.pdfviewer.databinding.ActivitySubjectBinding;

public class SubjectActivity extends AppCompatActivity {

    private ActivitySubjectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnChemisrty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubjectActivity.this,MainActivity.class));
            }
        });


    }
}