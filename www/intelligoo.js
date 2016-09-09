/**
 * 
 */

cordova.define("cordova.plugin.intelligoo", function(require, exports, module){
	var exec = require("cordova/exec");
	var Intelligoo = function() {};
	Intelligoo.prototype.search = function(username, password) {
		console.log(username);
		console.log(password);
		exec(function(devList){
			console.log(devList);
		}, function() {
			console.log('err');
		}, "Intelligoo", "list", [username, password]);
	}
	Intelligoo.prototype.openDoor = function(success, failture, data) {
		console.log(data);
		exec(success, failture, "Intelligoo", "openDoor", [data]);
	}
	module.exports = new Intelligoo();
});