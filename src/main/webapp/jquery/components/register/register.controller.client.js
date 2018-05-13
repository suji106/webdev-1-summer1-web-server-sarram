(function () {
	    var $usernameFld, $passwordFld, $verifyPasswordFld;
	    var $registerBtn;
	    var userService = new UserService();
	    $(main);
	
	    function main() { 
			$('#signUpBtn').click(register);
	    }
	    
	    function register() {
	    	console.log('loginUser');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();
			$verifyPasswordFld = $('#verifyPasswordFld').val();
	
			var user = {
					username: $usernameFld,
					password: $passwordFld,
			};
	
			userService
			.register(user)
			.then(xxx);
	    }
	    
	    function xxx() {
	    	alert("rrrrr");
	    }
})();
