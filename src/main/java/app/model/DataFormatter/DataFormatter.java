package app.model.DataFormatter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DataFormatter {

    private ResourceBundle resourceBundle;
    private NumberFormat currencyFormatter;

    //Constructor
    public DataFormatter(String country){
        Locale locale = this.getLocale(country);
        this.resourceBundle = ResourceBundle.getBundle("MessagesBundle", locale);
        this.currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    public NumberFormat getCurrencyFormatter() {
        return this.currencyFormatter;
    }

    private Locale getLocale(String country){

        Locale currentLocale;

        switch (country) {
            case "AR":
                currentLocale = new Locale("es", country);
                break;

            case "US":
                currentLocale = new Locale("en", country);
                break;

            default:
                currentLocale = new Locale("es", "AR");
        }

        return currentLocale;
    }
}
