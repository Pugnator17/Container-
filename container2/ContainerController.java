             
import java.awt.Color;
import javax.swing.JOptionPane;

public class ContainerController {
    
    private Stack<Container> containerStapel1;
    private Stack<Container> containerStapel2;
    //private Stack<Container> haken;
    private Stack<Container> containerStapelHafen;
    
    private Container selectedContainer;
    
    private Color[] farben;
    protected Color farbe;
    
    private Container[] ContainerSave1;
    private Container[] ContainerSave2;
    private Container haken;
    
    ContainerView view;
    
    public ContainerController() {
        containerStapel1 = new Stack<Container>();
        containerStapel2 = new Stack<Container>();
        containerStapelHafen = new Stack<Container>();
        
        reset();        
        view = new ContainerView(this, false);
    }
    
    public void reset() {
        emptyStack(containerStapel1);
        
        haken = null;
        
        ContainerSave1 = new Container[4];
        ContainerSave1[0] = new Container(1, Color.RED);
        ContainerSave1[1] = new Container(2, Color.GRAY);
        ContainerSave1[2] = new Container(3, Color.ORANGE);
        ContainerSave1[3] = new Container(3, Color.RED);
        for(int i = 0; i < ContainerSave1.length; i++){
            containerStapel1.push(ContainerSave1[i]);
        }
        
        
        emptyStack(containerStapel2);
        ContainerSave2 = new Container[2];
        ContainerSave2[0] = new Container(4, Color.GREEN);
        ContainerSave2[1] = new Container(5, Color.YELLOW);    
        for(int i = 0; i < ContainerSave2.length; i++){
            containerStapel2.push(ContainerSave2[i]);
        }
        
        emptyStack(containerStapelHafen);
        selectedContainer = null;
    }
    
    public void reset2() {
        emptyStack(containerStapel1);
        
        haken = null;
        
        containerStapel1.push(new Container(7, Color.RED));
        containerStapel1.push(new Container(3, Color.GRAY));
        for(int i = 0; i < ContainerSave1.length; i++){
            containerStapel1.push(ContainerSave1[i]);
        }
        
        
        emptyStack(containerStapel2);
        containerStapel1.push(new Container(2, Color.ORANGE));
        containerStapel2.push(new Container(3, Color.GREEN));
        containerStapel2.push(new Container(9, Color.YELLOW));    
        containerStapel2.push(new Container(5, Color.RED));
        for(int i = 0; i < ContainerSave2.length; i++){
            containerStapel2.push(ContainerSave2[i]);
        }
        
        emptyStack(containerStapelHafen);
        selectedContainer = null;
    }

    public void resetRandom() {
        emptyStack(containerStapel1);
        
        haken = null;
        
        int j = random(5);
        ContainerSave1 = new Container[j];
        for(int i = 0; i < j; i++) {
            //containerStapel1.push(new Container(random(10), farbe(12)));
            ContainerSave1[i] = new Container(random(10), farbe(random(random(12))));
            containerStapel1.push(ContainerSave1[i]);
        }
        
        emptyStack(containerStapel2);
        int k = random(5);
        ContainerSave2 = new Container[k];
        for(int i = 0; i < k; i++) {
            ContainerSave2[i] = new Container(random(10), farbe(random(random(12))));
            containerStapel2.push(ContainerSave2[i]);
        }
        
        emptyStack(containerStapelHafen);
        selectedContainer = null;
    }
    
    public void resetLast() {
        emptyStack(containerStapel1);
        
        haken = null;
        
        for(int i = 0; i < ContainerSave1.length; i++){
            containerStapel1.push(ContainerSave1[i]);
        }
        emptyStack(containerStapel2);
        for(int i = 0; i < ContainerSave2.length; i++){
            containerStapel2.push(ContainerSave2[i]);
        }
        
        emptyStack(containerStapelHafen);
        selectedContainer = null;
    }
    
    private void emptyStack(Stack<Container> stapel) {
        while (!stapel.isEmpty()) {
            stapel.pop();
        }        
    }

    public Stack<Container> getStapel1() {
        return containerStapel1;
    }

    public Stack<Container> getStapel2() {
        return containerStapel2;
    }
    
    public Container getStapelHaken() {
        return haken;
    }
    
    public Stack<Container> getStapelHafen() {
        return containerStapelHafen;
    }

    public Container getSelectedContainer() {
        return selectedContainer;        
    }
    
    public void pop(Stack<Container> stapel) {        
        if(stapel.isEmpty() == false){    
            if (haken == null){
                haken = stapel.top();
                stapel.pop();
            }
            else{
                //system.out.prinln("Haken besetzt");
                textAusgeben("Haken Besetzt");
            }
        }
        else{
            textAusgeben("Hier ist kein Container der entfernt werden kann");
        }
    }
    
