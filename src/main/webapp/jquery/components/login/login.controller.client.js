(function () {
	    var $usernameFld, $passwordFld, $verifyPasswordFld;
	    var $registerBtn;
	    var userService = new UserService();
	    $(main);
	
	    function main() { 
			$('#signInBtn').click(login);
	    }
	    
	    function login() {
	    	console.log('loginUser');
	
	    	$usernameFld = $('#usernameFld').val();
			$passwordFld = $('#passwordFld').val();
	
			var user = {
					username: $usernameFld,
					password: $passwordFld,
			};
	
			userService
			.login(user)
			.then(xxx);
	    }
	    
	    function xxx() {
	    	alert("ssss");
	    }
})();
