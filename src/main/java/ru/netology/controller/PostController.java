package ru.netology.controller;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        sendResponse(data, response);
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        try {
            final var data = service.getById(id);
            sendResponse(data, response);
        } catch (NotFoundException e) {
            notFound(response);
        }
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        try {
            final var data = service.save(post);
            sendResponse(data, response);
        } catch (NotFoundException e) {
            notFound(response);
        }
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        try {
            service.removeById(id);;
            sendResponse(true, response);
        } catch (NotFoundException e) {
            notFound(response);
        }
    }

    private void sendResponse(Object data, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    private void notFound(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
