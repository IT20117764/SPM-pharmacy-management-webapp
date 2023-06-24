import React,{useEffect, useState} from "react";
import Avator from "../images/profile.png";
import Edit from "../images/pencile.jpg";
import Delete from "../images/delete.png";
import RestService from "../services/RestService";


function Profile() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    const[user, setUser] = useState([]);
    const[pharmacy, setPharmacy] = useState([]);


    const[fname, setFname] = useState();
    const[lname, setLname] = useState();
    const[mobile, setMobile] = useState();
    const[pname, setPname] = useState();
    const[rid, setRid] = useState();
    const[city, setCity] = useState();
    const[address, setAddress] = useState();
    const[email, setEmail] = useState();
    const[about, setAbout] = useState();

    function setValues() {
        setFname(user.firstName);
        setLname(user.lastName);
        setMobile(user.mobile);
        setPname(pharmacy.pharmacyName);
        setRid(pharmacy.registeredId);
        setCity(pharmacy.city);
        setAddress(pharmacy.address);
        setEmail(user.email)
        setAbout(pharmacy.about)
    }


    useEffect(()=> {
        if(isLogged){

            const requestDto = {
                userId: userId
            }

            RestService.getProfile(token, requestDto).then((res)=>{

                if(res.data.success == true){  
                    console.log(res.data)       
                    setUser(res.data.user);
                    setPharmacy(res.data.pharmacydto);
                } 

            }).catch((err)=>{
                console.log(err);
            })   
        } else {
            window.location.href = '/login'; 
        }
       

    },[])


    function updateDetails(){
        const userDto = {
            userId:userId,
            firstName:fname,
            lastName:lname,
            email:email,
            mobile:mobile
        }

        const pharmacyDto = {
            pid:pharmacy.pid,
            pharmacyName:pname,
            about:about,
            address:address,
            registeredId:rid,
            city:city
        }

        const requestDto = {
            userDto:userDto,
            pharmacyDto:pharmacyDto
        }

        RestService.updatePharmacy(token, requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Successfully updated pharmacy details');
                sessionStorage.setItem("firstname", fname);
                sessionStorage.setItem("lastname", lname);         
                window.location.reload(false);
                
            } else {
                alert('failed to update data')
            }
        }).catch((err)=>{
            console.log(err);
        })
    }

    function deletePharmacy(){

        const requestDto = {
            pharmacyId:pharmacy.pid,
        }

        RestService.deletePharmacy(token, requestDto).then((res)=>{
            if(res.data.success == true){
                alert('Successfully deleted pharmacy profile');
                sessionStorage.clear();       
                window.location.reload(false);
                
            } else {
                alert('failed to delete data')
            }
        }).catch((err)=>{
            console.log(err);
        })
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

                    <label>{pharmacy.owner}</label> <br />
                    <label>{pharmacy.pharmacyName}</label> <br />
                    <label>{pharmacy.registeredId}</label> <br />
                    <label>{user.email}</label> <br />
                    <label>{pharmacy.address}</label> <br />
                    <label>{pharmacy.city}</label>
                    
                </div>

                <div className="update&delete" style={{display: "flex", justifyContent: "space-between"}}>
                <img src={Edit} alt="edit-icon" style={{width: '18px', marginLeft: '250px',cursor: 'pointer'}} data-toggle="modal" data-target="#editProfile" onClick={setValues} />
                <img src={Delete} alt="edit-icon" style={{width: '18px', marginRight: '15px', cursor: 'pointer'}} data-toggle="modal" data-target="#delete" />
                </div>

            </div>

            

        </div>

        {/* second-card */}
        <div className="col">

            <div className="card" style={{ height: '570px', marginTop: '50px', borderRadius: '25px', marginRight:'30px'}}>

                <div className="textarea-1-about" style={{marginTop:'20px', marginLeft:'30px'}}>
                    
                <h4 className="text-secondary">About</h4>

        
                <div className="card p-3" style={{marginTop:'20px', borderRadius:'5px', width:'95%',height:'30vh'}}>
                    <p>{pharmacy.about}</p>
                </div>  

                </div>
            </div>

        </div>


        <div class="modal fade" id="editProfile" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Edit Profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body p-5">
                <div className="row">
                    <div className="col">
                        <label>First Name</label>
                        <input type="text" value={fname} className="form-control" onChange={(e)=>setFname(e.target.value)} />
                    </div>
                    <div className="col">
                        <label>Last Name</label>
                        <input type="text" value={lname} className="form-control" onChange={(e)=>setLname(e.target.value)} />
                    </div>
                </div>

                <div className="row mt-2">
                    <div className="col">
                        <label>Mobile</label>
                        <input type="text" value={mobile} className="form-control" onChange={(e)=>setMobile(e.target.value)} />
                    </div>
                    <div className="col">
                        <label>Pharmacy Name</label>
                        <input type="text" value={pname} className="form-control" onChange={(e)=>setPname(e.target.value)} />
                    </div>
                </div>

                <div className="row mt-2">
                    <div className="col">
                        <label>Register ID</label>
                        <input type="text" value={rid} className="form-control" onChange={(e)=>setRid(e.target.value)} />
                    </div>
                    <div className="col">
                        <label>City</label>
                        <input type="text" value={city} className="form-control" onChange={(e)=>setCity(e.target.value)} />
                    </div>
                </div>

                <div className="row mt-2">
                    <div className="col">
                        <label>Address</label>
                        <textarea className="form-control" value={address}  cols="30" rows="5" onChange={(e)=>setAddress(e.target.value)} />
                    </div>
                </div>

                <div className="row mt-2">
                    <div className="col">
                        <label>About</label>
                        <textarea className="form-control" value={about}  cols="30" rows="5" onChange={(e)=>setAbout(e.target.value)} />
                    </div>
                    
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onClick={updateDetails} >Update</button>
            </div>
            </div>
        </div>
        </div>


        {/* Delete Modal */}
        <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Delete Profile</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    Do you want to delete your profile?
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger"  onClick={deletePharmacy}>Delete</button>
                </div>
                </div>
            </div>
        </div>


    </div>

     );
}

export default Profile;