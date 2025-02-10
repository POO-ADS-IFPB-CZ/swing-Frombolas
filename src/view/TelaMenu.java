package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaMenu extends JFrame {
    private JPanel contentPane;
    private JButton produtosButton;
    private JButton clientesButton;
    private JButton vendasButton;
    private JButton buttonOK;

    public TelaMenu() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("img/icon.png");
        setIconImage(icon.getImage());
        setTitle("Supermercado Orientado");
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setBounds(0,0,600,300);
        setLocationRelativeTo(null);

        produtosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroProduto cadastroProduto = new TelaCadastroProduto();
                dispose();
                cadastroProduto.pack();
                cadastroProduto.setLocationRelativeTo(null);
                cadastroProduto.setVisible(true);
                setVisible(true);

            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
                dispose();
                cadastroCliente.pack();
                cadastroCliente.setLocationRelativeTo(null);
                cadastroCliente.setVisible(true);
                setVisible(true);
            }
        });
        vendasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entrou " + e.getX() + " " + e.getY());

            }
        });
    }

    public static void main(String[] args) {
        TelaMenu dialog = new TelaMenu();
        //dialog.pack();
        dialog.setVisible(true);
    }
}
