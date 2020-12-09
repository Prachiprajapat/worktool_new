package com.example.worktool_new.Util;

import android.os.SystemClock;
import android.view.View;

public class DebounceClickListener implements View.OnClickListener {
    private static final long DEBOUNCE_INTERVAL_DEFAULT = 500;
    private View.OnClickListener clickListener;
    private long debounceInterval;
    private long lastClickTime;

    public DebounceClickListener(View.OnClickListener clickListener2) {
        this(clickListener2, DEBOUNCE_INTERVAL_DEFAULT);
    }

    public DebounceClickListener(View.OnClickListener clickListener2, long debounceInterval2) {
        this.clickListener = clickListener2;
        this.debounceInterval = debounceInterval2;
    }

    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - this.lastClickTime >= this.debounceInterval) {
            this.lastClickTime = SystemClock.elapsedRealtime();
            this.clickListener.onClick(v);
        }
    }
}
