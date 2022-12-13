import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Form {

    private JPanel jPanel;
    private JPanel mainPanel;

    private JTextArea textArea1;


    public Form() {
        textArea1.setText(JframeBox.jlabel);
        var ex = Executors.newScheduledThreadPool(1);
        ex.scheduleAtFixedRate((new Runnable() {
            @Override
            public void run() {
                textArea1.setText(JframeBox.jlabel);
            }
        }), 1, 10, TimeUnit.SECONDS);



        }


    public JPanel getjPanel(){
          return mainPanel;
      }




}
