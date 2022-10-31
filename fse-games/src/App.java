import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class App extends Application {
    public static final int WINSIZE_X = 630, WINSIZE_Y = 730;
    private final String WINTITLE = "Snake";
    public GamePanel gp;
    public Button startButton;
    public Button toggleButton;
    public BorderPane rootPane;
    public HBox topBox;

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = new BorderPane();
        gp = new GamePanel();
        gp.setOnKeyPressed(gp.getKeyLogger());
        startButton = new Button("START");
        startButton.setFocusTraversable(false);
        startButton.setOnAction(new StartButtonHandler());
        startButton.setPrefSize(WINSIZE_X, 50);
        topBox = new HBox();
        topBox.setPrefSize(WINSIZE_X, 50);
        Label scoreText = new Label("Score: ");
        Label score = new Label("0");
        topBox.setFocusTraversable(false);
        gp.setScore(score);
        topBox.getChildren().addAll(scoreText, score);
        topBox.setAlignment(Pos.CENTER);
        rootPane.setTop(topBox);
        rootPane.setBottom(startButton);
        rootPane.setCenter(gp);
        rootPane.setPrefSize(WINSIZE_X, WINSIZE_Y);
        Scene scene = new Scene(rootPane, WINSIZE_X, WINSIZE_Y);
        stage.setTitle(WINTITLE);
        stage.setScene(scene);
        stage.show();
    }

    private class StartButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gp.requestFocus();
            Runnable task = new Runnable() {
                public void run() {
                    gp.start();
                }
            };
            Thread backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
            startButton.setDisable(true);
            toggleButton = new Button("PAUSE");
            toggleButton.setFocusTraversable(false);
            toggleButton.setOnAction(new PauseButtonHandler());
            toggleButton.setPrefSize(WINSIZE_X, 50);
            rootPane.setBottom(toggleButton);
            System.out.println(Thread.currentThread());
        }
    }

    private class PauseButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gp.pause();
            toggleButton.setText("RESUME");
            toggleButton.setOnAction(new ResumeButtonHandler());
        }
    }

    private class ResumeButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            gp.requestFocus();
            Runnable task = new Runnable() {
                public void run() {
                    gp.start();
                }
            };
            Thread backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
            toggleButton.setText("PAUSE");
            toggleButton.setOnAction(new PauseButtonHandler());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}