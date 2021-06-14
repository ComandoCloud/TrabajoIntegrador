package CapaPresentacion;

import CapaNegocios.Cancha;
import CapaNegocios.Reserva;
import CapaNegocios.ReservasEstados;
import CapaNegocios.ResponseObject;
import CapaNegocios.Usuario;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmReservas extends javax.swing.JFrame {

    private DefaultTableModel tablaCanchas = new DefaultTableModel();
    private DefaultTableModel tablaReservas = new DefaultTableModel();
    private Reserva oReserva = new Reserva();
    private Reserva oReservaSeleccionada = new Reserva();
    private int idCanchaSeleccionada;
    private Cancha oCanchas = new Cancha();

    //CONSTRUCTOR
    public frmReservas() throws SQLException, InterruptedException {
        initComponents();
        comenzarCarga();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        pnlTitulo = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvReservas = new javax.swing.JTable();
        lblTelefono1 = new javax.swing.JLabel();
        cboCancha = new javax.swing.JComboBox<>();
        btnVerDisponibilidad = new javax.swing.JButton();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        btnReprogramar = new javax.swing.JButton();
        btnLiberar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlContenedor.setBackground(new java.awt.Color(0, 153, 153));
        pnlContenedor.setName(""); // NOI18N

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setText("Fecha (dd-mm-yyyy):");

        pnlTitulo.setBackground(new java.awt.Color(0, 153, 153));

        lblTitulo.setBackground(new java.awt.Color(0, 153, 153));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("RESERVAS");
        lblTitulo.setToolTipText("");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitulo.setName("lblTitulo"); // NOI18N

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
        );

        dgvReservas.setModel(new javax.swing.table.DefaultTableModel(
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
        dgvReservas.setDoubleBuffered(true);
        dgvReservas.setShowGrid(true);
        dgvReservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvReservas);

        lblTelefono1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelefono1.setText("Cancha:");

        cboCancha.setName("cboCargo"); // NOI18N

        btnVerDisponibilidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerDisponibilidad.setText("Ver disponibilidad");
        btnVerDisponibilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerDisponibilidadMouseClicked(evt);
            }
        });
        btnVerDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDisponibilidadActionPerformed(evt);
            }
        });

        txtDia.setText("01");

        txtMes.setText("06");

        txtAno.setText("2021");

        btnReprogramar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReprogramar.setText("Reprogramar");
        btnReprogramar.setEnabled(false);
        btnReprogramar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReprogramarMouseClicked(evt);
            }
        });
        btnReprogramar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReprogramarActionPerformed(evt);
            }
        });

        btnLiberar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLiberar.setText("Liberar reserva");
        btnLiberar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLiberarMouseClicked(evt);
            }
        });
        btnLiberar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLiberarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboCancha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(629, 629, 629))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                            .addComponent(btnVerDisponibilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(btnLiberar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReprogramar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCancha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnVerDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReprogramar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLiberar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //METODO QUE SE ENCARGA DE LLAMAR A LOS METODOS DE BUSQUEDA DE DATOS A LA BBDD 
    //Y LUEGO EL METODO PARA MOSTRAR LOS DATOS E INICIALIZAR LOS COMPONENTES 
    private void comenzarCarga() throws SQLException, InterruptedException {
        cargarDatos();
        asignarDatos();
    }

     //LLAMA A LOS METODOS DE LA CAPA NEGOCIOS
    private void cargarDatos() throws SQLException, InterruptedException {
        try {
            ResponseObject oRes2 = oCanchas.Listar();
            tablaCanchas = oRes2.getjTResultado();
        } catch (SQLException ex) {
            Logger.getLogger(frmReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //EL METODO PARA MOSTRAR LOS DATOS E INICIALIZAR LOS COMPONENTES 
    private void asignarDatos() {
        dgvReservas.setModel(tablaReservas);
        for (int row = 0; row < tablaCanchas.getRowCount(); row++) {
            for (int col = 0; col < tablaCanchas.getColumnCount(); col++) {
                if (col == 0) {
                    cboCancha.addItem(new Cancha(
                            Integer.parseInt(tablaCanchas.getValueAt(row, col).toString()),
                            tablaCanchas.getValueAt(row, col + 2).toString())
                    );
                }
            }
        }
        //SI EL USUARIO ES PERSONAL PPUEDE LIBERAR CUALQUIER TURNO
        if (Usuario.getoUsuario().getTipoPersona() == 1) {
            btnLiberar.setVisible(true);
            btnLiberar.setEnabled(true);
        } else {
            btnLiberar.setVisible(false);
            btnLiberar.setEnabled(false);

        }
    }

    private void selectItemByString(String s) {
        for (int i = 0; i < cboCancha.getItemCount(); i++) {
            if (cboCancha.getItemAt(i).equals(s)) {
                cboCancha.setSelectedIndex(i);
                break;
            }
        }
        return;
    }

    private void dgvReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvReservasMouseClicked
        
        //EBVENTO CLICK, SI ES UN USUAEIO AL HACER CLICK EN UN TURNO LIBRE, PREGUNTA SI LO QUIERE RESERVAR
        int[] selectedRows = dgvReservas.getSelectedRows();
        String fecha = txtAno.getText() + "-" + txtMes.getText() + "-" + txtDia.getText();
        String fechaSeleccionada = fecha + " " + tablaReservas.getValueAt(selectedRows[0], 0).toString();
        String estadoActual = tablaReservas.getValueAt(selectedRows[0], 1).toString();
        if (Usuario.getoUsuario().getTipoPersona() == 2) {
            if (estadoActual.equals("LIBRE")) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date parsedDate;
                ReservasEstados oEstado = new ReservasEstados();
                int reply = JOptionPane.showConfirmDialog(null, "Esta reservando un turno para el dia " + fecha + " ¿Desea continuar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        parsedDate = dateFormat.parse(fechaSeleccionada);
                        Timestamp fechaTime = new java.sql.Timestamp(parsedDate.getTime());
                        ResponseObject oRes = oReserva.Reservar(idCanchaSeleccionada, fechaTime, Usuario.getoUsuario().getId(), ReservasEstados.ReservaEstado.RESERVADA.getEstado());
                        if (oRes.getCodigoSalida() == 0) {
                            JOptionPane.showMessageDialog(null, "Cancha reservada correctamente");
                            ResponseObject oRes2 = oReserva.getHorarios(idCanchaSeleccionada, fecha);
                            tablaReservas = oRes2.getjTResultado();
                            dgvReservas.setModel(tablaReservas);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(frmReservas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmReservas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El turno seleccionado no esta disponible");
            }
        } else {
            if (estadoActual.equals("RESERVADA")) {
                btnLiberar.setEnabled(true);
            } else {
                btnLiberar.setEnabled(false);
            }
        }

    }//GEN-LAST:event_dgvReservasMouseClicked

    private void btnVerDisponibilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerDisponibilidadMouseClicked
        Cancha oCanchaSeleccionada = (Cancha) cboCancha.getSelectedItem();
        idCanchaSeleccionada = oCanchaSeleccionada.getIdCancha();
        String fecha = txtAno.getText() + "-" + txtMes.getText() + "-" + txtDia.getText();
        try {
            //BUSCA LOS TURNOS PARA LA FECHA Y CANCHA SELECCIONADAS
            ResponseObject oRes = oReserva.getHorarios(idCanchaSeleccionada, fecha);
            tablaReservas = oRes.getjTResultado();
            //MUESTRA EL RESULTADO EN LA GRILLA
            dgvReservas.setModel(tablaReservas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.toString(), "Bienvenido al sistema", JOptionPane.ERROR);
        }
    }//GEN-LAST:event_btnVerDisponibilidadMouseClicked


    private void btnVerDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDisponibilidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerDisponibilidadActionPerformed

    private void btnReprogramarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReprogramarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReprogramarMouseClicked

    private void btnReprogramarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprogramarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReprogramarActionPerformed

    private void btnLiberarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLiberarMouseClicked

    }//GEN-LAST:event_btnLiberarMouseClicked

    private void btnLiberarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiberarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLiberarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmReservas().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(frmReservas.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(frmReservas.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLiberar;
    private javax.swing.JButton btnReprogramar;
    private javax.swing.JButton btnVerDisponibilidad;
    private javax.swing.JComboBox<Cancha> cboCancha;
    private javax.swing.JTable dgvReservas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    // End of variables declaration//GEN-END:variables
}
