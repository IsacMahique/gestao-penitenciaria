package gestaopenitenciaria;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class ListarMedico extends JFrame{

    DefaultTableModel modelo= new DefaultTableModel();
    JTable tabela = new JTable(modelo);
    JPanel painelFundo;
    JScrollPane barraRolagem;
    
    public ListarMedico(){ 
        
        modelo.addColumn("Nome");
        modelo.addColumn("ID");
        modelo.addColumn("Nome Da Doenca");
        modelo.addColumn("Cuidados");
        
        try{
            String url = "jdbc:mysql://localhost/arquivopenitenciaria";
            Connection conexao= DriverManager.getConnection(url, "root", "vertrigo");
            PreparedStatement pesquisa= conexao.prepareStatement("SELECT * FROM historicomedico");
            ResultSet resultado = pesquisa.executeQuery();
            while(resultado.next()){
                String nome= resultado.getString("nome");
                String id= resultado.getString("id");
                String doenca= resultado.getString("nomedadoenca");
                String cuidados= resultado.getString("cuidados");
                
                modelo.addRow(new Object[]{nome, id, doenca, cuidados});
                
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
        setTitle("Historico Medico");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }
    
}

