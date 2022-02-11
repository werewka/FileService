import javax.swing.*;

public class Task4 {

    public static void main(String[] args) {

        FileService myfile = new FileService();
        myfile.connection(args);

        JOptionPane.showMessageDialog(null, myfile.fileoutput(), "Current file data", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, myfile.countering(), "Reiteration of - " + myfile.requiredString, JOptionPane.PLAIN_MESSAGE);
        myfile.replaceLines();
        JOptionPane.showMessageDialog(null, myfile.fileoutput(), "Current file data", JOptionPane.PLAIN_MESSAGE);
    }
}