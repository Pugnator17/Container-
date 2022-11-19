 

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.RepaintManager;

public class ContainerSideView {

    private ContainerController controller;
    private Canvas leinwand;
    
    private static final int WIDTH = 900;
    private static final int HEIGHT = 500;

    private static final int CONTAINER_HEIGHT = 50;
    private static final int CONTAINER_WIDTH = 200;
        
    private static int CONTAINER_STAPEL1_X = 180;
    private static int CONTAINER_STAPEL2_X = CONTAINER_STAPEL1_X + CONTAINER_WIDTH + 50;
    private static int CONTAINER_STAPEL_HAFEN_X = CONTAINER_STAPEL2_X + CONTAINER_WIDTH + 50;
    
    private static final int CONTAINER_Y = HEIGHT-100;
    int show;
    
    public ContainerSideView(ContainerController controller) {
        this.controller = controller;
        leinwand = new Canvas("SideView", WIDTH, HEIGHT);        
        leinwand.setVisible(true);
        updateView();
        leinwand.getJFrame().setLocation(0, ContainerView.HEIGHT+50);
    }

    private void updateView(int x, Stack<Container> stapel) {
        if(show != 1){    
            int y = CONTAINER_Y-CONTAINER_HEIGHT;
        
            Stack<Container> temp = new Stack<Container>();
        
            while(stapel.isEmpty() == false) {
                temp.push(stapel.top());
                stapel.pop();
            }
        
            while(temp.isEmpty() == false) {
                Container c = temp.top();
            
                leinwand.setForegroundColor(c.getFarbe());
                leinwand.fillRectangle(x, y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
                leinwand.setForegroundColor(Color.BLACK);
                leinwand.drawString(Integer.toString(c.getCode()), x+CONTAINER_WIDTH/2, y+25);
                y = y - CONTAINER_HEIGHT;
            
                stapel.push(c);
                temp.pop();
            }
        }
    }

    public void updateView() {
        if(show != 1){
            leinwand.erase();
            leinwand.drawString("Der Containerterminal von der Seite:", 200, 20);
        
            Rectangle ship = new Rectangle(CONTAINER_STAPEL1_X, HEIGHT-100, CONTAINER_STAPEL2_X+50, 100);
            leinwand.draw(ship);
        
            leinwand.drawLine(0, HEIGHT-100, CONTAINER_STAPEL1_X, CONTAINER_Y);
            leinwand.drawLine(0, HEIGHT-100, CONTAINER_STAPEL1_X, HEIGHT);
    
            Rectangle dock = new Rectangle(CONTAINER_STAPEL_HAFEN_X,CONTAINER_Y, HEIGHT, WIDTH);
            leinwand.draw(dock);
        
            updateView(CONTAINER_STAPEL1_X, controller.getStapel1());
            updateView(CONTAINER_STAPEL2_X, controller.getStapel2());
            updateView(CONTAINER_STAPEL_HAFEN_X, controller.getStapelHafen());
        }
    }
    
    public void close(){
        leinwand.setVisible(false);
    }
}
