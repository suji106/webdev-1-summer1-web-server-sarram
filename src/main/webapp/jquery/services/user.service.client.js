function UserServiceClient() {
	this.createUser = createUser;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.searchByUserName = searchByUserName;
	this.findAllUsers = findAllUsers;
	this.user = 
		'http://localhost:8080/api/user';
	this.users = 
		'http://localhost:8080/api/users';
	this.register =
		'http://localhost:8080/api/register';
	var self = this;

	function updateUser(user) {
		return fetch(self.user, {
			method: 'put',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
	}

	function createUser(user, callback) {
		return fetch(self.register, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
		callback();
	}
	
	function searchByUserName(userName) {
		console.log('profileByNameService');
		return fetch(self.user + '?user=' + userName)
		.then(function (response) {
			var r = response.json()
			// console.log(r);
			return r;
		});
	}

/*
	function searchUser(userId) {
		console.log("searchService")
		return fetch(self.user)
		.then(function (response) {
			return response.json();
		});
	}*/

	function deleteUser(userId) {
		fetch(self.user + "/" + userId, {
			method: 'delete'});
		return fetch(self.user)
		.then(function (response) {
			return response.json();
		});

	}

	function findAllUsers() {
		console.log("finding users");
		return fetch(self.users)
		.then(function (response) {
			return response.json();
		});
	}
}

function UserService() {
	console.log("userService");
	this.register = register;
	this.login = login;
	this.updateUser = updateUser;
	
	this.registerUrl =
		"http://localhost:8080/api/register";
	this.loginUrl =
		"http://localhost:8080/api/login";
	this.profile = 
		"http://localhost:8080/api/profile";
	
	var self = this;
	
	function register(user) {
		console.log('registerService');
		return fetch(self.registerUrl, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
	}
	
	function login(user) {
		console.log('loginService');
		return fetch(self.loginUrl, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
	}
	
	function updateUser(user) {
		console.log('updateService');
		return fetch(self.profile, {
			method: 'put',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
	}
}
