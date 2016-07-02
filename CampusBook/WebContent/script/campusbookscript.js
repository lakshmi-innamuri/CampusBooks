/**
 * JS scripts for form validation
 */

function checkLoginForm(form) {

	var username = null;
	var password = null;

	if (null != form.username.value) {
		username = form.username.value.trim();
	}
	if (null != form.password.value) {
		password = form.password.value.trim();
	}
	if (username == "") {
		alert();
		document.getElementById('errorMsg').innerHTML = "Error: Username cannot be blank!";
		form.username.focus();
		return false;
	}

	if (password == "") {
		document.getElementById('errorMsg').innerHTML  = "Error: Password cannot be blank!";
		form.password.focus();
		return false;
	}

	return true;
}

function checkForm(form) {

	var winW = window.innerWidth;
	var winH = window.innerHeight;
	var dialogoverlay = document.getElementById('dialogoverlay');
	var dialogbox = document.getElementById('dialogbox');
	dialogoverlay.style.display = "block";
	dialogoverlay.style.height = winH + "px";
	dialogbox.style.left = (winW / 2) - (550 * .5) + "px";
	dialogbox.style.top = "100px";
	dialogbox.style.display = "block";
	document.getElementById('dialogboxhead').innerHTML = "Acknowledge This Message";
	document.getElementById('dialogboxbody').innerHTML = dialog;
	document.getElementById('dialogboxfoot').innerHTML = '<button onclick="Alert.ok()">OK</button>';
	if (form.username.value == "") {
		alert("Error: Username cannot be blank!");
		form.username.focus();
		return false;
	}
	re = /^\w+$/;
	if (!re.test(form.username.value)) {
		alert("Error: Username must contain only letters, numbers and underscores!");
		form.username.focus();
		return false;
	}
	if (form.password.value != "") {
		if (form.password.value.length < 6) {
			alert("Error: Password must contain at least six characters!");
			form.password.focus();
			return false;
		}
		if (form.password.value == form.username.value) {
			alert("Error: Password must be different from Username!");
			form.password.focus();
			return false;
		}
		re = /[0-9]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one number (0-9)!");
			form.password.focus();
			return false;
		}
		re = /[a-z]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one lowercase letter (a-z)!");
			form.password.focus();
			return false;
		}
		re = /[A-Z]/;
		if (!re.test(form.password.value)) {
			alert("Error: password must contain at least one uppercase letter (A-Z)!");
			form.password.focus();
			return false;
		}
	} else {
		alert("Error: Please check that you've entered and confirmed your password!");
		form.password.focus();
		return false;
	}
	alert("You entered a valid password: " + form.password.value);
	return true;
}

