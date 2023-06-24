import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import Base64Downloader, { triggerBase64Download } from "react-base64-downloader";
import {Link} from 'react-router-dom';


function ViewPharmacyRequests() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const[requestList, setRequestList] = useState([]);
    const[selectedOrder, setSelectedOrder] = useState([]);

    const[filteredList, setFilteredList] = useState([])


    useEffect(()=> {

        if(isLogged){

            const requestDto = {
                userId:userId
            } 

            RestService.findPharmacyRequests(token,requestDto).then((res)=>{
                setFilteredList(res.data.quotationDtoList);
                setRequestList(res.data.quotationDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])


    function search(searchVal) {

        let temp = [];

        if(searchVal != ""){

            for(const i of requestList) {
                if(i.userDto.firstName.toLowerCase().startsWith(searchVal.toLowerCase()) || i.userDto.lastName.toLowerCase().startsWith(searchVal.toLowerCase()) || i.status == searchVal ){
                    temp.push(i);
                }
            }
    
            setFilteredList(temp);
        } else {
            setFilteredList(requestList);
        }
       
    }

    return ( 
        <div>
            <h1>- Requests -</h1>
            <div className="p-5">
                <div className=" row mb-3 p-3">
                    <input type="text" class="form-control col mr-4"  style={{borderRadius:'25px'}} id="search"  placeholder="Search" onChange={(e)=> search(e.target.value)} />    
                        <select class="form-control col-3" style={{borderRadius:'25px'}} onChange={(e)=> search(e.target.value)}  >
                            <option value="">Select status...</option>
                            <option value="0">PENDING</option>
                            <option value="1" >SENT</option>
                        </select>                 
                </div>
                <table className="table table-hover table-light mb-5 mt-3" id='item-tbl'>
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
                                request =>
                                <tr key={request.id}>
                                    <td>{request.id}</td> 
                                    <td>{request.userDto.firstName} {request.userDto.lastName}</td>        
                                    <td>
                                        {request.status == 0 && "PENDING"}
                                        {request.status == 1 && "SENT"}

                                    </td> 
                                
                                    <td className="text-center">
                                    <Base64Downloader className='btn btn-secondary btn-sm mr-2' downloadName={request.userDto.firstName} base64={request.prescription}>
                                        View
                                    </Base64Downloader>
                                    <Link className="btn btn-primary btn-sm" state={{request:request}} to={{
                                            pathname: '/create_invoice/' + request.id
                                        }} >Sent Offer</Link>

                                    </td> 
                                </tr>
                            )                                             
                        }
                    </tbody>
                </table>
            </div>
         
        </div>
     );
}

export default ViewPharmacyRequests;