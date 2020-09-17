package compsci;
import java.util.Scanner;


public class Unit1_project 
{
	public static void main(String []args) 
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the mass of the water in grams");
		double mass = reader.nextDouble();
		
		System.out.println("Enter the starting temperature of water in degree CELSIUS");
		double initialtemp = reader.nextDouble();
		if ( initialtemp < -273.14)
			initialtemp = -273.14;
		
		System.out.println("Enter the ending temperature. ");
		double finaltemp = reader.nextDouble(); 
		if (finaltemp < -273.14)
			finaltemp = 273.14;
		// DETERMINING THE PHASE OF WATER
		String initialphase = "Liquid";
		
		if (initialtemp < 0)
			initialphase = "Solid"; 
		if (initialtemp > 100)
			initialphase = "Vapor";
		
		String finalphase = "Liquid";
		
		if (finaltemp < 0)
			finalphase = "Solid"; 
		if (finaltemp > 100)
			finalphase = "Vapor";
		
		boolean endothermic = false; 
		if (finaltemp > initialtemp )
			endothermic = true;
			
			
			
		System.out.println("Mass: "+ mass+ "g");
		System.out.println("Initial Temperature: "+ initialtemp + "C" + "  (" + initialphase + ")");
		System.out.println("Final Temperature: " + finaltemp + "C" + "  (" + finalphase + ")\n");
		
		
		double heatEnergy = 0;
		if (initialphase.equals("Solid"))
		{
			heatEnergy += tempChangeSolid(mass, initialtemp, finaltemp, finalphase, endothermic );
			if(!finalphase.equals("Solid"))
			{
				heatEnergy += fusion(mass, endothermic);
				heatEnergy += tempChangeLiquid(mass,0, finaltemp, finalphase, endothermic);
			}
			if (finalphase.equals("Vapor"))
			{
				heatEnergy += vaporization(mass, endothermic); 
				heatEnergy += tempChangeGas(mass,100, finaltemp, finalphase, endothermic);
			}
		}
		
		if(initialphase.equals("Liquid"))
		{
			heatEnergy +=  tempChangeLiquid(mass, initialtemp, finaltemp,finalphase,  endothermic);
			if (finalphase.equals("Solid"))
			{
				heatEnergy += fusion(mass, endothermic); 
				heatEnergy += tempChangeSolid(mass, 0, finaltemp, finalphase, endothermic); 
			}
			if (finalphase.equals("Vapor"))
			{
				heatEnergy += vaporization(mass, endothermic);
				heatEnergy += tempChangeGas(mass, 100, finaltemp, finalphase, endothermic);
			}
		}
			
//Starting sate of matter is is Vapor
			
		if(initialphase.equals("Vapor"))
		{
			heatEnergy += tempChangeGas(mass, initialtemp, finaltemp, finalphase, endothermic );
			if(!finalphase.equals("Vapor"))
			{
				heatEnergy += vaporization(mass, endothermic);
				heatEnergy += tempChangeLiquid(mass,100, finaltemp, finalphase, endothermic);
			}
			if (finalphase.equals("Solid"))
			{
				heatEnergy += fusion(mass, endothermic); 
				heatEnergy += tempChangeSolid(mass,0, finaltemp, finalphase, endothermic);
			}
		}
		
		System.out.println("The Total Heat Energy: " + heatEnergy + " KJ");
		
    	
	}
	
	
	
	
	

	
	public static double tempChangeSolid(double mass, double startTemp, double endTemp, String endphase, boolean endothermic)
	{
		if(!endphase.equals("Solid"))
			endTemp = 0; 
		double energyChange = round(mass*0.002108*(endTemp - startTemp));
		if(endothermic)
			System.out.println("Heating (Solid): " + energyChange + " KJ" );
		if (!endothermic)
			System.out.println("Cooling (Solid): " + energyChange + " KJ");
		return energyChange; 
			 
	}
	public static double tempChangeLiquid(double mass, double startTemp, double endTemp, String endphase, boolean endothermic)
	{
		if(endphase.equals("Solid"))
			endTemp = 0; 
		if (endphase.equals("Vapor"))
			endTemp = 100; 
		double energyChange = round(mass*0.004184*(endTemp - startTemp));
		if(endothermic)
			System.out.println("Heating (Liquid): " + energyChange + " KJ" );
		if (!endothermic)
			System.out.println("Cooling (Liquid): " + energyChange + " KJ");
		return energyChange; 
			 
	}
	public static double tempChangeGas(double mass, double startTemp, double endTemp, String endphase, boolean endothermic)
	{
		if(!endphase.equals("Vapor"))
			endTemp = 100;
		double energyChange = round(mass*0.001996*(endTemp - startTemp));
		if (endothermic)
			System.out.println("Heating (Vapor): " + energyChange + " KJ");
		else
			System.out.println("Cooling (Liquid): " + energyChange +" KJ");
		return energyChange;
		
	}
	
	public static double fusion(double mass, boolean endothermic)
	{
		double energyChange;
		if(endothermic)
		{
			energyChange = round(+0.333*mass);
			System.out.println("Melting: " + energyChange + " KJ");
		}
		else
		{
			energyChange = round(-0.333*mass); 
			System.out.println("Freezing: " + energyChange + " KJ");
		}
		return energyChange; 
	}
	public static double vaporization(double mass, boolean endothermic)
	{
		double energyChange;
		if(endothermic)
		{
			energyChange = round(+2.257*mass);
			System.out.println("Evaporation: " + energyChange + " KJ");
		}
		else
		{
			energyChange = round(-2.257*mass); 
			System.out.println("Condensation: " + energyChange + " KJ");
		}
		return energyChange; 
	}
	public static double round(double x)
	{
		x *= 1000; 
		if (x>0)
			x = (int)(x+0.5)/1000.0;
		else
			x = (int)(x-0.5)/1000.0;
		return x;
				
		
	}
	
}
















