package drk.graphics.game;
import drk.KarnaughLog;
import drk.graphics.*;
public class KarnaughShaders
{ 
	
/*
 *	
 *	if(dot(c1,c1) < dot(c2,c2))
 *	{
 *		NormalTransform[2] = normalize(c2);
 *		NormalTransform[0] = normalize(NormalTransform[1],NormalTransform[2]);
 *	}
 *	else
 *	{
 *		NormalTransform[0] = normalize(c1);
 *		NormalTransform[2] = normalize(NormalTransform[1],NormalTransform[0]); //should these be switched?
 *
 *	}
	



*/
	
	
	
	
	
	static final String LightingRenderVertex=

	"varying vec3 Binormal;"+
	"varying vec3 Normal;"+
	"varying vec3 Tangent;"+
	"varying vec3 Lpos;"+
	"varying vec3 Lpos2;"+
	"varying vec2 dist;"+
	"void main(void)"+"\n"+
	"{"+"\n"+
	"   Tangent=gl_MultiTexCoord1.xyz;"+"\n"+
    "   Normal=gl_Normal;"+"\n"+
    "   Binormal=cross(Normal,Tangent);"+"\n"+
	"   gl_TexCoord[0]=gl_MultiTexCoord0;"+
	//"   NormalTransform=transpose(NormalTransform);"+
	"   const vec3 Position = (gl_ModelViewMatrix*gl_Vertex).xyz; "+
	"   gl_Position = ftransform();"+"\n"+
	"   Lpos = gl_LightSource[0].position.xyz - Position.xyz;"+"\n"+
	"   Lpos2 = gl_LightSource[1].position.xyz - Position.xyz;"+"\n"+
	"   dist = vec2(length(Lpos),length(Lpos2));"+
	//"   Position = gl_Modelgl_Vertex.xyz;"+"\n"+
	"}"+"\n"+
	"";
	//fix this for point light cutoffs.
	static final String LightingRenderFragment=
		"uniform sampler2D texture,surface;\n" +
		"varying vec3 Binormal;"+
		"varying vec3 Normal;"+
		"varying vec3 Tangent;"+
		"varying vec3 Lpos;"+
		"varying vec3 Lpos2;"+
		"varying vec2 dist;"+
		"vec3 getNormal();"+
		"const vec3 latt=vec3(" +
		"gl_LightSource[0].constantAttenuation," +
		"gl_LightSource[0].linearAttenuation," +
		"gl_LightSource[0].quadraticAttenuation);"+
		
		"const vec3 latt2=vec3(" +
				"gl_LightSource[1].constantAttenuation," +
				"gl_LightSource[1].linearAttenuation," +
				"gl_LightSource[1].quadraticAttenuation);"+
		"void main (void)"+"\n"+
		"{"+"\n"+
		"    vec4 diffuse = texture2D(texture,gl_TexCoord[0].st);"+
		"    const vec3 N = getNormal();"+
		"    const vec3 LightDot= normalize(Lpos);"+
		"    const vec3 Light2Dot=normalize(Lpos2);"+
		"    const  vec3 distv = vec3(1,dist.x,dist.x*dist.x);"+
		"    const vec3  dist2v= vec3(1,dist.y,dist.y*dist.y);"+
		"    const float att=1.0/dot(latt,distv);"+		
		"    const float att2=1.0/dot(latt2,dist2v);"+
		"    const vec3 M = gl_LightSource[0].diffuse.rgb*max(dot(N,LightDot),0.0)*att;"+
		"    const vec3 M2 = gl_LightSource[1].diffuse.rgb*max(dot(N,Light2Dot),0.0)*att2;"+
		
		"    gl_FragColor=diffuse*vec4(M+M2,1.0);"+
	"	}"+"\n"+
	"   vec3 getNormal()"+
	"   {" +
	"    vec4 normalcolor = texture2D(surface,gl_TexCoord[0].st);\n" +
	"    const mat3 ntransform=gl_NormalMatrix*mat3(Binormal,Tangent,Normal);" +
	"    normalcolor.rgb/=normalcolor.a;"+
	"    normalcolor.rgb=normalize(normalcolor.rgb*2.0-1.0);"+
	"    return ntransform*normalcolor.rgb;"+
	"   }" +
	"";

	
	
/*	static final String LightingRenderFragment=
	"uniform sampler2D texture,surface;\n" +
	"varying mat3 NormalTransform;"+"\n"+
//	"varying vec3 v;"+"\n"+
	"void main (void)"+"\n"+
	"{"+"\n"+
	// "    outcolor = texture2D(surface,gl_TexCoord[0].st);\n" +
	"vec3 L = gl_LightSource[0].position.xyz - gl_FragCoord.xyz; "+"\n"+
	//"vec3 E = normalize(-gl_FragCoord.xyz); // we are in Eye Coordinates, so EyePos is (0,0,0)"+"\n"+
	//"vec3 R = normalize(-reflect(L,N)); "+"\n"+

//	calculate Ambient Term:
//"	vec4 Iamb = gl_LightSource[0].ambient;"+"\n"+

//	calculate Diffuse Term:
//"	vec4 Idiff =gl_LightSource[0].diffuse * max(dot(N,L), 0.0);"+"\n"+
//	 calculate Specular Term:
//"	vec4 Ispec = gl_FrontLightProduct[0].specular "+"\n"+
//"	              //    * pow(max(dot(R,E),0.0),0.3*gl_FrontMaterial.shininess);"+"\n"+

//	 write Total Color:
"	gl_FragColor = vec4((normalize(NormalTransform[1])+1.0)/2,1.0);"+"\n"+

"	}"+"\n"+
"";
*/		
		
		
		
		
		
		
		
		
		
		
/*		
		
		"uniform sampler2D texture,surface;\n" +
		"void main()\n" +
		"{\n" +
		"   vec4 outcolor;"+
	    "    outcolor = texture2D(texture,gl_TexCoord[0].st);\n" +
		"    outcolor = texture2D(surface,gl_TexCoord[0].st);\n" +
		
		//"    if(dot(gl_FragCoord.rgb,gl_FragCoord.rgb) > .5*.5)\n"+
		//"        outcolor*=2.0;\n"+
		"    gl_FragColor = vec4(normalize(outcolor.rgb),1.0);\n" +
		"}" +
		"";*/

	
	/*"void main()"+
	"{"+
		"gl_Position=ftransform();"+
///			"gl_TexCoord[0]=gl_MultiTexCoord0;"+
	"}"+
	"";*/
		
	
	

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
		out.addShaderSection(vert);
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
