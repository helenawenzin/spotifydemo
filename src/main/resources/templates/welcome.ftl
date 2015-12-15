

<#include "header.ftl">

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav" id="leftsidebar">
            </p>
            <hr>
            <h3>Top tracks Sweden!</h3>

            <iframe src="https://embed.spotify.com/?uri=spotify:user:spotify:playlist:7jmQBEvJyGHPqKEl5UcEe9" width="250" height="80" frameborder="0" allowtransparency="true"></iframe>
            <br>
            <br>
            <p><b>Be up to date with what</b></p>
            <p><b>is playing right now!</b></p>
        </div>
        <div class="col-sm-8 text-left">


            <h1>Welcome to your Spotify page</h1>

            <p>Today is: ${time?date}
                <br>

                <br>
                Your playlists!

                <#list playlists as playlist>
                <p><a href="/playlist?id=${playlist.id}">${playlist.name}</a>
                <#else>
                <p>No playlists
                </#list>

<#include "footer.ftl">
