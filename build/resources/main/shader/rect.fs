#version 450
out vec4 FragColor;

in vec3 oColor;
in vec3 fragPos;

void main()
{
    FragColor = vec4(oColor, 1.0f);
}