package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.render.TextRenderer;
import dev.kolja.gnengine.gui.text.CharInfo;
import dev.kolja.gnengine.gui.text.Font;
import dev.kolja.gnengine.gui.text.FontFactory;
import dev.kolja.gnengine.gui.text.TextCharacter;
import dev.kolja.gnengine.input.KeyboardInputHandler;
import dev.kolja.gnengine.input.MouseButton;
import dev.kolja.gnengine.input.MouseButtonAction;
import dev.kolja.gnengine.render.Renderable;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class TextField extends GUIComponent implements IText{

    private Renderable renderer;
    private Container container;
    private Font font;
    private Color textColor;

    private Color normalColor;
    private Color hoverColor;

    private boolean showCursor;
    private int cursorTimer;

    private String text;
    private boolean isHovered;
    private boolean isSelected;

    public TextField(Container container) {
        this.textColor = new Color(0.0f, 0.0f, 0.0f);
        this.font = FontFactory.retrieveFont("font");
        this.container = container;
        this.normalColor = container.getColor();
        this.hoverColor = new Color(container.getColor().asVector().add(-0.2f, -0.2f, -0.2f));
        this.setHitBox(container.getHitBox());
        this.renderer = new TextRenderer(this);
        this.text = "";
    }

    @Override
    public void render() {
        container.render();
        renderer.render();
    }

    @Override
    public void tick() {
        boolean wasHovered = isHovered;
        isHovered = checkHovering();
        if(!isHovered && wasHovered) {
            this.container.setColor(normalColor);
        } else if (isHovered && !wasHovered && !isSelected) {
            this.container.setColor(hoverColor);
        } else if(isHovered) {
            MouseButtonAction action = mouseInputHandler.getButtonAction(MouseButton.LEFT_MOUSE_BUTTON);
            if(action == MouseButtonAction.PRESSED) {
                this.container.setColor(normalColor);
                isSelected = true;
                showCursor = true;
                KeyboardInputHandler.getInstance().registerCharCallback(this.toString(), this::onCharTyped);
                KeyboardInputHandler.getInstance().registerKeyCallback(this.toString(), KeyboardInputHandler.Key.BACKSPACE, this::onDelete);
                KeyboardInputHandler.getInstance().registerKeyCallback(this.toString(), KeyboardInputHandler.Key.ENTER, this::onEnter);
            }
        } else if(isSelected) {
            if(showCursor && cursorTimer < 40) {
                cursorTimer++;
            } else if(showCursor) {
                showCursor = false;
            } else if(cursorTimer > 0) {
                cursorTimer--;
            } else {
                showCursor = true;
            }
        }
    }

    private void onEnter() {
        isSelected = false;
        showCursor = false;
        KeyboardInputHandler.getInstance().unregisterCharCallback(this.toString());
        KeyboardInputHandler.getInstance().unregisterKeyCallback(this.toString(), KeyboardInputHandler.Key.BACKSPACE);
        KeyboardInputHandler.getInstance().unregisterKeyCallback(this.toString(), KeyboardInputHandler.Key.ENTER);
    }

    private void onDelete() {
        if(text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    private void onCharTyped(char code) {
        if(font.getCharInfo(code) != null) {
            text += code;
        }
    }

    private boolean checkHovering() {
        return hitBox.isHit(mouseInputHandler.getXPos(), mouseInputHandler.getYPos());
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean includesCursor() {
        return showCursor;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public Color getTextColor() {
        return textColor;
    }

    @Override
    public HitBox getHitBox() {
        return container.getHitBox();
    }

    @Override
    public TextCharacter[] getCharHitBoxes() {
        int length = showCursor ? text.length() + 1 : text.length();
        TextCharacter[] chars = new TextCharacter[length];
        int nextX = 4;
        for(int i = 0; i < chars.length; i++) {
            if(showCursor && i == length - 1) {
                chars[i] = new TextCharacter(nextX, 0, 'I', font);
                continue;
            }
            char code = text.charAt(i);
            CharInfo info = font.getCharInfo(code);
            chars[i] = new TextCharacter(nextX, 0, code, font);
            chars[i].setTextureCoords(new Vector2f(info.getX(), info.getY()));
            nextX += info.getWidth();
        }
        return chars;
    }

    @Override
    public void setXPos(int xPos) {

    }

    @Override
    public void setYPos(int yPos) {

    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public void setHitBox(HitBox hitBox) {
        container.setHitBox(hitBox);
        this.hitBox = hitBox;
        this.xPos = (int) hitBox.x();
        this.yPos = (int) hitBox.y();
        this.width = (int) hitBox.width();
        this.height = (int) hitBox.height();
    }

    public Color getColor() {
        return container.getColor();
    }

    public void setColor(Color color) {
        container.setColor(color);
    }
}
