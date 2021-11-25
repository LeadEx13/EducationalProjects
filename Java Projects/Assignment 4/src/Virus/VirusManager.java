package Virus;

import java.util.Random;

public class VirusManager 
{
	private static boolean[][] mutationTable;
	static {
		/**
		 * create the mutation table
		 */
		mutationTable=new boolean[Viruses.values().length][];
		for(int i=0;i<mutationTable.length;i++)
		{
			mutationTable[i]=new boolean[Viruses.values().length];
			for(int j=0;j<mutationTable.length;j++)
			{
				if(i!=j)
					mutationTable[i][j]=false;
				else
					mutationTable[i][j]=true;
			}
		}
	}
	public static void toogle(int i, int j)
	{
		mutationTable[i][j]=!mutationTable[i][j];
	}

	public static IVirus contagion(IVirus src) 
	{
		int index = Viruses.findv(src);
		if(index==-1)
			return null;
		IVirus v=randMutation(mutationTable[index]);
		return v;
	}
	public static IVirus randMutation(boolean[] mutationTable)
	{
		int size=0;
		int[] indexes=null;
		for(int i=0;i<mutationTable.length;i++)
		{
			if(mutationTable[i])
			{
				size++;
			}
		}
		indexes=new int[size];
		int j=0;
		for(int i=0;i<size;i++)
		{
			if(mutationTable[i])
			{		
				indexes[j]=i;
				j++;
			}
		}
		Random rand=new Random();
		int x=rand.nextInt(size);

		return Viruses.values()[indexes[x]].getv();	
	}
	public static boolean getValue(int i, int j)
	{
		return mutationTable[i][j];
	}
}