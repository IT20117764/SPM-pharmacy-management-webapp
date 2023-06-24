import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import Base64Downloader, { triggerBase64Download } from "react-base64-downloader";
import {Link} from 'react-router-dom';

function ViewUserRequests() {
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

            RestService.findUserRequests(token,requestDto).then((res)=>{
                if(res.data.quotationDtoList != null) {
                    setRequestList(res.data.quotationDtoList);
                    setFilteredList(res.data.quotationDtoList);
                }                 
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    function deleteRequest(qid) {
        const requestDto = {
            quotationId:qid
        } 

        RestService.deleteQuotationById(token,requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Successfully deleted the request')
                window.location.reload(false);
            } else {
                alert('failed to delete the data')
            }
        }).catch((err)=>{
            console.log(err);
        });

    }

    function search(searchVal) {

        let temp = [];

        if(searchVal != ""){

            for(const i of requestList) {
                if(i.userDto.firstName.toLowerCase().startsWith(searchVal.toLowerCase()) || i.userDto.lastName.toLowerCase().startsWith(searchVal.toLowerCase()) ||  i.status == searchVal ){
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
            <h1>- REQUESTS -</h1>
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
                            <th scope="col">Pharmacy Name</th>
                            <th scope="col">Status</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {  filteredList.length != 0 &&
                            filteredList.map(
                                request =>
                                <tr key={request.id}>
                                    <td>{request.id}</td> 
                                    <td>{request.pharmacyName}</td>        
                                    <td>
                                        {request.status == 0 && "PENDING"}
                                        {request.status == 1 && "SENT"}
                                    </td> 
                                
                                    <td className="text-center">
                                        <Base64Downloader className='btn btn-secondary btn-sm mr-2' downloadName={request.userDto.firstName} base64={request.prescription}>
                                            View
                                        </Base64Downloader>
                                        <button className="btn btn-danger btn-sm" onClick={(e)=>deleteRequest(request.id)} >Delete</button>
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

export default ViewUserRequests;