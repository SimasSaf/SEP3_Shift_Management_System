package group3.model;

import java.io.Serializable;

public class TransferObject implements Serializable
{
    private Object object;
    private String command;

    public TransferObject(Object object, String command)
    {
        this.object = object;
        this.command = command;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
