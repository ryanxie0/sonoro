/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.ui;

import com.sonoro.model.Member;
import com.sonoro.model.MemberUpdateListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ryanl
 */
public class MemberButton extends JButton implements ActionListener, MemberUpdateListener {
    
    private boolean inEditMode; // determines click behavior of the button
    private Member memberCopy; // associated member stored in the data model
    private final Color initialColor; // used to toggle the color of the button
    
    public MemberButton(Member memberCopy){
        addActionListener(this);
        this.memberCopy = memberCopy;
        initialColor = getBackground();
        setButtonBackground(memberCopy.isSelected());
        setText(memberCopy.getName());
        setFont(new Font("Cambria", Font.PLAIN, 16));
    }
    
    @Override
    public void memberUpdated(String name, String email)
    {
        setText(name);
        memberCopy.setName(name);
        memberCopy.setEmail(email);
    }
    
    public void setInEditMode(boolean inEditMode)
    {
        this.inEditMode = inEditMode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if the section is in edit mode, open the edit member dialog
        if (inEditMode)
        {
            EditMemberDialog editMemberDialog = new EditMemberDialog(SonoroWindow.rootWindow, true, memberCopy);
            editMemberDialog.setMemberUpdateListener(this);
            editMemberDialog.setSize(400, 250);
            editMemberDialog.setLocationRelativeTo(null);
            editMemberDialog.setVisible(true);
        }
        // otherwise, the member button behaves as a selection mode
        else
        {
            // if there is an associated member in the button, 
            // then toggle the member's selected state and update the button's state as necessary
            if (memberCopy.getName() != null)
            {
                memberCopy.setSelected(!memberCopy.isSelected());
                setButtonBackground(memberCopy.isSelected());
            }
        }
    }
    
    private void setButtonBackground(boolean selected)
    {        
        if (selected)
        {
            setBackground(Color.darkGray);
            setForeground(Color.white);
        }
        else
        {
            setBackground(initialColor);
            setForeground(Color.black);
        }
    }
}
