package dao;

import model.Produto;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDao {

    private File arquivo;

    public ProdutoDao() {
        arquivo = new File("Produtos");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean adicionarProduto(Produto produto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        if(produtos.add(produto)) {
            try(ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(arquivo))) {
                out.writeObject(produtos);
            }
            return true;
        }
        return false;
    }

    public Set<Produto> getProdutos() throws IOException, ClassNotFoundException {
        if(arquivo.length() == 0){
            return new HashSet<>();
        }

        try(ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(arquivo))){
            return (Set<Produto>) in.readObject();
        }
    }

    public boolean deletarProduto(Produto produto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        if(produtos.remove(produto)){
            atualizarArquivo(produtos);
            return true;
        }
        return false;
    }

    public boolean atulizar(Produto produto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        if(produtos.contains(produto)){
            if(produtos.remove(produto) && produtos.add(produto)){
                atualizarArquivo(produtos);
                return true;
            }
        }
        return false;
    }

    private void atualizarArquivo(Set<Produto> produtos) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivo))){
            out.writeObject(produtos);
        }
    }
}

