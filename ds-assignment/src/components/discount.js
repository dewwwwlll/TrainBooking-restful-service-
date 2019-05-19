import React , {Component} from 'react';
import {NAME, EMAIL, CC, CVC, EXP, PRICE, C, MC} from "./cc";
import {SOURCE,DESTINATION,TICKETS} from "./train.info";
import {M,MOBILEEMAIL,MOBILENUMBER,CM,PIN} from './mobilePay';

class Discount extends Component{
    constructor(props){
        super(props);
        this.state = {
            nameOnCard: sessionStorage.getItem(NAME),
            email:sessionStorage.getItem(EMAIL),
            mobileEmail:sessionStorage.getItem(MOBILEEMAIL),
            creditm:sessionStorage.getItem(CM),
            mobilec:sessionStorage.getItem(MC),
            credit:sessionStorage.getItem(C),
            mobile:sessionStorage.getItem(M),
            mobileNumber: sessionStorage.getItem(MOBILENUMBER),
            cardNumber: sessionStorage.getItem(CC),
            cVC: sessionStorage.getItem(CVC),
            expireDate: sessionStorage.getItem(EXP),
            price: sessionStorage.getItem(PRICE),
            source: sessionStorage.getItem(SOURCE),
            destination: sessionStorage.getItem(DESTINATION),
            tickets: sessionStorage.getItem(TICKETS),
            pin: sessionStorage.getItem(PIN),
            confirmationCode:"",
            pMobile:false,
            pCredit:false,
            percentage: "50",
            total:0,
            amount:0,
            discount:0,
            isGovernment: "none",
            proceed: "none",
            creditDiv: "none",
            mobileDiv: "none",
            nic:"",
            proceedBtn:"none",
            printBtn:"none",
            finalDiv: "block"
        }
    }

    government = (e) => {
        this.setState({isGovernment : "block", proceed: "none"});
    }

    nonGovernment =(e) => {
        this.setState({isGovernment : "none", proceed: "block", proceedBtn: "block", total: this.state.price, discount: 0});
        if(this.state.mobileNumber === null) {
            this.setState({creditDiv: "block"});
            this.setState({mobileDiv: "none"});
            this.setState({pCredit: true});
            this.setState({pMobile: false});
            console.log("credit true")
        }
        else if(this.state.cardNumber === null) {
            this.setState({mobileDiv: "block"});
            this.setState({creditDiv: "none"});
            this.setState({pMobile: true});
            this.setState({pCredit: false});
            console.log("mobil true")
        }
        console.log(this.state.credit);
        console.log(this.state.mobile);
        console.log(this.state.creditm);
        console.log(this.state.mobilec);
    }

    nicVerfier = (e) => {
        this.setState({nic: e.target.value});
    }

