package main;  

import java.util.Random;  

public class GameBoard {  
    private Tile[][] board;  
    private int rows;  
    private int cols;  
    private int tileSize;  
    private Random random;  
    
    // 用于标记匹配的方块  
    private boolean[][] matchedTiles;  
    
    public GameBoard(int rows, int cols, int tileSize) {  
        this.rows = rows;  
        this.cols = cols;  
        this.tileSize = tileSize;  
        this.board = new Tile[rows][cols];  
        this.matchedTiles = new boolean[rows][cols];  
        this.random = new Random();  
        
        initializeBoard();  
    }  
    
    private void initializeBoard() {  
        // 初始化棋盘，确保没有初始匹配  
        for (int row = 0; row < rows; row++) {  
            for (int col = 0; col < cols; col++) {  
                int type;  
                do {  
                    type = random.nextInt(6) + 1; // 1-6的随机数  
                    board[row][col] = new Tile(type, col, row, tileSize);  
                } while (hasInitialMatch(row, col));  
            }  
        }  
    }  
    
    private boolean hasInitialMatch(int row, int col) {  
        // 检查是否有初始的三连  
        if (col >= 2) {  
            if (board[row][col-1] != null && board[row][col-2] != null &&  
                board[row][col-1].getType() == board[row][col].getType() &&   
                board[row][col-2].getType() == board[row][col].getType()) {  
                return true;  
            }  
        }  
        
        if (row >= 2) {  
            if (board[row-1][col] != null && board[row-2][col] != null &&  
                board[row-1][col].getType() == board[row][col].getType() &&   
                board[row-2][col].getType() == board[row][col].getType()) {  
                return true;  
            }  
        }  
        
        return false;  
    }  
    
    public Tile getTile(int row, int col) {  
        if (row >= 0 && row < rows && col >= 0 && col < cols) {  
            return board[row][col];  
        }  
        return null;  
    }  
    
    public void swapTiles(Tile tile1, Tile tile2) {  
        // 交换两个方块的位置  
        int tempX = tile1.getX();  
        int tempY = tile1.getY();  
        
        tile1.setX(tile2.getX());  
        tile1.setY(tile2.getY());  
        
        tile2.setX(tempX);  
        tile2.setY(tempY);  
        
        // 更新棋盘数组  
        board[tile1.getY()][tile1.getX()] = tile1;  
        board[tile2.getY()][tile2.getX()] = tile2;  
    }  
    
    public boolean checkForMatches() {  
        // 重置匹配标记  
        matchedTiles = new boolean[rows][cols];  
        boolean foundMatch = false;  
        
        // 检查水平匹配  
        for (int row = 0; row < rows; row++) {  
            for (int col = 0; col < cols - 2; col++) {  
                if (board[row][col] != null && board[row][col+1] != null && board[row][col+2] != null &&  
                    board[row][col].getType() == board[row][col+1].getType() &&   
                    board[row][col].getType() == board[row][col+2].getType()) {  
                    
                    matchedTiles[row][col] = true;  
                    matchedTiles[row][col+1] = true;  
                    matchedTiles[row][col+2] = true;  
                    foundMatch = true;  
                }  
            }  
        }  
        
        // 检查垂直匹配  
        for (int col = 0; col < cols; col++) {  
            for (int row = 0; row < rows - 2; row++) {  
                if (board[row][col] != null && board[row+1][col] != null && board[row+2][col] != null &&  
                    board[row][col].getType() == board[row+1][col].getType() &&   
                    board[row][col].getType() == board[row+2][col].getType()) {  
                    
                    matchedTiles[row][col] = true;  
                    matchedTiles[row+1][col] = true;  
                    matchedTiles[row+2][col] = true;  
                    foundMatch = true;  
                }  
            }  
        }  
        
        return foundMatch;  
    }  
    
    public void removeMatches() {  
        // 移除所有标记为匹配的方块  
        for (int row = 0; row < rows; row++) {  
            for (int col = 0; col < cols; col++) {  
                if (matchedTiles[row][col]) {  
                    board[row][col] = null;  
                }  
            }  
        }  
    }  
    
    public void fillEmptySpaces() {  
        // 首先将现有方块下移填补空位  
        for (int col = 0; col < cols; col++) {  
            // 从底部向上移动方块  
            for (int row = rows - 1; row >= 0; row--) {  
                if (board[row][col] == null) {  
                    // 找到一个空位，从上面找一个方块下移  
                    for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {  
                        if (board[aboveRow][col] != null) {  
                            // 找到了一个方块，移动它  
                            board[row][col] = board[aboveRow][col];  
                            board[aboveRow][col] = null;  
                            // 更新方块位置  
                            board[row][col].setY(row);  
                            break;  
                        }  
                    }  
                }  
            }  
        }  
        
        // 然后在顶部生成新方块  
        for (int row = 0; row < rows; row++) {  
            for (int col = 0; col < cols; col++) {  
                if (board[row][col] == null) {  
                    int type = random.nextInt(6) + 1;  
                    board[row][col] = new Tile(type, col, row, tileSize);  
                }  
            }  
        }  
    }  
    
    public int getRows() {  
        return rows;  
    }  
    
    public int getCols() {  
        return cols;  
    }  
}  