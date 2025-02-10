package view;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class TelaCadastroProduto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel Titulo;
    private JLabel label1;
    private JTextField campoCodigo;
    private JTextField campoDescricao;
    private JTextField campoPreco;
    private JButton listarButton;
    private JFormattedTextField formattedTextField1;
    private ProdutoDao produtoDao;

    public TelaCadastroProduto() {
        produtoDao = new ProdutoDao();
        setContentPane(contentPane);
        setModal(true);
        setTitle("Cadastro de Produtos");
        ImageIcon icon = new ImageIcon("img/icon.png");
        setIconImage(icon.getImage());
        getRootPane().setDefaultButton(buttonOK);

        //Função do botao salvar
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarCampos()){
                    String codigoString = campoCodigo.getText();
                    int codigo = Integer.parseInt(codigoString);
                    String descricao = campoDescricao.getText();
                    String precoString = campoPreco.getText();
                    Float preco = Float.parseFloat(precoString);
                    String validadeString =  formattedTextField1.getText();
                    LocalDate validade = LocalDate.parse(validadeString);
                    Produto produto = new Produto(codigo,descricao,preco,validade);


                    try {
                        if(produtoDao.adicionarProduto(produto)){
                            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Produto já cadastrado!", null, JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            private boolean validarCampos() {
                if(campoCodigo.getText().isEmpty() || campoDescricao.getText().isEmpty() || campoPreco.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "É necessário preencher todos os campos! ");
                    return false;
                }
                return true;
            }
        });
        //Função do botao excluir
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoString = campoCodigo.getText();
                int codigo = Integer.parseInt(codigoString);
                Produto produto = new Produto(codigo,null,null,null);

                try {
                    if(produtoDao.deletarProduto(produto)){
                        JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Produto não encontrado!", "erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Produto não encontrado!", "erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVisualizarProdutos visualizarProdutos = new TelaVisualizarProdutos();
                visualizarProdutos.pack();
                visualizarProdutos.setLocationRelativeTo(null);
                visualizarProdutos.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        formattedTextField1 = new JFormattedTextField();
        try {
            MaskFormatter formatter = new MaskFormatter("####-##-##");
            formatter.install(formattedTextField1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        ImageIcon icon = new ImageIcon("img/icon.png");
        label1 = new JLabel(icon);
        label1.setIcon(icon);

        buttonOK = new JButton("");
        ImageIcon icon2 = new ImageIcon("img/saveicon.png");
        buttonOK.setIcon(icon2);

        buttonCancel = new JButton("");
        ImageIcon icon3 = new ImageIcon("img/deleteicon.png");
        buttonCancel.setIcon(icon3);

    }
}
