package com.example.ticktactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] gameState ={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;
        //counter.getTag();
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource((R.drawable.circle));

                activePlayer = 1;

            } else {
                counter.setImageResource((R.drawable.cross));
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPosition) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //when someone has won!
                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Circle";

                    } else {
                        winner = "Cross";
                    }

                    Button playAgainButton = findViewById(R.id.button);

                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }


        public void playAgain(View view){

            Button playAgainButton = findViewById(R.id.button);

            TextView winnerTextView = findViewById(R.id.winnerTextView);

            playAgainButton.setVisibility(View.INVISIBLE);

            winnerTextView.setVisibility(View.INVISIBLE);

            GridLayout gridLayout = findViewById(R.id.gridLayout);

            for (int i=0; i<gridLayout.getChildCount(); i++){

                ImageView counter = (ImageView) gridLayout.getChildAt(i);

                counter.setImageDrawable(null);
            }

            for (int i=0; i<gameState.length; i++){

                gameState[i] = 2;
            }
            activePlayer = 0;

            gameActive = true;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}