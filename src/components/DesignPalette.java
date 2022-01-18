package components;

import java.awt.*;

/**
 * Stores colors and fonts for objects in the visual editor
 * @author Filipp Dokienko
 */

public class DesignPalette {
    // ~~ COLORS ~~ //

    // PANELS //
    public static Color BACKGROUND_EDITOR = new Color(233,233,233);;

    // LUDEME BLOCK //
        // fonts
    public static Color FONT_LUDEME_INPUTS_COLOR = new Color(110,110,110);
    public static Color FONT_LUDEME_TITLE_COLOR = new Color(110,110,110);
        // backgrounds
    public static Color BACKGROUND_LUDEME_BODY = new Color(241,241,241);
    public static Color BACKGROUND_LUDEME_TITLE = new Color(230,230,230);
        // fills
    public static Color LUDEME_CONNECTION_POINT = new Color(112,112,112);
    public static Color LUDEME_CONNECTION_EDGE = new Color(112,112,112);

    // ~~ FONTS ~~ //

    // LUDEME BLOCK //
    public static final Font LUDEME_TITLE_FONT = new Font(Font.SANS_SERIF, Font.BOLD,  14);
    public static final Font LUDEME_INPUT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

}