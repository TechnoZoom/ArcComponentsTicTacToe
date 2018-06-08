package com.kapil.tictactoe;


public class NavigationController {

    private android.support.v4.app.FragmentManager fragmentManager;
    private int containerId;

    public NavigationController(android.support.v4.app.FragmentManager fragmentManager, int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void navigateToBoardFragment() {
        TicTacToeFragment ticTacToeFragment = TicTacToeFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(containerId, ticTacToeFragment)
                .commitAllowingStateLoss();
    }

    public void navigateToResultFragment(String result) {
        ResultFragment resultFragment = ResultFragment.newInstance(result);
        fragmentManager.beginTransaction()
                .replace(containerId, resultFragment)
                .commitAllowingStateLoss();
    }

}
