(function()	{
	var myApp = angular.module('AdminAllModules');
	myApp.service('adminClientSideWebService', adminClientSideWebService);
	
	function adminClientSideWebService($http)
	{
		var self = this;
		self.createCompany = function($http, company)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/createCompany", company);
			return promise;
		}
		
		self.removeCompany = function($http, company)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/removeCompany", company);
			return promise;
		}
		
		self.updateCompany = function($http, company)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/updateCompany", company);
			return promise;
		}
		
		
		
		self.getAllCompanies = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/getAllCompanies");
			return promise;
		}
		
		self.createCustomer = function($http, customer)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/createCustomer", customer);
			return promise;
		}
		
		self.removeCustomer = function($http, customer)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/removeCustomer", customer);
			return promise;
		}
		
		self.updateCustomer = function($http, customer)
		{
			var promise =  $http.post("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/updateCustomer", customer);
			return promise;
		}
		
	
		self.getAllCustomers = function($http)
		{
			var promise =  $http.get("http://localhost:8080/myCouponProjectMaven/webapi/AdminService/getAllCustomers");
			return promise;
		}
		
	
	}

		})();
  
  