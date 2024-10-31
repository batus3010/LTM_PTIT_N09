/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import run.ClientRun;

/**
 *
 * @author DELL
 */
public class GameViewCards extends javax.swing.JFrame {

    String competitor = "";

    private int[] selectedCards = new int[3];
    private boolean[] cardsSelected = new boolean[10];
    private int maxSelections = 3;
    private int currentSelections = 0;

    
    /**
     * Creates new form GameViewCards
     */
    public GameViewCards() {
        initComponents();
        btnCard1.addActionListener(radioButtonListener);
        btnCard2.addActionListener(radioButtonListener);
        btnCard3.addActionListener(radioButtonListener);
        btnCard4.addActionListener(radioButtonListener);
        btnCard5.addActionListener(radioButtonListener);
        btnCard6.addActionListener(radioButtonListener);
        btnCard7.addActionListener(radioButtonListener);
        btnCard8.addActionListener(radioButtonListener);
        btnCard9.addActionListener(radioButtonListener);
        btnCard10.addActionListener(radioButtonListener);
        // close window event
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(GameViewCards.this, "Are you sure want to leave game? You will lose?", "LEAVE GAME", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    ClientRun.socketHandler.leaveGame(competitor);
                    ClientRun.socketHandler.setRoomIdPresent(null);
                    dispose();
                }
            }
        });
    }
    
    private ActionListener radioButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            JRadioButton button = (JRadioButton) evt.getSource();
            if (button.isSelected()) {
                currentSelections++;
                if (currentSelections == maxSelections) {
                    showNotification();
                }
                else if(currentSelections > maxSelections)
                {
                    showNotificationMaxCardsSelected();
                    button.setSelected(false);
                }
            } else {
                currentSelections--;
            }
            System.out.println("Current selection: " + currentSelections);
        }
    };
    
    private void showNotification() {
        JOptionPane.showMessageDialog(this, "You've selected 3 cards! Please wait for the result. Or click [Submit] to end game faster!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    private void showNotificationMaxCardsSelected()
    {
        JOptionPane.showMessageDialog(this, "You can only select 3 cards!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        currentSelections--;
    }

    public void setInfoPlayer(String username) {
        competitor = username;
        jLabel1.setText("Playing against: " + username);
    }
    
    

    public void setWaitingRoom() {
        // Disable submit and leave buttons
        jButton3.setEnabled(false);
        jButton2.setEnabled(false);

        // Show waiting message
        JLabel waitingLabel = new JLabel("Waiting for other players...");
        waitingLabel.setHorizontalAlignment(JLabel.CENTER);
        waitingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel waitingPanel = new JPanel();
        waitingPanel.add(waitingLabel);
        waitingPanel.setLayout(new BorderLayout());
        waitingPanel.add(waitingLabel, BorderLayout.CENTER);

        // Replace the existing Cards panel with the waiting panel
        Cards.removeAll();
        Cards.add(waitingPanel);
        Cards.revalidate();
        Cards.repaint();
    }

    public void setStartGame() {
        // Enable submit and leave buttons
        jButton3.setEnabled(true);
        jButton2.setEnabled(true);

//        // Reset the Cards panel
//        Cards.removeAll();
//        Cards.setLayout(new GridLayout(2, 5));
//
//        // Add card buttons
//        Arrays.asList(btnCard1, btnCard2, btnCard3, btnCard4, btnCard5, btnCard6, btnCard7, btnCard8, btnCard9, btnCard10).forEach(btn -> {
//            btn.setVisible(true);
//            btn.setEnabled(true);
//        });

        // Initialize progress bar
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setString("Time Remaining: ");
        jProgressBar1.setValue(100);

        // Start countdown timer
        ActionListener timerListener = e -> {
            int value = jProgressBar1.getValue();
            if (value > 0) {
                jProgressBar1.setValue(value - 1);
                jProgressBar1.setString("Time Remaining: ");
            } else {
                ((Timer) e.getSource()).stop();
                // Handle end of game logic here
            }
        };

        Timer timer = new Timer(1000, timerListener);
        timer.start();
    }

    public void afterSubmit() {
        // Disable submit button
        jButton3.setEnabled(false);

        // Show waiting message
        JLabel waitingLabel = new JLabel("Waiting for result...");
        waitingLabel.setHorizontalAlignment(JLabel.CENTER);
        waitingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel waitingPanel = new JPanel();
        waitingPanel.add(waitingLabel);
        waitingPanel.setLayout(new BorderLayout());
        waitingPanel.add(waitingLabel, BorderLayout.CENTER);

        // Replace the existing Cards panel with the waiting panel
        Cards.removeAll();
        Cards.add(waitingPanel);
        Cards.revalidate();
        Cards.repaint();
    }

    public String[] getSelectedButtons() {
        List<String> selectedButtons = new ArrayList<>();

        for (JRadioButton button : Arrays.asList(btnCard1, btnCard2, btnCard3, btnCard4, btnCard5, btnCard6, btnCard7, btnCard8, btnCard9, btnCard10)) {
            if (button.isSelected()) {
                selectedButtons.add(button.getText());
            }
        }

        return selectedButtons.toArray(new String[0]);
    }

    private void showAskPlayAgain() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "PLAY AGAIN", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Reset game state
            setStartGame();
        } else {
            // Exit the game
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Cards = new javax.swing.JPanel();
        btnCard1 = new javax.swing.JRadioButton();
        btnCard2 = new javax.swing.JRadioButton();
        btnCard3 = new javax.swing.JRadioButton();
        btnCard4 = new javax.swing.JRadioButton();
        btnCard5 = new javax.swing.JRadioButton();
        btnCard6 = new javax.swing.JRadioButton();
        btnCard7 = new javax.swing.JRadioButton();
        btnCard8 = new javax.swing.JRadioButton();
        btnCard9 = new javax.swing.JRadioButton();
        btnCard10 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(255, 255, 255));

        Cards.setBackground(new java.awt.Color(245, 255, 250));
        Cards.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(66, 109, 85), 2, true));

        btnCard1.setBorder(null);
        btnCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard2.setBorder(null);
        btnCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N
        btnCard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCard2ActionPerformed(evt);
            }
        });

        btnCard3.setBorder(null);
        btnCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard4.setBorder(null);
        btnCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard5.setBorder(null);
        btnCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard6.setBorder(null);
        btnCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard7.setBorder(null);
        btnCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N
        btnCard7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCard7ActionPerformed(evt);
            }
        });

        btnCard8.setBorder(null);
        btnCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard9.setBorder(null);
        btnCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        btnCard10.setBorder(null);
        btnCard10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/card.png"))); // NOI18N
        btnCard10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/rolloverCard.png"))); // NOI18N
        btnCard10.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/selectedCard.png"))); // NOI18N

        javax.swing.GroupLayout CardsLayout = new javax.swing.GroupLayout(Cards);
        Cards.setLayout(CardsLayout);
        CardsLayout.setHorizontalGroup(
            CardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CardsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(CardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CardsLayout.createSequentialGroup()
                        .addComponent(btnCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CardsLayout.createSequentialGroup()
                        .addComponent(btnCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCard10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        CardsLayout.setVerticalGroup(
            CardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CardsLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(CardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCard5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCard4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCard2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(CardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCard10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCard9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCard7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCard8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jButton2.setBackground(new java.awt.Color(255, 51, 0));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Leave Game");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(66, 107, 85));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Submit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(66, 107, 85));
        jLabel1.setText("Playing with:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(237, 183, 34));
        jLabel2.setText("Remain:");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(66, 107, 85));
        jProgressBar1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(66, 107, 85), 1, true));

        btnStart.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStart))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Cards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnCard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCard2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCard2ActionPerformed

    private void btnCard7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCard7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCard7ActionPerformed
    
    // submit button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (JOptionPane.showConfirmDialog(GameViewCards.this, "Are you sure want to leave game? You will lose?", "LEAVE GAME", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            ClientRun.socketHandler.leaveGame(competitor);
            ClientRun.socketHandler.setRoomIdPresent(null);
            dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        ClientRun.socketHandler.startGame(competitor);
        btnStart.setVisible(false);
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameViewCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameViewCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameViewCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameViewCards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameViewCards().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Cards;
    private javax.swing.JRadioButton btnCard1;
    private javax.swing.JRadioButton btnCard10;
    private javax.swing.JRadioButton btnCard2;
    private javax.swing.JRadioButton btnCard3;
    private javax.swing.JRadioButton btnCard4;
    private javax.swing.JRadioButton btnCard5;
    private javax.swing.JRadioButton btnCard6;
    private javax.swing.JRadioButton btnCard7;
    private javax.swing.JRadioButton btnCard8;
    private javax.swing.JRadioButton btnCard9;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
