package com.myapplicationdev.android.p11mydatabook;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BioFragment extends Fragment {

    Button btnEdit;
    TextView tvDisplay;
    SharedPreferences sp;
    public static final String USER_PREF = "USER_PREF";

    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);
        tvDisplay = view.findViewById(R.id.tvDisplay);
        btnEdit = view.findViewById(R.id.btnEdit);

        sp = getActivity().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Get the layout inflater
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialogbox, null);

                final EditText etDialog = (EditText) dialogView.findViewById(R.id.etDialog);

                builder.setView(dialogView)
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String text = etDialog.getText().toString();
                                tvDisplay.setText(text);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });


                builder.show();

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("bio", tvDisplay.getText().toString());
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        String bioText = sp.getString("bio","");
        tvDisplay.setText(bioText);
    }

}
