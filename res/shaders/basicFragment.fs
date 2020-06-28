#version 130

in vec2 textCoord;

out vec4 fragColor;

uniform vec3 color;
uniform sampler2D sampler;

void main()
{		
	vec4 textureColor = vec4( color, 1 );
	
	if (textureColor != vec4(0, 0, 0, 0))
		textureColor *= texture(sampler, textCoord.xy); 

	fragColor = textureColor; 
}
