import React,{useEffect, useState} from "react";
import logo from '../Logo.png';
import profile from '../profile.png';
import '../styles/header.css';

function Header() {

    const[fname, setFname] = useState();


    useEffect(()=> {
        if(!sessionStorage.getItem("isLogged")) {
            // window.location.href = '/login';
        }

    },[])

    function logout() {
        sessionStorage.clear();
        window.location.href = '/login';
    }

    return ( 
        <div className="">
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-4">
                <a class="navbar-brand d-flex justify-content-start" href="/"><img src={logo} style={{width:'200px'}} /></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                {sessionStorage.getItem("isLogged") &&
                <div class="collapse navbar-collapse d-flex justify-content-center"   id="navbarSupportedContent">
                    <ul class="navbar-nav"  >
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/">Home</a>
                        </li>
                        </ul>
                        {sessionStorage.getItem("role") == 0 &&
                        <ul class="navbar-nav"  >
                            <li class="nav-item active p-3">
                                <a class="nav-link custom-nav" href="/view_medicine_list">MEDICINES</a>
                            </li>
                            <li class="nav-item active p-3">
                                <a class="nav-link custom-nav" href="view_pharmacy_list">PHARMACIES</a>
                            </li>
                        </ul>
                        }
                        {sessionStorage.getItem("role") == 1 &&
                         <ul class="navbar-nav"  >
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/view_user_orders">Orders</a>
                        </li>
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/user_requests">Requests</a>
                        </li>
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/create_pharmacy_profile">Create Pharmacy</a>
                        </li>
                        </ul>
                        }

                        {sessionStorage.getItem("role") == 2 &&
                         <ul class="navbar-nav"  >
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/view_pharmacy_orders">Orders</a>
                        </li>
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/view_pharmacy_request">Requests</a>
                        </li>
                        <li class="nav-item active p-3">
                            <a class="nav-link custom-nav" href="/inventory">Inventory</a>
                        </li>
                        </ul>
                        }
                      
                </div>
               }
                {sessionStorage.getItem("isLogged") ? 
                    <div class=" d-flex justify-content-end">
                        <img src={profile} style={{width:'50px'}} />
                        <div class="dropdown" style={{margin:'auto'}}>
                        <a class="nav-link dropdown-toggle" href="#" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style={{color:'black'}}>
                            {sessionStorage.getItem("firstname")} {sessionStorage.getItem("lastname")}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/edit_profile">Edit Profile</a>
                            {sessionStorage.getItem("role") == 2 &&
                            <a class="dropdown-item" href="/profile">View Pharmacy Profile</a>
                            }
                            <a class="dropdown-item" href="#" onClick={logout}>Logout</a>
                        </div>
                    </div>
                    </div>
                    : 
                    <div class=" d-flex justify-content-end ml-5">
                        
                         <ul class="navbar-nav"  >
                            <li class="nav-item active p-3">
                                <a class="nav-link custom-nav" href="/login">Login</a>
                            </li>
                            
                            <li class="nav-item active p-3">
                                <a class="nav-link custom-nav" href="/register">Sign Up</a>
                            </li>
                        </ul>
                    </div>
                }

                

                

                
            </nav>  
        </div>
     );
}

export default Header;