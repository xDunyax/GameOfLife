import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //GameOfLife
        GameOfLife g = new GameOfLife();

        // Elemente
        Label title = new Label("Testfälle");
        Button testOne = new Button("Testfall 1 Gleiter");
        Button testTwo = new Button("Testfall 2 Feld 54");

        // Buttons Power
        testOne.setOnAction(e -> {
            g.init();
            g.testGlider(0, g.y_SIZE);
            g.graffic(1000);
        });
        testTwo.setOnAction(e -> {
            g.init();
            g.testSpecificField();
            g.graffic(60);
        });



        // Layout
        VBox layout = new VBox(title, testOne, testTwo);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        // Scene
        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}