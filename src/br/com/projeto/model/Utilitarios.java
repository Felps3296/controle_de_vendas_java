package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Utilitarios {
    
    //Método limparCampos
    //Você vai informa no Jpanel, onde está os campos que você quer limpar
    //ele vai percorrer cada campo, e vai setar o texto para null
    public void LimpaTela(JPanel container){
       Component components[] = container.getComponents();
       for(Component component : components){
           if(component instanceof JTextField){
               ((JTextField)component).setText(null);
           }
       }
    }    
}
