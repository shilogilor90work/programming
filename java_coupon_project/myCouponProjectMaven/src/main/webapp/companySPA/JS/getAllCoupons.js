/// <reference path="JS/angular.js" />



var companyModule = angular.module("CompanyAllModules")
.controller("getAllCouponsController",function ($scope,$http,companyClientSideWebService)
	{
		var vmcompany = this;
		
		vmcompany.logout = function ()
		{
			$http.get("http://localhost:8080/myCouponProjectMaven/LogoutServlet").then(function(response)
			{
				$window.location.href = "http://localhost:8080/myCouponProjectMaven/loginSystem.html"					
			},
			function(response)
			{
				
			});
		}

		companyClientSideWebService.printCompany($http).then (function (response)
				{
					vmcompany.company = response.data;
				},
				function (response)
				{
					$scope.logMessage = response.data;
				});
		
			companyClientSideWebService.getAllCoupons($http).then (function (response)
				{
				vmcompany.coupons = response.data;
				},
				function (response)
				{
					vmcompany.logMessage = response.data;
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
        	vmcompany.update=false;

        	vmcompany.updateFunction =function ()
   	{
				vmcompany.update=true;
   	}
			vmcompany.applyFunction =function (couponTitle, index)
   	{
				 var coupon = {
			        	    title : couponTitle,
			        	    endDate :  vmcompany.coupons[index].couponEndDateUpdate,		        	   
			        	    price :  vmcompany.coupons[index].couponPriceUpdate
			        }
   		companyClientSideWebService.updateCoupon($http,coupon).then(function(response)
        		{
       	    //		$state.reload();
   			vmcompany.logMessage =  coupon.title + " was updated successfuly at the database";
        		}, 
        		function(response)
        		{
        			vmcompany.logMessage = response.data;     			
        		});
   		$scope.update=false;

   	}
			vmcompany.cancelFunction =function ()
   	{
				vmcompany.update=false;
   	}
			vmcompany.removeFunction =function (coupon)
   	{
     	    companyClientSideWebService.removeCoupon($http,coupon).then(function(response)
       		{
     	    	vmcompany.logMessage =  coupon.title + " was deleted successfuly from the database";	
       		}, 
       		function(response)
       		{
       			vmcompany.logMessage = response.data;
       		});
   	}
	vmcompany.createFunction =function ()
   	{
		vmcompany.filesSelected = document.getElementById("inputFileToLoad").files;
	    if (vmcompany.filesSelected.length > 0)
	    {
	    	vmcompany.fileToLoad = vmcompany.filesSelected[0];
	        vmcompany.fileReader = new FileReader();
	        vmcompany.fileReader.onload = function(fileLoadedEvent) 
	        {
		        vmcompany.srcData = fileLoadedEvent.target.result; // <--- data: base64
		        vmcompany.newImage = document.createElement('img');
		        vmcompany.newImage.src = vmcompany.srcData;
		        vmcompany.baseImage = vmcompany.newImage.src;
		
		        var coupon = {        	
			 	    title : vmcompany.couponTitle,
			   	    startDate : vmcompany.couponStartDate,
			   	    endDate :  vmcompany.couponEndDate,
			   	    amount : vmcompany.couponAmount,
			   	    type : vmcompany.couponType,
			   	    message:  vmcompany.couponMessage,
			   	    price :  vmcompany.couponPrice,
			   	    image :vmcompany.baseImage
			     }				   
		         companyClientSideWebService.createCoupon($http,coupon).then(function(response)
		       	{
		        	 vmcompany.logMessage =  coupon.title + " was added successfuly to the database";	
		       	}, 
		       	function(response)
		       	{
		       		vmcompany.logMessage = response.data;     			
		       	});    
	   		}
	    }
	    vmcompany.fileReader.readAsDataURL(vmcompany.fileToLoad);
   	}
  })
				    
				    .directive('myLogoutDirective', function() {
  return {
    restrict: 'AE',
    templateUrl: '../directiveLogout.html'
  };
});