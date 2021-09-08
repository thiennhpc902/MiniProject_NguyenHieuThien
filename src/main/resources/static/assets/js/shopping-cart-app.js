var app = angular.module('shopping-cart-app', []);
app.controller('ctrl', function($scope, $http) {


	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.id === id); // kiểm tra id nhập vào có tồn tại trong giỏ hàng hay chưa
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else // nếu chưa tồn tại trong giỏ hàng
				$http.get(`/rest/products/${id}`).then(response => {
					response.data.qty = 1; // set sl = 1
					this.items.push(response.data); // push vào mảng
					this.saveToLocalStorage();
				})
		},

		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() {
			this.items = [];
			this.saveToLocalStorage();

		},
		amt_of(item) { },
		get count() {
			return this.items.map(item => item.qty).reduce((total, qty) => total += qty, 0);
			// lấy ra số lượng trên mỗi sản phẩm
			// rồi cộng lại 

		},
		get amount() {
			return this.items.map(item => item.qty * item.price).reduce((total, qty) => total += qty, 0);
			// lấy ra số lượng * đơn giá trên mỗi sản phẩm
			// rồi cộng lại 


		},
		loadLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
			// nếu dữ liệu trong local của Items có thì gán vào danh sách Items, ngược lại gán []
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items)); // thực hiện chuyển mảng items sang json 
			localStorage.setItem("cart", json); // lưu vào localStorage
		}
	}
	$scope.cart.loadLocalStorage();

	$scope.order = {
		createDate: new Date(),
		address: "",
		account: {
			username: $("#username").text()
		},
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			})
		},
		purchase() {
			var order = angular.copy(this);
			// Thực hiện đặt hàng

			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công")
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(erro => {
				alert("Đặt hàng lỗi")
				console.log(erro)
			})
		}
	}
})