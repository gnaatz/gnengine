package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.gui.animator.Animator;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.text.FontFactory;
import dev.kolja.gnengine.gui.text.TextComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Button which displays text.
 */
public class TextButton extends GUIComponent {

    private final Button button;
    private final TextComponent text;

    /**
     * Creates a new TextButton from a Button and a String.
     * @param button button which is used as basis
     * @param text text to be displayed on button
     */
    public TextButton(Button button, String text) {
        this.button = button;
        button.setPadding(5);
        Animator animator = button.getAnimator();
        this.text = new TextComponent(text, FontFactory.retrieveFont("font"));
        this.text.setHitBox(new HitBox(button.getXPos() + button.getPadding(), button.getYPos() + button.getPadding(), 1, 1));
        try {
            Constructor<?> ctor = animator.getClass().getConstructor(GUIComponent.class);
            Animator animator1 = (Animator) ctor.newInstance(this.text);
            button.hookAnimator(animator1);
        } catch (NoSuchMethodException e) {
            Engine.LOGGER.error("No constructor for class of animator found");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            Engine.LOGGER.error("Error while creating animator for TextButton");
            Engine.LOGGER.error(e.getMessage());
        }
    }

    /**
     * Wrapper for the buttons setButtonCallback
     * @param callbackFn callback function when clicked
     */
    public void setButtonCallback(ButtonCallbackFn callbackFn) {
        button.setButtonCallback(callbackFn);
    }

    @Override
    public void render() {
        button.render();
        text.render();
    }

    @Override
    public void tick() {

    }


    public Color getColor() {
        return button.color;
    }

    @Override
    public HitBox getHitBox() {
        return button.getHitBox();
    }

    @Override
    public void setXPos(int xPos) {
        button.setXPos(xPos);
        text.setXPos(xPos + button.getPadding());
        this.xPos = xPos;
    }

    @Override
    public void setYPos(int yPos) {
        button.setYPos(yPos);
        text.setYPos(yPos + button.getPadding());
        this.yPos = yPos;
    }

    @Override
    public void setWidth(int width) {
        button.setWidth(width);
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        button.setHeight(height);
        this.height = height;
    }

    public void setColor(Color color) {
        button.color = color;
    }

    @Override
    public void setHitBox(HitBox hitBox) {
        button.setHitBox(hitBox);
        this.hitBox = hitBox;
        text.setHitBox(new HitBox((int) hitBox.x() + button.getPadding(), (int) hitBox.y() + button.getPadding(), 1, 1));
    }
}
