package main;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  

public class Game implements ActionListener {  
    private JFrame window;  
    private GamePanel gamePanel;  
    private Timer gameTimer;  
    
    public static final int WINDOW_WIDTH = 600;  
    public static final int WINDOW_HEIGHT = 650;  
    private static final int FPS = 60;  
    
    public Game() {  
        // 创建游戏窗口  
        window = new JFrame("Match Three Game");  
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        window.setResizable(false);  
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  
        
        // 创建游戏面板  
        gamePanel = new GamePanel(this);  
        window.add(gamePanel);  
        
        // 设置游戏计时器  
        gameTimer = new Timer(1000 / FPS, this);  
        gameTimer.start();  
        
        // 居中显示窗口  
        window.setLocationRelativeTo(null);  
        window.setVisible(true);  
    }  
    
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // 游戏循环 - 刷新屏幕  
        gamePanel.repaint();  
    }  
    
    public static void main(String[] args) {  
        // 使用Swing的事件分发线程创建游戏  
        SwingUtilities.invokeLater(() -> {  
            new Game();  
        });  
    }  
}  