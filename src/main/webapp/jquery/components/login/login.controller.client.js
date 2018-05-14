(function () {
	    var $usernameFld, $passwordFld, $verifyPasswordFld;
	    var $registerBtn;
	    var userService = new UserService();
	    $(main);
	
	    function main() { 
			$('#signInBtn').click(login);
			$('#signUpBtn').click(signUpRedirect);
			// $('#forgotBtn').click(login);
	    }
	    
	    function login() {
	    	console.log('loginUser');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();
	
//			var user = {
//					username: $usernameFld,
//					password: $passwordFld,
//			};
//			
			var user = new User($usernameFld, $passwordFld, null, null);
	
			userService
			.login(user)
			.then(function(response){
				if(response.status != 200){
					alert("No user found with the given combination!!");
				}
				else if(response.status == 200){
					window.location.href = "http://localhost:8080/jquery/components/profile/profile.template.client.html";
				}
			});
	    }
	    
	    function signUpRedirect() {
			window.location.href = "http://localhost:8080/jquery/components/register/register.template.client.html"
		}
})();
