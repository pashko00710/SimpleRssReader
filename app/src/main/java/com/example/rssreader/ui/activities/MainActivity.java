package com.example.rssreader.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rssreader.R;
import com.example.rssreader.utils.ConstantsManager;
import com.example.rssreader.utils.RssRead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RssRead readRss;
    Dialog dialog;
    Editable text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        readRss=new RssRead(this,recyclerView, ConstantsManager.TAG_ADRESS);
        readRss.execute();
    }

    @OnClick(R.id.fab)
    public void fabClick() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_add_url_layout);
        final EditText editTextDialog = (EditText) dialog.findViewById(R.id.dialog_input_text);
        Button buttonOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button buttonCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editTextDialog.getText();
                if(!TextUtils.isEmpty(text)) {
                    dialog.dismiss();
                    changeUrl(String.valueOf(text));

                } else {
                    Toast.makeText(getApplicationContext(), R.string.emptylist, Toast.LENGTH_SHORT).show();
                }
            }

        });


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.background_light);
        dialog.show();

    }

    private void changeUrl(String text) {
        readRss = null;
        readRss = new RssRead(this, recyclerView, text);
        readRss.execute();
    }

}
