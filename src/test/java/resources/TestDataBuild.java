package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlaceAPI;

public class TestDataBuild {
	
	AddPlace obj;
	DeletePlaceAPI deletePlaceRequestBuild;
	
	public AddPlace addPlacePayload(String name,String address,String language) {
		obj = new AddPlace();
		pojo.Location obj1 = new pojo.Location();
		obj1.setLat(38.383494);
		obj1.setLng(33.427362);
		obj.setLocation(obj1);
		obj.setAccuracy(50);
		obj.setName(name);
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setAddress(address);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		
		
		
		obj.setTypes(types);
		obj.setWebsite("http://google.com");
		obj.setLanguage(language);
		return obj;
		
		
	}
	
	public DeletePlaceAPI deletePlacePayload(String string) {
		
		deletePlaceRequestBuild = new DeletePlaceAPI();
		deletePlaceRequestBuild.setPlace_id(string);
		return deletePlaceRequestBuild;
		
	}

}
