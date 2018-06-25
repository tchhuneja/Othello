package com.example.tc.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Obutton grid[][];
    ArrayList<LinearLayout> rows;

    LinearLayout root;

    String currentplayer,otherplayer;
    boolean gameover;
    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root=findViewById(R.id.rootlayout);
        setupgrid();
        gameover=false;
        currentplayer="black";
        otherplayer="white";

        grid[3][3].setPlayer("white");
        grid[3][3].setDisc();


        grid[4][4].setPlayer("white");
        grid[4][4].setDisc();

        grid[3][4].setPlayer("black");
        grid[3][4].setDisc();

        grid[4][3].setPlayer("black");
        grid[4][3].setDisc();
    }

    @Override
    public void onClick(View view) {
        if (!gameover){
            Obutton obutton=(Obutton) view;
            obutton.setRevealed();
            move();
        }
        else
            displaywinner();
    }

    public void setupgrid() {
        rows=new ArrayList<>();
        grid=new Obutton[8][8];
        root.removeAllViews();

        for(int i=0;i<8;i++){
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            root.addView(linearLayout);
            rows.add(linearLayout);
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                Obutton button=new Obutton(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);
                button.setTextSize(80);
                button.setPadding(0,0,0,0);
                button.setBackgroundResource(R.drawable.button_border);
                button.setOnClickListener(this);
                LinearLayout row=rows.get(i);
                row.addView(button);
                grid[i][j]=button;
            }
        }
    }
    public void move() {
        int done=0,isnull;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (grid[i][j].revealed) {
                    //upwards
                    isnull=0;
                    for (int a=i-1;a>=0;a--){
                        if (grid[i-1][j].player == otherplayer){
                            if (grid[a][j].player==currentplayer && isnull==0){
                                for (int b=a+1;b<=i;b++){
                                    grid[b][j].setPlayer(currentplayer);
                                    grid[b][j].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][j].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //bottom
                    isnull=0;
                    for (int a=i+1;a<8;a++){
                        if (grid[i+1][j].player == otherplayer){
                            if (grid[a][j].player==currentplayer && isnull==0){
                                for (int b=a-1;b>=i;b--){
                                    grid[b][j].setPlayer(currentplayer);
                                    grid[b][j].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][j].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //left
                    isnull=0;
                    for (int a=j-1;a>=0;a--){
                        if (grid[i][j-1].player == otherplayer){
                            if (grid[i][a].player==currentplayer && isnull==0){
                                for (int b=a+1;b<=j;b++){
                                    grid[i][b].setPlayer(currentplayer);
                                    grid[i][b].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[i][a].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //right
                    isnull=0;
                    for (int a=j+1;a<8;a++){
                        if (grid[i][j+1].player == otherplayer){
                            if (grid[i][a].player==currentplayer && isnull==0){
                                for (int b=a-1;b>=j;b--){
                                    grid[i][b].setPlayer(currentplayer);
                                    grid[i][b].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[i][a].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //up-left
                    isnull=0;
                    for ( int a=i-1,b =j-1;a>=0 && b>=0;a--,b--){
                        if (grid[i-1][j-1].player == otherplayer){
                            if (grid[a][b].player==currentplayer && isnull==0){
                                for (int c=a+1,d=b+1;c<=i && d<=j;c++,d++){
                                    grid[c][d].setPlayer(currentplayer);
                                    grid[c][d].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][b].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //up-right
                    isnull=0;
                    for ( int a=i-1,b =j+1;a>=0 && b<8;a--,b++){
                        if (grid[i-1][j+1].player == otherplayer){
                            if (grid[a][b].player==currentplayer && isnull==0){
                                for (int c=a+1,d=b-1;c<=i && d>=j;c++,d--){
                                    grid[c][d].setPlayer(currentplayer);
                                    grid[c][d].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][b].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //bottom-left
                    isnull=0;
                    for ( int a=i+1,b =j-1;a<8 && b>=0;a++,b--){
                        if (grid[i+1][j-1].player == otherplayer){
                            if (grid[a][b].player==currentplayer && isnull==0){
                                for (int c=a-1,d=b+1;c>=i && d<=j;c--,d++){
                                    grid[c][d].setPlayer(currentplayer);
                                    grid[c][d].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][b].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    //bottom-right
                    isnull=0;
                    for ( int a=i+1,b =j+1;a<8 && b<8;a++,b++){
                        if (grid[i+1][j+1].player == otherplayer){
                            if (grid[a][b].player==currentplayer && isnull==0){
                                for (int c=a-1,d=b-1;c>=i && d>=j;c--,d--){
                                    grid[c][d].setPlayer(currentplayer);
                                    grid[c][d].setDisc();
                                    done=1;
                                }
                            }
                            else if (grid[a][b].player==null)
                                isnull=1;
                        }
                        else break;
                    }
                    grid[i][j].revealed=false;
                }
            }
        }
        if (done==1)
            toggleplayer();
    }
    public void displaywinner(){
        if (winner=="white")
            Toast.makeText(this,"Player 2 Won",Toast.LENGTH_LONG).show();
        else if (winner=="dark")
            Toast.makeText(this,"Player 1 Won",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();
    }
    public void toggleplayer(){
     String temp;
     temp=currentplayer;
     currentplayer=otherplayer;
     otherplayer=temp;
    }
}