package com.example.bankuishapp.representation.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;

import com.example.bankuishapp.R;


public class MyProgressDialog {
    Activity context;
    Dialog alert;


    public MyProgressDialog(Activity myfragment) {
        this.context = myfragment;

    }
    public void startProgressDialog(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 alert= new Dialog(context,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
                LayoutInflater inflater = context.getLayoutInflater();
                alert.setContentView(inflater.inflate(R.layout.progress_dialog_geenral,null));
                alert.setCancelable(false);
                alert.show();
            }
        });

    }
    public void stopProgressDialog(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (alert != null && alert.isShowing()) {
                    alert.dismiss();
                }

            }
        });
    }

}
