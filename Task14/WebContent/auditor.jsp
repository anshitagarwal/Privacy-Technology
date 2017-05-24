<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Giant Eagle | Auditor Page </title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
	<div class="form">
	<div class="tab-content" style="text-align: -webkit-center;">
	<form action="#">
		<h1>Auditor's Credentials</h1>
				<div class="top-row">
					<div class="field-wrap">
						<label> First Name<span class="req">*</span>
						</label> <input name="firstname" type="text" required autocomplete="off" />
					</div>
					<div class="field-wrap">
						<label> Last Name<span class="req">*</span>
						</label> <input name="lastname" type="text" required autocomplete="off" />
					</div>
				</div>
				<div class="field-wrap">
            <label>
              Purpose of Downloading Information
            </label>
            <textarea rows="4" cols="50"></textarea>
          </div>
		<h5>Check out our <a href="https://drive.google.com/file/d/0B8B5IJp1AaerbWxxa3pYc2dRcDg/view?usp=sharing" target="_blank">Privacy Policy.</a></h5>
		<h1 style="font-size: 26px;">Data Information</h1>
		
		<!-- add -->
		<h5>
			<table style="width: 100%" border="1" align="center">
					<tr>
						<th style="background: #00ccff;    width: 20%;" >Column Name</th>
						<th style="background: #00ccff;    width: 40%;">Sharing Status for Opt-In users</th>
						<th style="background: #00ccff;    width: 40%;">Sharing Status for Opt-Out users</th>
					</tr>
					<tr>
						<td align="center" valign="middle" >disease_name</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">disease_treats</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">chemical_name</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">marketing_name</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">prescription_probability</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Limited Shared - The information has been rounded so that no reverse identification is possible.</td>
					</tr>
					<tr>
						<td align="center" valign="middle">insurance_health_id</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">plan_number</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">firstname</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">lastname</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">gender</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">dob</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Limited Shared - Only the age group of the person is shared.</td>
					</tr>
					<tr>
						<td align="center" valign="middle">address</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">zip</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">ethnicity</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">ssn</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">city</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">state</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">grocery_member_id</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">credit_card</td>
						<td align="center" valign="middle">Not Shared - Security Concerns</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					<tr>
						<td align="center" valign="middle">ad_keywords</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">coupon_code</td>
						<td align="center" valign="middle">Shared</td>
						<td align="center" valign="middle">Shared</td>
					</tr>
					<tr>
						<td align="center" valign="middle">id</td>
						<td align="center" valign="middle">Not Shared - Internal Addressing</td>
						<td align="center" valign="middle">Not Shared - Internal Addressing</td>
					</tr>
					<tr>
						<td align="center" valign="middle">data_sharing</td>
						<td align="center" valign="middle">Not Shared - Internal Addressing</td>
						<td align="center" valign="middle">Not Shared - Internal Addressing</td>
					</tr>
					<tr>
						<td align="center" valign="middle">email</td>
						<td align="center" valign="middle">Shared - If Available</td>
						<td align="center" valign="middle">Not Shared - Identifiable</td>
					</tr>
					
					
				</table>
			</h5>	
				
				<!-- add -->
		
		<ul class="tab-group">
		<button name="act" type="submit" class="button button-block" value="consent" style="cursor: pointer; margin: 10px; font-size: 20px; text-transform: none;"/>Data of user's who Opted-In</button>
		<button name="act" type="submit" class="button button-block" value="noconsent" style="cursor: pointer; margin: 10px; font-size: 20px; text-transform: none;"/>Data of user's who Opted-Out</button>
		<button name="act" type="submit" class="button button-block" value="historydata" style="cursor: pointer; margin: 10px; font-size: 20px; text-transform: none;" onclick="showAlert2()"/>History Data</button>
		</ul>
			</form>
	
	</div>
	</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>
	function showAlert2() {
	    alert("The requested page is under construction.");
	}
</script>

<script src="js/index.js"></script>

</body>

</html>