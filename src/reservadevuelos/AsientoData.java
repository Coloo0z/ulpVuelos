/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservadevuelos;

/**
 *
 * @author Grupo30
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsientoData {
    private Connection connection = null;
    private Conexion conexion;
     
    public AsientoData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    ///agrega un asiento a la base de datos (insert)
    ///hay un atributo de tipo Vuelo
    public void guardarAsiento(Asiento asiento){
        try {
            String sql = "INSERT INTO asientos (letra, numero,es_pasillo,disponibilidad,precio,vuelo_id) VALUES (? , ? , ? , ? , ? , ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, asiento.getLetra());
            statement.setInt(2, asiento.getNumero());
            statement.setBoolean(3, asiento.isEsPasillo());
            statement.setInt(4, asiento.getDisponibilidad());
            statement.setDouble(5, asiento.getPrecio());
            statement.setInt(6, asiento.getVuelo().getId_vuelo());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                asiento.setId_asiento(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un asiento");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un asiento: " + ex.getMessage());
        }
    }   
    
    ///borra un asiento de la base de datos (delete)
    public void borrarAsiento(int id){        
        try {
                String sql = "DELETE FROM asientos WHERE id =?;";

                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, id);


                statement.executeUpdate();


                statement.close();

        } catch (SQLException ex) {
            System.out.println("Error al eliminar un asiento: " + ex.getMessage());
        }  
    
    }
     
    ///modifica los datos de un asiento (update)
    public void actualizarAsiento(Asiento asiento){ 
            try {

                String sql = "UPDATE asientos SET disponibilidad = ?, precio = ? WHERE id = ?;";

                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, asiento.getDisponibilidad());
                statement.setDouble(2, asiento.getPrecio());
                statement.setInt(3, asiento.getId_asiento());
                statement.executeUpdate();

                statement.close();

            } catch (SQLException ex) {
                System.out.println("Error al modificar un asiento: " + ex.getMessage());
            }

        }
    
    ///retorna una lista de asientos (select)
    public List<Asiento> obtenerAsientosPorVuelo(int id){
        List<Asiento> asientos = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM asientos WHERE vuelo_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Asiento asiento;
            while(resultSet.next()){
                asiento = new Asiento();
                asiento.setId_asiento(resultSet.getInt(1));
                asiento.setLetra(resultSet.getString(2));
                asiento.setNumero(resultSet.getInt(3));
                asiento.setEsPasillo(resultSet.getBoolean(4));
                asiento.setDisponibilidad(resultSet.getInt(5));
                asiento.setPrecio(resultSet.getDouble(6));
                Vuelo vuelo=buscarVuelo(resultSet.getInt(7));
                asiento.setVuelo(vuelo);

                asientos.add(asiento);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los asientos: " + ex.getMessage());
        }
        
        
        return asientos;
    }
    
    ///retorna un vuelo segun su id
    public Vuelo buscarVuelo(int id){
    
        VueloData vd=new VueloData(conexion);
        
        return vd.buscarVuelo(id);
        
    }
    
    ///retorna un cliente segun su id
    public Asiento buscarAsiento(int id){
    Asiento asiento=null;
    try {
            
            String sql = "SELECT * FROM asientos WHERE id =?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                asiento = new Asiento();
                asiento.setId_asiento(resultSet.getInt(1));
                asiento.setLetra(resultSet.getString(2));
                asiento.setNumero(resultSet.getInt(3));
                asiento.setEsPasillo(resultSet.getBoolean(4));
                asiento.setDisponibilidad(resultSet.getInt(5));
                asiento.setPrecio(resultSet.getDouble(6));
                Vuelo vuelo=buscarVuelo(resultSet.getInt(7));
                asiento.setVuelo(vuelo);
            }      
            statement.close();
            
            
            
            
    
        } catch (SQLException ex) {
            System.out.println("Error al obtener un asiento: " + ex.getMessage());
        }
        
        return asiento;
    }
    
}

