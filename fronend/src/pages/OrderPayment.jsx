import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import { useLocation } from "react-router-dom";

function OrderPayment() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const[mobile, setMobile] = useState("");
    const[address, setAddress] = useState("");
    const[delivery, setDelivery] = useState("");
    const[payment, setPayment] = useState("");
    

    const location = useLocation();

    const order = location.state?.order;

    function approveOrder(e) {
        e.preventDefault();

        if(mobile === "" || address === "" || delivery === "" || payment === ""){
            alert("Input fields can't be empty.");
            return;
        }

        const orderDto = { 
            oid:order.oid,
            status:1,
            mobile:mobile,
            address:address,
            delivery:delivery,
            payment:payment
        }

        const requestDto = {
            userId:userId,
            orderDto:orderDto
        } 

        RestService.approveOrder(token,requestDto).then((res)=>{
            if(res.data.success){
                alert('Payment Success');
                window.location.href = '/view_user_orders'; 
               
            } else {
                alert('fail');
            }
        }).catch((err)=>{
            console.log(err);
            alert('fail');
        });
    }

    return ( 
        <div>
            <h1>- PAYMENT -</h1>
            <div className="p-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="row">
                        <div className="col">
                            <label>Name</label>
                            <input type="text" className="form-control" value={order.cusName} readOnly />
                        </div>
                        <div className="col">
                            <label>Mobile</label>
                            <input type="text" className="form-control" onChange={(e)=>setMobile(e.target.value)} />
                        </div>
                    </div>
                    <div className="row mt-3">
                        <div className="col">
                        <label>Address</label>
                        <textarea className="form-control"  cols="30" rows="5" onChange={(e)=>setAddress(e.target.value)}/>
                        </div>
                    </div>
                    <div className="row mt-3">
                        <div className="col">
                            <label>Delivery Method</label>
                            <select class="form-control" onChange={(e)=>setDelivery(e.target.value)} >
                                <option>Select Method</option>
                                <option value="PICKUP">Pickup</option>
                                <option value="POST" >By Post</option>
                            </select>
                        </div>
                        <div className="col">
                            <label>Payment</label>
                            <select class="form-control" onChange={(e)=>setPayment(e.target.value)} >
                                <option>Select Method</option>
                                <option value="ONLINE">Visa/Master</option>
                                <option value="CASH" >Cash on delivery</option>
                            </select>
                        </div>
                    </div>

                    { payment == 'ONLINE' &&

                        <div className="card p-4 mt-3">
                            <div className="row mt-3">
                                <div className="col">
                                    <label>Card Number</label>
                                    <input type="text" className="form-control" />
                                </div>
                                <div className="col">
                                    <label>CVV</label>
                                    <input type="text" className="form-control" />
                                </div>
                            </div>
                            <div className="row mt-3">
                                <div className="col">
                                    <label>Year</label>
                                    <input type="text" className="form-control" />
                                </div>
                                <div className="col">
                                    <label>Month</label>
                                    <input type="text" className="form-control" />
                                </div>
                            </div>
                        </div>            

                    }
                    <h4 className="mt-3">Total Amount (LKR) : {order.total}</h4>
                    <div className="text-center mt-5">
                        <button className="btn btn-primary" onClick={approveOrder}>Confirm & Pay</button>
                    </div>

                </form>
            </div>
        </div>
     );
}

export default OrderPayment;