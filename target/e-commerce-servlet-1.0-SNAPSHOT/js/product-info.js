
product_list = [
    {
        id: "1A",
        name: "Amalgam Fleece+Snyth",
        price: "$174.99",
        description: "Warm multicolor fleece. The different colors represent the famous Nuance brand. Wash on cold. 40% cotton",
        srcOne: "assets/Product-1-v0.jpg",
        srcTwo: "assets/Product-1-v1.jpg",
        srcThree: "assets/Product-1-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "2R",
        name: "Retro Walkers",
        price: "$89.99",
        description: "Multi color runners. Designed in Italy, sold on Nuance 9. Shoe runs true to size",
        srcOne: "assets/Product-2-v0.jpg",
        srcTwo: "assets/Product-2-v1.jpg",
        srcThree: "assets/Product-2-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "3D",
        name: "Deathlyhallow #3",
        price: "$49.99",
        description: "Cargo camo pants. The material is imported from South Africa. Wash on cool since it is 34% Polyester",
        srcOne: "assets/Product-3-v0.jpg",
        srcTwo: "assets/Product-3-v1.jpg",
        srcThree: "assets/Product-3-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "4F",
        name: "Featuring Teddy Roosevelt",
        price: "$19.99",
        description: "Bear hat is a one size fits all hat. The material is safe for all types of washes",
        srcOne: "assets/Product-4-v0.jpg",
        srcTwo: "assets/Product-4-v1.jpg",
        srcThree: "assets/Product-4-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "5G",
        name: "Graphic Design Is My Passion",
        price: "$74.99",
        description: "This multicolor shoe is designed by the Great Robert. It infuses different cultural influences from all around the world. Fits true to size. ",
        srcOne: "assets/Product-5-v0.jpg",
        srcTwo: "assets/Product-5-v1.jpg",
        srcThree: "assets/Product-5-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "6P",
        name: "P.A.N.T.S.",
        price: "$79.99",
        description: "Multi patch is made of different colored patches. Size runs true to size, and is 50% cotten.",
        srcOne: "assets/Product-6-v0.png",
        srcTwo: "assets/Product-6-v1.png",
        srcThree: "assets/Product-6-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "7S",
        name: "Subtle Arachnid",
        price: "$39.99",
        description: "Metal chain of a collection of rings and cubain chains. Can be cleaned easily and safe for water wear.",
        srcOne: "assets/Product-7-v0.jpg",
        srcTwo: "assets/Product-7-v1.jpg",
        srcThree: "assets/Product-7-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "8T",
        name: "Tactical Cubeta",
        price: "$29.99",
        description: "Black bucket hat with long string. Flexiable and heat resistant. Material is 50% cotton and is machine wash safe.",
        srcOne: "assets/Product-8-v0.jpg",
        srcTwo: "assets/Product-8-v1.jpg",
        srcThree: "assets/Product-8-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "9T",
        name: "The Vest",
        price: "$54.99",
        description: "Black wrap around best. 5 deep pockets making it easy to carry things, and easy to wash.",
        srcOne: "assets/Product-9-v0.jpg",
        srcTwo: "assets/Product-9-v1.jpg",
        srcThree: "assets/Product-9-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "10S",
        name: "Samurai",
        price: "$59.99",
        description: "Black/Red jacket. Created from influences of several animaes. Clean on cold and is washer safe. ",
        srcOne: "assets/Product-10-v0.jpg",
        srcTwo: "assets/Product-10-v1.jpg",
        srcThree: "assets/Product-10-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "11D",
        name: "Drifter",
        price: "$64.99",
        description: "Black/White/Red shoes. Material is everything on this shoe. Shoe is true to size.",
        srcOne: "assets/Product-11-v0.jpg",
        srcTwo: "assets/Product-11-v1.jpg",
        srcThree: "assets/Product-11-v2.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "12G",
        name: "Godzilla",
        price: "$24.99",
        description: "Black/Red/White short sleeve t shirt with race car design. 20% cotton and easy to wash.",
        srcOne: "assets/Product-12-v0.jpg",
        srcTwo: "assets/Product-12-v1.jpg",
        srcThree: "assets/Product-12-v2.jpg",
        image_href: "#",
        alt: ""
    },
]

