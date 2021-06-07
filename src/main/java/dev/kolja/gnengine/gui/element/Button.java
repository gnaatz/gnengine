package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.color.Color;
import dev.kolja.gnengine.gui.animator.Animator;
import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.animator.SlideRightAnimator;
import dev.kolja.gnengine.input.MouseButton;
import dev.kolja.gnengine.input.MouseButtonAction;
import dev.kolja.gnengine.gui.render.ButtonRenderer;
import org.joml.Vector3f;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple Button implementation of a solid color button.
 * Can be clicked or hovered. The standard width, height for a button are 200px, 40px
 */
public class Button extends ParentComponent implements IColoredComponent {

    private boolean isClicked;
    private boolean isHovered;
    private ButtonCallbackFn callbackFn;
    private Animator animator;
    private Color hoverColor;
    private List<Animator> animatorList;

    /**
     * Creates a new button at the parents location
     * @param color color of the button
     * @param parent parent of the button, used for positioning
     */
    public Button(Color color, ParentComponent parent) {
        this(
                parent.xPos + parent.padding,
                parent.yPos + parent.padding,
                parent.width - 2 * parent.padding,
                parent.height - 2 * parent.padding,
                color,
                new Color(color.asVector().mul(0.8f, new Vector3f())),
                parent,
                null
        );
    }

    /**
     * Creates a new button at 0,0 with standard width and height
     */
    public Button() {
        this(0, 0, 200, 40);
    }

    /**
     * Creates a button at x, y with standard width and height
     * @param xPos x value of the button position
     * @param yPos y value of the button position
     */
    public Button(int xPos, int yPos) {
        this(xPos, yPos, 200,40);
    }

    /**
     * Creates a new button with slide right animation.
     * @param xPos x value of the button position
     * @param yPos y value of the button position
     * @param width width of the button
     * @param height height of the button
     * @param color color of the button
     * @param hoverColor color of the button while it is hovered
     * @param parent parent of the button
     * @param callbackFn function callback for when the button is clicked
     */
    public Button(int xPos, int yPos, int width, int height, Color color, Color hoverColor, GUIComponent parent, ButtonCallbackFn callbackFn) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.hitBox = new HitBox(xPos, yPos, width, height);
        this.color = color;
        this.hoverColor = hoverColor;
        this.callbackFn = callbackFn;
        this.renderer = new ButtonRenderer(this);
        this.animator = new SlideRightAnimator(this);
        this.animatorList = new LinkedList<>();
        hookAnimator(this.animator);
    }

    /**
     * Creates a new button with slide right animation without parent.
     * @param xPos x value of the button position
     * @param yPos y value of the button position
     * @param width width of the button
     * @param height height of the button
     * @param color color of the button
     * @param callbackFn function callback for when the button is clicked
     */
    public Button(int xPos, int yPos, int width, int height, Color color, ButtonCallbackFn callbackFn) {
        this(xPos, yPos, width, height, color, new Color(color.asVector().mul(0.8f, new Vector3f())), null, callbackFn);
    }

    /**
     * Creates a new button.
     * @param xPos x value of the button position
     * @param yPos y value of the button position
     * @param width width of the button
     * @param height height of the button
     * @param color color of the button
     */
    public Button(int xPos, int yPos, int width, int height, Color color) {
        this(xPos, yPos, width, height, color,null);
    }

    /**
     * Creates a new button with color rgb(0.2, 0.7, 0.4)
     * @param xPos x value of the button position
     * @param yPos y value of the button position
     * @param width width of the button
     * @param height height of the button
     */
    public Button(int xPos, int yPos, int width, int height) {
        this(xPos, yPos, width, height, new Color(0.2f, 0.7f, 0.4f));
    }

    /**
     * Creates a button at 0,0 with standard width, height
     * @param color color of the button
     */
    public Button(Color color) {
        this();
        this.color = color;
    }

    /**
     * Hooks a new animator to the button.
     * @param animator animator for the button
     */
    public void hookAnimator(Animator animator) {
        animatorList.add(animator);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void tick() {
        boolean wasHovered = isHovered;
        isHovered = checkHovering();
        if(!isHovered && wasHovered) {
            animatorList.forEach(Animator::stopAnimation);
        } else if (isHovered && !wasHovered) {
            animatorList.forEach(Animator::startAnimation);
        } else if(isHovered) {
            MouseButtonAction action = mouseInputHandler.getButtonAction(MouseButton.LEFT_MOUSE_BUTTON);
            if(action == MouseButtonAction.PRESSED) {
                isClicked = true;
            } else if(action == MouseButtonAction.RELEASED) {
                isClicked = false;
                if(callbackFn != null)
                    callbackFn.callback(this);
            }
        }
        animatorList.forEach(Animator::animate);
    }

    /**
     * Sets a new callback function for when the button is clicked.
     * @param callbackFn callback function for when the button is clicked
     */
    public void setButtonCallback(ButtonCallbackFn callbackFn) {
        this.callbackFn = callbackFn;
    }

    private boolean checkHovering() {
        return hitBox.isHit(mouseInputHandler.getXPos(), mouseInputHandler.getYPos());
    }

    /**
     * Main animator of the button
     * @return main animator of the button
     */
    public Animator getAnimator() {
        return animator;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void setPadding(int padding) {
        this.padding = padding;
        // TODO: update children
    }

    @Override
    public void setXPos(int xPos) {
        this.xPos = xPos;
        hitBox.updateX(xPos);
    }

    @Override
    public void setYPos(int yPos) {
        this.yPos = yPos;
        hitBox.updateY(yPos);
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        hitBox.updateWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        hitBox.updateHeight(height);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setHitBox(HitBox hitBox) {
        xPos = (int) hitBox.x();
        yPos = (int) hitBox.y();
        width = (int) hitBox.width();
        height = (int) hitBox.height();
        this.hitBox = hitBox;
    }

    @Override
    int getPadding() {
        return padding;
    }

    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Returns true if the mouse is hovered over this button.
     * @return true if button is hovered
     */
    public boolean isHovering() {
        return isHovered;
    }

    /**
     * Returns true if the button is clicked in this frame
     * @return true if button is clicked in this frame
     */
    public boolean isClicked() {
        return isClicked;
    }

    /**
     * Returns the color of the button if it is hovered over.
     * @return hover color of the button
     */
    public Color getHoverColor() {
        return hoverColor;
    }
}
