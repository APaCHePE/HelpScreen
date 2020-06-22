package com.apachepe.helpscreen;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class HelpRentabilidad extends ConstraintLayout {
    public HelpRentabilidad(Context context) {
        // Required empty public constructor
        super(context);
        initialize(context);
    }

    public HelpRentabilidad(Context contextR, AttributeSet attributeSet){
        super(contextR,attributeSet);
        initialize(contextR);
    }
    private void initialize(Context context){
        inflate(context,R.layout.help_rentabilidad, this);
    }
}
