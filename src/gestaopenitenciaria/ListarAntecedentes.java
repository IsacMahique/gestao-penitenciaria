package gestaopenitenciaria;

import java.awt.Color;
import java.awt.GridLayout;
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

public class ListarAntecedentes extends JFrame{

    DefaultTableModel modelo= new DefaultTableModel();
    JTable tabela = new JTable(modelo);
    JPanel painelFundo;
    JScrollPane barraRolagem;
    
    public ListarAntecedentes(){ 
        
        modelo.addColumn("Nome");
        modelo.addColumn("ID");
        modelo.addColumn("crimes");
        
        try{
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            Connection conexao= DriverManager.getConnection(url, "root", "vertrigo");
            PreparedStatement pesquisa= conexao.prepareStatement("SELECT * FROM crimes");
            ResultSet resultado = pesquisa.executeQuery();
            while(resultado.next()){
                String nome= resultado.getString("nome");
                String id= resultado.getString("id");
                String crimes= resultado.getString("crimes");
                
                modelo.addRow(new Object[]{nome, id, crimes});
                
            }
            
         }catch(Exception erro){
             System.out.println("Erro \n "+ erro);
         }
        //jjjj
        
        //altura coluna tabela
        tabela.setRowHeight(40);

            
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
        setTitle("Antecedentes Criminais");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }
    
}
