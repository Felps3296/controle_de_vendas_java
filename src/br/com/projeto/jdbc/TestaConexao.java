package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

public class TestaConexao {
    
    public static void main(String args[]){
        
        try {
            
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com Sucesso");
                    
        } catch (Exception  e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao Conectar: " + e);
                                                                           
        }
    }
      
}
