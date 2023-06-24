import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import Base64Downloader, { triggerBase64Download } from "react-base64-downloader";
import {Link} from 'react-router-dom';

function ViewPharmacyList() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const[pharmacyList, setPharmacyList] = useState([]);

 
   


    useEffect(()=> {

        if(isLogged){
            const requestDto = {
                userId:userId
            } 

            RestService.getPharmacyList(token,requestDto).then((res)=>{
                setPharmacyList(res.data.pharmacyDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    


    return ( 
        <div>
            <h1>- PHARMACIES -</h1>
            <div className="p-5">
                <table className="table table-hover table-light mb-5 mt-3" id='item-tbl'>
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Owner</th>
                            <th scope="col">Pharmacy Name</th>
                            <th scope="col">City</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            pharmacyList.map(
                                pharmacy =>
                                <tr key={pharmacy.pid}>
                                    <td>{pharmacy.pid}</td> 
                                    <td>{pharmacy.owner}</td>        
                                    <td>{pharmacy.pharmacyName}</td>
                                    <td>{pharmacy.city}</td>
                                    <td className="text-center">
                                    
                                    <Link className="btn btn-secondary btn-sm" to={{
                                        pathname: '/pharmacy_profile/'+ pharmacy.pid
                                        }} >View</Link>
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

export default ViewPharmacyList;