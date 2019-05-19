package com.trainservice.train;

//stations entity
public class TrainStations {

	private String _id;
	private String stationCode;
	private String stationID;
	private String stationName;
	
	public TrainStations(String _id, String stationCode, String stationID, String stationName) {
		this._id = _id;
		this.stationCode = stationCode;
		this.stationID = stationID;
		this.stationName = stationName;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	
}
