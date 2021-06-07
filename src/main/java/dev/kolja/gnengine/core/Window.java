package dev.kolja.gnengine.core;

import dev.kolja.gnengine.color.Color;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;

/**
 * Contains the GL handle of a window. Also provides wrappers for the window specific gl and glfw calls. Should probably
 * only used by Engine's window management.
 */
public class Window {

    private final long handle;

    Window(String title, int width, int height) {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);

        handle = glfwCreateWindow(width, height, title, 0, 0);
        if(handle == 0) {
            Engine.LOGGER.error("GLFW could not create window");
            return;
        }

        glfwMakeContextCurrent(handle);
        GL.createCapabilities();
        glfwSwapInterval(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * GL handle of the window - needed for direct GL calls regarding this window
     * @return GL handle of the window
     */
    public long getGLHandle() {
        return handle;
    }

    /**
     * Set's the window's clear color (glClearColor)
     * @param color new background color
     */
    public void setBackgroundColor(Color color) {
        glClearColor(color.r(), color.g(), color.b(), 1.0f);
    }

    /**
     * Polls key and mouse events. Should be used in the main loop, but only once.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Swaps the screen buffers. Use once in render loop - or main loop if preferred.
     */
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    /**
     * Closes this window.
     */
    public void close() {
        glfwDestroyWindow(handle);
    }
}
