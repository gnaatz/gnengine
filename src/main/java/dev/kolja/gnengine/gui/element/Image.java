package dev.kolja.gnengine.gui.element;

import dev.kolja.gnengine.gui.HitBox;
import dev.kolja.gnengine.gui.render.ImageRenderer;
import dev.kolja.gnengine.render.Framebuffer;
import dev.kolja.gnengine.render.Renderable;

public class Image extends GUIComponent {

    private Renderable renderer;
    private String textureName;

    public Image(String name, HitBox hitBox) {
        super();
        this.setHitBox(hitBox);
        this.textureName = name;
        this.renderer = new ImageRenderer(this);
    }

    public Image(Framebuffer texture, HitBox hitBox) {
        super();
        this.setHitBox(hitBox);
        this.textureName = null;
        this.renderer = new ImageRenderer(this, texture);
    }

    public Image(Framebuffer texture) {
        this(texture, new HitBox(0, 0, 400, 400));
    }

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

    public void changeTexture(String textureName) {
        this.textureName = textureName;
        ((ImageRenderer) renderer).notifyTextureChange();
    }

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
