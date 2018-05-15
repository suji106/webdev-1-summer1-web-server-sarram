function UserServiceClient() {
	this.createUser = createUser;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.searchUser = searchUser;
	this.findAllUsers = findAllUsers;
	this.url = 
		'http://localhost:8080/api/user';
	this.register =
		'http://localhost:8080/api/register';
	var self = this;

	function updateUser(user) {
		return fetch(self.url, {
			method: 'put',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
	}

	function searchUser(userId, user) {
		return fetch(self.url + "/" + userId, {
			method: 'get'       
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

	function searchUser(userId) {
		console.log("searchService")
		return fetch(self.url)
		.then(function (response) {
			return response.json();
		});
	}

	function deleteUser(userId) {
		fetch(self.url + "/" + userId, {
			method: 'delete'});
		return fetch(self.url)
		.then(function (response) {
			return response.json();
		});

	}

	function findAllUsers() {
		return fetch(self.url)
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
	this.searchByUserName = searchByUserName;
	
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
	
	function searchByUserName(userName) {
		console.log('profileByNameService');
		return fetch(self.profile + '?user=' + userName)
		.then(function (response) {
			return response.json();
		});
	}
}
