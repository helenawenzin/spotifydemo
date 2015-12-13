<#include "header.ftl">

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
                <p><a href="/playlist?id=${playlist.id}">${playlist.name}</a>
                <#else>
                <p>No playlists
                </#list>

<#include "footer.ftl">
