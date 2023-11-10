package gestaopenitenciaria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Funcionario extends JFrame  implements ActionListener{
    
    Font fGrande= new Font("MONOSPACED", Font.BOLD,30);
    Font fMedia= new Font("Calibre", Font.BOLD,20);
    
    JLabel jlconsultas= new JLabel("CONSULTA");
    JButton jbconsultarProcesso = new JButton("Consultar Processo");
    JButton jbconsultarAntecedentesCriminais = new JButton("Consultar Antecedentes Criminais");
    JButton jbconsultarHistoricoMedico = new JButton("Consultar Historico Medico");
    JButton jbconsultarvisitas = new JButton("Consultar Visitas");
    JButton jbsair = new JButton("S a i r ");
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jbconsultarProcesso){
            new ListarProcesso();
        }
        if(e.getSource()==jbconsultarAntecedentesCriminais){
            new ListarAntecedentes();
        }
        if(e.getSource()==jbconsultarHistoricoMedico){
             new ListarMedico();
        }
        if(e.getSource()==jbconsultarvisitas){
           new ListarVisitas();
        }
        if(e.getSource()==jbsair){
            System.exit(0);
        }
    }
    
    public Funcionario(){
        setLayout(null);
        jbsair.addActionListener(this);
        
        jbconsultarProcesso.addActionListener(this);
        jbconsultarAntecedentesCriminais.addActionListener(this);
        jbconsultarHistoricoMedico.addActionListener(this);
        jbconsultarvisitas.addActionListener(this);
        jbsair.addActionListener(this);
        
        
        jlconsultas.setFont(fGrande);
        jbconsultarProcesso.setFont(fMedia);
        jbconsultarAntecedentesCriminais.setFont(fMedia);
        jbconsultarHistoricoMedico.setFont(fMedia);
        jbconsultarvisitas.setFont(fMedia);
        jbsair.setFont(fMedia);

        
        jlconsultas.setBounds(400, 50, 200, 40);
        jbconsultarProcesso.setBounds(300, 150,380, 40);
        jbconsultarAntecedentesCriminais.setBounds(300, 220, 380, 40);
        jbconsultarHistoricoMedico.setBounds(300, 290, 380, 40);
        jbconsultarvisitas.setBounds(300, 360, 380, 40);
        jlconsultas.setForeground(Color.GREEN);
        add(jlconsultas);
        add(jbconsultarProcesso);
        add(jbconsultarAntecedentesCriminais);
        add(jbconsultarHistoricoMedico);
        add(jbconsultarvisitas);
        
        jbsair.setBounds(750, 500, 130, 40);
        jbsair.setBackground(Color.red);
        jbsair.setForeground(Color.ORANGE);
        add(jbsair);
        
        
        setTitle("Gestao Penitenciaria");
        setSize(1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }
    
}
