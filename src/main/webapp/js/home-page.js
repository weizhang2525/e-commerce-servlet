
product_list = [
    {
        id: "1A",
        name: "Amalgam Fleece+Snyth",
        price: "$174.99",
        description: "An amalgamation of different materials stiched together to form the pertect hoodie.",
        src: "assets/Product-1-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "2R",
        name: "Retro Walkers",
        price: "$89.99",
        description: "Comfortable yet stylish sneakers that take inspiration from the NES generation.",
        src: "assets/Product-2-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "3D",
        name: "Deathlyhallow #3",
        price: "$49.99",
        description: "Cargo camo jogger for a all occasions, like a nice walk in the park or executing a stealth operation.",
        src: "assets/Product-3-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "4F",
        name: "Featuring Teddy Roosevelt",
        price: "$19.99",
        description: "This hat has been constructed from recycled materials sourced from US national parks.",
        src: "assets/Product-4-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "5G",
        name: "Graphic Design Is My Passion",
        price: "$74.99",
        description: "If you love graphic design then this is the shoe for you. It may look an Adidas shoe, but I promise you it's not :)",
        src: "assets/Product-5-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "6P",
        name: "P.A.N.T.S.",
        price: "$79.99",
        description: "Plants, Animals and Nature are Transcendent Soldiers. What does that mean? Thats for us to know and you to find out!",
        src: "assets/Product-6-v0.png",
        image_href: "#",
        alt: ""
    },
    {
        id: "7S",
        name: "Subtle Arachnid",
        price: "$39.99",
        description: "The perfect accessory if you are looking to complete the perfect goth look. Also nice for Halloween.",
        src: "assets/Product-7-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "8T",
        name: "Tactical Cubeta",
        price: "$29.99",
        description: "Trying to jump on the bucket hat trend? Are you going on a long sunny hike? Operating in a foreign country where you are banned due to you're connection to the Mafia? If you answer yes to any of these questions this hat is for you.",
        src: "assets/Product-8-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "9T",
        name: "The Vest",
        price: "$54.99",
        description: "If you need more info on this product, then this isn't the vest for you. If you know you know.",
        src: "assets/Product-9-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "10S",
        name: "Samurai",
        price: "$59.99",
        description: "Want to look cool while going out into the country to protect your feudal lord's land? Don't forget to wear this jacket.",
        src: "assets/Product-10-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "11D",
        name: "Drifter",
        price: "$64.99",
        description: "Keep the sliding happen when you step out of the car with these shoes.",
        src: "assets/Product-11-v0.jpg",
        image_href: "#",
        alt: ""
    },
    {
        id: "12G",
        name: "Godzilla",
        price: "$24.99",
        description: "What better way to represent the best generation of cars than to wear it on your shirt?",
        src: "assets/Product-12-v0.jpg",
        image_href: "#",
        alt: ""
    },
]

window.onload = function() {
    DOM_products_contaner = document.getElementById("products-container")

    for (let i in product_list) {
        var product = product_list[i]
        // this.console.log(product)

        // Create the product card container with column formatting
        new_product_container = this.document.createElement("div")
        new_product_container.classList.add("col-lg-6")
        new_product_container.classList.add("col-md-6")
        new_product_container.classList.add("col-sm-6")
        new_product_container.classList.add("col-6")

        // new_product_container.classList.add("mb-6")
        new_product_container.classList.add("product-card")

        // Create the product card
        new_product_card = this.document.createElement("div")
        new_product_card = this.document.createElement("a")
        new_product_card.classList.add("card")
        new_product_card.classList.add("h-100")
        new_product_card.href = "product-page.html";
        new_product_card.onclick=function(e){
            // var path = e.path[2];
            // var title = document.getElementByClass(e)
            var productInfo = e.target.offsetParent.innerText
            var productName = productInfo.split("$")
            passValue(productName[0])
        }

        // Create the image container for this product
        image_container = this.document.createElement("a")
        image_container.classList.add("image-container")
        img = this.document.createElement("img")
        img.classList.add("card-img-top")
        img.src = product.src;
        img.alt = product.alt;
        // image_container.href = "#";
        // image_container.onclick=function(e){
        //     console.log(e)
            // var product = e.toElement.text;
            // passValue(product)
        // }

        // Card body
        card_body = this.document.createElement("div")
        card_body.classList.add("card-body")

        // Card title
        card_title = this.document.createElement("h4");
        card_title.classList.add("card-title");

        // Product title clickable a
        card_title_a = this.document.createElement("a")
        card_title_a.classList.add("product-title")
        card_title_a.innerHTML = product.name
        card_title_a.href = "product-page.html";
        card_title_a.onclick=function(e){
            // console.log(e.toElement.text)
            var product = e.toElement.text;
            passValue(product)
        }
        


        // Price
        card_cost = this.document.createElement("h5")
        card_cost.classList.add("card-cost")
        card_cost.innerHTML = product.price

        // Card text
        card_text = this.document.createElement("p");
        card_text.classList.add("card-text")
        card_text.innerHTML = product.description


        // Add everything together
        image_container.appendChild(img)
        card_title.appendChild(card_title_a)

        card_body.appendChild(card_title)
        card_body.appendChild(card_cost)
        card_body.appendChild(card_text)
        new_product_card.appendChild(image_container)
        new_product_card.appendChild(card_body)
        new_product_container.appendChild(new_product_card)

        // Now add it to the DOM
        DOM_products_contaner.appendChild(new_product_container)
    }



function passValue(product){
    console.log(product)
    localStorage.setItem("textvalue",product);
    console.log(product)
    return false;
    // console.log(product)
}

}
