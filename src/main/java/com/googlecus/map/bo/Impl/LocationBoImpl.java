package com.googlecus.map.bo.Impl;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.example.ServiceFireBaseDung;
import com.google.firebase.example.ServiceFireBaseDung.KeyMap;
import com.google.firebase.example.ServiceFireBaseDung.Locations;
import com.google.firebase.example.ServiceFireBaseDung.MapUser_Location;
import com.google.firebase.example.ServiceFireBaseDung.Province_User;

public class LocationBoImpl {
    private static final String LIST_LOCATION = "z-ListLocation";
    DatabaseReference firebaseLocation ;
	private static final String LIST_DISTRICT = "z-ListDistrict";
	private static final String TABLE_CITY = "City";
	private static final String TABLE_KEY_MAP = "Key_Map";
	private static final String TABLE_DICSTRICT = "District";
	private static final String TABLE_KEY_PROVINCE = "Province";
	private static final String LIST_USER = "z-ListUser";
	FirebaseDatabase database;
	DatabaseReference firebaseKey_Map;
	DatabaseReference firebaseCity;
	private KeyMap kMap;
	private DatabaseReference firebaseDistrict;
	private DatabaseReference firebasePovince;
	DatabaseReference firebaseMapUser;
	private static final String TABLE_LOCATION = "Location";
    private static final String TABLE_KEY_MAP_USER = "Map_User";
	public void createLocation(Locations pLocations) {
		Locations location = pLocations;
		
		FirebaseDatabase database = FirebaseDatabase.getInstance();
    	
		  firebaseLocation = database.getReference(TABLE_LOCATION);
		 
		firebaseCity = database.getReference(TABLE_CITY);
    	  firebaseMapUser = database.getReference(TABLE_KEY_MAP_USER);
		firebaseKey_Map = database.getReference(TABLE_KEY_MAP);
		firebaseDistrict = database.getReference(TABLE_DICSTRICT);

		 firebasePovince = database.getReference(TABLE_KEY_PROVINCE);

		// addlocation
		// split address
		// check split address in KeyMap
		//
		//
		//

		String[] address = pLocations.full_address.split(",");
		if ((pLocations.modify_address != null)&&(!"".equals(pLocations.modify_address))) 
			address = pLocations.modify_address.split(",");
		int lengthAddress = address.length;

		String cityName = "";
		String district = "";

		String arr1 = null;
		if (lengthAddress > 0) {
			arr1 = address[lengthAddress - 1].trim();

			KeyMap kMap = ServiceFireBaseDung.hshKeyMap.get(arr1);
			if (kMap != null) {
				if ("Country".equals(kMap.Type)) {
					if (lengthAddress > 1) {
						cityName = address[lengthAddress - 2].trim();
						String keyCity = addCity(cityName);

						if (lengthAddress > 2) {
							district = address[lengthAddress - 3].trim();
							String keyDistrict = addDistrict(district, keyCity);
							
							
							Province_User provice_user = new Province_User(pLocations.user_id, pLocations.user_email);
							
						DatabaseReference o = firebasePovince.child(keyCity)
							.child(LIST_DISTRICT)
							.child(keyDistrict);
						if( o ==null)
						{
							
							Province_District province_district = new Province_District(keyDistrict, district);
							firebasePovince.child(keyCity).child(LIST_DISTRICT).child(keyDistrict).setValue(province_district);
							
						}
							
							firebasePovince.child(keyCity)
											.child(LIST_DISTRICT)
											.child(keyDistrict)
											.child(LIST_USER)
											.child(pLocations.user_id)
											.setValue(provice_user);

						}
					}
				}

			}

		}
		
	 


	DatabaseReference mapuser = firebaseMapUser.child(location.user_id) ;
	 
		Province_User user = new Province_User(location.user_id, location.user_email);							
		firebaseMapUser.child(location.user_id).child("USER_INFO").setValue(user);
		
	 
	String id_location = firebaseLocation.push().getKey();
	
	MapUser_Location location_map  = new MapUser_Location();
	location_map.location_id = id_location;
	location_map.address = location.full_address;
	location_map.latitude = location.latitude;
	location_map.longitude = location.longitude;
	
	firebaseMapUser.child(location.user_id)
					.child(LIST_LOCATION)
					.child(id_location)
					.setValue(location_map);
								
	firebaseLocation.child(id_location)
					.setValue(location);	
	
	
	
	
	}

