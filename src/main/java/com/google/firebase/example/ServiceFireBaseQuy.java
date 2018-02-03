/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.firebase.example;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ServiceFireBaseQuy {

	private static final String TABLE_LOCATION = "Location";
    private static final String TABLE_LOCATION_TMP = "Location_tmp";
    private static final String TABLE_CITY = "City";
    private static final String TABLE_DICSTRICT = "District";
    private static final String TABLE_KEY_PROVINCE = "Province";
    private static final String TABLE_KEY_MAP_USER = "Map_User";
    private static final String TABLE_TRIP_SERVICE = "Trip_Service";
    private static final String TABLE_TRIP_LOCATION_SERVICE = "Trip_Location_Service";
    private static final String TABLE_CELL_MAP = "Cell_Map";
    
    private static final String LIST_LOCATION = "z-ListLocation";
    private static final String LIST_DISTRICT = "z-ListDistrict";
    private static final String LIST_USER = "z-ListUser";
    
    private static final String POINT_START = "Ä?Iá»‚M Ä?áº¦U";
    private static final String POINT_END = "Ä?Iá»‚M CUá»?I";
	
    private static final String[] countries = {"Viá»‡t nam", "vietnam", "viet nam"};
    /**
     * @author hung
     *
     */
    public static class Locations{
    	public String user_id;
    	public String user_email;
        public String address;
        public String full_address;
        public String latitude;
        public String longitude;
        public String modify_address;
        public String latitude_degree;
        public String longitude_degree;
        public int type;

        public Locations(){

        }

		public Locations(String user_id, String user_email, String address, String full_address, String latitude,
				String longitude, String modify_address, String latitude_degree, String longitude_degree, int type) {
			this.user_id = user_id;
			this.user_email = user_email;
			this.address = address;
			this.full_address = full_address;
			this.latitude = latitude;
			this.longitude = longitude;
			this.modify_address = modify_address;
			this.latitude_degree = latitude_degree;
			this.longitude_degree = longitude_degree;
			this.type = type;
		}		      
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
    
    public static class Province_City{
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
    
    public static class Province_District{
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
    
    public static class MapUser_Location {
    	public String location_id;  	
    	public String address;
    	public String latitude;
    	public String longitude;
    	
    	public MapUser_Location() {
    		
    	}
    		
		public MapUser_Location(String location_id, String address, String latitude, String longitude) {
			this.location_id = location_id;
			this.address = address;
			this.latitude = latitude;
			this.longitude = longitude;
		}
    }
    
    public static class Point {
    	public double lat;
        public double lng;
        public String lat_degree;
        public String lng_degree;
        public String fullAddress;

        public Point() {
        }

        public Point(double lat, double lng, String lat_degree, String lng_degree, String fullAddress) {
            this.lat = lat;
            this.lng = lng;
            this.lat_degree = lat_degree;
            this.lng_degree = lng_degree;
            this.fullAddress = fullAddress;
        }		
	}
    
    public static class Trip{
        public String userId;
        public String userEmail;
        public double distance;
        public String timeStart;


        public Trip(String userId, String userEmail, double distance, String timeStart) {
            this.userId = userId;
            this.userEmail = userEmail;
            this.distance = distance;
            this.timeStart = timeStart;
        }

        public Trip() {
        }
    }
    
    public static class Road {
    	public String userId;
        public double latitude;
        public double longitude;
        public String lat_degree;
        public String lng_degree;
        public String next_point;
        public String pre_point;
        public boolean isTableTrip; // True is Trip, False is Trip_location
        public String timeStart;
        public Road() {
        }
        
        public Road(double latitude, double longitude ) {
        	this.latitude = latitude;
        	this.longitude = longitude;
        }
        
        public Road(double latitude, double longitude, String lat_degree,String lng_degree ) {
        	this.latitude = latitude;
        	this.longitude = longitude;
        	this.lat_degree = lat_degree;
        	this.lng_degree = lng_degree;
        }
        
		public Road(String userId, double latitude, double longitude, String lat_degree,
				String lng_degree, String next_point, String pre_point, boolean isTableTrip) {
			this.userId = userId;
			this.latitude = latitude;
			this.longitude = longitude;
			this.lat_degree = lat_degree;
			this.lng_degree = lng_degree;
			this.next_point = next_point;
			this.pre_point = pre_point;
			this.isTableTrip = isTableTrip;
		}       
    }
    
    public void deleteAll() {
    	FirebaseDatabase database = FirebaseDatabase.getInstance();
    	//DatabaseReference firebasePovince = database.getReference(TABLE_KEY_PROVINCE); 
    	DatabaseReference firebaseTripLocationService = database.getReference(TABLE_TRIP_LOCATION_SERVICE);
    	//firebasePovince.getRef().setValue(null);	
    	firebaseTripLocationService.getRef().setValue(null);
    }
    
    public void service(){
    	
    	FirebaseDatabase database = FirebaseDatabase.getInstance();
    	
    	DatabaseReference firebaseLocationTemp = database.getReference(TABLE_LOCATION_TMP);
    	DatabaseReference firebaseLocation = database.getReference(TABLE_LOCATION);
    	DatabaseReference firebaseCity = database.getReference(TABLE_CITY);
    	DatabaseReference firebaseDistrict = database.getReference(TABLE_DICSTRICT);
    	DatabaseReference firebasePovince = database.getReference(TABLE_KEY_PROVINCE);
    	DatabaseReference firebaseMapUser = database.getReference(TABLE_KEY_MAP_USER);
    	DatabaseReference firebaseTripService = database.getReference(TABLE_TRIP_SERVICE);
    	DatabaseReference firebaseTripLocationService = database.getReference(TABLE_TRIP_LOCATION_SERVICE);
    	DatabaseReference firebaseCellMap = database.getReference(TABLE_CELL_MAP);
    	
    	firebaseLocationTemp.addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				System.out.println(": "+snapshot.getKey());
				
				// 1. Get location from Temp
				Locations location  = snapshot.getValue(Locations.class);	
	
				String[] address = location.full_address.split(",");
				int lengthAddress = address.length;
				
				if(lengthAddress > 0) {
					String country = address[lengthAddress - 1].trim().toUpperCase();
					boolean isExistCountry = false;
					
					for(String itemCountry : countries) {
						if(itemCountry.toUpperCase().equals(country)) {
							isExistCountry = true;
							break;
						}
					}
					
					if(isExistCountry)
						lengthAddress = lengthAddress - 1;
				}
				
				String cityName = "";
				String district = "";
				
				if(lengthAddress > 0)
					cityName = address[lengthAddress - 1].trim();
				
				if(lengthAddress > 1)
					district = address[lengthAddress - 2].trim();
				
				String city_name = cityName;
				final String district_name = district;	
				
				firebasePovince.addValueEventListener(new ValueEventListener() {
					
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						if(snapshot.exists()) 
						{
							String keyCity = "";
							String keyDistrict = "";
							String KeyUser = "";
									
							for(DataSnapshot city_Id : snapshot.getChildren()) {
								Province_City cityItem = city_Id.getValue(Province_City.class);
								if(city_name.equals(cityItem.city_name) && !city_name.isEmpty()){
									keyCity = city_Id.getKey();
									
									for(DataSnapshot districtId : city_Id.child(LIST_DISTRICT).getChildren()) {
										Province_District districItem = districtId.getValue(Province_District.class);
										if(district_name.equals(districItem.district_name) && !district_name.isEmpty()) {
											keyDistrict = districtId.getKey();
											
											for(DataSnapshot userItem : districtId.child(LIST_USER).getChildren()) {
												if(location.user_id.equals(userItem.getKey())){
													KeyUser = userItem.getKey();
												}
											}
											
										}
									}
								}
							}
							
							// Add City to Province and City
							if(keyCity.isEmpty()) 
							{
								keyCity = firebaseCity.push().getKey();
								
								// Save city to Province
								Province_City province_city = new Province_City(keyCity, city_name);															
								firebasePovince.child(keyCity).setValue(province_city);
								
								// Save city to city
								City city = new City(city_name);
								firebaseCity.child(keyCity).setValue(city);
								
								return;
							}
							
							// Add City to Province and City
							if(!keyCity.isEmpty() && keyDistrict.isEmpty()) 
							{
								keyDistrict = firebaseDistrict.push().getKey();
								
								Province_District province_district = new Province_District(keyDistrict, district_name);								
								firebasePovince.child(keyCity)
												.child(LIST_DISTRICT)
												.child(keyDistrict)
												.setValue(province_district);
																
								District district = new District(district_name);
								firebaseDistrict.child(keyDistrict).setValue(district);
								
								return;
							}
							
							if(!keyCity.isEmpty() && !keyDistrict.isEmpty() && KeyUser.isEmpty()) 
							{
								Province_User provice_user = new Province_User(location.user_id, location.user_email);
								firebasePovince.child(keyCity)
												.child(LIST_DISTRICT)
												.child(keyDistrict)
												.child(LIST_USER)
												.child(location.user_id)
												.setValue(provice_user);
								
								return;
							}							
						}else 
						{
							String keyCity = firebaseCity.push().getKey();
							
							Province_City province_city = new Province_City(keyCity, city_name);							
							firebasePovince.child(keyCity).setValue(province_city);
								
							City city = new City(city_name);
							firebaseCity.child(keyCity).setValue(city);
						}						
					}
					
					@Override
					public void onCancelled(DatabaseError error) {
						
					}
				});				
				
				firebaseMapUser.addValueEventListener(new ValueEventListener() {
					
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						if(snapshot.exists()) {
							String key_userId = "";
							String key_locationId = "";
							
							for(DataSnapshot user_id : snapshot.getChildren()) {
								if(user_id.getKey().equals(location.user_id)) {
									key_userId = user_id.getKey();
									
									DataSnapshot listAddress = user_id.child(LIST_LOCATION);	
									
									for(DataSnapshot address_id : listAddress.getChildren()) {
										MapUser_Location mapuser_location = address_id.getValue(MapUser_Location.class);
										
										if(location.full_address.equals(mapuser_location.address))
											key_locationId = address_id.getKey();
									}
								}
							}
							
							if(key_userId.isEmpty()) {
								Province_User user = new Province_User(location.user_id, location.user_email);							
								firebaseMapUser.child(location.user_id).setValue(user);
								
								return;
							}
							
							if(!key_userId.isEmpty() && key_locationId.isEmpty()) {
								// 2. Save location
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
						}else {						
							Province_User user = new Province_User(location.user_id, location.user_email);
							
							firebaseMapUser.child(location.user_id).setValue(user);
						}						
					}
					
					@Override
					public void onCancelled(DatabaseError error) {}
				});
				
				firebaseLocation.addChildEventListener(new ChildEventListener() {
					
					@Override
					public void onChildRemoved(DataSnapshot snapshot) {
						
					}
					
					@Override
					public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
						
					}
					
					@Override
					public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
						
					}
					
					@Override
					public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
						firebaseLocationTemp.getRef().setValue(null);				
					}
					
					@Override
					public void onCancelled(DatabaseError error) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError error) {}
		});  	
     
    	addEventTripService(firebaseTripService, firebaseTripLocationService, firebaseCellMap);	
    }
    
    public void addEventTripService(DatabaseReference firebaseTripService, DatabaseReference firebaseTripLocationService, DatabaseReference frbsCellMap) {
    	

    	firebaseTripService.addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot snapshot) {	}
			
			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) { }
			
			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				
					String idTrip = snapshot.getKey();
					Trip trip = snapshot.getValue(Trip.class);
					String userId = trip.userId;
					String startTime = trip.timeStart;
					
					Point pointStart = snapshot.child(POINT_START).getValue(Point.class);				
					Point pointEnd = snapshot.child(POINT_END).getValue(Point.class);
					
					if(pointStart == null || pointEnd == null)
						return;
					
					String origin = pointStart.fullAddress;				
					String destination = pointEnd.fullAddress;					
					
					StringBuilder urlLatLong = new StringBuilder();
					urlLatLong.append("http://maps.googleapis.com/maps/api/directions/json?");
					urlLatLong.append("origin=");
					urlLatLong.append(pointStart.lat);
					urlLatLong.append(",");
					urlLatLong.append(pointStart.lng);
					urlLatLong.append("&destination=");
					urlLatLong.append(pointEnd.lat);
					urlLatLong.append(",");
					urlLatLong.append(pointEnd.lng);
					urlLatLong.append("&sensor=false&units=metric&mode=driving");
					
					OkHttpClient httpClient = new OkHttpClient();
					Request requesturlLatLng = new Request.Builder()
						    .url(urlLatLong.toString())
						    .build();
						httpClient.newCall(requesturlLatLng).enqueue(new Callback() {
							
							@Override
							public void onResponse(Response response) throws IOException {
								try {
								saveTripLocationService(response, firebaseTripLocationService, idTrip, userId, startTime);
								}catch (Exception e) {
									System.err.println("Direction LatLong - Error Direction API: "+e.toString());
								}
							}
							
							@Override
							public void onFailure(Request request, IOException e) {
								System.err.println("Direction: "+e.toString());
							}
						});
					
