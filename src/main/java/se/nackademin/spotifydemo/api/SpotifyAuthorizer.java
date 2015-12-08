package se.nackademin.spotifydemo.api;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class SpotifyAuthorizer {

    /* Set a state. This is used to prevent cross site request forgeries. */
    public final String state = "xsrf-helenas-heliga-hemlighet-" + Instant.now().toString();

    final String clientId = "e7de8ad077504d6aaf3d827589f5d51b";
    final String clientSecret = "c45956c52cf8497aabea8a8075f00e5c";
    final String redirectURI = "http://localhost:8080/callback";

    private final Api api = Api.builder()
            .clientId(clientId)
            .clientSecret(clientSecret)
            .redirectURI(redirectURI)
            .build();


    public Api getApi() {
        return api;
    }

    public String getURL() {
        /* Set the necessary scopes that the application will need from the user */
        final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");

        String authorizeURL = api.createAuthorizeURL(scopes, state);

        /* Continue by sending the user to the authorizeURL, which will look something like
         https://accounts.spotify.com:443/authorize?client_id=5fe01282e44241328a84e7c5cc169165&response_type=code&redirect_uri=https://example.com/callback&scope=user-read-private%20user-read-email&state=some-state-of-my-choice
        */
        return authorizeURL;
    }

    public void getAndSetSpotifyTokens(String code) {
        /* Application details necessary to get an access token */
        //final String code = "<insert code>";

        /* Make a token request. Asynchronous requests are made with the .getAsync method and synchronous requests
        * are made with the .get method. This holds for all type of requests. */
        final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

        /* Add callbacks to handle success and failure */
        Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() {
            @Override
            public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) {
                /* The tokens were retrieved successfully! */
                System.out.println("Successfully retrieved an access token! " + authorizationCodeCredentials.getAccessToken());
                System.out.println("The access token expires in " + authorizationCodeCredentials.getExpiresIn() + " seconds");
                System.out.println("Luckily, I can refresh it using this refresh token! " +     authorizationCodeCredentials.getRefreshToken());

                /* Set the access token and refresh token so that they are used whenever needed */
                api.setAccessToken(authorizationCodeCredentials.getAccessToken());
                api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            }

            @Override
            public void onFailure(Throwable throwable) {
                /* Let's say that the client id is invalid, or the code has been used more than once,
                * the request will fail. Why it fails is written in the throwable's message. */
                System.out.println(throwable.getMessage());
            }
        });
    }

}
