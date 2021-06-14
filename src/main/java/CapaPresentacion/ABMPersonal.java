package CapaPresentacion;

import javax.swing.JOptionPane;
import CapaNegocios.PersonalCargo;
import CapaNegocios.Personal;
import CapaNegocios.ResponseObject;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ABMPersonal extends javax.swing.JFrame {

    //REGION DE PROPIEDADES
    private Personal oPers;
    private Personal oPersonalSeleccionado;
    private DefaultTableModel modelo = new DefaultTableModel();
    private DefaultTableModel modeloCargos = new DefaultTableModel();
    private PersonalCargo oCargo = new PersonalCargo();

    //CONTRUCTOR
    public ABMPersonal() throws SQLException, InterruptedException {
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //int height = pantalla.height;
        //int width = pantalla.width;
        //setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        comenzarCarga();
    }

    //METODO QUE SE ENCARGA DE LLAMAR A LOS METODOS DE BUSQUEDA DE DATOS A LA BBDD 
    //Y LUEGO EL METODO PARA MOSTRAR LOS DATOS E INICIALIZAR LOS COMPONENTES 
    private void comenzarCarga() throws SQLException, InterruptedException {
        DeshabilitartextBox();
        cargarDatos();
    }

    //LLAMA A LOS METODOS DE LA CAPA NEGOCIOS
    private void cargarDatos() throws SQLException, InterruptedException {
        oPers = new Personal();
        ResponseObject oRes = oPers.Listar();
        modelo = oRes.getjTResultado();
        ResponseObject oRes2 = oCargo.Listar();
        modeloCargos = oRes2.getjTResultado();

        //Y LUEGO EL METODO PARA MOSTRAR LOS DATOS E INICIALIZAR LOS COMPONENTES 
        asignarDatos();
    }

    //HABILITA LOS CONTROLES
    private void HabilitartextBox() {
        txtApellido.setEnabled(true);
        txtNombre.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtDni.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPassword.setEnabled(true);
    }

    //DESHABILITA LOS CONTROLES
    private void DeshabilitartextBox() {
        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtDni.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPassword.setEnabled(false);
    }

    //LIMPIA LOS CONTROLES
    private void LimpiarCampos() {
        txtApellido.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDni.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    //METODOS PARA MOSTRAR LOS DATOS DEVUELTOS EN LA CAPA NEGOCIOS Y PARA INICIALIZAR LA FUENTE DE INFORMACION DE LOS CONTROLES 
    private void asignarDatos() {
        for (int row = 0; row < modeloCargos.getRowCount(); row++) {
            for (int col = 0; col < modeloCargos.getColumnCount(); col++) {
                if (col == 0) {
                    cboCargo.addItem(new PersonalCargo(
                            Integer.parseInt(modeloCargos.getValueAt(row, col).toString()),
                            modeloCargos.getValueAt(row, col + 1).toString())
                    );
                }
            }

        }
        //ASIGNA LA INFORMACION DEVUELTA POR LA CAPA NEGOCIOS A LA GRILLA DEL FORMULARIO
        dgvPersonal.setModel(modelo);
        //CONFIGURA LA GRILLA OCULANDO LAS COLUMNAS NO NECESARIAS
        dgvPersonal.getColumnModel().getColumn(0).setMinWidth(0);
        dgvPersonal.getColumnModel().getColumn(0).setMaxWidth(0);
        dgvPersonal.getColumnModel().getColumn(5).setMinWidth(0);
        dgvPersonal.getColumnModel().getColumn(5).setMaxWidth(0);
        dgvPersonal.getColumnModel().getColumn(7).setMinWidth(0);
        dgvPersonal.getColumnModel().getColumn(7).setMaxWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvPersonal = new javax.swing.JTable();
        lblTelefono1 = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox<>();
        lblDNI = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlContenedor.setBackground(new java.awt.Color(0, 153, 153));
        pnlContenedor.setName(""); // NOI18N

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmail.setText("Email");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(0, 153, 153));

        lblTitulo.setBackground(new java.awt.Color(0, 153, 153));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("ABM PERSONAL");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
        );

        lblTitulo.getAccessibleContext().setAccessibleName("lblTitulo");

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPassword.setText("Clave:");

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        lblApellido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblApellido.setText("Apellido:");

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelefono.setText("Telefono:");

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        dgvPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
        dgvPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvPersonalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvPersonal);

        lblTelefono1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelefono1.setText("Cargo:");

        cboCargo.setName("cboCargo"); // NOI18N

        lblDNI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDNI.setText("Dni: ");

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenedorLayout.createSequentialGroup()
                            .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtApellido))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContenedorLayout.createSequentialGroup()
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(lblTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblDNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))))
        );

        lblEmail.getAccessibleContext().setAccessibleName("LabelNombre");
        lblEmail.getAccessibleContext().setAccessibleDescription("LabelNombre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlContenedor.getAccessibleContext().setAccessibleName("pnlContenedor");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //LAMA A LOS METODOS PARA CANCELAR Y LIMPIAR LOS CONTROLES
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        LimpiarCampos();
        DeshabilitartextBox();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //SE HABILITAN TODOS LOS CONTROLES
        oPers.setId(0);
        HabilitartextBox();
        LimpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void dgvPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvPersonalMouseClicked

    }//GEN-LAST:event_dgvPersonalMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //METODO QUE LLAMA AL METODO ELIMINAR DEL OBJETO
        Personal oPersonal = new Personal();
        int eli = dgvPersonal.getSelectedRowCount();
        if (eli >= 0) {
            modelo.removeRow(eli);
        } else {
            JOptionPane.showMessageDialog(null, "NO HAY DATOS QUE ELIMINAR");
        }
        try {
            ResponseObject oRes = oPersonal.Guardar(oPersonal);
        } catch (SQLException ex) {
            Logger.getLogger(ABMPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        //PREPARA LOS OBJETOS PARA LUEGO LLAMAR AL MEOTODO GUARDAR
        oPersonalSeleccionado = new Personal();

        int indiceSelecionado = dgvPersonal.getSelectedRow();
        oPersonalSeleccionado.setId(Integer.parseInt(dgvPersonal.getModel().getValueAt(indiceSelecionado, 0).toString()));
        oPersonalSeleccionado.setNombre(dgvPersonal.getModel().getValueAt(indiceSelecionado, 1).toString());
        oPersonalSeleccionado.setApellido(dgvPersonal.getModel().getValueAt(indiceSelecionado, 2).toString());
        oPersonalSeleccionado.setEmail(dgvPersonal.getModel().getValueAt(indiceSelecionado, 3).toString());
        oPersonalSeleccionado.setTelefono(dgvPersonal.getModel().getValueAt(indiceSelecionado, 4).toString());
        oPersonalSeleccionado.setPassword(dgvPersonal.getModel().getValueAt(indiceSelecionado, 6).toString());
        oPersonalSeleccionado.setIdPersonalCargo(Integer.parseInt(dgvPersonal.getModel().getValueAt(indiceSelecionado, 7).toString()));

        txtEmail.setText(oPersonalSeleccionado.getEmail());
        txtPassword.setText(oPersonalSeleccionado.getPassword());
        txtNombre.setText(oPersonalSeleccionado.getNombre());
        txtApellido.setText(oPersonalSeleccionado.getApellido());
        txtTelefono.setText(oPersonalSeleccionado.getTelefono());
        txtDni.setText(oPersonalSeleccionado.getDni());

        oPers.setId(oPersonalSeleccionado.getId());
        HabilitartextBox();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        //LLAMA AL MEOTODO GUARDAR
        oPers.setEmail(txtEmail.getText());
        oPers.setApellido(txtApellido.getText());
        oPers.setNombre(txtNombre.getText());
        oPers.setDni(txtDni.getText());
        oPers.setIdPersonalCargo(1);
        oPers.setPassword(txtPassword.getText());
        oPers.setTelefono(txtTelefono.getText());
        PersonalCargo cargoSeleccionado = (PersonalCargo) cboCargo.getSelectedItem();
        int Seleccionado = cargoSeleccionado.getId();
        oPers.setIdPersonalCargo(Seleccionado);

        try {
            ResponseObject oRes = oPers.Guardar(oPers);
            if (oRes.getCodigoSalida() == 0) {
                JOptionPane.showMessageDialog(null, "Operacion realizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al guardar el personal.");
            }
            LimpiarCampos();
            comenzarCarga();

        } catch (SQLException ex) {
            Logger.getLogger(ABMPersonal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ABMPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ABMPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ABMPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ABMPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ABMPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ABMPersonal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ABMPersonal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ABMPersonal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<PersonalCargo> cboCargo;
    private javax.swing.JTable dgvPersonal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
