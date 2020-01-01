<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=zh-CN>
<head>
<meta charset="UTF-8">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Cache" content="no-cache">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css"
	href="../assets/bootstrap-4.1.3/css/bootstrap.min.css" />
<script src="../assets/bootstrap-4.1.3/css/bootstrap.min.css"></script>

<title>爱音乐</title>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<a class="navbar-brand" href="index.html">爱音乐</a>
		<ul class="navbar-nav px-3 bg-danger rounded">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="../login"> 登录 </a></li>
		</ul>
		<ul class="navbar-nav px-3 bg-danger rounded">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="../logout"> <%=session.getAttribute("username")%> 退出
			</a></li>
		</ul>
	</nav>

	<div role="main" class="container">
		<div id="app" class="mt-3">
			<div class="jumbotron">
				<h2 class="display-4">爱音乐</h2>
				<p class="lead">同学，回家听歌了... 顺便写写代码。</p>
				<hr class="my-4">
				<audio id="musicplayer" controls>
					<source id="musicsource"
						src="http://service.uspacex.com/music.server/music?md5=77008b41f4c692808ac7b414722269e0"
						type="audio/mpeg"></source>
				</audio>
			</div>
			<div class="my-3 p-3 bg-white rounded shadow-sm"
				v-for="musicsheet in musicsheets">
				<h4 class="border-bottom border-gray pb-2 mb-1">{{musicsheet.name}}</h4>
				<p
					class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
					<strong class="d-block text-gray-dark">Creator:
						{{musicsheet.creator}}</strong> <strong class="d-block text-gray-dark">Date
						created: {{musicsheet.dateCreated}}</strong>
				</p>

				<table class="table table-sm">
					<thead>
						<tr>
							<th>Name</th>
							<th>MD5</th>
							<th>Operation</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="name, md5 in musicsheet.musicItems">
							<td>{{name}}</td>
							<td>{{md5}}</td>
							<td><button type="button" class="btn btn-info btn-sm"
									v-on:click="loadAndPlay(name, md5)">PLAY</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		new Vue({
			el: '#app',
			data () {
		  	  return {
		    	  musicsheets: null,
		    	}	
		  	},
		  	mounted () {
		    	axios.get('http://service.uspacex.com/music.server/queryMusicSheets?type=top20')
				.then(response => (this.musicsheets = response.data.musicSheetList))
			  	.catch(function (error) { 
		    	    console.log(error);
		    	});
		  	},
		  	methods: {
		  		loadAndPlay: function (name, md5) {
		  			console.log("Load and Play." + name + "(" + md5 + ")");
		  			document.getElementById('musicsource').src = 'http://service.uspacex.com/music.server/music?md5=' + md5;
		  			document.getElementById('musicplayer').load();
		  			document.getElementById('musicplayer').play();
		  		}
		  	}
		})
		
		function loadAndPlay(src) {
			
		}   
	</script>
</body>
</html>