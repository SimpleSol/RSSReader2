package com.example.leon.rssreader2.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.content.Channel;
import com.example.leon.rssreader2.service.SyncService;

/**
 * Created by Leon on 01.12.2015.
 */
public class AddChannelDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText input = new EditText(getActivity());
        return new AlertDialog.Builder(getActivity())
                .setView(input)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Editable url = input.getText();
                        if (!TextUtils.isEmpty(url)) {
                            final ContentValues values = new ContentValues();
                            values.put(Channel.Columns.URL, url.toString());
                            getActivity().getContentResolver().insert(Channel.URI, values);
                            getActivity().startService(
                                    new Intent(getActivity().getApplicationContext(), SyncService.class)
                            );
                        } else {
                            input.setError(getString(R.string.enter_channel_address));
                            input.requestFocus();
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
















