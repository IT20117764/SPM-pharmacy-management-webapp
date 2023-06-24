import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import Header from "../components/Header";
import PharmacyCard from '../components/PharmacyCard';
import RestService from "../services/RestService";
import {Link} from 'react-router-dom';
import MedicineCard from "../components/MedicineCard";

function Home(props) {

    //common
    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));


    const[pharmacyList, setPharmacyList] = useState([]);
    const[medicineList, setMedicineList] = useState([]);
    const[searchType, setSearchType] = useState(0);


    const[enableSearch, setEnableSearch] = useState(false);



    const[searchedMedList, setSearchedMedList] = useState([]);
    const[searchedPharmacyList, setSearchedPharmacyList] = useState([]);

    const[pharmacySearchType,setPharmacySearchType] = useState(0)



 
    
    useEffect(()=> {
        if(isLogged){

            let requestDto = {requestDto:[]}

            RestService.getAllPharmaciesAndMedicines(token,requestDto).then((res)=>{
                setMedicineList(res.data.medicineDtoList)
                setPharmacyList(res.data.pharmacyDtoList);
            }).catch((err)=>{
                console.log(err);
            });
        } else {
            window.location.href = '/login'; 
        }
    },[])


    function handleSearch(searchValue){

        if(searchValue == ""){
            let temp = [];
            setSearchedMedList(temp);
            setSearchedPharmacyList(temp);
            setEnableSearch(false);

        } else if(searchType == 0){
            setEnableSearch(true);
            let temp = [];



            if(pharmacySearchType == 0){
                for(const p of pharmacyList) {
                    if(p.city.toLowerCase().startsWith(searchValue.toLowerCase())){
                        temp.push(p);
                    }
                }
            } else if(pharmacySearchType == 1){
                for(const p of pharmacyList) {
                    if(p.pharmacyName.toLowerCase().startsWith(searchValue.toLowerCase())){
                        temp.push(p);
                    }
                }
            }
  
            setSearchedPharmacyList(temp);

        } else if(searchType == 1){
            setEnableSearch(true);
            let temp = [];
            for(const m of medicineList) {
                if(m.name.toLowerCase().startsWith(searchValue.toLowerCase())){
                    temp.push(m);
                }
            }

            setSearchedMedList(temp);
        }    
    }


    

    return ( 
        <div className="" >
            <input type="text" class="form-control mt-3"  style={{borderRadius:'25px',width:'50%', margin:'auto'}} id="search"  placeholder="Search" onChange={(e)=>handleSearch(e.target.value)} />

            <div className="row mt-3" style={{width:'40%', margin:'auto'}}>
                <div className="col">
                    <div className="row">                
                        <div className="form-group col">
                            <select class="form-control" style={{borderRadius:'25px'}}  value={searchType} onChange={(e)=>{setSearchType(e.target.value); setEnableSearch(false)}} >
                                <option>Select type...</option>
                                <option value="0">Pharmacies</option>
                                <option value="1" >Medicines</option>
                            </select>
                        </div>
                        {searchType == 0 &&
                        <div className="form-group col">
                            <select class="form-control" style={{borderRadius:'25px'}}  onChange={(e)=>{setPharmacySearchType(e.target.value)}} >
                                <option selected>Search By</option>
                                <option value="0">City</option>
                                <option value="1" >Name</option>
                            </select>
                        </div>
                        }                       
                    </div>
                </div>
                
            </div>
            
            <br/>
            

            {enableSearch == false &&             
            <div className="mb-5"  style={{paddingLeft:'10%', paddingRight:'10%'}}>
                {searchType==0 && pharmacyList.map(
                    pharmacy =><PharmacyCard pharmacy={pharmacy} />
                )}

                {searchType==1 && medicineList.map(
                    med =><MedicineCard medicine={med} />
                )}     

            </div> 
            }

            {enableSearch == true &&
            <div className="mb-5"  style={{paddingLeft:'10%', paddingRight:'10%'}}>
                <i>Search result : </i>
                
                {searchType==0 && searchedPharmacyList.map(
                    pharmacy =><PharmacyCard pharmacy={pharmacy} />
                )}

                {searchType==1 && searchedMedList.map(
                    med =><MedicineCard medicine={med} />
                )}     

            </div>           
            }           
            
        </div>
     );
}

export default Home;