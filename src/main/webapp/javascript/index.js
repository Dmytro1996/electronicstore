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

var btns=document.querySelectorAll('.buy');
if(btns!==null){
  for(let i=0;i<btns.length;i++){
      btns[i].addEventListener('click',addToBasket);
  }
}
let buyBtnClickCount=findMaxKey()+1;

//removeBtns.forEach(()=>addEventListener('click',removeFromBasket));

function addToBasket(){
    id=this.id;    
    let itemToAdd=getCommodity(id);    
    sessionStorage.setItem(buyBtnClickCount.toString(),itemToAdd);
    buyBtnClickCount++;
}
function arrToHTML(arr){
    let html='';
    for(let i=0;i<arr.length;i++){
      let key=sessionStorage.key(i);
      html+='<div id=\'basketItem\' class=\'basketItem\'>'+arr.getItem(key)+'</div>';      
    }
    return html;
}
function getCommodity(id){
  var result='';
  let brandModel=document.querySelector('.'+id+' #brandModel').innerHTML;
  let price=document.querySelector('.'+id+' #price').innerHTML;
  var image='.'+id+' img';
  result+='<img src='+document.querySelector(image).src+'></img>';
  result+='<p>'+brandModel+'</p>'+'<p id=\'addedItemPrice\'>'+price+'</p>';  
  result+='<input type=\'button\' class=\'remove\' id=\'remove'+
          buyBtnClickCount+'\' value=\'Remove\'>';  
  return result;
}

let openBasketBtn=document.getElementById('basket');
let closeBasketBtn=document.getElementById('close');
let overlay=document.getElementById('overlay');
if(document.getElementById('send')!==null){
    document.getElementById('send').addEventListener('click',clearBasket);
}
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
        fillAddedItems();        
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
function fillAddedItems(){
   
  if(arrToHTML(sessionStorage).length>0){
    document.getElementById('addedItems').innerHTML='Currently in basket:'+sessionStorage.length;
    document.getElementById('addedItems').innerHTML+=arrToHTML(sessionStorage);
    document.getElementById('addedItems').innerHTML+=calculateTotalSum();
    document.getElementById('addedItems').classList.remove('emptyBasket');
  } else{
    document.getElementById('addedItems').innerHTML='Basket is empty';
    document.getElementById('addedItems').classList.add('emptyBasket');
  }
  createRemoveBtns();
}
function removeFromBasket(){
  key=this.id.substring(6);
  sessionStorage.removeItem(key.toString());
  fillAddedItems();
}
function createRemoveBtns(){
    var removeBtns=document.querySelectorAll('.remove');
    for(let i=0;i<removeBtns.length;i++){
      removeBtns[i].addEventListener('click',removeFromBasket);
    }
}

function clearBasket(){    
    sessionStorage.clear();
}

function calculateTotalSum(){
    let addedItemsPrices=document.querySelectorAll('#addedItemPrice');
    var sum=0;
    addedItemsPrices.forEach(function(el){sum+=Number(el.innerHTML.substring(6));});       
    return '<p id=\'totalSum\'>Total sum:'+sum+'</p>';
}
function findMaxKey(){
    let max=0;
    for(let i=0;i<sessionStorage.length;i++){
        if(parseInt(sessionStorage.key(i))>max){
            max=parseInt(sessionStorage.key(i));
        }
    }
    return max;
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
loginToOrder.onclick=function(){
    closeBasket(basket);
    showLoginForm();    
};
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
    if(event.target!==loginMenuOpenerBtn || event.target!==loginToOrder){
        if(document.querySelector('#loginMenu').style.display==='block'){
             closeLoginMenu();
         }
    }    
};


