import React,{useEffect, useState} from "react";
import { useParams } from 'react-router-dom';
import RestService from "../services/RestService";
import {Link} from 'react-router-dom';
import Avator from "../images/profile.png";

function PharmacyProfile() {

    const { pid } = useParams();

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const [owner, setOwner] = useState("");
    const [pharmacyName, setPharmacyName] = useState("");
    const [registeredId, setRegisteredId] = useState("");
    const [email, setEmail] = useState("");
    const [city, setCity] = useState("");
    const [address, setAddress] = useState("");
    const [about, setAbout] = useState("");

    const [pharmacyDto, setPharmacyDto] = useState([]);


    useEffect(()=> {
        if(isLogged){

            const requestDto = {
                pharmacyId: pid
            }
            RestService.viewPharmacy(token, requestDto).then((res)=>{
                console.log(res.data.pharmacydto)
                if(res.data.success == true){
                    
                    setPharmacyDto(res.data.pharmacydto);
    
                } 
            }).catch((err)=>{
                console.log(err);
            })   
        } else {
            window.location.href = '/login'; 
        }
       

    },[])

    function viewPharmacy(){
            
        
    }

    return (  
        <div className="row">

            {/* first-card */}

            <div className="col-4">

                <div className="card" style={{height: '570px', marginTop: '50px', borderRadius: '25px', marginLeft:'30px'}}>

                <div className="text-center mt-5">
                <img src={Avator} alt="profile image" style={{width:'100px'}} />
                </div>

                    <div className="pharmacy-details" style={{ marginTop: '100px', marginLeft: '30px' }}>

                        <label>{pharmacyDto.owner}</label> <br />
                        <label>{pharmacyDto.pharmacyName}</label> <br />
                        <label>{pharmacyDto.registeredId}</label> <br />
                        <label>{pharmacyDto.email}</label> <br />
                        <label>{pharmacyDto.city}</label> <br />
                        <label>{pharmacyDto.address}</label>
                        
                    </div>
                </div>

            </div>

            {/* second-card */}
            <div className="col">

                <div className="card" style={{ height: '570px', marginTop: '50px', borderRadius: '25px', marginRight:'30px'}}>

                    <div className="textarea-1-about" style={{marginTop:'20px', marginLeft:'30px'}}>

                        <Link className="btn btn-primary mt-3" style={{borderRadius:'20px', width:'100px', height:'40px', marginLeft:'520px', marginRight:'35px'}} to={{
                        pathname: '/request_quotation/'+ pid,
                        }} >Request</Link>
                        <h4 className="text-secondary">About</h4>

                        <div className="card p-3" style={{marginTop:'20px', borderRadius:'5px', width:'95%',height:'30vh'}}>
                            <p>{pharmacyDto.about}</p>
                        </div>      

                    </div>        

                </div>

            </div>

        </div>
    );

}

export default PharmacyProfile;