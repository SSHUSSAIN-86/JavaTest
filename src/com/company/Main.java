package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


class MarupekeGrid{
    int size;
    MarupekeTile[][] marupekeTiles;
    boolean isLegalGrid;
    List<Reason> illegalitiesInGrid;

    MarupekeGrid(MarupekeTile[][] marupekeTiles,int size){
        this.marupekeTiles=marupekeTiles;
        this.size=size;

        //Setting up isLegalGrid value
        if(size>10 || size<3) {
            isLegalGrid = false;
            illegalitiesInGrid= new ArrayList<Reason>();
        } else{
            isLegalGrid = true;
            intilizeAllMaupekeTiles(marupekeTiles,size);
            printTiles(marupekeTiles,size);

        }

    }


    // intilizeAllMaupekeTiles intitilzes Grid to all Blank
    void intilizeAllMaupekeTiles(MarupekeTile[][] marupekeTiles,int size){
        Tile tile=Tile.BLANK;
        isLegalGrid = false;
        illegalitiesInGrid= new ArrayList<Reason>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                marupekeTiles[i][j] = new MarupekeTile(true,tile);
            }
        }

    }

    void checkIllegalitiesInGrid(boolean isLegalGrid, MarupekeGrid marupekeGrid){
        if(isLegalGrid==false) {
            if (marupekeGrid.size > 10) {

                ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid size cannot be greater then 10");
                illegalitiesInGrid.add(reasonDiagonal);

            } else if(marupekeGrid.size < 3){
                ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid size cannot be less then 3");
                illegalitiesInGrid.add(reasonDiagonal);

            } else {
                // Scanning for Horizontal violation [x,y] [x,y+1] and [x,y+2]
                for(int i = 0; i < size; i++) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j+2].tile){
                            ReasonHorizontal reasonHorizontal = new ReasonHorizontal("Grid violation at grid coordinates " + i +"," + j +" "+ i +"," + (j+1) + " and "
                            + i +"," + (j+2));
                            illegalitiesInGrid.add(reasonHorizontal);
                        }
                    }
                }

                // Scanning for Horizontal violation [x,y] [x,y-1] and [x,y-2]
                for(int i = 0; i < size; i++) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i][j-2].tile){
                            ReasonHorizontal reasonHorizontal = new ReasonHorizontal("Grid violation at grid coordinates " + i +"," + j +" "+ i +"," + (j-1) + " and "
                                    + i +"," + (j-2));
                            illegalitiesInGrid.add(reasonHorizontal);
                        }
                    }
                }

                // Scanning for Vertical violation [x,y] [x+1,y] and [x+2,y]
                for(int i = 0; i < size-2; i++) {
                    for (int j = 0; j < size; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j].tile){
                            ReasonVertical reasonVertical = new ReasonVertical("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + j + " and "
                                    + (i+2) +"," + j);
                            illegalitiesInGrid.add(reasonVertical);
                        }
                    }
                }

                // Scanning for Vertical violation [x,y] [x-1,y] and [x-2,y]
                for(int i = size-1; i > 1; i--) {
                    for (int j = 0; j < size; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j].tile){
                            ReasonVertical reasonVertical = new ReasonVertical("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + j + " and "
                                    + (i-2) +"," + j);
                            illegalitiesInGrid.add(reasonVertical);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x+1,y-1] and [x+2,y-2]
                for(int i = 0; i < size-2; i++) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j-2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + (j-1) + " and "
                                    + (i+2) +"," + (j-2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x-1,y+1] and [x-2,y+2]
                for(int i = size-1; i > 1; i--) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j+2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + (j+1) + " and "
                                    + (i-2) +"," + (j+2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x-1,y-1] and [x-2,y-2]
                for(int i = size-1; i > 1; i--) {
                    for (int j = size-1; j > 1; j--) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-1][j-1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i-2][j-2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i-1) +"," + (j-1) + " and "
                                    + (i-2) +"," + (j-2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

                // Scanning for Diagonal violation [x,y] [x+1,y+1] and [x+2,y+2]
                for(int i = 0; i < size-2; i++) {
                    for (int j = 0; j < size-2; j++) {
                        if(marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+1][j+1].tile &&
                                marupekeGrid.marupekeTiles[i][j].tile == marupekeGrid.marupekeTiles[i+2][j+2].tile){
                            ReasonDiagonal reasonDiagonal = new ReasonDiagonal("Grid violation at grid coordinates " + i +"," + j +" "+ (i+1) +"," + (j+1) + " and "
                                    + (i+2) +"," + (j+2));
                            illegalitiesInGrid.add(reasonDiagonal);
                        }
                    }
                }

            }

        }
    }

    //printTiles will print put out only if Grid value is legal
    void printTiles(MarupekeTile[][] marupekeTiles,int size){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.println(marupekeTiles[i][j].tile);
            }
            System.out.println("\n\r");
        }


    }

    public GridPane createGrid(BooleanProperty[][] binaryController) {

        int columns = binaryController.length ;
        int rows = binaryController[0].length ;

        GridPane grid = new GridPane();

        for (int x = 0 ; x < columns ; x++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(columnConstraints);
        }

        for (int y = 0 ; y < rows ; y++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (int x = 0 ; x < columns ; x++) {
            for (int y = 0 ; y < rows ; y++) {
                grid.add(createCell(binaryController[x][y]),x,y);
            }
        }


        grid.getStyleClass().add("grid");
        return grid;
    }
    public GridPane createCell(BooleanProperty booleanControl) {

        GridPane gridpane = new GridPane();

        Line line1 = new Line(-25,25,25,-25);
        Line line2 = new Line(-25,-25,25,25);
        Group group = new Group(line1,line2);
        Circle circle = new Circle(20, Color.CORNFLOWERBLUE);
        Rectangle rectangle = new Rectangle(50,50);

        List<ObservableList> list = new ArrayList<>();


        gridpane.setOnMouseClicked(event -> {
            if(event.getClickCount()==1) {gridpane.getChildren().removeAll(group,rectangle); gridpane.getChildren().add(circle);
            gridpane.getChildren().forEach(Node->{System.out.println(Node);});
            list.add(gridpane.getChildren());
            }
            if(event.getClickCount()==2)  {gridpane.getChildren().removeAll(circle,rectangle); gridpane.getChildren().add(group);
            gridpane.getChildren().forEach(Node->{System.out.println(Node);});
            list.add(gridpane.getChildren());
            }
            if(event.getClickCount()==3)  {gridpane.getChildren().removeAll(circle,group); gridpane.getChildren().add(rectangle);
            gridpane.getChildren().forEach(Node->{System.out.println(Node);});
            list.add(gridpane.getChildren());
            }
            if(event.getClickCount()>3 ||  event.getClickCount()<=0) {gridpane.getChildren().removeAll(circle,group,rectangle);
            gridpane.getChildren().forEach(Node->{System.out.println(Node);});
            list.add(gridpane.getChildren());
            }
            System.out.println(list);

        });

        //System.out.println(list);


        gridpane.getStyleClass().add("cell");
        gridpane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: red;");
        return gridpane;
    }

    public void checkIfGridIsFull(GridPane grid){
        ObservableList<Node> gridChildren = grid.getChildren();
        if(gridChildren!=null) {
            gridChildren.forEach(Node->{
                System.out.println(Node);
            });
        }
    }

}

