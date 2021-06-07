package CapaPresentacion;

import CapaNegocios.Deportes;
import CapaNegocios.ResponseObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Irene
 */
public class frmDeporte extends javax.swing.JFrame {

    public frmDeporte() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardarD = new javax.swing.JButton();
        btnCancelarD = new javax.swing.JButton();
        btnLimpiarD = new javax.swing.JButton();
        txtDescripcionD = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deporte");

        jLabel1.setText("Descripción");

        btnGuardarD.setText("Guardar");
        btnGuardarD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDActionPerformed(evt);
            }
        });

        btnCancelarD.setText("Cancelar");

        btnLimpiarD.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnGuardarD)))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelarD)
                        .addGap(68, 68, 68)
                        .addComponent(btnLimpiarD))
                    .addComponent(txtDescripcionD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcionD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarD)
                    .addComponent(btnCancelarD)
                    .addComponent(btnLimpiarD))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDActionPerformed
      System.out.println("com.comandocloud.tpintegrador.Deportes.main()");
        Deportes oDepor = new Deportes();
        oDepor.setDescripcion(txtDescripcionD.getText());
       // try {
            //ResponseObject oRespuesta = oDepor.Guardar(oDepor);
            //if(oRespuesta.getCodigoSalida()==0){
             //   JOptionPane.showMessageDialog(null,"Se guardó correctamente");
            //}else
              //  JOptionPane.showMessageDialog(null," Hubo un problema al guardar el deporte. "+ oRespuesta.getSalida());
        
       // }catch (SQLException ex) {
          //  Logger.getLogger(frmDeporte.class.getName()).log(Level.SEVERE, null, ex);
      // }
    }//GEN-LAST:event_btnGuardarDActionPerformed
        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        Deportes oDepor = new Deportes();
       /** try {
            ResponseObject oRespuesta = oDepor.Eliminar(1);
            if(oRespuesta.getCodigoSalida()==0){
                JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            }else
            JOptionPane.showMessageDialog(null," Hubo un problema al eliminar la cancha. "+ oRespuesta.getSalida());
        } catch (SQLException ex) {
           Logger.getLogger(frmDeporte.class.getName()).log(Level.SEVERE, null, ex);*/ 
    } 
   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new frmDeporte().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarD;
    public javax.swing.JButton btnGuardarD;
    public javax.swing.JButton btnLimpiarD;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JTextField txtDescripcionD;
    // End of variables declaration//GEN-END:variables
}