//					String urlLatLong = "http://maps.googleapis.com/maps/api/directions/xml?"
//			                + "origin=" + start.latitude + "," + start.longitude
//			                + "&destination=" + end.latitude + "," + end.longitude
//			                + "&sensor=false&units=metric&mode=" + mode;
					
					
//					OkHttpClient httpClient = new OkHttpClient();
//					Request request = new Request.Builder()
//						    .url(url.toString())
//						    .build();
//						httpClient.newCall(request).enqueue(new Callback() {
//							
//							@Override
//							public void onResponse(Response response) throws IOException {
//								try {
//									saveTripLocationService(response, firebaseTripLocationService, idTrip, userId);	
//								}catch (Exception e) {
//									System.err.println("Direction - Error Direction API: "+e.toString());
//									
//									Request requesturlLatLng = new Request.Builder()
//										    .url(urlLatLong.toString())
//										    .build();
//										httpClient.newCall(requesturlLatLng).enqueue(new Callback() {
//											
//											@Override
//											public void onResponse(Response response) throws IOException {
//												try {
//												saveTripLocationService(response, firebaseTripLocationService, idTrip, userId);
//												}catch (Exception e) {
//													System.err.println("Direction LatLong - Error Direction API: "+e.toString());
//												}
//											}
//											
//											@Override
//											public void onFailure(Request request, IOException e) {
//												System.err.println("Direction: "+e.toString());
//											}
//										});
//								}	
//							}
//							
//							@Override
//							public void onFailure(Request request, IOException e) {
//								System.err.println("Direction: "+e.toString());
//								
//							}
//						});		
			}
			
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    private void saveTripLocationService(Response response, DatabaseReference firebaseTripLocationService, String idTrip, String userId, String startTime ) {
    	String body = null;
		try {
			body = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	JSONObject jsonbject = new JSONObject(body);
		 	
		
		JSONArray arrayStep  = jsonbject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
		if(arrayStep != null) {
			ArrayList<Road> roads = new ArrayList<>();
			int lengthTrip = arrayStep.length();
			for(int i=0; i< lengthTrip; i++) {
				JSONObject start_location = arrayStep.getJSONObject(i).getJSONObject("start_location");
				
				Road itemRoadStart = new Road();
				itemRoadStart.latitude = start_location.getDouble("lat");;
				itemRoadStart.longitude = start_location.getDouble("lng");
				roads.add(itemRoadStart);																
				
				String poly = arrayStep.getJSONObject(i).getJSONObject("polyline").getString("points");
				ArrayList<Road> arr = decodePoly(poly);
                for (int j = 0; j < arr.size(); j++) {
                	roads.add(arr.get(j));						                    
                }								
								                
                JSONObject end_location = arrayStep.getJSONObject(i).getJSONObject("end_location");						                
                Road itemRoadEnd = new Road();
				itemRoadEnd.latitude = end_location.getDouble("lat");
				itemRoadEnd.longitude = end_location.getDouble("lng");
				roads.add(itemRoadEnd);										
			}
			
			int pointLenght = roads.size();
			for(int i = 0; i < pointLenght; i++) {
				Road item  = roads.get(i);
				
				item.isTableTrip = false;
				item.userId = userId;							
				item.lat_degree = LocationUtil.convertLatitudeDegree(item.latitude);
				item.lng_degree = LocationUtil.convertLongitudeDegree(item.longitude);
				item.pre_point = "";
				item.next_point = "";
				item.timeStart = startTime;
				
				if(i > 0) {
					Road preItem = roads.get(i-1);
					StringBuilder strPrepoint = new StringBuilder();
					strPrepoint.append(preItem.lat_degree);
					strPrepoint.append(" ");
					strPrepoint.append(preItem.lng_degree);
					
					item.pre_point = strPrepoint.toString();
				}
				
				if(i < pointLenght -1) {
					Road nextItem = roads.get(i + 1);
					
					String nextLatDegree = LocationUtil.convertLatitudeDegree(nextItem.latitude);
					String nextLngDegree = LocationUtil.convertLongitudeDegree(nextItem.longitude);
					
					StringBuilder strNextPoint = new StringBuilder();
					strNextPoint.append(nextLatDegree);
					strNextPoint.append(" ");
					strNextPoint.append(nextLngDegree);
					
					item.next_point = strNextPoint.toString();
				}	
				
				roads.set(i, item);
			}
																				
			firebaseTripLocationService.child(idTrip).setValue(roads);	
		}
    }
    
    private ArrayList<Road> decodePoly(String encoded) {
        ArrayList<Road> poly = new ArrayList<Road>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Road position = new Road((double) lat / 1E5, (double) lng / 1E5, "", "");
            poly.add(position);
        }
        return poly;
    }
    
    public static void main(String[] args) {
	    // write your code here
    }
}
