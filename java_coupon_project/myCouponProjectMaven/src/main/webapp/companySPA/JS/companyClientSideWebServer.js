(function()	{
	var myApp = angular.module("CompanyAllModules",[]);
	myApp.service("companyClientSideWebService", companyClientSideWebService);
	
	function companyClientSideWebService($http)
	{
		var self = this;
		self.createCoupon = function($http, coupon)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/CompanyService/createCoupon", coupon);
			return promise;
		}
		
		self.removeCoupon = function($http, coupon)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/CompanyService/removeCoupon", coupon);
			return promise;
		}
		
		self.updateCoupon = function($http, coupon)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/CompanyService/updateCoupon", coupon);
			return promise;
		}
			
		self.getAllCoupons = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/CompanyService/getAllCoupons");
			return promise;
		}
		
		self.printCompany = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/CompanyService/printCompany");
			return promise;
		}
	}

		})();
  
  