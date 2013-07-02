package com.snist.it.cbir.service;
public class EucledianDistanceService {
  
	public  double findEcledianDistance(double [] a ,double [] b )
	{
		double sum=0;
		
		
		for(int x=0;x<3;x++)
		{
			
				sum=sum+((a[x]-b[x])*(a[x]-b[x]));
		}
		
		double ed=Math.sqrt(sum);
		System.out.println(ed);
		return ed;
	}


}
