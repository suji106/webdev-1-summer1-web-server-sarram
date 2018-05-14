(function () {
	var $usernameFld, $passwordFld, $verifyPasswordFld;
	var $registerBtn;
	var userService = new UserService();
	$(main);

	function main() { 
		$('#signUpBtn').click(register);
		$('#signInBtn').click(loginRedirect);
	}

	function register() {
		console.log('registerUser');

		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$verifyPasswordFld = $('#verifyPasswordFld').val();
		
		if($passwordFld == $verifyPasswordFld){
			
			var user = new User($usernameFld, $passwordFld, null, null);
			
			userService
			.register(user)
			.then(function(response){
				if(response.status != 200){
					alert("User already exists!!");
				}
				else if(response.status == 200){
					window.location.href = "http://localhost:8080/jquery/components/profile/profile.template.client.html";
				}
			});
		}
		else{
			alert("Passwords don't match!")
		}
	}
	
	function loginRedirect() {
		console.log("sss");
		window.location.href = "http://localhost:8080/jquery/components/login/login.template.client.html"
	}
})();
