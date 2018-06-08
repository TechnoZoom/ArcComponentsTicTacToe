package com.kapil.tictactoe;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kapil.tictactoe.databinding.FragmentTicTacToeBinding;


public class TicTacToeFragment extends Fragment implements TicTacRecyclerAdapter.TileCallBacks {

    private FragmentTicTacToeBinding fragmentTicTacToeBinding;
    public static final String PLAYER_1_NAME = "A";
    public static final String PLAYER_2_NAME = "B";
    private TicTacRecyclerAdapter ticTacRecyclerAdapter;
    private TicTacViewModel ticTacViewModel;
    private NavigationController navigationController;


    public TicTacToeFragment() {
    }


    public static TicTacToeFragment newInstance() {
        TicTacToeFragment ticTacToeFragment = new TicTacToeFragment();
        return ticTacToeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentTicTacToeBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_tic_tac_toe, container, false);
        return fragmentTicTacToeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigationController = new NavigationController(getActivity().getSupportFragmentManager(), R.id.container);
        ticTacViewModel = ViewModelProviders.of(this).get(TicTacViewModel.class);
        setTicTacRecyclerView();
        fragmentTicTacToeBinding.whosTurnTv.setText(getString(R.string.whos_turn,PLAYER_1_NAME));
        ticTacViewModel.performInitialisations();
        setCallBacksAndListeners();
    }

    private void setCallBacksAndListeners() {
        ticTacViewModel.getIsCrossTurn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isCrossTurn) {
                if (isCrossTurn == null) {
                    return;
                }
                if (isCrossTurn) {
                    fragmentTicTacToeBinding.whosTurnTv.setText(getString(R.string.whos_turn, PLAYER_2_NAME));
                } else {
                    fragmentTicTacToeBinding.whosTurnTv.setText(getString(R.string.whos_turn, PLAYER_1_NAME));
                }
            }
        });

        ticTacViewModel.getGameStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer gameStatus) {
                String result = "";
                switch (gameStatus) {
                    case TicTacViewModel.GameStatus.A_WON:
                        result = getString(R.string.a_won);
                        break;
                    case TicTacViewModel.GameStatus.B_WON:
                        result = getString(R.string.b_won);
                        break;
                    case TicTacViewModel.GameStatus.DRAW:
                        result = getString(R.string.draw);
                        break;
                }

                navigationController.navigateToResultFragment(result);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setTicTacRecyclerView() {
        ticTacRecyclerAdapter = new TicTacRecyclerAdapter(ticTacViewModel.getEmptyListOfBoxes(), this);
        fragmentTicTacToeBinding.boardRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        fragmentTicTacToeBinding.boardRv.setAdapter(ticTacRecyclerAdapter);
    }

    @Override
    public void onTileClicked(TicTacItem ticTacItem, int position) {
        if (ticTacItem.getStatus() == null) {
            ticTacItem.setStatus(!ticTacViewModel.getIsCrossTurn().getValue());
            ticTacRecyclerAdapter.setItem(ticTacItem, position);
            ticTacViewModel.updateBoardArray(position);
        }
    }
}
