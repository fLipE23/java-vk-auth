module vk {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.apache.logging.log4j;

    requires sdk;

    opens vk to javafx.fxml;
    exports vk;
}