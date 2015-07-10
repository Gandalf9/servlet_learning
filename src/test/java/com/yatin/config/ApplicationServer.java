package com.yatin.config;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import com.yatin.resource.AuthorResource;
import com.yatin.resource.BookResourceV1;
import com.yatin.resource.BookResourceV2;

public class ApplicationServer {

    private final UndertowJaxrsServer server = new UndertowJaxrsServer();

    public ApplicationServer(Integer port, String host) {
        Undertow.Builder serverBuilder = Undertow.builder().addHttpListener(port, host);
        server.start(serverBuilder);
    }

    public DeploymentInfo deployApplication(Class<? extends Application> applicationClass) {
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
        deployment.setApplicationClass(applicationClass.getName());
        deployment.setResourceClasses(new ArrayList<String>() {{
            add(BookResourceV1.class.getName());
            add(BookResourceV2.class.getName());
            add(AuthorResource.class.getName());
        }});

        ApplicationPath appPath = (ApplicationPath) applicationClass.getAnnotation(ApplicationPath.class);
        String path = "/";
        if (appPath != null) {
            path = appPath.value();
        }

        return server.undertowDeployment(deployment, path);
    }

    public void deploy(DeploymentInfo deploymentInfo) throws ServletException {
        server.deploy(deploymentInfo);
    }

}
