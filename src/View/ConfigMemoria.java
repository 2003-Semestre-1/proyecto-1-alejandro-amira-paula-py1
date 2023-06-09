/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Controller.ConfigMemoriaController;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import View.MiniPC;
/**
 *
 * @author user
 */
public class ConfigMemoria extends javax.swing.JFrame {

    ConfigMemoriaController controller = new ConfigMemoriaController();
    MiniPC miniPC;
    
    public ConfigMemoria() {
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_MemPrincipal = new javax.swing.JTextField();
        txt_Disco = new javax.swing.JTextField();
        txt_MemVirtual = new javax.swing.JTextField();
        lbl_MemPrincipal = new javax.swing.JLabel();
        lbl_Disco = new javax.swing.JLabel();
        lbl_MemVirtual = new javax.swing.JLabel();
        lbl_Titulo = new javax.swing.JLabel();
        lbl_Subtitulo = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 153));

        txt_MemPrincipal.setText("256");
        txt_MemPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemPrincipalActionPerformed(evt);
            }
        });

        txt_Disco.setText("512");

        txt_MemVirtual.setText("64");
        txt_MemVirtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MemVirtualActionPerformed(evt);
            }
        });

        lbl_MemPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_MemPrincipal.setText("Memoria Principal");

        lbl_Disco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Disco.setText("Disco");

        lbl_MemVirtual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_MemVirtual.setText("Memoria Virtual");

        lbl_Titulo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_Titulo.setText("Escriba los tamaños de memoria que desea");

        lbl_Subtitulo.setText("Deben ser números mayores a los que ya se tiene");

        btn_Aceptar.setBackground(new java.awt.Color(0, 204, 204));
        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbl_Titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lbl_Subtitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_MemPrincipal)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_Disco)
                                    .addGap(35, 35, 35)))
                            .addComponent(lbl_MemVirtual)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Disco, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(txt_MemVirtual)
                            .addComponent(txt_MemPrincipal))))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_Aceptar)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbl_Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Subtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbl_MemPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MemPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_Disco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Disco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_MemVirtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MemVirtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btn_Aceptar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_MemPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemPrincipalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemPrincipalActionPerformed

    private void txt_MemVirtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MemVirtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MemVirtualActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        // Obtener los números ingresados por el usuario
        String strNumero1 = txt_MemPrincipal.getText();
        String strNumero2 = txt_Disco.getText();
        String strNumero3 = txt_MemVirtual.getText();

        // Pasar los numeros a enteros
        try {
            int numero1 = Integer.parseInt(strNumero1);
            int numero2 = Integer.parseInt(strNumero2);
            int numero3 = Integer.parseInt(strNumero3);
          
        // Validar que los numeros sean mayor a lo que corresponde    
        if (numero1 >= 256 && numero2 >= 512 && numero3 >= 64) {
            // Los números son válidos
            JOptionPane.showMessageDialog(this, "Los tamaños son correctos");
            this.getController().setMemorySizes(miniPC, numero1, numero2, numero3);
            miniPC.getTblMemoryList().setValueAt("Memoria Principal", 0, 0);
            miniPC.getTblMemoryList().setValueAt(miniPC.getController().getCpu().getMemory().getSize(), 0, 1);
            miniPC.getTblMemoryList().setValueAt("Memoria Secundaria", 1, 0);
            miniPC.getTblMemoryList().setValueAt(miniPC.getSecondaryMemory().getSize(), 1, 1);
            miniPC.getTblMemoryList().setValueAt("Memoria Virtual", 2, 0);
            miniPC.getTblMemoryList().setValueAt(miniPC.getSecondaryMemory().getVirtualMemorySize(), 2, 1);
            this.setVisible(false);
            this.getMiniPC().updateMemory(this.getMiniPC().getController().getCpu().getMemory().getSize(), this.getMiniPC().getSecondaryMemory().getSize(), this.getMiniPC().getSecondaryMemory().getVirtualMemorySize(), this.getMiniPC().getSecondaryMemory().getIndiceArchivos().size());
            
          
        } else {
            // Los números no son válidos
            JOptionPane.showMessageDialog(this, "Los tamaños son incorrectos");
        }
            } catch (NumberFormatException e) {
            // Al menos uno de los campos de texto no contiene un número entero
            JOptionPane.showMessageDialog(this, "Uno de los campos de texto no contiene un número entero");
        
        }
 
    }//GEN-LAST:event_btn_AceptarActionPerformed

    public ConfigMemoriaController getController() {
        return controller;
    }

    public void setController(ConfigMemoriaController controller) {
        this.controller = controller;
    }

    public MiniPC getMiniPC() {
        return miniPC;
    }

    public void setMiniPC(MiniPC miniPC) {
        this.miniPC = miniPC;
    }
    
    public String getTxt_MemPrincipal() {
        return txt_MemPrincipal.getText(); 
    }

    public String getTxt_Disco() {
        return txt_Disco.getText();
    }

    public String  getTxt_MemVirtual() {
        return txt_MemVirtual.getText();
    }
    
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
            java.util.logging.Logger.getLogger(ConfigMemoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigMemoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigMemoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigMemoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfigMemoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JLabel lbl_Disco;
    private javax.swing.JLabel lbl_MemPrincipal;
    private javax.swing.JLabel lbl_MemVirtual;
    private javax.swing.JLabel lbl_Subtitulo;
    private javax.swing.JLabel lbl_Titulo;
    private javax.swing.JTextField txt_Disco;
    private javax.swing.JTextField txt_MemPrincipal;
    private javax.swing.JTextField txt_MemVirtual;
    // End of variables declaration//GEN-END:variables
}
