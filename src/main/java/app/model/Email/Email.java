package app.model.Email;

import app.model.Exceptions.InvalidEmailException;

public class Email {

    //Parameters
    private String user;
    private String pass;
    private String subject;
    private String receiver;
    private String message;
    private String filePath;
    private String fileName;

    public Email(){

        this.user = "viandasya.c1i@gmail.com";
        this.pass = "ktmlnpbqckpkbtcd";
        this.subject  = "";
        this.receiver = "";
        this.message  = "";
        this.filePath = "";
        this.fileName = "";

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

    public String getFileName() { return fileName; }

    public String getFilePath() {  return filePath; }

    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public void setFilePath(String filePath) { this.filePath = filePath; }

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
