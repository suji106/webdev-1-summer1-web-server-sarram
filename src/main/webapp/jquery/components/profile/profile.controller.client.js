(function () {
	var usernameParam = window.location.hash.substring(1);
	var $usernameFld, $phoneFld, $emailFld, $roleFld, $dobFld;
	var $registerBtn;
	var flag = true;
	
	var userService = new UserService();
	
	var userServiceClient = new UserServiceClient();
	userServiceClient.searchByUserName(usernameParam).then(main);

	function main(user) { 
		console.log("user:");
		console.log(JSON.stringify(user));
		$('#usernameFld').val(usernameParam);
		$('#usernameFld').attr("disabled", true);
		
		// $('#usernameFld').val(usernameParam);
		
		$('#phoneFld').val(user.phone);
		$('#emailFld').val(user.email);
		$('#roleFld').val(user.role);
		$('#dobFld').val(user.dateOfBirth);
		
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

		var user = new User($usernameFld, null, null, null, $emailFld, $phoneFld, $roleFld, $dobFld);
		
		console.log(JSON.stringify(user));

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
		window.location.href = "/jquery/components/login/login.template.client.html"
	}
})();
