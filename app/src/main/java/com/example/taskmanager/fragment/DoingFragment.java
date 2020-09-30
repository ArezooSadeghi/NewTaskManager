package com.example.taskmanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class DoingFragment extends Fragment {
    private RecyclerView mRecyclerViewDoing;
    private LinearLayout mLinearLayout;

    public DoingFragment() {
    }

    public static DoingFragment newInstance() {
        DoingFragment fragment = new DoingFragment();
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

        View view = inflater.inflate(R.layout.fragment_doing, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void findViews(View view) {
        mRecyclerViewDoing = view.findViewById(R.id.doing_recyclerview);
        mLinearLayout = view.findViewById(R.id.empty_recyclerview);
    }


    private void initViews() {
        mRecyclerViewDoing.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Task> tasks = new ArrayList<>();
        /*Task task = new Task();
        task.setTitle("Task One");
        task.setState(State.DOING);
        task.setDescription("Task is Doing");
        task.setDate(new Date());
        tasks.add(task);*/
        if (!(tasks.size() == 0)) {
            DoingAdapter doingAdapter = new DoingAdapter(tasks);
            mRecyclerViewDoing.setAdapter(doingAdapter);
        } else {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    public class DoingViewHolder extends RecyclerView.ViewHolder {
        public static final String DIALOG_DOING_FRAGMENT = "DIALOG DOING FRAGMENT";
        private TextView mTextViewTitle;

        public DoingViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.txt_title_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InformationOfTaskFragment informationOfTaskFragment = InformationOfTaskFragment.newInstance();
                    informationOfTaskFragment.show(getActivity().getSupportFragmentManager(), DIALOG_DOING_FRAGMENT);
                }
            });
        }

        public void bindTask(Task task) {
            mTextViewTitle.setText(task.getTitle());
        }
    }

    public class DoingAdapter extends RecyclerView.Adapter<DoingViewHolder> {
        private List<Task> mTasks;

        public DoingAdapter(List<Task> tasks) {
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
        public DoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.task_item, parent, false);
            DoingViewHolder doingHolder = new DoingViewHolder(view);
            return doingHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoingViewHolder holder, int position) {
            holder.bindTask(mTasks.get(position));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }


}