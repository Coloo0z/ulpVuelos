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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grupo30
 */
public class VueloData {
    private Connection connection = null;
    private Conexion conexion;

    public VueloData(Conexion conexion) {
        try {
            this.conexion=conexion;
            this.connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    ///agrega un vuelo a la base de datos (insert)
    ///tiene como atributos 2 CIUDADES
    public void guardarVuelo(Vuelo vuelo){
        try {
            String sql = "INSERT INTO vuelo (id_origen , id_destino , nombre_aerolinea , modelo , fecha_llegada , fecha_salida) VALUES (? , ? , ? , ? , ? , ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, vuelo.getOrigen().getId_ciudad());
            statement.setInt(2, vuelo.getDestino().getId_ciudad());
            statement.setString(3, vuelo.getAerolinea());
            statement.setString(4, vuelo.getModelo());
            statement.setDate(5, Date.valueOf(vuelo.getFechaLlegada()));
            statement.setDate(6, Date.valueOf(vuelo.getFechaSalida()));
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                vuelo.setId_vuelo(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una ciudad");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una ciudad: " + ex.getMessage());
        }
    } 
    
    ///retorna una lista de vuelos  (select)
    public List<Vuelo> obtenerVuelos(){
        List<Vuelo> vuelos = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM vuelo;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Vuelo vuelo;
            while(resultSet.next()){
                vuelo = new Vuelo();
                vuelo.setId_vuelo(resultSet.getInt(1));
                
                Ciudad origen=buscarCiudad(resultSet.getInt(2));
                vuelo.setOrigen(origen);
                
                Ciudad destino=buscarCiudad(resultSet.getInt(3));
                vuelo.setOrigen(destino);
                
                vuelo.setAerolinea(resultSet.getString(4));
                vuelo.setModelo(resultSet.getString(5));
                
                vuelo.setFechaLlegada(resultSet.getDate(6).toLocalDate());
                vuelo.setFechaLlegada(resultSet.getDate(7).toLocalDate());

                vuelos.add(vuelo);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los vuelos: " + ex.getMessage());
        }
        
        
        return vuelos;
    }

    ///borra un vuelo de la base de datos   (delete)
    public void borrarVuelo(int id){    
    try {
            String sql = "DELETE FROM vuelo WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al eliminar un vuelo: " + ex.getMessage());
        }  
    
    }
    
    ///modifica los datos de un vuelo en la base de datos (update)
    ///tiene como atributos 2 CIUDADES
    public void actualizarVuelo(Vuelo vuelo){    
        try {
            
            String sql = "UPDATE vuelo SET id_origen = ?, id_destino = ?, nombre_aerolinea = ?, modelo = ?, fecha_llegada = ?, fecha_salida = ? WHERE id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, vuelo.getOrigen().getId_ciudad());
            statement.setInt(2, vuelo.getDestino().getId_ciudad());
            statement.setString(3, vuelo.getAerolinea());
            statement.setString(4, vuelo.getModelo());
            statement.setDate(5, Date.valueOf(vuelo.getFechaLlegada()));
            statement.setDate(6, Date.valueOf(vuelo.getFechaSalida()));
            
            statement.executeUpdate();
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al modificar un vuelo: " + ex.getMessage());
        }
    }
    
    ///retorna una ciudad segun su id ((podria ser private porque su uso es solo para cuando se quiere retornar la lista de vuelos))
    public Ciudad buscarCiudad(int id){
    
        CiudadData cd=new CiudadData(conexion);
        
        return cd.buscarCiudad(id);
        
    }

    ///busca un vuelo segun su id
    public Vuelo buscarVuelo(int id){
    Vuelo vuelo=null;
    try {
            
            String sql = "SELECT * FROM vuelo WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                vuelo = new Vuelo();
                vuelo.setId_vuelo(resultSet.getInt(1));
                
                Ciudad origen=buscarCiudad(resultSet.getInt(2));
                vuelo.setOrigen(origen);
                
                Ciudad destino=buscarCiudad(resultSet.getInt(3));
                vuelo.setOrigen(destino);
                
                vuelo.setAerolinea(resultSet.getString(4));
                vuelo.setModelo(resultSet.getString(5));
                
                vuelo.setFechaLlegada(resultSet.getDate(6).toLocalDate());
                vuelo.setFechaLlegada(resultSet.getDate(7).toLocalDate());
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al buscar un vuelo: " + ex.getMessage());
        }
        
        return vuelo;
    }
    
}
