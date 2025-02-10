package view;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TelaVisualizarProdutos extends JDialog {
    private JPanel contentPane;
    private JTable table1;
    private JButton buttonOK;

    public TelaVisualizarProdutos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    private void createUIComponents() {
        ProdutoDao dao = new ProdutoDao();
        Set<Produto> produtos = null;
        try {
            produtos = dao.getProdutos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(produtos!=null) {
            table1 = new JTable();
            String titulos[] = {"Codigo","Descricao","Preco", "Validade"};
            String conteudo[][] = new String[produtos.size()][titulos.length];
            List<Produto> produtoList = produtos.stream().sorted(Comparator.comparing(Produto::getId)).toList();

            for (int i = 0; i < produtoList.size(); i++) {
                conteudo[i][0] = "" + produtoList.get(i).getId();
                conteudo[i][1] = produtoList.get(i).getDescricao();
                conteudo[i][2] = "" + (produtoList.get(i).getPreco());
                conteudo[i][3] = "" + (produtoList.get(i).getValidade());
            }

            table1.setModel(new DefaultTableModel(conteudo, titulos));
        }
    }
}
