// addresses = getData();

async function submitForm(form){

    var invalidInput = false;
    var alertMsg = "";
    var name_re = new RegExp("^[A-Za-z]+$");

    var pid = document.OrderForm.pid.value;
    const idList = ["1A", "2R", "3D", "4F", "5G", "6P", "7S", "8T", "9T", "10S", "11D", "12G"]
    if((pid.length == 0) || !idList.includes(pid)){
        invalidInput = true;
        alertMsg += "Bad input for product id \n"
    }
    
    //check first name
    var fname = document.OrderForm.fname.value;
    if((fname.length == 0) || !(name_re.test(fname))){
        invalidInput = true;
        alertMsg += "Bad input for first name \n"
    }


    //check last name
    var lname = document.OrderForm.lname.value;
    if((lname.length == 0) || !(name_re.test(lname))){
        invalidInput = true;
        alertMsg += "Bad input for last name \n"
    }

    // check if email matches the email format
    var email = document.OrderForm.email.value;
    const email_re = new RegExp("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$");
    if(!(email_re.test(email))){
        invalidInput = true;
        alertMsg += "Bad input for email \n"
    }


    // checks quantity for input less than 1 and if it is a number
    var quantity = document.OrderForm.quantity.value;
    if ((parseInt(quantity) <= 0) || isNaN(quantity)) {
        invalidInput = true;
        alertMsg += "Bad input for quantity \n";
    }

    //check if phone matches the format 123-456-7890 or +1 123-456-7890
    var phone = document.OrderForm.phone.value;
    const phone_re = /^(\+1\s)?\d{3}-\d{3}-\d{4}$/;
    if(!(phone_re.test(phone))){
        invalidInput = true;
        alertMsg += "Bad input for phone number \n"
    }

    //check for stress address
    // var address1 = document.OrderForm.address1.value;
    // const address1_re = /^\s*\S+(?:\s+\S+){2}$/;
    // console.log(address1)
    // if(!(address1_re.test(address1))){
    //     invalidInput = true;
    //     alertMsg += "Bad input for street address \n"
    // }


    // checks if state city and zip match an entry in csv file
    var state = document.OrderForm.state.value;
    var city = document.OrderForm.city.value;
    var zip = document.OrderForm.zip.value;
    const validAddress = await getData(city, state, zip);
    if(!(validAddress)){
        alertMsg += "Bad input for city state zip combination \n"
        invalidInput = true;
    }
    

    //check if credit card number has 16 digits and is a number
    var ccnum = document.OrderForm.ccnum.value;
    if ((ccnum.length != 16) || isNaN(ccnum)) {
        invalidInput = true;
        alertMsg += "Bad input for credit card number \n"
        // alert(alertMsg);
    }

    //check credit card expiration date against regex
    var expiration = document.OrderForm.expiration.value
    const expiration_re = new RegExp("^(0[1-9]|10|11|12)/[0-9]{2}$")
    if (!(expiration_re.test(expiration))){
        invalidInput = true;
        alertMsg += "Bad input for credit card expiration \n"
    }

    var cvv = document.OrderForm.cvv.value;
    if (cvv.length != 3 || isNaN(cvv)) {
        invalidInput = true;
        alertMsg += "Bad input for cvv code \n"
    }

    //all conditions passed, triggers mail client
    if(invalidInput){
        alert(alertMsg);
    }
    else{    
        form.submit()
    }
}

async function getData(city, state, zip) {
    const response = await fetch('./data/zip_code_database.csv');
    const data = await response.text();
    const rows = data.split('\n')

    var validAddress = false;
    rows.forEach(entry => {
        const row = entry.split(',');
        const z = row[0];
        const c = row[3];
        const s = row[6];
        if ((z == zip) && (c == city) && (s == state)) {
            validAddress = true;
        }

    })
    return validAddress;
}

function generateStateOptions(){
    var states = ['AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FM', 'FL', 'GA',
    'GU', 'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MH', 'MD', 'MA',
    'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM', 'NY', 'NC', 'ND',
    'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT',
    'VT', 'VI', 'VA', 'WA', 'WV', 'WI', 'WY']

    var selectField = document.getElementById("state");
    states.forEach(state =>{
        option = document.createElement("option");
        option.text = state;
        option.value = state;
        selectField.appendChild(option);
    })
}