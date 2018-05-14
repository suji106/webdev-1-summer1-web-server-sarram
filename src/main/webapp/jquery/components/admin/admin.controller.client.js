(function () {
	var $usernameFld, $passwordFld;
	var $removeBtn, $editBtn, $createBtn;
	var $firstNameFld, $lastNameFld;
	var $userRowTemplate, $tbody;
	var userId;
	$(main);

	var userService = new UserServiceClient()

	function main() {		
		tbody = $('tbody');
		$userRowTemplate = $('.table-row');
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

		userService.searchUser(342).then(renderUsers);
	}
	
	function editUser(event) {
		console.log('editUser');

		var deleteBtn = $(event.currentTarget);
		userId = deleteBtn
		.parent()
		.parent()
		.parent()
		.attr('id');
		
		$valuesTemplate = $('#' + userId);
		
		$('#usernameFld').val($valuesTemplate.find('.wbdv-username').html());		
		$('#firstNameFld').val($valuesTemplate.find('.wbdv-first-name').html());
		$('#lastNameFld').val($valuesTemplate.find('.wbdv-last-name').html());		
	}
	
	function updateUser() {
		console.log('updateUser');
		
		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$firstNameFld = $('#firstNameFld').val();
		$lastNameFld = $('#lastNameFld').val();
		
		var user = {
				username: $usernameFld,
				password: $passwordFld,
				firstName: $firstNameFld,
				lastName: $lastNameFld
		};

		userService
		.updateUser(user)
		.then(findAllUsers);
	}
	
	function deleteUser(event) {
		console.log('deleteUser');

		var deleteBtn = $(event.currentTarget);
		var userId = deleteBtn
		.parent()
		.parent()
		.parent()
		.attr('id');

		userService
		.deleteUser(userId).then(findAllUsers);
	}

	function createUser() {
		console.log('createUser');

		$usernameFld = $('#usernameFld').val();
		$passwordFld = $('#passwordFld').val();
		$firstNameFld = $('#firstNameFld').val();
		$lastNameFld = $('#lastNameFld').val();

		var user = {
				username: $usernameFld,
				password: $passwordFld,
				firstName: $firstNameFld,
				lastName: $lastNameFld
		};

		userService
		.createUser(user).then(findAllUsers);
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
			clone.find('.wbdv-edit').click(editUser);
			tbody.append(clone);
		}
	}
})();
