package camera.socket.app.gxj.com.provider;

public class Person {

	private int personid;
	private String name;

	private String sex;//升级添加列

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	
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
