package org.fasttrackit.todolist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.todolist.service.ToDoItemService;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/to-do-items")

public class ToDoItemServlet  extends HttpServlet {

   private ToDoItemService toDoItemService=new ToDoItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        CreateToDoItemRequest request = objectMapper.readValue(req.getReader(), CreateToDoItemRequest.class);
        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " +e.getMessage());
        }
    }
}
