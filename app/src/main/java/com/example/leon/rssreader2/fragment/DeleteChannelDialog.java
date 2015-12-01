package com.example.leon.rssreader2.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.Channel;

/**
 * Created by Leon on 01.12.2015.
 */
public class DeleteChannelDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.are_you_sure)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getContentResolver().delete(Channel.URI,
                                Channel.Columns._ID + "=?",
                                new String[]{String.valueOf(getArguments().getLong(Channel.Columns._ID))});
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

}
