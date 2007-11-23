package drk.graphics.game;
import drk.KarnaughLog;
import drk.graphics.*;
public class KarnaughShaders
{
	static final String LightingRenderFragment=
		"uniform sampler2D texture,surface;\n" +
		"void main()\n" +
		"{\n" +
		"   vec4 outcolor;"+
	//    "    outcolor = texture2D(texture,gl_TexCoord[0].st);\n" +
		"    outcolor = texture2D(surface,gl_TexCoord[0].st);\n" +
		
		//"    if(dot(gl_FragCoord.rgb,gl_FragCoord.rgb) > .5*.5)\n"+
		//"        outcolor*=2.0;\n"+
		"    gl_FragColor = vec4(outcolor.aaa,1.0);\n" +
		"}" +
		"";
	
	static final String LightingRenderVertex=
		"void main()"+
		"{"+
			"gl_Position=ftransform();"+
///			"gl_TexCoord[0]=gl_MultiTexCoord0;"+
		"}"+
		"";
		
	
	

		/* Compute the diffuse, ambient and globalAmbient term
	/*tmp1 = input_image(x,y) - input_image(x+1,y+1)
	tmp2 = input_image(x+1,y) - input_image(x,y+1)
	output_image(x,y) = absolute_value(tmp1) + absolute_value(tmp2)*/
	/*
	static final String FinalRenderFragment=
		"uniform sampler2D texture;\n" +
		"uniform float t;\n" +
		"void main()\n" +
		"{\n" +
		"    float blue=abs(sin((t+gl_FragCoord.y)*0.003));\n"+
		"    float hardb=step(abs(sin((t*0.2+gl_FragCoord.x)*0.02)),0.2)*blue*blue;\n"+
		"    vec2 coffd=vec2(0.0,1.0/256.0);\n"+
		"    vec4 tmp1 = texture2D(texture,gl_TexCoord[0].st) - texture2D(texture,gl_TexCoord[0].st+coffd.xy+coffd.yx);\n" +
		"    vec4 tmp2 = texture2D(texture,gl_TexCoord[0].st+coffd.yx) - texture2D(texture,gl_TexCoord[0].st+coffd);\n" +
		"    vec4 outcolor = abs(tmp1) + abs(tmp2);\n"+
		"    outcolor =outcolor*vec4(1.0-hardb*0.5,1.0-hardb*0.5,1.0+blue+hardb,1.0);\n" +
		"    gl_FragColor=outcolor;"+
		"}" +
		"";*/
	
	public static GLSLShader getOutputShader()
	{
		GLSLShader out=new GLSLShader();
		GLSLShaderSection vert=new GLSLShaderSection(LightingRenderVertex);
		vert.setVertexShader(true);
		GLSLShaderSection frag=new GLSLShaderSection(LightingRenderFragment);
		out.addShaderSection(frag);
	//	out.
		
		try
		{
			out.compileShader();
			out.linkShader();
		}
		catch(Exception e)
		{
		//	System.err.println("Shader error");
			KarnaughLog.log(e);
		}
		
		return out;
	}

}
