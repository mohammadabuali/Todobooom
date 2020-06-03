package com.example.todobooom;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
public class FireBaseManager {
    public HashMap<String, Todo> todoMap = new HashMap<>();

    FireBaseManager(){
            FirebaseFirestore free = FirebaseFirestore.getInstance();
            CollectionReference cRef = free.collection("todobooom");
            cRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                    @Nullable FirebaseFirestoreException e) {

                    todoMap.clear();
                    if(queryDocumentSnapshots==null){
                        return;
                    }
                    for(DocumentSnapshot ds: queryDocumentSnapshots.getDocuments()){
                        Todo task = ds.toObject(Todo.class);
                        todoMap.put(task.getId(), task);
                    }
                    MainActivity.adapter.setArray(FireBaseManager.this);

                }

            });
        }



    public void add_task(Todo task){
        todoMap.put(task.getId(), task);
        FirebaseFirestore.getInstance().collection("todobooom").document(task.getId())
                .set(task);
    }


    public ArrayList<Todo> getTodoList(){
        ArrayList<Todo> lst = new ArrayList<>(todoMap.values());
        return lst;
    }
    public void edit_tasks(Todo task){
        task.set_edit_time_stamp();
        todoMap.remove(task.getId());
        todoMap.put(task.getId(), task);
        FirebaseFirestore.getInstance().collection("todobooom").document(task.getId())
                .set(task);
    }
    public void delete_task(Todo task){
        todoMap.remove(task.getId());
        FirebaseFirestore.getInstance().collection("todobooom").document(task.getId())
                .delete();

    }



}
