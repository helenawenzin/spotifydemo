<#include "header.ftl">

    <div class="container-fluid text-center">
        <div class="row content">
            <div class="col-sm-2 sidenav" id="leftsidebar">
                </p>
                <hr>
                <h3>Party with Helena!</h3>

                <iframe src="https://embed.spotify.com/?uri=spotify:user:113111420:playlist:6HeJn9ByWHLtNaB9OcXQxL" width="250" height="80" frameborder="0" allowtransparency="true"></iframe>

            </div>
            <div class="col-sm-8 text-left">


    <h1>Artist: ${artist.name}</h1>

    <img src="${image.url}" width="${image.width}" height="${image.height}"/>

    <p> Available albums for this artist:
    <br>
    <#list albums as album>
        <p> <#t> * ${album.name}
    <#else>
        <p>No albums available
    </#list>

<#include "footer.ftl">


