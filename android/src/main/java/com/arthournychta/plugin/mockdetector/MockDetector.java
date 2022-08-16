package com.arthournychta.plugin.mockdetector;

import android.util.Log;

public class MockDetector {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
