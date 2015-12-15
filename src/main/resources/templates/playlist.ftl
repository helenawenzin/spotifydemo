
<#include "header.ftl">

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav" id="leftsidebar">
            </p>
            <hr>
            <h3>Playlist of your choice!</h3>

            <iframe src="https://embed.spotify.com/?uri=spotify:user:${playlist.owner.id}:playlist:${playlist.id}" width="250" height="80" frameborder="0" allowtransparency="true"></iframe>

        </div>
        <div class="col-sm-8 text-left">


    <h1>Playlist: ${playlist.name}</h1>
    <#list playlist.images as image>
        <img src="${image.url}" width="200"/>
        <#break>
    </#list>

    <#list playlist.tracks.items as track>
    <p>${track.track.name} by
        <#list track.track.artists as artist>
        <a href="/artist?id=${artist.id}">${artist.name}</a>
        <#else>
        <p>No artists
        </#list>

    <#else>
    <p>No tracks
    </#list>

<#include "footer.ftl">
