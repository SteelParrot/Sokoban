package game.componenets;

import javax.swing.*;
import java.awt.*;

public class Timer extends JPanel implements Runnable{

    private JLabel label;
    private JLabel timeLabel;
    private Thread timerThread;

    private int timerTime;
    private boolean runOutOfTime = false;
    private boolean infiniteTime = false;
    private boolean stopTimer = false;

    public Timer() {
        initialize();
    }

    public void initialize(){
        setBounds(700, 50, 150, 100);
        setFocusable(true);
        setLayout(null);

        label = new JLabel("REMAINING TIME:");
        label.setBounds(0,0,150,50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(new Color(227, 254, 247));
        label.setBackground(new Color(19, 93, 102));
        label.setOpaque(true);
        //label.setVisible(true);
        //label.setFont(new Font("Monospaced", Font.PLAIN, 15));

        timeLabel = new JLabel("0");
        timeLabel.setBounds(0,50,150,50);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(new Color(227, 254, 247));
        timeLabel.setBackground(new Color(19, 93, 102));
        timeLabel.setOpaque(true);
        //timeLabel.setVisible(true);

        add(label);
        add(timeLabel);
        setVisible(false);
    }

    public boolean runOutOfTime(){
        if (infiniteTime){
            return false;
        }else {
            return runOutOfTime;
        }
    }

    public void startNewTimer(int time, boolean infiniteTime) {
        reset();
        this.timerTime = time;
        if (!infiniteTime){
            this.timerThread.start();
        }else {
            timeLabel.setText("infinite");
        }
    }

    private void reset(){
        this.runOutOfTime = false;
        this.infiniteTime = false;
        if (this.timerThread != null){
            System.out.println("INTERRUPTING");
            this.timerThread.interrupt();
        }

        this.timerThread = new Thread(this);
    }

    @Override
    public void run() {
        while (timerTime > 0 && !infiniteTime){
            try {
                timerTime--;
                timeLabel.setText(String.valueOf(timerTime));
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                return;
            }
        }
        runOutOfTime = true;
    }
}
