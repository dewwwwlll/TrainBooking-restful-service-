package com.trainservice.mongodb.queries;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.trainservice.train.Employee;
import com.trainservice.train.TrainStations;

public class TrainDetailsOperations {
	
	MongoClient mongoClient;
	private ArrayList<TrainStations> trainStationsObject = new ArrayList<>();
	
	public TrainDetailsOperations() throws UnknownHostException {

		mongoClient =new MongoClient(new MongoClientURI("mongodb://localhost:27017"));		
	}
	
	//getting all the stations from mongoDB
	public  List<TrainStations> getAllStations() {
		try {		
			
		DB train = mongoClient.getDB("train");
		DBCollection stations = train.getCollection("stations");
		
		DBCursor stationsCursor = stations.find();
		
		while(stationsCursor.hasNext()) {
			
			BasicDBObject stationObj = (BasicDBObject) stationsCursor.next();
			String _id = stationObj.getString("_id");
			String stationCode = stationObj.getString("stationCode");
			String stationID = stationObj.getString("stationID");
			String stationName = stationObj.getString("stationName");
			
			trainStationsObject.add(new TrainStations(_id, stationCode, stationID, stationName));
			
		}	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trainStationsObject;
	}	

	
}
