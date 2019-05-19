import React from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import TrainInfo from "./components/train.info";
import Payment from "./components/payment.info";
import Discount from "./components/discount";
import "./App.css";
import CreditCardDetails from "./components/cc";

function App() {
  return (
    <div className="container-fluid">
        <Router>
            <Switch>
                <Route exact path="/" component={TrainInfo} />
                <Route path="/payment" component={Payment} />
                <Route path="/discount" component={Discount} />
                <Route path="/creditcard" component={CreditCardDetails}/>
                <Route component={Discount} />
            </Switch>
        </Router>
    </div>
  );
}

export default App;
