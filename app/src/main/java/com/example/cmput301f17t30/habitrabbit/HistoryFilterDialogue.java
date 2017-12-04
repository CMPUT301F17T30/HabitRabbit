/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Adam on 02-Dec-17.
 */

public class HistoryFilterDialogue extends Dialog {

    private HabitHistoryActivity activity;
    private EditText searchText;
    private HistoryFilterDialogue thisDialog;

    private Integer searchBy = 0;
    private String textString;

    public HistoryFilterDialogue(HabitHistoryActivity context) {
        super(context);
        this.activity = context;
        this.thisDialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialogue_popup);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        initalize();
    }

    private void initalize() {
        searchText = findViewById(R.id.search_text);

        final RadioGroup filterGroup = findViewById(R.id.radio_filter_group);


        Button cancel = findViewById(R.id.search_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDialog.cancel();
            }
        });

        Button search = findViewById(R.id.search_confirm);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textString = searchText.getText().toString();

                if (textString.trim().length() == 0){
                    Toast.makeText(activity, "Search field cannot be blank", Toast.LENGTH_SHORT).show();
                }
                else{
                    int radioButtonID = filterGroup.getCheckedRadioButtonId();
                    View radioButton = filterGroup.findViewById(radioButtonID);
                    searchBy = filterGroup.indexOfChild(radioButton);
                    activity.filterHistoryList(textString,searchBy);
                    thisDialog.cancel();
                }

            }
        });
    }
}
