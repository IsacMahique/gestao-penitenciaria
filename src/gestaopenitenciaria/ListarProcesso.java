package gestaopenitenciaria;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarProcesso extends JFrame implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==jbeliminar){ 
            eliminacao();
            this.dispose();
            new ListarProcesso();
            //JOptionPane.showMessageDialog(null, "O botao eliminar foi clicado");
        }
        
    }

    DefaultTableModel modelo= new DefaultTableModel();
    JTable tabela = new JTable(modelo);
    JPanel painelFundo;
    JScrollPane barraRolagem;
    JButton jbeliminar= new JButton("Eliminar");
    Font fMedia= new Font("Calibre", Font.BOLD,20);
    
    public ListarProcesso(){ 
        jbeliminar.addActionListener(this);
        jbeliminar.setFont(fMedia);
        
        
        modelo.addColumn("Nome");
        modelo.addColumn("ID");
        modelo.addColumn("Data De Nascimento");
        modelo.addColumn("Contacto");
        modelo.addColumn("Endereco");
        modelo.addColumn("Genero");
        modelo.addColumn("Nacionalidade");
        
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
                
                modelo.addRow(new Object[]{nome, id, dataDeNascimento, contacto, endereco, genero, nacionalidade});
                
            }
            
         }catch(Exception erro){
             System.out.println("Erro \n "+ erro);
         }
        //jjjj
        
        //altura coluna tabela
        tabela.setRowHeight(40);
        
        //botao eliminar
        jbeliminar.setBounds(740, 490, 220, 60);
        jbeliminar.setBackground(Color.WHITE);
        add(jbeliminar);

            
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
       
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);

        getContentPane().add(painelFundo);

        
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
        setTitle("Processos");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }
    
    //Eliminar processo e tudo
    public void eliminacao(){
        String idEliminar= JOptionPane.showInputDialog(null, "Digite um ID valido para eliminar");
         
        String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sql= "DELETE FROM processo WHERE id= '"+idEliminar+"' ";
            
            try{
                Connection conexao = DriverManager.getConnection(url, "root", "vertrigo");
                PreparedStatement actualizar = conexao.prepareStatement (sql);
                
                actualizar.executeUpdate();
                 
                 //apagar antecedentes Criminais
                 String urlAnt = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sqlAnt= "DELETE FROM crimes WHERE id= '"+idEliminar+"' ";
            
            try{
                Connection conexaoAnt = DriverManager.getConnection(urlAnt, "root", "vertrigo");
                PreparedStatement actualizarAnt = conexaoAnt.prepareStatement (sqlAnt);
                
                actualizarAnt.executeUpdate();
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
                 
                 //eliminar historico medico
                 
                 String urlH = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sqlH= "DELETE FROM historicomedico WHERE id= '"+idEliminar+"' ";
            
            try{
                Connection conexaoH = DriverManager.getConnection(urlH, "root", "vertrigo");
                PreparedStatement actualizarH = conexaoH.prepareStatement (sqlH);
                
                actualizarH.executeUpdate();
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
                 
                 //Eliminar visitas
                 String urlV = "jdbc:mysql://localhost/arquivopenitenciaria";
            String sqlV= "DELETE FROM visita WHERE id= '"+idEliminar+"' ";
            
            try{
                Connection conexaoV = DriverManager.getConnection(urlV, "root", "vertrigo");
                PreparedStatement actualizarV = conexaoV.prepareStatement (sqlV);
                
                actualizarV.executeUpdate();
                 JOptionPane.showMessageDialog(null, "O processo foi ELIMINADO "
                         + "com o respectivo: \n Antecedente Criminal, Historico Medico e Visitas \n ");
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
                 //fim eliminar visitas
                 
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro \n" + erro);
            }
        
    }
    
}
