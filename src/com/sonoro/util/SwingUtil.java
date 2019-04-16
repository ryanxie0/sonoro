/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author ryanl
 */
public class SwingUtil {
    
    public static final void maximizeWindow(Window window)
    {        
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
//        window.setBounds(0, 0, screenWidth, screenHeight);
          window.setBounds(0, 0, 800, 480);
    }    
}
