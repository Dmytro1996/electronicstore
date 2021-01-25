/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let minPriceSlider=document.getElementById("minPrice");
var minPriceOutput=document.getElementById("minPriceValue");
if(minPriceSlider!==null){
  minPriceOutput.innerHTML=minPriceSlider.value;
  minPriceSlider.addEventListener('change',function(){    
  minPriceOutput.innerHTML=this.value;  
  },false);
}
var maxPriceSlider=document.getElementById("maxPrice");
var maxPriceOutput=document.getElementById("maxPriceValue");
if(maxPriceSlider!==null){
  maxPriceOutput.innerHTML=maxPriceSlider.value;
  maxPriceSlider.oninput=function(){    
  maxPriceOutput.innerHTML=this.value;  
};
}

if(document.getElementById('filterBtn')!==null){
  document.getElementById('filterBtn').addEventListener('click',hide);
}

function hide(){    
        document.getElementById("filterBtn").value='Show filter';
        document.querySelector('#form form').style.display='none';
        document.getElementById('filterBtnContainer').style.marginTop='-18px';
        document.getElementById('filterBtn').removeEventListener('click',hide);
        document.getElementById('filterBtn').addEventListener('click',show);        
};
function show(){    
        document.getElementById('filterBtn').value='Hide filter';
        if(document.querySelector('body').offsetWidth>720){
          document.querySelector('#form form').style.display='grid';
        } else{
          document.querySelector('#form form').style.display='flex';          
        }
        document.getElementById('filterBtnContainer').style.marginTop='0px';
        document.getElementById('filterBtn').removeEventListener('click',show);
        document.getElementById('filterBtn').addEventListener('click',hide);        
};

let openBasketBtn=document.getElementById('basket');
let closeBasketBtn=document.getElementById('close');
let overlay=document.getElementById('overlay');
/*if(document.getElementById('send')!==null){
    document.getElementById('send').addEventListener('click',clearBasket);
}*/
openBasketBtn.addEventListener('click',()=>{
    let basket=document.getElementById('popup_window');
    openBasket(basket);
});
closeBasketBtn.addEventListener('click',()=>{
    let basket=document.getElementById('popup_window');
    closeBasket(basket);
});
overlay.addEventListener('click', () => {
  const basket = document.getElementById('popup_window');  
  closeBasket(basket);
  closeLoginForm();
});
function openBasket(basket){
    if(basket!==null){        
        //fillAddedItems();        
        basket.classList.add('active');
        overlay.classList.add('active');
    }
}
function closeBasket(basket){
    if(basket!==null){
        basket.classList.remove('active');
        overlay.classList.remove('active');
    }
}

if(document.querySelector('#basketItem')!==null){
    document.querySelector('#popup_window .orderingForm').style.display='block';
    document.querySelector('#popup_window .buyingProposal').style.display='none';
    document.querySelector("#totalSum").style.display='block';
} else{
    document.querySelector('#popup_window .orderingForm').style.display='none';
    document.querySelector('#popup_window .buyingProposal').style.display='block';
    document.querySelector("#totalSum").style.display='none';
}

if(document.querySelector('.shouldBasketBeOpened')!==null){
    openBasket(document.querySelector('#popup_window'));
}

var loginMenuOpenerBtn=document.querySelector('#loginMenuOpener');
if(loginMenuOpenerBtn!==null){loginMenuOpenerBtn.addEventListener('click',openLoginMenu);}
function openLoginMenu(){
    //window.addEventListener('click',closeDropDownMenu);
    document.querySelector('#loginMenu').style.display='block';
    loginMenuOpenerBtn.removeEventListener('click',openLoginMenu);
    loginMenuOpenerBtn.addEventListener('click',closeLoginMenu);    
}
function closeLoginMenu(){
    document.querySelector('#loginMenu').style.display='none';
    loginMenuOpenerBtn.removeEventListener('click',closeLoginMenu);
    //window.removeEventListener('click',closeDropDownMenu);
    loginMenuOpenerBtn.addEventListener('click',openLoginMenu);
}
let loginBtn=document.querySelector('#loginButton');
if(loginBtn!==null){loginBtn.addEventListener('click',showLoginForm);}
var loginToOrder=document.querySelector('#loginToOrder');
if(loginToOrder!==null){
    loginToOrder.onclick=function(){  
        const basket = document.getElementById('popup_window');
        closeBasket(basket);
        showLoginForm();    
};
}
function showLoginForm(){
    overlay.classList.add('active');
    document.querySelector("#loginFormContainer").style.display='block';    
}
let closeLoginFormBtn=document.querySelector('#closeLoginForm');
closeLoginFormBtn.addEventListener('click',closeLoginForm);
function closeLoginForm(){
    overlay.classList.remove('active');
    document.querySelector("#loginFormContainer").style.display='none';
}

window.onclick=function(event){    
    if(event.target!==loginMenuOpenerBtn){
        //if(event.target!==loginToOrder){
            if(document.querySelector('#loginMenu')!==null && 
                    document.querySelector('#loginMenu').style.display==='block'){
                closeLoginMenu();
            }
        //}
    }    
};




