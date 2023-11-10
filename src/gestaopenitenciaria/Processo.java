package gestaopenitenciaria;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

//importacoes para sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Processo extends JFrame implements ActionListener{
    
    private String nome;
    private String id;
    private String dataDeNascimento;
    private String contacto;
    private String endereco;
    private String genero;
    private String nacionalidade;
   
    
    //accao botao
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jbregistar){
            //setters
            setNome();
            setId();
            setDataDeNascimento();
            setContacto();
            setEndereco();
            setGenero();
            setNacionalidade();
            
            if(getId().equals("1234")){
                JOptionPane.showMessageDialog(null, "Ja existe um ID correspondente");
            }
            if (jtnomeCompleto.getText().equals("") || jtid.getText().equals("") || jtendereco.getText().equals("") || 
                    jtcontacto.getText().equals("") || jtnacionalidade.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            }else{
                gravarProcesso();
            this.dispose();
            new Administrador();
            }
        }
    }
    
    Font f1= new Font("Calibre", Font.BOLD,15);
    JLabel jlnomeCompleto= new JLabel("Nome Completo ");
    JTextField jtnomeCompleto= new JTextField(200);
    JLabel jlid= new JLabel("ID do Detento");
    JTextField jtid= new JTextField(200);
    JLabel jlendereco= new JLabel("Endereco ");
    JTextField jtendereco= new JTextField(200);
    JLabel jlcontacto= new JLabel("Contacto ");
    JTextField jtcontacto= new JTextField(200);
    JLabel jldataDeNascimento= new JLabel("Data de Nascimento");
    JLabel jldia= new JLabel("Dia");
    JComboBox<String> jcdia= new JComboBox<String>();
    JLabel jlmes= new JLabel("Mes");
    JComboBox<String> jcmes= new JComboBox<String>();
    JLabel jlano= new JLabel("   Ano");
    JComboBox<String> jcano= new JComboBox<String>();
    JLabel jlgenero= new JLabel("Genero");
    JRadioButton jrmasculino= new JRadioButton("Masculino");
    JRadioButton jrfeminino= new JRadioButton("Feminino");
    ButtonGroup bggenero= new ButtonGroup();
    JButton jbregistar = new JButton("   REGISTAR   ");
    JLabel jlnacionalidade= new JLabel("Nacionalidade");
    JTextField jtnacionalidade= new JTextField();
    
    JTable tabela = new JTable();
    
    public Processo(){
        setLayout(null);
        //add accao botao
        jbregistar.addActionListener(this);
        
        jlnomeCompleto.setFont(f1);
        jlid.setFont(f1);
        jlendereco.setFont(f1);
        jlcontacto.setFont(f1);
        jldataDeNascimento.setFont(f1);
        jldia.setFont(f1);
        jlmes.setFont(f1);
        jlano.setFont(f1);
        jlgenero.setFont(f1);
        jbregistar.setFont(f1);
        jrmasculino.setFont(f1);
        jrfeminino.setFont(f1);
        jlnacionalidade.setFont(f1);
        
        jlnomeCompleto.setBounds(50,50,150,30);
        jtnomeCompleto.setBounds(200,50,400,30);
        add(jlnomeCompleto);
        add(jtnomeCompleto);
        
        jlid.setBounds(50,100,150,30);
        jtid.setBounds(200,100,400,30);
        add(jlid);
        add(jtid);
        
        jlendereco.setBounds(50,150, 150, 30);
        jtendereco.setBounds(200,150, 400, 30);
        add(jlendereco);
        add(jtendereco);
        
        jlcontacto.setBounds(50,200, 150, 30);
        jtcontacto.setBounds(200,200, 200, 30);
        add(jlcontacto);
        add(jtcontacto);
        
        jldataDeNascimento.setBounds(50, 250, 200, 30);
        jldia.setBounds(350, 250, 150, 30);
        jcdia.setBounds(400, 250,60, 30);
        add(jldataDeNascimento);
        add(jldia);
        add(jcdia);
        jcdia.addItem("01"); jcdia.addItem("02"); jcdia.addItem("03");
        jcdia.addItem("04"); jcdia.addItem("05"); jcdia.addItem("06");
        jcdia.addItem("07"); jcdia.addItem("08"); jcdia.addItem("09");
        jcdia.addItem("10"); jcdia.addItem("11"); jcdia.addItem("12");
        jcdia.addItem("13"); jcdia.addItem("14"); jcdia.addItem("15");
        jcdia.addItem("16"); jcdia.addItem("17"); jcdia.addItem("18");
        jcdia.addItem("19"); jcdia.addItem("20"); jcdia.addItem("21");
        jcdia.addItem("22"); jcdia.addItem("23"); jcdia.addItem("24");
        jcdia.addItem("15"); jcdia.addItem("26"); jcdia.addItem("27");
        jcdia.addItem("28"); jcdia.addItem("19"); jcdia.addItem("30"); jcdia.addItem("31"); 
        
        jlmes.setBounds(550,250, 50, 30);
        jcmes.setBounds(600, 250, 100, 30);
        add(jlmes);
        add(jcmes);
        jcmes.addItem("Janeiro"); jcmes.addItem("Fevereiro"); jcmes.addItem("Março"); 
        jcmes.addItem("Abril"); jcmes.addItem("Maio"); jcmes.addItem("junho"); 
        jcmes.addItem("Julho"); jcmes.addItem("Agosto"); jcmes.addItem("Setembro"); 
        jcmes.addItem("Outubro"); jcmes.addItem("Novembro"); jcmes.addItem("Dezembro"); 
        
        jlano.setBounds(750, 250, 50, 30);
        jcano.setBounds(800, 250, 80, 30);
        add(jlano);
        add(jcano);
        jcano.addItem("2005"); jcano.addItem("2004"); jcano.addItem("2003"); jcano.addItem("2002");
        jcano.addItem("2001"); jcano.addItem("2000"); jcano.addItem("1999"); jcano.addItem("1998");
        jcano.addItem("1997"); jcano.addItem("1996"); jcano.addItem("1995"); jcano.addItem("1994"); 
        jcano.addItem("1993"); jcano.addItem("1992"); jcano.addItem("1991"); jcano.addItem("1990");
        jcano.addItem("1989"); jcano.addItem("1988"); jcano.addItem("1987"); jcano.addItem("1986");
        jcano.addItem("1985"); jcano.addItem("1984"); jcano.addItem("1983"); jcano.addItem("1982"); 
        jcano.addItem("1981"); jcano.addItem("1980"); jcano.addItem("1979"); jcano.addItem("1978");
        jcano.addItem("1977"); jcano.addItem("1976"); jcano.addItem("1975"); jcano.addItem("1974");
        jcano.addItem("1973"); jcano.addItem("1972"); jcano.addItem("1971"); jcano.addItem("1970");
        jcano.addItem("1969"); jcano.addItem("1968"); jcano.addItem("1967"); jcano.addItem("1966"); 
        jcano.addItem("1965"); jcano.addItem("1964"); jcano.addItem("1963"); jcano.addItem("1962");
        jcano.addItem("1961"); jcano.addItem("1960"); jcano.addItem("1959"); jcano.addItem("1958");
        jcano.addItem("1957"); jcano.addItem("1956"); jcano.addItem("1955"); jcano.addItem("1954");
        jcano.addItem("1953"); jcano.addItem("1952"); jcano.addItem("1951"); jcano.addItem("1950"); 
        jcano.addItem("1949"); jcano.addItem("1948"); jcano.addItem("1947"); jcano.addItem("1946");
        jcano.addItem("1945"); jcano.addItem("1943"); jcano.addItem("1942"); jcano.addItem("1941");
        jcano.addItem("1940"); jcano.addItem("1939"); jcano.addItem("1938"); jcano.addItem("1937");
        jcano.addItem("1936"); jcano.addItem("1935"); jcano.addItem("1934"); jcano.addItem("1933");
        jcano.addItem("1932"); jcano.addItem("1931"); jcano.addItem("1930"); jcano.addItem("1929"); 
        jcano.addItem("1928"); jcano.addItem("1927"); jcano.addItem("1926"); jcano.addItem("1925");
        
        jlgenero.setBounds(50, 300, 100, 30);
        jrmasculino.setBounds(230, 300, 100, 30);
        jrfeminino.setBounds(400, 300, 100, 30);
        add(jlgenero);
        add(jrmasculino);
        add(jrfeminino);
        bggenero.add(jrmasculino);
        bggenero.add(jrfeminino);
        
        jlnacionalidade.setBounds(50, 350, 100, 30);
        jtnacionalidade.setBounds(200, 350, 200, 30);
        add(jlnacionalidade);
        add(jtnacionalidade);
        
        jbregistar.setBounds(390, 450, 200, 50);
        add(jbregistar);
        
        
        
        setTitle("Processo do preso");
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

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento() {
        String data= String.valueOf(jcdia.getSelectedItem())+"/"+ String.valueOf(jcmes.getSelectedItem())+"/"+String.valueOf(jcano.getSelectedItem());
        this.dataDeNascimento = data;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto() {
        this.contacto = jtcontacto.getText();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco() {
        this.endereco = jtendereco.getText();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero() {
        if(this.jrfeminino.isSelected()){
            this.genero = "Feminino";
        }else{
            this.genero = "Masculino";
        }
        
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade() {
        this.nacionalidade = jtnacionalidade.getText();
    }
     
     //gravar processo
     public void gravarProcesso(){
         
         
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sql= "INSERT INTO processo(nome, id, nascimento, contacto, endereco, genero, nacionalidade) "
                    + "values( ?, ?, ?, ?, ?, ?, ?)";
            
            try{
                Connection conexao = DriverManager.getConnection(url, "root", "vertrigo");
                PreparedStatement actualizar = conexao.prepareStatement (sql);
                
                actualizar.setString(1, getNome());
                actualizar.setString(2, getId());
                actualizar.setString(3, getDataDeNascimento());
                actualizar.setString(4, getContacto());
                actualizar.setString(5, getEndereco());
                actualizar.setString(6, getGenero());
                actualizar.setString(7, getNacionalidade());
                
                actualizar.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Gravado com sucesso");
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
            
     }
     
     
}
