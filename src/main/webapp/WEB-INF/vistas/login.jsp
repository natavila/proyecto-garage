<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
	  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
	    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
	  </symbol>
	  <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
	    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
	  </symbol>
	  <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
	    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
	  </symbol>
	</svg>
	<!-- Bootstrap core CSS -->
	    <!--<link href="css/bootstrap.min.css" rel="stylesheet" >-->
	    <!-- Bootstrap theme -->
	    <!--<link href="css/bootstrap-theme.min.css" rel="stylesheet">-->
	    <meta charset="ISO-8859-1">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	    <link href="css/estilos.css" rel="stylesheet" type="text/css">
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container">
		    <a class="navbar-brand" href="#">Garage</a>
		    <div class="btn-group" role="group" aria-label="Basic example">
			  <button type="button" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#exampleModal">Iniciar sesi�n</button>
			  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header text-light bg-dark">

				        <h5 class="modal-title" id="exampleModalLabel">Iniciar sesion</h5>

				       

				        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <div class = "container-fluid">
			<div id="loginbox" style="margin-top:10px;" class="mainbox col-md-8 col-md-offset-3 col-sm-8 col-sm-offset-4 ">
				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
					<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<div class="form-floating mb-3"> 
				<div class="row">
				<div class="col">
				<h5 class="mb-3">Iniciar sesion en Garage</h5>
				<form:form action="validar-login" method="POST" modelAttribute="cliente">
				
				  <div class="form-floating mb-3">
				    <form:input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" path="email"/>
  					<label for="floatingInput">Correo electronico</label>
				  </div>

				  <div class="form-floating">
					  <form:input type="password" class="form-control" id="floatingPassword" placeholder="Contrase�a" path="password"/>
					  <label for="floatingPassword">Contrase�a</label>
					</div>
				  <button type="submit" class="btn btn-outline-dark mt-3">Iniciar sesi�n</button>

				</form:form>			

				</form:form>
				
				
				</div>
				<div class="col mb-3">
					<svg id="b69449d1-5d1b-47ef-955d-97dc06911a02" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="400" height="200" viewBox="0 0 602 535.28379"><path d="M386.51124,612.2258l-18.30162-11.659a273.13465,273.13465,0,0,1-.78666-30.77636l9.49369,2.42554-9.37047-5.96941c.51751-12.68956,1.6637-21.06011,1.6637-21.06011s37.0035,24.0357,60.60336,56.615L425.78893,629.355l9.5974-19.269a110.5054,110.5054,0,0,1,6.60266,12.21685c19.44421,42.47823,21.33357,83.26415,4.22,91.09776s-46.74947-20.2514-66.19366-62.72969c-6.02791-13.16868-9.35521-28.30973-11.07932-42.93537Z" transform="translate(-299 -182.3581)" fill="#f0f0f0"/><path d="M348.91768,653.2272l-21.69358-.52051a273.14027,273.14027,0,0,1-16.58178-25.93933l9.38079-2.831-11.10711-.2665c-6.11625-11.13035-9.4618-18.88836-9.4618-18.88836s44.10074,1.44846,81.14355,17.139l10.79752,25.66731-1.7444-21.45606a110.5067,110.5067,0,0,1,11.96707,7.04529c38.60219,26.31258,61.30188,60.25048,50.70108,75.80244s-50.48761,6.82874-89.08981-19.48381c-11.96706-8.15716-22.64181-19.39868-31.67775-31.0277Z" transform="translate(-299 -182.3581)" fill="#f0f0f0"/><path d="M658.10181,332.61314l-17-2-6,52L619.783,454.42107a10.99719,10.99719,0,1,0,11.93262,1.50476l15.38623-42.31269Z" transform="translate(-299 -182.3581)" fill="#ffb6b6"/><polygon points="383.797 520.968 369.791 520.968 363.127 466.943 383.799 466.944 383.797 520.968" fill="#ffb6b6"/><path d="M686.36886,716.90332l-45.16217-.00167v-.57123a17.57932,17.57932,0,0,1,17.57838-17.5781h.00111l27.58351.00112Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><polygon points="491.287 513.329 477.535 515.984 460.747 464.204 481.044 460.285 491.287 513.329" fill="#ffb6b6"/><path d="M796.36936,708.34005l-44.34288,8.56327-.10833-.56086a17.57931,17.57931,0,0,1,13.92571-20.59279l.00109-.00022,27.08314-5.23006Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><path d="M660.83551,247.3967l8.13824,15.61361,60.95076-13.44341-6.67694-30.27241c-5.285-23.96168-26.47247-41.26679-50.43367-35.98186s-35.14214,28.70523-29.85711,52.66691l9.82192,30.60278,9.9376-2.19185Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><path d="M639.10188,419.61315s-6,30,2,74,10,63,10,63-6,7-2,11,0,12,0,12l9,97,31-5-4-91s4-8,2-10,2-14,2-14l5-95,31,104s-3,4,0,7,9,18,9,18l20,70,32-6-18-80s2-5-1-8a7.83339,7.83339,0,0,1-2-7s3-3-1-7-6-20-6-20-3-107-13-113S639.10188,419.61315,639.10188,419.61315Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><circle cx="385.31831" cy="43.79395" r="28" fill="#ffb6b6"/><path d="M723.77814,261.99379s20.32374-2.38064,26.32374,3.61936l-6,121s4,3,2,10a28.5687,28.5687,0,0,0,0,14s6,7,3,10-111,1-111,1-4-7,2-11,3-15,3-15-2-3,3-8,9-46,9-46l-13-50S655.188,276.84261,670.145,275.72792Z" transform="translate(-299 -182.3581)" fill="#e6e6e6"/><path d="M657.59449,292.30131l-15.49261-.68816s-9,18-10,34l-5.50739,59.68816,35.50739,5.31184Z" transform="translate(-299 -182.3581)" fill="#e6e6e6"/><path d="M660.32984,227.54057l-10.40161-13.38183a10.91076,10.91076,0,0,1,8.28222-12.96924l39.29224-8.66651a10.91174,10.91174,0,0,1,12.97,8.28223l.02149.09668-2.81372,16.15918-.49414-.08838c-24.38477-4.34766-31.33106-2.81836-46.45923,10.22559Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><path d="M764.97657,441.35338l-1.87476-78.74024-2-56-24,3,12.88892,133.84577a11,11,0,1,0,14.98584-2.10553Z" transform="translate(-299 -182.3581)" fill="#ffb6b6"/><path d="M732.59449,267.30131l17.50739-1.68816s3,0,6,10,19.49261,115.68816,19.49261,115.68816l-36.49261,3.31184Z" transform="translate(-299 -182.3581)" fill="#e6e6e6"/><path d="M623.82249,392.24245h-2.3399V328.14168a37.09977,37.09977,0,0,0-37.09974-37.09982H448.57661a37.09977,37.09977,0,0,0-37.09985,37.0997V679.8035a37.09979,37.09979,0,0,0,37.09975,37.09982H584.38267a37.0998,37.0998,0,0,0,37.09989-37.09967V437.87048h2.33993Z" transform="translate(-299 -182.3581)" fill="#3f3d56"/><path d="M585.87969,300.69395h-17.7272a13.16291,13.16291,0,0,1-12.18706,18.13419H478.1638a13.16291,13.16291,0,0,1-12.18707-18.13422H449.41948a27.70571,27.70571,0,0,0-27.70572,27.70567V679.54553a27.70571,27.70571,0,0,0,27.70569,27.70573H585.87969a27.70571,27.70571,0,0,0,27.70573-27.70569h0V328.39962A27.70568,27.70568,0,0,0,585.87969,300.69395Z" transform="translate(-299 -182.3581)" fill="#fff"/><path d="M495.74379,428.30825l3.7733,13.07091,48.2194-.85068-.4225-23.94912c-.33443-18.95658-13.73167-35.30622-32.68787-34.9718s-31.00948,16.36026-30.67506,35.31684l2.7538,24.68079,7.86184-.1387Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><circle cx="217.78089" cy="233.4553" r="21.63483" fill="#ffb6b6"/><path d="M559.84449,449.55193l-.49024,9.1a57.9288,57.9288,0,0,1-80.28955,3.11c-.5-.44-.99023-.9-1.47021-1.37,3-2.98,11.28027-10.34,20.47021-11.02l41.43994-2.61005S555.20435,444.92193,559.84449,449.55193Z" transform="translate(-299 -182.3581)" fill="#e4e4e4"/><path d="M540.46461,400.62194a8.39116,8.39116,0,0,0-1.49024-4.63c-.09961-.14-.19971-.27-.2998-.4a8.43088,8.43088,0,0,0-6.76026-3.23l-15.5498.28-15.54.27a8.41955,8.41955,0,0,0-8.25,8.55l3.70019,7.45,2.12989,4.28.35009-.2c13.44971-7.56,18.94971-7.66,36.75-.63l.35987.14,1.41015-3.61,3.19971-8.19Z" transform="translate(-299 -182.3581)" fill="#2f2e41"/><path d="M563.34449,454.06194a58.61855,58.61855,0,0,1-16.06006,14.18l-.96973-17.38,13.52979-1.31S561.46461,449.55193,563.34449,454.06194Z" transform="translate(-299 -182.3581)" fill="#e4e4e4"/><path d="M517.59449,477.41192a58.81825,58.81825,0,0,1-39.19385-14.90235c-.50977-.44824-1.01318-.92041-1.50586-1.40332a58.39859,58.39859,0,0,1-18.30029-42.69433,59,59,0,0,1,118,0,58.36548,58.36548,0,0,1-12.46192,36.26562,58.95633,58.95633,0,0,1-46.53808,22.73438Zm0-116a57.06468,57.06468,0,0,0-57,57,56.42575,56.42575,0,0,0,17.68921,41.25537c.47778.46777.95483.91552,1.44165,1.34375a57.06813,57.06813,0,0,0,82.83105-7.56494,56.38,56.38,0,0,0,12.03809-35.03418A57.06468,57.06468,0,0,0,517.59449,361.41192Z" transform="translate(-299 -182.3581)" fill="#3f3d56"/><path d="M570.634,559.20946H466.69555a7.89209,7.89209,0,1,1,0-15.78418H570.634a7.89209,7.89209,0,1,1,0,15.78418Z" transform="translate(-299 -182.3581)" fill="#6c63ff"/><path d="M570.634,584.20946H466.69555a7.89209,7.89209,0,1,1,0-15.78418H570.634a7.89209,7.89209,0,1,1,0,15.78418Z" transform="translate(-299 -182.3581)" fill="#6c63ff"/><path d="M545.3313,533.90575h-53.332a7.89209,7.89209,0,1,1,0-15.78418h53.332a7.89209,7.89209,0,0,1,0,15.78418Z" transform="translate(-299 -182.3581)" fill="#6c63ff"/><path d="M900,717.6419H300a1,1,0,0,1,0-2H900a1,1,0,0,1,0,2Z" transform="translate(-299 -182.3581)" fill="#cacaca"/></svg>

				</div>
				</div>
				</div>
				<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
				<c:if test="${not empty Error}">
				
				    <h4>${Error}</h4>
				 				        	        
		        </c:if>

		        <p>�Todavia no tenes cuenta? <a href="mostrarRegistro">Registrate</a></p>

		        <a href="/mostrarOlvido">�Olvidaste tu contrase�a?</a>

			</div>
		</div>
				      </div>
				      <div class="modal-footer bg-dark">
				      </div>
				    </div>
				  </div>
				</div>
			  <a type="button" class="btn btn btn-light" href="mostrarRegistro">Registrarse</a>
			  
			</div>
		      
		    </div>
		  
		</nav>
		<div class="container">
		<div class="grid-log">
		<div class="slogan">

		 <h1 >Inicia sesi�n para<br>

		 acceder a tu cuenta</h1>
		 </div>
		 <div class="image-log">
		 <svg id="f5797293-4767-4b98-be82-dd8b3477ccfb" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" width="700.32531" height="600.02695" viewBox="0 0 1077.32531 784.02695"><path d="M1120.51592,609.15349c-18.84034,38.87-51.46045,68.61-88.78028,91.24a392.45588,392.45588,0,0,1-47.64013,24.39q-16.10962,6.975-32.65967,12.73c-54.73,19.11-112.64014,28.45-170.65039,28.48q-14.22.015-28.43994-.73-4.0796-.21-8.15967-.48-21.49512-1.425-42.8501-4.57c-.28027-.04-.56982-.08-.8501-.13a539.77422,539.77422,0,0,1-87.77-20.57q-8.3247-2.7-16.5498-5.67c-1.48-.52-2.96-1.07-4.43018-1.61005q-9.27026-3.435-18.3999-7.21-7.68018-3.165-15.27-6.57-7.47-3.375-14.83984-6.97-12.06006-5.90991-23.86035-12.38995-9.10475-5.04008-17.96973-10.46a496.62043,496.62043,0,0,1-42.49024-29.08q-7.22973-5.52-14.25976-11.32c-3.25-2.68-6.47022-5.42-9.74024-8.07995-6.8999-5.61-14-10.94-21.91992-14.93a78.62631,78.62631,0,0,0-7.33984-3.24006,91.90524,91.90524,0,0,0-29.15039-5.67c-16.98975-.67-34.52979,2.49-50.96,7.17-7.80957,2.21-15.52979,4.75-23.25,7.35q-4.75488,1.605-9.5,3.22c-6.54,2.23-13.07959,4.41-19.67969,6.4-2.2002.66-4.3999,1.31-6.61035,1.92a194.22735,194.22735,0,0,1-26.90967,5.72,143.37367,143.37367,0,0,1-60.49023-5.27l-1.66993-.52c-2.7998-.88-5.56982-1.84-8.33007-2.88995l-1.66993-.64q-3.98949-1.545-7.83007-3.31l-1.66993-.76c-2.46-1.15-4.87011-2.35-7.23974-3.62-.2002-.1-.39014-.2-.59033-.31-.91993-.48-1.82959-1-2.73-1.49a164.61837,164.61837,0,0,1-39.15967-30.49c-.33008-.33-.64014-.67-1-1-1.59033-1.72-3.15039-3.47-4.66016-5.26-.56006-.66-1.12012-1.32-1.66992-2q-2.68506-3.285-5.18994-6.71c-.25-.33-.49024-.67-.73-1a145.03046,145.03046,0,0,1-16.87012-30.5c-.14014-.33-.27-.67-.39014-1q-1.9951-5.145-3.6499-10.47c-.45019-1.48-.87988-3-1.26025-4.48-.12989-.44-.25-.89-.35987-1.33-5.46972-21.72-6.06-44.31006-6.06-66.77v-7.85c.16015-36.33.72021-73.28,6.08007-109.11a6.03567,6.03567,0,0,1,.10987-.74,344.02421,344.02421,0,0,1,7.31006-35.82,265.59868,265.59868,0,0,1,12.31-36.3c12.33008-29,30.26026-55.39,50.16993-79.85,50.10009-61.51,115.87011-113.22,192.85009-132.51,82.9502-20.83,175.14014-.87,242.04981,52.4,19.73047,15.7,37.52051,34.2,59.39013,46.66q1.11036.645,2.25,1.26,4.33521,2.35491,8.85987,4.28a91.427,91.427,0,0,0,12.31006,4.25c13.29,3.57,27.18017,3.89,40.43017-.54,1.08008-.37,2.16016-.76,3.23-1.19,2.27-.9,4.43994-1.74,6.52979-2.52,14.26025-5.31,25.24023-7.89,42.57031-6.96a411.34574,411.34574,0,0,1,120.35986,25.19c1.56006.57,3.12012,1.16,4.66993,1.75,100.71,38.55005,187.30029,115.1,232.28027,213.47q1.79956,3.9,3.46973,7.86C1140.42558,474.62346,1150.10576,548.09349,1120.51592,609.15349Z" transform="translate(-61.33735 -57.98653)" fill="#f2f2f2" style="isolation:isolate"/><polygon points="63.608 512.617 63.608 513.017 63.458 513.197 63.608 512.617" fill="#cacaca"/><path d="M302.28545,630.83348q-4.75488,1.605-9.5,3.22v-3.22Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><rect x="575.29648" y="58.76762" width="23.42189" height="58.55474" fill="#e4e4e4"/><path d="M700.48564,170.69347v589.39a539.77422,539.77422,0,0,1-87.77-20.57q-8.3247-2.7-16.5498-5.67c-1.48-.52-2.96-1.07-4.43018-1.61005q-9.27026-3.435-18.3999-7.21-7.68018-3.165-15.27-6.57-7.47-3.375-14.83984-6.97-12.06006-5.90991-23.86035-12.38995-9.10475-5.04008-17.96973-10.46v-517.94Z" transform="translate(-61.33735 -57.98653)" fill="#e4e4e4"/><path d="M780.78545,170.69347v595.3q-14.22.015-28.43994-.73-4.0796-.21-8.15967-.48-21.49512-1.425-42.8501-4.57c-.28027-.04-.56982-.08-.8501-.13v-589.39Z" transform="translate(-61.33735 -57.98653)" fill="#cacaca"/><rect x="476.02902" y="171.25989" width="30.11386" height="30.11386" fill="#fff"/><rect x="524.5458" y="171.25989" width="30.11386" height="30.11386" fill="#fff"/><rect x="573.06259" y="171.25989" width="30.11386" height="30.11386" fill="#fff"/><rect x="476.86552" y="239.85258" width="30.11386" height="30.11386" fill="#fff"/><rect x="525.3823" y="239.85258" width="30.11386" height="30.11386" fill="#fff"/><rect x="573.89908" y="239.85258" width="30.11386" height="30.11386" fill="#fff"/><rect x="477.70202" y="308.44528" width="30.11386" height="30.11386" fill="#fff"/><rect x="526.2188" y="308.44528" width="30.11386" height="30.11386" fill="#fff"/><rect x="574.73558" y="308.44528" width="30.11386" height="30.11386" fill="#fff"/><rect x="478.53851" y="377.03797" width="30.11386" height="30.11386" fill="#fff"/><rect x="527.05529" y="377.03797" width="30.11386" height="30.11386" fill="#fff"/><rect x="575.57207" y="377.03797" width="30.11386" height="30.11386" fill="#fff"/><rect x="479.37501" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="527.89179" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="576.40857" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="480.2115" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="528.72829" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="577.24507" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="481.048" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><rect x="529.56478" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><rect x="578.08156" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><path d="M573.33574,709.39348v15.63q-7.68018-3.165-15.27-6.57-7.47-3.375-14.83984-6.97v-2.09Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M621.85576,709.39348v30.12h-9.14014q-8.3247-2.7-16.5498-5.67c-1.48-.52-2.96-1.07-4.43018-1.61005v-22.84Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><rect x="578.91806" y="651.40873" width="30.11386" height="30.11386" fill="#fff"/><rect x="848.26985" y="356.96206" width="23.42189" height="34.70408" fill="#e4e4e4"/><path d="M951.43584,446.67345v290.84c-54.73,19.11-112.64014,28.45-170.65039,28.48q-14.22.015-28.43994-.73v-318.59Z" transform="translate(-61.33735 -57.98653)" fill="#e4e4e4"/><path d="M1031.73564,446.67345v253.72a392.45588,392.45588,0,0,1-47.64013,24.39q-16.10962,6.975-32.65967,12.73v-290.84Z" transform="translate(-61.33735 -57.98653)" fill="#cacaca"/><rect x="730.32388" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="778.84066" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="827.35744" y="445.63066" width="30.11386" height="30.11386" fill="#fff"/><rect x="731.16037" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="779.67715" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="828.19394" y="514.22335" width="30.11386" height="30.11386" fill="#fff"/><rect x="731.99687" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><rect x="780.51365" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><rect x="829.03043" y="582.81604" width="30.11386" height="30.11386" fill="#fff"/><rect x="732.83337" y="651.40873" width="30.11386" height="30.11386" fill="#fff"/><rect x="781.35015" y="651.40873" width="30.11386" height="30.11386" fill="#fff"/><rect x="829.86693" y="651.40873" width="30.11386" height="30.11386" fill="#fff"/><path d="M775.91157,775.167s10.71245,38.099,55.57767,36.36726,64.26109-37.06,64.26109-37.06Z" transform="translate(-61.33735 -57.98653)" fill="#3f3d56"/><polygon points="96.331 568.594 83.513 592.07 85.594 636.133 392.751 727.401 526.504 743.85 500.355 584.338 408.852 568.136 271.586 572.058 155.211 550.584 96.331 568.594" fill="#2f2e41"/><path d="M200.42647,781.055h-7.92912A40.14406,40.14406,0,0,1,155.973,757.88037c-12.22389-26.68555-8.60289-63.58655-8.54928-64.11284,2.77-31.89509,20.25026-52.70411,43.50068-51.75976,25.66746,1.035,47.55461,89.81414,48.47377,93.59292a33.0874,33.0874,0,0,1-1.15288,19.199A40.01006,40.01006,0,0,1,200.42647,781.055Z" transform="translate(-61.33735 -57.98653)" fill="#3f3d56"/><path d="M523.76526,842.01347a27.92868,27.92868,0,0,1-4.04683-.391,62.70016,62.70016,0,0,1-47.34728-36.04322c-3.21495-6.99136-6.30779-14.14981-9.19261-21.27715-11.58462-28.62095-1.87316-78.65433,13.90662-98.02044,13.92725-17.09315,27.94481-16.95786,49.1653-16.75762,2.17859.021,4.43547.04194,6.77827.046,23.56092.03856,48.46938,86.70979,56.65455,115.19071.65145,2.26619,1.19026,4.14138,1.607,5.54777,1.28242,4.32808,1.13,9.50446-.45307,15.38438-4.99441,18.55095-21.45371,32.30908-40.957,34.23636C535.872,841.312,527.08557,842.01347,523.76526,842.01347Z" transform="translate(-61.33735 -57.98653)" fill="#3f3d56"/><path d="M146.93123,694.12s-9.00523-40.17716,4.50261-67.53919l13.6142-32.90371,51.446,47.797,11.74746,74.79042L429.55675,746.7659S417.088,624.1563,495.36417,619.30733s115.418,127.45857,115.418,127.45857L649.83842,775.167,922.0733,755.07842s4.80582,13.16148-2.0997,20.08857-337.328,28.4011-337.328,28.4011-16.625-164.17218-82.43244-148.93257S454.088,785.3877,454.088,785.3877L218.973,743.99506s-7.61981-111.52625-46.41155-111.52625S146.93123,694.12,146.93123,694.12Z" transform="translate(-61.33735 -57.98653)" fill="#3f3d56"/><polygon points="131.986 496.458 176.339 426.935 365.449 415.852 575.34 418.623 673.012 509.368 370.99 526.685 127.849 513.524 131.986 496.458" fill="#3f3d56"/><path d="M193.32368,554.44478s-37.73358.78691-41.88984,72.136c0,0,40.17716-72.73451,74.81264,89.35954L433.0203,749.53674S425.4005,614.45836,503.67668,624.849,600.656,759.23467,600.656,759.23467s15.23961,30.47923,99.05748,25.63026,209.19831-12.46877,222.35979-29.78651c0,0,30.47922-103.21374-64.422-169.71386L734.349,567.35411l-279.162,5.54167Z" transform="translate(-61.33735 -57.98653)" fill="#6c63ff"/><path d="M287.89764,603.02865H264.34551a4.849,4.849,0,1,1,0-9.69793h23.55213a4.849,4.849,0,0,1,0,9.69793Z" transform="translate(-61.33735 -57.98653)" fill="#cacaca"/><circle cx="585.03752" cy="680.46686" r="15.23961" fill="#fff"/><path d="M638.82474,663.67524c-10.43377,0-22.8902-4.4539-34.03985-13.04242-19.67407-15.1564-27.93974-37.53485-18.42546-49.88524,9.51427-12.35107,33.26122-10.068,52.93512,5.08709,9.50785,7.32486,16.66646,16.53032,20.15707,25.92046,3.51124,9.44629,2.89633,17.957-1.73177,23.9641C653.59758,661.07082,646.80206,663.67524,638.82474,663.67524Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M909.637,644.32538c-7.67629,0-21.46419-7.77269-35.11578-20.38623-8.18263-7.561-14.96158-15.66512-19.08808-22.82018-4.19143-7.268-5.14627-12.73531-2.6883-15.39521s7.98375-2.13968,15.55992,1.46457c7.45847,3.54879,16.07235,9.66682,24.25532,17.22777,17.2687,15.956,26.834,32.74271,21.77672,38.21606A6.13338,6.13338,0,0,1,909.637,644.32538Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M265.86284,554.4304q-.24708,0-.4967-.023l-50.0892-4.65718a5.38828,5.38828,0,0,1-4.08-8.2053l28.2455-45.53855a5.3846,5.3846,0,0,1,4.34127-2.54287l37.35914-1.64823a5.38824,5.38824,0,0,1,5.39928,6.92811L271.02687,550.587A5.40676,5.40676,0,0,1,265.86284,554.4304Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M397.85616,565.10009q-.741,0-1.49281-.073l-86.59429-8.5246a15.11775,15.11775,0,0,1-13.105-19.02042l12.45745-45.71a15.04774,15.04774,0,0,1,14.05865-11.13375l69.88537-2.44039a15.20219,15.20219,0,0,1,15.545,13.36781l5.76661,49.74724a15.08667,15.08667,0,0,1-.24844,4.97175l-1.51547,6.92743A15.12347,15.12347,0,0,1,397.85616,565.10009Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M465.43915,567.50564a11.26779,11.26779,0,0,1-10.93335-8.692l-15.27648-66.03031a11.22061,11.22061,0,0,1,10.93182-13.74968h181.153a11.28058,11.28058,0,0,1,8.05444,3.40841l59.16968,61.0058a11.22086,11.22086,0,0,1-7.8038,19.03024l-225.04641,5.02485C465.60505,567.505,465.52168,567.50564,465.43915,567.50564Z" transform="translate(-61.33735 -57.98653)" fill="#fff"/><path d="M236.12986,487.34554l1.54633-2.42388s9.00522-24.93755,133.00025-29.0938,239.67753-2.07813,266.00049,20.78129l-214.04727-.69271L241.83244,488.38521Z" transform="translate(-61.33735 -57.98653)" fill="#6c63ff"/><path d="M177.41045,665.02616c-13.00759,0-23.55213,21.70954-23.55213,48.48968,0,26.78009,10.54454,48.48967,23.55213,48.48967s23.55213-21.70958,23.55213-48.48967C200.96258,686.7357,190.418,665.02616,177.41045,665.02616Zm1.03906,10.39065c2.86927,0,5.19533,5.58243,5.19533,12.46877s-2.32606,12.46877-5.19533,12.46877-5.19532-5.58247-5.19532-12.46877S175.58025,675.41681,178.44951,675.41681Zm3.1172,38.099c0,2.29544-1.55082,4.15626-3.46355,4.15626s-3.46355-1.86082-3.46355-4.15626,1.55082-4.15626,3.46355-4.15626S181.56671,711.22039,181.56671,713.51584Zm-18.097,2.66755c-4.45425.34407-8.18094-.88145-8.32436-2.73727s3.35092-3.63922,7.80516-3.98329c4.45391-.34407,8.18061.88144,8.324,2.73731S167.92357,715.83932,163.46967,716.18339Zm14.97984,35.43148c-2.86926,0-5.19532-5.58248-5.19532-12.46878s2.32606-12.46877,5.19532-12.46877,5.19533,5.58244,5.19533,12.46877S181.31878,751.61487,178.44951,751.61487Zm13.42125-35.43148c-4.45424.34407-8.18094-.88145-8.32435-2.73727s3.35091-3.63922,7.80516-3.98329c4.45391-.34407,8.1806.88144,8.324,2.73731S196.32467,715.83932,191.87076,716.18339Z" transform="translate(-61.33735 -57.98653)" fill="#e4e4e4"/><path d="M506.44752,697.58352c-20.65884,0-37.40632,27.75721-37.40632,61.99751,0,34.24025,16.74748,61.99751,37.40632,61.99751s37.40632-27.75726,37.40632-61.99751C543.85384,725.34073,527.10637,697.58352,506.44752,697.58352Zm-22.34715,66.64992c-6.03314.466-11.0815-1.194-11.27565-3.7078s4.53915-4.92947,10.57228-5.39552,11.08149,1.194,11.27564,3.70776C494.867,761.3517,490.1335,763.76735,484.10037,764.23344Zm22.36914,47.30081c-3.88652,0-7.03719-7.56171-7.03719-16.88953s3.15067-16.88953,7.03719-16.88953c3.88668,0,7.03736,7.56171,7.03736,16.88953S510.35619,811.53425,506.46951,811.53425Zm-5.56366-51.60687c0-3.6727,2.48113-6.65,5.54167-6.65s5.54168,2.97733,5.54168,6.65-2.48114,6.65-5.54168,6.65S500.90585,763.60009,500.90585,759.92738Zm5.56366-17.82784c-3.88652,0-7.03719-7.56167-7.03719-16.88953s3.15067-16.88949,7.03719-16.88949c3.88668,0,7.03736,7.56167,7.03736,16.88949S510.35619,742.09954,506.46951,742.09954ZM530.19075,762.848c-6.03313.466-11.08149-1.194-11.27564-3.7078s4.53931-4.92947,10.57245-5.39552,11.08132,1.194,11.27547,3.70776S536.22389,762.38193,530.19075,762.848Z" transform="translate(-61.33735 -57.98653)" fill="#e4e4e4"/><path d="M375.5254,573.58849l8.31252,19.39587s13.85419,5.54168,22.85942-13.16148l-18.24772-18.24772Z" transform="translate(-61.33735 -57.98653)" fill="#cacaca"/><path d="M364.09569,578.09111c-14.89647,0-27.01567-8.07914-27.01567-18.01045,0-9.931,12.1192-18.01046,27.01567-18.01046s27.01568,8.07948,27.01568,18.01046C391.11137,570.012,378.99216,578.09111,364.09569,578.09111Z" transform="translate(-61.33735 -57.98653)" fill="#cacaca"/><path d="M885.01631,641.474l-154.877,4.52735s-33.89425,9.50744-37.35285,36.67155c0,0,4.842,22.184,17.293,22.184s203.36548-9.96018,203.36548-9.96018,12.451-19.01488,2.07516-32.59693S885.01631,641.474,885.01631,641.474Z" transform="translate(-61.33735 -57.98653)" fill="#3f3d56"/><rect x="716.78358" y="653.13081" width="181.72483" height="2.07791" transform="translate(-82.51257 -30.94157) rotate(-1.89362)" fill="#e4e4e4"/><rect x="707.44118" y="661.18712" width="197.73806" height="2.07755" transform="translate(-90.48339 -20.81434) rotate(-2.59409)" fill="#e4e4e4"/><rect x="700.01133" y="671.3426" width="212.14189" height="2.07842" transform="translate(-96.23224 -13.79524) rotate(-3.0738)" fill="#e4e4e4"/><g id="fa69add6-ae6a-462f-9f4a-a8964149f7d7" data-name="Group 142"><path id="f4e602e9-d2b5-4192-9853-189e5cc927ef" data-name="Path 2185" d="M176.30958,619.619a9.42193,9.42193,0,0,1,3.0431-14.123L177.385,584.05474l12.73787-4.37341,2.35384,30.31841a9.47291,9.47291,0,0,1-16.169,9.6202Z" transform="translate(-61.33735 -57.98653)" fill="#ffb8b8"/><path id="e2d38235-adb3-41cf-b4a9-63352e01515a" data-name="Path 2186" d="M189.98407,496.558s8.74117-18.34532,16.50892-11.89662-10.64934,60.69632-10.64934,60.69632l-2.80043,52.66236L178.281,596.54432l-4.17419-51.30272Z" transform="translate(-61.33735 -57.98653)" fill="#e6e6e6"/><path id="eea0d059-1974-4b26-aac7-8a2396c42a8e" data-name="Path 2187" d="M72.73188,767.96592,83.69,776.83644l39.42971-38.05026L106.94691,725.698Z" transform="translate(-61.33735 -57.98653)" fill="#ffb8b8"/><path id="a9703c69-ade6-4e5d-bca9-6a3d314fecef" data-name="Path 2188" d="M72.83173,762.12528l21.581,17.46725h0a17.69478,17.69478,0,0,1,2.61914,24.88562l-.36161.44662L61.33735,776.32566Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/><path id="fb949616-149f-4f99-b6d4-35f2ecdeae65" data-name="Path 2189" d="M225.02832,792.59h14.09843l6.70365-54.38451H225.02549Z" transform="translate(-61.33735 -57.98653)" fill="#ffb8b8"/><path id="b920e865-38ed-4b06-94ca-a4364bcce1a4" data-name="Path 2190" d="M221.43078,787.98718h27.7663a17.69479,17.69479,0,0,1,17.69479,17.69292v.57593H221.4317Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/><path id="e64f2ddc-8714-4699-9f4e-cfc1a70d06b3" data-name="Path 2191" d="M249.76644,537.64662S276.21658,569.843,270.46656,602.047s-10.35006,56.35123-10.35006,56.35123l-9.19534,72.44847s3.45,19.73328-1.15,24.81578c0,0,6.9,11.98441-5.75,16.58444s-19.48024-1.58626-19.48024-1.58626-9.27463-13.361-3.52461-16.80632l2.42276-142.19279L131.3168,731.99667s-1.6929,14.63472-13.497,14.79337l-14.10316,10.50677-16.10007-9.202,8.05-10.35s-4.6-11.5,5.75-13.80009L161.217,645.74621,176.167,605.496s8.05-31.05014,29.90014-43.70021V548.3366Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/><path id="bce97f6a-e88b-4fe5-bf4a-3ce22a01c084" data-name="Path 2192" d="M204.34215,481.87135h0a21.95964,21.95964,0,0,1,28.94465-4.0864l.9555.63731h0a84.76523,84.76523,0,0,1,19.84278,40.85541l4.30639,23.54492L204.3412,554.53036l-12.67085-43.70022a24.68926,24.68926,0,0,1,12.67085-28.95787Z" transform="translate(-61.33735 -57.98653)" fill="#e6e6e6"/><path id="b697c87b-19ec-4b06-9e7e-37987317631c" data-name="Path 2193" d="M293.14279,624.625a10.78347,10.78347,0,0,1-6.15132-15.34853L271.39935,590.1947l9.285-12.30355,21.64051,27.25739a10.842,10.842,0,0,1-9.18211,19.47647Z" transform="translate(-61.33735 -57.98653)" fill="#ffb8b8"/><path id="ff802918-ea99-4deb-b868-19882644d1b6" data-name="Path 2194" d="M227.34133,499.12145s-3.45-23.00013,8.05-21.8501,28.75015,64.40032,28.75015,64.40032l31.05014,51.75025-14.95006,8.05-36.80019-46.00022Z" transform="translate(-61.33735 -57.98653)" fill="#e6e6e6"/><path id="b2ad01c3-7822-402f-83d4-81805929538c" data-name="Path 2195" d="M248.40115,465.49653H193.639a4.25351,4.25351,0,0,1-4.24879-4.24879V437.64335a31.62987,31.62987,0,1,1,63.25975,0h0v23.60439A4.25351,4.25351,0,0,1,248.40115,465.49653Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/><circle id="fe1c8106-5903-484b-8252-f0483d4636a1" data-name="Ellipse 430" cx="166.18271" cy="383.77056" r="23.18989" fill="#ffb8b8"/><path id="e2d5a9f4-7ffc-4596-9574-3d578968164c" data-name="Path 2196" d="M260.13255,440.00379H230.94808l-.29929-4.80775-1.49651,4.80775H224.659l-.59293-9.52863-2.96565,9.52863h-8.69493v-.47209c0-13.79629,9.7826-25.02065,21.81046-25.02065h4.11472c12.02406,0,21.81046,11.22436,21.81046,25.02065Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/><path id="aa8282a4-b0b8-48ea-8496-97297549be2f" data-name="Path 2197" d="M232.15663,469.903a7.01819,7.01819,0,0,1-1.065-.08215l-34.73715-5.35064V428.28939a13.936,13.936,0,0,1,13.936-13.936h24.3031l-.94418.964c-13.17126,13.40731-3.248,35.15449,3.839,46.92553a4.6378,4.6378,0,0,1-.47209,5.49606,6.30989,6.30989,0,0,1-4.85777,2.1669Z" transform="translate(-61.33735 -57.98653)" fill="#2f2e41"/></g></svg>
		 </div>
		 </div>
		 </div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
