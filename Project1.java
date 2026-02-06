import javax.swing.JFrame;

public class Project1 {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        GamePlay game = new GamePlay();
        f.setBounds(10, 10, 700, 600); // 600 , 
        f.setTitle("Break-The-Ball");
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(game);
    }
}