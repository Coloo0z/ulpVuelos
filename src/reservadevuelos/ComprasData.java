/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo30
 */
public class ComprasData {
    private Connection connection = null;
    private Conexion conexion;

    public ComprasData(Conexion conexion) {
        try {
            this.conexion=conexion;
            this.connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    ///agrega una compra a la base de datos
    ///tiene como atributos, un ASIENTO y un CLIENTE
    public void guardarCompra(Compras compra){
        try {
            String sql = "INSERT INTO compras (cliente , asiento , fecha_compra , monto_compra) VALUES (? , ? , ? , ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, compra.getCliente().getId_cliente());
            statement.setInt(2, compra.getAsiento().getId_asiento());
            statement.setDate(3, Date.valueOf(compra.getFechaCompra()));
            statement.setDouble(4, compra.getMonto());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                compra.setId_compra(rs.getInt(1));
            } 
            else {
                System.out.println("No se pudo obtener el id luego de insertar una ciudad");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una ciudad: " + ex.getMessage());
        }
    } 
    
    ///borra una compra de la base de datos
    public void borrarCompra(int id){    
    try {
            String sql = "DELETE FROM compras WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar una compra: " + ex.getMessage());
        }  
    
    }
    
    ///modifica los datos de una compra en la base de datos
    ///tiene como atributos, un ASIENTO y un CLIENTE
    public void actualizarCompra(Compras compra){    
        try {
            
            String sql = "UPDATE compras SET cliente = ?, asiento = ?, fecha_compra = ?, monto_compra = ? WHERE id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, compra.getCliente().getId_cliente());
            statement.setInt(2, compra.getAsiento().getId_asiento());
            statement.setDate(3, Date.valueOf(compra.getFechaCompra()));
            statement.setDouble(4, compra.getMonto());
            statement.setInt(5, compra.getId_compra());
            statement.executeUpdate();
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al modificar una compra: " + ex.getMessage());
        }
    }
    
    ///retorna una lista de compras  (select)
    public List<Compras> obtenerCompras(){
        List<Compras> compras = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM compras;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Compras compra;
            while(resultSet.next()){
                compra = new Compras();
                compra.setId_compra(resultSet.getInt(1));
                
                Cliente cliente=buscarCliente(resultSet.getInt(2));
                compra.setCliente(cliente);
                
                Asiento asiento=buscarAsiento(resultSet.getInt(3));
                compra.setAsiento(asiento);
                
                compra.setFechaCompra(resultSet.getDate(4).toLocalDate());
                
                compra.setMonto(resultSet.getDouble(5));
                
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las compras: " + ex.getMessage());
        }
        
        
        return compras;
    }

    ///retorna un cliente segun su id ((podria ser private))
    public Cliente buscarCliente(int id){
    
        ClienteData cd=new ClienteData(conexion);
        
        return cd.buscarCliente(id);
        
    }
    
    ///retorna un asiento segun su id ((podria ser private))
    public Asiento buscarAsiento(int id){
    
        AsientoData ad=new AsientoData(conexion);
        
        return ad.buscarAsiento(id);
        
    }

}
