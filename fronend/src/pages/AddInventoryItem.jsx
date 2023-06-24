import React,{useEffect, useState} from "react";
import RestService from "../services/RestService";

function AddInventoryItem() {

    const[isLogged, setIsLogged] = useState(sessionStorage.getItem("isLogged"));
    const[token, setToken] = useState(sessionStorage.getItem("token"));
    const[username, setUserName] = useState(sessionStorage.getItem("username"));
    const[userId, setUserId] = useState(sessionStorage.getItem("userId"));

    //error message
    const[message, setMessage] = useState("");
    const[errClass, setErrClass] = useState("");

    const[itemName, setItemName] = useState("");
    const[type, setType] = useState("");
    const[price,setPrice] = useState("");
    const[qty,setQty] = useState("");
    const[description, setDescription] = useState("");


    useEffect(()=> {
        if(isLogged){

        } else {
            window.location.href = '/login'; 
        }
    },[])

    function addInventoryItem(e){
        e.preventDefault();

        if(itemName === "" || type === "" || price === "" || qty ==="" || description ===""){
            alert("Input fields can't be empty");
            return;
        }

        if(isNaN(price) || isNaN(qty)){
            alert("please enter valid no");
            return;
        }

        const itemDto = { 
            itemName:itemName,
            type:type,
            pricePerItem:price,
            qty:qty,
            description:description
        }

        const requestDto = {
            userId:userId,
            itemDto:itemDto
        } 

        RestService.addInventoryItem(token,requestDto).then((res)=>{
            if(res.data.success){

                setItemName('');
                setPrice('');
                setQty('');
                setDescription("");

                setErrClass("alert alert-success");
                setMessage("Item added successfully!");
            } else {
                setErrClass("alert alert-danger");
                setMessage("Failed to add the item. Please try again.")
            }
        }).catch((err)=>{
            console.log(err);
        });

    }

    return ( 
        <div>
            <h1>- ADD ITEM -</h1>
            <div className="p-5" style={{width:'75%',margin:'auto'}}>
                <form>
                    <div className="form-group">
                        <label>Item Name</label>
                        <input type="text" className="form-control" value={itemName} placeholder="Enter item name" onChange={(e)=>setItemName(e.target.value)}  />
                    </div>
                    <div className="form-group">
                        <label>Type</label>
                        <select class="form-control" value={type} onChange={(e)=>setType(e.target.value)} >
                            <option>Select type...</option>
                            <option value="0">Tablets</option>
                            <option value="1" >Syrups</option>
                            <option value="2" >Other</option>
                        </select>
                    </div>
                    {type==0 &&
                        <div className="form-group">
                            <label>Price Per Tablet (LKR)</label>
                            <input type="text" className="form-control" value={price} placeholder="Price per tablet" onChange={(e)=>setPrice(e.target.value)} />
                        </div>
                    }

                    {type==1 &&
                        <div className="form-group">
                            <label>Price Per Bottle (LKR)</label>
                            <input type="text" className="form-control" value={price} placeholder="Price per bottle" onChange={(e)=>setPrice(e.target.value)} />
                        </div>
                    }

                    {type==2 &&
                        <div className="form-group">
                            <label>Price Per item (LKR)</label>
                            <input type="text" className="form-control" value={price} placeholder="Price per item" onChange={(e)=>setPrice(e.target.value)} />
                        </div>
                    }

                    <div className="form-group">
                        <label>Quantity</label>
                        <input type="text" className="form-control" value={qty} placeholder="Enter quantity" onChange={(e)=>setQty(e.target.value)} />
                    </div>

                    <div className="form-group">
                        <label>Description</label>
                        <textarea class="form-control" placeholder="Enter description" value={description} rows="3" onChange={(e)=>setDescription(e.target.value)}></textarea>
                    </div>

                    <div className={errClass} role="alert">
                         {message}
                    </div>

                    <div className="text-center">
                        <button type="button" class="btn btn-primary" onClick={addInventoryItem} >Add Item</button>
                    </div>
                    
                </form>
            </div>
        </div>
     );
}

export default AddInventoryItem;