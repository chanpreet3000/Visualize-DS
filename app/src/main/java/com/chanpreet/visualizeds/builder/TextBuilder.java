package com.chanpreet.visualizeds.builder;

import android.text.Html;

import java.util.Map;

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

    public static String makeBulletList(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String line = "&#8226; " + entry.getKey() + "\t:\t" + entry.getValue().toString() + ((cnt == map.size() - 1) ? "" : "<br>");
            stringBuilder.append(line);
            cnt++;
        }
        return Html.fromHtml(stringBuilder.toString()).toString();
    }
}
