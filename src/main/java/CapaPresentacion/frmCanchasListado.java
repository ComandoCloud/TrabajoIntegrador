package CapaPresentacion;

import CapaNegocios.ResponseObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Irene
 */
public class frmCanchasListado extends javax.swing.JFrame {
     //DefaultTableModel model =new  DefaultTableModel();
    public frmCanchasListado() {
        initComponents();
       // this.TabladeDatos.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TabladeDatos = new javax.swing.JTable();
        btnNuevoCL = new javax.swing.JButton();
        btnEditarCL = new javax.swing.JButton();
        btnEliminarCL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Listado de Canchas");

        TabladeDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TabladeDatos);

        btnNuevoCL.setText("Nuevo");
        btnNuevoCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCLActionPerformed(evt);
            }
        });

        btnEditarCL.setText("Editar");

        btnEliminarCL.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnNuevoCL)
                        .addGap(144, 144, 144)
                        .addComponent(btnEditarCL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarCL)
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCL)
                    .addComponent(btnEditarCL)
                    .addComponent(btnEliminarCL))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoCLActionPerformed

       /** private void btnEliminarPerformed(java.awt.event.ActionEvent evt) {                                            
        CanchasListado oCanchasListado = new CanchasListado();
        try {
            ResponseObject oRespuesta = oCanchasListado.Eliminar(1);
            if(oRespuesta.getCodigoSalida()==0){
                JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            }else
            JOptionPane.showMessageDialog(null," Hubo un problema al eliminar la cancha. "+ oRespuesta.getSalida());
        } catch (SQLException ex) {
            Logger.getLogger(frmCanchasListado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> {
            new frmCanchasListado().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TabladeDatos;
    public javax.swing.JButton btnEditarCL;
    public javax.swing.JButton btnEliminarCL;
    public javax.swing.JButton btnNuevoCL;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
