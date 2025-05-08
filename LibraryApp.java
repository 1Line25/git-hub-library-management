@Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Library System");

    // Layout containers
    VBox root = new VBox(10);
    root.setPadding(new Insets(15));

    // Instructions
    Label instruction = new Label("Welcome to My Application!");
    Label enterAction = new Label("Enter an action number [0-9]:");

    // Input
    TextField actionInput = new TextField();
    Button executeButton = new Button("Execute");
    Label errorLabel = new Label();
    errorLabel.setStyle("-fx-text-fill: red;");

    // Action list
    ListView<String> actionsList = new ListView<>();
    actionsList.getItems().addAll(
            "[0] Exit",
            "[1] Add Document",
            "[2] Remove Document",
            "[3] Update Document",
            "[4] Find Document",
            "[5] Display Document",
            "[6] Add User",
            "[7] Borrow Document",
            "[8] Return Document",
            "[9] Display User Info"
    );
    actionsList.setMaxHeight(200);

    // Result Table (initial empty setup)
    TableView<String> resultTable = new TableView<>();
    resultTable.setPlaceholder(new Label("No data to display."));

    // Logic for executing commands
    executeButton.setOnAction(e -> {
        String input = actionInput.getText().trim();
        if (!input.matches("[0-9]")) {
            errorLabel.setText("Action is not supported");
        } else {
            errorLabel.setText("");
            int action = Integer.parseInt(input);
            System.out.println("Selected action: " + action);
            // TODO: Handle actual logic for each case
        }
    });

    // Build the layout
    root.getChildren().addAll(instruction, actionsList, enterAction, actionInput, executeButton, errorLabel, resultTable);

    Scene scene = new Scene(root, 600, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
}