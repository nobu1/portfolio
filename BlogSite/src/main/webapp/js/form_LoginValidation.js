//Login parameters
const loginEmail = document.querySelector('#F_LoginEmail');
const loginEmailCheckMsg = document.querySelector('#email_check_login');
const loginPassword = document.querySelector('#F_LoginPassword');
const loginPasswordCheckMsg = document.querySelector('#password_check_login');

//Validation format
const emailValidation = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
const passwordValidation = /^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,100}$/;

//Login Validation
loginEmail.addEventListener('keyup', onKeyUpLoginEmail);
loginPassword.addEventListener('keyup', onKeyUpLoginPassword);

function onKeyUpLoginEmail() {
	if (emailValidation.test(loginEmail.value)) {
		loginEmailCheckMsg.style.color = "Green";
		loginEmailCheckMsg.style.fontweight = "bold";
		loginEmailCheckMsg.innerText = "OK";
	} else {
		loginEmailCheckMsg.style.color = "Red";
		loginEmailCheckMsg.style.fontweight = "bold";
		loginEmailCheckMsg.innerText = "Not match email format.";
		document.querySelector('#loginBtn').disabled = true;
	}
}

function onKeyUpLoginPassword() {
	if (passwordValidation.test(loginPassword.value)) {
		loginPasswordCheckMsg.style.color = "Green";
		loginPasswordCheckMsg.style.fontweight = "bold";
		loginPasswordCheckMsg.innerText = "OK";
		document.querySelector('#loginBtn').disabled = false;
	} else {
		loginPasswordCheckMsg.style.color = "Red";
		loginPasswordCheckMsg.style.fontweight = "bold";
		loginPasswordCheckMsg.innerText = "Not satisfy password regulation.";
		document.querySelector('#loginBtn').disabled = true;
	}
}