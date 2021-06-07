package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.render.ImageRenderer;
import dev.kolja.gnengine.render.Framebuffer;
import dev.kolja.gnengine.render.Renderable;

/**
 * Image component which displays an image. If width and height are not according to the image's aspect ration,
 * it gets stretched or squished.
 */
public class Image extends GUIComponent {

    private Renderable renderer;
    private String textureName;

    /**
     * Creates a new image and tries to find it's texture via texture name.
     * @param name name of the image texture
     * @param hitBox HitBox of the image
     */
    public Image(String name, HitBox hitBox) {
        super();
        this.setHitBox(hitBox);
        this.textureName = name;
        this.renderer = new ImageRenderer(this);
    }

    /**
     * Creates a new image from an ImageBuffer texture.
     * @param texture ImageBuffer to be used as texture
     * @param hitBox HitBox of the image
     */
    public Image(Framebuffer texture, HitBox hitBox) {
        super();
        this.setHitBox(hitBox);
        this.textureName = null;
        this.renderer = new ImageRenderer(this, texture);
    }

    /**
     * Creates a new image from an ImageBuffer texture.
     * @param texture ImageBuffer to be used as texture
     */
    public Image(Framebuffer texture) {
        this(texture, new HitBox(0, 0, 400, 400));
    }

    /**
     * Creates a new image and tries to find it's texture via a texture name.
     * @param textureName name of the image texture
     */
    public Image(String textureName) {
        this(textureName, new HitBox(0, 0, 400, 400));
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void tick() {

    }

    /**
     * Change texture
     * @param textureName name of the new texture
     */
    public void changeTexture(String textureName) {
        this.textureName = textureName;
        ((ImageRenderer) renderer).notifyTextureChange();
    }

    /**
     * Returns current name of the texture
     * @return current texture name
     */
    public String getTextureName() {
        return textureName;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
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
    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
        xPos = (int) hitBox.x();
        yPos = (int) hitBox.y();
        width = (int) hitBox.width();
        height = (int) hitBox.height();
    }
}
