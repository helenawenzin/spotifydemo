<#include "header.ftl">

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
