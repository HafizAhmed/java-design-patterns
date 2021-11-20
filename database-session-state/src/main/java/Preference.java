public class Preference {
    private int fontSize;
    private String fontColor;

    public Preference(int fontSize, String fontColor) {
        this.fontSize = fontSize;
        this.fontColor = fontColor;
    }
    public Preference() {

    }

    public int getFontSize() {
        return fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }



}
