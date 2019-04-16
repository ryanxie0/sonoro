/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.util;

import com.sonoro.model.Settings;
import java.io.IOException;

/**
 *
 * @author ryanl
 */
public class RecordingHandler implements Runnable {
    
    private Process process;
    private Settings settings;
    private long recordingDurationInMillis;
    
    public RecordingHandler()
    {
        settings = Settings.getInstance();
    }
    
    @Override
    public void run()
    {
        
    }
    
    public void start() throws IOException
    {
        invokeStartCommand();
    }
    
    public void stop()
    {
        invokeStopCommand();
    }
    
    private void invokeStartCommand() throws IOException
    {
        // -d limits duration in seconds, -r sample rate in Hz, -c provides 2 channels for stereo
        // file size will be roughly <1MB per 10 seconds of audio (max roughly <180MB)
        recordingDurationInMillis = System.currentTimeMillis();
        String command = "arecord -f S16_LE -d 1800 -r 44100 -c 2 " + settings.getRecordingName();
        Runtime runtime = Runtime.getRuntime();
        process = runtime.exec(command);
    }
    
    private void invokeStopCommand()
    {
        if (process != null)
        {
            process.destroy();
            recordingDurationInMillis = System.currentTimeMillis() - recordingDurationInMillis;
        }
    }

    public long getRecordingDurationInMillis() {
        return recordingDurationInMillis;
    }
}
