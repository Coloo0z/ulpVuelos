/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo30
 */
public class ClienteData {
    private Connection connection = null;

    public ClienteData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    ///agrega un cliente a la base de datos
    ///no hay objetos como atributos
    public void guardarCliente(Cliente cliente){
        try {
            
            String sql = "INSERT INTO clientes (n_pasaporte,n_tarjeta,nombre) VALUES (? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getnPasaporte());
            statement.setString(2, cliente.getnTarjeta());
            statement.setString(3, cliente.getNombre());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                cliente.setId_cliente(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un cliente");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un cliente: " + ex.getMessage());
        }
    } 
    
    ///retorna una lista de clientes
    public List<Cliente> obtenerClientes(){
        List<Cliente> clientes = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM clientes;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Cliente cliente;
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setnPasaporte(resultSet.getString(2));
                cliente.setnTarjeta(resultSet.getString(3));
                cliente.setNombre(resultSet.getString(4));

                clientes.add(cliente);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los clientes: " + ex.getMessage());
        }
        
        
        return clientes;
    }

    ///borra un cliente de la base de datos
    public void borrarCliente(int id){    
    try {
            String sql = "DELETE FROM clientes WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar un cliente: " + ex.getMessage());
        }  
    
    }
    
    ///modifica los datos de un cliente en la base de datos
    ///no hay objetos como atributos
    public void actualizarCliente(Cliente cliente){    
        try {
            
            String sql = "UPDATE cliente SET n_pasaporte = ?, n_tarjeta = ?, nombre = ? WHERE id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getnPasaporte());
            statement.setString(2, cliente.getnTarjeta());
            statement.setString(3, cliente.getNombre());
            statement.setInt(4, cliente.getId_cliente());
            statement.executeUpdate();
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al modificar un cliente: " + ex.getMessage());
        }
    }
    
    ///retorna un cliente segun su id
    public Cliente buscarCliente(int id){
    Cliente cliente=null;
    try {
            
            String sql = "SELECT * FROM clientes WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setnPasaporte(resultSet.getString(2));
                cliente.setnTarjeta(resultSet.getString(3));
                cliente.setNombre(resultSet.getString(4));
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al obtener un cliente: " + ex.getMessage());
        }
        
        return cliente;
    }

}
