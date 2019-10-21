package org.fasttrackit.todolist.transfer;

public class UpdateToDoItemRequest {
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    private boolean done;

    @Override
    public String toString() {
        return "UpdateToDoItemRequest{" +
                "done=" + done +
                '}';
    }
}
