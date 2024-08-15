package in;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener{
    
    // making the frame
    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();

    int elaspsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    boolean started = false;

    // foramte of the strings to store the time
    String second_String = String.format("%02d", seconds);
    String minutes_String = String.format("%02d", minutes);
    String hours_String = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            elaspsedTime += 1000;
            hours = (elaspsedTime / 3600000);   // 3600000 --> total milliseconds in 1 hour
            minutes = (elaspsedTime / 60000) % 60;   // 60000   --> total milliseconds in 1 minutes
            seconds = (elaspsedTime / 1000) % 60;    // 1000    --> total milliseconds in 1 seconds
            second_String = String.format("%02d", seconds);
            minutes_String = String.format("%02d", minutes);
            hours_String = String.format("%02d", hours);
            timeLabel.setText(hours_String + ":" + minutes_String + ":" + second_String );
        }
    });
    
    
    
    Stopwatch() {
        
        // adding the time box
        timeLabel.setText(hours_String + ":" + minutes_String + ":" + second_String);
        timeLabel.setBackground(Color.white);
        timeLabel.setBounds(300, 200, 600, 400);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 95));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);


        // making the startButton
        startButton.setBounds(300, 650, 290, 65);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 40));
        startButton.setForeground(Color.DARK_GRAY);
        startButton.setBackground(Color.decode("#0D98D1"));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        // making the reset 
        resetButton.setBounds(610, 650, 290, 65);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 40));
        resetButton.setForeground(Color.DARK_GRAY);
        resetButton.setBackground(Color.decode("#0D98D1"));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);


        // adding the different components
        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(resetButton);


        frame.setTitle("StopWatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 880);
        frame.setLocation(380, 200);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setVisible(true); // try to make this last line 
        
    }

    
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == startButton) {
                if(started == false) {
                    started = true;
                    startButton.setText("Stop");
                    start();
                } else {
                    started = false;
                    startButton.setText("Start");
                    stop();
                }
            }

            if(e.getSource() == resetButton) {
                started = false;
                startButton.setText("Start");
                reset();
            }
            
        }

        public void start() {
            timer.start();
        }

        public void stop() {
            timer.stop();
        }

        public void reset() {
            timer.stop();
            elaspsedTime = 0;
            seconds = 0;
            minutes = 0;
            hours = 0;
            second_String = String.format("%02d", seconds);
            minutes_String = String.format("%02d", minutes);
            hours_String = String.format("%02d", hours);
            timeLabel.setText(hours_String + ":" + minutes_String + ":" + second_String);
        }

}
