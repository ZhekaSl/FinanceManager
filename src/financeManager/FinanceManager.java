package financeManager;

import financeManager.exception.ModelException;
import financeManager.settings.Format;
import financeManager.settings.Settings;
import financeManager.settings.Text;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class FinanceManager {
    public static void main(String[] args) throws ModelException {
        init();
        System.out.println(Format.dateMonth(new Date()));

    }

    private static void init() {
        try {
            Settings.init();

            Text.init();
            GraphicsEnvironment robotoLight = GraphicsEnvironment.getLocalGraphicsEnvironment();
            robotoLight.registerFont(Font.createFont(Font.TRUETYPE_FONT, Settings.FONT_ROBOTO_LIGHT));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }


    }


}
