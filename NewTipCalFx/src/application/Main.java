package application;

//import java.io.BufferedReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javafx.stage.Stage;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	private TextField amtTF;
	private TextField perctTF;
	private Label dInfo;
	private Label ddInfo;
	private Label dddInfo;

	NumberFormat currency = NumberFormat.getCurrencyInstance();

	@Override
	public void start(Stage Stage) throws Exception {
		Stage.setTitle("Tip Calculator");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(10);
		grid.setVgap(10);

		Scene scene = new Scene(grid, 450, 200);

		grid.add(new Label("Amount: "), 0, 0);
		amtTF = new TextField();
		grid.add(amtTF, 1, 0);

		grid.add(new Label("Percentage"), 0, 1);
		perctTF = new TextField();
		grid.add(perctTF, 1, 1);

		dInfo = new Label();
		dInfo.setWrapText(true);
		dInfo.setPrefWidth(400);
		dInfo.setWrapText(true);
		grid.add(dInfo, 0, 2, 5, 3);
		
		ddInfo = new Label();
		grid.add(ddInfo, 0, 3, 5,3);
		
		dddInfo = new Label();
		grid.add(dddInfo, 0, 4, 5, 3);

		Button btn = new Button();
		Button btx = new Button();
		Button btt = new Button();

		btn.setText("Calculate");
		btt.setText("Exit");
		btx.setText("Clear");
		btn.setOnAction(event -> calculateButtonClicked());
		btt.setOnAction(event -> exitButtonClicked());
		btx.setOnAction(even -> clearButtonClicked());

		HBox buttonBox = new HBox(15);
		buttonBox.getChildren().add(btn);
		buttonBox.getChildren().add(btx);
		buttonBox.getChildren().add(btt);

		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		grid.add(buttonBox, 0, 7, 2, 1);

		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage.setScene(scene);

		Stage.show();
	}

	private void calculateButtonClicked() {

		String tipAmount = amtTF.getText();
		String percentage = perctTF.getText().toString().trim();

		System.out.println("Percent value from form: " + percentage);
		try {
			if (!(percentage.isEmpty())) {
				BigDecimal amt = new BigDecimal(tipAmount);

				BigDecimal perctge = new BigDecimal(percentage).divide(new BigDecimal(100));
				BigDecimal tip = amt.multiply(perctge);
				dInfo.setText("Your tip on " + currency.format(amt) + " " + "is" + " " + currency.format(tip));

			} else if (percentage.isEmpty()) {

				BigDecimal tip15 = new BigDecimal(tipAmount).multiply(new BigDecimal(0.15));
				BigDecimal tip20 = new BigDecimal(tipAmount).multiply(new BigDecimal(0.20));
				BigDecimal tip25 = new BigDecimal(tipAmount).multiply(new BigDecimal(0.25));
				dInfo.setText("Cost of tip options: " + " " + "(15%)== " + currency.format(tip15));
				ddInfo.setText("\t\t\t\t " +"(20%)== " + ""+ currency.format(tip20) );	
				dddInfo.setText("\t\t\t\t " +"(25%)== " + currency.format(tip25)); 
			}

		} catch (NumberFormatException e) {
			dInfo.setText("Enter amount only for default values or enter a valid percentage number");

		}
	}

	private void exitButtonClicked() {
		System.exit(0);
	}

	private void clearButtonClicked() {
		amtTF.clear();
		perctTF.clear();
		dInfo.setText(null);
		ddInfo.setText(null);
		dddInfo.setText(null);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
