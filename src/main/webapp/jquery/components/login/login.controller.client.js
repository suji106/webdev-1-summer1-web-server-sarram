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
	    	var email = user.email;
	    	subject = 'Email Recovery: Admin Services for ' + user.firstName;
	    	body = 'Your password is: ' + $passwordFld;
	    	mailContent = 'mailto:' + email + '?subject=' + subject + '&body=' + body
	    	window.open(mailContent);
	    	alert("Password has been sent to your email address!");
	    }
	    
	    function login() {
	    	console.log('loginUser');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();
	
//			var user = {
//					username: $usernameFld,
//					password: $passwordFld,
//			};
			
			var user = new User($usernameFld, $passwordFld, null, null, null, null, null, null);
	
			userService
			.login(user)
			.then(function(response){
				if(response.status != 200){
					alert("No user found with the given combination!!");
				}
				else if(response.status == 200){
					window.location.href = "http://localhost:8080/jquery/components/profile/profile.template.client.html#" 
						+ $usernameFld;
				}
			});
	    }
	    
	    function signUpRedirect() {
			window.location.href = "http://localhost:8080/jquery/components/register/register.template.client.html"
		}
})();
