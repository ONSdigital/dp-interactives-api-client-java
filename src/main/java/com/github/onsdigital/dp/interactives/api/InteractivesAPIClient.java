package com.github.onsdigital.dp.interactives.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.onsdigital.dp.interactives.api.exceptions.*;
import com.github.onsdigital.dp.interactives.api.models.Interactive;
import com.github.onsdigital.dp.interactives.api.models.InteractiveFilter;
import com.github.onsdigital.dp.interactives.api.models.InteractiveMetadata;
import com.github.onsdigital.dp.interactives.api.models.PatchRequest;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class InteractivesAPIClient implements Client {
    private String hostname;
    private String authToken;
    private CloseableHttpClient httpClient;
    private static final String FILTER_PARAM = "filter";
    private static final ObjectMapper json = new ObjectMapper();

    public InteractivesAPIClient(String hostname, String authToken) {
        this.hostname = hostname;
        this.authToken = authToken;
        httpClient = HttpClients.createDefault();
    }

    @Override
    public void deleteInteractive(String interactiveId) {
        CloseableHttpResponse httpResponse;
        String uri = "/v1/interactives/" + interactiveId;
        try {
            HttpDelete request = new HttpDelete(hostname + uri);
            request.addHeader("Authorization", "Bearer " + authToken);

            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            throw new ConnectionException("error talking to interactives api", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            return;
        }

        String body;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            body = "ERROR GETTING BODY FROM RESPONSE OBJECT";
        }

        switch (statusCode) {
            case HttpStatus.SC_NOT_FOUND:
                throw new NoInteractivesInCollectionException("No interactives found with id: " + interactiveId);
          case HttpStatus.SC_FORBIDDEN:
                throw new UnauthorizedException("You are not authorized to delete interactives");
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServerErrorException("Server error returned from interactives api: " + body);
            default:
                throw new UnexpectedResponseException("Unexpected error from interactives api: " + body);
        }
    }

    @Override
    public Interactive getInteractive(String interactiveId) {
        CloseableHttpResponse httpResponse;
        String uri = "/v1/interactives/" + interactiveId;
        try {
            HttpGet request = new HttpGet(hostname + uri);
            request.addHeader("Authorization", "Bearer " + authToken);

            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            throw new ConnectionException("error talking to interactives api", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        String body;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
            Interactive ixs = json.readValue(body, Interactive.class);
            if (statusCode == HttpStatus.SC_OK) {
                return ixs;
            }
        } catch (Exception e) {
            body = "ERROR GETTING BODY FROM RESPONSE OBJECT";
        }

        switch (statusCode) {
            case HttpStatus.SC_NOT_FOUND:
                throw new NoInteractivesInCollectionException("No interactives found: " + interactiveId);
            case HttpStatus.SC_FORBIDDEN:
                throw new UnauthorizedException("You are not authorized to access interaction");
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServerErrorException("Server error returned from interactives api: " + body);
            default:
                throw new UnexpectedResponseException("Unexpected error from interactives api: " + body);
        }
    }

    @Override
    public Interactive[] getInteractivesInCollection(String collectionId) {
        CloseableHttpResponse httpResponse;
        String uri = "/v1/interactives";
        try {
            HttpGet request = new HttpGet(hostname + uri);
            request.addHeader("Authorization", "Bearer " + authToken);

            // set filter
            InteractiveMetadata md = new InteractiveMetadata();
            md.setCollectionId(collectionId);
            InteractiveFilter filter = new InteractiveFilter();
            filter.setMetadata(md);

            request.setURI(new URIBuilder(request.getURI())
            .addParameter(FILTER_PARAM, jsonSerialise(filter))
            .build());

            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            throw new ConnectionException("error talking to interactives api", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        String body;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
            Interactive[] ixs = json.readValue(body, Interactive[].class);
            if (statusCode == HttpStatus.SC_OK) {
                return ixs;
            }
        } catch (Exception e) {
            body = "ERROR GETTING BODY FROM RESPONSE OBJECT";
        }

        switch (statusCode) {
            case HttpStatus.SC_NOT_FOUND:
                throw new NoInteractivesInCollectionException("No interactives found in collection: " + collectionId);
            case HttpStatus.SC_CONFLICT:
                throw new InteractiveInvalidStateException("interactives in collection: " + collectionId + " not in a publishable state");
            case HttpStatus.SC_FORBIDDEN:
                throw new UnauthorizedException("You are not authorized to publish collections");
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServerErrorException("Server error returned from interactives api: " + body);
            default:
                throw new UnexpectedResponseException("Unexpected error from interactives api: " + body);
        }
    }

    @Override
    public void publishCollection(String collectionId) {
        CloseableHttpResponse httpResponse;
        String uri = "/v1/collection/" + collectionId;
        try {
            HttpPatch request = new HttpPatch(hostname + uri);
            request.addHeader("Authorization", "Bearer " + authToken);

            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            throw new ConnectionException("error talking to interactives api", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            return;
        }

        String body;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            body = "ERROR GETTING BODY FROM RESPONSE OBJECT";
        }

        switch (statusCode) {
            case HttpStatus.SC_NOT_FOUND:
                throw new NoInteractivesInCollectionException("No interactives found in collection: " + collectionId);
            case HttpStatus.SC_CONFLICT:
                throw new InteractiveInvalidStateException("interactives in collection: " + collectionId + " not in a publishable state");
            case HttpStatus.SC_FORBIDDEN:
                throw new UnauthorizedException("You are not authorized to publish collections");
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServerErrorException("Server error returned from interactives api: " + body);
            default:
                throw new UnexpectedResponseException("Unexpected error from interactives api: " + body);
        }
    }

    @Override
    public void linkInteractiveToCollection(String interactiveId, String collectionId) {
        CloseableHttpResponse httpResponse;
        String uri = "/v1/interactives/" + interactiveId;
        try {
            HttpPatch request = new HttpPatch(hostname + uri);
            request.addHeader("Authorization", "Bearer " + authToken);

            InteractiveMetadata md = new InteractiveMetadata();
            md.setCollectionId(collectionId);
            Interactive interactive = new Interactive();
            interactive.setMetadata(md);
            PatchRequest pr = new PatchRequest();
            pr.setInteractive(interactive);
            addBody(pr, request); 
            
            httpResponse = httpClient.execute(request);
        } catch (Exception e) {
            throw new ConnectionException("error talking to interactives api", e);
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            return;
        }

        String body;
        try {
            body = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            body = "ERROR GETTING BODY FROM RESPONSE OBJECT";
        }

        switch (statusCode) {
            case HttpStatus.SC_NOT_FOUND:
                throw new NoInteractivesInCollectionException("No interactives found with id: " + interactiveId);
          case HttpStatus.SC_FORBIDDEN:
                throw new UnauthorizedException("You are not authorized to delete interactives");
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
                throw new ServerErrorException("Server error returned from interactives api: " + body);
            default:
                throw new UnexpectedResponseException("Unexpected error from interactives api: " + body);
        }
    }

    private String jsonSerialise(Object object) throws JsonProcessingException, UnsupportedEncodingException {
        String body = json.writeValueAsString(object);
        return body;
    }

    private void addBody(Object object, HttpEntityEnclosingRequestBase httpRequest) throws JsonProcessingException, UnsupportedEncodingException {

        String body = json.writeValueAsString(object);
        StringEntity stringEntity = new StringEntity(body);
        httpRequest.setEntity(stringEntity);
    }
}
