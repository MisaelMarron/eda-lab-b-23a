import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
public class program extends JFrame implements ActionListener{
	
    private JMenuBar menubar;
    private JMenuItem menuItem1,menuItem2,menuItem3;
    private JTextPane TextArea1;
    private JScrollPane scrollPane1;
    private Tries trie = new Tries();
    public program(){
        setLayout(null);
        menubar= new JMenuBar();
        setJMenuBar(menubar);

        menuItem2 = new JMenuItem ("BUSCAR");
        menuItem2.addActionListener(this);
        menubar.add(menuItem2);
        menuItem3 = new JMenuItem ("REMPLAZAR");
        menuItem3.addActionListener(this);
        menubar.add(menuItem3);
        TextArea1= new JTextPane();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, Color.BLACK); 
        StyleConstants.setFontFamily(attr, "Arial"); 

        scrollPane1= new JScrollPane(TextArea1);
        scrollPane1.setBounds(0,0,370,320);
        add(scrollPane1);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1) {
            System.exit(0);
        }
        if (e.getSource() == menuItem2) {
            trie.clear();
            String Texto = TextArea1.getText();
            insertSentence(trie, Texto);
            String busqueda = JOptionPane.showInputDialog(null, "Ingrese un valor de búsqueda:");
            if (busqueda != null) {
            JOptionPane.showMessageDialog(null, "Valor de búsqueda ingresado: " + busqueda);
            TextArea1.setText("");
            trie.printString2( busqueda, TextArea1);
            } else {
            JOptionPane.showMessageDialog(null, "Búsqueda cancelada");
            }
        } 
        if (e.getSource() == menuItem3) {
            trie.clear();
            String Texto = TextArea1.getText();
            insertSentence(trie, Texto);
            String busqueda = JOptionPane.showInputDialog(null, "Ingrese lo que quiere reemplazar:");
            if (busqueda != null) {
            JOptionPane.showMessageDialog(null, "Valor Ingresar: " + busqueda);
            String remplaza = JOptionPane.showInputDialog(null, "Ingrese dato nuevo:");
            if (!trie.startsWith(busqueda)){
                JOptionPane.showMessageDialog(null, "Lo ingresado:  "+busqueda+" no existe");
            }
            else if (remplaza != null) {
                TextArea1.setText("");
                trie.replace(busqueda, remplaza);
                JOptionPane.showMessageDialog(null, "Se cambio el valor: " + busqueda+" con "+remplaza);
                trie.printString2(remplaza, TextArea1);
            }else{
                JOptionPane.showMessageDialog(null, "Cancel");
            }

            
            } else {
            JOptionPane.showMessageDialog(null, "CANCEL");
            }
        } 
    }
    public static void main(String[] args) {
        program buscador1 = new program();
        buscador1.setResizable(true);
        buscador1.setBounds(0,0,382,200);
        buscador1.setTitle("PROGRAM");
        buscador1.setVisible(true);
        buscador1.setLocationRelativeTo(null);
        buscador1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static String[] separaStrings (String text){
        return text.split(" ");
    }
    public static void insertSentence(Tries trie, String Text){
        String[] words = separaStrings(Text);
        for (String word : words) {
            trie.insert(word);
        }
    }
}