package viandasYaModel.Email;

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

}
