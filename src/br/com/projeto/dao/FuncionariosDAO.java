package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.view.Frmmenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionariosDAO {
    
    private java.sql.Connection con;
    
    public FuncionariosDAO(){
        
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Método Cadastrar
    public void cadastrarFuncionarios(Funcionarios obj){
        
        try {
            
            //Criar o comando SQL
            String sql = "insert into tb_funcionarios (nome"
                                                + ",rg"
                                                + ",cpf"
                                                + ",email"
                                                + ",senha"
                                                + ",cargo"
                                                + ",nivel_acesso"
                                                + ",telefone"
                                                + ",celular"
                                                + ",cep"
                                                + ",endereco"
                                                + ",numero"
                                                + ",complemento"
                                                + ",bairro"
                                                + ",cidade,"
                                                + "estado) "
                                                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //Conectar o banco de dados e organizar o comando SQL
            //PreparedStatement, para organizar e executar o comando SQL            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            //(coloca na primeira posição, oq tiver dentro do atributo nome, dentro do obj do tipo cliente)
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
              
           stmt.execute();
           stmt.close();
        
           JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
               
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, "Erro: ");       
        } 
    }
    
    
    //Método Excluir
    public void excluirFuncionario(Funcionarios obj){
        
        try {
            
            String sql = "delete from tb_funcionarios where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
            
        } catch (SQLException erro) {            
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        
    }
    
    
    //Método alterar
    public void alterarFuncionario(Funcionarios obj){
        
        try {
            
            String sql = "update tb_funcionarios set nome=?"
                                            + ", rg=?"
                                            + ", cpf=?"
                                            + ", email=?"
                                            + ", senha=?"
                                            + ", cargo=?"
                                            + ", nivel_acesso"
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
                               
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,obj.getNome());
            stmt.setString(2,obj.getRg());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getEmail());
            stmt.setString(5,obj.getSenha());
            stmt.setString(6,obj.getCargo());
            stmt.setString(7,obj.getNivel_acesso());
            stmt.setString(8,obj.getTelefone());
            stmt.setString(9,obj.getCelular());
            stmt.setString(10,obj.getCep());
            stmt.setString(11,obj.getEndereco());
            stmt.setInt(12,obj.getNumero());
            stmt.setString(13,obj.getComplemento());
            stmt.setString(14,obj.getBairro());
            stmt.setString(15,obj.getCidade());
            stmt.setString(16,obj.getEstado());
            stmt.setInt(17,obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);       
        }   
    }
    
    
     //Método Consulta por CPF
    //Retorna um Objeto do tipo Cliente
    public Funcionarios consultaPorCpf(String cpf){
        
        try {
            
            //Criar o comando sql, organizar e executar.            
            String sql = "select * from tb_funcionarios where cpf =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            
            Funcionarios obj = new Funcionarios();
            
            //Se ele encontrar alguém, ele vai montar o objeto
            //pega todos os dados.
            if(rs.next()){                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
            }
            
            return obj;
              
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e); 
            
            return null;
        }              
    }
    
    
    //Método buscar Funcionario por nome
    public List<Funcionarios> buscaFuncionarioPorNome(String nome){
        
        try {
            
            //Lista
            List<Funcionarios> lista = new ArrayList<>();
            
            //Criar o comando sql, organizar e executar.
            String sql = "select * from tb_funcionarios where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            //Todo comando Select, executa e armazena o resultado dele
            //em outro objeto(Resulset)
            //RS vai receber a execução do SQL
            ResultSet rs = stmt.executeQuery();
            
            //Enquanto ele conseguir percorrer os registros
            while(rs.next()){
                
                //Vou capturar oq ele pegou no rs, e passar para OBJ
                Funcionarios obj = new Funcionarios();
                
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
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
    
    
    public List<Funcionarios> listarFuncionarios() {
        try {

            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));

                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));

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
 
    
    public Funcionarios buscaCep(String cep){
        
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        
        Funcionarios obj = new Funcionarios();
       
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
    
    
    //Método Login
    public void efetuaLogin(String email, String senha){
        
        try {
            
            String sql = "select * from tb_funcionarios where email=? and senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                //Usuário logou
                JOptionPane.showMessageDialog(null, "Seja bem vindo(a) ao sistema");
                Frmmenu tela = new Frmmenu();
                tela.usuariologado = rs.getString("nome");
                tela.setVisible(true);
                
            }else{
                //Dados incorretos
                JOptionPane.showMessageDialog(null, "Dados incorretos");
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
        
    }
}
