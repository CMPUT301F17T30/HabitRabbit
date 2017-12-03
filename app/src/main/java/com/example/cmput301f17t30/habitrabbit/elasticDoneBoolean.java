package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by Jacqueline on 2017-12-02.
 */

public class elasticDoneBoolean {
    private boolean elasticDone = false;
    private ChangeListener listener;

    public boolean isDone() {
        return elasticDone;
    }

    public void setDone(boolean done) {
        this.elasticDone = done;
        if (listener != null) {
            listener.onChange();
        }
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}
