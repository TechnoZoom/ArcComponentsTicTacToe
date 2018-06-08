package com.kapil.tictactoe;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.kapil.tictactoe.TicTacViewModel.GameStatus.A_WON;
import static com.kapil.tictactoe.TicTacViewModel.GameStatus.B_WON;
import static com.kapil.tictactoe.TicTacViewModel.GameStatus.DRAW;


public class TicTacViewModel extends ViewModel {

    public static final int TOTAL_BOXES = 9;
    public static final int TOTAL_COLUMNS = 3;

    private Boolean[][] boardArray = new Boolean[TOTAL_COLUMNS][TOTAL_COLUMNS];
    private int updateCount = 0;

    public MutableLiveData<Boolean> getIsCrossTurn() {
        return isCrossTurn;
    }

    private MutableLiveData<Boolean> isCrossTurn = new MutableLiveData<>();

    public MutableLiveData<Integer> getGameStatus() {
        return gameStatus;
    }

    private MutableLiveData<Integer> gameStatus = new MutableLiveData<>();

    public void performInitialisations() {
        isCrossTurn.setValue(false);
    }


    public List<TicTacItem> getEmptyListOfBoxes() {
        List<TicTacItem> ticTacItemList = new ArrayList<>();
        for (int i = 0; i < TOTAL_BOXES; i++) {
            TicTacItem ticTacItem = new TicTacItem();
            ticTacItemList.add(ticTacItem);
        }
        return ticTacItemList;
    }

    public void updateBoardArray(int singlePosition) {
        updateCount++;
        int row = getRowFromSinglePosition(singlePosition);
        int column = getColumnFromSinglePosition(singlePosition);
        isCrossTurn.setValue(!isCrossTurn.getValue());
        boardArray[row][column] = isCrossTurn.getValue();
        checkGameStatus();
    }

    private void checkGameStatus() {
        if (isHorizontalOrLeftDiagonalWin(isCrossTurn.getValue())
                || isVerticalWin(isCrossTurn.getValue()) ||
                isRightDiagonalWin(isCrossTurn.getValue())) {
            getGameStatus().setValue(isCrossTurn.getValue() ? A_WON : B_WON);
            return;
        }

        if (updateCount == TOTAL_BOXES) {
            getGameStatus().setValue(DRAW);
        }
    }

    private boolean isHorizontalOrLeftDiagonalWin(Boolean value) {

        int totalCellsMatchedLeftDiagonally = 0;
        for (int i = 0; i < TOTAL_COLUMNS; i++) {
            int totalCellsMatchedHorizontally = 0;
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                if (boardArray[i][j] != null && boardArray[i][j] == value) {
                    totalCellsMatchedHorizontally++;
                    if (totalCellsMatchedHorizontally == TOTAL_COLUMNS) {
                        return true;
                    }
                }

                if (i == j && boardArray[j][i] != null && boardArray[j][i] == value) {
                    totalCellsMatchedLeftDiagonally++;
                    if (totalCellsMatchedLeftDiagonally == TOTAL_COLUMNS) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    private boolean isVerticalWin(Boolean value) {

        for (int i = 0; i < TOTAL_COLUMNS; i++) {
            int totalCellsMatchedVertically = 0;
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                if (boardArray[j][i] != null && boardArray[j][i] == value) {
                    totalCellsMatchedVertically++;
                    if (totalCellsMatchedVertically == TOTAL_COLUMNS) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private boolean isRightDiagonalWin(Boolean value) {
        int totalCellsMatchedRightDiagonally = 0;
        for (int i = 0; i < TOTAL_COLUMNS; i++) {
            if (boardArray[TOTAL_COLUMNS - i - 1][i] != null
                    && boardArray[TOTAL_COLUMNS - i - 1][i] == value) {
                totalCellsMatchedRightDiagonally++;
                if (totalCellsMatchedRightDiagonally == TOTAL_COLUMNS) {
                    return true;
                }

            }
        }
        return false;
    }

    private int getRowFromSinglePosition(int singlePosition) {
        return singlePosition / TOTAL_COLUMNS;
    }

    private int getColumnFromSinglePosition(int singlePosition) {
        return (singlePosition) % TOTAL_COLUMNS;
    }

    public class GameStatus {
        public static final int A_WON = 0;
        public static final int B_WON = 1;
        public static final int DRAW = 2;

    }
}
