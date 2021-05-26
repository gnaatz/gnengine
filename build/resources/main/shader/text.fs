#version 450
out vec4 FragColor;

uniform sampler2D iTexture;

in vec2 texCoords;
in vec3 fColor;

void main()
{
    FragColor = texture(iTexture, texCoords);
}