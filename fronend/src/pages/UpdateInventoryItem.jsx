import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";
import { useLocation } from "react-router-dom";

function UpdateInventoryItem(props) {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));
    

    const location = useLocation();

    const item = location.state?.item;

    const[itemId, setItemId] = useState(item.itemId);
    const[itemName, setItemName] = useState(item.itemName);
    const[price,setPrice] = useState(item.pricePerItem);
    const[qty,setQty] = useState(item.qty);
    const[description, setDescription] = useState(item.description);



    useEffect(()=> {
        if(isLogged){

        } else {
            window.location.href = '/login'; 
        }
    },[])


    function updateItem(e) {
        e.preventDefault();

        if(itemName === "" || price === "" || qty ==="" || description ===""){
            alert("Input fields can't be empty");
            return;
        }

        if(isNaN(price) || isNaN(qty)){
            alert("please enter valid no");
            return;
        }

        const itemDto = { 
            itemId:itemId,
            itemName:itemName,
            pricePerItem:price,
            qty:qty,
            description:description
        }

        const requestDto = {
            userId:userId,
            itemDto:itemDto
        } 

        RestService.updateInventoryItem(token,requestDto).then((res)=>{
            if(res.data.success){

                setItemName('');
                setPrice('');
                setQty('');
                setDescription("");
                window.location.href = '/inventory'; 
               
            } else {
                alert('fail');
            }
        }).catch((err)=>{
            console.log(err);
            alert('fail');
        });
    }

    return ( 


        <div>
            <h1>- UPDATE ITEM -</h1>
            <div className="p-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="form-group">
                        <label>Item Name</label>
                        <input type="text" className="form-control" value={itemName} placeholder="Enter item name" onChange={(e)=>setItemName(e.target.value)}  />
                    </div>

                    <div className="form-group">
                        <label>Price Per Item (LKR)</label>
                        <input type="text" className="form-control" value={price} placeholder="Price per item" onChange={(e)=>setPrice(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label>Quantity</label>
                        <input type="text" className="form-control" value={qty} placeholder="Enter quantity" onChange={(e)=>setQty(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label>Description</label>
                        <textarea class="form-control" placeholder="Enter description" value={description} rows="3" onChange={(e)=>setDescription(e.target.value)}></textarea>
                    </div>
                    
                    <div className="text-center">
                        <button type="button" class="btn btn-primary" onClick={updateItem}>Update Item</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default UpdateInventoryItem;