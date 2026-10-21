/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Categoria;
import Conexao.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateu
 */
public class CategoriaDAO {
    
    public boolean inserir(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setString(1, categoria.getNome());
            comando.setString(2, categoria.getDescricao());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM categoria ORDER BY id";
        
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()) {
            
            while (resultado.next()) {
                Categoria categoria = new Categoria(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("descricao")
                );
                
                categorias.add(categoria);
            }
        }
        
        return categorias;
    }
    
    public boolean atualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria set nome = ?, descricao = ? WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setString(1, categoria.getNome());
            comando.setString(2, categoria.getDescricao());
            comando.setInt(3, categoria.getId());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setInt(1, id);
            
            return comando.executeUpdate() > 0;
        }
    }
    
}
