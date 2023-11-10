package gestaopenitenciaria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Gestaopenitenciaria extends JFrame  implements ActionListener{
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==jblogin){
            if (jtusername.getText().equals("dmi") && jpsenha.getText().equals("dmiadmin")){
                this.dispose();
                new Administrador();
            }else if (jtusername.getText().equals("dmi") && jpsenha.getText().equals("dmistaff")){
                new Funcionario();
            }else{
                JOptionPane.showMessageDialog(null, "Sem registo! \n Verifique se os dados estao correctos.");
            }
        }
        
    }
    
    Font fGrande= new Font("MONOSPACED", Font.BOLD,30);
    Font fMedia= new Font("Calibre", Font.BOLD,20);
    Font fNormal= new Font("Serif", Font.ROMAN_BASELINE,20);
    //login
    JLabel jlusername= new JLabel("Username");
    JTextField jtusername= new JTextField(200);
    JLabel jlsenha= new JLabel("Senha");
    JPasswordField jpsenha= new JPasswordField(200);
    JButton jblogin = new JButton("L o g i n ");
    
    
    public Gestaopenitenciaria(){
        setLayout(null);
        jblogin.addActionListener(this);
        
        jlusername.setFont(fMedia);
        jlsenha.setFont(fMedia);
        jtusername.setFont(fNormal);
        jpsenha.setFont(fNormal);
        
        //login
        jlusername.setBounds(150, 150, 200, 40);
        jlusername.setForeground(Color.white);
        jlsenha.setForeground(Color.white);
        jblogin.setForeground(Color.black);
        jtusername.setBounds(300, 150, 400, 40);
        jlsenha.setBounds(150, 250, 200, 40);
        jpsenha.setBounds(300, 250, 400, 40);
        jblogin.setBounds(380, 350, 200, 40);
        jblogin.setBackground(Color.green);
        add(jlusername);
        add(jtusername);
        add(jlsenha);
        add(jpsenha);
        add(jblogin);
        
        
        
        setTitle("Gestao Penitenciaria");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    public static void main(String[] args){
     new Gestaopenitenciaria();
        
    }
    
}
