package org.fasttrackit.todolist.persistance;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemRepository {

    public void createToDoItem(CreateToDoItemRequest request) throws SQLException, IOException, ClassNotFoundException {
       String sql="INSERT INTO `to-do-item` (description, deadline) VALUES (?,?)";

        //try - with - resources

       try (Connection connection=DatabaseConfiguration.getConnnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql)) {

           preparedStatement.setString(1,request.getDescription());
           preparedStatement.setDate(2, Date.valueOf(request.getDeadline()));
           preparedStatement.executeUpdate();
       }

    }

    public void updateToDOItem(long id, boolean done) throws SQLException, IOException, ClassNotFoundException {
        String sql="UPDATE `to-do-item` SET done =? WHERE id=?";
        try (Connection connection=DatabaseConfiguration.getConnnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, done);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
        }

   public void deleteToDOItem(long id) throws SQLException, IOException, ClassNotFoundException {
       String sql = "DELETE FROM `to-do-item` WHERE id=? ";

       try (Connection connection = DatabaseConfiguration.getConnnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setLong(1, id);
           preparedStatement.executeUpdate();

       }
   }

   public List<ToDoItem> getToDoItems () throws SQLException, IOException, ClassNotFoundException {

        String sql="SELECT id, description, deadline, done FROM `to-do-item`";

       List<ToDoItem> toDoItems=new ArrayList<>();
       try (Connection connection = DatabaseConfiguration.getConnnection();
        Statement statement =connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql)) {

           while (resultSet.next()) {
               ToDoItem toDoItem=new ToDoItem();
               toDoItem.setId(resultSet.getLong("id"));
               toDoItem.setDescription(resultSet.getString("description"));
               toDoItem.setDeadline(resultSet.getDate("deadline").toLocalDate());
               toDoItem.setDone(resultSet.getBoolean("done"));
               toDoItems.add(toDoItem);
           }
           return toDoItems;
       }


   }
}
