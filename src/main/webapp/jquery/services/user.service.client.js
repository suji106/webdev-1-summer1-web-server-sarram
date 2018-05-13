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

	function updateUser(userId, user) {
		return fetch(self.url + "/" + userId, {
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