package com.chanpreet.visualizeds.builder;

import android.text.Html;

public class TextBuilder {
    public static String makeBulletList(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i] + (i < lines.length - 1 ? "<br><br>" : "");
            line = "&#8226; " + line;
            stringBuilder.append(line);
        }
        return Html.fromHtml(stringBuilder.toString()).toString();
    }
    public static String makeOrderedList(String... lines) {
        int step = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = step++ + ") " + lines[i] + (i < lines.length - 1 ? "<br><br>" : "");
            stringBuilder.append(line);
        }
        return Html.fromHtml(stringBuilder.toString()).toString();
    }
}
