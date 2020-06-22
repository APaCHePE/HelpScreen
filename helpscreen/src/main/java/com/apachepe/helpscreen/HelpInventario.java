package com.apachepe.helpscreen;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;


public class HelpInventario extends ConstraintLayout {

    public HelpInventario(Context context) {
        // Required empty public constructor
        super(context);
        initialize(context);
    }

    public HelpInventario(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        initialize(context);
    }
    private void initialize(Context context){
        inflate(context,R.layout.help_inventario, this);
    }

}
