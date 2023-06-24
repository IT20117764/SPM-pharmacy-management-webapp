import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import Avator from "../images/profile.png";


function CreatePharmacyProfile() {


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


    useEffect(()=> {
        if(isLogged){

        } else {
            window.location.href = '/login'; 
        }
    },[])


    function savePharmacy(){

        if(owner === "" || pharmacyName ==="" || registeredId === "" || email === "" || city === "" || address === "" || about === ""){
            alert("input fields can't be empty");
            return;
        }

        const requestDto = {
            pharmacyDto:{
                owner: owner,
                pharmacyName: pharmacyName,
                registeredId: registeredId,
                email: email,
                city: city,
                address: address,
                about: about
            },
            userId: userId
        }

        RestService.savePharmacy(token, requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Successfully added pharmacy details')
                sessionStorage.clear();
                window.location.reload(false);
            } else {
                alert('failed to added data')
            }
        }).catch((err)=>{
            console.log(err);
        })
    }

    return (  
        

            <div className="card" style={{width:'900px', height:'600px', marginTop:'40px', borderRadius:'25px', margin:'auto'}}>
                 <div className="text-center mt-5">
                <img src={Avator} alt="profile image" style={{width:'100px'}} />
                </div>

                <div className="row" style={{marginTop:'50px'}}>
                    {/* column-1 */}
                    <div className="col" style={{marginLeft:'50px'}}>
                        <input type="text" onChange={(e)=>setOwner(e.target.value)} className="form-control" placeholder="Owner" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>
                        <input type="text" onChange={(e)=>setEmail(e.target.value)} className="form-control" placeholder="Email" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>
                        <input type="text" onChange={(e)=>setRegisteredId(e.target.value)} className="form-control" placeholder="Registered ID" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>

                    </div>

                    {/* column-2 */}
                    <div className="col" style={{marginLeft:'50px', marginRight:'50px'}}>

                      <input type="text" onChange={(e)=>setPharmacyName(e.target.value)} className="form-control" placeholder="Pharmacy Name" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>
                      <input type="text" onChange={(e)=>setCity(e.target.value)} className="form-control" placeholder="City" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>
                      <input type="text" onChange={(e)=>setAddress(e.target.value)} className="form-control" placeholder="Address" style={{borderRadius:'10px', height:'35px', width:'350px', marginBottom:'30px'}}/>

                    </div>

                    <textarea className="form-control" onChange={(e)=>setAbout(e.target.value)}  name="About" id="" cols="30" rows="5" style={{borderRadius:'10px', width:'85%', marginLeft:'65px',}} placeholder="About">

                    </textarea>

                </div>

                <button className="btn btn-primary mt-3" onClick={savePharmacy} style={{borderRadius:'20px', width:'100px', height:'40px', marginLeft:'740px', marginRight:'35px'}}>Save</button>

            </div>
            
        
    );
}

export default CreatePharmacyProfile;