function createCompany()
{
//	var company = [];
//	company["id"] = 0;
//	company["name"] = document.getElementById(companyname).value;
//	company["password"] = document.getElementById(password).value;
//	company["email"] = document.getElementById(email).value;
//	var company = {id:0, name:document.getElementById("companyname").value , password:document.getElementById("password").value
//			email:document.getElementById("email").value};	
	var newName = document.getElementById("companyname").value;
	var newPassword = document.getElementById("password").value;
	var newEmail = document.getElementById("email").value;
	
	$ajax({ 
		url: "http://localhost:8080//myCouponProjectMaven/webapi/AdminFacade/createCompany",
		data:{name: newName, password: newPassword, email: newEmail}
//		success:
//		error // what to do when function fails
	});
}
function success()
{
	document.write("create company accomplished")
}
function error()
{
	document.write("create company failed")
}