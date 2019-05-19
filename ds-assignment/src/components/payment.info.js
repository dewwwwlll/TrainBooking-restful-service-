import React, {Component} from "react";
import CreditCardDetails from "./cc";
import MobilePay from "./mobilePay";
import {SOURCE,DESTINATION,TICKETS} from "./train.info";
import axios from "axios";

export const PRICE = 0;
class Payment extends Component{
    constructor(props){
        super(props);
        this.state={
            props:props,
            ticketInfo:[],
            start: sessionStorage.getItem(SOURCE),
            end: sessionStorage.getItem(DESTINATION),
            quantity: sessionStorage.getItem(TICKETS),
            price: 0,
            paymentOption:null
        }
    }

    componentDidMount() {
        // axios.get("http://localhost:8080/trainservice/webapi/myresource/"
        //     + this.state.start + "/" + this.state.end
        //         + "/" + this.state.quantity)
        //     .then(res => {
        //         this.setState({ticketInfo : res.data})
        //     })
        fetch('http://localhost:8080/trainservice/webapi/traindetails/', {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                "source": this.state.start,
                "destination": this.state.end,  
                "no_of_tickets": Number(this.state.quantity)
            })
        })
            .then(response => {
                return response.json();
            })
            .then(json => {
                this.setState({price : json.price})
            })

    }


    onCreditClick(e){
        // e.preventDefault();
        this.setState({paymentOption: <CreditCardDetails price={this.state.price}/>})
    }

    onMobileClick(e){
        // e.preventDefault();
        this.setState({paymentOption: <MobilePay/>})
    }

    render(){
        return(
            <div className="card col-md">
                <h4 className="card-header bg-dark text-success">Booking Details</h4>
                <h5  className="card-text text-info">
                    <code className="text-info">{sessionStorage.getItem(TICKETS)}</code> Tickets&nbsp;
                    To <code className="text-info">{sessionStorage.getItem(DESTINATION)}</code>&nbsp;
                    station from : <code className="text-info">{sessionStorage.getItem(SOURCE)}</code>&nbsp;
                </h5>
                <h4 className="card-header bg-dark text-success">Payment Details</h4>
                <h5 className="text-info"> Amount : {this.state.price.toFixed()}LKR</h5>
                <h5>Select a payment method</h5>
                    <form className="was-validated">
                        <div className="custom-control custom-radio col-sm-4">
                            <input type="radio" onClick={this.onCreditClick.bind(this)} className="custom-control-input" id="customControlValidation2"
                                   name="radio-stacked" required/>
                                <label className="custom-control-label" htmlFor="customControlValidation2">
                                    Credit Card
                                </label>
                        </div>
                        <div className="custom-control custom-radio mb-3 col-sm-5">
                            <input type="radio" onClick={this.onMobileClick.bind(this)} className="custom-control-input" id="customControlValidation3"
                                   name="radio-stacked" required/>
                                <label className="custom-control-label" htmlFor="customControlValidation3">
                                    Mobile Payment
                                </label>
                        </div>
                        {this.state.paymentOption}
                    </form>


            </div>
        );
    }
}

export default Payment;