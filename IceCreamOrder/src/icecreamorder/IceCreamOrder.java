package icecreamorder;

import java.io.File;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This program helps you determine the cost of
 * one ice cream order.
 * Ice Cream costs $2.25 and toppings are $0.50, plus $0.06 tax.
 * @author Sim
 * December 11, 2016
 */

public class IceCreamOrder extends Application {

    public static final double ICE_CREAM_COST = 2.25;
    public static final double ICE_CREAM_EXTRAS = 0.50;
    public static final double MICH_TAX = 0.06;
    public static final String ICE_CREAM_FILE = "icecream.txt";
    
    @Override
    public void start(Stage primaryStage) {

    RadioButton Chocolate = new RadioButton("Chocolate ");
    RadioButton Vanilla = new RadioButton("Vanilla ");
    RadioButton Strawberry = new RadioButton("Strawberry");

    Button CalculateCost = new Button("Calculate Cost");
    Button Save = new Button("Save ");
    Button Restore = new Button("Restore "); 

    CheckBox nuts = new CheckBox("Nuts");     
    CheckBox cherries = new CheckBox("Cherries  ");
      
    CalculateCost.setOnAction(
        (event)-> {
        
        double totalCost = ICE_CREAM_COST;
        double subTotal = 0.0;
        double taxedAmount = 0.0;
        
        if (nuts.isSelected() == true) {
            totalCost += ICE_CREAM_EXTRAS;
        }
        
        if (cherries.isSelected() == true) {
            totalCost += ICE_CREAM_EXTRAS;
        } 
            
        subTotal = totalCost;
        taxedAmount = totalCost * MICH_TAX;
        totalCost += totalCost * MICH_TAX;
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Your Order");

        String r = String.format("Total: $ %.2f", totalCost);
        alert.setHeaderText(r);
        String s = String.format(" Order: $ %.2f" + "\n Tax:     $ %.2f" + "\n "
                + "Total:  $ %.2f", subTotal, taxedAmount, totalCost);
        alert.setContentText(s);
        alert.showAndWait();
        }
    );
    
    Save.setOnAction(
        (event)-> {
            try
            {
                PrintWriter out = new PrintWriter(ICE_CREAM_FILE);
                
                if (Vanilla.isSelected() == true) {
                    out.println("Vanilla");
                }
                if (Chocolate.isSelected() == true) {
                    out.println("Chocolate");
                }
                if (Strawberry.isSelected() == true) {
                    out.println("Strawberry");
                }
                if (nuts.isSelected() == true) {
                    out.println("With_Nuts");
                }
                if (nuts.isSelected() == false) {
                    out.println("Without_Nuts");
                }
                if (cherries.isSelected() == true) {
                    out.println("With_Cherries");
                }
                if (cherries.isSelected() == false) {
                    out.println("Without_Cherries");
                }
                out.close();
            }
            
            catch (Exception e)
            {
                System.out.println("I did not see that coming...");   
            }
        }
    );
    
    Restore.setOnAction(
        (event)-> {
            try
            {
                File iceCreamFile = new File(ICE_CREAM_FILE);

                if (iceCreamFile.exists())
                {   
                    Scanner scan = new Scanner(iceCreamFile);
                    String flavorWord = scan.nextLine();
                    String nutWord = scan.nextLine();
                    String cherryWord = scan.nextLine();

                    if (flavorWord.equalsIgnoreCase("Chocolate"))
                        Chocolate.setSelected(true);
                    
                    if (flavorWord.equalsIgnoreCase("Strawberry"))
                        Strawberry.setSelected(true);
                    
                    if (flavorWord.equalsIgnoreCase("Vanilla"))
                        Vanilla.setSelected(true);
                    
                    if (nutWord.equalsIgnoreCase("With_Nuts"))
                        nuts.setSelected(true);
                        
                    if (nutWord.equalsIgnoreCase("Without_Nuts"))
                        nuts.setSelected(false);
                    
                    if (cherryWord.equalsIgnoreCase("With_Cherries"))
                        cherries.setSelected(true);
                    
                    if (cherryWord.equalsIgnoreCase("Without_Cherries"))
                        cherries.setSelected(false);
            
                    scan.close();
                }
                    
                else
                {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Restoring File");
                    alert.setHeaderText("Error encountered");
                    alert.setContentText("java.io.FileNotFoundException: icecream.txt (The "
                            + "system cannot find the file specified)");
                    alert.showAndWait();
                }     
            }
            
            catch (Exception e)
            {
                System.out.println("I... didn't see that coming!");
            }                          
        }
    );
    
    ToggleGroup group = new ToggleGroup();
    group.getToggles().addAll(Chocolate, Vanilla, Strawberry);
      
    Chocolate.setSelected(true);
      
    HBox box = new HBox();
    box.getChildren().addAll(Chocolate, Vanilla, Strawberry);
      
    HBox boxToppings = new HBox();
    TitledPane toppings = new TitledPane();
    toppings.setText("Toppings");
    toppings.setContent(boxToppings);
    boxToppings.getChildren().addAll(cherries, nuts);
      
    TitledPane pane = new TitledPane();
    pane.setText("Flavors");
    pane.setContent(box);
     
    HBox boxOrder = new HBox();
    boxOrder.getChildren().addAll(CalculateCost, Save, Restore);
      
    TitledPane paneOrder = new TitledPane();
    paneOrder.setText("Order");
    paneOrder.setContent(boxOrder);

    Scene sceneOrder = new Scene(paneOrder);
   
    HBox allBoxes = new HBox();
    allBoxes.getChildren().addAll(pane, toppings, paneOrder);
    Scene scene = new Scene(allBoxes);

    primaryStage.setTitle("Simon - Ice Cream");
    primaryStage.setScene(scene);
    primaryStage.show();
     
    }   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

