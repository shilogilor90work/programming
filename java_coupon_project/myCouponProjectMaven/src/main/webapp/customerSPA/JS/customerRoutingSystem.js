/// <reference path="JS/angular.js" />


var customerRouteModule = angular.module("CustomerAllModules", ["ui.router"]) 

/*
customerRouteModule.config(['$locationProvider', 
	function($locationProvider) 
	{
	    $locationProvider.hashPrefix('');
	}]);
*/

.config(function($stateProvider ,$urlMatcherFactoryProvider, $urlRouterProvider) {
	$urlMatcherFactoryProvider.caseInsensitive(true);
	$urlRouterProvider.otherwise("/resturants");
	$stateProvider
		.state("getAllCoupons", {
		url : "/getAllCoupons",
		templateUrl : "htmlRoute/getAllCoupons.html",
		controller : "getAllCouponsController",
		controllerAs : "getAllCouponsAsController"

	})
	.state("travel", {
		url : "/travel",
		templateUrl : "htmlRoute/travel.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("fashionAndSports", {
		url : "/fashionAndSports",
		templateUrl : "htmlRoute/sports.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("resturants", {
		url : "/resturants",
		templateUrl : "htmlRoute/resturants.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("healthAndBeauty", {
		url : "/healthAndBeauty",
		templateUrl : "htmlRoute/health.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("foodAndGroceries", {
		url : "/foodAndGroceries",
		templateUrl : "htmlRoute/food.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("electronics", {
		url : "/electronics",
		templateUrl : "htmlRoute/electricity.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	.state("camping", {
		url : "/camping",
		templateUrl : "htmlRoute/camping.html",
		controller : "getAllAndEveryCouponsController",
		controllerAs : "getAllAndEveryCouponsAsController"
	})
	
	
	});
