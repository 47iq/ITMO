package client.commands;

import common.Response;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public interface MessagingCommand {
    Response execute(Response response);

    default ResourceBundle getLocalResourceBundle() {
        ResourceBundle bundle = null;
        try {
            File file = new File("/");
            URL[] urls = {file.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            try {
                Locale locale = Locale.getDefault();
                bundle = ResourceBundle.getBundle("messages", locale, loader);
            } catch (Exception e) {
                Locale locale = new Locale("en", "EN");
                bundle = ResourceBundle.getBundle("messages", locale, loader);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return bundle;
    }
}
