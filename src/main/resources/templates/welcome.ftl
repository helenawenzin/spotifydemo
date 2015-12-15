

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
        <div class="col-sm-8 text-left" id="middleEarth">


            <h1>Welcome to your Spotify page</h1>

            <p>Today is: ${time?date}
                <br>

                <h2>Your playlists</h2>

                <#list playlists as playlist>
                <p>
                <div style="vertical-align: middle">
                    <#list playlist.images as image>
                        <img src="${image.url}" width="100"/>
                        <#break>
                    </#list>
                    <a href="/playlist?id=${playlist.id}&owner=${playlist.owner.id}">${playlist.name}</a>
                </div>
                <#else>
                <p>No playlists
                </#list>

<#include "footer.ftl">
