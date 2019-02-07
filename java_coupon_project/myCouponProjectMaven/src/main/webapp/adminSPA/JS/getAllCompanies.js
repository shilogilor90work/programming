/// <reference path="angular.js" />


var adminModule = angular.module("AdminAllModules")
	.controller("getAllCompaniesController",function ($scope, $http,adminClientSideWebService)
{
	var vm = this;
	
	vm.logout = function ()
	{
		$http.get("http://localhost:8080/myCouponProjectMaven/webapi/LogoutServlet").then(function(response)
		{
			$window.location.href = "http://localhost:8080/myCouponProjectMaven/loginSystem.html"					
		},
		function(response)
		{
			
		});
	}
	
	
			adminClientSideWebService.getAllCompanies($http).then (function (response)
				{
				vm.companies = response.data;
				},
				function (response)
				{
					vm.message = response.data;
				});
			$scope.sorting="name";
			$scope.reverseSorting=false;
			$scope.sortData = function(column)
	        {
				$scope.reverseSorting =($scope.sorting== column) ? !$scope.reverseSorting : false;  
				$scope.sorting = column;
	        }
			$scope.getSortClass = function (column)
     {
     		if ($scope.sorting == column)
     		{
		         return $scope.reverseSorting ? 'arrow-down' :'arrow-up'
     		}
		         return '';
     }
			$scope.update=false;

			vm.updateFunction =function ()
   	{
				$scope.update=true;

   	}
   
			vm.applyFunction =function (companyName, index)
   	{
   		var company = {
        		compName : companyName,
        		password : vm.companies[index].passup,
        		email : vm.companies[index].emup
   		}
   		adminClientSideWebService.updateCompany($http,company).then(function(response)
        		{
       	    //		$state.reload();
   			vm.message =  company.compName + " was updated successfuly at the database";
        		}, 
        		function(response)
        		{
        			vm.message = response.data;     			
        		});
   		$scope.update=false;

   	}
			vm.cancelFunction =function ()
   	{
				$scope.update=false;

   	}
   	
			vm.removeFunction =function (company)
   	{
     	    adminClientSideWebService.removeCompany($http,company).then(function(response)
       		{
     	    	vm.message =  company.compName + " was deleted successfuly from the database";	
       		}, 
       		function(response)
       		{
       			vm.message = response.data;
       		});
   	}
			vm.createFunction =function ()
   	{
    	 var company = {
         		compName : vm.companyName,
         		password : vm.companyPassword,
         		email : vm.companyEmail
         }
         adminClientSideWebService.createCompany($http,company).then(function(response)
         		{
        	 		vm.message =  company.compName + " was added successfuly to the database";	
         		}, 
         		function(response)
         		{
         			vm.message = response.data;
         			
         		});
   	}
})

 .directive('myLogoutDirective', function() {
  return {
    restrict: 'AE',
    templateUrl: '../directiveLogout.html'
  };
});