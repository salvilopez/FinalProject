package com.ssl.finalproject.model;

import io.swagger.annotations.ApiModelProperty;

public class EmailUser {

    @ApiModelProperty("Destino email para recuperar password")
   private String destino;

    @ApiModelProperty("asunto email")
    private String subject;

    @ApiModelProperty("contenido email")
    private  String content;


    public EmailUser(String destino, String subject, String content) {
        this.destino = destino;
        this.subject = subject;
        this.content = content;
    }

    public EmailUser() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "emailUser{" +
                "destino='" + destino + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
