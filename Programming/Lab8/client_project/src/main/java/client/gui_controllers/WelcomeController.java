package client.gui_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeController implements Controller {
    public Button logInButton;
    public Button registerButton;
    public ChoiceBox<String> language;
    public ImageView imgView;
    public Button exitButton;
    public Text addText;

    private ControllerContext context;
    private ControlManager controlManager;
    private ResourceBundle bundle;

    @Override
    public void initialize(ControllerContext context) {
        this.context = context;
        controlManager = context.getControlManager();
        bundle = context.getBundle();
        localize();
        ObservableList<String> types = FXCollections.observableArrayList("English", "Russian", "Turkish", "French", "Spanish(Honduras)");
        language.setItems(types);
        if (bundle != null)
            switch (bundle.getLocale().getLanguage()) {
                case "ru" -> language.setValue("Russian");
                case "tr" -> language.setValue("Turkish");
                case "fr" -> language.setValue("French");
                case "es" -> language.setValue("Spanish(Honduras)");
                default -> language.setValue("English");
            }
        else
            language.setValue("English");
        language.setOnAction(actionEvent -> {
            context.setBundle(getLocalBundle());
            reload();
        });
        logInButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) logInButton.getScene().getWindow(), "login.fxml", this);
        });
        registerButton.setOnAction(actionEvent -> {
            controlManager.showScene((Stage) logInButton.getScene().getWindow(), "register.fxml", this);
        });
        exitButton.setOnAction(actionEvent -> System.exit(0));
    }

    private ResourceBundle getLocalBundle() {
        try {
            File file = new File("/");
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            Locale locale;
            switch (language.getValue()) {
                case "English" -> {
                    locale = new Locale("en", "EN");
                    Locale.setDefault(locale);
                }
                case "Russian" -> {
                    locale = new Locale("ru", "RU");
                    Locale.setDefault(locale);
                }
                case "Turkish" -> {
                    locale = new Locale("tr", "TR");
                    Locale.setDefault(locale);
                }
                case "French" -> {
                    locale = new Locale("fr", "FR");
                    Locale.setDefault(locale);
                }
                case "Spanish(Honduras)" -> {
                    locale = new Locale("es", "HN");
                    Locale.setDefault(locale);
                }
                default -> throw new RuntimeException();
            }
            return ResourceBundle.getBundle("messages", locale, loader);
        } catch (Exception e) {
            LogManager.getLogger().error("Error while getting resource bundle");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    private void reload() {
        controlManager.showScene((Stage) logInButton.getScene().getWindow(), "welcome.fxml", this);
    }

    private void localize() {
        logInButton.setText(bundle.getString("LOG_IN"));
        registerButton.setText(bundle.getString("REGISTER"));
        exitButton.setText(bundle.getString("EXIT"));
    }

    @Override
    public void setContext(ControllerContext context) {
        this.context = context;
    }

    @Override
    public ControllerContext getContext() {
        System.out.println(context);
        return context;
    }
}
