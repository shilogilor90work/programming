/// <reference path="JS/angular.js" />


var customerModule = angular.module('CustomerAllModules')
	.controller("getAllCouponsController",function ($scope,$http,customerClientSideWebService, $window)
	{
	//	var customerName = $window.
		vmMainPrivateCustomer = this;

			customerClientSideWebService.getAllCoupons($http).then (function (response)
				{
				vmMainPrivateCustomer.coupons = response.data;
				},
				function (response)
				{
					vmMainPrivateCustomer.message = response.data;
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
	})

