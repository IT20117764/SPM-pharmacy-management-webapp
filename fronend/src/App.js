import { BrowserRouter as Router, Route, Routes  } from 'react-router-dom';
import Footer from './components/Footer';
import Header from './components/Header';
import AddMedicine from './pages/AddMedicine';
import CreatePharmacyProfile from './pages/CreatePharmacyProfile';
import Home from './pages/Home';
import Inventory from './pages/Inventory';
import RequestQuotation from './pages/RequestQuotation';
import MyRequests from './pages/MyRequests';
import ViewPharmacyRequests from './pages/ViewPharmacyRequests';
import Register from './pages/Register';
import PharmacyProfile from './pages/PharmacyProfile';
import Login from './pages/Login';
import './App.css';
import './styles/common.css';
import AddInventoryItem from './pages/AddInventoryItem';
import UpdateInventoryItem from './pages/UpdateInventoryItem';
import ImageUpload from './pages/ImageUpload';
import ViewMedicine from './pages/ViewMedicine';
import CreateInvoice from './pages/CreateInvoice';
import ViewPharmacyOrders from './pages/ViewPharmacyOrders';
import Profile from './pages/Profile';
import ViewUserOrders from './pages/ViewUserOrders';
import OrderPayment from './pages/OrderPayment';
import ViewUserRequests from './pages/ViewUserRequests';
import ViewPharmacyList from './pages/ViewPharmacyList';
import ViewMedicineList from './pages/ViewMedicineList';
import UpdateMedicine from './pages/UpdateMedicine';
import EditUserProfile from './pages/EditUserProfile';


function App(props) {
  return (
    <div className='hero' >
      <Header></Header>
        <div className='container card content'  >
          <Router>  
              <Routes>

                  <Route exact path='*' element={<Home/>}/>

                  {/* Shashika */}
                  <Route exact path='/' element={<Home/>}/>
                  <Route exact path='/inventory' element={<Inventory/>}/>
                  <Route exact path='/add_inventory_item' element={<AddInventoryItem/>}/>
                  <Route exact path='/update_inventory_item/:id' element={<UpdateInventoryItem/>}/>
                  <Route exact path='/image_upload' element={<ImageUpload/>}/>

                  {/* Tharuka */}
                  <Route exact path='/request_quotation/:pid' element={<RequestQuotation/>}/>
                  <Route exact path='/view_user_orders' element={<ViewUserOrders/>}/>
                  <Route exact path='/user_requests' element={<ViewUserRequests/>}/>
                  <Route exact path='/payment' element={<OrderPayment/>}/>
                  

                  {/* Punsisi */}
                  <Route exact path='/create_pharmacy_profile' element={<CreatePharmacyProfile/>}/>
                  <Route exact path='/pharmacy_profile/:pid' element={<PharmacyProfile/>}/>
                  <Route exact path='/create_invoice/:id' element={<CreateInvoice/>}/>
                  <Route exact path='/view_pharmacy_orders' element={<ViewPharmacyOrders/>}/>
                  <Route exact path='/profile' element={<Profile/>}/>
                  <Route exact path='/view_pharmacy_request' element={<ViewPharmacyRequests/>}/>

                  {/* Bhashitha */}
                  <Route exact path='/login' element={<Login/>}/>
                  <Route exact path='/register' element={<Register/>}/>
                  <Route exact path='/edit_profile' element={<EditUserProfile/>}/>
                  <Route exact path='/add_medicine' element={<AddMedicine/>}/>
                  <Route exact path='/view_medicine_list' element={<ViewMedicineList/>}/>
                  <Route exact path='/view_medicine/:id' element={<ViewMedicine/>}/>
                  <Route exact path='/view_pharmacy_list' element={<ViewPharmacyList/>}/>
                  <Route exact path='/update_medicine/:id' element={<UpdateMedicine/>}/>

              </Routes> 
          </Router>
        </div>
          
      <Footer></Footer>
    </div>
  );
}

export default App;
