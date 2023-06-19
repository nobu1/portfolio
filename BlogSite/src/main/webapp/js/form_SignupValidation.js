//Signup parameters
const signupNickname = document.querySelector('#F_NicknameSignup');
const signupNicknameCheckMsg = document.querySelector('#nickname_check_signup');
const signupEmail = document.querySelector('#F_EmailSignup');
const signupEmailCheckMsg = document.querySelector('#email_check_signup');
const signupPassword = document.querySelector('#F_PasswordSignup');
const signupRetypePassword = document.querySelector('#F_RetypePasswordSignup');
const signupPasswordCheckMsg = document.querySelector('#password_check_signup');

//Validation format
const emailValidation = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/;
const passwordValidation = /^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,100}$/;

//Signup Validation
signupNickname.addEventListener('keyup', onKeyUpSignupNickname);
signupEmail.addEventListener('keyup', onKeyUpSignupEmail);
signupPassword.addEventListener('keyup', onKeyUpSignupPassword);

function onKeyUpSignupNickname() {
	if (signupNickname.value.length <= 100) {
		signupNicknameCheckMsg.style.color = "Green";
		signupNicknameCheckMsg.style.fontweight = "bold";
		signupNicknameCheckMsg.innerText = "OK";
	} else {
		signupNicknameCheckMsg.style.color = "Red";
		signupNicknameCheckMsg.style.fontweight = "bold";
		signupNicknameCheckMsg.innerText = "Please input the nickname less than 100 characters.";
		document.querySelector('#signupBtn').disabled = true;
	} 
}

function onKeyUpSignupEmail() {
	if (emailValidation.test(signupEmail.value)) {
		signupEmailCheckMsg.style.color = "Green";
		signupEmailCheckMsg.style.fontweight = "bold";
		signupEmailCheckMsg.innerText = "OK";
	} else {
		signupEmailCheckMsg.style.color = "Red";
		signupEmailCheckMsg.style.fontweight = "bold";
		signupEmailCheckMsg.innerText = "Not match email format.";
		document.querySelector('#signupBtn').disabled = true;
	}
}

function onKeyUpSignupPassword() {
	if (passwordValidation.test(signupPassword.value)) {
		signupRetypePassword.addEventListener('keyup', onKeyUpSignupRetypePassword);
	} else {
		document.querySelector('#signupBtn').disabled = true;
	}
}

function onKeyUpSignupRetypePassword() {
	if (passwordValidation.test(signupRetypePassword.value)) {
		if (signupPassword.value === signupRetypePassword.value) {
			signupPasswordCheckMsg.style.color = "Green";
			signupPasswordCheckMsg.style.fontweight = "bold";
			signupPasswordCheckMsg.innerText = "OK";
			document.querySelector('#signupBtn').disabled = false;
		} else {
			signupPasswordCheckMsg.style.color = "Red";
			signupPasswordCheckMsg.style.fontweight = "bold";
			signupPasswordCheckMsg.innerText = "Not match password.";
			document.querySelector('#signupBtn').disabled = true;
		}
	} else {
		signupPasswordCheckMsg.style.color = "Red";
		signupPasswordCheckMsg.style.fontweight = "bold";
		signupPasswordCheckMsg.innerText = "Not satisfy password regulation.";
		document.querySelector('#signupBtn').disabled = true;
	}
}