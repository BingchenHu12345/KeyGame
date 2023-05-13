module com.example.keygame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.keygame to javafx.fxml;
    exports com.example.keygame;
}