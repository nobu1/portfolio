//Forgot parameters
const forgotEmail = document.querySelector('#F_LoginForgotEmail');
const forgotEmailCheckMsg = document.querySelector('#email_check_forgot');
const forgotPassword = document.querySelector('#F_ResetNewPassword');
const forgotRetypePassword = document.querySelector('#F_ReEnterPassword');
const forgotRetypePasswordCheckMsg = document.querySelector('#password_check_forgot');

//Validation format
const emailValidation = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
const passwordValidation = /^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,100}$/;

forgotEmail.addEventListener('keyup', onKeyUpEmail);
forgotPassword.addEventListener('keyup', onKeyUpPassword);

function onKeyUpEmail() {
	if (emailValidation.test(forgotEmail.value)) {
		forgotEmailCheckMsg.style.color = "Green";
		forgotEmailCheckMsg.style.fontweight = "bold";
		forgotEmailCheckMsg.innerText = "OK";
	} else {
		forgotEmailCheckMsg.style.color = "Red";
		forgotEmailCheckMsg.style.fontweight = "bold";
		forgotEmailCheckMsg.innerText = "Not match email format.";
		document.querySelector('#changeBtn').disabled = true;
	}
}

function onKeyUpPassword() {
	if (passwordValidation.test(forgotPassword.value)) {
		forgotRetypePassword.addEventListener('keyup', onKeyUpforgotRetypePassword);
	} else {
		document.querySelector('#changeBtn').disabled = true;
	}
}

function onKeyUpforgotRetypePassword() {
	if (passwordValidation.test(forgotRetypePassword.value)) {
		if (forgotPassword.value === forgotRetypePassword.value) {
			forgotRetypePasswordCheckMsg.style.color = "Green";
			forgotRetypePasswordCheckMsg.style.fontweight = "bold";
			forgotRetypePasswordCheckMsg.innerText = "OK";
			document.querySelector('#changeBtn').disabled = false;
		} else {
			forgotRetypePasswordCheckMsg.style.color = "Red";
			forgotRetypePasswordCheckMsg.style.fontweight = "bold";
			forgotRetypePasswordCheckMsg.innerText = "Not match password.";
			document.querySelector('#changeBtn').disabled = true;
		}
	} else {
		forgotRetypePasswordCheckMsg.style.color = "Red";
		forgotRetypePasswordCheckMsg.style.fontweight = "bold";
		forgotRetypePasswordCheckMsg.innerText = "Not satisfy password regulation.";
		document.querySelector('#changeBtn').disabled = true;
	}
}