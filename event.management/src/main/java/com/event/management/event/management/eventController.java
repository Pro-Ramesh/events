package com.event.management.event.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class eventController {

	@GetMapping
	public String printStrings() {
		return "AMBUSH";
	}

	@GetMapping
	@RequestMapping("/name")
	public String printString() {
		return "RAMESH";

	}

	@GetMapping
	@RequestMapping("/details")
	public sample returnDetails() {
		sample sample = new sample();
		sample.setName("M.RAMESH");
//		sample.setId("123");
		sample.setType("String");
		sample.setVenue("coda");
		sample.setDate("2020");
		return sample;

	}

	@GetMapping
	@RequestMapping("details/list")
	public List<sample> returnList() {
		List<sample> sampleList = new ArrayList<>();

		sample sample = new sample();
		sample.setName("M.RAMESH");
//		sample.setId("123");
		sample.setType("String");
		sample.setVenue("coda");
		sample.setDate("2020");
		sampleList.add(sample);
		return sampleList;
	}

	@GetMapping
	@RequestMapping("/event/list")
	public List<sample> getEvents() throws SQLException {
		System.out.println("inside get");
		Connection con = null;
		List<sample> eventList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "ramesh4074");
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("Select * from event");
			while (res.next()) {
				sample event = new sample();
				event.setId(res.getInt("id"));
				event.setName(res.getString("name"));
				event.setDate(res.getString("date"));
				event.setVenue(res.getString("venue"));
				event.setType(res.getString("type"));
				eventList.add(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return eventList;
	}
	
	@PostMapping
	public boolean addEvent(@RequestBody sample event) throws SQLException {
	
			System.out.println("inside get");
			Connection con = null;
			List<sample> eventList = new ArrayList<>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "ramesh4074");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO event (name, type) "
				          +"VALUES ('"+event.getName()+"', '"+event.getType()+"')");
				//INSERT INTO event (name, type) VALUES ("+event.getName()+", "+event.getType()+")"
				} 
			catch (Exception e)
			{
				e.printStackTrace();
			} 
			finally 
			{
				con.close();
			}
			
	return true;
		
	}
	
	

	
	
}
