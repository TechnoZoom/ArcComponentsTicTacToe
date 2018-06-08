package com.kapil.tictactoe;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class TicTacToeInstrumentationTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true,
            false);


    @Before
    public void setUp() {
        mActivityTestRule.launchActivity(new Intent());
    }

    @Test
    public void testPlayerOnesWinInFirstColumn() {
        clickOnTicTacBoardCells(new int[]{0, 1, 3, 4, 6});
        String expectedText = InstrumentationRegistry.getTargetContext().getString(R.string.a_won);
        onView(allOf(withId(R.id.gme_rslt_tv), withText(expectedText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testPlayerOnesWinInRightDiagonal() {
        clickOnTicTacBoardCells(new int[]{2, 1, 4, 7, 6});
        String expectedText = InstrumentationRegistry.getTargetContext().getString(R.string.a_won);
        onView(allOf(withId(R.id.gme_rslt_tv), withText(expectedText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testPlayerOnesWinInThirdRow() {
        clickOnTicTacBoardCells(new int[]{6, 1, 7, 3, 8});
        String expectedText = InstrumentationRegistry.getTargetContext().getString(R.string.a_won);
        onView(allOf(withId(R.id.gme_rslt_tv), withText(expectedText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testPlayerTwosWinInThirdRow() {
        clickOnTicTacBoardCells(new int[]{1, 6, 3, 7, 5, 8});
        String expectedText = InstrumentationRegistry.getTargetContext().getString(R.string.b_won);
        onView(allOf(withId(R.id.gme_rslt_tv), withText(expectedText)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testDraw() {
        clickOnTicTacBoardCells(new int[]{0, 1, 2, 5, 8, 6, 3, 4, 7});
        String expectedText = InstrumentationRegistry.getTargetContext().getString(R.string.draw);
        onView(allOf(withId(R.id.gme_rslt_tv), withText(expectedText)))
                .check(matches(isDisplayed()));
    }
    
    private void clickOnTicTicBoardCell(int position) {
        onView(withId(R.id.board_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    private void clickOnTicTacBoardCells(int[] positionsArray) {
        for(int i = 0; i <positionsArray.length; i++ ) {
            clickOnTicTicBoardCell(positionsArray[i]);
        }
    }
}
