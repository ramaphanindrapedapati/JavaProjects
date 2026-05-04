function formValidation()
{
    let fname = document.getElementById("fullname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;

    //conversion
    let Phone = Number(phone);
    console.log(typeof(phone));

    let isValid = true;
    //fullname
    if(fname === "")
    {
        document.getElementById("fullnameErr").innerHTML="x This field cannot be empty ";
        isValid=false;
    }
    else if(fname.length<3)
    {
        document.getElementById("fullnameErr").innerHTML="x name should be Greater than the 3 characters";
        isValid=false;
    }
    else
    {
        document.getElementById("fullnameErr").innerHTML="";
    }

     /* EMAIL */
    if (email === "") 
    {
        document.getElementById("emailErr").innerHTML = "x This field cannot be empty";
        isValid = false;

    } 
    else if (!email.endsWith("@gmail.com")) 
    {
        document.getElementById("emailErr").innerHTML = "x Enter a valid Gmail address";
        isValid = false;
    } 
    else 
    {
        document.getElementById("emailErr").innerHTML = "";
    }

    //phone

    if(Phone == "")
    {
        document.getElementById("phoneErr").innerHTML="x This field cannot be empty"
        isValid = false;
    }
    else if (!/[0-9]{10}$/.test(Phone)) 
    {
        document.getElementById("phoneErr").innerHTML ="x Phone number should contain only digits and must contains 10 numbers";
        isValid = false;
    }
    else
    {
        document.getElementById("phoneErr").innerHTML="";
    }
    
    return isValid;

}


