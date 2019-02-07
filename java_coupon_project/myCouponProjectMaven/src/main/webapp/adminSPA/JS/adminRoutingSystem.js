/// <reference path="angular.js" />


var adminRouteModule = angular.module("AdminAllModules", ["ui.router"]) 
	.config(function($stateProvider,$urlMatcherFactoryProvider, $urlRouterProvider) {
		
		$urlMatcherFactoryProvider.caseInsensitive(true);
		$urlRouterProvider.otherwise("/getAllCompanies");
		$stateProvider
		
		.state("getAllCompanies", {
			url : "/getAllCompanies",
			templateUrl : "htmlRoute/getAllCompanies.html",
			controller : "getAllCompaniesController",
			controllerAs : "getAllCompaniesAsController"
		})
		.state("getAllCustomers", {
			url : "/getAllCustomers",
			templateUrl : "htmlRoute/getAllCustomers.html",
			controller : "getAllCustomersController",
			controllerAs : "getAllCustomersAsController"
		})
	});