//Retrives local storage
var product = localStorage.getItem("textvalue")


product = product.trim()


// GO INTO HPDO.PHP and return the array into this page


var name = ""
var price = ""
var description = ""
var srcOne = ""
var srcTwo = ""
var srcThree = ""
var src = ""
var pid = ""

var stateSet = false
var zipSet = false

window.onload = function() {

    // this.phpArray = "<?php php_func();?>";
    
    // console.log(this.phpArray)
    
    console.log(this.product);
    createCookie("gfg",this.product,10);
    // Unlock bottom
    // updateVariables();

    // logInfo();

    // Unlock bottom
    // updatePage();

    ajaxUpdateTaxAmmount();
};

function createCookie(name, value,days){
    var expires;

    console.log(value);
    document.cookie = escape(name)+"="+escape(value)

}
function updateVariables(){
//intializes the information
    
    for(var i=0;i<product_list.length; i++) {
        if (this.product === product_list[i].name){
            this.name = product_list[i].name;
            this.price = product_list[i].price;
            this.description = product_list[i].description;
            this.srcOne = product_list[i].srcOne
            this.srcTwo = product_list[i].srcTwo
            this.srcThree = product_list[i].srcThree
            this.src = product_list[i].src;
            this.pid = product_list[i].id;
        }
    }
}

function updatePage(){
    var name = document.getElementById("name");
    var description = document.getElementById("description");
    var price = document.getElementById("price");
    var firstImage = document.getElementById("first-image")
    var secondImage = document.getElementById("second-image")
    var thirdImage = document.getElementById("third-image")
    var id = document.getElementById("pid")
    name.textContent = this.name;
    description.textContent = this.description;
    price.textContent = this.price;
    firstImage.src = this.srcOne
    secondImage.src = this.srcTwo
    thirdImage.src = this.srcThree
    id.textContent = "Product ID: " + this.pid;
}

function ajaxUpdateTaxAmmount() {
    var stateField = document.getElementById("state");
    var zipField = document.getElementById("zip");

    stateField.addEventListener('change', (event) =>{
        const state = this.document.getElementById("state").value;
        if (state != "") {
            stateSet = true;
        } else {
            stateSet = false;
        }

        if (stateSet && zipSet) {
            updateTaxRateInDOM();
        }
    });

    zipField.addEventListener('change', (event) =>{
        const zip = this.document.getElementById("zip").value;

        if (zip && zip.length == 5) {
            zipSet = true;
        } else {
            zipSet = false;
        }

        if (stateSet && zipSet) {
            updateTaxRateInDOM();
        } 
    });
}


async function updateTaxRateInDOM() {
    const state = this.document.getElementById("state").value;
    const zip = this.document.getElementById("zip").value;

    const file = await fetch('./data/tax_rates.csv');
    const data = await file.text();
    const rows = data.split('\n')

    var taxRate = "";
    rows.forEach(entry => {
        const row = entry.split(',');
        const s = row[0];
        const z = row[1];
        if (z == zip && s == state) {
            taxRate = row[3];
        }

    })

    const price = document.getElementById("price").textContent.replace("$", "");
    var taxRateNumber = Number(taxRate);
    var producePriceNumber = Number(price);
    var taxAmmount = (taxRateNumber * producePriceNumber)
    var taxAmmountFormatted = "+ $" + taxAmmount.toFixed(2)

    tax = document.getElementById("tax");
    tax.textContent = taxAmmountFormatted;
}



// function addToCart(){
//     document.OrderForm.pid.value = this.pid;
//     document.OrderForm.quantity.value = 1; 
// }



