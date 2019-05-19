import React, {Component} from "react";
import CreditCardInput from 'react-credit-card-input';
import {withRouter} from 'react-router-dom';
import {M,MOBILENUMBER} from './mobilePay';

export const NAME = "NAME";
export const EMAIL = "EMAIL";
export const CC = "CC";
export const C = "false";
export const MC = "false";
export const CVC = "CVC";
export const EXP = "EXP";
export const PRICE = "PRICE";

class CreditCardDetails extends Component{

    constructor(props){
        super(props);
        this.state={
            name:"",
            email:"",
            cardNumber:"",
            cvc:"",
            expire:"",
            m: sessionStorage.getItem(M),
            price: this.props.price
        }
    }

    componentDidMount() {
        sessionStorage.removeItem(MOBILENUMBER)
    }

    nameSetter=(e)=>{
        this.setState({name:e.target.value});
    }

    emailSetter=(e)=>{
        this.setState({email:e.target.value});
    }

    submitCCDetails(e){
        e.preventDefault();
        // sessionStorage.setItem(C, "true");
        if(this.state.name !== "" && this.state.cardNumber !== ""
            && this.state.cvc !== "" && this.state.expire !== ""){
            console.log("inside");
            sessionStorage.setItem(NAME, this.state.name);
            sessionStorage.setItem(EMAIL, this.state.email);
            sessionStorage.setItem(CC, this.state.cardNumber);
            sessionStorage.setItem(CVC, this.state.cvc);
            sessionStorage.setItem(EXP, this.state.expire);
            sessionStorage.setItem(PRICE, this.state.price);
            // sessionStorage.setItem(MC,"false");
            this.props.history.push(`/discount`);
        }else{
            alert("Fill the payment details to proceed!");
        }
    }

    render(){
        return(
            <div>
                <h5 className="card-header">Enter Card Details</h5>

                {/*<h6 className="card-header" onChange={this.nameSetter.bind()} style={{textAlign:"left"}}>Name on card <input type="text"/></h6>*/}
                {/*<h6 className="card-header" onChange={this.emailSetter.bind()} style={{textAlign:"left"}}>Email <input type="email"/></h6>*/}
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <h6 className="card-text">Name on card</h6>
                        </td>
                        <td style={{textAlign:"left"}}>
                            <h6 className="card-text" onChange={this.nameSetter.bind()}><input type="text"/></h6>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h6 className="card-text">Email</h6>
                        </td>
                        <td style={{textAlign:"left"}}>
                            <h6 className="card-text" onChange={this.emailSetter.bind()}><input type="email"/></h6>
                        </td>
                    </tr>
                <tr>
                    <td><h6 className="card-text">Card Details</h6></td>
                    <td>
                        <CreditCardInput
                            cardNumberInputProps={{ value: <input type="text"/> , onChange: this.handleCardNumberChange }}
                            cardExpiryInputProps={{ value: <input type="text"/>, onChange: this.handleCardExpiryChange }}
                            cardCVCInputProps={{ value: <input type="text"/>, onChange: this.handleCardCVCChange }}
                            cardCVCInputProps={{
                                onBlur: e => console.log('cvc blur', e),
                                onChange: e => console.log('cvc change', e),
                                onError: err => console.log(`cvc error: ${err}`)
                            }}
                            cardExpiryInputProps={{
                                onBlur: e => console.log('expiry blur', e),
                                onChange: e => console.log('expiry change', e),
                                onError: err => console.log(`expiry error: ${err}`)
                            }}
                            cardNumberInputProps={{
                                onBlur: e => console.log('number blur', e),
                                onChange: e => console.log('number change', e),
                                onError: err => console.log(`number error: ${err}`)
                            }}
                            cardCVCInputRenderer={({ handleCardCVCChange, props }) => (
                                <input
                                    {...props}
                                    onChange={handleCardCVCChange(e => this.setState({cvc : e.target.value})
                                    )}
                                />
                            )}
                            cardExpiryInputRenderer={({ handleCardExpiryChange, props }) => (
                                <input
                                    {...props}
                                    onChange={handleCardExpiryChange(e => this.setState({expire : e.target.value})
                                    )}
                                />
                            )}
                            cardNumberInputRenderer={({ handleCardNumberChange, props }) => (
                                <input
                                    {...props}
                                    onChange={handleCardNumberChange(e => this.setState({cardNumber : e.target.value})
                                    )}
                                />
                            )}
                            fieldClassName="input"
                        />
                    </td>
                </tr>
                <tr>
                    <td>
                        <button className="btn btn-success" onClick={this.submitCCDetails.bind(this)}>Proceed</button>
                    </td>
                </tr>
                    </tbody>
                </table>
            </div>
        )
    }

}

export default withRouter(CreditCardDetails);