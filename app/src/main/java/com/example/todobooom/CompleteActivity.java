package com.example.todobooom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class CompleteActivity extends AppCompatActivity {
    private TextView completedContent;
    private Button deleteBtn;
    private Button uncheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        completedContent = findViewById(R.id.completedContent);
        deleteBtn = findViewById(R.id.deleteBtn);
        uncheckBtn = findViewById(R.id.uncheckbtn);
        Intent createdMe = getIntent();
        Gson gson = new Gson();
        String json = createdMe.getStringExtra("completedItem");
        final Todo task = gson.fromJson(json, Todo.class);
        completedContent.setText(task.getContent());
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fire.delete_task(task);
                finish();
                return;
            }
        });
        uncheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setIs_done(false);
                MainActivity.fire.edit_tasks(task);
                finish();
                return;
            }
        });
    }
}