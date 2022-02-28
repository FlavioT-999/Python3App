package com.application.python3.calculations.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* classe che contiene i dati salienti di una escursione */ 

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrackSummary 
{
	private Double distance;
	private Double climb;
	private Double descent;
	private Long totalTime;
	private int indexMaxHeight;
	private Double maxHeight;
	
	@Override
	public String toString() {
		return "TrackSummary [distance=" + distance + ", climb=" + climb + ", descent=" + descent + ", totalTime="
				+ totalTime + ", indexMaxHeight=" + indexMaxHeight + ", maxHeight=" + maxHeight + "]";
	}
	
	public boolean equals(TrackSummary oSum)
	{
		return (this.distance.equals(oSum.distance))&&(this.climb.equals(oSum.climb))&&
				(this.descent.equals(oSum.descent))&&(this.totalTime.equals(oSum.totalTime))&&
				(this.indexMaxHeight==oSum.indexMaxHeight)&&(this.maxHeight.equals(oSum.maxHeight));
		
		
	}
	
}
