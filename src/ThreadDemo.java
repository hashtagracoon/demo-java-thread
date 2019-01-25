import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
 
public class ThreadDemo extends JFrame {
	
	private int getRandomSleepTime() {
		return new Random().nextInt(250) + 50;
	}
	
	// thread pool
	private Thread thread1;
	private Thread thread2;
	private Thread thread3;
	
	private boolean flag = false;
	
    public ThreadDemo() {
    	
    	int totalLength = 900;

        JButton btn = new JButton("Start a New Game");
        JLabel label1 = new JLabel("Thread1: 0/" + totalLength);
        JLabel label2 = new JLabel("Thread2: 0/" + totalLength);
        JLabel label3 = new JLabel("Thread3: 0/" + totalLength);
        JLabel resultLabel = new JLabel("");
        
        btn.addActionListener(new ActionListener() { 
        	
            public void actionPerformed(ActionEvent e) {
            	
            	System.out.println("Start a New Game");
            	flag = false;
            	resultLabel.setText("");
            	
            	if(thread1 != null && thread2 != null && thread3 != null) {
            		// force stop, dangerous
            		thread1.stop();
            		thread2.stop();
            		thread3.stop();
            	}

            	repaint();
            	
                thread1 = new Thread(new Runnable() {
                    public void run() {
                        Graphics g = getGraphics(); 

                        for(int i = 0; i <= totalLength; i+=10) { 
                            try { 
                                Thread.sleep(getRandomSleepTime()); 
                                g.drawOval(i, 200, 10, 10); 
                                label1.setText("Thread1: " + i + "/" + totalLength);
                                if(i == totalLength && flag == false) {
                                	resultLabel.setText("*** WINNER is Thread1 ***");
                                	flag = true;
                                }
                            } 
                            catch(InterruptedException e) { 
                                e.printStackTrace(); 
                            } 
                        }
                    }
                });
                
                thread2 = new Thread(new Runnable() {
                    public void run() {
                        Graphics g = getGraphics(); 

                        for(int i = 0; i <= totalLength; i+=10) { 
                            try { 
                                Thread.sleep(getRandomSleepTime()); 
                                g.drawOval(i, 400, 15, 15); 
                                label2.setText("Thread2: " + i + "/" + totalLength);
                                if(i == totalLength && flag == false) {
                                	resultLabel.setText("*** WINNER is Thread2 ***");
                                	flag = true;
                                }
                            } 
                            catch(InterruptedException e) { 
                                e.printStackTrace(); 
                            } 
                        }
                    }
                });
                
                thread3 = new Thread(new Runnable() {
                    public void run() {
                        Graphics g = getGraphics(); 

                        for(int i = 0; i <= totalLength; i+=10) { 
                            try { 
                                Thread.sleep(getRandomSleepTime()); 
                                g.drawOval(i, 600, 20, 20); 
                                label3.setText("Thread3: " + i + "/" + totalLength);
                                if(i == totalLength && flag == false) {
                                	resultLabel.setText("*** WINNER is Thread3 ***");
                                	flag = true;
                                }
                            } 
                            catch(InterruptedException e) { 
                                e.printStackTrace(); 
                            } 
                        }
                    }
                });
                
                thread1.start();
                thread2.start();
                thread3.start();
            } 
        }); 
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout()); 
        c.add(btn); 
        c.add(label1); 
        c.add(label2); 
        c.add(label3);
        c.add(resultLabel);

        setDefaultCloseOperation(
                   WindowConstants.EXIT_ON_CLOSE); 
        setSize(totalLength, 1000);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new ThreadDemo();
    }
}