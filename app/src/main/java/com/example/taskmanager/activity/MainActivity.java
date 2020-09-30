package com.example.taskmanager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.taskmanager.AddNewTaskFragment;
import com.example.taskmanager.R;
import com.example.taskmanager.fragment.DoingFragment;
import com.example.taskmanager.fragment.DoneFragment;
import com.example.taskmanager.fragment.TodoFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ADD_NEW_TASK_FRAGMENT = "ADD_NEW_TASK_FRAGMENT";
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private FloatingActionButton mActionButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        mActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTaskFragment addNewTaskFragment = AddNewTaskFragment.newInstance();
                addNewTaskFragment.show(getSupportFragmentManager(), ADD_NEW_TASK_FRAGMENT);

            }
        });
        initViews();
    }

    private void findViews() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        mActionButtonAdd = findViewById(R.id.add_fab);
    }

    private void initViews() {
        mViewPager.setAdapter(new TaskPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Todo");
                        break;
                    case 1:
                        tab.setText("Doing");
                        break;
                    default:
                        tab.setText("Done");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

    }

    private class TaskPagerAdapter extends FragmentStateAdapter {

        public TaskPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return TodoFragment.newInstance();
                case 1:
                    return DoingFragment.newInstance();
                default:
                    return DoneFragment.newInstance();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }


    }
}