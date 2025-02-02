package view;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int id;
        String descricao;
        Float preco;
        LocalDate validade;
        int escolha;

        ProdutoDao dao = new ProdutoDao();

        do{
            escolha = Integer.parseInt(JOptionPane.showInputDialog(null,"Escolha uma opção:\n" +
                    "1 - Cadastrar Produto\n" +
                    "2 - Visualizar Produtos\n" +
                    "3 - Excluir Produto\n" +
                    "4 - Atualizar Produto\n" +
                    "5 - Sair do programa\n"));

            switch (escolha) {
                case 1:
                    id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto: "));
                    descricao = JOptionPane.showInputDialog("Descrição do produto: ");
                    preco = Float.parseFloat(JOptionPane.showInputDialog("Informe o valor da produto: "));
                    validade = LocalDate.parse(JOptionPane.showInputDialog("Informe a data de validade: "));

                    dao.adicionarProduto(new Produto(id,descricao,preco,validade));
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null,"Produtos listados:" +
                            dao.getProdutos());
                    break;

                case 3:
                    id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto: "));
                    dao.deletarProduto(new Produto(id,null,null,null));
                    System.out.println(dao.getProdutos());
                    break;
                case 4:
                    id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto: "));
                    descricao = JOptionPane.showInputDialog("Descrição do produto: ");
                    preco = Float.parseFloat(JOptionPane.showInputDialog("Informe o valor da produto: "));
                    validade = LocalDate.parse(JOptionPane.showInputDialog("Informe a data de validade: "));

                    dao.atulizar(new Produto(id,descricao,preco,validade));
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }while (escolha != 5);
    }
}