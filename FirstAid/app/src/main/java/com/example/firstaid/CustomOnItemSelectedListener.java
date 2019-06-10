package com.example.firstaid;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
            ((TextView) parent.getChildAt(0)).setTextColor(0x00000000);
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
