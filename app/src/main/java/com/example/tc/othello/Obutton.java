package com.example.tc.othello;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;

public class Obutton extends AppCompatButton{

    String player=null;
    public boolean revealed=false;

    public Obutton(Context context) {
        super(context);
    }
    public void setPlayer(String a){
        this.player=a;
    }

    public void setDisc(){
        if(this.player=="white") {
            setText("●");
            setTextColor(Color.parseColor("white"));
            setEnabled(false);
        }
        if(this.player=="black") {
            setText("●");
            setTextColor(Color.parseColor("black"));
            setEnabled(false);
        }
    }

    public void setRevealed(){
        this.revealed=true;
    }
}