package camera.socket.app.gxj.com.provider;

public class Person {

	private int personid;
	private String name;
	
	public Person(){}
	
	public Person(String name,int personid) {
		this.personid = personid;
		this.name = name;
	}

	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
