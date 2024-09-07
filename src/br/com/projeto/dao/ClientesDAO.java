package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDAO {
    
    private Connection con;
    
    //Esse método conecta no banco de dado
    //Tudo que for programado no construtor da classe, será executado quando 
    //você instancia um objeto com esse construtor
    public ClientesDAO(){
        // getConnection cria uma conexão com o bando de dados e retorna um objeto do tipo connection
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Método cadastrar Cliente
    public void cadastrarCliente(Clientes obj){
        
        try {
            
            //Criar o comando SQL
            String sql = "insert into tb_clientes (nome"
                                                + ",rg"
                                                + ",cpf"
                                                + ",email"
                                                + ",telefone"
                                                + ",celular"
                                                + ",cep"
                                                + ",endereco"
                                                + ",numero"
                                                + ",complemento"
                                                + ",bairro"
                                                + ",cidade,"
                                                + "estado) "
                                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //Conectar o banco de dados e organizar o comando SQL
            //PreparedStatement, para organizar e executar o comando SQL            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            //(coloca na primeira posição, oq tiver dentro do atributo nome, dentro do obj do tipo cliente)
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
              
        //Executar o comando SQL 
           stmt.execute();
           stmt.close();
        
           JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
               
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, "Erro: ");       
        } 
    }
    
    //Método Alterar clientes
    public void alterarCliente(Clientes obj){
        
        try {
            
            //Criando comando SQL
            String sql = "update tb_clientes set nome=?"
                                            + ", rg=?"
                                            + ", cpf=?"
                                            + ", email=?"
                                            + ", telefone=?"
                                            + ", celular=?"
                                            + ", cep=?"
                                            + ", endereco=?"
                                            + ", numero=?"
                                            + ", complemento=?"
                                            + ", bairro=?"
                                            + ", cidade=?"
                                            + ", estado=?"
                                            + "  where id =? ";
                               
            //Conectar o banco de dados e organizar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getTelefone());
            stmt.setString(6,obj.getCelular());
            stmt.setString(7,obj.getCep());
            stmt.setString(8,obj.getEndereco());
            stmt.setInt(9,obj.getNumero());
            stmt.setString(10,obj.getComplemento());
            stmt.setString(11,obj.getBairro());
            stmt.setString(12,obj.getCidade());
            stmt.setString(13,obj.getEstado());
            stmt.setInt(14,obj.getId());
            
            //Executar comando SQL
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);       
        }   
    }
    
    //Método Excluir
    public void excluirCliente(Clientes obj){
        
        try {

            //Criar o comando sql
            String sql = "delete from tb_clientes where id = ?";

            //Conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //Executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
         
    }
    
    //Método Listar Todos os Clientes
    //Método vai retorna uma lista de Clientes
    //(list) vai retorna uma lista de clientes
    public List<Clientes> listarClientes() {
        
        try {

            //Criar a lista
            List<Clientes> lista = new ArrayList<>();

            //Criar o comando sql, organizar e executar.
            String sql = "select * from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            //Todo comando Select, executa e armazena o resultado dele
            //em outro objeto(Resulset)
            //RS vai receber a execução do SQL
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Clientes obj = new Clientes();
                
                /*Comando para pegar oque ele achar na coluna "id"
                que é do tipo Int, e armazenar dentro do obj
                no atributo Id, e assim fazer para todos os atributos 
                da classe Clientes.
                */
                
                //Pegando tudo que tem dentro do rs, e colocando nos 
                //seus devidos atributos, dentro do obj
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            
            return null;
        }
    }
    
    //Método Consulta por CPF
    //Retorna um Objeto do tipo Cliente
    public Clientes consultaPorCpf(String cpf){
        
        try {
            
            //Criar o comando sql, organizar e executar.            
            String sql = "select * from tb_clientes where cpf =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            
            Clientes obj = new Clientes();
            
            //Se ele encontrar alguém, ele vai montar o objeto
            //pega todos os dados.
            if(rs.next()){                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));   
            }else{
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }
            
            return obj;
              
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e); 
            
            return null;
        }              
    }
    
    //Método buscar Cliente por nome
    public List<Clientes> buscaClientePorNome(String nome){
        
        try {
            
            //Lista
            List<Clientes> lista = new ArrayList<>();
            
            //Criar o comando sql, organizar e executar.
            String sql = "select * from tb_clientes where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            //Todo comando Select, executa e armazena o resultado dele
            //em outro objeto(Resulset)
            //RS vai receber a execução do SQL
            ResultSet rs = stmt.executeQuery();
            
            //Enquanto ele conseguir percorrer os registros
            while(rs.next()){
                
                //Vou capturar oq ele pegou no rs, e passar para OBJ
                Clientes obj = new Clientes();
                
                /*
                Pegar oq ele encontrar na coluna id que é do tipo Int 
                e armazenar dentro de OBJ no abributo Id, e fazer isso
                com os demais registros.               
                */
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                
                lista.add(obj);
                
            }
            
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            
            return null;
        }      
    }
    
    //Busca Cep
    
    public Clientes buscaCep(String cep){
        
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        
        Clientes obj = new Clientes();
        
        //Se essa pesquisa tiver Sucesso, quero que ele preencha OBJ com as informação
        if(webServiceCep.wasSuccessful()){
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        }else{
            JOptionPane.showMessageDialog(null, "Erro número: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }
    }
}
    
   

