/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Produto;
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
public class ProdutoDAO {
    
    public boolean inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto (nome, descricao, preco, quantidade_estoque, id_categoria) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {
            
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDescricao());
            comando.setDouble(3, produto.getPreco());
            comando.setInt(4, produto.getQuantidadeEstoque());
            comando.setInt(5, produto.getIdCategoria());
            
            return comando.executeUpdate() > 0;
        }
    }
    
    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, preco, quantidade_estoque, id_categoria FROM produto ORDER BY id";
        
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql);
                ResultSet resultado = comando.executeQuery()) {
            
            while (resultado.next()) {
                Produto produto = new Produto(
                    resultado.getInt("id"),
                    resultado.getString("nome"),
                    resultado.getString("descricao"),
                    resultado.getDouble("preco"),
                    resultado.getInt("quantidade_estoque"),
                    resultado.getInt("id_categoria")
                );
                
                produtos.add(produto);
            }
        }
        
        return produtos;
    }
    
    public boolean atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produto set nome = ?, descricao = ?, preco = ?, quantidade_estoque = ?, id_categoria = ? WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement (sql)) {
            
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getDescricao());
            comando.setDouble(3, produto.getPreco());
            comando.setInt(4, produto.getQuantidadeEstoque());
            comando.setInt(5, produto.getIdCategoria());
            comando.setInt(6, produto.getId());
                
            return comando.executeUpdate() > 0;
            
        }
    }
    
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        
        try (Connection conexao = ConexaoDAO.conectar();
            PreparedStatement comando = conexao.prepareStatement (sql)) {
        
            comando.setInt(1, id);
        
            return comando.executeUpdate() > 0;
        }     
    }
    
}
