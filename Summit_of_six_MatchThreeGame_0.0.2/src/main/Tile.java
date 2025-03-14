package main;  

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;  

public class Tile {  
    private int type; // 方块类型（1-6）  
    private int x, y; // 方块在棋盘中的位置  
    private int pixelX, pixelY; // 方块在屏幕上的像素位置  
    private int size; // 方块大小  
    private boolean selected; // 是否被选中  
    private BufferedImage image; // 方块图片  
    
    // 临时使用的颜色数组，后续可替换为图片  
    private static final Color[] COLORS = {  
        Color.RED, Color.BLUE, Color.GREEN,   
        Color.YELLOW, Color.MAGENTA, Color.CYAN  
    };  
    
    // 存储加载的贴图  
    private static BufferedImage[] tileImages;  
    
    // 静态初始化块，加载所有图片  
    static {  
        tileImages = new BufferedImage[6];  
        try {  
            // 尝试加载图片，如果存在的话  
            for (int i = 0; i < 6; i++) {  
                File file = new File("src/main/resources/tile" + (i+1) + ".jpg");  
                if (file.exists()) {  
                    tileImages[i] = ImageIO.read(file);  
                }  
            }  
        } catch (IOException e) {  
            System.out.println("无法加载图片资源: " + e.getMessage());  
            // 如果加载失败，我们将使用颜色代替  
        }  
    }  
    
    public Tile(int type, int x, int y, int size) {  
        this.type = type;  
        this.x = x;  
        this.y = y;  
        this.size = size;  
        this.pixelX = x * size;  
        this.pixelY = y * size;  
        this.selected = false;  
        
        // 尝试使用对应类型的图片  
        if (type > 0 && type <= tileImages.length && tileImages[type-1] != null) {  
            this.image = tileImages[type-1];  
        }  
    }  
    
    public void draw(Graphics2D g2) {  
        if (image != null) {  
            // 如果有图片，绘制图片  
            g2.drawImage(image, pixelX, pixelY, size, size, null);  
        } else {  
            // 否则绘制颜色块  
            g2.setColor(COLORS[type - 1]);  
            g2.fillRect(pixelX, pixelY, size, size);  
        }  
        
        // 绘制方块边框  
        g2.setColor(Color.BLACK);  
        g2.drawRect(pixelX, pixelY, size, size);  
        
        // 如果被选中，绘制高亮边框  
        if (selected) {  
            g2.setColor(Color.WHITE);  
            g2.setStroke(new BasicStroke(3));  
            g2.drawRect(pixelX + 3, pixelY + 3, size - 6, size - 6);  
            g2.setStroke(new BasicStroke(1));  
        }  
    }  
    
    // Getters and Setters 保持不变  
    public int getType() { return type; }  
    public int getX() { return x; }  
    public int getY() { return y; }  
    public int getPixelX() { return pixelX; }  
    public int getPixelY() { return pixelY; }  
    
    public void setX(int x) {  
        this.x = x;  
        this.pixelX = x * size;  
    }  
    
    public void setY(int y) {  
        this.y = y;  
        this.pixelY = y * size;  
    }  
    
    public void setSelected(boolean selected) {  
        this.selected = selected;  
    }  
    
    public boolean isSelected() {  
        return selected;  
    }  
    
    // 检查点击是否在方块内  
    public boolean contains(int mouseX, int mouseY) {  
        return mouseX >= pixelX && mouseX < pixelX + size &&  
               mouseY >= pixelY && mouseY < pixelY + size;  
    }  
}  