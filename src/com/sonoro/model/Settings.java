/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.model;

/**
 *
 * @author ryanl
 */
public class Settings {
    
    private boolean initialized;
    private String emailUsername;
    private char[] emailPassword; // for security purposes
    private String recordingName = "recording.wav"; // system will only store the most recent recording/clip by overwriting
    private String clipName = "clip.wav"; 
    
    // hard-coded for now, can be changed to editable later
    private String emailServer = "smtp.gmail.com"; // use gmail, must enable "Allow less secure client"
    private String emailPort = "587"; // use TLS protocol
    
    private Settings()
    {
        
    }
    
    public static final Settings getInstance()
    {
        return Builder.instance;
    }
    
    private static class Builder
    {
        private static Settings instance = new Settings();        
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public String getEmailUsername() {
        return emailUsername;
    }

    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    public char[] getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(char[] emailPassword) {
        this.emailPassword = emailPassword;
    }
    
    public String getEmailServer() {
        return emailServer;
    }

    public void setEmailServer(String emailServer) {
        this.emailServer = emailServer;
    }

    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public String getRecordingName() {
        return recordingName;
    }

    public String getClipName() {
        return clipName;
    }
}
