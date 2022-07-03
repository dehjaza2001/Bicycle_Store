package com.example.bicycle_store;

import android.view.View;

public interface Observable {

    //methods to register and unregister observers
    void register(OnEventController obj);
    void unregister(OnEventController obj);

    //method to notify observers of change
    void notifyObservers(int eventType, View view, Object data);

    //method to get updates from subject
    Object getUpdate(OnEventController obj);

    // post message to observer
    void postMessage(int eventType, Object msg);

}