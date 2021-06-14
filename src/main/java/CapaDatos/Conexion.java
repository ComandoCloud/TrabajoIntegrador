package CapaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Conexion {

    Connection oCon = null;
    //PARAMETROS DE CONEXION
    public static final String Url = "jdbc:mysql://179.51.237.45/db_canchas";
    public static final String Usuario = "root";
    public static final String Clave = "root2020";
    //OBJETOS NECESARIOS PARA CONECTARSE A UNA BASE DE DATOS
    public PreparedStatement comando;
    public ResultSet comandoResult;

    public Conexion() {
    }

    //METODO PARA CONECTARSE A LA BASE DE DATOS, SIN LA CONEXION ABIERTA NO SE PUEDE EJECUTAR NINGUN COMANDO SQL
    public void Conectar() throws SQLException {
        if (this.oCon != null && this.oCon.isValid(1)) {
            throw new SQLException("Para conectarse a la base de debe cerrar la conexion creada anteriormente.");
        }
        try {
            if (this.oCon == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.oCon = DriverManager.getConnection(this.Url, this.Usuario, this.Clave);
                System.out.println("Conexion exitosa.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error al conectarse con la base de datos:\n" + e.toString());
        }
    }

    //METODO PARA DESCONECTARSE DE LA BBDD
    public void Desconectar() throws SQLException {
        if (this.oCon.isValid(3000)) {
            this.oCon = null;
            this.comando = null;
            this.comandoResult = null;
        }
    }

    //METODO QUE ESTABLECE EL COMANDO SQL AL OBJETO oCon 
    public void CrearComando(String Comando) throws SQLException {
        this.comando = oCon.prepareStatement(Comando.toString().toLowerCase());
    }

    //LLEVA A CABO LA CONSULTA ESTABLECIDA EN EL METODO CREAR COMANDO
    public boolean EjecutarComando() throws SQLException {
        return this.comando.execute();
    }

    //TRANSFORMA EL RESULTADO DE UNA COSULTA A UN OBETO 
    public DefaultTableModel Tabla() throws SQLException {
        this.comandoResult = this.comando.executeQuery();
        ResultSetMetaData metaDatos = this.comandoResult.getMetaData();
        DefaultTableModel dt = new DefaultTableModel();
        JTable tabla = new JTable();
        tabla.setModel(dt);
        int NumColumn = metaDatos.getColumnCount();
        Object[] nombreColumnas = new Object[NumColumn];
        Object[] fila = new Object[NumColumn];
        //OBTIENE LOS NOMBRES DE LAS COLUMNAS DEL RESULTADO DE LA CONSULTA 
        for (int i = 0; i < NumColumn; i++) {
            nombreColumnas[i] = metaDatos.getColumnLabel(i + 1);
        }
        //SETEA LA COLUMNAS EN OBJETO QUE SE RETORNARA
        dt.setColumnIdentifiers(nombreColumnas);
        //RECORRE LAS FILAS DEVUELTAS POR LA CONSULTA SQL Y LAS AGREGA A EL OBJETO DT 
        while (this.comandoResult.next()) {
            for (int i = 0; i < NumColumn; i++) {
                fila[i] = this.comandoResult.getObject(i + 1);
            }
            dt.addRow(fila);
        }
        //RETORNO DefaultTableModel CON LAS COLUMNAS Y LAS FILAS DEVUELTAS POR LA CONSULTA
        return dt;
    }
}
