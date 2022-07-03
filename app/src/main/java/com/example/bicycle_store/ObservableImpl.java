package com.example.bicycle_store;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public enum ObservableImpl implements Observable {
    INSTANCE;

    private Context mContext;

    private List<OnEventController> lstObservers = new ArrayList<>();
    private Object message;
    private boolean changed;
    private final Object MUTEX= new Object();

    public static ObservableImpl newInstance(Context context) {
        INSTANCE.mContext = context;
        return INSTANCE;
    }

    @Override
    public void register(OnEventController obj) {
        if(obj == null) throw new NullPointerException("Observer is null.");
        synchronized (MUTEX) {
            if(!lstObservers.contains(obj)) lstObservers.add(obj);
        }
    }

    @Override
    public void unregister(OnEventController obj) {
        synchronized (MUTEX) {
            lstObservers.remove(obj);
        }
    }

    @Override
    public void notifyObservers(int eventType, View view, Object data) {
        List<OnEventController> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed) return;
            observersLocal = new ArrayList<>(this.lstObservers);
            this.changed=false;
        }
        for (OnEventController obj : observersLocal) {
            obj.onEvent(eventType, null, data);
        }
    }

    @Override
    public Object getUpdate(OnEventController obj) {
        return message;
    }

    @Override
    public void postMessage(int eventType, Object msg) {
        this.message = msg;
        this.changed = true;
        notifyObservers(eventType, null, msg);
    }
}