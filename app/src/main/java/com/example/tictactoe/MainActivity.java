package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player=0;
    boolean gameActive=true;
    //0 .Yellow 1. Red 2. blank or empty cell
    int state[]={2,2,2,2,2,2,2,2,2};
    int winPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView coin = (ImageView) view;

        int tappedCell = Integer.parseInt(coin.getTag().toString());
        if (state[tappedCell] == 2 && gameActive) {
            coin.setTranslationY(-1500);
            state[tappedCell] = player;
            if (player == 0) {
                coin.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                coin.setImageResource(R.drawable.red);
                player = 0;
            }
            coin.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int position[] : winPositions) {
                if (state[position[0]] == state[position[1]] && state[position[1]] == state[position[2]] && state[position[0]] != 2) {
                    String winner = "";
                    if (player == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won the game", Toast.LENGTH_SHORT).show();
                    Button playagain=(Button)findViewById(R.id.playAgain);
                    TextView winnerText=(TextView) findViewById(R.id.winnerView);
                    winnerText.setText(winner+" has won");
                    playagain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                    gameActive=false;
                }
            }
        }
    }

    public void play(View view)
    {
        Button playagain=(Button)findViewById(R.id.playAgain);
        TextView winnerText=(TextView) findViewById(R.id.winnerView);
        playagain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<state.length;i++)
        {
            state[i]=2;
        }
        player=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
