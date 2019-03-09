var app = angular.module('app', []);

app.controller('appController', function($scope, $http) {

	$scope.selected = 'home';

	$scope.menu = function(menu) {
		$scope.selected = menu;
		$scope.loadEvents();
		$scope.loadEmployees();
	};

	$scope.loadEvents = function() {
		$http({
			method : 'GET',
			url : '/eventmgmt/event/list',
			data : null,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data, status, headers, config) {
			$scope.events = data;
		}).error(function(data, status, headers, config) {
			console.log("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};

	$scope.register = function() {

		var data = {
			"eventId" : $scope.eventSelect,
			"employeeId" : $scope.empSelect
		};

		$http({
			method : 'POST',
			url : '/eventmgmt/event/register',
			data : data,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data, status, headers, config) {
			$scope.message = "Registered Successfully";
			$scope.color = "green";
		}).error(function(data, status, headers, config) {
			console.log("failure message: " + JSON.stringify({
				data : data
			}));
			$scope.color = "red";
			$scope.message = "Registered Error";
		});
	};

	$scope.registedEmpl = function() {

		var data = {
			"eventId" : $scope.eventSelectReg
		};

		$http({
			method : 'POST',
			url : '/eventmgmt/event/registered',
			data : data,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data, status, headers, config) {
			$scope.employeesRegistered = data.employeeList;
		}).error(function(data, status, headers, config) {
			console.log("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};

	$scope.loadEmployees = function() {

		var headers = null

		$http({
			method : 'GET',
			url : '/eventmgmt/employee/list',
			data : null,
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data, status, headers, config) {
			$scope.employees = data;
		}).error(function(data, status, headers, config) {
			console.log("failure message: " + JSON.stringify({
				data : data
			}));
		});
	};
});