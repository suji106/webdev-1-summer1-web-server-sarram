(function () {
	    var $usernameFld, $passwordFld, $verifyPasswordFld;
	    var $registerBtn;
	    var userService = new UserService();
	    var userServiceClient = new UserServiceClient();
	    $(main);
	
	    function main() { 
			$('#signInBtn').click(login);
			$('#signUpBtn').click(signUpRedirect);
			$('#forgotBtn').click(forgotPassword);
	    }
	    
	    function forgotPassword() {
	    	console.log('forgotPasswordController');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();

			var user = new User($usernameFld, null, null, null, null, null, null, null);
	
			userServiceClient
			.searchByUserName($usernameFld)
			.then(function(response){
				console.log(response);
				if(response.hasOwnProperty('status')){
					alert("No such user exists!!");
				}
				else{
					sendEmail(response);
				}
			});
	    }
	    
	    function sendEmail(user){
	    	console.log("sendEmailController");
	    	console.log(user.password);
	    	$passwordFld = user.password;
	    	subject = 'Email Recovery: Admin Services for ' + user.firstName;
	    	body = 'Your password is: ' + $passwordFld;
	    	mailContent = 'mailto:' + email + '?subject=' + subject + '&body=' + body
	    	window.open(mailContent);
	    	var email = prompt("Please enter your email address to recover your password!", "");
	    	console.log(email);
	    	alert("An email containing link to reset your password has been sent to " + email);
	    	location.reload();
	    }
	    
	    function login() {
	    	console.log('loginUser');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();

			var user = new User($usernameFld, $passwordFld, null, null, null, null, null, null);
	
			userService
			.login(user)
			.then(function(response){
				if(response.status == 401){
					alert("Password entered is wrong for the user!!");
				}
				else if(response.status == 200){
					window.location.href = "/jquery/components/profile/profile.template.client.html#" 
						+ $usernameFld;
				}
				else{
					alert("No user found with the given username!!");
				}
			});
	    }
	    
	    function signUpRedirect() {
			window.location.href = "/jquery/components/register/register.template.client.html"
		}
})();