//Main class
public class Main extends Application{
    public static void main(String[] args) {

        launch(args);
    }



    //Start method begins here
    @Override
    public void start(Stage primaryStage) {
        int size;

        /*  This reads the size of grid
         *  provided by player
         */
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter size of grid: ");

        // This method reads the number provided
        size = scan.nextInt();

        // Closing Scanner after the use
        scan.close();

        //Initializing Marupeke Tile Array
        MarupekeTile[][] marupekeTileArray=new MarupekeTile[size][size];

        //Initializing Marupeke Grid
        MarupekeGrid marupekeGrid = new MarupekeGrid(marupekeTileArray,size);


        marupekeGrid.intilizeAllMaupekeTiles(marupekeTileArray,size);


        //Checking for Maurpeke Grid legality conditions
        marupekeGrid.checkIllegalitiesInGrid(marupekeGrid.isLegalGrid,marupekeGrid);

        System.out.println(marupekeGrid.illegalitiesInGrid);
        System.out.println(marupekeGrid.isLegalGrid);



        BooleanProperty[][] binaryController = new BooleanProperty[size][size];
        for (int x = 0 ; x < size ; x++) {
            for (int y = 0 ; y < size ; y++) {
                binaryController[x][y] = new SimpleBooleanProperty();
            }
        }

        GridPane grid = marupekeGrid.createGrid(binaryController);

        marupekeGrid.checkIfGridIsFull(grid);

        StackPane stackPane = new StackPane(grid);

        Button submit = new Button("Submit");

        submit.setOnMouseClicked(event ->{
            if(event.getClickCount()==1){
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                //Checking for Maurpeke Grid legality conditions
                marupekeGrid.checkIllegalitiesInGrid(marupekeGrid.isLegalGrid,marupekeGrid);
            }

        });

        grid.add(submit,size-size/3,size);


        stackPane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        grid.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: green;");

        //This sets the main scene
        Scene scene = new Scene(stackPane, 600, 600);

        //Subsequent scenes
        Scene scene1, scene2;

        //Scene 2
        Label label1= new Label("Click 3 times on the grid to set up the constraints");
        Button button1= new Button("Click here to start!");
        button1.setOnAction(e -> primaryStage.setScene(scene));
        VBox vbox1 = new VBox(20);
        vbox1.getChildren().addAll(label1, button1);
        scene2= new Scene(vbox1, 500, 500);

        //Scene 1
        Label label= new Label("Do you want to play the game?");
        Button button= new Button("Click to play!");
        button.setOnAction(e -> primaryStage.setScene(scene2));
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(label, button);
        scene1= new Scene(vbox, 300, 250);

        //primaryStage starts from Scene1
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Marupeke Game");
        primaryStage.show();


    }

}

