import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";

function Register() {

    //User roles 
    // Admin : 0
    // Buyer : 1
    // Pharmacist : 2
 
    const[fname, setFname] = useState("");
    const[lname, setLname] = useState("");
    const[email, setEmail] = useState("");
    const[mobile, setMobile] = useState("");
    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");

    function register(){  
        if(fname === "" || lname === "" || email === "" || mobile === "" || username === "" || password === ""){
            alert("Input fields can't be empty");
            return;
        }
        const userDto = {
            firstName:fname,
            lastName:lname,
            email:email,
            mobile:mobile,
            username:username,
            password:password,
            userRole:1
        }

        RestService.register(userDto).then((res)=>{
            
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
             <h1>- SIGN UP -</h1>
             <div className="p-5 card mt-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="row">
                        <div className="col">
                            <label>First Name</label>
                            <input type="text" className="form-control"  onChange={(e)=>setFname(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Last Name</label>
                            <input type="text" className="form-control"  onChange={(e)=>setLname(e.target.value)}  />
                        </div>
                    </div>
                    <div className="row mt-4">
                        <div className="col">
                            <label>Email</label>
                            <input type="text" className="form-control"  onChange={(e)=>setEmail(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Mobile</label>
                            <input type="text" className="form-control"  onChange={(e)=>setMobile(e.target.value)}  />
                        </div>
                    </div>
                    <div className="row mt-4">
                        <div className="col">
                            <label>Username</label>
                            <input type="text" className="form-control"  onChange={(e)=>setUsername(e.target.value)}  />
                        </div>
                        <div className="col">
                            <label>Password</label>
                            <input type="password" className="form-control"  onChange={(e)=>setPassword(e.target.value)}  />
                        </div>
                    </div>

                    
                    <div className="text-center mt-4">
                        <button type="button" class="btn btn-primary" onClick={register}>Sign up</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default Register;