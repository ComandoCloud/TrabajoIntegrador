
package CapaNegocios;

import java.sql.SQLException;

public interface IOperacionesBasicas<T> {
    public ResponseObject Guardar(T objeto)throws SQLException;
    public ResponseObject Listar()throws SQLException;
    public ResponseObject Eliminar(int id)throws SQLException;
}
