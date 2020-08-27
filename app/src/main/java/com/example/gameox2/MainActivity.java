package com.example.gameox2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int colorItem = 0;
    int[] boardStatus = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2},{2,5,8},{6,7,8},{0,3,6},{0,4,8},{2,4,6},{3,4,5},{1,4,7}};
    int winnerCheck = 0;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void playAgain(View view)
    {
        colorItem = 0;
        winnerCheck = 0;
        LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
        GridLayout Board = findViewById(R.id.GridLayout);

        for(int i=0;i<9;i++)
        {
            ImageView gridChild = (ImageView) Board.getChildAt(i);
           gridChild.setImageResource(0);
            gridChild.animate().translationY(-50);
           boardStatus[i] = 2;
        }

        playAgainLayout.setVisibility(View.INVISIBLE);
    }

    public void dropItem(View view)
    {
        // Check Game is over or not
        gameOver = true;
        // 0 = red, 1 = yellow
        ImageView Item = (ImageView) view;
        if(colorItem == 0)
        {
            if(boardStatus[Integer.parseInt(Item.getTag().toString())] == 2 && winnerCheck == 0)
            {
                Item.animate().translationY(-100);
                Item.setImageResource(R.drawable.red);
                Item.animate().translationYBy(100).rotation(3600).setDuration(300);
                colorItem = 1;
                boardStatus[Integer.parseInt(Item.getTag().toString())] = 0;
            }
        }
        else
        {
            if(boardStatus[Integer.parseInt(Item.getTag().toString())] == 2 && winnerCheck == 0)
            {
                Item.animate().translationY(-100);
                Item.setImageResource(R.drawable.yellow);
                Item.animate().translationYBy(100).rotation(3600).setDuration(300);
                colorItem = 0;
                boardStatus[Integer.parseInt(Item.getTag().toString())] = 1;
            }
        }

        if(winnerCheck == 0) {
            for (int i = 0; i < 8; i++)
            {
                if (boardStatus[winningPosition[i][0]] == boardStatus[winningPosition[i][1]] && boardStatus[winningPosition[i][1]] == boardStatus[winningPosition[i][2]] && boardStatus[winningPosition[i][0]] != 2 && winnerCheck == 0)
                {
                    winnerCheck = 1;
                    TextView winnerText = findViewById(R.id.winnerText);
                    LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                    if(boardStatus[winningPosition[i][0]] == 0)
                    {
                        winnerText.setText("The Winner is red" );
                    }
                    else if(boardStatus[winningPosition[i][0]] == 1)
                    {
                        winnerText.setText("The Winner is yellow" );
                    }
                    playAgainLayout.setVisibility(View.VISIBLE);
                }

            }
            for(int i=0;i<9;i++)
            {
                if(boardStatus[i] == 2)
                {
                    gameOver = false;
                }

            }
            if(gameOver)
            {
                TextView winnerText = findViewById(R.id.winnerText);
                LinearLayout playAgainLayout = findViewById(R.id.playAgainLayout);
                winnerText.setText("Draw" );
                playAgainLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}