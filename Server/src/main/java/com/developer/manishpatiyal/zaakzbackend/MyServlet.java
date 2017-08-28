/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.developer.manishpatiyal.zaakzbackend;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "key=AIzaSyCEY3BUwJSnCxnnGGAXyXtHTKw2YK53xGU");
        Token tokenEntity = ofy().load().type(Token.class).list().get(0);

        JSONObject message = new JSONObject();
        message.put("to", tokenEntity.tokenId);
        message.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "Java");
        notification.put("body", "Notificação do Java");

        message.put("notification", notification);

        post.setEntity(new StringEntity(message.toString(), "UTF-8"));
        HttpResponse response = client.execute(post);
        System.out.println(response);
        System.out.println(message);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String tokenString = req.getParameter("tokenId");
        Token token = new Token();
        token.tokenId = tokenString;
        token.userId = new String("Manish");
        ofy().save().entity(token).now();
    }
}
