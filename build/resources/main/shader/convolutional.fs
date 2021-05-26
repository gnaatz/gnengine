#version 450
out vec4 FragColor;

uniform float kernel[9];
uniform sampler2D iTexture;

in vec2 texCoords;

void main()
{
    ivec2 texSize = textureSize(iTexture, 0);
    vec2 stepSize = 1.0 / vec2(float(texSize.x), float(texSize.y));
    vec4 color = vec4(0.0);
    color += texture(iTexture, vec2(
                texCoords.x - stepSize.x,
                texCoords.y - stepSize.y)
            ) * kernel[0];
    color += texture(iTexture, vec2(
                texCoords.x,
                texCoords.y - stepSize.y)
            ) * kernel[1];
    color += texture(iTexture, vec2(
                texCoords.x + stepSize.x,
                texCoords.y - stepSize.y)
            ) * kernel[2];
    color += texture(iTexture, vec2(
                texCoords.x - stepSize.x,
                texCoords.y)
            ) * kernel[3];
    color += texture(iTexture, vec2(
                texCoords.x - stepSize.x,
                texCoords.y)
            ) * kernel[4];
    color += texture(iTexture, vec2(
                texCoords.x + stepSize.x,
                texCoords.y)
            ) * kernel[5];
    color += texture(iTexture, vec2(
                texCoords.x - stepSize.x,
                texCoords.y + stepSize.y)
            ) * kernel[6];
    color += texture(iTexture, vec2(
                texCoords.x,
                texCoords.y + stepSize.y)
            ) * kernel[7];
    color += texture(iTexture, vec2(
                texCoords.x + stepSize.x,
                texCoords.y + stepSize.y)
            ) * kernel[8];
    color.a = 1.0;
    FragColor = color;
    //FragColor = texture(iTexture, texCoords);
}