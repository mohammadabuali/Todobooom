package com.example.todobooom;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TodoAdaptor extends RecyclerView.Adapter<TodoAdaptor.TodoViewHolder> {
    public static class TodoViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        private ImageView img;



        TodoViewHolder(View view){
            super(view);
            text = itemView.findViewById(R.id.todo_description);
            img = itemView.findViewById(R.id.todo_icon);

        }
    }
    private static List<Todo> items = new ArrayList<Todo>();

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.task_line, parent, false);
        final TodoViewHolder holder = new TodoViewHolder(view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = holder.getLayoutPosition();
                Todo tr = (Todo) items.get(position);
                if(!tr.getIs_done()){
//                    tr.setIs_done(true);
//                    CharSequence text = "Task "+tr.getContent()+" has been cleared!";
//                    int duration = Toast.LENGTH_SHORT;
//                    Toast.makeText(context, text, duration).show();
                    incompleteActivity(context, tr);
//                    if(tr.getIs_done()){
//
//                    holder.img.setImageResource(R.drawable.done_icon);
//                    holder.itemView.setBackgroundResource(R.drawable.back_done);
//                    }
//                    saveData(context);
                }
                else{
                    completeActivity(context, tr);
                }
            }
        });
        return holder;
    }

    public void incompleteActivity(Context context, Todo task){
        Intent intent = new Intent(context, IncompleteActivity.class);
        Gson gson = new Gson();
        String item = gson.toJson(task);
        intent.putExtra("item", item);
        context.startActivity(intent);
    }
    public void completeActivity(Context context, Todo task){
        Intent intent = new Intent(context, CompleteActivity.class);
        Gson gson = new Gson();
        String item = gson.toJson(task);
        intent.putExtra("completedItem", item);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo item = (Todo) items.get(position);
        holder.text.setText(item.getContent());
        if (item.getIs_done()){
            holder.img.setImageResource(R.drawable.done_icon);
            holder.itemView.setBackgroundResource(R.drawable.back_done);
        }
        else{
            holder.img.setImageResource(0);
            holder.itemView.setBackgroundResource(0);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addTask(String description, FireBaseManager fire) {
//        ArrayList<Todo> todoLst = fire.getTodoList();
        Todo task = new Todo(description, false);
        fire.add_task(task);
        items = fire.getTodoList();
        notifyDataSetChanged();


//        Todo tr = new Todo(description, false);
//        items.add(tr);
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(fire);
//        Gson gson = new Gson();
//        String json = gson.toJson(items);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("kkk", json).apply();
//        notifyDataSetChanged();
    }
    public void removeTask(TodoViewHolder holder, Context context){
        return;
    }
//    public void setArray(Context context)
//    {
//
//
//
//        Gson gson = new Gson();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//        String json = sp.getString("kkk", null);
//        Type type = new TypeToken<ArrayList<Todo>>(){}.getType();
//        items = gson.fromJson(json, type);
//        if(items == null){
//            items = new ArrayList<Todo>();
//        }
////        items = arr;
//        notifyDataSetChanged();
//    }

    public void setArray(FireBaseManager fire){
        items = fire.getTodoList();
        notifyDataSetChanged();
    }


    public void saveData(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = gson.toJson(items);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("kkk", json).apply();
        notifyDataSetChanged();
    }
}
