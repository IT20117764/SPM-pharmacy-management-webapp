import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";

function EditUserProfile() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));
    const[role, setRole] = useState(sessionStorage.getItem("role"));
    

    const[fname, setFname] = useState();
    const[lname, setLname] = useState();
    const[email, setEmail] = useState();
    const[mobile, setMobile] = useState();
    const[uname, setUname] = useState();
    const[password, setPassword] = useState();


    useEffect(()=> {
        if(isLogged){
 
            const requestDto = {
                userId: userId
            }

            RestService.getProfile(token, requestDto).then((res)=>{
                
                if(res.data.success == true){      
                    setFname(res.data.user.firstName);
                    setLname(res.data.user.lastName);
                    setEmail(res.data.user.email);
                    setMobile(res.data.user.mobile);
                    setUname(username);
                } 

            }).catch((err)=>{
                console.log(err);
            })   
        } else {
            window.location.href = '/login'; 
        }
       

    },[])


    function updateProfile(){  
        const userDto = {
            userId:userId,
            firstName:fname,
            lastName:lname,
            email:email,
            mobile:mobile,
            username:username,
            password:password,
            userRole:role
        }

        RestService.updateAppUser(userDto).then((res)=>{
            
            if(res.data.userId != null){
                alert('Registered Successfully')
                window.location.href = '/login'; 
            } else {
                alert('Failed to register')
            }
        }).catch((err)=>{
            console.log(err);
        })
    }

    return ( 
        <div>
             <h1>- EDIT PROFILE -</h1>
             <div className="p-5 card mt-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="row">
                        <div className="col">
                            <label>First Name</label>
                            <input type="text" className="form-control" value={fname}  onChange={(e)=>setFname(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Last Name</label>
                            <input type="text" className="form-control" value={lname}  onChange={(e)=>setLname(e.target.value)}  />
                        </div>
                    </div>
                    <div className="row mt-4">
                        <div className="col">
                            <label>Email</label>
                            <input type="text" className="form-control" value={email}  onChange={(e)=>setEmail(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Mobile</label>
                            <input type="text" className="form-control"  value={mobile} onChange={(e)=>setMobile(e.target.value)}  />
                        </div>
                    </div>
                    <div className="row mt-4">
                        <div className="col">
                            <label>Username</label>
                            <input type="text" className="form-control" value={uname}  onChange={(e)=>setUname(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Password</label>
                            <input type="password" className="form-control"  onChange={(e)=>setPassword(e.target.value)}  />
                        </div>
                    </div>

                    
                    <div className="text-center mt-4">
                        <button type="button" class="btn btn-primary" onClick={updateProfile}>Update</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default EditUserProfile;