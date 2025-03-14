package main;  

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  

public class GamePanel extends JPanel implements MouseListener {  
    private Game game;  
    private GameBoard board;  
    private Tile selectedTile;  
    private int score;  
    
    // 游戏常量  
    private static final int BOARD_SIZE = 8; // 8x8的棋盘  
    private static final int TILE_SIZE = 60; // 60像素的方块  
    private static final int BOARD_OFFSET_X = 60; // 棋盘X偏移  
    private static final int BOARD_OFFSET_Y = 100; // 棋盘Y偏移  
    
    public GamePanel(Game game) {  
        this.game = game;  
        this.score = 0;  
        
        // 初始化游戏棋盘  
        board = new GameBoard(BOARD_SIZE, BOARD_SIZE, TILE_SIZE);  
        
        // 设置面板属性  
        setPreferredSize(new Dimension(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT));  
        setBackground(new Color(50, 50, 50));  
        
        // 添加鼠标监听器  
        addMouseListener(this);  
    }  
    
    @Override  
    protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D) g;  
        
        // 启用抗锯齿  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
        
        // 绘制游戏标题  
        g2.setColor(Color.WHITE);  
        g2.setFont(new Font("Arial", Font.BOLD, 30));  
        g2.drawString("Match Three Game", 160, 50);  
        
        // 绘制分数  
        g2.setFont(new Font("Arial", Font.PLAIN, 20));  
        g2.drawString("Score: " + score, 240, 80);  
        
        // 绘制棋盘背景  
        g2.setColor(new Color(30, 30, 30));  
        g2.fillRect(BOARD_OFFSET_X - 5, BOARD_OFFSET_Y - 5,   
                   BOARD_SIZE * TILE_SIZE + 10, BOARD_SIZE * TILE_SIZE + 10);  
        
        // 绘制所有方块  
        g2.translate(BOARD_OFFSET_X, BOARD_OFFSET_Y); // 移动坐标系  
        
        for (int row = 0; row < board.getRows(); row++) {  
            for (int col = 0; col < board.getCols(); col++) {  
                Tile tile = board.getTile(row, col);  
                if (tile != null) {  
                    tile.draw(g2);  
                }  
            }  
        }  
    }  
    
    @Override  
    public void mouseClicked(MouseEvent e) {  
        // 转换鼠标坐标到棋盘坐标  
        int mouseX = e.getX() - BOARD_OFFSET_X;  
        int mouseY = e.getY() - BOARD_OFFSET_Y;  
        
        // 检查点击是否在棋盘范围内  
        if (mouseX < 0 || mouseY < 0 ||   
            mouseX >= BOARD_SIZE * TILE_SIZE ||   
            mouseY >= BOARD_SIZE * TILE_SIZE) {  
            return;  
        }  
        
        // 计算点击的方块位置  
        int col = mouseX / TILE_SIZE;  
        int row = mouseY / TILE_SIZE;  
        
        Tile clickedTile = board.getTile(row, col);  
        
        if (selectedTile == null) {  
            // 第一次选择方块  
            selectedTile = clickedTile;  
            selectedTile.setSelected(true);  
        } else {  
            // 第二次选择方块  
            // 检查两个方块是否相邻  
            if (areAdjacent(selectedTile, clickedTile)) {  
                // 交换方块  
                board.swapTiles(selectedTile, clickedTile);  
                
                // 检查是否有匹配  
                if (board.checkForMatches()) {  
                    // 有匹配，增加分数  
                    score += 100;  
                    // 处理匹配和下落  
                    handleMatches();  
                } else {  
                    // 没有匹配，交换回来  
                    board.swapTiles(selectedTile, clickedTile);  
                }  
            }  
            
            // 无论是否交换成功，都取消选择状态  
            selectedTile.setSelected(false);  
            selectedTile = null;  
        }  
        
        // 重绘面板  
        repaint();  
    }  
    
    private boolean areAdjacent(Tile tile1, Tile tile2) {  
        // 检查两个方块是否相邻  
        int rowDiff = Math.abs(tile1.getY() - tile2.getY());  
        int colDiff = Math.abs(tile1.getX() - tile2.getX());  
        
        // 相邻意味着(行相同且列相差1)或(列相同且行相差1)  
        return (rowDiff == 0 && colDiff == 1) || (colDiff == 0 && rowDiff == 1);  
    }  
    
    private void handleMatches() {  
        // 处理匹配和下落新方块的逻辑  
        // 这部分会在GameBoard中实现  
        board.removeMatches();  
        board.fillEmptySpaces();  
        
        // 检查是否有新的匹配形成  
        if (board.checkForMatches()) {  
            score += 50; // 连锁反应额外加分  
            handleMatches(); // 递归处理新的匹配  
        }  
    }  
    
    // 实现剩余的MouseListener接口方法  
    @Override  
    public void mousePressed(MouseEvent e) {}  
    
    @Override  
    public void mouseReleased(MouseEvent e) {}  
    
    @Override  
    public void mouseEntered(MouseEvent e) {}  
    
    @Override  
    public void mouseExited(MouseEvent e) {}  
}  