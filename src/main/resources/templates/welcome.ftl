<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Spotify demo page</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
            width: inherit;
            position: relative;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>

    <!-- My own stylesheet -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">

</head>

<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Spotify demo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav" id="leftsidebar">
            </p>
            <hr>
            <h3>Parta med Helena!</h3>

            <iframe src="https://embed.spotify.com/?uri=spotify:user:113111420:playlist:6HeJn9ByWHLtNaB9OcXQxL" width="250" height="80" frameborder="0" allowtransparency="true"></iframe>

        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome</h1>
            <p>Date: ${time?date}
                <br>
                Time: ${time?time}
                <br>
                Message: ${message}
                <br>
                Album: ${album}
                <br>
                Playlist: ${playlist}
                <br>
                <br>
                Playlists available:

                <#list playlists as playlist>
                <p><a href="#">${playlist.name}</a>
                <#else>
                <p>No playlists
                </#list>

        </div>
        <div class="col-sm-2 sidenav">

                <button type="button" class="btn btn-danger" id="logoutbutton">Logout</button>

        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>This page belongs to:  ${name}</p>
</footer>

</body>

</html>