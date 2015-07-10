package com.yatin.config;

import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

import java.net.URL;

import javax.servlet.ServletException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.yatin.utils.Exceptions;

public class BaseFeatureTest {

    private final static String CONTEXT_PATH = "/bookstore";
    private final static String DEPLOYMENT_NAME = "Rest Service Application";
    private final static int PORT = 9933;
    private final static String HOST = "localhost";
    private final static String PROTOCOL = "http";
    protected final static String baseUrl = Exceptions.uncheck(() -> new URL(PROTOCOL, HOST, PORT, CONTEXT_PATH).toString());

    private static ApplicationServer applicationServer = new ApplicationServer(PORT, HOST);

    protected final static ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void classSetUp() throws ServletException {
        DeploymentInfo di = applicationServer.deployApplication(BookServiceApplication.class)
                .setClassLoader(ApplicationServer.class.getClassLoader())
                .setContextPath(CONTEXT_PATH)
                .setDeploymentName(DEPLOYMENT_NAME)
                .addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));
        applicationServer.deploy(di);
    }

    protected GetRequest get(String relativeUrl) {
        return Unirest.get(baseUrl + relativeUrl);
    }

    protected HttpRequestWithBody post(String relativeUrl) {
        return Unirest.post(baseUrl + relativeUrl);

    }

    protected HttpRequestWithBody put(String relativeUrl) {
        return Unirest.put(baseUrl + relativeUrl);
    }

}
