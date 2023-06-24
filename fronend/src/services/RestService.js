import axios from 'axios';

const USER_BASE_URL = "http://localhost:8885/eCare_backend";


class RestService {
    
    
//------------------Shashika-------------------------//
    addInventoryItem(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/inventory/save',requestDto, config);
    }

    updateInventoryItem(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/inventory/update',requestDto, config);
    }

    getAllPharmaciesAndMedicines(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/getAllPharmaciesAndMedicines',requestDto, config);
    }

    deleteItemById(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/inventory/deleteItemById',requestDto, config);
    }

    findPharmacyInventoryById(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/inventory/findInventoryByUserId',requestDto, config);
    }    


//-----------------------------------------------------//





//------------------Tharuka-------------------------//
    saveQuotation(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/saveQuotation',requestDto, config);
    }

    findUserRequests(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/findQuotationsByUserId',requestDto, config);
    }

    findUserOrders(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/findUserOrdersByUserId',requestDto, config);
    }

    deleteQuotationById(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/deleteQuotationById',requestDto, config);
    }

    approveOrder(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/approveOrders',requestDto, config);
    }

    changeOrderStatus(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/changeOrderStatusById',requestDto, config);
    }


//---------------------------------------------------//





//------------------Bhashitha-------------------------//

    register(userDto) {
        return axios.post(USER_BASE_URL + '/register',userDto)
    }

    authenticateUser(username, password) {
        return axios.post(USER_BASE_URL + '/authenticate',{username:username, password:password})
    }

    updateAppUser(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/update',requestDto, config);
    }

    addMedicine(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/medicine/addMedicine',requestDto, config);
    }

    findMedicineById(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/medicine/getMedicineById',requestDto, config);
    }
 
    getPharmacyList(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/getPharmacyList',requestDto, config);
    }

    getMedicineList(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/medicine/getMedicineList',requestDto, config);
    }

    updateMedicine(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/medicine/updateMedicine',requestDto, config);
    }

    deleteMedicine(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/medicine/deleteMedicine',requestDto, config);
    }


//----------------------------------------------------//




//-------------------- Punsisi -------------------------//
    savePharmacy(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/savePharmacy',requestDto, config);
    }

    createInvoice(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/saveOrder',requestDto, config);
    }

    findPharmacyOrders(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/findOrders',requestDto, config);
    }

    findPharmacyRequests(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/getAllRequestsByPharmacyId',requestDto, config);
    }

    viewPharmacy(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/findPharmacyByPid',requestDto, config);
    }

    getProfile(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/user/getProfile',requestDto, config);
    }

    updatePharmacy(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/update',requestDto, config);
    }

    deletePharmacy(token, requestDto){
        const config = {
            headers: { 
                'Access-Control-Allow-Origin': "*", 
                'Authorization': 'Bearer ' + token 
            }
        }
        return axios.post(USER_BASE_URL+'/pharmacy/delete',requestDto, config);
    }

} 


export default new RestService();