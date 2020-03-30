package orgChart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.*;

public class OrgChartTest {

	@Test
	public void personTest() {
		Person p = new Person("Raj", "Jumani", "333-333-3333", "445 Columbus, Syracuse, NY", "1");

		// test if person is created correctly
		assertEquals("Raj", p.getFirstName());
		assertEquals("Jumani", p.getLastName());
		assertEquals("333-333-3333", p.getPhone());
		assertEquals("445 Columbus, Syracuse, NY", p.getAddress());
		assertEquals(1, p.getID());
	}

	// Test for file if it is read
	@Test
	public void fileReadTest() throws IllegalArgumentException, IOException {

		// test if file is read
		File personFile = new File("resources/personal.txt");
		assertTrue(personFile.exists() && personFile.length() > 0);

		File orgFile = new File("resources/organization.txt");
		assertTrue(orgFile.exists() && orgFile.length() > 0);

		File teamFile = new File("resources/team.txt");
		assertTrue(teamFile.exists() && teamFile.length() > 0);
	}

	// test if person and his team can be found or not
	@Test
	public void findPersonTest() {
		HashMap<Integer, Person> personMap = new HashMap<>();

		OrgChart orgChart = new OrgChart();

		Person manager = new Person("Raj", "Jumani", "333-333-3333", "445 Columbus, Syracuse, NY", "1");
		manager.setTitle("Manager");
		manager.setOrganization("Product Engineering");
		manager.setLevel(1);

		personMap.put(manager.getID(), manager);

		assertTrue(!orgChart.findPerson(personMap, "Raj").isEmpty());
		assertTrue(orgChart.findPerson(personMap, "Joe").isEmpty());

	}
}
