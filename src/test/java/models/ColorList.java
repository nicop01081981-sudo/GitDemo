package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorList {
	private List<Color> data;

	public List<Color> getData() {
		return data;
	}

	public void setData(List<Color> data) {
		this.data = data;
	}
	
	

}
