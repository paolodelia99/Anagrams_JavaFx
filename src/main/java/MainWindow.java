import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class MainWindow extends Application {
    Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;
        VBox vbox = new VBox(10);
        Label titleLabel = new Label("Insert the word to anagram");
        TextField textField = new TextField();
        Button createAnagramButton = new Button("Create Anagrams");

        VBox.setMargin(titleLabel,new Insets(0,10,0,10));
        VBox.setMargin(textField,new Insets(0,10,0,10));
        VBox.setMargin(createAnagramButton,new Insets(0,10,0,10));

        createAnagramButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String wordToAnagram = textField.getText();
                if(wordToAnagram== null){return;}
                ShowAnagrams(wordToAnagram);
            }
        });

        vbox.getChildren().addAll(titleLabel,textField,createAnagramButton);

        scene = new Scene(vbox,300,125);
        scene.getStylesheets().add("Viper.css");
        stage = primaryStage;
        stage.setTitle("Create Anagrams");
        stage.getIcons().add(new Image("disegno-di-lettera-alfabeto-colorato-600x600.png"));
        stage.setScene(scene);
        stage.show();
    }

    private void ShowAnagrams(String wordToAnagram){
        Stage anagramStage = new Stage();
        Scene scene;
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem saveAnagramsItem = new MenuItem("Save Anagrams");
        VBox vBox = new VBox(10);
        Permutation permutation = new Permutation(wordToAnagram);
        ListView<String> anagramListView = new ListView<>();
        ArrayList<String> anagramsArrayList = permutation.returnAnagrams();
        ObservableList<String> anagramsObservableList;

        anagramsObservableList = FXCollections.observableArrayList(anagramsArrayList);
        anagramListView.setItems(anagramsObservableList);
        anagramListView.setOrientation(Orientation.VERTICAL);
        vBox.getChildren().addAll(anagramListView);

        menu.getItems().add(saveAnagramsItem);
        menuBar.getMenus().add(menu);

        saveAnagramsItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String prefixPath = "C:\\Users\\PC\\IdeaProjects\\sample.anagram\\src\\main\\resources\\";
                String pathOfFile = prefixPath+wordToAnagram+".txt";
                File file = new File(pathOfFile);

                try {
                    if (file.createNewFile()){
                        //fragment with the path of the file and copy the path
                        openPathWindow(pathOfFile);

                        FileWriter writer = null;
                        try {
                            writer = new FileWriter(prefixPath+wordToAnagram+".txt");
                            for(String str: anagramsArrayList) {
                                writer.write(str + System.lineSeparator());
                            }
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        //fragement that asks you if you want to change the name
                        openPathWindow(pathOfFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox);

        scene = new Scene(borderPane,300,400);
        anagramStage.setTitle("All the anagrams of "+wordToAnagram);
        anagramStage.setScene(scene);
        anagramStage.getIcons().add(new Image("disegno-di-lettera-alfabeto-colorato-600x600.png"));
        anagramStage.show();
    }

    private void openPathWindow(String pathOfFile){
        Stage stage = new Stage();
        VBox vBox = new VBox(20);
        Label thisIsPathLabel = new Label("This is the path of the file: ");
        SelectableLabel pathLabel = new SelectableLabel(pathOfFile);

        vBox.setPadding(new Insets(20,20,10,20));
        vBox.setAlignment(Pos.TOP_CENTER);

        vBox.getChildren().addAll(thisIsPathLabel,pathLabel);

        Scene scene = new Scene(vBox,550,125);
        scene.getStylesheets().add("Viper.css");
        stage.setTitle("Path of the file");
        stage.getIcons().add(new Image("disegno-di-lettera-alfabeto-colorato-600x600.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }


    /*
    FIXME cose da fare:
    - stile css
    - trova anagramma
    - lista di anagrammi che cominciano con ...
    - trova un modo per aprire nuovo stage in un altro modo(se riesci e se ne vale la pena)
    */

}
