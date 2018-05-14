(function () {
	    var $usernameFld, $phoneFld, $emailFld, $roleFld, $dobFld;
	    var $registerBtn;
	    var userService = new UserService();
	    $(main);
	
	    function main() { 
			$('#updateBtn').click(updateProfile);
	    }
	    
	    function updateProfile() {
	    	console.log('updateProfile');
	
	    	$usernameFld = $('#usernameFld').val();
	    	$phoneFld = $('#phoneFld').val();
	    	$emailFld = $('#emailFld').val();
	    	$roleFld = $('#roleFld').val();
	    	$dobFld = $('#dobFld').val();
	
			var user = {
					username: $usernameFld,
					phone: $phoneFld,
					email: $emailFld,
					role: $roleFld,
					dateOfBirth: $dobFld
			};
			
			var s = '<div class="popup">Profile successfully updated!</div>';
			userService
			.updateUser(user).then(function(response){
				if(response.status == 200){
					$('form')
					.prepend(s);
					// alert("Successfully updated!");
				}
			});
	    }
	    
	    function xxx() {
	    	alert("rrrrr");
	    }
})();
