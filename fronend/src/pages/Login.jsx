import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";


function Login() {

    const[username, setUsername] = useState();
    const[password, setPassword] = useState();

    function authenticate(){  
        RestService.authenticateUser(username, password).then((res)=>{
            console.log(res.data)
            if(res.data.token != null){
                sessionStorage.setItem("token", res.data.token);
                sessionStorage.setItem("isLogged", true);     
                          
                sessionStorage.setItem("firstname", res.data.userDto.firstName);
                sessionStorage.setItem("lastname", res.data.userDto.lastName);
                
                sessionStorage.setItem("role", res.data.userDto.userRole);
                sessionStorage.setItem("username", res.data.userDto.username);

                sessionStorage.setItem("userId", res.data.userDto.userId);
                window.location.href = '/'; 
            } else {
                alert('Failed Login')
            }
        }).catch((err)=>{
            console.log(err);
        })
    }

    return ( 
        <div>
             <h1>- LOGIN -</h1>
             <div className="p-5 card mt-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control"  onChange={(e)=>setUsername(e.target.value)}  />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control"  onChange={(e)=>setPassword(e.target.value)}  />
                    </div>
                    
                    <div className="text-center">
                        <button type="button" class="btn btn-primary" onClick={authenticate}>Login</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default Login;