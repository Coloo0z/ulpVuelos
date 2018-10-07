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
public class CiudadData {
    private Connection connection = null;

    public CiudadData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    ///agrega una ciudad a la base de datos
    ///no hay objetos como atributos
    public void guardarCiudad(Ciudad ciudad){
        try {
            
            String sql = "INSERT INTO ciudades (nombre, vigencia, pais) VALUES (? , ? , ? );";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ciudad.getNombre());
            statement.setBoolean(2, ciudad.isVigencia());
            statement.setString(3, ciudad.getPais());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                ciudad.setId_ciudad(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una ciudad");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una ciudad: " + ex.getMessage());
        }
    } 
    
    ///retorna una lista de ciudades
    public List<Ciudad> obtenerCiudades(){
        List<Ciudad> ciudades = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM ciudades;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Ciudad ciudad;
            while(resultSet.next()){
                ciudad = new Ciudad();
                ciudad.setId_ciudad(resultSet.getInt(1));
                ciudad.setNombre(resultSet.getString(2));
                ciudad.setVigencia(resultSet.getBoolean(3));;
                ciudad.setPais(resultSet.getString(4));

                ciudades.add(ciudad);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las ciudades: " + ex.getMessage());
        }
        
        
        return ciudades;
    }

    ///borra una ciudad de la base de datos
    public void borrarCiudad(int id){    
    try {
            String sql = "DELETE FROM ciudades WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar una ciudad: " + ex.getMessage());
        }  
    
    }
    
    ///modifica los datos de una ciudad  en la base de datos
    ///no hay objetos como atributos
    public void actualizarCiudad(Ciudad ciudad){    
        try {
            
            String sql = "UPDATE ciudades SET nombre = ?, vigencia = ?, pais = ? WHERE id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ciudad.getNombre());
            statement.setBoolean(2, ciudad.isVigencia());
            statement.setString(3, ciudad.getPais());
            statement.setInt(4, ciudad.getId_ciudad());
            statement.executeUpdate();
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al modificar una ciudad: " + ex.getMessage());
        }
    }
    
    ///retorna una ciudad segun su id
    public Ciudad buscarCiudad(int id){
    Ciudad ciudad=null;
    try {
            
            String sql = "SELECT * FROM ciudades WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                ciudad = new Ciudad();
                ciudad.setId_ciudad(resultSet.getInt(1));
                ciudad.setNombre(resultSet.getString(2));
                ciudad.setVigencia(resultSet.getBoolean(3));;
                ciudad.setPais(resultSet.getString(4));
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al obtener una ciudad: " + ex.getMessage());
        }
        
        return ciudad;
    }


}
