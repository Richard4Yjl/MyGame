package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TicTacToeActivity extends AppCompatActivity {
    Button[][] Btn = new Button[3][3];
    boolean player1 = true;
    boolean win = false;
    boolean start = false;
    int count = 0;

    boolean check(String check_string) {
        boolean ret = false;
        for (int i = 0; i < 3; i++) {
            boolean row = true;
            boolean col = true;
            for (int j = 0; j < 3; j++) {
                if (Btn[i][j].getText() != check_string) {
                    row = false;
                }
                if (Btn[j][i].getText() != check_string) {
                    col = false;
                }
            }
            ret = ret | row | col;
        }
        ret = ret | (Btn[0][0].getText() == check_string & Btn[1][1].getText() == check_string & Btn[2][2].getText() == check_string)
                | (Btn[0][2].getText() == check_string & Btn[1][1].getText() == check_string & Btn[2][0].getText() == check_string);
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.cl);
        Intent intent = getIntent();
        int each = Math.min((int) (0.6 * height / 3), (int) (0.8 * width / 3));
        int top0 = (int)(height * 0.3);
        int left0 = (width - each * 3) / 2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Btn[i][j] = new Button(TicTacToeActivity.this);
                Btn[i][j].setId(i);
                Btn[i][j].setText(" ");
                Btn[i][j].setTag(i * 3 + j);

                ConstraintLayout.LayoutParams btParams = new ConstraintLayout.LayoutParams(each, each);
                Btn[i][j].setTextSize((float)(each * 0.1));
                btParams.leftToLeft = R.id.cl;
                btParams.topToTop = R.id.cl;
                btParams.topMargin = top0 + each * i;
                btParams.leftMargin = left0 + each * j;
                layout.addView(Btn[i][j], btParams);
                Btn[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!win && start) {
                            int pos = (Integer) view.getTag();
                            int i = pos / 3;
                            int j = pos % 3;
                            TextView result = (TextView) findViewById(R.id.tictactoe_result);
                            if (Btn[i][j].getText() == " " && player1) {
                                Btn[i][j].setText("O");
                                player1 = !player1;
                                result.setText(R.string.player2_turn);
                                count++;
                            }
                            if (Btn[i][j].getText() == " " && !player1) {
                                Btn[i][j].setText("X");
                                player1 = !player1;
                                result.setText(R.string.player1_turn);
                                count++;
                            }
                            if (check("O")) {
                                result.setText(R.string.player1_win);
                                win = true;
                            } else {
                                if (check("X")) {
                                    result.setText(R.string.player2_win);
                                    win = true;
                                } else {
                                    if (count == 9) {
                                        result.setText(R.string.is_draw);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void start_game(View view) {
        if (!start) {
            start = true;
            TextView result = (TextView) findViewById(R.id.tictactoe_result);
            result.setText(R.string.player1_turn);
            count = 0;
        }
    }

    public void restart_game(View view) {
        if (start) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Btn[i][j].setText(" ");
                    TextView result = (TextView) findViewById(R.id.tictactoe_result);
                    result.setText(R.string.player1_turn);
                    player1 = true;
                    win = false;
                    count = 0;
                }
            }
        }
    }
}
