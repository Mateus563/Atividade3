/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexao.ConexaoDAO;
import Model.ItemVenda;
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
public class ItemVendaDAO {

    // CADASTRAR
    public boolean inserir(ItemVenda item) throws SQLException {
        String sql = "INSERT INTO item_venda (quantidade, subtotal, id_produto, id_venda) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, item.getQuantidade());
            comando.setDouble(2, item.getSubtotal());
            comando.setInt(3, item.getIdProduto());
            comando.setInt(4, item.getIdVenda());

            return comando.executeUpdate() > 0;
        }
    }


    public List<ItemVenda> listar() throws SQLException {
        List<ItemVenda> itens = new ArrayList<>();
        String sql = "SELECT id, quantidade, subtotal, id_produto, id_venda FROM item_venda ORDER BY id";

        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                ItemVenda item = new ItemVenda(
                    resultado.getInt("id"),
                    resultado.getInt("quantidade"),
                    resultado.getDouble("subtotal"),
                    resultado.getInt("id_produto"),
                    resultado.getInt("id_venda")
                );

                itens.add(item);
            }
        }

        return itens;
    }

    public boolean atualizar(ItemVenda item) throws SQLException {
        String sql = "UPDATE item_venda SET quantidade = ?, subtotal = ?, id_produto = ?, id_venda = ? WHERE id = ?";

        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, item.getQuantidade());
            comando.setDouble(2, item.getSubtotal());
            comando.setInt(3, item.getIdProduto());
            comando.setInt(4, item.getIdVenda());
            comando.setInt(5, item.getId());

            return comando.executeUpdate() > 0;
        }
    }


    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM item_venda WHERE id = ?";

        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            return comando.executeUpdate() > 0;
        }
    }
}