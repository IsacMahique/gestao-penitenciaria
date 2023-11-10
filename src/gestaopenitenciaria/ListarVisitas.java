package gestaopenitenciaria;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarVisitas extends JFrame{

    DefaultTableModel modelo= new DefaultTableModel();
    JPanel painelFundo;
    JTable tabela = new JTable(modelo);
    JScrollPane barraRolagem;
    
    
    public ListarVisitas(){
        
        modelo.addColumn("Nome");
        modelo.addColumn("ID");
        modelo.addColumn("Visitas");
        modelo.addColumn("Outras Visitas");
        
        
        try{
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            Connection conexao= DriverManager.getConnection(url, "root", "vertrigo");
            PreparedStatement pesquisa= conexao.prepareStatement("SELECT * FROM visita");
            ResultSet resultado = pesquisa.executeQuery();
            while(resultado.next()){
                String nome= resultado.getString("nome");
                String id= resultado.getString("id");
                String visitas= resultado.getString("visitas");
                String outrasVisitas= resultado.getString("outrasVisitas");
                
                modelo.addRow(new Object[]{nome, id, visitas, outrasVisitas});
                
            }
            
         }catch(Exception erro){
             System.out.println("Erro \n "+ erro);
         }
        
        //altura coluna tabela
        tabela.setRowHeight(40);
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        
        
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
        setTitle("Gestao Penitenciaria");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