	private String addDistrict(String districtName, String keyCity) {
		String districtId = null;
		KeyMap districtIdMap = ServiceFireBaseDung.hshKeyMap.get(districtName);
		if (districtIdMap != null) {

			districtId = districtIdMap.Id;
			if (districtId == null) {
				// insert city

				districtId = firebaseDistrict.push().getKey();

				// Province_City province_city = new Province_City(keyCity, cityName);
				// firebasePovince.child(keyCity).setValue(province_city);

				District district = new District(districtName);
				firebaseDistrict.child(districtId).setValue(district);

				String keyDistrict = districtId;
				String district_name = districtName;

				Province_District province_district = new Province_District(keyDistrict, district_name);
				firebasePovince.child(keyCity).child(LIST_DISTRICT).child(keyDistrict).setValue(province_district);

				// update keymap id
				kMap = new KeyMap("District", districtId);
				firebaseKey_Map.child(districtName).setValue(kMap);
			}

		} else {
			// insert city

			districtId = firebaseDistrict.push().getKey();

			// Province_City province_city = new Province_City(keyCity, cityName);
			// firebasePovince.child(keyCity).setValue(province_city);

			District district = new District(districtName);
			firebaseDistrict.child(districtId).setValue(district);

			String keyDistrict = districtId;
			String district_name = districtName;

			Province_District province_district = new Province_District(keyDistrict, district_name);
			firebasePovince.child(keyCity).child(LIST_DISTRICT).child(keyDistrict).setValue(province_district);

			// update keymap id
			kMap = new KeyMap("District", districtId);
			firebaseKey_Map.child(districtName).setValue(kMap);

			if (!ServiceFireBaseDung.hshKeyMap.containsKey(districtName)) {

				ServiceFireBaseDung.hshKeyMap.put(districtName, kMap);
			}
		}
		return districtId;
	}

	private String addCity(String cityName) {
		String cityId = null;
		KeyMap ciyMap = ServiceFireBaseDung.hshKeyMap.get(cityName);
		if (ciyMap != null) {

			cityId = ciyMap.Id;
			if (cityId == null) {
				// insert city

				cityId = firebaseCity.push().getKey();

				// Province_City province_city = new Province_City(keyCity, cityName);
				// firebasePovince.child(keyCity).setValue(province_city);

				City city = new City(cityName);
				firebaseCity.child(cityId).setValue(city);

				Province_City province_city = new Province_City(cityId, cityName);
				firebasePovince.child(cityId).setValue(province_city);
				// update keymap id
				ciyMap = new KeyMap("City", cityId);
				firebaseKey_Map.child(cityName).setValue(ciyMap);
			}

		} else {
			// insert city

			cityId = firebaseCity.push().getKey();

			// Province_City province_city = new Province_City(keyCity, cityName);
			// firebasePovince.child(keyCity).setValue(province_city);

			City city = new City(cityName);
			firebaseCity.child(cityId).setValue(city);
			Province_City province_city = new Province_City(cityId, cityName);
			firebasePovince.child(cityId).setValue(province_city);

			// update keymap id
			ciyMap = new KeyMap("City", cityId);
			firebaseKey_Map.child(cityName).setValue(ciyMap);

			if (!ServiceFireBaseDung.hshKeyMap.containsKey(cityName)) {

				ServiceFireBaseDung.hshKeyMap.put(cityName, ciyMap);
			}
		}
		return cityId;
	}

	public class City {
		public String city_name;

		public City() {
		}

		public City(String city_name) {
			this.city_name = city_name;
		}
	}

	public class District {
		public String district_name;

		public District() {
		}

		public District(String district_name) {
			this.district_name = district_name;
		}
	}

	public static class Province_District {
		public String district_id;
		public String district_name;

		public Province_District() {
		}

		public Province_District(String district_id, String district_name) {
			super();
			this.district_id = district_id;
			this.district_name = district_name;
		}
	}

	public static class Province_City {
		private String city_id;
		private String city_name;

		public Province_City() {
		}

		public Province_City(String city_id, String city_name) {
			this.city_id = city_id;
			this.city_name = city_name;
		}

		public String getCity_id() {
			return city_id;
		}

		public void setCity_id(String city_id) {
			this.city_id = city_id;
		}

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}
	}
    public static class Province_User{
    	public String user_id;
		public String user_name;

		
		public Province_User() {
		}

		public Province_User(String user_id, String user_name) {
			super();
			this.user_id = user_id;
			this.user_name = user_name;
		}		
    }
    
}
