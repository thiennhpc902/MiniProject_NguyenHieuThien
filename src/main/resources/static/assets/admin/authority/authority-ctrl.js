app.controller('authority-ctrl', function ($scope, $http) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initialize = function () {
		$http.get('/rest/roles').then(response => {
			$scope.roles = response.data;
		})

		$http.get('/rest/accounts?admins=true').then(response => {
			$scope.admins = response.data;
			console.log($scope.admins);
		})
		$http.get('/rest/authorities?admins=true').then(response => {
			$scope.authorities = response.data;
		}).catch(err => {
			console.log(err);
			$location.path("/unauthorized");
		})
	}

	$scope.authority_of = function (acc, role) {
		console.log(acc);
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
			// tìm kiếm xem tài khoản đó có được phân quyền đó hay chưa, nếu có thì checked
		}s
	}

	$scope.authority_change = function (acc, role) {
		var authority = $scope.authority_of(acc, role);
		if (authority) {
			$scope.revoke_authority(authority); // nếu có được phân quyền đó r mà checked nữa thì thu hồi quyền
		} else {
			authority = { account: acc, role: role }; // chuyển authority thành đối tượng
			$scope.grant_authority(authority); // thêm mới quyền
		}
	}
	// Thêm mới quyền
	$scope.grant_authority = function (authority) {
		$http.post(`/rest/authorities`, authority).then(response => {
			$scope.authorities.push(response.data); // lấy data về đưa lên view
			alert('Cấp quyền thành công');

		}).catch(error => {
			alert('Cấp quyền thất bại')
			console.log('ERROR', error);
		})
	}

	$scope.revoke_authority = function (authority) {
		$http.delete(`/rest/authorities/${authority.id}`).then(response => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id); // tìm vị trị của id
			$scope.authorities.slice(index, 1); // xóa khỏi view
			alert('Thu hồi quyền thành công');

		}).catch(error => {
			alert('Thu hồi quyền thất bại');
			console.log('ERROR', error);
		})
	}
	$scope.initialize();
})