package drk.circuit;
public class Inverter extends Wire
{
	public Inverter(OutputSystem os)
	{
		super(os);
	}
	public boolean evaluate()
	{
		return !(input.evaluate());
	}
}
