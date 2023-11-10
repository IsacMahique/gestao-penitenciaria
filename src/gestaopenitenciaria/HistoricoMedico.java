package gestaopenitenciaria;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class HistoricoMedico extends JFrame implements ActionListener{
    
    private String nome;
    private String id;
    private String nomeDaDoenca;
    private String cuidados;
    
    
    //accao botao
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jbconcluir){
            setNome();
            setId();
            setNomeDaDoenca();
            setCuidados();
            
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
                    gravarHistoricoMedico();
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
    Font f2= new Font("Calibre", Font.ROMAN_BASELINE,15);
    JLabel jlid= new JLabel("ID do Detento");
    JTextField jtid= new JTextField();
    JLabel jlnomeCompleto= new JLabel("Nome Completo ");
    JTextField jtnomeCompleto= new JTextField();
    JLabel jldoenca= new JLabel("Sofre de algum problema de saude?");
    JRadioButton jrdoencaSim= new JRadioButton("Sim");
    JRadioButton jrdoencaNao= new JRadioButton("Não");
    ButtonGroup bgdoenca= new ButtonGroup();
    JLabel jlnomeDaDoenca= new JLabel("Nome da doenca:");
    JTextField jtnomeDaDoenca= new JTextField();
    JLabel jlcuidados= new JLabel("Cuidados necessario:");
    JTextArea jtcuidados= new JTextArea();
    JLabel jlignore= new JLabel("IGNORE ESTE CAMPO CASO NAO TENHA NENHUMA DOENCA.");
    JLabel jlignorar= new JLabel("IGNORE ESTE CAMPO CASO NAO TENHA NENHUMA DOENCA.");
    JButton jbconcluir= new JButton("  CONCLUIR  ");
    
    
    
    
    public HistoricoMedico(){
        setLayout(null);
        jbconcluir.addActionListener(this);
        
        jlid.setFont(f1);
        jlnomeCompleto.setFont(f1);
        jldoenca.setFont(f1);
        jrdoencaSim.setFont(f1);
        jrdoencaNao.setFont(f1);
        jlnomeDaDoenca.setFont(f1);
        jlcuidados.setFont(f1);
        jlignore.setFont(f2);
        jlignorar.setFont(f2);
        
        jlid.setBounds(50,50,150,30);
        jtid.setBounds(200,50,400,30);
        add(jlid);
        add(jtid);
        
        jlnomeCompleto.setBounds(50,100,150,30);
        jtnomeCompleto.setBounds(200,100,400,30);
        add(jlnomeCompleto);
        add(jtnomeCompleto);
        
        jldoenca.setBounds(50,200, 300, 30);
        jrdoencaSim.setBounds(450, 200, 100, 30);
        jrdoencaNao.setBounds(650, 200, 100, 30);
        add(jldoenca);
        add(jrdoencaSim);
        add(jrdoencaNao);
        bgdoenca.add(jrdoencaSim);
        bgdoenca.add(jrdoencaNao);
        
        jlnomeDaDoenca.setBounds(50, 250, 300, 30);
        jtnomeDaDoenca.setBounds(200, 250, 300, 30);
        jlignorar.setBounds(510, 250, 500, 30);
        add(jlnomeDaDoenca);
        add(jtnomeDaDoenca);
        add(jlignorar);
        
        jlcuidados.setBounds(50, 300, 300, 30);
        jtcuidados.setBounds(50, 330, 500, 100);
        add(jlcuidados);
        add(jtcuidados);
   
        jlignore.setBounds(50, 430, 500, 30);
        add(jlignore);
        
        jbconcluir.setBounds(700, 500, 250, 50);
        add(jbconcluir);
        
        
        setTitle("HISTORICO MEDICO");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    //setters e getters

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

    public String getNomeDaDoenca() {
        return nomeDaDoenca;
    }

    public void setNomeDaDoenca() {
        this.nomeDaDoenca = jtnomeDaDoenca.getText();
    }

    public String getCuidados() {
        return cuidados;
    }

    public void setCuidados() {
        this.cuidados = jtcuidados.getText();
    }
    
       
    //gravar h.medico
     public void gravarHistoricoMedico(){
         
         
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sql= "INSERT INTO historicomedico(nome, id, nomedadoenca, cuidados) "
                    + "values( ?, ?, ?, ?)";
            
            try{
                Connection conexao = DriverManager.getConnection(url, "root", "vertrigo");
                PreparedStatement actualizar = conexao.prepareStatement (sql);
                
                actualizar.setString(1, getNome());
                actualizar.setString(2, getId());
                actualizar.setString(3, getNomeDaDoenca());
                actualizar.setString(4, getCuidados());
                
                actualizar.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
            
     }
     
        
}
