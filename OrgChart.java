package orgChart;

import java.io.*;
import java.util.*;

public class OrgChart {

	static final int maxLevel = 3;

	public static void main(String[] args) {

		HashMap<Integer, Person> personMap = new HashMap<>();
		OrgChart orgChart = new OrgChart();

		try {
			boolean firstFlag = true;
			orgChart.readPersonFile(personMap);

			orgChart.readOrgFile(personMap);

			orgChart.readTeamFile(personMap);

			String input;
			if (args.length == 1)
				input = args[0];
			else if (args.length == 2)
				input = args[0] + " " + args[1];
			else
				throw new IllegalArgumentException("Invalid Command Line Arguments");

			Scanner sc = new Scanner(System.in);
			while (true) {
				if (!firstFlag) {
					System.out.println("Enter the first name or last name or full name to search the person: ");
					input = sc.nextLine();
				}
				List<Person> personList = orgChart.findPerson(personMap, input);

				if (!personList.isEmpty()) {
					System.out.println("Enter level till which data is to be fetched? (1 or 2 or 3) - Default is 1: ");
					String levelInput = sc.nextLine();

					int personCount = 1;
					for (Person person : personList) {
						System.out.println(personCount++ + ") ");
						orgChart.display(person);
						List<Person> teamList = orgChart.findTeam(person, levelInput);
						for (Person member : person.getTeamMember())
							orgChart.display(member);
						System.out.println();
					}
				}

				else
					System.out.println("Person with the details not found");

				System.out.println("Do you want to continue? (Y / N) - Default is N: ");
				String continueMsg = sc.nextLine();
				String msg = "n";
				if (!continueMsg.isEmpty())
					msg = continueMsg;

				if (msg.equalsIgnoreCase("n"))
					break;

				firstFlag = false;
			}
			sc.close();
		} catch (NullPointerException | IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

	// finds the team of person
	public List<Person> findTeam(Person person, String levelInput) {

		int level = 1;
		if (!levelInput.isEmpty()) {
			if (!isNumeric(levelInput.trim()))
				throw new NumberFormatException("Error in parsing the level: Incorrect Input");
			level = Integer.parseInt(levelInput);
		}

		if (level < 1 || level > maxLevel)
			throw new IllegalArgumentException("Error: Level Input is OutOfBound");

		int personLevel = person.getLevel() + 1;
		int levelLimit = personLevel + level;
		List<Person> teamList = person.getTeamMember();
		Collections.sort(teamList, Person.levelComparator);

		for (Person teamMember : teamList) {
			if (teamMember.getLevel() > personLevel && teamMember.getLevel() <= levelLimit)
				teamMemberList.add(teamMember);
		}
		return teamMemberList;
	}

	// prints the person details
	public void display(Person person) {
		System.out.println(person.getFirstName() + " " + person.getLastName() + "    " + person.getTitle() + "    "
				+ person.getOrganization() + "    " + person.getPhone());

	}

	// find the person matching the detail from data structure
	public List<Person> findPerson(HashMap<Integer, Person> personMap, String input) {

		List<Person> personList = new ArrayList<Person>();
		for (Map.Entry<Integer, Person> entry : personMap.entrySet()) {

			Person entryPerson = entry.getValue();

			String firstName = entryPerson.getFirstName();
			String lastName = entryPerson.getLastName();

			if (firstName.equalsIgnoreCase(input) || lastName.equalsIgnoreCase(input)
					|| (firstName + " " + lastName).equalsIgnoreCase(input))

				personList.add(entryPerson);
		}

		return personList;

	}

	// reads the team file
	public void readTeamFile(HashMap<Integer, Person> personMap) throws IOException, IllegalArgumentException {

		File teamFile = new File("resources/team.txt").getAbsoluteFile();
		String st;
		BufferedReader br = new BufferedReader(new FileReader(teamFile));

		while ((st = br.readLine()) != null) {
			String[] teamDetailsStr = st.split(",");

			if (teamDetailsStr.length != 2)
				throw new IllegalArgumentException("Error in parsing team file: Input is incorrect");

			if (!isNumeric(teamDetailsStr[0].trim()))
				throw new NumberFormatException("Error in parsing the ID: Incorrect Input");

			if (!isNumeric(teamDetailsStr[1].trim()))
				throw new NumberFormatException("Error in parsing the ID: Incorrect Input");

			int managerID = Integer.parseInt(teamDetailsStr[0].trim());
			Person manager = personMap.get(managerID);

			int personID = Integer.parseInt(teamDetailsStr[1].trim());
			Person currPerson = personMap.get(personID);

			if (manager == null || currPerson == null) {
				int pID = manager == null ? managerID : personID;
				throw new IllegalArgumentException(
						"Invalid Input: Person with the personID: " + pID + " does not exist!");
			}

			manager.addTeamMember(currPerson);
		}
		br.close();
	}

	// reads the organization file
	public void readOrgFile(HashMap<Integer, Person> personMap) throws IOException, IllegalArgumentException {

		File organizationFile = new File("resources/organization.txt").getAbsoluteFile();
		String st;
		BufferedReader br = new BufferedReader(new FileReader(organizationFile));

		while ((st = br.readLine()) != null) {
			String[] orgDetailsStr = st.split(",");

			if (orgDetailsStr.length != 3)
				throw new IllegalArgumentException("Error in parsing organization file: Input is incorrect");

			if (!isNumeric(orgDetailsStr[2].trim()))
				throw new NumberFormatException("Error in parsing the ID: Incorrect Input");

			int personID = Integer.parseInt(orgDetailsStr[2].trim());
			Person currPerson = personMap.get(personID);

			if (currPerson == null)
				throw new IllegalArgumentException(
						"Invalid Input: Person with the personID: " + personID + " does not exist!");

			currPerson.setTitle(orgDetailsStr[0]);
			currPerson.setOrganization(orgDetailsStr[1]);

			String[] levelMap = new String[] { "Manager", "Team Lead", "Developer" };

			for (int i = 0; i < levelMap.length; i++) {
				if (levelMap[i].equals(currPerson.getTitle())) {
					currPerson.setLevel(i + 1);
					break;
				}
			}
		}
		br.close();
	}

	// reads the personal details file and set the data in the data structure
	public void readPersonFile(HashMap<Integer, Person> personMap) throws IOException, IllegalArgumentException {

		File personalFile = new File("resources/personal.txt").getAbsoluteFile();
		String st;
		BufferedReader br = new BufferedReader(new FileReader(personalFile));

		while ((st = br.readLine()) != null) {
			String[] personDetailsStr = st.split(",");

			if (personDetailsStr.length != 7)
				throw new IllegalArgumentException("Error in parsing person file: Input is incorrect");

			if (!isNumeric(personDetailsStr[6].trim()))
				throw new NumberFormatException("Error in parsing the ID: Incorrect Input");

			Person personDetails = new Person(personDetailsStr[0], personDetailsStr[1], personDetailsStr[2],
					personDetailsStr[3] + personDetailsStr[4] + personDetailsStr[5], personDetailsStr[6]);

			String phone = personDetails.getPhone().replace("-", "");

			if (phone.length() != 10)
				throw new IllegalArgumentException("Error in parsing phone");

			if (personMap.containsKey(personDetails.id))
				throw new IllegalArgumentException("Person with id " + personDetails.id + " already exists");

			personMap.put(personDetails.id, personDetails);
		}
		br.close();
	}

	// checks if the if the string contains numeric data
	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
