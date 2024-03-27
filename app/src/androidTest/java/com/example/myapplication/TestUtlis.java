package com.example.myapplication;

import android.os.SystemClock;

import androidx.test.platform.app.InstrumentationRegistry;

public class TestUtlis {


    public static void disableAnimations() {
        // Execute ADB commands to disable animations
        executeShellCommand("settings put global window_animation_scale 0");
        executeShellCommand("settings put global transition_animation_scale 0");
        executeShellCommand("settings put global animator_duration_scale 0");
    }

    public static void enableAnimations() {
        // Execute ADB commands to enable animations
        executeShellCommand("settings put global window_animation_scale 1");
        executeShellCommand("settings put global transition_animation_scale 1");
        executeShellCommand("settings put global animator_duration_scale 1");
    }

    private static void executeShellCommand(String command) {
        try {
            InstrumentationRegistry.getInstrumentation().getUiAutomation()
                    .executeShellCommand(command);
            // Wait for changes to take effect
            SystemClock.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
