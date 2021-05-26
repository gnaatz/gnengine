package dev.kolja.gnengine.model;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class TexturedModel extends Model {

    private static final float[] vertices = {
            0.0f, 1.0f, 0.0f, 0.0f, 1.0f,  // top left
            0.0f, 0.0f, 0.0f, 0.0f, 0.0f, // bottom left
            1.0f, 0.0f, 0.0f, 1.0f, 0.0f, // bottom right
            1.0f, 1.0f, 0.0f, 1.0f, 1.0f // top right
    };

    private static final int[] indices = {
            0, 1, 3,
            1, 2, 3
    };

    static void create() {
        new TexturedModel();

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * 4, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * 4, 3 * 4);
        glEnableVertexAttribArray(1);
    }

    private TexturedModel() {
        super(vertices, indices);
        ModelFactory.register(TexturedModel.class, this);
    }

}
