import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public Bricks(int row , int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = 540/col;
        brickHeight = 150/row;
    

    }
        public void drawBricks(Graphics2D graphics2d)
        {
            for (int i = 0; i < map.length; i++){
                for (int j = 0; j < map[0].length; j++){
                    if (map[i][j] > 0) {
                        graphics2d.setColor(Color.WHITE);
                        graphics2d.fillRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);

                        graphics2d.setStroke(new BasicStroke(3));
                        graphics2d.setColor(Color.BLACK);
                        graphics2d.drawRect(j * brickWidth + 80, i * brickHeight + 40, brickWidth, brickHeight);
                        
                    }
                }
            }   
        }

        public void setBrickValue(int value , int row , int col){
            map[row][col] = value;
        }
}