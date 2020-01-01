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

<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
<style>
#container {
	height: 340px;
	width: 580px;
}

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
.scroll-father {
	height: 180px;
	width: 550px;
	overflow: auto;
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
				<li class="active"><a href="index.jsp"><i class="icon-home"></i>音乐广场
				</a></li>
				<li><a href="sheetmanager.jsp"> <i class="icon-form"></i>歌单管理
				</a></li>
				<li><a href="rank.jsp"> <i class="fa fa-bar-chart"></i>播放排行
				</a></li>
				<li><a href="collections.jsp"> <i class="icon-grid"></i>我的收藏
				</a></li>
				<li><a href="login.jsp"> <i class="icon-interface-windows"></i>切换账号
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="page">
		<!-- navbar-->
		<div>
			<header class="header">
				<nav class="navbar">
					<div class="container-fluid">
						<div
							class="navbar-holder d-flex align-items-center justify-content-between">
							<div class="navbar-header">
								<a id="toggle-btn" href="#" class="menu-btn"><i
									class="icon-bars"> </i></a><a href="index.html"
									class="navbar-brand">
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
		</div>

		<!-- Breadcrumb-->
		<div class="breadcrumb-holder">
			<div class="container-fluid">
				<ul class="breadcrumb">
					<li class="breadcrumb-item"><a href="index.jsp">主页</a></li>
					<li class="breadcrumb-item active">音乐广场</li>
				</ul>
			</div>
		</div>
		<section class="forms">
			<div class="container-fluid" id="app">
				<!-- Page Header-->
				<header>
					<h1 class="h3 display">歌单详情</h1>
				</header>
				<div class="row">
					<div class="col-lg-6">
						<div class="card">
							<div class="card-header d-flex align-items-center">
								<h4>详细信息</h4>
							</div>

							<div class="card-body">
								<div id="container">
									<div class="form-group" id="imagepanel">
										<img :src="picture" width="300px"
											style="float: right; margin-right: 50px" />
									</div>
									<div class="form-group">
										<label>歌单名称：{{name}}</label>
									</div>
									<div class="form-group">
										<label>创建者：{{creator}}</label>
									</div>
									<div class="form-group">
										<label>创建日期：{{dateCreated}}</label>
									</div>

									<div class="form-group">
										<label>图片路径：{{picture}}</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="card">
							<div class="card-header d-flex align-items-center">
								<h4>全部评论</h4>
							</div>
							<div class="card-body">
								<div id="container">
									<div class="mt-3">
										<div class="scroll-father">
											<div class="form-group" v-for="comment in comments">
												<label>评论: </label><label>{{comment.content}}</label> <br>
												<label>By: </label><label>{{comment.username}}</label> <br>
												<label>Date: </label><label>{{comment.date}}</label>
											</div>
										</div>
										<form action="#" name="login" id="myform">

											<div class="form-group">
												<label>添加评论</label> <input type="text" id="contentinput"
													style="width: 500px" class="form-control">
											</div>
											<div class="form-group">
												<br>
												<c:choose>
													<c:when test="${empty username}">
														<button id="details" type="button"
															class="btn btn-info btn-sm" style="float: left"
															v-on:click="alert()">添加</button>
													</c:when>
													<c:otherwise>
														<button id="details" type="button"
															class="btn btn-info btn-sm" style="float: left"
															v-on:click="add()">添加</button>
													</c:otherwise>
												</c:choose>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Page Header-->
				<header>
					<h1 class="h3 display">音乐广场</h1>
				</header>
				<!-- Counts Section -->
				<div class="container-fluid">
					<div role="main" class="container">
						<div class="mt-3">
							<div class="my-3 p-3 bg-white rounded shadow-sm"
								v-for="musicsheet in musicsheets">
								<h4 class="border-bottom border-gray pb-2 mb-1">{{musicsheet.name}}</h4>
								<p
									class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
									<br>
									<button id="details" type="button" class="btn btn-info btn-sm"
										style="float: right"
										v-on:click="getinfo(musicsheet.name,musicsheet.creator,musicsheet.dateCreated,musicsheet.picture,musicsheet.uuid)">查看详情</button>
									<strong class="d-block text-gray-dark">创建者:{{musicsheet.creator}}</strong>
									<br> <strong class="d-block text-gray-dark">创建时间:
										{{musicsheet.dateCreated}}</strong> <br> <strong
										class="d-block text-gray-dark">图片路径:
										{{musicsheet.picture}}</strong>
								</p>
								<table class="table table-striped">
									<thead>
										<tr>
											<th>音乐</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="name, md5 in musicsheet.musicItems">
											<td>{{name}}</td>
											<td><button type="button" class="btn btn-info btn-sm"
													v-on:click="loadAndPlay(name, md5)">PLAY</button></td>
											<td><c:choose>
													<c:when test="${empty username}">
														<button type="button" class="btn btn-info btn-sm"
															v-on:click="alert()">Like</button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-info btn-sm"
															v-on:click="collectMusic(name, md5, musicsheet.creator, musicsheet.name, musicsheet.uuid)">Like</button>
													</c:otherwise>
												</c:choose></td>
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
		    	  musicsheets: null,
		    	  comments: null,
		    	  name: null,
		    	  creator: null,
		    	  dateCreated: null,
		    	  picture: 'img/2.jpg',
		    	  uuid: null,
		    	}	
		  	},
		  	mounted () {
		    	axios.get('queryMusicSheets?type=all')
				.then(response => (this.musicsheets = response.data.musicSheetList))
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
		  			axios.get('queryRank?type=update&md5value=' + md5);
		  		},
		  		collectMusic: function (name, md5, creator, sheetname) {
		  			axios.get('collectmusic?name=' + name + "&md5=" + md5 + "&creator=" + creator + "&sheetname=" + sheetname)
		  			alert("收藏成功！");
		  		},
		  		getinfo: function (name1, creator1, dateCreated1, picture1, uuid1) {
				  	this.name = name1;
				  	this.creator = creator1;
				 	this.dateCreated = dateCreated1;
				 	this.picture = picture1;
				 	this.uuid = uuid1;
				 	console.log(this.uuid);
				 	axios.get('CommentServlet?type=all&uuid='+ this.uuid)
					.then(response => {
						this.comments = response.data.comlist;
						console.log(this.comments);
						})
				  	.catch(function (error) { 
			    	    console.log(error);
			    	});
				 	var top = document.body.scrollTop || document.documentElement.scrollTop;
			 		scrollBy(0,-top);
		  		},
		  		add: function () {
		  			var content = document.getElementById("contentinput").value;
		  			axios.get('CommentServlet?type=add&uuid='+ this.uuid +"&content=" + content)
		  			alert("添加成功！");
		  			axios.get('CommentServlet?type=all&uuid='+ this.uuid)
		  			axios.get('CommentServlet?type=all&uuid='+ this.uuid)
		  			axios.get('CommentServlet?type=all&uuid='+ this.uuid)
					.then(response => {
						this.comments = response.data.comlist;
						console.log(this.comments);
						})
				  	.catch(function (error) { 
			    	    console.log(error);
			    	});
		  		},
		  		alert: function () {
		  			alert("请先登录！");
		  		}
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