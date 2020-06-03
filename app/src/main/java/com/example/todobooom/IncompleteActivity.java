package com.example.todobooom;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class IncompleteActivity extends AppCompatActivity {
    private String firstTask;
    private TextView content;
    private ImageView editedImg;
    private Button apply;
    private Button done;
    private TextView creationTime;
    private TextView editTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomplete);

        content = (TextView) findViewById(R.id.content);
        creationTime = (TextView) findViewById(R.id.creationTime);
        editTime = (TextView) findViewById(R.id.editTime);
        editedImg = (ImageView) findViewById(R.id.editedImg);
        apply = (Button) findViewById(R.id.apply);
        done = (Button) findViewById(R.id.done);
        Intent createdMe = getIntent();
        Gson gson = new Gson();
        String json = createdMe.getStringExtra("item");
        final Todo task = gson.fromJson(json, Todo.class);
        firstTask = task.getContent();
        content.setText(task.getContent());
        creationTime.setText(task.getCreation_timestamp().toString());
        editTime.setText(task.getEdit_timestamp().toString());

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content.getText().toString() == null || content.getText().toString().isEmpty())
                {
                    CharSequence text = "Task description is empty";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(IncompleteActivity.this, text, duration).show();
                }
                else{

                if(!content.getText().toString().equals(firstTask))
                {

                task.setContent(content.getText().toString());
                task.set_edit_time_stamp();
                editTime.setText(task.getEdit_timestamp().toString());
                editedImg.setImageResource(R.drawable.edit_icon);
                editedImg.setVisibility(View.VISIBLE);
                MainActivity.fire.edit_tasks(task);
                }
                else{
                    CharSequence text = "Task was not changed";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(IncompleteActivity.this, text, duration).show();
                }
                }

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setIs_done(true);
                MainActivity.fire.edit_tasks(task);
                finish();

                return;
            }
        });
    }
}