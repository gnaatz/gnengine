package dev.kolja.gnengine.gui.text;

/**
 * Contains positional information about a char of a font.
 */
public class CharInfo {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int xOffset;
    private final int yOffset;
    private final int xAdvance;
    private final int page;
    private final int channel;

    CharInfo(int x, int y, int width, int height, int xOffset, int yOffset, int xAdvance, int page, int channel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xAdvance = xAdvance;
        this.page = page;
        this.channel = channel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public int getxAdvance() {
        return xAdvance;
    }

    public int getPage() {
        return page;
    }

    public int getChannel() {
        return channel;
    }
}
