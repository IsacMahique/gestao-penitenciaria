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
public class Administrador extends JFrame implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==jbsair){
            System.exit(0);
        }
        if(e.getSource()==jbcriarProcesso){
            this.dispose();
            new Processo();
        }
        if(e.getSource()==jbcriarAntecedentesCriminais){
            this.dispose();
            new AntecedentesCriminais();
        }
        if(e.getSource()==jbcriarHistoricoMedico){
            this.dispose();
            new HistoricoMedico();
        }
        if(e.getSource()==jbcriarvisitas){
            this.dispose();
            new Visitas();
        }
        //consultas
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
    
    Font fGrande= new Font("MONOSPACED", Font.BOLD,30);
    Font fMedia= new Font("Calibre", Font.BOLD,20);
    
    JLabel jlregistar= new JLabel("REGISTAR");
    JButton jbcriarProcesso = new JButton("Criar Processo");
    JButton jbcriarAntecedentesCriminais = new JButton("Registar Antecedentes Criminais");
    JButton jbcriarHistoricoMedico = new JButton("Registar Historico Medico");
    JButton jbcriarvisitas = new JButton("Registar Visitas");
    //menu direito
    JLabel jlconsultas= new JLabel("CONSULTA");
    JButton jbconsultarProcesso = new JButton("Consultar Processo");
    JButton jbconsultarAntecedentesCriminais = new JButton("Consultar Antecedentes Criminais");
    JButton jbconsultarHistoricoMedico = new JButton("Consultar Historico Medico");
    JButton jbconsultarvisitas = new JButton("Consultar Visitas");
    
    JButton jbsair = new JButton("S a i r ");
    
    public Administrador(){
        setLayout(null);
        jbsair.addActionListener(this);
        jbcriarProcesso.addActionListener(this);
        jbcriarAntecedentesCriminais.addActionListener(this);
        jbcriarHistoricoMedico.addActionListener(this);
        jbcriarvisitas.addActionListener(this);
        jbconsultarProcesso.addActionListener(this);
        jbconsultarAntecedentesCriminais.addActionListener(this);
        jbconsultarHistoricoMedico.addActionListener(this);
        jbconsultarvisitas.addActionListener(this);
        
        jbconsultarAntecedentesCriminais.addActionListener(this);
        jbconsultarHistoricoMedico.addActionListener(this);
       jbconsultarvisitas.addActionListener(this);
                
        jlregistar.setFont(fGrande);
        jbcriarProcesso.setFont(fMedia);
        jbcriarAntecedentesCriminais.setFont(fMedia);
        jbcriarHistoricoMedico.setFont(fMedia);
        jbcriarvisitas.setFont(fMedia);
        jlconsultas.setFont(fGrande);
        jbconsultarProcesso.setFont(fMedia);
        jbconsultarAntecedentesCriminais.setFont(fMedia);
        jbconsultarHistoricoMedico.setFont(fMedia);
        jbconsultarvisitas.setFont(fMedia);
        jbsair.setFont(fMedia);

        
        jlregistar.setBounds(130, 50, 200, 40);
        jbcriarProcesso.setBounds(50, 150, 350, 40);
        jbcriarAntecedentesCriminais.setBounds(50, 220, 350, 40);
        jbcriarHistoricoMedico.setBounds(50, 290, 350, 40);
        jbcriarvisitas.setBounds(50, 360, 350, 40);
        jlregistar.setForeground(Color.GREEN);
        add(jlregistar);
        add(jbcriarProcesso);
        add(jbcriarAntecedentesCriminais);
        add(jbcriarHistoricoMedico);
        add(jbcriarvisitas);
        
        jlconsultas.setBounds(650, 50, 200, 40);
        jbconsultarProcesso.setBounds(550, 150,380, 40);
        jbconsultarAntecedentesCriminais.setBounds(550, 220, 380, 40);
        jbconsultarHistoricoMedico.setBounds(550, 290, 380, 40);
        jbconsultarvisitas.setBounds(550, 360, 380, 40);
        jlconsultas.setForeground(Color.GREEN);
        add(jlconsultas);
        add(jbconsultarProcesso);
        add(jbconsultarAntecedentesCriminais);
        add(jbconsultarHistoricoMedico);
        add(jbconsultarvisitas);
        
        jbsair.setBounds(550, 430, 100, 50);
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
   
