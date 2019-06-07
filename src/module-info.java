module calc {
	requires javafx.fxml;
    requires javafx.controls;
    requires jdk.jshell;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    
    opens calc to javafx.fxml;
    
    exports calc;
}

