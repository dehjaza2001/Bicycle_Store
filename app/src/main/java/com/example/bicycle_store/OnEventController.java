package com.example.bicycle_store;

import android.view.View;

public interface OnEventController {

    int PROVIDE_DATA = 999999;

    /**
     * Send data and Handle Event in App
     *
     * @param eventType
     * @param view
     * @param data
     */
    void onEvent(int eventType, View view, Object data);

    /**
     * Send data and Handle Event in App
     *
     * @param eventType
     * @param view
     * @param data
     */
    void onEvent(int eventType, View view, Object data, OnEventController eventController);

    /**
     * Send data and Handle Event in App
     * @param eventType
     * @param view
     * @param data
     * @param <T>
     */
//    <T> void  onEvent(int eventType, View view, T data);
}