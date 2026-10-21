/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cliente;
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
public class ClienteDAO {
    
    public boolean inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, cep) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getTelefone());
            comando.setString(4, cliente.getEmail());
            comando.setString(5, cliente.getCep());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, telefone, email, cep FROM cliente ORDER BY id";
        
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()) {
            
            while (resultado.next()) {
                Cliente cliente = new Cliente(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("cpf"),
                    resultado.getString("telefone"),
                    resultado.getString("email"),
                    resultado.getString("cep")
                );
                
                clientes.add(cliente);
            }
        }
    
        return clientes;
    }
    
    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente set nome = ?, cpf = ?, telefone = ?, email = ?, cep = ? WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getTelefone());
            comando.setString(4, cliente.getEmail());
            comando.setString(5, cliente.getCep());
            comando.setInt(6, cliente.getId());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement (sql)) {
            
            comando.setInt(1, id);
            
            return comando.executeUpdate() > 0;
        }
    }
}
