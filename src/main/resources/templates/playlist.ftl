<#include "header.ftl">

    <h1>Playlist: ${playlist.name}</h1>

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
