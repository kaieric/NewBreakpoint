package game;
import java.awt.*;
import java.awt.color.*;

public class PixelSprite {
    private Integer[][] image;
    private int pxSize;
    private Point position;
    private Color[] pallete;

    private int width, height;

    public PixelSprite(Integer[][] image, int pxSize, Point position, Color[] pallete) {
        this.image = image;
        this.pxSize = pxSize;
        this.position = position;
        this.pallete = pallete;

        this.width = image.length * pxSize;
        this.height = image[0].length * pxSize;
    }

    public void paint(Graphics brush) {
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {
                if (image[row][col] != null) {
                    brush.setColor(pallete[image[row][col]]); //the int in the image corresponds to a pallete Color.
                    brush.fillRect(
                        (int)(position.getX() - (width/2)) + (col * pxSize),
                        (int)(position.getY() - (height/2)) + (row * pxSize),
                        pxSize,
                        pxSize);
                }
            }
        }
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void updatePos(Point pos) {
        this.position = pos;
    }



    /*
     * private int[][] spriteData = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 2, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
    };

    private Color[] palette = {
            Color.BLACK,
            Color.WHITE,
            Color.RED
    };

    private int scale = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < spriteData.length; y++) {
            for (int x = 0; x < spriteData[y].length; x++) {
                int colorIndex = spriteData[y][x];
                g.setColor(palette[colorIndex]);
                g.fillRect(x * scale, y * scale, scale, scale);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Art Sprite");
        PixelArtSprite panel = new PixelArtSprite();
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
     */
}
