(function () {
	var $usernameFld, $passwordFld;
	var $removeBtn, $editBtn, $createBtn;
	var $firstNameFld, $lastNameFld;
	var $userRowTemplate, $tbody;
	$(main);
	
	var userService = new UserServiceClient()
	
	function main() {		
		tbody = $('tbody');
		$userRowTemplate = $('.xxx');
		$('#createUser').click(createUser);
		$('#updateUser').click(updateUser);
		$('#searchUser').click(searchUser);
	
		findAllUsers();
	}
	
	function searchUser(event) {
		console.log('deleteUser');

		var searchBtn = $(event.currentTarget);
        var userId = searchBtn
            .parent()
            .parent()
            .attr('id');
        console.log(userId);

        userService.searchUser(342).then(renderUsers);
    }
	
	function deleteUser(event) {
		console.log('deleteUser');

		var deleteBtn = $(event.currentTarget);
        var userIdBody = deleteBtn
            .parent()
            .parent()
            .parent();
        
        console.log(userIdBody.html());
        
        var userId = userIdBody.attr('id');

        userService.deleteUser(userId).then(renderUsers);
    }
	
	function editUser(event) {
		console.log('deleteUser');

		var editBtn = $(event.currentTarget);
        var userIdBody = editBtn
            .parent()
            .parent()
            .parent();
        
        console.log(userIdBody.html());
        
        var userId = userIdBody.attr('id');

        userService.editUser(userId).then(renderUsers);
    }
	
	function updateUser() {
		console.log('updateUser');

		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$firstNameFld = $('#firstNameFld').val();
		$lastNameFld = $('#lastNameFld').val();

		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName
		};

		userService
		.updateUser(342, user)
		.then(findAllUsers);
	}
	
	function createUser() {
		console.log('createUser');

		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$firstNameFld = $('#firstNameFld').val();
		$lastNameFld = $('#lastNameFld').val();

		var user = {
				username: username,
				password: password,
				firstName: firstName,
				lastName: lastName
		};

		userService
		.createUser(user, function() {
			  alert("New user created");
		})
		.then(findAllUsers);
	}

	function findAllUsers() {
		console.log('findAllUsersController');
		userService.findAllUsers().then(renderUsers);
	}
	
	function renderUsers(users) {
		console.log("Rendering users");
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = $userRowTemplate.clone(); 
            clone.attr('id', user.id);
            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-remove').click(deleteUser);
            // clone.find('.wbdv-edit').click(editUser);
            tbody.append(clone);
        }
    }
})();
