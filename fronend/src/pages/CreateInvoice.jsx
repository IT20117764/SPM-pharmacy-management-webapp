import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import { useLocation } from "react-router-dom";
import { useParams } from 'react-router-dom';
// import Add from "../images/add.jpg";

function CreateInvoice() {
    

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    //error message
    const[message, setMessage] = useState("");
    const[errClass, setErrClass] = useState("");

    const[items, setItems] = useState([]);
    const[filteredList, setFilteredList] = useState([]);
    const[selectedItem, setSelectedItem] = useState([]);

    const[cartItems, setCartItems] = useState([]);
    const[qty, setQty] = useState("");
    const[tot, setTot] = useState(0);


    const location = useLocation();
    const request = location.state?.request;


    const { id } = useParams();

    //For Development
    // const[cusId, setCusId] = useState(1);

    useEffect(()=> {

        if(isLogged){

            const requestDto = {
                userId:userId
            } 

            RestService.findPharmacyInventoryById(token,requestDto).then((res)=>{
                setItems(res.data.itemDtoList);
                setFilteredList(res.data.itemDtoList);
            }).catch((err)=>{
                console.log(err);
            });


        } else {
            window.location.href = '/login'; 
        }
    },[])

    function filterItems(searchVal) {
        if(searchVal != ""){
            let temp = [];
            for(const i of items){
                if(i.itemName.toLowerCase().startsWith(searchVal.toLowerCase())){
                    temp.push(i);
                }
            }

            setFilteredList(temp);
        } else {
            setFilteredList(items);
        }
    }

    function addToCart(){
        console.log(selectedItem)
        if(selectedItem.qty !== undefined && qty !==""){

            if(isNaN(qty)) {
                alert("Please enter a valid number")
                return;
            }
            selectedItem.qty = qty;
            totPricCalc([...cartItems, selectedItem])

            setCartItems([...cartItems, selectedItem])

            
        } else {
            alert("Input fields can't be empty")
            return
        }
    }

    function removeItem(item){

        let oldCart = cartItems;
        let newCart = [];

        for(const i of oldCart) {
            if(item !=   i){
                newCart.push(i)
            }
        }
        setCartItems(newCart);
    }

    function totPricCalc(itemList){
        let tempTot = 0;

            for(const i of itemList) {
                tempTot = tempTot + (i.qty * i.pricePerItem)
            }
    
            setTot(tempTot);

        return tempTot;
    }

    function createInvoice(e) {
        e.preventDefault();
        if(cartItems.length == 0) {
            alert("Please add atleast one item!!");
            return;
        }
        const orderDto = { 
            cid:request.userDto.userId,
            total:tot,
            orderItems:cartItems
        }

        const requestDto = {
            userId:userId,
            orderDto:orderDto,
            quotationId:id
        } 

        RestService.createInvoice(token,requestDto).then((res)=>{
            if(res.data.success){
                setErrClass("alert alert-success");
                setMessage("Invoice sent successfully!");
            } else {
                setErrClass("alert alert-danger");
                setMessage("Failed to add the item. Please try again.")
            }
        }).catch((err)=>{
            console.log(err);
        });

    }

    return (
        <div >
            <h1>-INVOICE-</h1>

            <div className="p-5" style={{width:'75%', margin:'auto'}}>

                <div className={errClass} role="alert"> {message} </div>


                <div className="row">
                    <div className="col-3">
                        <label>Customer Name</label>
                    </div>
                    <div className="col">
                        <label> {request.userDto.firstName} {request.userDto.lastName}</label>
                    </div>
                </div>

                <div className="row mt-3">
                    <div className="col-3">
                        <label>Product Name</label>
                    </div>
                    <div className="col">
                        <div class="dropdown">
                            <input className="form-control dropdown-toggle" type="text" value={selectedItem.itemName} id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
                            <div class="dropdown-menu overflow-auto" aria-labelledby="dropdownMenu2" style={{width:'550px'}}>
                                <input className="form-control" type="text" style={{width:'96%', margin:'auto'}} onChange={(e)=>filterItems(e.target.value)} />
                                    {filteredList.map(
                                        i=><button class="dropdown-item" type="button" key={i.itemId} onClick={(e)=>setSelectedItem(i)} >{i.itemName}</button>
                                    )}
                             </div>
                        </div>
                    </div>
                </div>
                <div className="row mt-3">
                    <div className="col-3">
                        <label>Price</label>
                    </div>
                    <div className="col">
                        <input className="form-control" type="text" value={selectedItem.pricePerItem}/>
                    </div>
                </div>
                <div className="row mt-3">
                    <div className="col-3">
                        <label>Quantity</label>
                    </div>
                    <div className="col">
                        <input className="form-control" type="text" value={qty} onChange={(e)=>setQty(e.target.value)}/>
                    </div>
                </div>
            </div>

            <div className="text-center">
                <button className="btn btn-secondary btn-sm" style={{borderRadius:'15px',width:'5vw'}}  onClick={addToCart}>Add</button>                           
            </div>

            <div className="row p-5" style={{height:'200px', overflow:'auto'}}>
                <table className="table table-hover " >
                    <thead>
                        <tr>
                            <th scope="col">Item Name</th>
                            <th scope="col">Qty</th>
                            <th scope="col">Price</th>
                            <th scope="col">Description</th>
                            <th scope="col" className="text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                                cartItems.map(
                                    item =>
                                    <tr key={item.itemId}>
                                        <td>{item.itemName}</td>        
                                        <td>{item.qty}</td> 
                                        <td>{item.pricePerItem}</td>
                                        <td>{item.description}</td> 
                                        <td className="text-center">
                                            <button type="button" className="btn btn-danger btn-sm"  onClick={(e)=>{removeItem(item)}} >-</button>  
                                            
                                        </td> 
                                    </tr>
                                )                                             
                        }
                    </tbody>
                </table>
            </div>

            <div className="row text-center mt-5">
                <div className="col">
                     <h3></h3>   
                </div>
                <div className="col">
                    <h3>Total Price (LKR) : {tot}</h3>  
                </div>        
            </div>  
            
            <div className="text-center" style={{marginBottom:'15vh'}}>
                <button className="btn btn-primary mt-3 btn-sm" style={{borderRadius:'15px',width:'5vw'}} onClick={createInvoice} > Send </button>
            </div>
            


            

        </div>
    );
}

export default CreateInvoice;