import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import {Link} from 'react-router-dom';
import autoTable from 'jspdf-autotable';
import jsPDF from "jspdf";

function ViewUserOrders() {

    
    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const[ordersList, setOrdersList] = useState([]);
    const[selectedOrder, setSelectedOrder] = useState([]);

    const[filteredList, setFilteredList] = useState([])

    useEffect(()=> {

        if(isLogged){

            const requestDto = {
                userId:userId
            } 

            RestService.findUserOrders(token,requestDto).then((res)=>{
                setFilteredList(res.data.orderDtoList);
                setOrdersList(res.data.orderDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    function changeOrderStatus(oid,status) {

        const orderDto = {
            status:status,
            oid:oid
        }

        const requestDto = {
            orderDto:orderDto
        } 

        RestService.changeOrderStatus(token,requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Order Updated')
                window.location.reload(false);
            } else {
                alert('failed to reject the order')
            }
        }).catch((err)=>{
            console.log(err);
        });

    }

    function search(searchVal) {

        let temp = [];

        if(searchVal != ""){
            for(const i of ordersList) {
                if(i.cusName.toLowerCase().startsWith(searchVal.toLowerCase()) || i.status == searchVal){
                    temp.push(i);
                }
            }
    
            setFilteredList(temp);
        } else {
            setFilteredList(ordersList);
        }
       
    }

    function generateReport() {
        const doc = new jsPDF()

        doc.text("My Orders", 15, 10);
        
        autoTable(doc, { html: '#my_orders' })

        doc.save('Orders.pdf')
    }

    

    return ( 
        <div>
            <h1>- ORDERS -</h1>
            <div className="p-5">

                <div className=" row mb-3 p-3">
                    <input type="text" class="form-control col mr-4"  style={{borderRadius:'25px'}} id="search"  placeholder="Search" onChange={(e)=> search(e.target.value)} />    
                    <select class="form-control col-3" style={{borderRadius:'25px'}} onChange={(e)=> search(e.target.value)}  >
                        <option value="">Select status...</option>
                        <option value="0">PENDING</option>
                        <option value="1" >APPROVED</option>
                        <option value="2" >COMPLETED</option>
                        <option value="3" >REJECTED</option>
                    </select>                 
                </div>
                <button className="btn btn-secondary mt-3" style={{borderRadius:'25px', width:'5vw'}} onClick={generateReport} >Report</button>
                <table className="table table-hover table-light mb-5 mt-3" id='my_orders'>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Customer Name</th>
                            <th scope="col">Status</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            filteredList.map(
                                order =>
                                <tr key={order.oid}>
                                    <td>{order.oid}</td> 
                                    <td>{order.cusName}</td>        
                                    <td>
                                        {order.status == 0 && "PENDING"}
                                        {order.status == 1 && "APPROVED"}
                                        {order.status == 2 && "COMPLETED"}
                                        {order.status == 3 && "REJECTED"}
                                    </td> 
                                
                                    <td className="text-center">

                                        <button type="button" className="btn btn-secondary btn-sm mr-2" data-toggle="modal" data-target="#viewOrder" onClick={(e)=>{setSelectedOrder(order)}} >View</button>
                                        
                                        {order.status == 0 && 
                                        <div>
                                            <Link className="btn btn-secondary btn-sm mr-2" state={{order:order}} to={{
                                                pathname: '/payment'
                                            }} >Approve</Link>
                                            <button type="button" className="btn btn-secondary btn-sm" onClick={(e)=>{changeOrderStatus(order.oid, 3)}} >Reject</button>    
                                        </div>
                                        }

                                        {order.status == 1 && 
                                            <button type="button" className="btn btn-secondary btn-sm" onClick={(e)=>{changeOrderStatus(order.oid, 2)}} >Mark As Completed</button>
                                        }

                                        
                                    </td> 
                                </tr>
                            )                                             
                        }
                    </tbody>
                </table>
            </div>


        {/* Order Modal */}
        <div class="modal fade" id="viewOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">View Order</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body p-5">
                
                <div style={{margin:'auto'}}>

                    <div className="row">
                        <div className="col-4">
                            <label>Customer Name</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.cusName}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Date</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.date}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Amount (LKR)</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.total}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Payment</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.payment}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Delivery</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.delivery}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Mobile</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.mobile}</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-4">
                            <label>Address</label>
                        </div>
                        <div className="col">
                            <label> {selectedOrder.address}</label>
                        </div>
                    </div>

                </div>



                <div className="row mt-3" style={{height:'200px', overflow:'auto'}}>
                    <table className="table table-hover " >
                        <thead>
                            <tr>
                                <th scope="col">Item Name</th>
                                <th scope="col">Qty</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            { selectedOrder.length != 0 &&
                                    selectedOrder.orderItems.map(
                                        item =>
                                        <tr key={item.itemId}>
                                            <td>{item.itemName}</td>        
                                            <td>{item.qty}</td> 
                                            <td>{item.pricePerItem}</td>
                                        </tr>
                                    )                                             
                            }
                        </tbody>
                    </table>
                </div>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                </div>
            </div>
        </div>



         
        </div>
     );
}

export default ViewUserOrders;