package viandasYaModel.Email;

import viandasYaModel.Exceptions.InvalidEmailException;

public class Email {

    //Parameters
    String user;
    String pass;
    String subject;
    String receiver;
    String message;

    public Email(){

        this.user = "viandasya.c1i@gmail.com";
        this.pass = "ktmlnpbqckpkbtcd";

    }

    public String getUser(){
        return this.user;
    }

    public String getMessage(){
        return this.message;
    }

    public String getReceiver(){
        return this.receiver;
    }

    public String getSubject(){
        return this.subject;
    }

    public String getPass(){
        return this.pass;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void composeEmailWith(String subject, String receiver, String message) {
        isAValidEmail(receiver);

        setSubject(subject);
        setReceiver(receiver);
        setMessage(message);
    }

    private void isAValidEmail(String newEmail) {
        if (!newEmail.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidEmailException();
    }

}