function validateRegistrationForm(form) {
	if (form.username.value == "") {
		alert();
		ele = document.getElementById("errorMsg");
		document.getElementById('errorMsg').innerHTML = "Error: Username cannot be blank!";
		//alert("Error: Username cannot be blank!"+ele.textContent);
		
		
		form.username.focus();
		return false;
	}
	re = /^\w+$/;
	if (!re.test(form.username.value)) {
		//alert("Error: Username must contain only letters or numbers or underscores!");
		document.getElementById('errorMsg').innerHTML = "Error: Username must contain only letters or numbers or underscores!";
		form.username.focus();
		return false;
	}
	if (form.password.value != "") {
		if (form.password.value.length < 6) {
			document.getElementById('errorMsg').innerHTML = "Error: Password must contain at least six characters!";
			//alert("Error: Password must contain at least six characters!");
			form.password.focus();
			return false;
		}
		if (form.password.value == form.username.value) {
			document.getElementById('errorMsg').innerHTML = "Error: Password must be different from Username!";
			//alert("Error: Password must be different from Username!");
			form.password.focus();
			return false;
		}
		re = /[0-9]/;
		if (!re.test(form.password.value)) {
			document.getElementById('errorMsg').innerHTML = "Error: password must contain at least one number (0-9)!";
			//alert("Error: password must contain at least one number (0-9)!");
			form.password.focus();
			return false;
		}
		re = /[a-z]/;
		if (!re.test(form.password.value)) {
			document.getElementById('errorMsg').innerHTML = "Error: password must contain at least one lowercase letter (a-z)!";
			//alert("Error: password must contain at least one lowercase letter (a-z)!");
			form.password.focus();
			return false;
		}
		re = /[A-Z]/;
		if (!re.test(form.password.value)) {
			document.getElementById('errorMsg').innerHTML = "Error: password must contain at least one uppercase letter (A-Z)!";
			//alert("Error: password must contain at least one uppercase letter (A-Z)!");
			form.password.focus();
			return false;
		}
	} else {
		//alert("Error: Password cannot be blank!");
		document.getElementById('errorMsg').innerHTML = "Error: Password cannot be blank!";
		form.password.focus();
		return false;
	}

	// first name validation

	if (form.Fname.value == "") {
		document.getElementById('errorMsg').innerHTML = "Error: First Name cannot be blank!";
		//alert("Error: First Name cannot be blank!");
		form.Fname.focus();
		return false;
	}
	re_name = /^[A-Za-z]+$/;
	if (!re_name.test(form.Fname.value)) {
		document.getElementById('errorMsg').innerHTML = "Error: First Name must contain only letters!";
		//alert("Error: First Name must contain only letters!");
		form.Fname.focus();
		return false;
	}

	// last name validation

	if (form.Lname.value == "") {
		document.getElementById('errorMsg').innerHTML = "Error: Last Name cannot be blank!";
		//alert("Error: Last Name cannot be blank!");
		form.Lname.focus();
		return false;
	}

	if (!re_name.test(form.Lname.value)) {
		document.getElementById('errorMsg').innerHTML = "Error: Last Name must contain only letters!";
		//alert("Error: Last Name must contain only letters!");
		form.Lname.focus();
		return false;
	}

	// EID Validation for non empty and only numbers

	if (form.eid.value == "") {
		document.getElementById('errorMsg').innerHTML = "Error: EID cannot be blank!";
		//alert("Error: EID cannot be blank!");
		form.eid.focus();
		return false;
	}
	if (form.eid.value.length != 8) {
		document.getElementById('errorMsg').innerHTML = "Error: EID must contain 8 numbers!";
		//alert("Error: EID must contain 8 numbers!");
		form.eid.focus();
		return false;
	}
	re_eid = /^[0-9]+$/;
	if (!re_eid.test(form.eid.value)) {
		document.getElementById('errorMsg').innerHTML = "Error: EID must contain only Numbers!";
		form.eid.focus();
		return false;
	}

	// var re_email = /^[A-Za-z0-9._]*/;
	var re_email = /^([a-zA-Z0-9_\.\-])/;
	// var re_email = /[A-Z0-9.\_\%+\-]/;
	// [-0-9a-zA-Z.+_]
	if (form.emailAddress.value.length == "") {
		document.getElementById('errorMsg').innerHTML ="Error: Email Address cannot be blank!";
		form.emailAddress.focus();
		return false;
	}

	if (!re_email.test(form.emailAddress.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Not a valid email address!";
		form.emailAddress.focus();
		return false;
	}

	var re_phno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;

	if (form.phoneNumber.value.length == "") {
		document.getElementById('errorMsg').innerHTML ="Error: Phone Number cannot be blank!";
		form.phoneNumber.focus();
		return false;
	}

	if (!re_phno.test(form.phoneNumber.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Not a valid Phone Number!";
		form.phoneNumber.focus();
		return false;
	}

	return true;
}

function validateBookDetails(form) {

	if (form.bookname.value == "") {
		
		document.getElementById('errorMsg').innerHTML ="Error: Book Title cannot be blank!";
		form.bookname.focus();
		return false;
	}

	re_name = /^[A-Za-z ]+$/;
	if (!re_name.test(form.bookname.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Book Title must contain only letters!";
		form.bookname.focus();
		return false;
	}
	if (form.isbn.value == "") {
		document.getElementById('errorMsg').innerHTML ="Error: ISBN cannot be blank!";
		form.isbn.focus();
		return false;
	}
	if (form.author.value == "") {
		document.getElementById('errorMsg').innerHTML ="Error: Author cannot be blank!";
		form.author.focus();
		return false;
	}

	if (!re_name.test(form.author.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Author must contain only letters!";
		form.author.focus();
		return false;
	}

	if (form.coursename.value == "") {
		document.getElementById('errorMsg').innerHTML ="Error: Course Name cannot be blank!";
		form.coursename.focus();
		return false;
	}

	if (!re_name.test(form.coursename.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Course Name must contain only letters!";
		form.coursename.focus();
		return false;
	}

	if (form.coursenumber.value == "") {
		document.getElementById('errorMsg').innerHTML ="Error: Course Number cannot be blank!";
		form.coursenumber.focus();
		return false;
	}

	re_digits = /^[0-9]+$/;
	if (!re_digits.test(form.coursenumber.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Course Number must contain only Numbers!";
		form.coursenumber.focus();
		return false;
	}

	var re_price = /^\d+(\.\d{0,2})?$/;
	if (!re_price.test(form.price.value)) {
		document.getElementById('errorMsg').innerHTML ="Error: Not a valid Price!";
		form.price.focus();
		return false;
	}
	
	var selected_index = form.elements["deptid"].selectedIndex;
	
	if (selected_index > 0) {

		var selected_option_value = oForm.elements["deptid"].options[selected_index].value;
		var selected_option_text = oForm.elements["deptid"].options[selected_index].text;
	} else {
		document.getElementById('errorMsg').innerHTML ='Error: Department Code must be selected ';
		form.deptid.focus();
		return false;
	}

	return true;

}

function removeErrorMsgs() {
	if (document.getElementById("errorMsg").style.display != "none") {
		document.getElementById("errorMsg").style.display = "none";
	}
}