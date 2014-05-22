package edu.upc.eetac.dsa.mdelgado.beeter.android.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class StingCollection {
 
	private Map<String, Link> links = new HashMap<String, Link>();
	private List<Sting> stings = new ArrayList<Sting>();;
	private long newestTimestamp;
	private long oldestTimestamp;
 
	public List<Sting> getStings() {
		return stings;
	}
 
	public void setStings(List<Sting> stings) {
		this.stings = stings;
	}
 
	public void addSting(Sting sting) {
		stings.add(sting);
	}
 
	public long getNewestTimestamp() {
		return newestTimestamp;
	}
 
	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}
 
	public long getOldestTimestamp() {
		return oldestTimestamp;
	}
 
	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}
 
	public Map<String, Link> getLinks() {
		return links;
	}
 
}