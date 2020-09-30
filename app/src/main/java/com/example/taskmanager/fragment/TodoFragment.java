package com.example.taskmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.InformationOfTaskFragment;
import com.example.taskmanager.R;
import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoFragment extends Fragment {
    private RecyclerView mRecyclerViewTodo;

    public TodoFragment() {
    }

    public static TodoFragment newInstance() {
        TodoFragment fragment = new TodoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void findViews(View view) {
        mRecyclerViewTodo = view.findViewById(R.id.todo_recyclerview);
    }

    private void initViews() {
        mRecyclerViewTodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        Task task = new Task();
        task.setTitle("Task Three");
        task.setState(State.DOING);
        task.setDescription("Task is Todo");
        task.setDate(new Date());
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        TodoAdapter todoAdapter = new TodoAdapter(tasks);
        mRecyclerViewTodo.setAdapter(todoAdapter);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        public static final String DIALOG_TODO_FRAGMENT = "DIALOG TODO FRAGMENT";
        private TextView mTextViewTitle;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_title_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InformationOfTaskFragment informationOfTaskFragment = InformationOfTaskFragment.newInstance();
                    informationOfTaskFragment.show(getActivity().getSupportFragmentManager(), DIALOG_TODO_FRAGMENT);
                }
            });
        }

        public void bindTask(Task task) {
            mTextViewTitle.setText(task.getTitle());
        }
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
        private List<Task> mTasks;

        public TodoAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public List<Task> getTasks() {
            return mTasks;
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.task_item, parent, false);
            TodoViewHolder todoViewHolder = new TodoViewHolder(view);
            return todoViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            holder.bindTask(mTasks.get(position));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}