/// <reference path="JS/angular.js" />


var customerModule = angular.module("CustomerAllModules")
	.controller("getAllAndEveryCouponsController",function ($scope,$http,customerClientSideWebService)
	{
		vmMainCustomer = this;
		
		vmMainCustomer.logout = function ()
		{
			$http.get("http://localhost:8080/myCouponProjectMaven/LogoutServlet").then(function(response)
			{
				$window.location.href = "http://localhost:8080/myCouponProjectMaven/loginSystem.html"					
			},
			function(response)
			{
				
			});
		}
		
			customerClientSideWebService.getAllAndEveryCoupon($http).then (function (response)
				{
				vmMainCustomer.coupons = response.data;
				},
				function (response)
				{
					vmMainCustomer.message = response.data;
				});
			
			
			vmMainCustomer.purchaseCoupon = function(coupon)
			{
				customerClientSideWebService.purchaseCoupon($http,coupon).then (function (response)
					{
					vmMainCustomer.message =  coupon.title + " was purchased successfuly";	
					},
					function (response)
					{
						vmMainCustomer.message = response.data;
					});
			}
		$scope.purchaseCoupon = function(coupon)
		{
			customerClientSideWebService.purchaseCoupon($http,coupon).then (function (response)
				{
					$scope.message =  coupon.title + " was purchased successfuly";	
				},
				function (response)
				{
					$scope.message = response.data;
				});
		}
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
        	vmMainCustomer.getHumanReadableDate = function(date) {
        	    if (date instanceof Date) {
        	         return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        	    } else if (isFinite(date)) {
        	        var d = new Date();
        	        d.setTime(date);
        	        return this.getHumanReadableDate(d);
        	    }
        	}
	})
.directive('myLogoutDirective', function() {
	  return {
	    restrict: 'AE',
	    templateUrl: '../directiveLogout.html'
	  };
	});

