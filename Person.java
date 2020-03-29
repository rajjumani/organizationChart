package orgChart;

import java.util.*;

// This class is used to maintain the Person entity

public class Person {
	int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String title;
	private String organization;
	private List<Person> teamMembers;
	private int level;
	
	public Person(String firstName, String lastName, String phone, String address, String id){
		setID(id);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(address);
		teamMembers = new ArrayList<Person>();
	}

	// setters and getters
	public void setID(String id) {
		this.id = Integer.parseInt(id.trim());
	}
	
	public int getID() {
		return id;
	}
	
	public void setFirstName(String firstName) {		
		this.firstName = firstName.trim();
	}
	
	public String getFirstName() {		
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}
	
	public String getLastName() {		
		return lastName;
	}
	
	public void setAddress(String address) {
		this.address = address.trim();
	}
	
	public String getAddress() {		
		return address;
	}
	
	public void setPhone(String phone) {
		this.phone = phone.trim();
	}

	public String getPhone() {		
		return phone;
	}
	
	public void setTitle(String title) {
		this.title = title.trim();
	}
	
	public String getTitle() {		
		return title;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setOrganization(String organization) {
		this.organization = organization.trim();
	}
	
	public String getOrganization() {		
		return organization;
	}
	
	public void addTeamMember(Person member) {
		if(!teamMembers.contains(member))
			this.teamMembers.add(member);
	}
	
	public List<Person> getTeamMember(){
		return teamMembers;
	}

	public static Comparator<Person> levelComparator = new Comparator<Person>() {
		
		@Override
		public int compare(Person p1, Person p2) {
			return p1.getLevel() - p2.getLevel();
		}
	};
}