    public void top(Stack<Container> stapel) {
         selectedContainer = stapel.top();
    }

    public void push(Stack<Container> stapel) {
        //stapel.push(selectedContainer);
        if (haken != null){
            stapel.push(haken);
            haken = null;
        }
        else{
            //system.out.prinln("kein Container am Haken")
            textAusgeben("kein Container am Haken");
        }
    }
    
    public void isEmpty(Stack<Container> stapel) {        
        JOptionPane.showMessageDialog(null, "isEmpty: " + stapel.isEmpty());
    }
    
    // TODO: Methode vervollständigen
    /**
     * Das Schiff wird komplett entladen
     */
    public void schiffEntladen() {
        while (containerStapel1.isEmpty() == false) {
            Container oberster = containerStapel1.top();
            containerStapel1.pop();
            containerStapelHafen.push(oberster);
        }
        
        while (containerStapel2.isEmpty() == false) {
            Container oberster = containerStapel2.top();
            containerStapel2.pop();
            containerStapelHafen.push(oberster);
        }
    }

    // TODO: Methode schreiben
    /**
     * Nur rote Container werden entladen
     */
    public void schiffEntladenRot() {
        while (containerStapel1.isEmpty() == false) {
            Container oberster = containerStapel1.top();
            
            if (oberster.getFarbe() ==  Color.RED) {
                containerStapel1.pop();
                containerStapelHafen.push(oberster);
            }
            else{
                containerStapel1.pop();
                containerStapel2.push(oberster);                
            }
        }
        
        while(containerStapel2.isEmpty() == false){
            Container oberster = containerStapel2.top();
            
            if (oberster.getFarbe() ==  Color.RED) {
                containerStapel2.pop();
                containerStapelHafen.push(oberster);
            }
            else{
                containerStapel2.pop();
                containerStapel1.push(oberster);                
            }
        }
    }

    // TODO: Methode schreiben
    /**
     * Alle Container, außer die roten werden entladen
     */    
    public void schiffEntladenNichtRot() {
        while (containerStapel1.isEmpty() == false) {
            Container oberster = containerStapel1.top();
            if (oberster.getFarbe() != Color.RED) {
                containerStapel1.pop();
                containerStapelHafen.push(oberster);
            }
            else{
                containerStapel1.pop();
                containerStapel2.push(oberster);                
            }
        }
        
        while(containerStapel2.isEmpty() == false){
            Container oberster = containerStapel2.top();
            
            if (oberster.getFarbe() != Color.RED) {
                containerStapel2.pop();
                containerStapelHafen.push(oberster);
            }
            else{
                containerStapel2.pop();
                containerStapel1.push(oberster);                
            }
        }
    }

    // TODO: Methode schreiben
    /**
    * Die Container sollen am Ende nach Code sortiert im Hafen stehen, der Container mit dem kleinsten Code ganz unten
    */
    public void schiffEntladenSortiert() {
        while(containerStapel1.isEmpty() == false || containerStapel2.isEmpty() == false){
            while (containerStapel2.isEmpty() == false){
                Container oberster = containerStapel2.top();
                containerStapel2.pop();
                containerStapel1.push(oberster);
            }
        
            Container oberster = containerStapel1.top();
            containerStapel1.pop();
            containerStapelHafen.push(oberster);
            
            while (containerStapel1.isEmpty() == false){
                Container containerHafen = containerStapelHafen.top();
                Container container1 = containerStapel1.top();
                if(containerHafen.getCode() <= container1.getCode()){
                    containerStapel1.top();
                    containerStapel1.pop();
                    containerStapel2.push(container1);
                }
                else{
                    containerStapelHafen.top();
                    containerStapelHafen.pop();
                    containerStapel2.push(containerHafen);
                    containerStapel1.top();
                    containerStapel1.pop();
                    containerStapelHafen.push(container1);
                }
            }
        }
    }
    
    int random(int n){
        return new java.util.Random().nextInt(n) + 1;
    }
    
    Color farbe(int f) {
        farben = new Color[12];
            farben[0] = Color.RED;
            farben[1] = Color.BLUE;
            farben[2] = Color.RED;
            farben[3] = Color.WHITE;
            farben[3] = Color.GREEN;
            farben[4] = Color.ORANGE;
            farben[5] = Color.RED;
            farben[6] = Color.MAGENTA;
            farben[7] = Color.GRAY;
            farben[8] = Color.CYAN;
            farben[9] = Color.YELLOW;
            farben[10] = Color.PINK;
            farben[11] = Color.RED;
        return farben[f];
    }
    
    private void textAusgeben(String text){
        view.textAusgeben(text);
    }
}