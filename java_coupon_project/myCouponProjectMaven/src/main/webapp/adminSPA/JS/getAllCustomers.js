/// <reference path="angular.js" />


var adminModule = angular.module("AdminAllModules")
	.controller("getAllCustomersController",function ($scope,$http,adminClientSideWebService)
	{
		var vmcust = this; 
			adminClientSideWebService.getAllCustomers($http).then (function (response)
				{
					vmcust.customers = response.data;
				},
				function (response)
				{
					vmcust.message = response.data;
				});
		$scope.sorting="name";
        $scope.reverseSorting=false;
        $scope.sortData = function(column) {
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
        	
        	vmcust.update=false;

        	vmcust.updateFunction =function ()
			{
        		vmcust.update=true;
			}
			
			vmcust.applyFunction =function (customerName, index)
		   	{
		   		var customer = {
		        		custName : customerName,
		        		password : vmcust.customers[index].passup
		   		}
		   		adminClientSideWebService.updateCustomer($http,customer).then(function(response)
		        		{
		       	    //		$state.reload();
		   			vmcust.message =  customer.custName + " was updated successfuly at the database";
		        		}, 
		        		function(response)
		        		{
		        			vmcust.message = response.data;     			
		        		});
		   		vmcust.update=false;

		   	}
			
			vmcust.cancelFunction =function ()
		   	{
				vmcust.update=false;

		   	}
        	
			vmcust.removeFunction =function (customer)
		   	{
		     	    adminClientSideWebService.removeCustomer($http,customer).then(function(response)
		       		{
		     	    	vmcust.message =  customer.custName + " was deleted successfuly from the database";	
		       		}, 
		       		function(response)
		       		{
		       			vm.message = response.data;
		       		});
		   	}
		    
			vmcust.createFunction =function ()
		   	{
		    	 var customer = {
		         		custName : vmcust.customerName,
		         		password : vmcust.customerPassword
		         }
		    	 adminClientSideWebService.createCustomer($http,customer).then(function(response)
		          		{
		         	 		vmcust.message =  customer.custName + " was added successfuly to the database";	
		          		}, 
		          		function(response)
		          		{
		          			vmcust.message = response.data;
		          			
		          		});
		    	}
	})

