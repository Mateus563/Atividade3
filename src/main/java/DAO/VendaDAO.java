/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Venda;
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
public class VendaDAO {
    
    public boolean inserir(Venda venda) throws SQLException {
        String sql = "INSERT INTO venda (data_venda, valor_total, forma_pagamento, id_cliente) VALUES (?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            comando.setDouble(2, venda.getValorTotal());
            comando.setString(3, venda.getFormaPagamento());
            comando.setInt(4, venda.getIdCliente());
             
            return comando.executeUpdate() > 0;
        }
    }
    
    public List<Venda> listar() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT id, data_venda, valor_total, forma_pagamento, id_cliente FROM venda ORDER BY id";
        
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()) {
            
            while (resultado.next()) {
                Venda venda = new Venda(
                    resultado.getInt("id"),
                    resultado.getDate("data_venda").toString(),
                    resultado.getDouble("valor_total"),
                    resultado.getString("forma_pagamento"),
                    resultado.getInt("id_cliente")
                );
                
                vendas.add(venda);
            }    
        }
        
        return vendas;
    }
    
    public boolean atualizar(Venda venda) throws SQLException {
        String sql = "UPDATE venda set data_venda = ?, valor_total = ?, forma_pagamento = ?, id_cliente = ? WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            comando.setDouble(2, venda.getValorTotal());
            comando.setString(3, venda.getFormaPagamento());
            comando.setInt(4, venda.getIdCliente());
            comando.setInt(5, venda.getId());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM venda WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement (sql)) {
            
            comando.setInt(1, id);
            
            return comando.executeUpdate() > 0;
        }
    }
}
