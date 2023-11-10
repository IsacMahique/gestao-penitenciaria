package gestaopenitenciaria;

import java.awt.Color;
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

public class Visitas extends JFrame implements ActionListener{
    
    private String nome;
    private String id;
    private String visitas;
    private String outrasVisitas;
    
    
    //accao botao
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jbconcluir){
            setNome();
            setId();
            setVisitas();
            setOutrasVisitas();
            
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
                    gravarVisitas();
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
    Font f2= new Font("Calibre", Font.BOLD,20);
    Font f3= new Font("MONOSPACED", Font.BOLD,20);
    Font f4= new Font("Calibre", Font.ITALIC,17);
    JLabel jlid= new JLabel("ID do Detento");
    JTextField jtid= new JTextField();
    JLabel jlnomeCompleto= new JLabel("Nome Completo ");
    JTextField jtnomeCompleto= new JTextField();
    JLabel jlnomeDeVisitante= new JLabel("Visitantes Frequentes");
    JLabel jlnomeVisitante= new JLabel("Nome");
    JTextField jtnomeVisitante= new JTextField();
    JLabel jlparentesco= new JLabel("Parentesco");
    JTextField jtparentesco= new JTextField();
    JLabel jlopcional= new JLabel("OPCIONAL");
    JLabel jloutrosVisitantes= new JLabel("Outros Visitantes");
    JTextArea jtoutrosVisitantes= new JTextArea(" exemplo: Fulano deFulano - primo");
    JButton jbconcluir= new JButton("C O N C L U I R");
    
    public Visitas(){
        setLayout(null);
        jbconcluir.addActionListener(this);
        
        jlid.setFont(f1);
        jlnomeCompleto.setFont(f1);
        jlnomeDeVisitante.setFont(f2);
        jlnomeVisitante.setFont(f1);
        jlparentesco.setFont(f1);
        jlopcional.setFont(f3);
        jloutrosVisitantes.setFont(f1);
        jtoutrosVisitantes.setFont(f4);
        
        
        jlid.setBounds(50,50,150,30);
        jtid.setBounds(200,50,400,30);
        add(jlid);
        add(jtid);
        
        jlnomeCompleto.setBounds(50,100,150,30);
        jtnomeCompleto.setBounds(200,100,400,30);
        add(jlnomeCompleto);
        add(jtnomeCompleto);
        
        jlnomeDeVisitante.setBounds(50, 200, 400, 30);
        jlnomeVisitante.setBounds(50, 250, 100, 30);
        jtnomeVisitante.setBounds(50, 280, 250, 30);
        jlparentesco.setBounds(350, 250, 100, 30);
        jtparentesco.setBounds(350, 280, 250, 30);
        add(jlnomeDeVisitante);
        add(jlnomeVisitante);
        add(jtnomeVisitante);
        add(jlparentesco);
        add(jtparentesco);
        
        jlopcional.setBounds(50, 350, 200, 30);
        jlopcional.setForeground(Color.red);
        add(jlopcional);
        
        jloutrosVisitantes.setBounds(50, 380, 200, 30);
        jtoutrosVisitantes.setBounds(50, 410, 500, 100);
        add(jloutrosVisitantes);
        add(jtoutrosVisitantes);
        
        jbconcluir.setBounds(700, 500, 200, 50);
        add(jbconcluir);
        
        setTitle("VISITAS");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

    public String getVisitas() {
        return visitas;
    }

    public void setVisitas() {
        this.visitas = jtnomeVisitante.getText()+" - "+jtparentesco.getText();
    }

    public String getOutrasVisitas() {
        return outrasVisitas;
    }

    public void setOutrasVisitas() {
        this.outrasVisitas = jtoutrosVisitantes.getText();
    }
    
    //gravar visitas
     public void gravarVisitas(){
         
         
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sql= "INSERT INTO visita (nome, id, visitas, outrasVisitas) "
                    + "values( ?, ?, ?, ?)";
            
            try{
                Connection conexao = DriverManager.getConnection(url, "root", "vertrigo");
                PreparedStatement actualizar = conexao.prepareStatement (sql);
                
                actualizar.setString(1, getNome());
                actualizar.setString(2, getId());
                actualizar.setString(3, getVisitas());
                actualizar.setString(4, getOutrasVisitas());
                
                actualizar.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
            
     }
     
}
