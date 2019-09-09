import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;


public class MainWindow extends Application {
    Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;
        VBox vbox = new VBox(10);
        Label titleLabel = new Label("Inserisci la Parola da anagrammare");
        TextField textField = new TextField();
        Button createAnagramButton = new Button("Create Anagrams");

        createAnagramButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");

        VBox.setMargin(titleLabel,new Insets(0,10,0,10));
        VBox.setMargin(textField,new Insets(0,10,0,10));
        VBox.setMargin(createAnagramButton,new Insets(0,10,0,10));

        createAnagramButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String wordToAnagram = textField.getText();
                ShowAnagrams(wordToAnagram);
            }
        });

        vbox.getChildren().addAll(titleLabel,textField,createAnagramButton);

        scene = new Scene(vbox,300,100);
        scene.getStylesheets().add("Viper.css");
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }

    private void ShowAnagrams(String wordToAnagram){
        Stage stage = new Stage();
        VBox vBox = new VBox(10);
        Permutation permutation = new Permutation(wordToAnagram);
        ListView<String> anagramListView = new ListView<>();
        ArrayList<String> anagramsArrayList = permutation.returnAnagrams();
        ObservableList<String> anagramsObservableList;

        anagramsObservableList = FXCollections.observableArrayList(anagramsArrayList);
        anagramListView.setItems(anagramsObservableList);
        anagramListView.setOrientation(Orientation.VERTICAL);
        vBox.getChildren().addAll(anagramListView);

        stage.setTitle("All the anagrams of "+wordToAnagram);
        stage.setScene(new Scene(vBox,300,400));
        stage.show();
    }

    /*
    FIXME cose da fare:
    - stile css
    -trova anagramma
    - ordinarlo
    -copia
    -creare un file txt con tutti gli anagrammi
    - lista di anagrammi che cominciano con ...
    */


}
