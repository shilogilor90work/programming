(function()	{
	var myApp = angular.module("CustomerAllModules");
	myApp.service("customerClientSideWebService", customerClientSideWebService);
	
	function customerClientSideWebService($http)
	{
		var self = this;
		self.purchaseCoupon = function($http, coupon)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/CustomerService/purchaseCoupon", coupon);
			return promise;
		}
		
		self.getAllCoupons = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/CustomerService/getAllPurchasedCoupons");
			return promise;
		}
		self.getAllAndEveryCoupon = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/CustomerService/getAllAndEveryCoupon");
			return promise;
		}
		self.getAllAndEveryCouponByType = function($http ,type)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/CustomerService/getAllAndEveryCouponByType",type);
			return promise;
		}
		
		
	}

		})();
  
  