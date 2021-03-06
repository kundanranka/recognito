package com.example.user.recognito.Activities.DashBoardActivityPack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.recognito.DataModels.DataBaseSongModel;
import com.example.user.recognito.Fragments.DashBoardFragment;
import com.example.user.recognito.Fragments.ErrorFragments.EmptyHistory;
import com.example.user.recognito.R;
import com.example.user.recognito.Utils.ToastMessageUtil;

import java.util.List;

/**
 * Created by emmanuel on 3/5/2018.
 */



public class DashBoardActivity extends AppCompatActivity implements DashBoardContract.View, DashBoardFragment.OnDashBoardFragmentListener{
    private FragmentManager fragmentManager;
    private Presenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fragmentManager = getSupportFragmentManager();

        presenter = new Presenter(this, getApplicationContext());
        presenter.getSongFromDb();

    }

    public void addFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.dash_board_frag_container, fragment);
        ft.commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.dash_board_frag_container, fragment);
        ft.commit();
    }

    public void removeFragment(Fragment fragment){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void fetchedSongs(List<DataBaseSongModel> baseSongModelList){
        if (baseSongModelList.size() <= 0){
            EmptyHistory emptyHistory = new EmptyHistory();
            replaceFragment(emptyHistory);
        }else {
            DashBoardFragment fragment = DashBoardFragment.newInstance(baseSongModelList);
            addFragment(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_board_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settings:
                //open the settings activity
                ToastMessageUtil.getToastMessage(this, "Settings");
                break;
            case R.id.about:
                //open the about activity
                ToastMessageUtil.getToastMessage(this, "About");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
