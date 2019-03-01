package com.darrahts.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {-1, -1, -1,
                        -1, -1, -1,
                        -1, -1, -1};

    int[][] wins = {{0,1,2},
                    {3,4,5},
                    {6,7,8},
                    {0,3,6},
                    {1,4,7},
                    {2,5,8},
                    {0,4,8},
                    {2,4,6}};

    public static int turn = 0;

    public static boolean gameActive = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int c = Integer.parseInt(counter.getTag().toString());
        if(gameState[c] == -1 && gameActive)
        {
            gameState[c] = turn;
            if (turn == 0) {
                counter.setImageResource(R.drawable.blue);
                turn = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                turn = 0;
            }

            counter.setTranslationY(-1000);
            counter.animate().translationYBy(1000).rotationX(3600f).rotationY(3600f).setDuration(300);

            for (int[] pos : wins)
            {
                if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != -1)
                {
                    String winner = "";
                    if (turn == 1)
                    {
                        winner = "blue";
                    } else
                    {
                        winner = "red";
                    }
                    String msg = winner + " wins the game!";
                    Log.i("Info", msg);
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainbutton);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(msg);
                    playAgainButton.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgainbutton);
        TextView textView = (TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setImageDrawable(null);
        }
        for(int i = 0; i < gameState.length; i++)
        {
            gameState[i] = -1;
        }
        turn = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
