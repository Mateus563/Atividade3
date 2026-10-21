/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author mateus.moras
 */
public class ConexaoDAO {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lojainformatica";
        String usuario = "postgres";
        String senha = "postgres";
        
        return DriverManager.getConnection(url, usuario, senha);
    }
    
}