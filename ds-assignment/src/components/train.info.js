import React, {Component} from "react";
import axios from "axios";
import stations from "../stations";

export const SOURCE = "SOURCE";
export const DESTINATION = "DESTINATION";
export const TICKETS = "TICKETS";

class TrainInfo extends Component{
    constructor(props){
        super(props);
        this.state={
            stationList:[],
            isStartStationSelected:false,
            isEndStationSelected:false,
            isNoOfTicketsSelected:false,
            isDateSelected:false,
            startStationSelect:"",
            endStationSelect:"",
            noOfTickets:0,
            trainDate:"",
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/trainservice/webapi/traindetails/stations')
            .then(res => {
                this.setState({stationList : res.data})
            })
    }

    onStartSelect = (e) => {
        this.setState({startStationSelect: e.target.value})
            this.setState({isStartStationSelected : true});
    }

    onEndSelect =(e) => {
        this.setState({endStationSelect: e.target.value})
            this.setState({isEndStationSelected : true});
    }

    onNoOfTickets =(e) => {
        this.setState({noOfTickets: e.target.value})
            this.setState({isNoOfTicketsSelected: true})
    }

    trainInformationSubmitHandler(e) {
        e.preventDefault();
        if(this.state.startStationSelect === this.state.endStationSelect){
            alert("Please Check Your Details!")
        }
        if (this.state.isStartStationSelected && this.state.isEndStationSelected && this.state.isNoOfTicketsSelected) {
            sessionStorage.setItem(SOURCE, this.state.startStationSelect);
            sessionStorage.setItem(DESTINATION, this.state.endStationSelect);
            sessionStorage.setItem(TICKETS, this.state.noOfTickets);
            this.props.history.push(`/payment`);
        }
        else {
        }
    }


    render(){
        return(
            <div>
                <div className="custom-control col card bg-dark">
                <h3 className="card-header bg-dark text-white">Train Information</h3>
                <br/>
                < div className = "form-group" >
                    <form className="was-validated">
                    <h5 className="card-text text-success">Start Station </h5>
                            <select className="custom-select" ref="startStation" onChange={this.onStartSelect.bind()} required>
                                <option defaultValue="selected"></option>
                                {this.state.stationList.map((eachStation) =>
                                    <option key={eachStation.stationID} value={eachStation.stationName}>{eachStation.stationName}</option>
                                )}
                            </select>
                        <div className="invalid-feedback">
                            Please select a Starting Station
                        </div>


                        <h5 className="card-text text-success">End Station </h5>
                            <select className="custom-select" ref="endStation" onChange={this.onEndSelect.bind()} required>
                                <option defaultValue="selected"></option>
                                {this.state.stationList.map((eachStation) =>
                                    <option key={eachStation.stationID} value={eachStation.stationName}>{eachStation.stationName}</option>
                                )}
                            </select>
                        <div className="invalid-feedback">
                            Please select a Destination Station
                        </div>

                        <h5 className="card-text text-success">How Many Tickets </h5>
                        <input type="text" className="form-control" id="tickets"
                               placeholder="No of tickets" onChange={this.onNoOfTickets.bind()} required/>
                        <div className="invalid-feedback">
                            Please Enter No Of Tickets
                        </div>
                        <br/>
                        <button className=" btn btn-success" type="button" onClick={this.trainInformationSubmitHandler.bind(this)}>Next</button>
                    </form>
                </div>
                </div>
            </div>

        );
    }
}

export default TrainInfo;