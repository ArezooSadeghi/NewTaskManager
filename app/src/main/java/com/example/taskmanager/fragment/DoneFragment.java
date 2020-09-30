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

public class DoneFragment extends Fragment {
    private RecyclerView mRecyclerViewDone;

    public DoneFragment() {
    }

    public static DoneFragment newInstance() {
        DoneFragment fragment = new DoneFragment();
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

        View view = inflater.inflate(R.layout.fragment_done, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void findViews(View view) {
        mRecyclerViewDone = view.findViewById(R.id.done_recyclerview);
    }

    private void initViews() {
        mRecyclerViewDone.setLayoutManager(new LinearLayoutManager(getActivity()));
        Task task = new Task();
        task.setTitle("Task Two");
        task.setState(State.DOING);
        task.setDescription("Task is Done");
        task.setDate(new Date());
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        DoneAdapter doneAdapter = new DoneAdapter(tasks);
        mRecyclerViewDone.setAdapter(doneAdapter);
    }

    public class DoneViewHolder extends RecyclerView.ViewHolder {
        public static final String DIALOG_DONE_FRAGMENT = "DIALOG DONE FRAGMENT";
        private TextView mTextViewTitle;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_title_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InformationOfTaskFragment informationOfTaskFragment = InformationOfTaskFragment.newInstance();
                    informationOfTaskFragment.show(getActivity().getSupportFragmentManager(), DIALOG_DONE_FRAGMENT);
                }
            });
        }

        public void bindTask(Task task) {
            mTextViewTitle.setText(task.getTitle());
        }
    }

    public class DoneAdapter extends RecyclerView.Adapter<DoneViewHolder> {
        private List<Task> mTasks;

        public DoneAdapter(List<Task> tasks) {
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
        public DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.task_item, parent, false);
            DoneViewHolder doneViewHolder = new DoneViewHolder(view);
            return doneViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoneViewHolder holder, int position) {
            holder.bindTask(mTasks.get(position));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}