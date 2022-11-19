import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;

public class ContainerView {
    
    private Canvas leinwand;
    private ContainerController controller;
    private JMenuBar menu;
    protected ContainerSideView sideView;
    protected boolean displayTop;
    
    public static final int WIDTH = 900;
    public static final int HEIGHT = 400;
    
    private static int CONTAINER_WIDTH = 200;
    private static final int CONTAINER_HEIGHT = 80;    
    private static final int CONTAINER_Y = 200;    
    private static final int CONTAINER_TITLE_Y = CONTAINER_Y + CONTAINER_HEIGHT + 20;
    
    private static int CONTAINER_STAPEL1_X = 180;
    private static int CONTAINER_STAPEL2_X = CONTAINER_STAPEL1_X + CONTAINER_WIDTH + 50;
    private static int CONTAINER_STAPEL_HAFEN_X = CONTAINER_STAPEL2_X + CONTAINER_WIDTH + 50;
    private static int CONTAINER_HAKEN_X = CONTAINER_STAPEL2_X;
    
    int N = 0;
    int showSideViewCheck = 0;
    
    public ContainerView(final ContainerController controller, boolean showSideView) {
        leinwand = new Canvas("Container View", WIDTH, HEIGHT);        
        this.controller = controller;        
        menu = new JMenuBar();          
              
        addStapelBefehle("Stapel1", CONTAINER_STAPEL1_X, controller.getStapel1());
        addStapelBefehle("Stapel2", CONTAINER_STAPEL2_X, controller.getStapel2());
        addStapelBefehle("Hafen", CONTAINER_STAPEL_HAFEN_X, controller.getStapelHafen());

        JMenuItem menuSetup = new JMenu("Setup");        
        JMenuItem menuSetup1 = new JMenuItem("Reset");
        menuSetup1.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.reset();
                updateView();
            }
        });
        menuSetup.add(menuSetup1);
                
        JMenuItem menuSetup2 = new JMenuItem("Reset2");
        menuSetup2.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.reset2();
                updateView();
            }
        });
        menuSetup.add(menuSetup2);
        
        JMenuItem menuSetup3 = new JMenuItem("Reset Random");
        menuSetup3.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.resetRandom();
                updateView();
            }
        });
        menuSetup.add(menuSetup3);
        
        JMenuItem menuSetup4 = new JMenuItem("Reset Last");
        menuSetup4.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.resetLast();
                updateView();
            }
        });
        menuSetup.add(menuSetup4); 
        menu.add(menuSetup);
               
        JMenuItem menuBefehle = new JMenu("Befehle");        
        JMenuItem menuEntladen = new JMenuItem("Alles entladen");
        menuEntladen.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.schiffEntladen();
                updateView();
            }
        });
        menuBefehle.add(menuEntladen);
        
        JMenuItem menuEntladenCodeX = new JMenuItem("Rote Container entladen");
        menuEntladenCodeX.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.schiffEntladenRot();
                updateView();
            }
        });
        menuBefehle.add(menuEntladenCodeX);
        
        JMenuItem menuEntladenNichtCodeX = new JMenuItem("Entladen außer rote Container");
        menuEntladenNichtCodeX.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.schiffEntladenNichtRot();
                updateView();
            }
        });
        menuBefehle.add(menuEntladenNichtCodeX);
        
        JMenuItem menuEntladenSortiert = new JMenuItem("Sortiert entladen");
        menuEntladenSortiert.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.schiffEntladenSortiert();
                updateView();
            }
        });
        menuBefehle.add(menuEntladenSortiert);
        menu.add(menuBefehle);
        JMenuItem menuHelp = new JMenu("Hilfe");
        
        JCheckBoxMenuItem menuZeigen = new JCheckBoxMenuItem("Seitenansicht");
        menuZeigen.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {                    
                if(sideView == null){                  
                    /*
                    String s = JOptionPane.showInputDialog("Passwort?", "");                
                    if (s.equals("stack")) {
                        sideView = new ContainerSideView(controller);
                    } 
                    else {
                        JOptionPane.showMessageDialog(leinwand.getJFrame(), "Ahaha! You didn't say the magic word!");                   
                    }
                    */
                    sideView = new ContainerSideView(controller);
                }
                else if(sideView != null){
                    sideView.close();
                    sideView = null;
                }
                updateView();
            }
        });  
            
        JCheckBoxMenuItem menuTop = new JCheckBoxMenuItem("Top Element immer anzeigen");
        menuTop.addActionListener(new ActionListener() {            
        public void actionPerformed(ActionEvent e) {
            if(displayTop == false){
                displayTop = true;
            }
            else{
                displayTop = false;
            }
            updateView();                
        }
        });
        menuHelp.add(menuTop);
        menuHelp.add(menuZeigen);
        menu.add(menuHelp);
        
        leinwand.getJFrame().setJMenuBar(menu);        
        leinwand.setVisible(true);
                
        updateView();   
        
        if (showSideView) {
            if(showSideViewCheck == 1){
                sideView = new ContainerSideView(controller);
            }
        }
    }


    private void addStapelBefehle(final String titel, final int x, final Stack<Container> stapel) {        
        JMenuItem menuStapel = new JMenu(titel);    
        
        JMenuItem menuStapeltop = new JMenuItem("top");
        menuStapeltop.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.top(stapel);                
                updateView();
                
                leinwand.setForegroundColor(Color.BLACK);
                leinwand.fillCircle(x+CONTAINER_WIDTH/2-10, CONTAINER_Y-30, 20);
            }
        });
        menuStapel.add(menuStapeltop);
  
        JMenuItem menuStapelpop = new JMenuItem("pop");
        menuStapelpop.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.pop(stapel);        
                updateView();
            }
        });
        menuStapel.add(menuStapelpop);
        
        JMenuItem menuStapelPush= new JMenuItem("push");
        menuStapelPush.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.push(stapel);    
                updateView();                
            }
        });
        menuStapel.add(menuStapelPush);
    
        JMenuItem menuStapelEmpty= new JMenuItem("isEmpty");
        menuStapelEmpty.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                controller.isEmpty(stapel);                
            }
        });
        menuStapel.add(menuStapelEmpty);
        
        menu.add(menuStapel);
    }

    public void updateView() {    
        leinwand.erase();
        leinwand.setForegroundColor(Color.BLACK);
        leinwand.drawString("Der Containerterminal von oben:", 200, 20);
        leinwand.drawString("Container Kran", 675, 20);
        
        Rectangle ship = new Rectangle(CONTAINER_STAPEL1_X-10, CONTAINER_Y-60, 500, 200);
        leinwand.draw(ship);
        
        Rectangle dock = new Rectangle(CONTAINER_STAPEL_HAFEN_X-10, -10, 300, 800);
        leinwand.draw(dock);
        
        leinwand.drawLine(0, CONTAINER_Y+50, CONTAINER_STAPEL1_X-10, CONTAINER_Y-60);
        leinwand.drawLine(0, CONTAINER_Y+50, CONTAINER_STAPEL1_X-10, CONTAINER_Y+140);
        
        drawContainerStapel("Stapel 1", CONTAINER_STAPEL1_X, controller.getStapel1());
        drawContainerStapel("Stapel 2", CONTAINER_STAPEL2_X, controller.getStapel2());
        drawContainerHaken(CONTAINER_HAKEN_X);
        drawContainerStapel("Hafen", CONTAINER_STAPEL_HAFEN_X, controller.getStapelHafen());
        
        if (sideView != null) {
            sideView.updateView();
        }
    }
    
    private void drawContainerStapel(String titel, int x, Stack<Container> containerStapel) {
        leinwand.setForegroundColor(Color.BLACK);
        leinwand.drawString(titel, x+60, CONTAINER_TITLE_Y);
        
        Container oberster = containerStapel.top();
        
        if (!displayTop && controller.getSelectedContainer() != oberster) {
            return;
        }
            
        if (oberster != null) {
            leinwand.setForegroundColor(oberster.getFarbe());
            leinwand.fillRectangle(x, CONTAINER_Y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
            leinwand.setForegroundColor(Color.BLACK);
            leinwand.drawString(Integer.toString(oberster.getCode()), x+90, CONTAINER_Y+40);
        } 
        else {
            leinwand.setForegroundColor(Color.WHITE);
            leinwand.fillRectangle(x, CONTAINER_Y, CONTAINER_WIDTH, CONTAINER_HEIGHT);
            leinwand.setForegroundColor(Color.BLACK);
            leinwand.drawString("Stapel leer", x+60, CONTAINER_Y+40);
        }
    }
    
    private void drawContainerHaken(int x) {
        leinwand.setForegroundColor(Color.BLACK);
        leinwand.drawString("Haken", x+60, CONTAINER_TITLE_Y+140);
        
        Container haken = controller.getStapelHaken();
        
        //if (!displayTop && controller.getSelectedContainer() != haken) {
            //return;
        //}
            
        if (haken != null) {
            leinwand.setForegroundColor(haken.getFarbe());
            leinwand.fillRectangle(x, CONTAINER_Y-180, CONTAINER_WIDTH, CONTAINER_HEIGHT);
            leinwand.setForegroundColor(Color.BLACK);
            leinwand.drawString(Integer.toString(haken.getCode()), x+90, CONTAINER_Y-140);
        } 
        else {
            leinwand.setForegroundColor(Color.WHITE);
            leinwand.fillRectangle(x, CONTAINER_Y-180, CONTAINER_WIDTH, CONTAINER_HEIGHT);
            leinwand.setForegroundColor(Color.BLACK);
            leinwand.drawString("Haken frei", x+60, CONTAINER_Y-140);
        }
        
        leinwand.fillCircle(x+CONTAINER_WIDTH/2+40, CONTAINER_Y-155, 20);
        leinwand.fillCircle(x+CONTAINER_WIDTH/2+160, CONTAINER_Y-165, 40);
        leinwand.drawLine(585, CONTAINER_Y-150, 700, CONTAINER_Y-150);
        leinwand.drawLine(585, CONTAINER_Y-140, 700, CONTAINER_Y-140);
    }
    
    public void textAusgeben(String text) {
        JOptionPane.showMessageDialog(leinwand.getJFrame(), text);
    }
}