package com.example.todobooom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {
    public static com.example.todobooom.TodoAdaptor adapter = new TodoAdaptor();
    public static FireBaseManager fire = new FireBaseManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp app = (MyApp) getApplicationContext();
//        FireBaseManager fire = new FireBaseManager();
        Button btn = (Button)findViewById(R.id.btn);
        EditText edt = (EditText) findViewById(R.id.edtTxt);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        btn.setOnClickListener(new SimpleListner(this, (TodoAdaptor)adapter, fire, edt));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false));

    }

}

class SimpleListner implements View.OnClickListener{
    private Context context;
    private EditText edt;
    private FireBaseManager fire;
    private TodoAdaptor adapter;


    public SimpleListner(Context context, TodoAdaptor adapter, FireBaseManager fire, EditText edt){
        this.adapter = adapter;
        this.edt = edt;
        this.fire = fire;
        this.context = context;
    }
    public void onClick(View view){
        String txt = edt.getText().toString();
        if(txt==null || txt.isEmpty())
        {
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, "You can't create an empty task", duration).show();
            return;
        }

        adapter.addTask(txt, fire);
        edt.getText().clear();
    }
}
