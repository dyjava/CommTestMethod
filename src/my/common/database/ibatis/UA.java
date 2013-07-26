package my.common.database.ibatis;

/** 
 * by dyong 2010-8-19
 */
public class UA {
	private String vendor;
	private String model;
	private String screen_size;
	private String screen_size_char;
	private String Agent ;
	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getScreen_size() {
		return screen_size;
	}
	public void setScreen_size(String screenSize) {
		screen_size = screenSize;
	}
	public String getScreen_size_char() {
		return screen_size_char;
	}
	public void setScreen_size_char(String screenSizeChar) {
		screen_size_char = screenSizeChar;
	}
	public String getAgent() {
		return Agent;
	}
	public void setAgent(String agent) {
		Agent = agent;
	}
	
	
}
