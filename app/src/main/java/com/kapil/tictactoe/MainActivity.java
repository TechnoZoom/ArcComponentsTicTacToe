package com.kapil.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationController = new NavigationController(getSupportFragmentManager(),R.id.container);
        navigationController.navigateToBoardFragment();
    }
}
