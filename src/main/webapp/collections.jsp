<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<!-- Bootstrap CSS-->
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome CSS-->
<link rel="stylesheet"
	href="vendor/font-awesome/css/font-awesome.min.css">
<!-- Fontastic Custom icon font-->
<link rel="stylesheet" href="css/fontastic.css">
<!-- Google fonts - Roboto -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700">
<!-- jQuery Circle-->
<link rel="stylesheet"
	href="css/grasp_mobile_progress_circle-1.0.0.min.css">
<!-- Custom Scrollbar-->
<link rel="stylesheet"
	href="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.css">
<!-- theme stylesheet-->
<link rel="stylesheet" href="css/style.default.css"
	id="theme-stylesheet">
<!-- Custom stylesheet - for your changes-->
<link rel="stylesheet" href="css/custom.css">
<!-- Favicon-->
<link rel="shortcut icon" href="img/favicon.ico">
<script src="js/angular/1.6.3/angular.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
<style>
#player {
	position: fixed;
	bottom: 10px;
	right: 40px;
	z-index: 99999;
}

#header {
	position: fixed;
	bottom: 0px;
	right: 0px;
	width: 1460px;
	height: 80px;
	z-index: 88888;
	opacity: 0.50;
}

audio {
	backgroud-color: black;
	color: green;
	width: 1240px;
	height: 45px;
	opacity: 1.0;
}
</style>
<title>爱音乐</title>
</head>
<body ng-app="musicApp">
	<div id="player">
		<audio id="musicplayer" controls>
			<source id="musicsource"
				src="http://service.uspacex.com/music.server/music?md5=77008b41f4c692808ac7b414722269e0"
				type="audio/mpeg"></source>
		</audio>
	</div>
	<!-- playerbottom-->
	<div id="header">
		<header class="header">
			<nav class="navbar" style="height: 160px"></nav>
		</header>
	</div>
<body ng-app="musicApp">
	<!-- Side Navbar -->
	<nav class="side-navbar">
		<div class="side-navbar-wrapper">
			<!-- Sidebar Header    -->
			<div
				class="sidenav-header d-flex align-items-center justify-content-center">
				<!-- User Info-->
				<div class="sidenav-header-inner text-center">
					<c:choose>
						<c:when test="${empty username}">
							<a href="login.jsp"><h2 class="h5">请登录</h2></a>
							<li><span>登录后体验更多功能</span></li>
						</c:when>
						<c:otherwise>
							<h2 class="h5">${sessionScope.username}</h2>
							<li><span>你好！</span></li>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- Small Brand information, appears on minimized sidebar-->
				<div class="sidenav-header-logo">
					<a href="index.jsp" class="brand-small text-center"> <strong>爱音乐</strong></a>
				</div>
			</div>
		</div>
		<!-- Sidebar Navigation Menus-->
		<div class="main-menu">
			<h5 class="sidenav-heading">MENU</h5>
			<ul id="side-main-menu" class="side-menu list-unstyled">
				<c:choose>
					<c:when test="${empty username}">
						<script type="text/javascript" language="javascript">
						alert("您还没有登录，请先登录！");
						window.document.location.href="index.jsp";</script>
					</c:when>
				</c:choose>
				<li><a href="index.jsp"> <i class="icon-home"></i>音乐广场
				</a></li>
				<li><a href="sheetmanager.jsp"> <i class="icon-form"></i>歌单管理
				</a></li>
				<li><a href="rank.jsp"> <i class="fa fa-bar-chart"></i>播放排行
				</a></li>
				<li class="active"><a href="collections.jsp"> <i
						class="icon-grid"></i>我的收藏
				</a></li>
				<li><a href="login.jsp"> <i class="icon-interface-windows"></i>切换账号
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="page">
		<!-- navbar-->
		<header class="header">
			<nav class="navbar">
				<div class="container-fluid">
					<div
						class="navbar-holder d-flex align-items-center justify-content-between">
						<div class="navbar-header">
							<a id="toggle-btn" href="#" class="menu-btn"><i
								class="icon-bars"> </i></a><a href="index.html" class="navbar-brand">
								<div class="brand-text d-none d-md-inline-block">
									<strong class="text-primary">爱音乐</strong>
								</div>
							</a>
						</div>
						<!-- Log in / out-->
						<c:choose>
							<c:when test="${empty username}">
								<li><a href="login.jsp"> <spans
											class="d-none d-sm-inline-block">登录</span> <i
											class="fa fa-sign-out"></i></spans></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/logout">
										<spans class="d-none d-sm-inline-block">退出</span> <i
											class="fa fa-sign-out"></i></spans>
								</a></li>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</nav>
		</header>
		<!-- Breadcrumb-->
		<div class="breadcrumb-holder">
			<div class="container-fluid">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.jsp">主页</a></li>
					<li class="breadcrumb-item active">我的收藏</li>
				</ul>
			</div>
		</div>
		<section class="forms">
			<div class="container-fluid">
				<!-- Page Header-->
				<header>
					<h1 class="h3 display">我的收藏</h1>
				</header>
				<!-- Counts Section -->
				<div class="container-fluid">
					<div role="main" class="container">
						<div id="app" class="mt-3">


							<div class="my-3 p-3 bg-white rounded shadow-sm">
								<h4>我的收藏</h4>

								<table class="table table-striped">
									<thead>
										<tr>
											<th>音乐名称</th>
											<th>所在歌单</th>
											<th>创建者</th>
											<th>创建时间</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="collection in collections">
											<td>{{collection.musicname}}</td>
											<td>{{collection.sheetname}}</td>
											<td>{{collection.creatorname}}</td>
											<td>{{collection.datecreated}}</td>

											<td><button type="button" class="btn btn-info btn-sm"
													v-on:click="loadAndPlay(collection.musicname, collection.md5value)">PLAY</button></td>
											<td><button type="button" class="btn btn-info btn-sm"
													v-on:click="deleteCollection(collection.md5value, collection.uuid)">DELETE</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<script type="text/javascript">
		new Vue({
			el: '#app',
			data () {
		  	  return {
		    	  collections: null,
		    	}	
		  	},
		  	mounted () {
		    	axios.get('queryCollections?type=byUserid')
				.then(response => (this.collections = response.data.collectionsList))
			  	.catch(function (error) { 
		    	    console.log(error);
		    	});
		  	},
		  	methods: {
		  		loadAndPlay: function (name, md5) {
		  			console.log("Load and Play." + name + "(" + md5 + ")");
		  			document.getElementById('musicsource').src = 'http://localhost:8080/music.server/music?md5=' + md5;
		  			document.getElementById('musicplayer').load();
		  			document.getElementById('musicplayer').play();
		  		},
		  		deleteCollection: function (md5, uuid) {
		  			axios.get('deletecollection?md5=' + md5 + "&uuid=" + uuid)
		  			axios.get('queryCollections?type=byUserid')
		  			axios.get('queryCollections?type=byUserid')
					.then(response => (this.collections = response.data.collectionsList))
				  	.catch(function (error) { 
			    	    console.log(error);
			    	});
		  			alert("删除成功！");
		  		},
		  	}
		})
		
  
	</script>
	<!-- JavaScript files-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/popper.js/umd/popper.min.js">
				
			</script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/grasp_mobile_progress_circle-1.0.0.min.js"></script>
	<script src="vendor/jquery.cookie/jquery.cookie.js">
				
			</script>
	<script src="vendor/chart.js/Chart.min.js"></script>
	<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
	<script
		src="vendor/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/charts-home.js"></script>
	<!-- Main File-->
	<script src="js/front.js"></script>
</body>
</html>