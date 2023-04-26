import java.awt.event.ActionListener;

public class Masukan implements ActionListener{

    boolean textClick = false;

    JButton

    public void drawString(MouseEvent e) throws IOException {
        if(textClick) { 
            int xLoc = e.getX();
            int yLoc = e.getY();
            String prompt = "Enter your Account Number:";
            String input = JOptionPane.showInputDialog(someComponent, prompt);
    
            myJLabel.setText(input); 
    
            textClick=false;
        }
    }

    public void actionPerformed(ActionEvent e){
        textClick = true;
    }
}
