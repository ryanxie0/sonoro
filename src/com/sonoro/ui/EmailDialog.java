/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonoro.ui;

import com.sonoro.model.Settings;
import com.sonoro.util.EmailHandler;
import java.util.List;
import javax.mail.MessagingException;
import javax.swing.JDialog;

/**
 *
 * @author ryanl
 */
public class EmailDialog extends JDialog {

    private List<String> emails;
    private Settings settings;
    
    public EmailDialog(java.awt.Frame parent, boolean modal, List<String> emails) {
        super(parent, modal);
        initComponents();
        this.emails = emails;
        settings = Settings.getInstance();
        setRecipientsTextArea();
    }
    
    private void setRecipientsTextArea()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String email : emails)
        {
            if (stringBuilder.length() > 0)
            {
                stringBuilder.append(", ");
            }
            stringBuilder.append(email);
        }
        this.recipientsTextArea.setText(stringBuilder.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subjectLabel = new javax.swing.JLabel();
        subjectTextField = new javax.swing.JTextField();
        recipientsLabel = new javax.swing.JLabel();
        recipientsScrollPane = new javax.swing.JScrollPane();
        recipientsTextArea = new javax.swing.JTextArea();
        emailBodyLabel = new javax.swing.JLabel();
        emailBodyScrollPane = new javax.swing.JScrollPane();
        emailBodyTextField = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sonoro - Email");
        getContentPane().setLayout(new java.awt.GridLayout(7, 1));

        subjectLabel.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        subjectLabel.setText("Subject");
        subjectLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subjectLabel.setPreferredSize(new java.awt.Dimension(61, 20));
        getContentPane().add(subjectLabel);

        subjectTextField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        getContentPane().add(subjectTextField);

        recipientsLabel.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        recipientsLabel.setText("Recipients");
        getContentPane().add(recipientsLabel);

        recipientsTextArea.setEditable(false);
        recipientsTextArea.setColumns(20);
        recipientsTextArea.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        recipientsTextArea.setRows(3);
        recipientsScrollPane.setViewportView(recipientsTextArea);

        getContentPane().add(recipientsScrollPane);

        emailBodyLabel.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        emailBodyLabel.setText("Body");
        getContentPane().add(emailBodyLabel);

        emailBodyTextField.setColumns(20);
        emailBodyTextField.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        emailBodyTextField.setRows(5);
        emailBodyScrollPane.setViewportView(emailBodyTextField);

        getContentPane().add(emailBodyScrollPane);

        sendButton.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        getContentPane().add(sendButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        EmailHandler emailHandler = new EmailHandler();
        try {
            emailHandler.sendEmail(String.join(",", emails.toArray(new String[emails.size()])), 
                    subjectTextField.getText(), emailBodyTextField.getText());
        } catch (MessagingException ex) {
            SonoroWindow.showError(ex.getMessage(), "Failed To Send Email");
        }
        dispose();
    }//GEN-LAST:event_sendButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailBodyLabel;
    private javax.swing.JScrollPane emailBodyScrollPane;
    private javax.swing.JTextArea emailBodyTextField;
    private javax.swing.JLabel recipientsLabel;
    private javax.swing.JScrollPane recipientsScrollPane;
    private javax.swing.JTextArea recipientsTextArea;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JTextField subjectTextField;
    // End of variables declaration//GEN-END:variables
}