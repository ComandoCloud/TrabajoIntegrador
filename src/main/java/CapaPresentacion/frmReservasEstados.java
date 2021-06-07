package CapaPresentacion;

import CapaNegocios.ReservasEstados;
import CapaNegocios.ResponseObject;

/**
 *
 * @author Irene
 */
public class frmReservasEstados extends javax.swing.JFrame {

    public frmReservasEstados() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardarR = new javax.swing.JButton();
        btnCancelarR = new javax.swing.JButton();
        btnLimpiarR = new javax.swing.JButton();
        txtDescripcionR = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estados de Reserva");

        jLabel1.setText("Descripción");

        btnGuardarR.setText("Guardar");

        btnCancelarR.setText("Cancelar");

        btnLimpiarR.setText("Limpiar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardarR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnCancelarR)
                        .addGap(70, 70, 70)
                        .addComponent(btnLimpiarR)
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescripcionR, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarR)
                    .addComponent(btnCancelarR)
                    .addComponent(btnLimpiarR))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void txtLargoActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
    }   
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    System.out.println("com.comandocloud.tpintegrador.ReservasEstados.main()");
        ReservasEstados oReservasEstados = new ReservasEstados();
         oReservasEstados.setDescripcion(txtDescripcionR.getText());
      /**  try {
            ResponseObject oRespuesta = oReservasEstados.Guardar(oReservasEstados);
            if(oRespuesta.getCodigoSalida()==0){
                JOptionPane.showMessageDialog(null,"Se guardó correctamente");
            }else
                JOptionPane.showMessageDialog(null," Hubo un problema al guardar la cancha. "+ oRespuesta.getSalida());
            
        } catch (SQLException ex) {
            Logger.getLogger(frmReservasEstados.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }                                          
         private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        ReservasEstados oReservasEstados = new ReservasEstados();
       /** try {
            ResponseObject oRespuesta = oReservasEstados.Eliminar(1);
            if(oRespuesta.getCodigoSalida()==0){
                JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            }else
            JOptionPane.showMessageDialog(null," Hubo un problema al eliminar la cancha. "+ oRespuesta.getSalida());
        } catch (SQLException ex) {
           Logger.getLogger(frmReservasEstados.class.getName()).log(Level.SEVERE, null, ex);*/ 
    } 
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new frmReservasEstados().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelarR;
    public javax.swing.JButton btnGuardarR;
    public javax.swing.JButton btnLimpiarR;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JTextField txtDescripcionR;
    // End of variables declaration//GEN-END:variables
}
