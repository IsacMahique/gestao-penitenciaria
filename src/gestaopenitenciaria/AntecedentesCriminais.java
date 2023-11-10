package gestaopenitenciaria;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AntecedentesCriminais extends JFrame implements ActionListener{
    
    
    private String nome;
    private String id;
    private String crimes;
    
    
    //accao botao
    public void actionPerformed(ActionEvent e){
        
        setNome();
        setId();
        setCrimes();
        
        if(e.getSource()==jbregistar){
            
            //verificar processo
             try{
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            Connection conexao= DriverManager.getConnection(url, "root", "vertrigo");
            PreparedStatement pesquisa= conexao.prepareStatement("SELECT * FROM processo");
            ResultSet resultado = pesquisa.executeQuery();
            while(resultado.next()){
                String nome= resultado.getString("nome");
                String id= resultado.getString("id");
                String dataDeNascimento= resultado.getString("nascimento");
                String contacto= resultado.getString("contacto");
                String endereco= resultado.getString("endereco");
                String genero= resultado.getString("genero");
                String nacionalidade= resultado.getString("nacionalidade");
                
                 if (id.equals(getId())){
                    gravarAntecedentesCriminais();
                    this.dispose();
                    new Administrador();
                 }else{
                     JOptionPane.showMessageDialog(null,"Processo nao encontrado!");
                 }
            }
            
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null,"Erro \n "+ erro);
         }
            //verificar processo
            
            this.dispose();
            new Administrador();
       }
    }
    
    Font f1= new Font("Calibre", Font.BOLD,15);
    Font f2= new Font("Calibre", Font.ITALIC,15);
    JLabel jlnomeCompleto= new JLabel("Nome Completo ");
    JTextField jtnomeCompleto= new JTextField(200);
    JLabel jlid= new JLabel("ID do Detento");
    JTextField jtid= new JTextField(200);
    JLabel jlcrimes= new JLabel("Datas de Condenacao  -  crimes  -  setenca");
    JTextArea jtcrimes= new JTextArea("exemplo:   "+ "20/05/2023 - furto(subtracao) - quatro(4) anos + multa"
            + " de 18.075,00 MT");
    JButton jbregistar = new JButton("R E G I S T A R");
    
    public AntecedentesCriminais(){
        
        setLayout(null);
        jbregistar.addActionListener(this);
        
        jlnomeCompleto.setFont(f1);
        jlid.setFont(f1);
        jlcrimes.setFont(f1);
        
        jlnomeCompleto.setBounds(50,50,150,30);
        jtnomeCompleto.setBounds(200,50,400,30);
        add(jlnomeCompleto);
        add(jtnomeCompleto);
        
        jlid.setBounds(50,100,150,30);
        jtid.setBounds(200,100,400,30);
        add(jlid);
        add(jtid);
        
        jlcrimes.setBounds(50, 180, 600, 30);
        jtcrimes.setBounds(50, 210, 800, 150);
        jtcrimes.setFont(f2);
        add(jlcrimes);
        add(jtcrimes);
        
        jbregistar.setBounds(700, 500, 200, 50);
        add(jbregistar);
        
        
        setTitle("Antecedentes Criminais");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome() {
        this.nome = jtnomeCompleto.getText();
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = jtid.getText();
    }

    public String getCrimes() {
        return crimes;
    }

    public void setCrimes() {
        this.crimes = jtcrimes.getText();
    }
    
    
     //gravar antecedentes criminais
     public void gravarAntecedentesCriminais(){
         
         
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sql= "INSERT INTO crimes(nome, id, crimes) values( ?, ?, ?)";
            
            try{
                Connection conexao = DriverManager.getConnection(url, "root", "vertrigo");
                PreparedStatement actualizar = conexao.prepareStatement (sql);
                
                
                actualizar.setString(1, getNome());
                actualizar.setString(2, getId());
                actualizar.setString(3, getCrimes());
                
                actualizar.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
            
     }
     
    
}
