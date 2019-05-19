import React,{Component} from "react";
import {withRouter} from 'react-router-dom';
import {CC} from './cc';
export const M = "false";
export const CM = "false";
export const MOBILENUMBER = "MOBILENUMBER";
export const PIN = "PIN";
export const MOBILEEMAIL = "MOBILEEMAIL";

class MobilePay extends Component{
    constructor(props) {
        super(props);
        this.state = {
            mobileNubmer:"",
            pinNumber:"",
            email:"",
            isMobile:"none",
            isPIN:"none",
            isEmail:"none"
        }
    }

    componentDidMount() {
        sessionStorage.removeItem(CC);
    }

    mobileChange = (e) => {
        this.setState({mobileNubmer: e.target.value});
    }
    pinChange = (e) => {
        this.setState({pinNumber: e.target.value});
    }
    emailChange = (e) => {
        this.setState({email: e.target.value});
    }

    mobileSubmit(e){
        e.preventDefault();
        const emailregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        // sessionStorage.setItem(M, "true");
        if(this.state.mobileNubmer.length === 10) this.setState({isMobile:"none"})
        if(this.state.mobileNubmer.length !== 10) this.setState({isMobile:"block"})
        if(this.state.pinNumber.length === 4) this.setState({isPIN:"none"})
        if(this.state.pinNumber.length !== 4) this.setState({isPIN:"block"})
        if(this.state.email.match(emailregex)) this.setState({isEmail:"none"})
        if(!this.state.email.match(emailregex)) this.setState({isEmail:"block"})
        else {
            this.setState({isMobile:"none"});
            this.setState({isPIN:"none"});
            this.setState({isEmail:"none"});
            sessionStorage.setItem(MOBILEEMAIL, this.state.email);
            sessionStorage.setItem(MOBILENUMBER, this.state.mobileNubmer);
            sessionStorage.setItem(PIN, this.state.pinNumber);
            // sessionStorage.setItem(CM, "false");
            this.props.history.push(`/discount`);
            console.log(this.state.mobileNubmer.length);
            console.log(this.state.pinNumber);
            console.log(this.state.email);
        }
    }

    render(){
        return(
            <div className="card">
                <h5 className="card-header">Dialog Mobile Pay</h5>
                <table>
                    <tr>
                        <td>
                            <h6 className="card-text">Enter Dialog Moblile Number </h6>
                        </td>
                        <td>
                            <small className="badge-danger" style={{display:this.state.isMobile}}>Wrong!</small>
                        </td>
                        <td>
                            <h6 onChange={this.mobileChange.bind()} className="card-text"><input type="text" required/></h6>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h6 className="card-text">Enter 4 digit PIN Number </h6>
                        </td>
                        <td>
                            <small className="badge-danger" style={{display:this.state.isPIN}}>Wrong!</small>
                        </td>
                        <td>
                            <h6 onChange={this.pinChange.bind()} className="card-text"><input type="text" required/></h6>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h6 className="card-text">Enter Email </h6>
                        </td>
                        <td>
                            <small className="badge-danger" style={{display:this.state.isEmail}}>Wrong!</small></td>
                        <td>
                            <h6 onChange={this.emailChange.bind()} className="card-text"><input type="email" required/></h6>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button onClick={this.mobileSubmit.bind(this)} className="btn btn-success" >Proceed</button>
                        </td>
                    </tr>
                </table>
            </div>
        )
    }

}

export default withRouter(MobilePay);