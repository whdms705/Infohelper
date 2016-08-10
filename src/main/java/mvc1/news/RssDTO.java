package mvc1.news;

public class RssDTO { // 처음 정보를 가져올때 구분하는 DTO
	private String id;
	 private String name;
	 private String type;
	 private String cat;
	 private String rss;
	 
	 
	 public RssDTO(String id,String name,String type,String cat,String rss){
		 this.id=id;
		 this.name=name;
		 this.type=type;
		 this.cat=cat;
		 this.rss=rss;
	 }
	 
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getRss() {
		return rss;
	}
	public void setRss(String rss) {
		this.rss = rss;
	}
	 

}
