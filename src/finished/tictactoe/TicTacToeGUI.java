/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finished.tictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.*;

/**
 *
 * @author Khaled
 */
public class TicTacToeGUI extends javax.swing.JFrame implements ActionListener {

    ImageIcon X, O;
    private final JFrame window;
    
    private final JButton cell[][];
    int playerturn = 0;
    int flag = 0;
    int moveCount = 0;
    int cons = 3;
    boolean win = false;
    int row = 6;
    int col = 7;
    int board[][];

    public TicTacToeGUI() {
        this.window = new JFrame();
        initComponents();
        setIcon();
        board = new int[row][col];

        

        window.setTitle("KHALY TICTACTOE");
        window.setResizable(false);
        
        cell = new JButton[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = 3;
                setFrame(i, j);
                SetAction(i, j);
            }
        }
    }

    private void moveturncheck() {

        if (playerturn == 1) {
            playerturn = 0;
            player.setIcon(O);
        } else if (playerturn == 0) {
            playerturn = 1;
            player.setIcon(X);
        }

    }

    private boolean check(int i, int j, int player, int direction) {
        int end = direction == 0 ? col : row;
        int winningSum = cons * (player + 1);
        int sum;
        for (int r = 0; r <= end - cons; r++) {
            sum = 0;
            for (int l = r; l < r + cons; l++) {
                if (direction == 0) {
                    sum += board[i][l] + 1;
                    
                } else {
                    sum += board[l][j] + 1;
                    
                }
            }
            if (sum == winningSum) {
                JOptionPane.showMessageDialog(null, "WON");
                return true;
            }
        }

        return false;
    }

    private boolean checkRightDiagonal(int i, int j, int player) {
        int sdi = i >= (cons - 1) ? (cons - 1) : i;
        int sdj = j >= (cons - 1) ? (cons - 1) : j;
        int edi = i + (cons - 1) < row ? (cons - 1) : (row - 1) - i;
        int edj = j + (cons - 1) < col ? (cons - 1) : (col - 1) - j;
        int startI = i - Math.min(sdi, sdj);
        int startJ = j - Math.min(sdi, sdj);
        int endI = i + Math.min(edi, edj);
        int endJ = j + Math.min(edi, edj);
        int sum;
        int winningSum = cons * (player + 1);

        for (int r = startI, c = startJ; r <= endI - (cons - 1) && c <= endJ - (cons - 1); r++, c++) {
            //System.out.println("in");
            sum = 0;
            if (board[r][c] != player) {
                continue;
            }
            int x = c, y = r;
            for (int n = 0; n < cons; n++) {
                sum += board[y++][x++] + 1;
                //  System.out.println("sum = "+sum);
            }
            if (sum == winningSum) {
                JOptionPane.showMessageDialog(null, "WON");
                return true;
            }
        }

        return false;
    }

    private boolean checkLeftDiagonal(int i, int j, int player) {
        int sdi = i >= (cons - 1) ? (cons - 1) : i;
        int sdj = j + (cons - 1) < col ? (cons - 1) : (col - 1) - j;
        int edi = i + (cons - 1) < row ? (cons - 1) : (row - 1) - i;
        int edj = j >= (cons - 1) ? (cons - 1) : j;
        int startI = i - Math.min(sdi, sdj);
        int startJ = j + Math.min(sdi, sdj);
        int endI = i + Math.min(edi, edj);
        int endJ = j - Math.min(edi, edj);
        int winningSum = cons * (player + 1);
        int sum;
        for (int r = startI, c = startJ; r <= endI - (cons - 1) && c >= endJ - (cons - 1); r++, c--) {
            sum = 0;
            int x = c, y = r;
            for (int l = 0; l < cons; l++) {
                sum += board[y++][x--] + 1;
                //System.out.println("sum = "+sum);
            }
            if (sum == winningSum) {
                JOptionPane.showMessageDialog(null, "WON");
                return true;
            }
        }

        return false;
    }

    public void output(int i, int j, int player) {

        if (check(i, j, player, 0) == true) {
            win = true;

        } else if (check(i, j, player, 1) == true) {
            win = true;
        } else if (checkRightDiagonal(i, j, player) == true) {
            win = true;
        } else if (checkLeftDiagonal(i, j, player) == true) {
            win = true;
        }

    }

    public boolean isFull() {
        return moveCount == row * col;

    }

    private void setIcon() {
        X = new ImageIcon(this.getClass().getResource("X.png"));
        O = new ImageIcon(this.getClass().getResource("O.png"));

    }

    private void setFrame(int i, int j) {
        Border thickBorder = new LineBorder(Color.DARK_GRAY, 2);
        cell[i][j] = new JButton();
        Grid.add(cell[i][j]);

        cell[i][j].setBorder(thickBorder);
        cell[i][j].setContentAreaFilled(false);
        }

    private void setplayerturn() {
        if (playerturn == 0) {
            player.setBackground(Color.blue);
            player.setText("X");

        }
        if (playerturn == 1) {
            player.setBackground(Color.ORANGE);
            player.setText("Y");

        }
    }

    private void SetAction(int i, int j) {
        cell[i][j].addActionListener(this);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        player = new javax.swing.JButton();
        Grid = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));

        player.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        player.setText("PLAY");
        player.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        Grid.setLayout(new java.awt.GridLayout(6, 7));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Grid, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Grid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerActionPerformed
        // TODO add your handling code here:
        flag = 1;
        player.setIcon(O);
        cell[0][0].setBackground(Color.red);
    }//GEN-LAST:event_playerActionPerformed

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
            java.util.logging.Logger.getLogger(TicTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grid;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton player;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (flag == 1) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    setplayerturn();
                    if (e.getSource() == cell[i][j] && playerturn == 1) {

                        cell[i][j].setIcon(X);

                        board[i][j] = 1;
                        output(i, j, playerturn);
                        setplayerturn();
                    }
                    if (e.getSource() == cell[i][j] && playerturn == 0) {

                        cell[i][j].setIcon(O);
                        board[i][j] = 0;
                        output(i, j, playerturn);
                        setplayerturn();
                    }

                }
            }
            moveturncheck();

        } else {
            JOptionPane.showMessageDialog(null, "Press Play Please");
        }
    }
}