    clickVerifyNic = (e) => {
        let nicSeq = /[0-9][v]/i;
        if(this.state.nic.match(nicSeq))
        {
            fetch('http://localhost:8080/trainservice/webapi/employeedetails/employeediscount', {
                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    "nIC": this.state.nic,
                    "amount": Number(this.state.price),
                    "discountPercentage": 50
                })
            })
                .then(response => {
                    return response.json();
                })
                .then(json => {
                    this.setState({total: json.total});
                    this.setState({amount: json.amount});
                    this.setState({discount: json.discount});
                    this.setState({proceed: "block", proceedBtn: "block"});
                        console.log(json)
                    if(this.state.mobileNumber === null) {
                        this.setState({creditDiv: "block"});
                        this.setState({mobileDiv: "none"});
                        this.setState({pCredit: true});
                        this.setState({pMobile: false});
                        console.log("credit true");
                        if(json.discount===0) {
                            this.setState({total : this.state.amount})
                            alert("Not a government employee!")
                        }
                    }
                    else if(this.state.cardNumber === null) {
                        this.setState({mobileDiv: "block"});
                        this.setState({creditDiv: "none"});
                        this.setState({pMobile: true});
                        this.setState({pCredit: false});
                        console.log("mobil true")
                        if(json.discount===0) {
                            this.setState({total : this.state.amount})
                            alert("Not a government employee!")
                        }
                    }
                    console.log(this.state.cardNumber + " " + this.state.mobileNumber)
                })
                .catch(error => {
                    console.log(error)
                });
        }
        else
            console.log("see you in the morning")
    }

    onConfirmPayment(){
        if(this.state.pMobile){
            fetch('http://localhost:8080/trainservice/webapi/payment/mobilepayment', {
                method: 'POST',
                headers: {
                    'content-type': 'application/json',
                },
                body:JSON.stringify({
                    "mobileNumber": this.state.mobileNumber,
                    "pinNumber": this.state.pin,
                    "email": this.state.email,
                    "amount": this.state.total,
                    "message":
                        "Total amount for your train tickets " + this.state.total + " has been successfully payed using " + this.state.mobileNumber + ". "
                        + " No Of Tickets : " + this.state.tickets + ", from " + this.state.source + ", to " + this.state.destination + ".",
                })
            }).then(response =>{
                return response.json();
            }).then(data =>{
                if(data.paymentRespones === "Successfully Payed!") {this.setState({proceedBtn: "none", printBtn: "block", finalDiv: "none", confirmationCode: "Booking Confirmed " +data.code})}
            })
        }
        else if(this.state.pCredit){

            fetch('http://localhost:8080/trainservice/webapi/payment/creditcardpayment', {
                method: 'POST',
                headers: {
                    'content-type': 'application/json',
                },
                body:JSON.stringify({
                    "cardNo": this.state.cardNumber,
                    "cvcNo": this.state.cVC,
                    "expDate": this.state.expireDate,
                    "cardHolder": this.state.nameOnCard,
                    "amount": this.state.total,
                    "email": this.state.email,
                    "message":
                        "Total amount for your train tickets " + this.state.total + " has been successfully payed using " + this.state.cardNumber + " card. "
                           + " No Of Tickets : " + this.state.tickets + ", from " + this.state.source + ", to " + this.state.destination + ".",
                    "subject": "Payment Confirmation",
                })
            }).then(response =>{
                return response.json();
            }).then(data =>{
                if(data.paymentRespones === "Successfully Payed!")  {this.setState({proceedBtn: "none", printBtn: "block", finalDiv: "none", confirmationCode: "Booking Confirmed " +data.code})}
            })

        }
        else alert("Please select a payment method!!");
    }

    render(){
        return(
            <div className="card container-fluid">
            <div style={{display: this.state.finalDiv}}>
              <h4 className="card-header">Discount</h4>
                <div className="card-body">
                    <h6 className="card-text">Are you a government employee? Then Enter your NIC to get a 50% discount.</h6>
                    <form className="was-validated">
                    <div className="row">
                        <div className="col-sm-5">
                        <div className="custom-control custom-radio">
                            <input type="radio" onClick={this.government.bind(this)} className="custom-control-input" id="customControlValidation2"
                                   name="radio-stacked" required/>
                            <label className="custom-control-label" htmlFor="customControlValidation2">
                                Government Employee
                            </label>
                        </div>
                        </div>
                        <div className="col-sm-6">
                        <div className="custom-control custom-radio mb-3">
                            <input type="radio" onClick={this.nonGovernment.bind(this)} className="custom-control-input" id="customControlValidation3"
                                   name="radio-stacked" required/>
                            <label className="custom-control-label" htmlFor="customControlValidation3">
                                Not a Government Employee
                            </label>
                        </div>
                        </div>
                    </div>
                    </form>
                </div>
                </div>
                    <div style={{textAlign:"left", display:this.state.isGovernment}}>
                        <h6>Total amount : {this.state.price} LKR</h6>
                        <h6>Enter NIC : <input onChange={this.nicVerfier.bind()} type="text"/>&nbsp;<button onClick={this.clickVerifyNic.bind()} className="btn btn-success btn-sm">Verify</button></h6>
                    </div>
                    <div style={{display:this.state.proceed}}>
                        <h6 className="card-header">Summary</h6>
                        <h6 className="alert-info">{this.state.confirmationCode}</h6>
                        <h6><small>Source : {this.state.source}</small></h6>
                        <h6><small>Destination : {this.state.destination}</small></h6>
                        <h6><small>No of tickets : {this.state.tickets}</small></h6>
                        <h6><small>Amount : {this.state.price} LKR</small></h6>
                        <h6><small>Discount : {this.state.discount} LKR</small></h6>
                        <h6><small>Total : {this.state.total} LKR</small></h6>
                        <h6 style={{display:this.state.creditDiv}}><small>Card Number : {this.state.cardNumber}</small></h6>
                        <h6 style={{display:this.state.mobileDiv}}><small>Mobile Number : {this.state.mobileNumber}</small></h6>
                        <h6><small>Name : {this.state.nameOnCard}</small></h6>
                        <h6><small>Email : {this.state.email}</small></h6>
                        <button style={{display: this.state.proceedBtn}} onClick={this.onConfirmPayment.bind(this)} className="btn btn-success">Proceed</button>
                        <button onClick={() => window.print()} style={{display: this.state.printBtn}} className="btn btn-info">Print</button>
                    </div>
            </div>
        );
    }
}

export default Discount;