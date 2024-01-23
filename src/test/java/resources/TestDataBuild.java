package resources;

import java.util.ArrayList;
import java.util.List;

import pojos.Location;
import pojos.firstPojo;

public class TestDataBuild {

	public firstPojo addPlacePayload(String name, String language, String address) {
		firstPojo p = new firstPojo();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		List<String> listOfType = new ArrayList<String>();
		listOfType.add("shoe park");
		listOfType.add("shop");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setTypes(listOfType);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		return p;
	}
	
	public String deletPlacePayload(String ID) {
		return "{\r\n    \"place_id\":\""+ID+"\"\r\n}";
	}
}
