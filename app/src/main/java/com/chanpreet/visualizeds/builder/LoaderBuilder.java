package com.chanpreet.visualizeds.builder;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chanpreet.visualizeds.R;

public class LoaderBuilder {
    public static Dialog build(Context context, String title) {
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.loader_layout, null, false);
        ((TextView) view.findViewById(R.id.loader_title)).setText(title);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
