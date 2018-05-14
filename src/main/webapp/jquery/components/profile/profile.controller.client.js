(function () {
	    var $usernameFld, $phoneFld, $emailFld, $roleFld, $dobFld;
	    var $registerBtn;
	    var flag = true;
	    
	    var userService = new UserService();
	    $(main);
	
	    function main() { 
			$('#updateBtn').click(updateProfile);
			$('#logoutBtn').click(loginRedirect);
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
			
			var appendString = '<div class="popup">Profile successfully updated!</div>';
			
			userService
			.updateUser(user).then(function(response){
				if(response.status == 200 && flag == true){
					flag = false;
					$('form')
					.prepend(appendString);
					// alert("Successfully updated!");
				}
				else{
					$(".popup").remove();
				}
			});
	    }
	    
	    function loginRedirect() {
			console.log("sss");
			window.location.href = "http://localhost:8080/jquery/components/login/login.template.client.html"
		}
})();
